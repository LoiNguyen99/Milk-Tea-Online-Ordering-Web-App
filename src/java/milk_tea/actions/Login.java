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
import javax.servlet.http.HttpServletRequest;
import milk_tea.dtos.UserDTO;
import mlik_tea.daos.UserDAO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class Login extends ActionSupport {
    private final String FAIL = "fail";
    private String username;
    private String password;
    
    
    public Login() {
    }
    
    
    @Action(value = "/login", results = {
        @Result(name = SUCCESS, location = "/home", type = "redirect"),
        @Result(name = FAIL, location = "/error.jsp")
    })
    public String execute() throws Exception {
        UserDAO dao = new UserDAO();
        UserDTO dto = dao.Login(username, password);
        if(dto != null)
        {
            Map session = ActionContext.getContext().getSession();
            session.put("USERINFO", dto);
            return SUCCESS;
        }
        else{
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERROR", "Invalid username or password");
            return FAIL;
        }
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
}
