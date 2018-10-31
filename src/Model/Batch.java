/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Rangika
 */
public class Batch {
    private String batchID;
    private static int count=0;
    private String academicYear;
    private int batchNo;
    private ArrayList<Field> fields ;
	
	private int semID;

    
    
    public Batch(String academicYear, int batchNo ){
        
        this.academicYear=academicYear;
        this.batchNo=batchNo;
        //setBatchID();
        count++;
        
       // setFields();
    }
    
    

    public String getBatchID() {
        return batchID;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public int getBatchNo() {
        return batchNo;
    }
    
    public void setFields(){
        Field f1 = new Field("f00EL","Electrical",18);
        Field f2 = new Field("f00RA","Ref & Air",18);
        Field f3 = new Field("f00EN","Electronic",18);
        Field f4 = new Field("f00MM","Machinist",18);
        Field f5 = new Field("f00MW","Welder",18);
        Field f6 = new Field("f00WW","Wood Work",12);
        Field f7 = new Field("f00PL","Plumbing",12);
        Field f8 = new Field("f00CS","Construction",6);
        Field f9 = new Field("f00ICT","ICT",6);
        Field f10 = new Field("f00CGD","CGD",12);
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
        fields.add(f4);
        fields.add(f5);
        fields.add(f6);
        fields.add(f7);
        fields.add(f8);
        fields.add(f9);
        fields.add(f10);       
        
    }

    public ArrayList<Field> getFields() {
        return fields;
    }
    
   
    
    public void setBatchID(String batchID){
        this.batchID=batchID;
    }
    
    

    
}
