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
public class DisplayProfile extends ActionSupport {
    private final String FAIL = "fail";
    private final String LOGIN = "login";
    
    UserDTO dto;

    public DisplayProfile() {

    }

    @Action(value = "/profile", results = {
        @Result(name = SUCCESS, location = "/profile.jsp"),
        @Result(name = LOGIN, location = "/login.jsp"),
        @Result(name = FAIL, location = "/error.jsp")
    })
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        UserDTO userinfo = (UserDTO) session.get("USERINFO");
        HttpServletRequest request = ServletActionContext.getRequest();
        if (userinfo != null) {
            UserDAO dao = new UserDAO();
            System.out.println(userinfo.getUsername());
            dto = dao.getUserByUsername(userinfo.getUsername());
            if (dto != null) {
                dto.setUsername(userinfo.getUsername());
                dto.setFullname(userinfo.getFullname());
            }
            else{
                request.setAttribute("ERROR", "Không tìm thấy dữ liệu!");
                return FAIL;
            }
        }
        else{
            return LOGIN;
        }
        return SUCCESS;
    }

    public UserDTO getDto() {
        return dto;
    }

    public void setDto(UserDTO dto) {
        this.dto = dto;
    }

}
