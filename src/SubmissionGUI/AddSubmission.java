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
import Model.SubmissionDate;
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
 * @author Rangika
 */
public class AddSubmission extends javax.swing.JFrame {

    /**
     * Creates new form AddSubmission
     */
    private String field;
    private String year;
    private int batchNo;
    private String number;
    private String indexNo;
    private String fieldID;
    private String batchID;
    private DefaultTableModel dtm;
    private int trainingPeriod;
    private int startMonth;
    private int startYear;
    private Date dueDate;
    private Date sDate;
    private int num;
    int count;
    TableColumn column;
    CellRenderer cell;
    ArrayList<Date> dDate;

    public AddSubmission() {
        initComponents();
        dtm = (DefaultTableModel) jTable.getModel();
        jScrollPane2.setVisible(false);
        jScrollPane1.setVisible(false);
        jTable.setVisible(false);
        jSave.setVisible(false);
        jLMonth.setVisible(false);
        jLYear.setVisible(false);
        jComboMonth.setVisible(false);
        jComboYear.setVisible(false);
        jLabel5.setVisible(false);
        jSubmissionDate.setVisible(false);
        Date s = new Date();
        System.out.println(s);

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

    private void clearColumns(int trainingPeriod) {
        for (int i = trainingPeriod + 1; i > 1; i--) {
            removeColumn(i, jTable);
        }
        dtm.getDataVector().clear();
        jLMonth.setVisible(false);
        jLYear.setVisible(false);
        jComboMonth.setVisible(false);
        jComboYear.setVisible(false);
        jLabel5.setVisible(false);
        jSubmissionDate.setVisible(false);

    }

    private void removeColumn(int index, JTable jTable) {
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

    private void getDetails() throws SQLException, ClassNotFoundException {
        int month = jComboMonth.getSelectedIndex();
        int year = Integer.parseInt(jComboYear.getSelectedItem().toString());
        dueDate = SubmissionDateController.getDueDate(year, month);
        sDate = jSubmissionDate.getDate();
        if (year == startYear) {
            if (month < startMonth) {
                JOptionPane.showMessageDialog(rootPane, "The selected submission month doesnot exist", "Error", 0);
                jComboMonth.requestFocus();
            }
        }
        if (month > count) {
            JOptionPane.showMessageDialog(rootPane, "The selected submission month doesn't exist", "Error", 0);
            jComboMonth.requestFocus();
        } else if (sDate.compareTo(new Date()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Submission date should be current day or before", "Error", 0);

        } else if (sDate.compareTo(new GregorianCalendar(year, month, 20).getTime()) < 0) {
            JOptionPane.showMessageDialog(rootPane, "The submission should be after 20 th of every month", "Error", 0);
        } else {

            int i = JOptionPane.showConfirmDialog(rootPane, "Are you sure index Number " + indexNo + " is submited report for " + jComboMonth.getSelectedItem().toString() + " in " + jComboYear.getSelectedItem().toString(), "Confirm", 0, 1);
            switch (i) {
                case 0:
                    Date d2 = new GregorianCalendar(startYear, startMonth, 1).getTime();
                    //System.out.println("d1--"+d1);
                    //System.out.println("d2--"+d2);
                    Calendar startCalendar = new GregorianCalendar();
                    startCalendar.setTime(d2);
                    Calendar endCalendar = new GregorianCalendar();
                    endCalendar.setTime(dueDate);

                    int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
                    int num = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                    //System.out.println(num);
                    if (dtm.getValueAt(0, num + 1).toString() != null | dtm.getValueAt(0, num + 1).toString() != "") {
                        int j = JOptionPane.showConfirmDialog(rootPane, "Do you want to edit the submitted the date " + dtm.getValueAt(0, num + 1).toString(), "Confirm", 0, 1);

                        switch (j) {

                            case 0:
                                SubmissionDateController.addDate(indexNo, sDate, dueDate, num);

                                dtm.setValueAt(SubmissionDateController.getDate(indexNo, num), 0, num + 1);
                                JOptionPane.showMessageDialog(rootPane, "Successfully added the submission date", "Confirm", 1);
                                break;

                            case 1:
                                jSave.requestFocus();
                                break;
                            default:
                                break;

                        }
                    } else {

                        SubmissionDateController.addDate(indexNo, sDate, dueDate, num);

                        dtm.setValueAt(SubmissionDateController.getDate(indexNo, num), 0, num + 1);
                        JOptionPane.showMessageDialog(rootPane, "Successfully added the submission date", "Confirm", 1);

                        break;
                    }
                case 1:
                    jSubmissionDate.requestFocus();
                    break;
                default:
                    break;
            }

//setDate(month, year);
//        dueDate = SubmissionDateController.getDueDate(year, month);
//        sDate = jSubmissionDate.getDate();
            //Calendar now = Calendar.getInstance();
            //now.setTime(sDate);
            //System.out.println("bbbbbbbbbbbb");
            //System.out.println(sDate);
            //Date d1 = new GregorianCalendar(year, month, 1).getTime();
//            Date d2 = new GregorianCalendar(startYear, startMonth, 1).getTime();
//            //System.out.println("d1--"+d1);
//            //System.out.println("d2--"+d2);
//            Calendar startCalendar = new GregorianCalendar();
//            startCalendar.setTime(d2);
//            Calendar endCalendar = new GregorianCalendar();
//            endCalendar.setTime(dueDate);
//
//            int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
//            int num = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
//            //System.out.println(num);
//            SubmissionDateController.addDate(indexNo, sDate, dueDate, num);
//
//            dtm.setValueAt(SubmissionDateController.getDate(indexNo, num), 0, num + 1);
        }
    }

//    private void setDate(int month, int year) {
//        dueDate = SubmissionDateController.getDueDate(year, month);
//
//    }
    private void getData() throws SQLException, ClassNotFoundException {
        clearColumns(trainingPeriod);
        String bNo;
        String name;
        String yearToday = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        field = jComboField.getSelectedItem().toString();
        fieldID = "f00" + field;
        bNo = jComboBatchNo.getSelectedItem().toString();
        batchNo = Integer.parseInt(bNo);
        year = jTextYear.getText();
        String batchYear = "20" + year;
        number = jTextNumber.getText();
        indexNo = field + "/" + year + "/" + bNo + "/" + number;
//        String name = StudentController.searchStudent(indexNo);
//        batchID = BatchController.getBatchID(batchYear, batchNo);
        String[] months = {"Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
        if (year.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter a year with last two digits", "Error", 0);
            jTextYear.requestFocus();

        } else if (year.length() != 2 | isNumber(year)) {
            JOptionPane.showMessageDialog(rootPane, "The year you eneterd is incorrect", "Error", 0);
            jTextYear.requestFocus();
        } else if (Integer.parseInt(20 + year) > Integer.parseInt(yearToday) + 2) {
            JOptionPane.showMessageDialog(rootPane, "The year you eneterd is far future", "Error", 0);
            jTextYear.requestFocus();
        } else if (number.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter a number correctly ", "Error", 0);
            jTextNumber.requestFocus();

        } else if (isNumber(number)) {
            JOptionPane.showMessageDialog(rootPane, "The last number you eneterd is incorrect", "Error", 0);
            jTextNumber.requestFocus();
        } else if (StudentController.isExist(indexNo) == false) {
            JOptionPane.showMessageDialog(rootPane, "The index number " + indexNo + " does not exist.", "Error", 0);
            jTextNumber.requestFocus();
        } else {
            name = StudentController.searchStudent(indexNo);
            batchID = BatchController.getBatchID(batchYear, batchNo);

            if (name == null | name == "") {
                JOptionPane.showMessageDialog(rootPane, "Selected student is a dropout", "Error", 0);
                jTextNumber.requestFocus();
            } else {
                ArrayList<SubmissionDate> subDates = new ArrayList<SubmissionDate>();
                //clearColumns(startMonth,trainingPeriod);
                subDates = SubmissionDateController.getSubDate(indexNo);
                trainingPeriod = FieldController.getTrainingPeriod(fieldID);
                startMonth = DurationController.getStartMonth(fieldID, batchID);
                startYear = DurationController.getStartYear(fieldID, batchID);
                jComboYear.addItem(startYear);
                count = startMonth;
                int year = startYear;
                Object[] row = {};
                dtm.addRow(row);
                Date submissionDate;
                jSubmissionDate.setDate(new Date());
                dtm.setValueAt(indexNo, 0, 0);
                dtm.setValueAt(StudentController.searchStudent(indexNo), 0, 1);
                for (int i = 0; i < trainingPeriod; i++) {
                    //TableColumn col =new TableColumn();
                    //jTable.addColumn();
                    dtm.addColumn(months[count]);
                    if (subDates.size() != 0) {
                        for (int j = 0; j < subDates.size(); j++) {
                            //System.out.println("size---"+subDates.size());

                            boolean isDelay = false;
                            Date d1 = subDates.get(j).getSubmissionDate();
                            Date d2 = subDates.get(j).getDueDate();
                            if (d1.compareTo(d2) > 0) {
                                isDelay = true;
                            }
                            if (subDates.get(j).getNumber() == i + 1) {
                            //System.out.println("ssss");
                                //System.out.println(subDates.get(j).getSubmissionDate());
                                dtm.setValueAt(subDates.get(j).getSubmissionDate(), 0, i + 2);

                                if (isDelay) {

                                    column = jTable.getColumnModel().getColumn(i + 2);
                                    cell = new CellRenderer(0, i + 2);
                                    column.setCellRenderer(cell);
                                    repaint();
                                }
//                        CellRenderer ex=new CellRenderer();
//                        ex.getTableCellRendererComponent(jTable,"22",true,true,0,(i+2));
//                        jTable.repaint();
                                ///////
                            }
                        }
                    }
                    count++;
                    if (count == 12) {
                        count = 0;
                        year++;
                        jComboYear.addItem(year);
                    }

                }
                jTable.getTableHeader().setReorderingAllowed(false);
                jTable.getTableHeader().setResizingAllowed(false);
                //TableColumn col = jTable.getColumnModel().getColumn(0);
                if (trainingPeriod == 18) {
                    Dimension s = new Dimension();
                    s.setSize(3500, 402);
                    jScrollPane1.setPreferredSize(s);
                } else if (trainingPeriod == 12) {
                    Dimension s = new Dimension();
                    s.setSize(2500, 402);
                    jScrollPane1.setPreferredSize(s);
                } else if (trainingPeriod == 6) {
                    Dimension s = new Dimension();
                    s.setSize(1500, 402);
                    jScrollPane1.setPreferredSize(s);
                }

                jScrollPane1.setVisible(true);
                jScrollPane2.setVisible(true);
                jTable.setVisible(true);
                jSave.setVisible(true);
                jLMonth.setVisible(true);
                jLYear.setVisible(true);
                jComboMonth.setVisible(true);
                jComboYear.setVisible(true);
                jLabel5.setVisible(true);
                jSubmissionDate.setVisible(true);
            }
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboField = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextYear = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBatchNo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jTextNumber = new javax.swing.JTextField();
        jBtnAddSubmission = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jSave = new javax.swing.JButton();
        jLMonth = new javax.swing.JLabel();
        jComboMonth = new javax.swing.JComboBox();
        jLYear = new javax.swing.JLabel();
        jComboYear = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jSubmissionDate = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Index no :");

        jComboField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CGD", "CS", "EL", "EN", "ICT", "MM", "MW", "PL", "RA", "WW" }));
        jComboField.setPreferredSize(new java.awt.Dimension(46, 25));
        jComboField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboFieldKeyPressed(evt);
            }
        });

        jLabel2.setText("/");

        jTextYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextYearKeyPressed(evt);
            }
        });

