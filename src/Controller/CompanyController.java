/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DBConnection;
import DB.DBHandler;
import Model.Company;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Hasini
 */
public class CompanyController {
    //Load arrays (number of vacanciees in each field) of a particular company that is recognized using companyID
    public static void loadArrays() throws SQLException,ClassNotFoundException{
        //get connection to the databse through DBConnection class
        Connection connection = DBConnection.getDBConnection().getConnection();
        //Create a statement
        Statement st = connection.createStatement();
        //Get the result set
        ResultSet rs = st.executeQuery("select * from company");
        //Print the result set
        while(rs.next()){
            System.out.println(rs.getString("companyID"));
            System.out.println(rs.getString("electrical"));
        }
        
    }
    //Writing recieved data from user to the database
    public static boolean addCompany(Company company) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into company Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        stm.setObject(1, company.getRefNumber());
        stm.setObject(2, company.getCompanyName());
        stm.setObject(3, company.getCompanyAddress());
        stm.setObject(4, company.getContactPerson());
        stm.setObject(5, company.getContactNumber());
        stm.setObject(6, company.getRate());
        stm.setObject(7, company.getIsBlackList());
//        stm.setObject(8, Company.electrical());
//        stm.setObject(9, company.);
//        stm.setObject(10, company.);
//        stm.setObject(11, company.);
//        stm.setObject(12, company.);
//        stm.setObject(13, company.);
//        stm.setObject(14, company.);
//        stm.setObject(15, company.);

        int res = stm.executeUpdate();
        return res > 0;
    }
    
    public static String getCompanyName(String companyID) throws SQLException, ClassNotFoundException{
        String sql = "Select name From company where companyID='" +companyID+ "'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
  
        while (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
    
    public static ArrayList<Company> getCompanies() throws ClassNotFoundException, SQLException{
        String sql="select * from company ORDER BY rate DESC";
        ResultSet rst=DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        
        ArrayList<Company> companies=new ArrayList<Company>();
        while(rst.next()){
            Company company = new Company(rst.getString(3),rst.getInt(6));
            companies.add(company);
        }
        return companies;
    }
    
    public static ArrayList<Company> getCompany(String field) throws ClassNotFoundException, SQLException{
        String sql="select * from company where f ORDER BY rate DESC";
        ResultSet rst=DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        
        ArrayList<Company> companies=new ArrayList<Company>();
        while(rst.next()){
            Company company = new Company(rst.getString(3),rst.getInt(6));
            companies.add(company);
        }
        return companies;
    }

}
