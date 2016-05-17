/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubmissionGUI;

import Controller.BatchController;
import Controller.DurationController;
import Controller.FieldController;
import Controller.StudentController;
import Controller.SubmissionDateController;
import Model.Student;
import Model.SubmissionDate;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Rangika
 */
public class GetSubmission extends javax.swing.JFrame {

    private String academicYear;
    private int batchNo = 0;
    private String field;
    private String batchID;
    private String fieldID;
    private int trainingPeriod;
    private int startMonth;
    private int startYear;
    private DefaultTableModel dtm;
    TableColumn column;
    CellRenderer cell;
    ArrayList<Date> dDate;

    public GetSubmission() {
        initComponents();
       
        dtm = (DefaultTableModel) jTable.getModel();
       
    }

    public void setCursor(int[] i, JComponent[] j, KeyEvent e) {                   // Set the cursour when neccessary keys are pressed
        for (int x = 0; x < i.length; x++) {
            if (e.getKeyCode() == i[x]) {
                j[x].requestFocus();
            }
        }

    }

    private boolean isNumber(String number) {

        if (!number.matches("\\d+")) {
            return true;
        }
        return false;
    }

    private void setData() {

    }

    private void removeColumn(int index, JTable jTable) {
        if (dtm.getColumnCount() != 2) {
            int nRow = jTable.getRowCount();
            int nCol = jTable.getColumnCount() - 1;
            Object[][] cells = new Object[nRow][nCol];
            String[] names = new String[nCol];

            for (int j = 0; j < nCol; j++) {
                if (j < index) {
                    names[j] = jTable.getColumnName(j);
                    for (int i = 0; i < nRow; i++) {
                        cells[i][j] = jTable.getValueAt(i, j);
                    }
                } else {
                    names[j] = jTable.getColumnName(j + 1);
                    for (int i = 0; i < nRow; i++) {
                        cells[i][j] = jTable.getValueAt(i, j + 1);
                    }
                }
            }

            dtm = new DefaultTableModel(cells, names);
            jTable.setModel(dtm);
        }
    }

    private void clearColumns(int trainingPeriod) {
        for (int i = trainingPeriod + 1; i > 1; i--) {
            removeColumn(i, jTable);
        }
        dtm.getDataVector().clear();

    }