        jLabel3.setText("/");

        jComboBatchNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02" }));
        jComboBatchNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBatchNoKeyPressed(evt);
            }
        });

        jLabel4.setText("/");

        jTextNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextNumberKeyPressed(evt);
            }
        });

        jBtnAddSubmission.setText("Add Submission Date");
        jBtnAddSubmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddSubmissionActionPerformed(evt);
            }
        });
        jBtnAddSubmission.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnAddSubmissionKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnAddSubmission)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jTextYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jTextNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jBtnAddSubmission)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jSave.setText("Save");
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });

        jLMonth.setText("Month :");

        jComboMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        jComboMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboMonthActionPerformed(evt);
            }
        });

        jLYear.setText("Year :");

        jLabel5.setText("Submission Date :");

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

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
        jScrollPane1.setViewportView(jTable);

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLMonth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLYear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSubmissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLMonth)
                        .addComponent(jComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLYear)
                        .addComponent(jComboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jSubmissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(jSave)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboFieldKeyPressed
        int[] i = {39};
        JComponent[] j = {jTextYear};
        setCursor(i, j, evt);
    }//GEN-LAST:event_jComboFieldKeyPressed

    private void jTextYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextYearKeyPressed
        int[] i = {37, 39};
        JComponent[] j = {jComboField, jComboBatchNo};
        setCursor(i, j, evt);
    }//GEN-LAST:event_jTextYearKeyPressed

    private void jComboBatchNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBatchNoKeyPressed
        int[] i = {37, 39};
        JComponent[] j = {jTextYear, jTextNumber};
        setCursor(i, j, evt);
    }//GEN-LAST:event_jComboBatchNoKeyPressed

    private void jTextNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNumberKeyPressed
        int[] i = {37, 39, 10};
        JComponent[] j = {jComboBatchNo, jBtnAddSubmission, jBtnAddSubmission};
        setCursor(i, j, evt);
    }//GEN-LAST:event_jTextNumberKeyPressed

    private void jBtnAddSubmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddSubmissionActionPerformed

        try {
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(AddSubmission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddSubmission.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jBtnAddSubmissionActionPerformed

    private void jBtnAddSubmissionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnAddSubmissionKeyPressed
        try {
            int[] i = {37, 38};
            JComponent[] j = {jTextNumber, jTextNumber};
            setCursor(i, j, evt);
            if (evt.getKeyCode() == 10) {

                getData();

            }
        } catch (SQLException ex) {
            Logger.getLogger(AddSubmission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddSubmission.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jBtnAddSubmissionKeyPressed

    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
        try {
            getDetails();
        } catch (SQLException ex) {
            Logger.getLogger(AddSubmission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddSubmission.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jSaveActionPerformed

    private void jComboMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboMonthActionPerformed

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
            java.util.logging.Logger.getLogger(AddSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddSubmission().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAddSubmission;
    private javax.swing.JComboBox jComboBatchNo;
    private javax.swing.JComboBox jComboField;
    private javax.swing.JComboBox jComboMonth;
    private javax.swing.JComboBox jComboYear;
    private javax.swing.JLabel jLMonth;
    private javax.swing.JLabel jLYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jSubmissionDate;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextNumber;
    private javax.swing.JTextField jTextYear;
    // End of variables declaration//GEN-END:variables
}
