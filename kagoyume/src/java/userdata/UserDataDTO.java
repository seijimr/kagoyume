/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userdata;

import java.sql.Timestamp;

/**
 *
 * @author morita
 */
public class UserDataDTO {

    private int userID;
    private String name;
    private String pass;
    private String mail;
    private String address;
    private int total;
    private Timestamp newDate;
    private int deleteflg = 0;

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the newDate
     */
    public Timestamp getNewDate() {
        return newDate;
    }

    /**
     * @param newDate the newDate to set
     */
    public void setNewDate(Timestamp newDate) {
        this.newDate = newDate;
    }

    /**
     * @return the deleteflg
     */
    public int getDeleteflg() {
        return deleteflg;
    }

    /**
     * @param deleteflg the deleteflg to set
     */
    public void setDeleteflg(int deleteflg) {
        this.deleteflg = deleteflg;
    }

}
