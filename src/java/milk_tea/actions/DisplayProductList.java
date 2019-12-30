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
public class DisplayProductList extends ActionSupport {
    String category;
    String page;
    String pageSize;
    List<ProductDTO> productList;

    public DisplayProductList() {
    }

    @Action(value = "/product", results = {
        @Result(name = SUCCESS, location = "/product.jsp")
        ,
        @Result(name = "failed", location = "/error.jsp")
    })
    public String execute() throws Exception {
        
        if (page == null) {
            page = "1";
        } 
        if (!page.matches("[1-9]{1,}")) {
            page = "1";
        }
        else if (Integer.parseInt(page) < 1) {
            page = "1";
        }

        if(category.equals("all"))
        {
            return getAllProduct();
        }
        else if(category.equalsIgnoreCase("tra-sua"))
        {
            return getProductByCategoryID(1);
        }
        else if(category.equalsIgnoreCase("tra-sui-bot"))
        {
            return getProductByCategoryID(2);
        }
        else if(category.equalsIgnoreCase("tra-tuoi"))
        {
            return getProductByCategoryID(3);
        }
        
        return "failed";
    }

    public String getAllProduct() throws Exception
    {
        int begin, end;
        begin = 10 * (Integer.parseInt(page) - 1);
        end = 10 * Integer.parseInt(page);
        ProductDAO dao = new ProductDAO();
        productList = dao.getAllProductFromBeginToEnd(begin, end);
        if (productList == null) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERROR", "Error to load data from database");
            return "failed";
        } else {
            return SUCCESS;
        }
    }
    
    public String getProductByCategoryID(int id) throws Exception
    {
        int begin, end;
        begin = 10 * (Integer.parseInt(page) - 1);
        end = 10 * Integer.parseInt(page);
        ProductDAO dao = new ProductDAO();
        productList = dao.getProductByCategoryIDFromBeginToEnd(begin, end, id);
        if (productList == null) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERROR", "Error to load data from database");
            return "failed";
        } else {
            return SUCCESS;
        }
    }
    
    

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }


    

    
}
