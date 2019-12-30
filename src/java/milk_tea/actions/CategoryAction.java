/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import milk_tea.dtos.CategoryDTO;
import mlik_tea.daos.CategoryDAO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class CategoryAction extends ActionSupport {
    private final String DELETE = "delete";
    private final String INSERT = "insert";
    private final String EDIT = "edit";
    private final String FAIL = "fail";
    String categoryID;
    String categoryName;
    String status;
    String action;
    public CategoryAction() {
        
        
    }
    
    @Action(value = "/category-action", results = {
        @Result(name = SUCCESS, location = "/manage-category?action=list", type = "redirect")
    })
    public String execute() throws Exception {
        if(action.equals(INSERT))
        {
            CategoryDTO dto = new CategoryDTO();
            dto.setCategoryName(categoryName);
            CategoryDAO dao = new CategoryDAO();
                if(dao.insert(dto))
                {
                    return SUCCESS;
                }
                else{
                    HttpServletRequest reques = ServletActionContext.getRequest();
                reques.setAttribute("ERROR", "Insert fail!");
                }

        }
        else if(action.equals(EDIT))
        {
            System.out.println("CA "+categoryID);
            System.out.println("CA "+categoryName);
            int categoryID = Integer.parseInt(this.categoryID);
            CategoryDTO dto = new CategoryDTO(categoryID, categoryName);
            dto.setStatus(status);
            CategoryDAO dao = new CategoryDAO();
            if(dao.updateCategory(dto))
            {
                return SUCCESS;
            }
            else{
                HttpServletRequest reques = ServletActionContext.getRequest();
                reques.setAttribute("ERROR", "Update fail!");
            }
        }
        else if(action.equals(DELETE))
        {
            CategoryDAO dao = new CategoryDAO();
            int categoryID = Integer.parseInt(this.categoryID);
            if(dao.deleteCategory(categoryID))
            {
                return SUCCESS;
            }
            else{
                HttpServletRequest reques = ServletActionContext.getRequest();
                reques.setAttribute("ERROR", "Delete fail!");
            }
        }
        
        return FAIL;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
