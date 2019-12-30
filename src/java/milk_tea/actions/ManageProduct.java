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
import milk_tea.dtos.ProductDTO;
import milk_tea.dtos.SizeDTO;
import mlik_tea.daos.CategoryDAO;
import mlik_tea.daos.ProductDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class ManageProduct extends ActionSupport {

    private final String LIST = "list";
    private final String ADD = "add";
    public final String EDIT = "edit";
    public String status;

    private HashMap<Integer, String> sizeList;
    private HashMap<Integer, String> categoryList;

    private List<ProductDTO> listDTO;
    private ProductDTO dto;
    String productID;

    String action;

    String page;
    double pageSize;

    public ManageProduct() {
    }

    @Action(value = "/manage-product",
            results = {
                @Result(name = SUCCESS, location = "/manage_product.jsp")
                ,
                                @Result(name = INPUT, location = "/manage_product.jsp")
                ,
                                @Result(name = "failed", location = "failed.jsp")

            }
    )
    public String execute() throws Exception {
        if (action == null) {
            action = LIST;
        }

        if (action.equals(LIST)) {
            listDTO = getProduct();
        } else if (action.equals(ADD)) {
            CategoryDAO dao = new CategoryDAO();
            ProductDAO pdao = new ProductDAO();
            sizeList = pdao.getSize();
            categoryList = dao.getCategory();
        } else if (action.equals(EDIT)) {
            CategoryDAO cdao = new CategoryDAO();
            categoryList = cdao.getCategory();
            ProductDAO pdao = new ProductDAO();
            sizeList = pdao.getSize();
            int productID = Integer.parseInt(this.productID);
            dto = pdao.getAllPropertyOfProduct(productID);
            HashMap<Integer, String> pSizeList = pdao.getSizeByProductID(productID);
            dto.setSizeList(pSizeList);
        }

        return SUCCESS;
    }

    public List<ProductDTO> getProduct() throws Exception {
        ProductDAO dao = new ProductDAO();
        pageSize = Math.ceil(dao.getAmountOfProducts() / 20f);
        if (page == null) {
            page = "1";
        }
        int page = Integer.parseInt(this.page);
        if (page < 1) {
            page = 1;
        }
        int begin = page * 20 - 20;
        int end = page * 20;
        List<ProductDTO> list;
        if(status == null)
        {
            status = "true";
        }
        list = dao.getAllPropertyOfProductFromBeginToEnd(1, 20, status);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSizeList(dao.getSizeByProductID(list.get(i).getProductID()));
            }
        }
        return list;
    }

    public HashMap<Integer, String> getSizeList() {
        return sizeList;
    }

    public void setSizeList(HashMap<Integer, String> sizeList) {
        this.sizeList = sizeList;
    }

    public HashMap<Integer, String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(HashMap<Integer, String> categoryList) {
        this.categoryList = categoryList;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<ProductDTO> getListDTO() {
        return listDTO;
    }

    public void setListDTO(List<ProductDTO> listDTO) {
        this.listDTO = listDTO;
    }

    public ProductDTO getDto() {
        return dto;
    }

    public void setDto(ProductDTO dto) {
        this.dto = dto;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public double getPageSize() {
        return pageSize;
    }

    public void setPageSize(double pageSize) {
        this.pageSize = pageSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
