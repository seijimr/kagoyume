/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ky;

import java.sql.Timestamp;

/**
 *
 * @author morita
 */
public class ItemDataBeans {
    private String name ="";
    private String description = "";
    private String smallimg = "";
    private String medimg= "";
    private String availability="";
    private int price =0;
    private String code = "";
    private Timestamp buyDate;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return the smallimg
     */
    public String getSmallimg() {
        return smallimg;
    }

    /**
     * @param smallimg the smallimg to set
     */
    public void setSmallimg(String smallimg) {
        this.smallimg = smallimg;
    }

    /**
     * @return the medimg
     */
    public String getMedimg() {
        return medimg;
    }

    /**
     * @param medimg the medimg to set
     */
    public void setMedimg(String medimg) {
        this.medimg = medimg;
    }

    /**
     * @return the availability
     */
    public String getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = new Integer(price);
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the buyDate
     */
    public Timestamp getBuyDate() {
        return buyDate;
    }

    /**
     * @param buyDate the buyDate to set
     */
    public void setBuyDate(Timestamp buyDate) {
        this.buyDate = buyDate;
    }
}
