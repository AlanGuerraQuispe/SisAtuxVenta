/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.swing.mvp;

import com.aw.core.domain.model.UserInfo;
import com.aw.core.util.TimeObserver;
import com.aw.support.reflection.AttributeAccessor;
import com.aw.swing.context.SwingContext;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.HeaderPanel;
import com.aw.swing.mvp.ui.SwingUtils;
import com.aw.swing.mvp.ui.common.DlgMensaje;
import com.aw.swing.mvp.ui.msg.MessageDisplayer;
import com.aw.swing.mvp.ui.msg.MsgDisplayer;
import com.aw.swing.mvp.view.View;
import com.aw.swing.mvp.view.ViewLayout;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fest.swing.fixture.DialogFixture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Class used when the View will be represented by JDialogs.
 * User: gmc
 * Date: 02-nov-2007
 */
public class JDialogView extends View {
    protected final Log logger = LogFactory.getLog(getClass());

    public MainFormLayout viewLayout;
    public DialogFixture dialogFixture;

    ImageIcon imageIcon = new ImageIcon(JDialogView.class.getResource("/images/dlg-sumi.png"));
    ImageIcon imageIconBig = new ImageIcon(JDialogView.class.getResource("/images/dlg-sumi-big.png"));

    private Dimension size;

    public JDialogView(Presenter pst, Object vsr) {
        super(pst, vsr);
    }


    public void init() {
        initializeLayout();
        createJDlg();
        initializeDlg();
    }

    private void initializeLayout() {
        if (!(vsr instanceof JDialog)) {
            UserInfo ui = pst.getApplicationUser();
            String userName = ui.getUsername();
            viewLayout = new MainFormLayout(getTitle(), "system1".equals(userName)?" ":userName);

            JPanel formPanel = (JPanel) AttributeAccessor.get(vsr, "pnlMain");
            formPanel.putClientProperty("pnlMain", true);

/*
            JPanel footerPanel = (JPanel) AttributeAccessor.get(vsr, "footerPanel");
            Component[]  components=footerPanel.getComponents();
            for(int i=0;i<components.length;i++){
                if(components[i] instanceof JLabel){
                    ((JLabel)components[i]).setBackground(new Color(-13395480));
                    ((JLabel)components[i]).setOpaque(true);
                    ((JLabel)components[i]).setForeground(new Color(-1));
                }
            }
*/

            viewLayout.contentPanel.add(formPanel, BorderLayout.CENTER);



            AbstractAction aa = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    if (logger.isDebugEnabled())
                        logger.debug("Closing view for " + pst);
                    pst.closeView();
                }
            };

