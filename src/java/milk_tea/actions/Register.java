/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
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
public class Register extends ActionSupport {
    private final String FAIL = "fail";
    
    private String username;
    private String password;
    private String confirm;
    private String fullname;
    private String phoneNo;
    private String address;
    
    public Register() {
    }
    @Action(value = "/register", results = {
        @Result(name = SUCCESS, location = "/register.jsp", type = "redirect")
        ,
        @Result(name = FAIL, location = "/error.jsp")
    })
    public String execute() throws Exception {
        UserDTO dto = new UserDTO(username, password, fullname, address, phoneNo);
        UserDAO dao = new UserDAO();
        if(dao.insert(dto))
        {
            return SUCCESS;
        }
        else{
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERROR", "Failed to register");
            return FAIL;
        }
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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
