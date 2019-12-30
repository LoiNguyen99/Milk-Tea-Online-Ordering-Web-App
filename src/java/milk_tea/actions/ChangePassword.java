/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import mlik_tea.daos.UserDAO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class ChangePassword extends ActionSupport {

    private final String FAIL = "fail";
    private final String WRONG_PASS = "wrong_pass";
    String password;
    String newPassword;
    String username;

    public ChangePassword() {
    }

    @Action(value = "/change-password", results = {
        @Result(name = SUCCESS, location = "/profile.jsp")
        ,
        @Result(name = FAIL, location = "/error.jsp")
    })
    public String execute() throws Exception {
        UserDAO dao = new UserDAO();
        String password = dao.getPassowrd(username);
        if (!password.equals(this.password)) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("MESS", "Sai mật khẩu!");
            return SUCCESS;
        } else {
            if (dao.updatePassword(username, newPassword)) {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("MESS", "Đổi mật khẩu thành công!");
                return SUCCESS;
            } else {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("ERROR", "Đổi mật khẩu không thành công!");
                return FAIL;
            }
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
