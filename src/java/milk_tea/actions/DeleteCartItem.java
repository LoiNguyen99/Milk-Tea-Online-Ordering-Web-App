/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import milk_tea.dtos.CartDTO;
import milk_tea.dtos.CartItemDTO;
import milk_tea.dtos.ToppingDTO;
import milk_tea.dtos.SizeDTO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class DeleteCartItem extends ActionSupport {

    List<String> toppingIDList;
    String price;
    String productID;
    String sizeID;
    String quantity;
    String action;

    public DeleteCartItem() {
    }

    @Action(value = "/delete", results = {
        @Result(name = SUCCESS, location = "/cart.jsp", type = "redirect")
        ,
        @Result(name = "failed", location = "/error.jsp")
    })
    public String execute() throws Exception {
        List<ToppingDTO> toppigList = new ArrayList<>();
        if (toppingIDList.size() == 1) {
            if (toppingIDList.get(0).equals("null")) {
                ToppingDTO toppingDTO = null;
            } else {
                int id = Integer.parseInt(toppingIDList.get(0));
                ToppingDTO toppingDTO = new ToppingDTO(id, "", 0);
                toppigList.add(toppingDTO);
            }
        } else {
            for (String toppingID : toppingIDList) {
                int id = Integer.parseInt(toppingID);
                ToppingDTO toppingDTO = new ToppingDTO(id, "", 0);
                toppigList.add(toppingDTO);
            }
        }
        SizeDTO sizeDTO = new SizeDTO(Integer.parseInt(sizeID), "");
        float price = Float.parseFloat(this.price);
        int productID = Integer.parseInt(this.productID);
        CartItemDTO cartItemDTO = new CartItemDTO(productID, "", price, toppigList, sizeDTO, "");

        Map session = ActionContext.getContext().getSession();
        CartDTO cartDTO = (CartDTO) session.get("CART");
        if (action.equals("delete")) {
            if (cartDTO != null) {
                if (cartDTO.getItems().containsKey(cartItemDTO)) {
                    cartDTO.getItems().remove(cartItemDTO);
                    session.put("CART", cartDTO);
                } else {
                    HttpServletRequest request = ServletActionContext.getRequest();
                    request.setAttribute("ERROR", "Sản phẩm không tồn tại trong giỏ hàng!");
                    return "failed";
                }
            }
        } else if (action.equals("update")) {
            if (cartDTO != null) {
                if (cartDTO.getItems().containsKey(cartItemDTO)) {
                    int quantity = Integer.parseInt(this.quantity);
                    cartDTO.getItems().put(cartItemDTO, quantity);
                } else {
                    HttpServletRequest request = ServletActionContext.getRequest();
                    request.setAttribute("ERROR", "Sản phẩm không tồn tại trong giỏ hàng!");
                    return "failed";
                }
            }
        }

        return SUCCESS;
    }

    public List<String> getToppingIDList() {
        return toppingIDList;
    }

    public void setToppingIDList(List<String> toppingIDList) {

        this.toppingIDList = toppingIDList;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getSizeID() {
        return sizeID;
    }

    public void setSizeID(String sizeID) {
        this.sizeID = sizeID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
