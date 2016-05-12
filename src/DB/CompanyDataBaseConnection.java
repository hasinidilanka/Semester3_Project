/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hasini
 */
public class CompanyDataBaseConnection {
    
    private Connection connection;
    
    private static CompanyDataBaseConnection dBConnection;
    private CompanyDataBaseConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://localhost/IndustrialTraining","root","nadeesha");
    }
    public static CompanyDataBaseConnection getDataBaseConnection() throws ClassNotFoundException, SQLException{
        if(dBConnection==null){
            dBConnection=new CompanyDataBaseConnection();
        }
        return dBConnection;
    }
    public Connection getConnection(){
        return connection;
    }
    
}
