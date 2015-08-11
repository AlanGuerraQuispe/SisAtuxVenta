package com.table.group;

import javax.swing.*;


public class GroupableColumnExample extends JFrame {

//    GroupableColumnExample() {
//        super("Multi-Width Header Example");
//
//        DefaultTableModel dm = new DefaultTableModel();
//        dm.setDataVector(new Object[][]{
//                {"119", "Finbar", "John", "Saunders", "ja", "ko", "zh"},
//                {"911", "Roger", "Peter", "Melly", "en", "fr", "pt"}},
//                new Object[]{"", "First", "1", "2", "Native", "2", "3"});
//
//        // Setup table
//        JTable table = new JTable( /*dm, new GroupableTableColumnModel()*/);
//        table.setColumnModel(new GroupableTableColumnModel());
//        table.setTableHeader(new GroupableTableHeader((GroupableTableColumnModel) table.getColumnModel()));
//        table.setModel(dm);
//
//        // Setup Column Groups
//        ColumnGroup g_token = new ColumnGroup(new GroupableTableCellRenderer(), "Second").addTableColumn(2,3);
//        ColumnGroup g_name = new ColumnGroup("Name").addTableColumn(1).add(g_token);
//        ColumnGroup g_other = new ColumnGroup("Others").addTableColumn(5,6);
//        ColumnGroup g_lang = new ColumnGroup("Language").addTableColumn(4).add(g_other);
//        GroupableTableColumnModel cm = ((GroupableTableColumnModel) table.getColumnModel()).addColumnGroup(g_name,g_lang);
//        // Finish off gui
//        JScrollPane scroll = new JScrollPane(table);
//        getContentPane().add(scroll);
//        setSize(800, 200);
//    }
//
//    public static void main(String[] args) {
//        GroupableColumnExample frame = new GroupableColumnExample();
//        frame.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
//        frame.setVisible(true);
//    }
//}
//
///**
// * Demo renderer just to prove they can be used.
// */
//class GroupableTableCellRenderer extends DefaultTableCellRenderer {
//    /**
//     * @param table
//     * @param value
//     * @param selected
//     * @param focused
//     * @param row
//     * @param column
//     * @return
//     */
//    public Component getTableCellRendererComponent(JTable table, Object value,
//                                                   boolean selected, boolean focused, int row, int column) {
//        JTableHeader header = table.getTableHeader();
//        if (header != null) {
//            setForeground(Color.WHITE);
//            setBackground(Color.RED);
//        }
//        setHorizontalAlignment(SwingConstants.CENTER);
//        setText(value != null ? value.toString() : " ");
//        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
//        return this;
//    }
}

