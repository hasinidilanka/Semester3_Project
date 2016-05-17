/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Hasini
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //BatchController.getLastBatchID();
       // BatchController.searchBatch("1998", 1);
        //BatchController.searchBatch("2015", 1);
//        BatchController.searchBatch("2016", 2);
        
           /* String code = "EN/14/01/02";
          String [] s= code.split("/");
           int i = Integer.parseInt(s[3])+1;  
           System.out.println(i);
           String ss = code.substring(0, code.length()-2);
           String sss;
           if(i<10){
               sss= ss+"0"+i;
           }
           else{
               sss= ss+i; 
           }
          
           System.out.println("sss : "+sss);*/
       // String index = StudentController.getLastStudentID("B0001","f00en");
        //System.out.println("index : "+ index);
        //System.out.println("nextIndex : "+StudentController.getNextIndexNumber(index));
        
//        String s = "q1.2q.w";
//        
//        
//        String[] ss = s.split("\\.");
//        System.out.println(ss.length);
//        boolean b;
//        for(String c : ss){
//            System.out.println(c);
//            if(!c.matches("[a-zA-Z]+")){
//                
//            }
//        }
//        String number = "1234r";
//        System.out.println("numb : "+number.matches("\\d+"));
        
        //System.out.println(s.matches("[a-zA-Z]+"));
        //System.out.println(s.matches("."));
       // String ss=s.replaceAll(".", "");
       // System.out.println(ss);
        
        //System.out.println(s.contains("."));
        
         //return name.matches("[a-zA-Z]+");
        
        Date g1 =new GregorianCalendar(2016, 4, 1).getTime();
        System.out.println("g1 "+g1);
        Date g2 =new GregorianCalendar(2017, 4, 1).getTime();
        System.out.println("g2 "+g2);
        DurationController.addDuration("B0002", "f00CGD", g1, g2);
           
        
    }
    
}
