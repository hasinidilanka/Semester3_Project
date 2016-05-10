/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import CompanyGUI.AddCompanyGUI;
import Controller.CompanyController;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Hasini
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Connection connection = DBConnection.getDBConnection().getConnection();
        CompanyController.loadArrays();
        AddCompanyGUI a = new AddCompanyGUI();
        a.setVisible(true);
    }
            
    
}
