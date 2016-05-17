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
    private int rate;

    private boolean isBlackList;
    
    private String[] trade = new String[20];
    private int[] numOfVac = new int[20];
    private int[] femaleCounts = new int[20];
    private int[] maleCounts = new int[20];
    
    public Company(){
    
    }
    
    public Company(String companyName, int rate){
        this.companyName=companyName;
        this.rate=rate;
    }
    
    //Company name
    public void setCompanyName(String name){
        this.companyName = name;
    }
    public String getCompanyName(){
        return companyName;
    }
    
    //Company address
    public void setCompanyAddress(String address){
        this.address = address;
    }
    public String getCompanyAddress(){
        return address;
    }
    
    //Comapny  Contact person
    public void setContactPerson(String contactPerson){
        this.contactPerson = contactPerson;
    }
    public String getContactPerson(){
        return contactPerson;
    }
    
    //Company contact number
    public void setContactNumber(String telePhone){
        this.telePhone = telePhone;
    }
    public String getContactNumber(){
        return telePhone;
    }
    
    //Company referance number
    public void setRefNumber(int referanceNumber){
        this.referanceNumber = referanceNumber;
    }
    public String getRefNumber(){
        return telePhone;
    }
    
    //Is a company blacklisted
    public void setBlackList(boolean isBlackList){
        this.isBlackList = isBlackList;
    }
    public boolean getIsBlackList(){
        return isBlackList;
    }
    
    //Set a rate for a company
    public void setRate(int rate){
        this.rate = rate;
    }
    public int getRate(){
        return rate;
    }
    
    //Relates to Trades
    public void setTradeName(String tradeName){
        for(int i=0;i<trade.length;i++){
            if(trade[i]==null){
                trade[i] = tradeName;
            }
        }
    }
    public String[] getTradeArray(){
        return trade;
    }
    public void setTradeArray( String[] trade){
        this.trade = trade;
    }
    
    ///Relates to vacancies
    public void setVacancies(int count){
        for(int i=0;i<numOfVac.length;i++){
            if(numOfVac[i]==0){
                numOfVac[i] = count;
            }
        }
    }
    public int[] getVacanciesArray(){
        return numOfVac;
    }
    public void setVacanciesArray( int[] numOfVac){
        this.numOfVac = numOfVac;
    }
    
    //Relates to female counts
    public void setFemaleCount(int count){
        for(int i=0;i<femaleCounts.length;i++){
            if(femaleCounts[i]==0){
                femaleCounts[i] = count;
            }
        }
    }
    public int[] getfemaleCountArray(){
        return femaleCounts;
    }
    public void setFemaleCountArray( int[] femaleCounts){
        this.femaleCounts = femaleCounts;
    }
    
    //Relates to mail count
    public void setMaleCount(int count){
        for(int i=0;i<maleCounts.length;i++){
            if(maleCounts[i]==0){
                maleCounts[i] = count;
            }
        }
    }
    public int[] getMaleCount(){
        return maleCounts;
    }
    public void setMaleCountArray( int[] maleCounts){
        this.maleCounts = maleCounts;
    }
  
    
}
