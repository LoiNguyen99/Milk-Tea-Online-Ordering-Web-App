/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import milk_tea.dtos.ProductDTO;
import mlik_tea.daos.ProductDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class Home extends ActionSupport {
    List<ProductDTO> productList;
    
    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }
    
    public Home() {
    }
    
    @Action(value = "/home",results = {
        @Result(name = SUCCESS, location = "/index.jsp")
    })
    public String execute() throws Exception {
        ProductDAO dao = new ProductDAO();
        productList = dao.getProduct();
        return SUCCESS;
    }
    
}
