/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.dtos;

import java.util.HashMap;

/**
 *
 * @author Loi Nguyen
 */
public class CartDTO {

    String userID;
    HashMap<CartItemDTO, Integer> items;

    public CartDTO(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public HashMap<CartItemDTO, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<CartItemDTO, Integer> items) {
        this.items = items;
    }

    public void addItem(CartItemDTO newItem, int quantity) {
        int newquantity = quantity;
        if (items == null) {
            items = new HashMap<>();
        }
        if (items.containsKey(newItem)) {
            newquantity = items.get(newItem);
            newquantity = newquantity + quantity;
        }
        items.put(newItem, newquantity);
    }
}