            if (pst instanceof FindPresenter || pst.isShowCloseButton()) {
                logger.info("Close button added to view");
//                ((HeaderPanel) viewLayout.headerPanel).createCloseButton(aa);
            }
            addUserInfo();
        }
    }

    private void addUserInfo() {
        UserInfo ui = pst.getApplicationUser();
    }

    private void createJDlg() {
        JDialog dlg;
        if (vsr instanceof JDialog) {
            dlg = (JDialog) vsr;
        } else {
            //jcv2.0.1
            if (pst.isEmbeddedView()){
                parentContainer = vsr;
            } else {
                dlg = assembleDialog();
                dlg.setName(getDlgName(vsr.getClass().getName()));
                parentContainer = dlg;
            }
        }
    }

    private static String getDlgName(String fieldName) {
        if (fieldName.indexOf(".") == -1) {
            return fieldName;
        } else {

            return fieldName.substring(fieldName.lastIndexOf(".") + 1, fieldName.length());
        }
    }

    private JDialog assembleDialog() {
        String title = getTitle();
        JDialog dlg = createDialog(title);

        boolean showAudit = showAuditFields();

        int headerHeight = 0;
//        int headerHeight = (int) viewLayout.headerPanel.getPreferredSize().getHeight();
        int auditInfoHeight = 0;//(int) viewLayout.auditInfo.getPreferredSize().getHeight();
        int toolbarHeight = 0;
        int errorHeight = 0;
        int contentHeight = (int) viewLayout.contentPanel.getPreferredSize().getHeight();
        int contentWidth = (int) viewLayout.contentPanel.getPreferredSize().getWidth();
        int footerHeight = 0;
        int delta=40;
/*        if(contentHeight<=450){
            delta=-160;
        }else if (contentHeight<=700){
            delta=40;
        }*/


        dlg.setSize(contentWidth, (showAudit ? -14 : -14) + headerHeight + toolbarHeight
                + auditInfoHeight + errorHeight + contentHeight + footerHeight+delta);

        return dlg;
    }

    private void installMouseAdapter(JDialog dlg) {
        MouseDragAdapter mouseAdapter = new MouseDragAdapter(dlg);
        viewLayout.headerPanel.addMouseListener(mouseAdapter);
        viewLayout.headerPanel.addMouseMotionListener(mouseAdapter);
    }

    private void installFestFixture(JDialog dlg) {
        if (System.getProperty("test.mode") != null) {
            if (SwingContext.getFixture() == null) {
                dialogFixture = new DialogFixture(dlg);
                SwingContext.setFixture(dialogFixture);
            }
        }
    }

    private JDialog createDialog(String title) {
        JDialog dlg = null;
        Presenter currentPst = AWWindowsManager.instance().getCurrentPst();
//        boolean thrower = (currentPst!=null && currentPst.getClass().getSimpleName().equals("AbrirDocumentoPst"));
//        if (thrower){
//            dlg = new JDialog(null, title, Dialog.ModalityType.MODELESS);
//        }else
        if (AWWindowsManager.instance().isInMainWindow() || (currentPst == null)) {
            dlg = new JDialog(AWWindowsManager.instance().getFrame(), title, Dialog.ModalityType.DOCUMENT_MODAL);
        } else {
            JDialog parent = (JDialog) ((View) currentPst.getView()).getParentContainer();
            dlg = new JDialog(parent, title, Dialog.ModalityType.DOCUMENT_MODAL);
        }
        setupIcons(dlg);
        dlg.setUndecorated(true);
        dlg.getContentPane().setLayout(new BorderLayout());

        installFestFixture(dlg);

//        installMouseAdapter(dlg);

        return dlg;
    }

    public boolean showAuditFields() {
        Object obj = pst.getBackBean();
//        if (obj instanceof Auditable &&  !(pst instanceof FindPresenter))
        if (pst.isShowAuditInfo()) {
            viewLayout.showAuditableFields(obj);
//            viewLayout.showAuditableFields(new BNMantenimientoFlt());
            return true;
        }
        return false;
    }

    private void initializeDlg() {
        if (pst.isEmbeddedView()) {
            return;
        }
        JDialog dlg = (JDialog) parentContainer;

        dlg.add(viewLayout.mainPanel, BorderLayout.CENTER);

//        SwingUtils.locateOnScreenCenter(dlg);
        SwingUtils.locateRelativeToMenu(dlg);
        dlg.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dlg.addWindowListener(new java.awt.event.WindowAdapter() {
            // todo ver cómo se va a hacer esto en la versión Filthy
            public void windowOpened(WindowEvent e) {
                if (pst != null) {
                    pst.onWindowsOpened(e);
                    pst.onWindowsOpenedInternalOnlyForAWFW(e);
                    if (!pst.isReadOnly()) {
//                        pst.setFocusToCmpOnWindowOpen();
//                        ActionManager.instance().getVisualMgrForActions().repaintMainDisabledActions(pst.getActionRsr());
                    } else {
                        pst.configureAsReanOnly();
                        JButton btnCancel = (JButton) pst.getIpView().getComponent("btnCancel");
                        if (btnCancel != null) {
                            btnCancel.requestFocusInWindow();
                        }
                    }
                }
            }

            public void windowClosing(WindowEvent e) {
                MsgDisplayer.showMessage("sw.common.closeWindowDisabled");
            }

            public void windowActivated(WindowEvent e) {
                Object oppositeWindow =e.getOppositeWindow();

                if (oppositeWindow instanceof DlgMensaje){
                    logger.debug("Ignoring Windows activated for DlgMensaje");
                    return;
                }
                if (oppositeWindow instanceof JDialog){
                    JDialog dlg = (JDialog)oppositeWindow;
                    if(MessageDisplayer.GENERIC_MESSAGE_TITLE.equals(dlg.getTitle())){
                        logger.debug("Ignoring Windows activated for System Msg");
                        return;
                    }
                }
                logger.debug("Windows Activated:"+pst);
                AWWindowsManager.instance().setPresenterActivated(pst);
            }
        });
    }

    public void show() {
        if (pst.isEmbeddedView()) {
            return;
        }

        TimeObserver time = new TimeObserver("Show Dialog");
        time.empezar();
        JDialog dlg = (JDialog) parentContainer;

        time.terminar();

        if (dialogFixture == null) {
            dlg.setModal(true);
            dlg.setVisible(true);
        }
    }


    public void close() {
        JDialog dlg = (JDialog) parentContainer;
        dlg.setVisible(false);
        dlg.dispose();
    }

    public ViewLayout getLayout() {
        return viewLayout;
    }

    public void hide() {
        JDialog dlg = (JDialog) parentContainer;
        size = dlg.getSize();
        dlg.setSize(0, 0);
    }

    public void unHide() {
        JDialog dlg = (JDialog) parentContainer;
        dlg.setSize(size);
        dlg.toFront();
    }

    private void setupIcons(JDialog dialog) {
        java.util.List list = new ArrayList();
        list.add(imageIcon.getImage());
        list.add(imageIconBig.getImage());
        dialog.setIconImages(list);
    }


    private static class MouseDragAdapter implements MouseListener, MouseMotionListener {

        private final Point origin = new Point();
        private JDialog dlg;

        public MouseDragAdapter(JDialog dlg) {
            this.dlg = dlg;
        }

        public void mousePressed(MouseEvent e) {
            origin.x = e.getX();
            origin.y = e.getY();
        }

        public void mouseDragged(MouseEvent e) {
            Point p = dlg.getLocationOnScreen();
            dlg.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
        }

    }


}
