/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubmissionGUI;

/**
 *
 * @author Rangika
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.regex.Pattern;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class Ptf_Viewer {

    private JFrame frame = new JFrame("Portable test file viewing");
    private JTable table;
    private final String[] columnNames = {"Test 1", "Test 2", "Test 3", "Test 4",
        "Test 5", "Test 6", "Test 7", "Test 8", "Test 9", "Test 10"};
    private final Object[][] data = {
        {"+", "+", "", "", "", "~", "", "", "", ""},
        {"-", "+", "", "", "", "", "", "", "", ""},
        {"+", "+", "", "", "~", "", "", "", "", ""},
        {"+", "+", "", "", "", "", "~", "", "", ""},
        {"+", "-", "", "~", "", "", "", "", "", ""},
        {"+", "-", "", "", "", "", "", "", "", ""},
        {"+", "-", "", "", "", "", "", "", "", ""},
        {"-", "+", "~", "", "", "", "", "", "", "+"}
    };
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);

    public Ptf_Viewer() {
        table = new JTable(data, columnNames) {
            //logics place for prepareRenderer
        };
        table.setModel(model);
        table.setPreferredScrollableViewportSize(
                new Dimension(800, table.getPreferredSize().height));
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 13));
        header.setBackground(Color.black);
        header.setForeground(Color.white);
        for (int i = 0; i < table.getColumnCount(); i++) {
            RowColorRenderer rowRenderer = new RowColorRenderer(i);
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(rowRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setFont(new Font("Times New Roman", Font.BOLD, 13));
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(50, 50);
        frame.pack();
        frame.setVisible(true);
    }

    private class RowColorRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;
        private int colNo = 0;

        RowColorRenderer(int col) {
            colNo = col;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);
            JComponent jc = (JComponent) comp;
            if (!isSelected) {
                if (table.getValueAt(row, colNo) != null) {
                    String str = table.getValueAt(row, colNo).toString();
                    if (!str.isEmpty()) {
                        if ((Pattern.compile("[+]").matcher(str).find())) {
                            setForeground(Color.magenta);
                            setBackground(Color.GREEN);
                            setFont(new Font("Serif", Font.BOLD, 16));
                            setHorizontalAlignment(CENTER);
                        } else if ((Pattern.compile("[-]").matcher(str).find())) {
                            setForeground(Color.blue);
                            setBackground(Color.RED);
                            setFont(new Font("Serif", Font.BOLD, 16));
                            setHorizontalAlignment(CENTER);
                        } else if ((Pattern.compile("[~]").matcher(str).find())) {
                            setForeground(Color.red);
                            setBackground(Color.YELLOW);
                            setFont(new Font("Serif", Font.BOLD, 16));
                            setHorizontalAlignment(CENTER);
                        } else {
                            setBackground(Color.WHITE);
                            setForeground(table.getForeground());
                            setFont(new Font("Serif", Font.PLAIN, 12));
                            setHorizontalAlignment(CENTER);
                        }
                    } else {
                        setBackground(Color.WHITE);
                        setForeground(table.getForeground());
                    }
                }
            }
            return this;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Ptf_Viewer frame = new Ptf_Viewer();
            }
        });
    }
}
