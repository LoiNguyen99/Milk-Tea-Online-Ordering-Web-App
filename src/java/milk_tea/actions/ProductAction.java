/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import milk_tea.dtos.ProductDTO;
import mlik_tea.daos.ProductDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class ProductAction extends ActionSupport {

    String submitAction;

    String productID;
    String productName;
    String price;
    String category;
    List<Integer> checkedSizeList;
    String imageFile;
    String status;
    private ProductDTO dto;

    public ProductAction() {
    }

    @Action(value = "/product-action", results = {
        @Result(name = SUCCESS, location = "/manage-product?action=list", type = "redirect")
    })
    public String execute() throws Exception {
        ProductDAO dao = new ProductDAO();
        if (submitAction.equals("delete")) {

            int productID = Integer.parseInt(this.productID);
            if (dao.deleteProduct(productID)) {
                return SUCCESS;
            }
        } else {
            int productID = Integer.parseInt(this.productID);
            int categoryID = Integer.parseInt(this.category);
            Float price = Float.parseFloat(this.price);
            if (imageFile.length() == 0) {
                if (dao.updateProductNotImage(productID, productName, price, categoryID, status)) {
                    dao.deleteSizeByProductID(productID);
                    for (int i = 0; i < checkedSizeList.size(); i++) {
                        
                        if (!dao.insertProductSize(productID, checkedSizeList.get(i))) {
                            return "fail";
                        }
                    }
                }
                return SUCCESS;
            }
        }
        
        return SUCCESS;
    }

public String getSubmitAction() {
        return submitAction;
    }

    public void setSubmitAction(String submitAction) {
        this.submitAction = submitAction;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Integer> getCheckedSizeList() {
        return checkedSizeList;
    }

    public void setCheckedSizeList(List<Integer> checkedSizeList) {
        this.checkedSizeList = checkedSizeList;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public ProductDTO getDto() {
        return dto;
    }

    public void setDto(ProductDTO dto) {
        this.dto = dto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
