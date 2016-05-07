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
public class Field {
   
    private String fieldID;
    private String name;
    private int trainingPeriod;
    private ArrayList<Student> students;
    
    public Field(String fieldID,String name,int trainingPeriod){
      this.fieldID = fieldID;
      this.name=name;
      this.trainingPeriod=trainingPeriod;
    }
     
    
    
    
    


    public String getFieldID() {
        return fieldID;
    }

    public String getName() {
        return name;
    }

    public int getTrainingPeriod() {
        return trainingPeriod;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(Student s) {
        students.add(s);
    }
    
  
    
    
    
    
    
    
}
