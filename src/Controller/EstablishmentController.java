/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DBConnection;
import DB.DBHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rangika
 */
public class EstablishmentController {
    
    public static String getEstablishment(String indexNo,int number) throws SQLException, ClassNotFoundException{
        String sql = "Select companyID From Establishment where indexNumber='" +indexNo+ "' and number='"+number+"'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
  
        while (rst.next()) {
            
            if(rst.getString(1).equals("")){
                return null;
            }
            return rst.getString(1);
        }
        return null;
    }
    
}
