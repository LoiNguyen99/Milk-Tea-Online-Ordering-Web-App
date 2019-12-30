/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import milk_tea.dtos.SizeDTO;
import mlik_tea.daos.ProductDAO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class SizeAction extends ActionSupport {
    
    private final String DELETE = "delete";
    private final String INSERT = "insert";
    private final String EDIT = "edit";
    private final String FAIL = "fail";
    String sizeID;
    String sizeName;
    String status;
    String action;
    public SizeAction() {
        
        
    }
    
    @Action(value = "/size-action", results = {
        @Result(name = SUCCESS, location = "/manage-size?action=list", type = "redirect")
    })
    public String execute() throws Exception {
        if(action.equals(INSERT))
        {
            SizeDTO dto = new SizeDTO(sizeName);
            ProductDAO dao = new ProductDAO();
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
            int sizeID = Integer.parseInt(this.sizeID);
            SizeDTO dto = new SizeDTO(sizeID, sizeName);
            dto.setStatus(status);
            ProductDAO dao = new ProductDAO();
            if(dao.updateSize(dto))
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
            ProductDAO dao = new ProductDAO();
            int sizeID = Integer.parseInt(this.sizeID);
            if(dao.deleteSize(sizeID))
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

    public String getSizeID() {
        return sizeID;
    }

    public void setSizeID(String sizeID) {
        this.sizeID = sizeID;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
}
