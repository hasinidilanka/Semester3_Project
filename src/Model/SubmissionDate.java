/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Rangika
 */
public class SubmissionDate {
    Date submissionDate;
    Date dueDate;
    int number;
    String indexNo;
    
    public SubmissionDate(Date submissionDate,Date dueDate,int number,String indexNo){
        this.submissionDate=submissionDate;
        this.dueDate=dueDate;
        this.number=number;
        this.indexNo=indexNo;
    }
    public String getIndexNo(){
        return indexNo;
    }
    public Date getSubmissionDate() {
        return submissionDate;
    }
    
    public Date getDueDate() {
        return dueDate;
    }
    
    public int getNumber() {
        return number;
    }
}
