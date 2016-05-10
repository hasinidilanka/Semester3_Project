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
 * @author Hasini
 */
public class FieldController {
    public static String searchFieldID(String field) throws SQLException, ClassNotFoundException{
        String sql2="Select fieldID from Field where name='"+field+"'";
       ResultSet rst2= DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql2);
       String fieldID="";
       while(rst2.next()){
           fieldID=rst2.getString(1);
       }
       return fieldID;
    
    }
}
