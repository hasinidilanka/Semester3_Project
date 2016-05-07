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
public class Institute {
    private ArrayList<Batch> batch;
    private ArrayList<Company> company;
    private Student s;
    private static Institute institute;
    
    private Institute(){                                              //Make the constructor private.So it wont create more than one Institute       
    }
    
    public static Institute getInstitute() {                //This method will create an object from the institute only if there is no object created previously
        if(institute==null){
            institute = new Institute();
            return institute;
        }
        return institute;
    }
    
    public void addBatch(String academicYear, int batchNo){
        boolean isExist=false; 
        for(Batch b : batch){
            if(b.getAcademicYear()==academicYear & b.getBatchNo()==batchNo){
                isExist=true;
            }
        }
        if(isExist==false){
            Batch newBatch=new Batch(academicYear,batchNo);
            System.out.println(newBatch.getBatchID());
            batch.add(newBatch);
        }
        
    }
    
    public void addUser(){
    
    }
    
    public void addCompany(Company c){
        company.add(c);
    }
    
    
    
}
