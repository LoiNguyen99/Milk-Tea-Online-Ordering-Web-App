/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.dtos;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import mlik_tea.daos.ProductDAO;

/**
 *
 * @author Loi Nguyen
 */
public class ToppingDTO {
    private int toppingID;
    private String toppingName;
    private float price;
    private String status;

    public ToppingDTO(int toppingID) {
        this.toppingID = toppingID;
    }

    
    
    public int getToppingID() {
        return toppingID;
    }

    public void setToppingID(int toppingID) {
        this.toppingID = toppingID;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ToppingDTO(int toppingID, String toppingName, float price) {
        this.toppingID = toppingID;
        this.toppingName = toppingName;
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("###,###.#");
        String formatedPrice = format.format(price);
        return toppingName + "+" + formatedPrice;//To change body of generated methods, choose Tools | Templates.
    }
    
}
