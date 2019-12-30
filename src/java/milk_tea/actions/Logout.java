/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;
import milk_tea.dtos.CartDTO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class Logout extends ActionSupport {
    
    public Logout() {
    }
    
    @Action(value = "/logout", results = {
        @Result(name = SUCCESS, location = "/home", type = "redirect")
    })
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.remove("USERINFO");
        return SUCCESS;
    }
    
}
