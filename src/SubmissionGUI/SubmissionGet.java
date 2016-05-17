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
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Hasini
 */
public class SubmissionGet extends javax.swing.JInternalFrame {

    private String academicYear;
    private int batchNo;
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
    
    public SubmissionGet() {
        initComponents();
         ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        //((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        //this.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 1, true));
         this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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

        academicYear = String.valueOf(jYear.getYear());
        field = jField.getSelectedItem().toString();
        batchNo=Integer.parseInt(jBatch.getSelectedItem().toString());
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
        }else if(batchID==null){
            JOptionPane.showMessageDialog(rootPane, "There is no data related to the selected batch", "Error", 0);
            jYear.requestFocus();
        } else {
            String[] months = {"Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
            ArrayList<Student> students = new ArrayList<Student>();
            //System.out.println("Student count--" + students.size());
            students = StudentController.getIndexNo(fieldID, batchID);
            System.out.println(students.size());
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

          
//            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelGetCounts = new javax.swing.JPanel();
        jField = new javax.swing.JComboBox();
        jLabelField = new javax.swing.JLabel();
        jLabelYear = new javax.swing.JLabel();
        jSearch = new javax.swing.JButton();
        jLabelBatch = new javax.swing.JLabel();
        jYear = new com.toedter.calendar.JYearChooser();
        jBatch = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(892, 572));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("View Submission Dates");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelGetCounts.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabelField.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabelField.setText("Field :");

        jLabelYear.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabelYear.setText("Academic Year :");

        jSearch.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabelBatch.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabelBatch.setText("Batch :");

        jYear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jBatch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));
        jBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBatchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGetCountsLayout = new javax.swing.GroupLayout(jPanelGetCounts);
        jPanelGetCounts.setLayout(jPanelGetCountsLayout);
        jPanelGetCountsLayout.setHorizontalGroup(
            jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGetCountsLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelYear)
                    .addComponent(jLabelField))
                .addGap(47, 47, 47)
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jYear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGetCountsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))
                    .addGroup(jPanelGetCountsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBatch)
                        .addGap(18, 18, 18)
                        .addComponent(jBatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelGetCountsLayout.setVerticalGroup(
            jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGetCountsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelField)
                    .addComponent(jField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelYear)
                    .addComponent(jYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelBatch)))
                .addGap(21, 21, 21)
                .addComponent(jSearch)
                .addGap(267, 267, 267))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

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
        JComponent[] j = {jBatch};
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

    private void jBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBatchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBatchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jBatch;
    private javax.swing.JComboBox jField;
    private javax.swing.JLabel jLabel1;
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
    private com.toedter.calendar.JYearChooser jYear;
    // End of variables declaration//GEN-END:variables
}
