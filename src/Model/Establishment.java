/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Hasini
 */
public class Establishment {
    private String companyName;
    private String establishmentID;
    private String registrationNumber;
    private Date fromDate;
    private Date toDate;
    private ArrayList<Date> submissionDate;
    
    public Establishment(String establishmentID,String companyName,String registrationNumber,Date fromDate,Date toDate){
        this.establishmentID=establishmentID;
        this.companyName=companyName;
        this.registrationNumber=registrationNumber;
        this.fromDate=fromDate;
        this.toDate=toDate;        
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEstablishmentID() {
        return establishmentID;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public ArrayList<Date> getSubmissionDate() {
        return submissionDate;
    }
    
    
}
