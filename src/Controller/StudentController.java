/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DBConnection;
import Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Hasini
 */
public class StudentController {
    
       public static boolean addStudent(Student student) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into student Values(?,?,?,?,?,?,?,?,?,?,?)");
        stm.setObject(1, student.getIndexNumber());
        //stm.setObject(2, student.get);
        //stm.setObject(3, customer.getAddress());
        stm.setObject(4, student.getName());
        stm.setObject(5, student.getAddress());
        stm.setObject(6, student.getGender());
        stm.setObject(7, student.getContactNumber());
        stm.setObject(8, student.getIDNumber());
        stm.setObject(9, student.getContractNumber());
        stm.setObject(10, student.getStatus());
        stm.setObject(11, student.getReason());
        int res = stm.executeUpdate();
        return res > 0;
    }
    
}
