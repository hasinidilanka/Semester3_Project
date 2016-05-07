/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hasini
 */
public class DBHandler {
    static Statement stm;
    
    public static ResultSet getData(Connection connection,String sql) throws SQLException{
        stm = (Statement)connection.createStatement();
        return stm.executeQuery(sql);
    }
    
    public static int setData(Connection connection,String sql) throws SQLException{
        stm = (Statement)connection.createStatement();
        return stm.executeUpdate(sql);
    }
}
