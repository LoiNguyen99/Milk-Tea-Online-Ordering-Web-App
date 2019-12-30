/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import milk_tea.dtos.CartDTO;
import milk_tea.dtos.CartItemDTO;
import milk_tea.dtos.ToppingDTO;
import mlik_tea.daos.ProductDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class InsertCart extends ActionSupport {

    String address;
    String phoneNo;

    public InsertCart() throws Exception {

        

    }

    @Action(value = "insert-cart", results = {
        @Result(name = SUCCESS, location = "/success.jsp", type = "redirect")
    })
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CartDTO cart = (CartDTO) session.get("CART");
        ProductDAO dao = new ProductDAO();
        String username = cart.getUserID();
        if (dao.insertCartInfo(username, address, phoneNo)) {
            int cartId = dao.getCurrentCartID();
            HashMap<CartItemDTO, Integer> cartInfo = cart.getItems();
            for (CartItemDTO dto : cartInfo.keySet()) {
                if (dao.insertCartDetail(dto, cartInfo.get(dto), cartId)) {
                    int toppingListID = dao.getCurrentToppingListID();
                    for (ToppingDTO topping : dto.getCheckedToppingList()) {
                        dao.insertCartTopping(topping.getToppingID(), toppingListID);       
                    }
                }
            }
        }
        session.remove("CART");
        return SUCCESS;
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
