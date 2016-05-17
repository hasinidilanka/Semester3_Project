/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DBConnection;
import DB.DBHandler;
import Model.SubmissionDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Rangika
 */
public class SubmissionDateController {
    
    public static int addDate(String indexNo,Date subDate,Date dueDate,int number) throws SQLException, ClassNotFoundException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Object sDate = new java.sql.Timestamp(subDate.getTime());
        Object dDate = new java.sql.Timestamp(dueDate.getTime());
        String sql = "Insert into SubmissionDate(indexNumber,submissionDate,dueDate,number) value ('"+indexNo+"','"+sDate+"','"+dDate+"','"+number+"')";
        return DBHandler.setData(DBConnection.getDBConnection().getConnection(), sql);
    }
    
    public static Date getDate(String indexNo,int number) throws SQLException, ClassNotFoundException {
        String sql = "Select submissionDate From SubmissionDate where indexNumber='"+indexNo+"' and number='"+number+"'" ;
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);

        while (rst.next()) {

//            if (rst.getString(1).equals("")) {
//                return null;
//            }
            return rst.getDate(1);
        }
        return null;
    }
    
    public static ArrayList<SubmissionDate> getSubDate(String indexNo) throws SQLException, ClassNotFoundException{
        String sql = "Select * From SubmissionDate where indexNumber='"+indexNo+"'" ;
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        ArrayList<SubmissionDate> submissionDates=new ArrayList<SubmissionDate>();
        while (rst.next()) {
            SubmissionDate subDate=new SubmissionDate(rst.getDate(3),rst.getDate(4),rst.getInt(5),rst.getString(2));
            submissionDates.add(subDate);
        }
        return submissionDates;
    }
    
    public static Date getDueDate(int year,int month){
        Date date=new GregorianCalendar(year,month+1,10).getTime();
        return date;
        
    }
    
}
