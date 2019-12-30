/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import milk_tea.dtos.CartDTO;
import milk_tea.dtos.CartItemDTO;
import milk_tea.dtos.SizeDTO;
import milk_tea.dtos.ToppingDTO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class UpdateCart extends ActionSupport {

    private final String FAIL = "fail";
    List<String> quantityList;

    public UpdateCart() {

    }

    @Action(value = "/update-cart", results = {
        @Result(name = SUCCESS, location = "cart.jsp", type = "redirect")
        ,
        @Result(name = "fail", location = "error.jsp")
    })
    public String execute() throws Exception {

        Map session = ActionContext.getContext().getSession();

        CartDTO cart = (CartDTO) session.get("CART");
        if (!cart.getItems().isEmpty()) {
            int count = 0;
            for (CartItemDTO cartItemDTO : cart.getItems().keySet()) {
                int quantity = Integer.parseInt(quantityList.get(count).trim());
                cart.getItems().put(cartItemDTO, quantity);
                count++;
            }
        }else{
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERROR", "Cập nhật không thành công vì giỏ hàng trống!");
            return FAIL;
        }
        return SUCCESS;

    }

    public List<String> getQuantityList() {
        return quantityList;
    }

    public void setQuantityList(List<String> quantityList) {
        this.quantityList = quantityList;
    }

}
