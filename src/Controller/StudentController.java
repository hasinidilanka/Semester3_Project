/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DBConnection;
import DB.DBHandler;
import Model.Student;
import Model.SubmissionDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hasini
 */
public class StudentController {

    public static boolean addStudent(Student student, String batchID, String fieldID) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into student Values(?,?,?,?,?,?,?,?,?,?,?)");
        stm.setObject(1, student.getIndexNumber());
        stm.setObject(2, batchID);
        stm.setObject(3, fieldID);
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

    public static String getLastStudentID(String batchID, String fieldID) throws ClassNotFoundException, SQLException {
        String sql = "Select indexNumber From Student where batchID='" + batchID + "' and fieldID='" + fieldID + "' order by indexNumber desc limit 1";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);

        while (rst.next()) {
            System.out.println(rst.getString(1));
            return rst.getString(1);

        }

        String subYear = BatchController.getAcademeciYear(batchID).substring(2);
        String number = "0" + BatchController.getBatchNumber(batchID);
        String field = fieldID.substring(3);
        System.out.println("sssss");
        return field + "/" + subYear + "/" + number + "/" + "00";

    }
    
    public static int setStatus(int status,String indexNo,String reason) throws SQLException, ClassNotFoundException{
        String sql = "Update student SET status='"+status+"' , reason='"+reason+"' where indexNumber='"+indexNo+"'";
        return DBHandler.setData(DBConnection.getDBConnection().getConnection(), sql);
    }

    public static String getNextIndexNumber(String code) {
        String[] s = code.split("/");
        int i = Integer.parseInt(s[3]) + 1;
        String ss = code.substring(0, code.length() - 2);
        System.out.println("ss : " + ss);
        if (i < 10) {
            return ss + "0" + i;
        } else {
            return ss + i;
        }

    }

    public static String generateIndexNumber(String batchID, String fieldID) throws ClassNotFoundException, SQLException {
        return getNextIndexNumber(getLastStudentID(batchID, fieldID));
    }
    
    public static boolean isExist(String indexNo) throws ClassNotFoundException, SQLException{
        String sql = "Select indexNumber From Student where indexNumber='" + indexNo + "'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        while(rst.next()){
            return true;
        }
        return false;
    }
    public static String searchStudent(String indexNo) throws SQLException, ClassNotFoundException {
        String sql = "Select name From Student where indexNumber='" + indexNo + "'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        
        
        while (rst.next()) {
            if(rst.getString(1).equals("")){
                return null;
            }
            return rst.getString(1);
        }
        return null;
    }

    public static String getMaleCount(String fieldID, String batchID) throws ClassNotFoundException, SQLException {
        int countMale = 0;
        String sql = "SELECT COUNT(indexNumber) FROM student where batchID='" + batchID + "' and fieldID='" + fieldID + "' and gender='M' and status=0 ";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        while (rst.next()) {
            countMale = rst.getInt(1);
        }
        return String.valueOf(countMale);
    }

    public static String getFemaleCount(String fieldID, String batchID) throws ClassNotFoundException, SQLException {
        int countFemale = 0;
        String sql = "SELECT COUNT(indexNumber) FROM student where batchID='" + batchID + "' and fieldID='" + fieldID + "' and gender='F' and status=0 ";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        while (rst.next()) {
            countFemale = rst.getInt(1);
        }
        return String.valueOf(countFemale);
    }

    public static String getInTrainingCount(String fieldID, String batchID, String gender) throws ClassNotFoundException, SQLException {
        int count = 0;
        String sql = "SELECT COUNT(indexNumber) FROM student where batchID='" + batchID + "' and fieldID='" + fieldID + "' and gender='" + gender + "' and status=-2 ";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        while (rst.next()) {
            count = rst.getInt(1);

        }
        return String.valueOf(count);
    }

    public static String getDropoutCount(String fieldID, String batchID, String gender) throws ClassNotFoundException, SQLException {
        int count = 0;
        String sql = "SELECT COUNT(indexNumber) FROM student where batchID='" + batchID + "' and fieldID='" + fieldID + "'and gender='" + gender + "'and status!=-2 and status!=-1 ";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        while (rst.next()) {
            count = rst.getInt(1);
        }
        return String.valueOf(count);
    }

    public static String getPlacementCount(String fieldID, String batchID, String gender) throws ClassNotFoundException, SQLException {
        int count = 0;
        String sql = "SELECT COUNT(indexNumber) FROM student where batchID='" + batchID + "' and fieldID='" + fieldID + "'and gender='" + gender + "'and status!=-1 ";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        while (rst.next()) {
            count = rst.getInt(1);
        }
        return String.valueOf(count);
    }

    public static String getTotalCount(String fieldID, String batchID) throws ClassNotFoundException, SQLException {
        int countTotal = 0;
        String sql = "SELECT COUNT(indexNumber) FROM student where batchID='" + batchID + "' and fieldID='" + fieldID + "'";
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        while (rst.next()) {
            countTotal = rst.getInt(1);
        }
        return String.valueOf(countTotal);
    }
    
   public static ArrayList<Student> getIndexNo(String fieldID,String batchID) throws SQLException, ClassNotFoundException{
       
        String sql = "Select * From Student where fieldID='"+fieldID+"' and batchID='"+batchID+"'" ;
        ResultSet rst = DBHandler.getData(DBConnection.getDBConnection().getConnection(), sql);
        ArrayList<Student> students=new ArrayList<Student>();
        while (rst.next()) {
            Student newStudent = new Student(rst.getString(1),rst.getString(4));
            students.add(newStudent);
        }
        return students;
    
   }

}
