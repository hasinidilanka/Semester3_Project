/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DBConnection;
import DB.DBHandler;
import Model.Establishment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rangika
 */
public class EstablishmentController {
    
    public static String getEstablishmentID(String indexNo, int number) throws ClassNotFoundException, SQLException{
        String sql = "Select establishmentID From Establishment where indexNumber='" + indexNo + "' and number='" + number + "'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);

        while (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public static String getEstablishment(String indexNo, int number) throws SQLException, ClassNotFoundException {
        String sql = "Select companyID From Establishment where indexNumber='" + indexNo + "' and number='" + number + "'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);

        while (rst.next()) {

            if (rst.getString(1).equals("")) {
                return null;
            }
            return rst.getString(1);
        }
        return null;
    }

    public static String getFromDate(String indexNo, int number) throws SQLException, ClassNotFoundException {
        String sql = "Select fromDate From Establishment where indexNumber='" + indexNo + "' and number='" + number + "'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);

        while (rst.next()) {

            if (rst.getString(1).equals("")) {
                return null;
            }
            return rst.getString(1);
        }
        return null;
    }

    public static int setEndDate(Date endDate,String establishmentID) throws ClassNotFoundException, SQLException {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Object today = new java.sql.Timestamp(endDate.getTime());
        String datestring = dateFormat.format(today);
        String sql = "Update Establishment SET toDate='"+datestring+"' where establishmentID='"+establishmentID+"'";
        return DBHandler.setData(DBConnection.getDBConnection().getConnection(), sql);
    }

}
