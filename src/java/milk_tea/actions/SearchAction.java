/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import milk_tea.dtos.ProductDTO;
import mlik_tea.daos.ProductDAO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class SearchAction extends ActionSupport {
    private float from;
    private float to;
    List<ProductDTO> productList;
    
    public SearchAction() {
    }
    
    @Action(value = "/search", results = {
        @Result(name = SUCCESS, location = "/search.jsp")
    })
    public String execute() throws Exception {
        ProductDAO dao = new ProductDAO();
        
        List<ProductDTO> list = dao.search(from, to);
        System.out.println(list);
        if(!list.isEmpty())
        {
            productList = list;
        }else{
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("message", "No item found!");
        }
        return SUCCESS;
    }

    public float getFrom() {
        return from;
    }

    public void setFrom(float from) {
        this.from = from;
    }

    public float getTo() {
        return to;
    }

    public void setTo(float to) {
        this.to = to;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }
    
    
    
}
