package com.aw.swing.mvp.ui.toolbar;

//import com.explodingpixels.macwidgets.*;
//import com.explodingpixels.widgets.WindowUtils;

/**
 * User: Juan Carlos Vergara
 * Date: 06/05/2009
 */
public class ToolbarManager {
//    UnifiedToolBar toolBar;
//
//    public void buildToolbar () {
//
//        JToggleButton leftButton = new JToggleButton(new ImageIcon(
//                DUnifiedToolbar.class.getResource("/com/explodingpixels/macwidgets/icons/sourceViewNormal.png")));
//        leftButton.setSelectedIcon(new ImageIcon(
//                DUnifiedToolbar.class.getResource("/com/explodingpixels/macwidgets/icons/sourceViewNormalSelected.png")));
//        leftButton.putClientProperty("JButton.buttonType", "segmentedTextured");
//        leftButton.putClientProperty("JButton.segmentPosition", "first");
//        leftButton.setFocusable(false);
//
//        JToggleButton rightButton = new JToggleButton(new ImageIcon(
//                DUnifiedToolbar.class.getResource("/com/explodingpixels/macwidgets/icons/ColumnViewTemplate.png")));
//        rightButton.putClientProperty("JButton.buttonType", "segmentedTextured");
//        rightButton.putClientProperty("JButton.segmentPosition", "last");
//        rightButton.setFocusable(false);
//
//        ButtonGroup group = new ButtonGroup();
//        group.add(leftButton);
//        group.add(rightButton);
//
//        LabeledComponentGroup viewButtons = new LabeledComponentGroup("View", leftButton, rightButton);
//
//        Icon blueGlobeIcon = new ImageIcon(DUnifiedToolbar.class.getResource(
//                "/com/explodingpixels/macwidgets/icons/DotMac.png"));
//        Icon greyGlobeIcon = new ImageIcon(DUnifiedToolbar.class.getResource(
//                "/com/explodingpixels/macwidgets/icons/Network.png"));
//        Icon gear = new ImageIcon(DUnifiedToolbar.class.getResource(
//                "/com/explodingpixels/macwidgets/icons/Advanced.png"));
//
//        Icon ok = new ImageIcon(DUnifiedToolbar.class.getResource(
//                "/images/aw/accepted_48.png"));
//
//        Icon cancel = new ImageIcon(DUnifiedToolbar.class.getResource(
//                "/images/aw/cancel_48.png"));
//
//        AbstractButton greyGlobeButton =
//                MacButtonFactory.makeUnifiedToolBarButton(
//                        new JButton("Agregar", ok));
//        greyGlobeButton.setEnabled(true);
////
//        toolBar = new UnifiedToolBar();
////
//        toolBar.addComponentToRight(viewButtons.getComponent());
////        toolBar.addComponentToLeft(MacButtonFactory.makeUnifiedToolBarButton(
////                new JButton("MobileMe", blueGlobeIcon)));
//        toolBar.addComponentToLeft(greyGlobeButton);
//        toolBar.addComponentToLeft(MacButtonFactory.makeUnifiedToolBarButton(
//                new JButton("Cancelar", cancel)));
////        toolBar.addComponentToLeft(MacButtonFactory.makeUnifiedToolBarButton(
////                new JButton("Advanced", gear)));
//
//        toolBar.addComponentToLeft(new JButton("Este es un botoncito"));
//
////        JTextField textField = new JTextField(10);
////        textField.putClientProperty("JTextField.variant", "search");
////        toolBar.addComponentToRight(new LabeledComponentGroup("Search", textField).getComponent());
//
////        JTextArea textArea = new JTextArea();
//    }
//
//    public JComponent getComponent(){
//        return toolBar.getComponent();
//    }
//
//    public static void main(String[] args) {
//        ToolbarManager tb = new ToolbarManager();
//        tb.buildToolbar();
//
//        JFrame frame = new JFrame();
//        //tb.installWindowDraggerOnWindow(frame);
//
//        //MacUtils.makeWindowLeopardStyle(frame.getRootPane());
//        WindowUtils.createAndInstallRepaintWindowFocusListener(frame);
//        frame.add(tb.getComponent(), BorderLayout.NORTH);
////        frame.add(textArea, BorderLayout.CENTER);
//        frame.setSize(500, 350);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setVisible(true);
//
//
//    }
}
