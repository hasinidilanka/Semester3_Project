/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sss;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nadeesha_pc
 */
public class ProgressReport extends Report{

    private Date currentDate;
    private Date dueDate;
    private String academicYear;
    private int batchNumber;
    private int maleCount;
    private int femaleCount;
    private int dropoutsCount;
    private String courseName;
    
    

    public void setCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));        
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getAcademicYear() {
        return academicYear;
    }
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public int getBatchNumber() {
        return batchNumber;
    }
    public void setMaleCount(int maleCount) {
        this.maleCount = maleCount;
    }

    public int getMaleCount() {
        return maleCount;
    }
    public void setFemaleCount(int femaleCount) {
        this.femaleCount = femaleCount;
    }

    public int getFemaleCount() {
        return femaleCount;
    }
    public void setDropoutsCount(int dropoutsCount) {
        this.dropoutsCount = dropoutsCount;
    }

    public int getDropoutsCount() {
        return dropoutsCount;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    

}
