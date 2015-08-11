package com.aw.swing.mvp.ui;

import com.aw.core.domain.model.Auditable;
import com.aw.core.domain.model.AuditableImpl;
import com.aw.core.format.DateFormatter;
import com.aw.core.format.Formatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

/**
 * User: Juan Carlos Vergara
 * Date: 05/06/2009
 * <p/>
 * Shows auditable info
 */
public class AuditablePanel extends JPanel {
    protected int panelHeight = 20;

    protected Color backgroundColor = new Color(219, 229, 236);
    protected Color borderColor = new Color(196, 212, 222);

    JLabel codigoLabel;
    JLabel codigoVal;
    JLabel estadoLabel;
    JLabel estadoVal;
    JLabel fechCreaLabel;
    JLabel fechCreaVal;
    JLabel usuaCreaLabel;
    JLabel usuaCreaVal;

    JButton btnCopyCode;
    private ImageIcon image;
    private ImageIcon imageOver;

    public AuditablePanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        this.setBackground(backgroundColor);
        setDefaultSize();
        image = new ImageIcon(getClass().getResource("/images/page_copy.png"));
        imageOver = new ImageIcon(getClass().getResource("/images/page_copy_over.png"));

//        showFields(new BNMantenimientoFlt());
    }

    private void createAuditFields(AuditableImpl auditable) {
        codigoLabel = new JLabel(auditable.getCodigoLabel());
        codigoLabel.setForeground(Color.GRAY);
        estadoLabel = new JLabel("Estado: ");
        estadoLabel.setForeground(Color.GRAY);
        fechCreaLabel = new JLabel("Creado el: ");
        fechCreaLabel.setForeground(Color.GRAY);
        usuaCreaLabel = new JLabel("Creado por: ");
        usuaCreaLabel.setForeground(Color.GRAY);

        codigoVal = new JLabel("");
        estadoVal = new JLabel("");
        fechCreaVal = new JLabel("");
        usuaCreaVal = new JLabel("");

        btnCopyCode = createCopyButton();
    }

    private java.awt.event.ActionListener copyCodeAction(){
        return new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selection = codigoVal.getText();
                StringSelection data = new StringSelection(selection);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(data, data);
            }
        };
    }

    private JButton createCopyButton() {
        JButton btnCopyCode = new JButton();
        btnCopyCode.setIcon(image);
        btnCopyCode.setRolloverIcon(imageOver);
        btnCopyCode.setBorder(null);
        btnCopyCode.setBorderPainted(false);
        btnCopyCode.setContentAreaFilled(false);
        btnCopyCode.setMargin(new Insets(0, 0, 0, 0));
        btnCopyCode.setFocusable(false);
        btnCopyCode.setFocusPainted(false);
        btnCopyCode.setPreferredSize(new Dimension(image.getIconWidth(),
                                              image.getIconHeight()));
        btnCopyCode.setToolTipText("Copiar Código");
        btnCopyCode.addActionListener(copyCodeAction());


        return btnCopyCode;
    }


    private void addAuditFields() {
        this.removeAll();
        this.add(Spacer.getSpacer());
        this.add(codigoLabel);
        this.add(codigoVal);
        this.add(btnCopyCode);
        this.add(Spacer.getSpacer());
        this.add(estadoLabel);
        this.add(estadoVal);
        this.add(Spacer.getSpacer());
        this.add(fechCreaLabel);
        this.add(fechCreaVal);
        this.add(Spacer.getSpacer());
        this.add(usuaCreaLabel);
        this.add(usuaCreaVal);
    }

    private void setDefaultSize(){
        this.setBorder(new EmptyBorder(0, 0, 5, 0));
        this.setPreferredSize(new Dimension(30, 5));
        this.setMinimumSize(new Dimension(30, 5));        
    }

    public void showFields(AuditableImpl auditable) {
        this.setBorder(new LineBorder(borderColor, 1));
        
        createAuditFields(auditable);
        addAuditFields();

        assignValues(auditable);
        this.setPreferredSize(new Dimension(30, this.panelHeight));
        this.setMinimumSize(new Dimension(30, this.panelHeight));
    }

    private void assignValues(Auditable auditable) {
        codigoVal.setText(auditable.getCodigo()==null?"": String.valueOf(auditable.getCodigo())  );
        estadoVal.setText(auditable.getEstado()==null?"":auditable.getEstado());
        Formatter frm = DateFormatter.DATE_TIME_FORMATTER;
        String fechCrea = (String)frm.format(auditable.getFechCrea());
        fechCreaVal.setText(fechCrea);
        usuaCreaVal.setText(auditable.getUsuaCrea());
    }

    private static class Spacer {
        static JLabel getSpacer(){
            return new JLabel("   ");
        }
    }
}
