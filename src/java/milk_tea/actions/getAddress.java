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
public class getAddress extends ActionSupport {
    String username;
    UserDTO dto;
    
    public getAddress() {
    }
    
    @Action(value = "/get-address", results = {
        @Result(name = SUCCESS, location = "/address.jsp"),
        @Result(name = "fail", location = "/fail.jsp")
    })
    public String execute() throws Exception {
        UserDAO dao = new UserDAO();
        System.out.println(username);
        dto = dao.getUserByUsername(username);
        if(dto != null)
        {
            return SUCCESS;
        }
        else {
            HttpServletRequest reques = ServletActionContext.getRequest();
            reques.setAttribute("ERROR", "Không thể lấy thông tin người dùng!");
            return "fail";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDTO getDto() {
        return dto;
    }

    public void setDto(UserDTO dto) {
        this.dto = dto;
    }
    
}
