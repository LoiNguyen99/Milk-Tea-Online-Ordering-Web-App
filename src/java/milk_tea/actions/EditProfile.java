/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

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
public class EditProfile extends ActionSupport {
    private final String FAIL = "fail";

    String username;
    String fullname;
    String address;
    String phoneNo;
    
    public EditProfile() {
    }
    
    @Action(value = "/edit-profile", results = {
        @Result(name = SUCCESS, location = "/profile",type = "redirect"),
        @Result(name = FAIL, location = "/error.jsp")
    })
    public String execute() throws Exception {
        UserDAO dao = new UserDAO();
        UserDTO dto = new UserDTO(username, "", fullname, address, phoneNo);
        if(dao.updateUser(dto)){
            return SUCCESS;
        }
        else{
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERROR", "Failed to update");
            return FAIL;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
}
