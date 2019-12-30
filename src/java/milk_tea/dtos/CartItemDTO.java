/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Loi Nguyen
 */
public class CartItemDTO {

    int productID;
    public float price;
    List<ToppingDTO> checkedToppingList;
    SizeDTO size;
    String productName;
    String imagePath;

    public CartItemDTO(int productID, String productName,float price, List<ToppingDTO> checkedToppingList, SizeDTO size, String imagePath) {
        this.productID = productID;
        this.price = price;
        this.checkedToppingList = checkedToppingList;
        this.size = size;
        this.productName = productName;
        this.imagePath = imagePath;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<ToppingDTO> getCheckedToppingList() {
        return checkedToppingList;
    }

    public void setCheckedToppingList(List<ToppingDTO> checkedToppingList) {
        this.checkedToppingList = checkedToppingList;
    }

    public SizeDTO getSize() {
        return size;
    }

    public void setSize(SizeDTO size) {
        this.size = size;
    }


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CartItemDTO)) {
            return false;
        }
        CartItemDTO other = (CartItemDTO) obj;

        if (price != other.price) {
            return false;
        } else if (size.getSizeID() != other.size.getSizeID()) {
            return false;
        }
        else if(checkedToppingList.size() != other.checkedToppingList.size())
        {
            return false;
        }
        
        for (int i = 0; i < checkedToppingList.size(); i++) {
            if(checkedToppingList.get(i).getToppingID() != other.checkedToppingList.get(i).getToppingID())
            {
                return false;
            }
        }
        
        
        
        return true;
    }

    @Override
    public int hashCode() {
        return productID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    
}
