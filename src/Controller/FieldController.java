    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DBConnection;
import DB.DBHandler;
import Model.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hasini
 */
public class FieldController {

    public static String getFieldID(String name) throws ClassNotFoundException, SQLException {
        String sql = "Select * From Field where name = '" + name + "'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);

        while (rst.next()) {

            return rst.getString(1);

        }

        return null;
    }

    public static ArrayList<Field> getField() throws SQLException, ClassNotFoundException {
        String sql = "Select * from field";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);

        ArrayList<Field> fields = new ArrayList<Field>();
        while (rst.next()) {
            Field newField = new Field(rst.getString(1),rst.getString(2), rst.getInt(3));
            fields.add(newField);
        }
        return fields;
    }
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
