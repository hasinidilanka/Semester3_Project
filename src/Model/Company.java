/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.Hashtable;
/**
 *
 * @author nadeesha_pc
 */
public class Company {
    
    private String companyName;
    private String address;
    private String contactPerson;
    private String telePhone;

    
    private int referanceNumber;
    
    private boolean isBlackList;
    
    public Company(){
    
    }
    
    public void setCompanyName(String name){
        this.companyName = name;
    }
    
    public String getCompanyName(){
        return companyName;
    }
    
    public void setCompanyAddress(String address){
        this.address = address;
    }
    
    public String getCompanyAddress(){
        return address;
    }
    
    public void setContactPerson(String contactPerson){
        this.contactPerson = contactPerson;
    }
    
    public String getContactPerson(){
        return contactPerson;
    }
    
    public void setContactNumber(String telePhone){
        this.telePhone = telePhone;
    }
    
    public String getContactNumber(){
        return telePhone;
    }
    
    public void setRefNumber(int referanceNumber){
        this.referanceNumber = referanceNumber;
    }
    
    public String getRefNumber(){
        return telePhone;
    }
    public void setBlackList(boolean isBlackList){
        this.isBlackList = isBlackList;
    }
    
    public boolean getIsBlackList(){
        return isBlackList;
    }
  
    
    public static void power(){
        int vacancy;
        int maleCount ;
        int femaleCount ;
    }
    public static void ref_and_air(){
        int vacancy;
        int maleCount ;
        int femaleCount ;
    }
    public static void electronic(){
        int vacancy;
        int maleCount ;
        int femaleCount ;
    }
    public static void mechinist(){
        int vacancy;
        int maleCount ;
        int femaleCount ;
    }
    public static void welding(){
        int vacancy;
        int maleCount ;
        int femaleCount ;
    }
    public static void wood_work(){
        int vacancy;
        int maleCount ;
        int femaleCount ;
    }
    public static void plumber(){
        int vacancy;
        int maleCount ;
        int femaleCount ;
    }
    public static void construction(){
        int vacancy;
        int maleCount ;
        int femaleCount ;
    }
    public static void ict(){
        int vacancy;
        int maleCount ;
        int femaleCount ;
    }
    public static void graphic_designing(){
        int vacancy;
        int maleCount ;
        int femaleCount ;
    }
}
