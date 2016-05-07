/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.SQLException;

/**
 *
 * @author Hasini
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //BatchController.getLastBatchID();
       // BatchController.searchBatch("1998", 1);
        //BatchController.searchBatch("2015", 1);
        BatchController.searchBatch("2016", 2);
    }
    
}
