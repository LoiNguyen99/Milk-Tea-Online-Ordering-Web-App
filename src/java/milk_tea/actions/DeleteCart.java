/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class DeleteCart extends ActionSupport {
    
    public DeleteCart() {
    }
    
    @Action(value = "/delete-cart", results = {
        @Result(name = SUCCESS, location = "cart.jsp", type = "redirect")
    })
    public String execute() throws Exception {
        Map sesion = ActionContext.getContext().getSession();
        sesion.remove("CART");
        return SUCCESS;
    }
    
}
