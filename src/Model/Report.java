/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sss;

import java.util.Date;

/**
 *
 * @author nadeesha_pc
 */
public abstract class Report {
    public abstract void setCurrentDate();
    public abstract Date getCurrentDate();
    
    public String reportHeading;
}
