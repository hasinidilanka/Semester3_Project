/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DBConnection;
import DB.DBHandler;
import Model.Batch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hasini
 */
public class BatchController {
    
        public static boolean addBatch(Batch batch) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into Batch Values(?,?,?)");
        stm.setObject(1, getNextBatchID(getLastBatchID()));
        stm.setObject(2, batch.getAcademicYear());
        stm.setObject(3, batch.getBatchNo());
        
        int res = stm.executeUpdate();
        return res > 0;
    }
        
    public static void searchBatch(String year,int number) throws ClassNotFoundException, SQLException {
        String sql = "Select batchID From Batch where year='" + year + "'and number = '"+number+"'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        
        boolean exist = false;
        while(rst.next()){
            System.out.println(rst.getString(1));
            exist = true;
        }
        System.out.println(exist);
        if(!exist){
            
            Batch b = new Batch(year,number);
            addBatch(b);
            
        }
      
    }
        
        public static String getNextBatchID(String code){
            String prefix=code.replaceAll("[\\d]", "");
            String value=code.replaceAll("[\\D]", "");
            int num=value.length();
            int kpNewValue=Integer.parseInt(value)+1;
            String newCode=String.format((prefix+"%0"+num+"d"),kpNewValue);
            return newCode;
        }
        
        
        public static String getLastBatchID() throws ClassNotFoundException, SQLException{
            String sql = "Select batchID From Batch order by batchID desc limit 1";
            ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
            
             while(rst.next()){
                 System.out.println(rst.getString(1));
                 return rst.getString(1);
                 
             }
             
             return null;            
             
             
        }
    
}
