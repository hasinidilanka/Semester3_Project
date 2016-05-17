/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DBConnection;
import DB.DBHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Rangika
 */
public class DurationController {
    
    
     public static void addDuration(String BatchID,String fieldID,Date startDate,Date endDate) throws ClassNotFoundException {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("insert into duration values(?,?,?,?,?)");
            System.out.println("d1 "+startDate);
            System.out.println("d2 "+endDate);
            Object param1 = new java.sql.Timestamp(startDate.getTime());
            System.out.println("p1 "+param1);
            
            Object param2 = new java.sql.Timestamp(endDate.getTime());
            System.out.println("p2 "+param2);
            stm.setObject(1, BatchID+fieldID);
            stm.setObject(2, BatchID);
            stm.setObject(3, fieldID);
            stm.setObject(4, startDate);
            stm.setObject(5, endDate);
            int res = stm.executeUpdate();
            
        } catch (SQLException ex) {
                System.out.println("error");
        }
    }
    
    public static int getStartMonth(String fieldID,String batchID) throws ClassNotFoundException, SQLException {
        
        
        
        String sql = "Select startDate From Duration where fieldID = '" + fieldID + "' and batchID='"+batchID+"'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        Date startDate;
        int month;
        while (rst.next()) {

             startDate=rst.getDate(1);
             Calendar now = Calendar.getInstance();
             now.setTime(startDate);
             month= now.get(Calendar.MONTH);
             return month;
        }

        return -1;
    }
    
    public static int getStartYear(String fieldID,String batchID) throws ClassNotFoundException, SQLException {
        
        
        
        String sql = "Select startDate From Duration where fieldID = '" + fieldID + "' and batchID='"+batchID+"'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        Date startDate;
        int year;
        while (rst.next()) {

             startDate=rst.getDate(1);
             Calendar now = Calendar.getInstance();
             now.setTime(startDate);
             year= now.get(Calendar.YEAR);
             System.out.println(year);
             return year;
        }

        return -1;
    }
}
