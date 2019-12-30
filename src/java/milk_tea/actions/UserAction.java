/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
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
public class UserAction extends ActionSupport {

    private final String DELETE = "delete";
    private final String EDIT = "edit";
    private final String FAIL = "fail";

    private String username;
    private String fullname;
    private String phoneNo;
    private String address;
    private String status;
    private String roleid;

    String action;

    private UserDTO dto;
    private List<UserDTO> list;

    public UserAction() {
    }

    @Action(value = "/user-action", results = {
        @Result(name = SUCCESS, location = "/manage-user?action=list", type = "redirect")
    })
    public String execute() throws Exception {
        if (action.equals(EDIT)) {
            UserDTO dto = new UserDTO(username, fullname, address, phoneNo);
            System.out.println(roleid);
            int roleid = Integer.parseInt(this.roleid);
            dto.setRoleID(roleid);
            dto.setStatus(status);
            UserDAO dao = new UserDAO();
            if (dao.updateUserByAdmin(dto)) {
                return SUCCESS;
            } else {
                HttpServletRequest reques = ServletActionContext.getRequest();
                reques.setAttribute("ERROR", "Update fail!");
            }
        } else if (action.equals(DELETE)) {
            UserDAO dao = new UserDAO();
            if (dao.deleteUser(username)) {
                return SUCCESS;
            } else {
                HttpServletRequest reques = ServletActionContext.getRequest();
                reques.setAttribute("ERROR", "Delete fail!");
            }
        }

        return FAIL;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public UserDTO getDto() {
        return dto;
    }

    public void setDto(UserDTO dto) {
        this.dto = dto;
    }

    public List<UserDTO> getList() {
        return list;
    }

    public void setList(List<UserDTO> list) {
        this.list = list;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    

}
