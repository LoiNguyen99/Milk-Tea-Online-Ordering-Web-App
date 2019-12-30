/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import milk_tea.dtos.ToppingDTO;
import mlik_tea.daos.ToppingDAO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class ToppingAction extends ActionSupport {
    private final String DELETE = "delete";
    private final String INSERT = "insert";
    private final String EDIT = "edit";
    private final String FAIL = "fail";
    
    String toppingID;
    String toppingName;
    String price;
    String status;
    String action;
    
    public ToppingAction() {
        
        
    }
    
    @Action(value = "/topping-action", results = {
        @Result(name = SUCCESS, location = "/manage-topping?action=list", type = "redirect")
    })
    public String execute() throws Exception {
        if(action.equals(INSERT))
        {
            int toppingID = Integer.parseInt(this.toppingID);
            float price = Float.parseFloat(this.price);
            ToppingDTO dto = new ToppingDTO(toppingID, toppingName, price);
            ToppingDAO dao = new ToppingDAO();
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
            int toppingID = Integer.parseInt(this.toppingID);
            float price = Float.parseFloat(this.price);
            ToppingDTO dto = new ToppingDTO(toppingID, toppingName, price);
            dto.setStatus(status);
            ToppingDAO dao = new ToppingDAO();
            if(dao.updateTopping(dto))
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
            ToppingDAO dao = new ToppingDAO();
            int toppingID = Integer.parseInt(this.toppingID);
            if(dao.deleteTopping(toppingID))
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

    public String getToppingID() {
        return toppingID;
    }

    public void setToppingID(String toppingID) {
        this.toppingID = toppingID;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
