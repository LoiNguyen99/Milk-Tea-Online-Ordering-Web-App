/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import milk_tea.dtos.UserDTO;
import mlik_tea.daos.UserDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class ManageUser extends ActionSupport {
    
    private final String LIST = "list";
    public final String EDIT = "edit";
    private String action;
    private String status;
    
    List<UserDTO> list;
    HashMap<Integer, String> roleList;
    UserDTO dto;
    String username;
        
    double pageSize;
    String page;
    
    public ManageUser() {
    }
    
    @Action(value = "/manage-user",
            results = {
                @Result(name = SUCCESS, location = "/manage_user.jsp")
                ,
                                @Result(name = INPUT, location = "/manage_user.jsp")
                ,
                                @Result(name = "failed", location = "failed.jsp")

            }
    )
    public String execute() throws Exception {
        if(action == null)
        {
            action = LIST;
        }
        
        if(action.equals(LIST))
        {
            if(status == null)
            {
                status = "true";
            }
            UserDAO dao  =new UserDAO();
            list = dao.getUsersFromBeginToEnd(1, 20,status);
            if(list != null)
            {
                pageSize = Math.ceil(dao.getAmountOfUser()/20f);
                if(page == null)
                {
                    page = "1";
                }
                int page = Integer.parseInt(this.page);
                if(page < 1)
                {
                    page = 1;
                }
                return SUCCESS;
            }
            else{
                return "fail";
            }
        } else if (action.equals(EDIT))
        {
            UserDAO dao = new UserDAO();
            dto = dao.getAllProertyOfUserByUsername(username);
            roleList = dao.getRole();
            if(dto != null)
            {
                return SUCCESS;
            }
        }
        return SUCCESS;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<UserDTO> getList() {
        return list;
    }

    public void setList(List<UserDTO> list) {
        this.list = list;
    }

    public double getPageSize() {
        return pageSize;
    }

    public void setPageSize(double pageSize) {
        this.pageSize = pageSize;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<Integer, String> getRoleList() {
        return roleList;
    }

    public void setRoleList(HashMap<Integer, String> roleList) {
        this.roleList = roleList;
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