    private void getData() throws ClassNotFoundException, SQLException {

        academicYear = jYear.getText();
        field = jField.getSelectedItem().toString();
        if (field == "Computer Graphic Designing") {
            field = "CGD";
        }
        dDate = new ArrayList<Date>();
        fieldID = FieldController.getFieldID(field);
        batchID = BatchController.getBatchID(academicYear, batchNo);
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        //System.out.println("fieldID-"+fieldID+"  batchID-"+batchID);
        if (academicYear.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter a year", "Error", 0);
            jYear.requestFocus();

        } else if (academicYear.length() != 4 | isNumber(academicYear)) {
            JOptionPane.showMessageDialog(rootPane, "The year you eneterd is incorrect", "Error", 0);
            jYear.requestFocus();
        } else if (Integer.parseInt(academicYear) > Integer.parseInt(year) + 2) {
            JOptionPane.showMessageDialog(rootPane, "The year you eneterd is far future", "Error", 0);
            jYear.requestFocus();
        } else if (batchNo == 0) {
            JOptionPane.showMessageDialog(rootPane, "Please select a batch number", "Error", 0);
            jBatch1.requestFocus();
            jBatch1.setSelected(true);
            batchNo=1;
        }else if(batchID==null){
            JOptionPane.showMessageDialog(rootPane, "There is no data related to the selected batch", "Error", 0);
            jYear.requestFocus();
        } else {
            String[] months = {"Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
            ArrayList<Student> students = new ArrayList<Student>();

            //System.out.println("Student count--" + students.size());
            students = StudentController.getIndexNo(fieldID, batchID);
            String indexNo;
            String name;
            clearColumns(trainingPeriod);
            trainingPeriod = FieldController.getTrainingPeriod(fieldID);
            startMonth = DurationController.getStartMonth(fieldID, batchID);
            startYear = DurationController.getStartYear(fieldID, batchID);
            int height = students.size();
            ArrayList<Integer> rows = new ArrayList<Integer>();
            ArrayList<Integer> columns = new ArrayList<Integer>();
            ArrayList<Boolean> delays = new ArrayList<Boolean>();
            ArrayList<Date> dates = new ArrayList<Date>();
            //System.out.println("Student count--" + students.size());
            for (int k = 0; k < students.size(); k++) {

                indexNo = students.get(k).getIndexNumber();
                name = students.get(k).getName();
                ArrayList<SubmissionDate> subDates = new ArrayList<SubmissionDate>();
                //clearColumns(startMonth,trainingPeriod);
                subDates = SubmissionDateController.getSubDate(indexNo);

//                trainingPeriod = FieldController.getTrainingPeriod(fieldID);
//                startMonth = DurationController.getStartMonth("f00CGD", "B0002");
//                startYear = DurationController.getStartYear("f00CGD", "B0002");
                int count = startMonth;
                int tyear = startYear;
                Object[] row = {};
               
                dtm.addRow(row);
                dtm.setValueAt(indexNo, k, 0);
                dtm.setValueAt(StudentController.searchStudent(indexNo), k, 1);
                for (int i = 0; i < trainingPeriod; i++) {
                    //TableColumn col =new TableColumn();
                    //jTable.addColumn();

                    if (k == 0) {
                        dtm.addColumn(months[count]);

                    }
                    for (int j = 0; j < subDates.size(); j++) {
                        //System.out.println("size---" + subDates.size());
                        boolean isDelay = false;
                        Date d1 = subDates.get(j).getSubmissionDate();
                        Date d2 = subDates.get(j).getDueDate();
                        if (d1.compareTo(d2) > 0) {
                            isDelay = true;
                        }
                        if (subDates.get(j).getNumber() == i + 1) {

                            //System.out.println("ssss");
                            //System.out.println(subDates.get(j).getSubmissionDate());
                            dtm.setValueAt(subDates.get(j).getSubmissionDate(), k, i + 2);
                            ///////
                            rows.add(k);
                            columns.add(i + 2);
                            delays.add(isDelay);
                            if (isDelay) {
                                //System.out.println("delay--"+(i));

//                                column = jTable.getColumnModel().getColumn(i + 2);
//                                cell = new CellRenderer(k,i+2);
//                                column.setCellRenderer(cell);
                                //repaint();
                            }
                        }
                    }
                    count++;
                    if (count == 12) {
                        count = 0;
                        tyear++;
                    }
                    dDate.add(new GregorianCalendar(tyear, i, 10).getTime());
                }
            }
            jTable.getTableHeader().setReorderingAllowed(false);
            jTable.getTableHeader().setResizingAllowed(false);
            if (trainingPeriod == 18) {
                Dimension s = new Dimension();
                s.setSize(3500, height * 20 + 20);
                jScrollPane2.setPreferredSize(s);

//                s.setSize(3502, 400);
//                jScrollPane1.setPreferredSize(s);
//                jScrollPane2.setRequestFocusEnabled(true);
            } else if (trainingPeriod == 12) {
                Dimension s = new Dimension();
                s.setSize(2500, height * 20 + 20);
                jScrollPane2.setPreferredSize(s);
            } else if (trainingPeriod == 6) {
                Dimension s = new Dimension();
                s.setSize(1500, height * 20 + 20);
                jScrollPane2.setPreferredSize(s);
            }

            //cell = new CellRenderer(0,0);
            // column = jTable.getColumnModel().getColumn(columns.get(0));
            ///////////////////////////////////////////
//            for (int a = 0; a < rows.size(); a++) {
//                if (delays.get(a) == true) {
//                    System.out.println("delayList--" + a);
//                    column = jTable.getColumnModel().getColumn(columns.get(a));
//
//                    // cell.setNewValues(rows.get(a),columns.get(a));
//                    cell = new CellRenderer(rows.get(a), columns.get(a));
//
//                    column.setCellRenderer(cell);
//
//                }
//            }
            
            ///////////////////////////////////////
//            for (int i = 2; i < jTable.getColumnCount(); i++) {
//                RowColorRenderer rowRenderer = new RowColorRenderer(i, dDate.get(i - 2));
//                TableColumn column = jTable.getColumnModel().getColumn(i);
//                column.setCellRenderer(rowRenderer);
//                
//                ///////////////////////////////////
//            }
        }
    }

//    private class RowColorRenderer extends DefaultTableCellRenderer {
//
//        private static final long serialVersionUID = 1L;
//        private int colNo = 0;
//        private Date dueDate;
//
//        RowColorRenderer(int col, Date date) {
//            colNo = col;
//            dueDate = date;
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value,
//                boolean isSelected, boolean hasFocus, int row, int column) {
//            Component comp = super.getTableCellRendererComponent(table, value,
//                    isSelected, hasFocus, row, column);
//            JComponent jc = (JComponent) comp;
//            try {
//                if (!isSelected) {
//                    if (table.getValueAt(row, colNo) != null) {
//                        //DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                        String str = jTable.getValueAt(row, colNo).toString();
//                        System.out.println(str);
//
//                        Date subDate = (Date) dateFormat.parse(str);
//
//                        if (subDate != null) {
//                            if (Pattern.compile("[06]").matcher(str).find()) {
//                                setForeground(Color.magenta);
//                                setBackground(Color.GREEN);
//
//                                setFont(new Font("Serif", Font.BOLD, 16));
//                            } else if ((Pattern.compile("[2016-07-20]").matcher(str).find())) {
//                                setForeground(Color.blue);
//                                setBackground(Color.RED);
//                                setFont(new Font("Serif", Font.BOLD, 16));
//                                setHorizontalAlignment(CENTER);
//                            } else {
//                                setBackground(Color.WHITE);
//                                setForeground(table.getForeground());
//                                setFont(new Font("Serif", Font.PLAIN, 12));
//                                setHorizontalAlignment(CENTER);
//                            }
////                            if (subDate.compareTo(dueDate) > 0) {
////                                //setForeground(Color.magenta);
////                                setBackground(Color.GREEN);
////                                //setFont(new Font("Serif", Font.BOLD, 16));
////                                //setHorizontalAlignment(CENTER);
////
////                            } else {
////                                setBackground(null);
////                                //setForeground(table.getForeground());
////                                //setFont(new Font("Serif", Font.PLAIN, 12));
////                                //setHorizontalAlignment(CENTER);
////                            }
//                        } else {
//                            setBackground(null);
//                            //setForeground(table.getForeground());
//                        }
//                    }
//                }
//            } catch (ParseException ex) {
//                Logger.getLogger(GetSubmission.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return this;
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        batch = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanelGetCounts = new javax.swing.JPanel();
        jField = new javax.swing.JComboBox();
        jLabelField = new javax.swing.JLabel();
        jLabelYear = new javax.swing.JLabel();
        jSearch = new javax.swing.JButton();
        jYear = new javax.swing.JTextField();
        jLabelBatch = new javax.swing.JLabel();
        jBatch1 = new javax.swing.JRadioButton();
        jBatch2 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelGetCounts.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Electrical", "Ref & Air", "Electronic", "Machanist", "Welder", "Wood Work", "Plumbing", "Construction", "ICT", "Computer Graphic Designing" }));
        jField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldActionPerformed(evt);
            }
        });
        jField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFieldKeyPressed(evt);
            }
        });

        jLabelField.setText("Field :");

        jLabelYear.setText("Academic Year :");

        jSearch.setText("Search");
        jSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchActionPerformed(evt);
            }
        });
        jSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSearchKeyPressed(evt);
            }
        });

        jYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jYearKeyPressed(evt);
            }
        });

        jLabelBatch.setText("Batch :");

        batch.add(jBatch1);
        jBatch1.setText("1");
        jBatch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBatch1MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBatch1MouseClicked(evt);
            }
        });
        jBatch1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jBatch1MouseDragged(evt);
            }
        });
        jBatch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBatch1KeyPressed(evt);
            }
        });

        batch.add(jBatch2);
        jBatch2.setText("2");
        jBatch2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBatch2MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBatch2MouseClicked(evt);
            }
        });
        jBatch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBatch2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGetCountsLayout = new javax.swing.GroupLayout(jPanelGetCounts);
        jPanelGetCounts.setLayout(jPanelGetCountsLayout);
        jPanelGetCountsLayout.setHorizontalGroup(
            jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGetCountsLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelBatch)
                    .addComponent(jLabelYear)
                    .addComponent(jLabelField))
                .addGap(47, 47, 47)
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGetCountsLayout.createSequentialGroup()
                        .addComponent(jYear, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelGetCountsLayout.createSequentialGroup()
                        .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelGetCountsLayout.createSequentialGroup()
                                .addComponent(jBatch1)
                                .addGap(72, 72, 72)
                                .addComponent(jBatch2))
                            .addComponent(jField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
        );
        jPanelGetCountsLayout.setVerticalGroup(
            jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGetCountsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelField)
                    .addComponent(jField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelYear)
                    .addComponent(jYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBatch)
                    .addComponent(jBatch1)
                    .addComponent(jBatch2)
                    .addComponent(jSearch))
                .addGap(267, 267, 267))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(3500, 372));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable);

        jScrollPane1.setViewportView(jScrollPane2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelGetCounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelGetCounts, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFieldActionPerformed

    private void jFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFieldKeyPressed
        int[] i = {10};
        JComponent[] j = {jYear};
        setCursor(i, j, evt);
    }//GEN-LAST:event_jFieldKeyPressed

    private void jSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchActionPerformed
        try {
            getData();
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetSubmission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetSubmission.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jSearchActionPerformed

    private void jSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSearchKeyPressed
        int[] i = {38};
        JComponent[] j = {jBatch1};
        setCursor(i, j, evt);
        if (evt.getKeyCode() == 10) {
            try {
                getData();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetSubmission.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GetSubmission.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jSearchKeyPressed

    private void jBatch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBatch1MouseClicked
        jBatch1.setSelected(true);
        batchNo = 1;
    }//GEN-LAST:event_jBatch1MouseClicked

    private void jBatch1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBatch1KeyPressed
        int[] i = {38, 39, 40};
        JComponent[] j = {jYear, jBatch2, jSearch};
        setCursor(i, j, evt);
        if (evt.getKeyCode() == 39) {
            jBatch2.setSelected(true);
            batchNo = 2;
        }
        if (evt.getKeyCode() == 10) {
            jBatch1.setSelected(true);
            batchNo = 1;
        }
    }//GEN-LAST:event_jBatch1KeyPressed

    private void jBatch2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBatch2MouseClicked
        jBatch2.setSelected(true);
        batchNo = 2;
    }//GEN-LAST:event_jBatch2MouseClicked

    private void jBatch2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBatch2KeyPressed
        int[] i = {38, 37, 40};
        JComponent[] j = {jYear, jBatch1, jSearch};
        setCursor(i, j, evt);
        if (evt.getKeyCode() == 37) {
            jBatch1.setSelected(true);
            batchNo = 1;
        }
        if (evt.getKeyCode() == 10) {
            jBatch2.setSelected(true);
            batchNo = 2;
        }
    }//GEN-LAST:event_jBatch2KeyPressed

    private void jYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jYearKeyPressed
        int[] i = {38, 40};
        JComponent[] j = {jField, jBatch1};
        setCursor(i, j, evt);
    }//GEN-LAST:event_jYearKeyPressed

    private void jBatch1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBatch1MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jBatch1MouseDragged

    private void jBatch1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBatch1MousePressed
        jBatch1.setSelected(true);
        batchNo = 1;
    }//GEN-LAST:event_jBatch1MousePressed

    private void jBatch2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBatch2MousePressed
        jBatch2.setSelected(true);
        batchNo = 2;
    }//GEN-LAST:event_jBatch2MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GetSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GetSubmission().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup batch;
    private javax.swing.JRadioButton jBatch1;
    private javax.swing.JRadioButton jBatch2;
    private javax.swing.JComboBox jField;
    private javax.swing.JLabel jLabelBatch;
    private javax.swing.JLabel jLabelField;
    private javax.swing.JLabel jLabelYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelGetCounts;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSearch;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jYear;
    // End of variables declaration//GEN-END:variables
}
