/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubmissionGUI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Rangika
 */
public class CellRenderer extends DefaultTableCellRenderer {
    int rowSelected;
    int columnSelected;
    Component cell;
    public CellRenderer(int row,int column){
        this.rowSelected=row;
        this.columnSelected=column;
    }
    Color originalColor=null;
    Color cellC=null;
    public void setNewValues(int row,int column){
        this.rowSelected=row;
        this.columnSelected=column;
    }
@Override
public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
    
    cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
    
//    if (isSelected)
        //cell.setBackground(Color.YELLOW);
    if(originalColor==null){
        originalColor=getBackground();
        
        
    }
//    if(originalColor==getBackground()){
////        System.out.println(rowSelected+"--"+columnSelected);
////        cell.setBackground(null);
////        rowSelected=row;
////        if (row == rowSelected ){
////        
////        cell.setBackground(Color.RED);
////                }
////        else cell.setBackground(originalColor);
//        
//        System.out.println("sss");
//        
//    }
    if (row == rowSelected && column == columnSelected){
        
        cell.setBackground(Color.RED);
        
       
                }
        
    if(row==rowSelected && column == columnSelected){
        cell.setBackground(Color.RED);
    }
    else cell.setBackground(originalColor);
    
//    if(originalColor==null){
//        originalColor=getBackground();
//       
//        //System.out.println(originalColor + " " + row + column);
//    }
//    if(cell.getBackground()==Color.RED){
//        
//        cell.setBackground(Color.RED);
//    }
//    else if(row == rowSelected && column == columnSelected){
//        cell.setBackground(Color.RED);
//                }
//    else cell.setBackground(originalColor);
//    for(int i=0;i<rowSelected;i++){
//        if(originalColor==null){
//            
//        }
//    
//    }
    
    return cell;
//        return this;
    

    
}
}