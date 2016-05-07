/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataBase.CompanyDataBaseConnection;
import Model.Company;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Hasini
 */
public class CompanyController {
    
       public static boolean addCompany(Company company) throws ClassNotFoundException, SQLException {
        Connection connection = CompanyDataBaseConnection.getDataBaseConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into company Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        stm.setObject(1, company.getRefNumber());
        stm.setObject(2, company.getCompanyName());
        stm.setObject(3, company.getCompanyAddress());
        stm.setObject(4, company.getContactPerson());
        stm.setObject(5, company.getContactNumber());
        stm.setObject(6, company.getRate());
        stm.setObject(7, company.getIsBlackList());
        stm.setObject(8, Company.electrical());
        stm.setObject(9, company.);
        stm.setObject(10, company.);
        stm.setObject(11, company.);
        stm.setObject(12, company.);
        stm.setObject(13, company.);
        stm.setObject(14, company.);
        stm.setObject(15, company.);
        int res = stm.executeUpdate();
        return res > 0;
    }
    
}
