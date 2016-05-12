/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import DB.DBHandler;
import Model.Student;
import java.util.ArrayList;

/**
 *
 * @author Userpc
 */
public class EstablishmentController1 {
     public static void searchEstablishments(String year, int number, String field) throws SQLException, ClassNotFoundException{
       /*String sql="Select batchId from Batch where year='"+year+"'and number='"+number+"'";
       ResultSet rst= DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
       String batchID="";
       while(rst.next()){
           batchID=rst.getString(1);
       }*/
       String batchID=BatchController.searchBatchID(year, number);
       /*String sql2="Select fieldID from Field where name='"+field+"'";
       ResultSet rst2= DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql2);
       String fieldID="";
       while(rst2.next()){
           fieldID=rst2.getString(1);
       }*/
       String fieldID=FieldController.searchFieldID(field);
       String sql3="Select studentID from Student where batchID='"+batchID+"'and fieldID='"+fieldID+"'";
       ResultSet rst3= DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql3);
       ArrayList<Student> student=new ArrayList<Student>();
       while(rst3.next()){
           
       }
   }
        
    
}
