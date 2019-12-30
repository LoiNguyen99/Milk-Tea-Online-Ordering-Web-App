/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.text.DecimalFormat;
import java.util.HashMap;
import milk_tea.dtos.ProductDTO;
import milk_tea.dtos.ToppingDTO;
import mlik_tea.daos.ProductDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class ProductDetails extends ActionSupport {

    private ProductDTO dto;
    private String fomartedPrice;
    HashMap<Integer, ToppingDTO> toppingList;
    int productID;

    public ProductDetails() {
    }

    @Action(value = "/product-details", results = {
        @Result(name = SUCCESS, location = "/product_details.jsp")
        ,
        @Result(name = "failed", location = "/error.jsp")
    })
    public String execute() throws Exception {

        ProductDAO pdao = new ProductDAO();

        dto = pdao.getProductByID(productID);
        if (dto != null) {
            HashMap<Integer, String> sizeList = pdao.getSizeByProductID(productID);
            dto.setSizeList(sizeList);
            toppingList = pdao.getToping();
            return SUCCESS;

        } else {
            return "failed";
        }

    }

    public ProductDTO getDto() {
        return dto;
    }

    public void setDto(ProductDTO dto) {
        this.dto = dto;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public HashMap<Integer, ToppingDTO> getToppingList() {
        return toppingList;
    }

    public void setToppingList(HashMap<Integer, ToppingDTO> toppingList) {
        this.toppingList = toppingList;
    }


    public String getFomartedPrice() {
        DecimalFormat format = new DecimalFormat("###,###.#");
        fomartedPrice = format.format(dto.getPrice());
        return fomartedPrice;
    }

    public void setFomartedPrice(String fomartedPrice) {
        this.fomartedPrice = fomartedPrice;
    }

}
