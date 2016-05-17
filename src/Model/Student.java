/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Hasini
 */
public class Student {
    private String indexNumber;
    private String name;
    private String gender;
    private String address;
    private String contactNumber;
    private String IDNumber;
    private String contractNumber;
    private int status;
    private String reason;
    private ArrayList<Establishment> establishment;
    private String field;
    private String academicYear;
    private int batch;
    
   
   
    
    public Student(String indexNumber,String name,String address,String gender,String contactNumber,String IDNumber,String contractNumber,int status,String reason){
        this.indexNumber=indexNumber;   
        this.name=name;
        this.gender=gender;
        this.address=address;
        this.contactNumber=contactNumber;
        this.IDNumber=IDNumber;
        this.contractNumber=contactNumber;
        this.status=status;
        this.reason=reason;
       
     
    }
    
    public Student(String indexNo,String name){
        this.indexNumber=indexNo;
        this.name=name;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getBatch() {
        return batch;
    }
    
    
    
    

    public String getContractNumber() {
        return contractNumber;
    }

    public int getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }  
    
    
    public String getIndexNumber(){
        return indexNumber;
    }
    
    public String getName(){
        return name;
    }
     
    public String getGender(){
        return gender;
    }
     
   public String getAddress(){
        return address;
    }
   
    public String getContactNumber(){
        return contactNumber;
    }
    
    public String getIDNumber(){
        return IDNumber;
    }
    
    public String getField(){
        return field;
    }
    
    
    
    public String getAcademicYear(){
        return academicYear;
    }  
   
    
    public void addEstablishment(Establishment e){
        establishment.add(e);
    }
    
    public ArrayList<Establishment> getEstablishments(){
        return establishment;
    }
    
    
    
}
