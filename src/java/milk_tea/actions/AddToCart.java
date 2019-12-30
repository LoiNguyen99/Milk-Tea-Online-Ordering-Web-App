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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import milk_tea.dtos.CartDTO;
import milk_tea.dtos.CartItemDTO;
import milk_tea.dtos.ProductDTO;
import milk_tea.dtos.ToppingDTO;
import milk_tea.dtos.SizeDTO;
import milk_tea.dtos.UserDTO;
import mlik_tea.daos.ProductDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class AddToCart extends ActionSupport {

    public String price;
    List<String> checkedToppingList;
    String sizeRadio;
    String productID;
    String quantity;
    String productName;
    String imagePath;

    public AddToCart() {
    }

    @Action(value = "/add", results = {
        @Result(name = SUCCESS, location = "/cart.jsp")
        ,
        @Result(name = "failed", location = "/error.jsp")
    })
    public String execute() throws Exception {
        float price = Float.parseFloat(this.price);
        int productID = Integer.parseInt(this.productID.trim());
        int sizeRadio = Integer.parseInt(this.sizeRadio);
        int quantity = Integer.parseInt(this.quantity);
        System.out.println(quantity);
        List<ToppingDTO> checkedToppingList = new ArrayList<>();
        ProductDAO dao = new ProductDAO();
        for (int i = 0; i < this.checkedToppingList.size(); i++) {
            int toppingID = Integer.parseInt(this.checkedToppingList.get(i));
            ToppingDTO dto = dao.getTopingByID(toppingID);
            if (dto != null) {
                checkedToppingList.add(dto);
                price = price + dto.getPrice();
            }
        }
        SizeDTO size = dao.getSizeByID(sizeRadio);

        CartItemDTO dto = new CartItemDTO(productID, productName, price, checkedToppingList, size, imagePath);
        Map session = ActionContext.getContext().getSession();
        UserDTO userdto = (UserDTO) session.get("USERINFO");
        String userid;
        if (userdto == null) {
            userid = "Guest";
        } else {
            userid = userdto.getUsername();
        }
        CartDTO cart = (CartDTO) session.get("CART");

        if (cart == null) {
            cart = new CartDTO(userid);
        }

        cart.addItem(dto, quantity);

        session.put("CART", cart);

        return SUCCESS;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getCheckedToppingList() {
        return checkedToppingList;
    }

    public void setCheckedToppingList(List<String> checkedToppingList) {
        this.checkedToppingList = checkedToppingList;
    }

    public String getSizeRadio() {
        return sizeRadio;
    }

    public void setSizeRadio(String sizeRadio) {
        this.sizeRadio = sizeRadio;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
