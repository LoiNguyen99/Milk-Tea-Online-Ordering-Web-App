package milk_tea.dtos;

import java.text.DecimalFormat;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Loi Nguyen
 */
public class ProductDTO {

    private int productID;
    private String productName;
    private float price;
    private String imagePath;
    private int categoryID;
    private String categoryName;
    private HashMap<Integer, String> sizeList;
    private String createDate;
    private String status;
    
    
    public ProductDTO() {
    }

    public ProductDTO(int productID, String productName, float price, String imagePath) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.imagePath = imagePath;
    }

    public ProductDTO(String productName, float price, String imagePath, int categoryID) {

        this.productName = productName;
        this.price = price;
        this.imagePath = imagePath;
        this.categoryID = categoryID;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public HashMap<Integer, String> getSizeList() {
        return sizeList;
    }

    public void setSizeList(HashMap<Integer, String> sizeList) {
        this.sizeList = sizeList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
