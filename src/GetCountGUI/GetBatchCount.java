/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GetCountGUI;

import Controller.BatchController;
import Controller.FieldController;
import Controller.StudentController;
import Model.Field;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rangika
 */
public class GetBatchCount extends javax.swing.JFrame {

    private String academicYear;
    private int batchNo=0;
    private String field;
    private String batchID;
    private String fieldID;
    private final DefaultTableModel dtm;
    private final DefaultTableModel dtm1;
    private final DefaultTableModel dtm2;
    public GetBatchCount() {
        initComponents();
        //jField.requestFocus();
        dtm = (DefaultTableModel) jTable.getModel();
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);
        dtm1= (DefaultTableModel) jTableCount.getModel();
        dtm2= (DefaultTableModel) jTableField.getModel();
        
    }
    
   
     public void setCursor(int[] i,JComponent[] j,KeyEvent e){                   // Set the cursour when neccessary keys are pressed
        for(int x=0;x<i.length;x++){
            if(e.getKeyCode()==i[x]){
                j[x].requestFocus();
            }
        }
       
    }
    
    private boolean isNumber(String number){
       
        if(!number.matches("\\d+")){
                return true;
        }return false;
      }
    
    private void setCount(String batchID,String fieldID) throws ClassNotFoundException, SQLException{
        Object[] row={StudentController.getPlacementCount(fieldID, batchID,"M"),StudentController.getPlacementCount(fieldID, batchID,"F")};
    }

    private void setData(){
        dtm1.getDataVector().removeAllElements();
        dtm2.getDataVector().removeAllElements();
        academicYear=jYear.getText();
        field=jField.getSelectedItem().toString();
        jCountTopic.setText("Students counts of "+field+" field in batch "+academicYear+" / 0"+batchNo);

        try {
            fieldID=FieldController.getFieldID(field);
            batchID=BatchController.getBatchID(academicYear,batchNo);

            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

            if(academicYear.isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Please enter a year","Error",0);
                jYear.requestFocus();

            } else if(academicYear.length()!=4 | isNumber(academicYear)){
                JOptionPane.showMessageDialog(rootPane, "The year you eneterd is incorrect","Error",0);
                jYear.requestFocus();
            }
            else if(Integer.parseInt(academicYear)>Integer.parseInt(year)+2){
                JOptionPane.showMessageDialog(rootPane, "The year you eneterd is far future","Error",0);
                jYear.requestFocus();
            }
            else if(batchNo==0){
                JOptionPane.showMessageDialog(rootPane, "Please select a batch number","Error",0);
                
            }
            else{
                if(field=="All"){
                   jPanel3.setVisible(false);
                   jPanel4.setVisible(true);
                   jTopicBatch.setText("Students count in batch "+academicYear+" / "+batchNo);
                   ArrayList<Field> fields=FieldController.getField();
                   for(int i=0;i<fields.size();i++){
                       fieldID=FieldController.getFieldID(fields.get(i).getName());
                       Object[] fieldName={fields.get(i).getName()};
                       dtm2.addRow(fieldName);
                       Object[] row={StudentController.getPlacementCount(fieldID, batchID,"M"),StudentController.getPlacementCount(fieldID, batchID,"F"),StudentController.getDropoutCount(fieldID, batchID,"M"),StudentController.getDropoutCount(fieldID, batchID,"F"),StudentController.getInTrainingCount(fieldID, batchID,"M"),StudentController.getInTrainingCount(fieldID, batchID,"F")};
                       dtm1.addRow(row);
                   
                   }
                   //Object[] row={}

                }else{
                    jPanel4.setVisible(false);
                    dtm.setValueAt(StudentController.getPlacementCount(fieldID, batchID,"M"), 0, 1);
                    dtm.setValueAt(StudentController.getPlacementCount(fieldID, batchID,"F"), 0, 2);
                    dtm.setValueAt(StudentController.getDropoutCount(fieldID, batchID,"M"), 1, 1);
                    dtm.setValueAt(StudentController.getDropoutCount(fieldID, batchID,"F"), 1, 2);
                    dtm.setValueAt(StudentController.getInTrainingCount(fieldID, batchID,"M"), 2, 1);
                    dtm.setValueAt(StudentController.getInTrainingCount(fieldID, batchID,"F"), 2, 2);
                    jPanel3.setVisible(true);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetCount.class.getName()).log(Level.SEVERE, null, ex);
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

        batch = new javax.swing.ButtonGroup();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jDisplay = new javax.swing.JPanel();
        jPanelGetCounts = new javax.swing.JPanel();
        jField = new javax.swing.JComboBox();
        jLabelField = new javax.swing.JLabel();
        jLabelYear = new javax.swing.JLabel();
        jSearch = new javax.swing.JButton();
        jYear = new javax.swing.JTextField();
        jLabelBatch = new javax.swing.JLabel();
        jBatch1 = new javax.swing.JRadioButton();
        jBatch2 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jCountTopic = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableCount = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableField = new javax.swing.JTable();
        jTopicBatch = new javax.swing.JLabel();

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable3);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanelGetCounts.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Power", "Ref & Air", "Electronic", "Mechanist", "Welders", "Wood work", "Plumbers", "Construction Supervisor", "ICT", "Computer Graphic Designing" }));
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBatch1MouseClicked(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
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
                .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGetCountsLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanelGetCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelBatch)
                            .addComponent(jBatch1)
                            .addComponent(jBatch2)))
                    .addGroup(jPanelGetCountsLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jSearch)))
                .addGap(256, 256, 256))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(null);

        jPanel3.setLayout(null);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"No of Placement", null, null},
                {"Dropouts", null, null},
                {"In-Training", null, null}
            },
            new String [] {
                "", "Male", "Female"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable);

        jPanel3.add(jScrollPane3);
        jScrollPane3.setBounds(90, 100, 385, 80);

        jCountTopic.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jPanel3.add(jCountTopic);
        jCountTopic.setBounds(20, 20, 520, 30);

        jPanel5.add(jPanel3);
        jPanel3.setBounds(58, 57, 570, 260);

        jPanel4.setLayout(null);

        jTableCount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Male", "Female", "Male", "Female", "Male", "Female"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTableCount);

        jPanel4.add(jScrollPane5);
        jScrollPane5.setBounds(180, 110, 440, 200);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("         No of Placement");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel1);
        jLabel1.setBounds(180, 90, 150, 16);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("             Dropouts");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel2);
        jLabel2.setBounds(330, 90, 140, 16);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("            In-Training");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel3);
        jLabel3.setBounds(470, 90, 144, 16);

        jTableField.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Field"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTableField);

        jPanel4.add(jScrollPane7);
        jScrollPane7.setBounds(10, 110, 170, 200);

        jTopicBatch.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jPanel4.add(jTopicBatch);
        jTopicBatch.setBounds(141, 30, 350, 30);

        jPanel5.add(jPanel4);
        jPanel4.setBounds(10, 12, 640, 340);

        javax.swing.GroupLayout jDisplayLayout = new javax.swing.GroupLayout(jDisplay);
        jDisplay.setLayout(jDisplayLayout);
        jDisplayLayout.setHorizontalGroup(
            jDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelGetCounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDisplayLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jDisplayLayout.setVerticalGroup(
            jDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDisplayLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanelGetCounts, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(416, Short.MAX_VALUE))
            .addGroup(jDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDisplayLayout.createSequentialGroup()
                    .addContainerGap(192, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBatch2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBatch2KeyPressed
        int[] i ={38,37,40};
        JComponent[] j ={jYear,jBatch1,jSearch};
        setCursor(i,j, evt);
        if(evt.getKeyCode()==37){
            jBatch1.setSelected(true);
            batchNo=1;
        }
        if(evt.getKeyCode()==10){
            jBatch2.setSelected(true);
            batchNo=2;
        }
    }//GEN-LAST:event_jBatch2KeyPressed

    private void jBatch1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBatch1KeyPressed
        int[] i ={38,39,40};
        JComponent[] j ={jYear,jBatch2,jSearch};
        setCursor(i,j, evt);
        if(evt.getKeyCode()==39){
            jBatch2.setSelected(true);
            batchNo=2;
        }
        if(evt.getKeyCode()==10){
            jBatch1.setSelected(true);
            batchNo=1;
        }
    }//GEN-LAST:event_jBatch1KeyPressed

    private void jYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jYearKeyPressed
        int[] i ={38,40};
        JComponent[] j ={jField,jBatch1};
        setCursor(i,j, evt);
    }//GEN-LAST:event_jYearKeyPressed

    private void jSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSearchKeyPressed
        int[] i ={38};
        JComponent[] j ={jBatch1};
        setCursor(i,j, evt);
        if(evt.getKeyCode()==10){
            setData();
        }
    }//GEN-LAST:event_jSearchKeyPressed

    private void jSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchActionPerformed
        setData();
    }//GEN-LAST:event_jSearchActionPerformed

    private void jFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFieldKeyPressed
        int[] i ={10};
        JComponent[] j ={jYear};
        setCursor(i,j, evt);
    }//GEN-LAST:event_jFieldKeyPressed

    private void jFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFieldActionPerformed

    private void jBatch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBatch1MouseClicked
            jBatch1.setSelected(true);
            batchNo=1;
    }//GEN-LAST:event_jBatch1MouseClicked

    private void jBatch2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBatch2MouseClicked
            jBatch2.setSelected(true);
            batchNo=2;
    }//GEN-LAST:event_jBatch2MouseClicked

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
            java.util.logging.Logger.getLogger(GetBatchCount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetBatchCount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetBatchCount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetBatchCount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GetBatchCount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup batch;
    private javax.swing.JRadioButton jBatch1;
    private javax.swing.JRadioButton jBatch2;
    private javax.swing.JLabel jCountTopic;
    private javax.swing.JPanel jDisplay;
    private javax.swing.JTable jDisplay1;
    private javax.swing.JTable jDisplay2;
    private javax.swing.JComboBox jField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelBatch;
    private javax.swing.JLabel jLabelField;
    private javax.swing.JLabel jLabelYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelGetCounts;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton jSearch;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTableCount;
    private javax.swing.JTable jTableField;
    private javax.swing.JLabel jTopic;
    private javax.swing.JLabel jTopic1;
    private javax.swing.JLabel jTopicBatch;
    private javax.swing.JTextField jYear;
    // End of variables declaration//GEN-END:variables
}
