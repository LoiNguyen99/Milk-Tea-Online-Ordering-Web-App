/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.List;
import javax.servlet.ServletContext;
import milk_tea.dtos.ProductDTO;
import mlik_tea.daos.ProductDAO;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.util.ServletContextAware;

/**
 *
 * @author Loi Nguyen
 */
public class InsertProduct extends ActionSupport implements ServletContextAware {

    private final String LOAD = "load";
    private final String RESOURCES_PATH = "/resources/product_images";
    private final String IMAGE_EXTENSION = ".jpg";

    private ProductDTO dto;
    private List<String> checkedSizeList;
    private String productName;
    private String price;
    private String category;
    private String action;

    private String imageFile;
    private String imageFileContentType, imageFileFileName;
    private ServletContext context;

    public String getImageFileContentType() {
        return imageFileContentType;
    }

    public void setImageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
    }

    public String getImageFileFileName() {
        return imageFileFileName;
    }

    public void setImageFileFileName(String imageFileFileName) {
        this.imageFileFileName = imageFileFileName;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public List<String> getCheckedSizeList() {
        return checkedSizeList;
    }

    public void setCheckedSizeList(List<String> checkedSizeList) {
        this.checkedSizeList = checkedSizeList;
    }

    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ProductDTO getDto() {
        return dto;
    }

    public void setDto(ProductDTO dto) {
        this.dto = dto;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public InsertProduct() {
    }

    @Action(value = "/insert-product",
            results = {
                @Result(name = SUCCESS, location = "/manage-product?action=add",type = "redirect")
                ,
                @Result(name = LOAD, location = "/insert_product.jsp")
                ,
                                @Result(name = INPUT, location = "erorr.jsp")
                ,
                                @Result(name = "failed", location = "failed.jsp")

            }
    )
    public String execute() throws Exception {
        if (insert()) {
            return SUCCESS;
        } else {
            return "failed";
        }
    }

    public boolean insert() throws Exception {
        ProductDAO dao = new ProductDAO();
        String path = context.getRealPath(RESOURCES_PATH);
        int count = dao.getProductCount();
        String filename = count + 1 + IMAGE_EXTENSION;
        File file = new File(path, filename);
        FileUtils.copyFile(new File(imageFile), file);

        float price = Float.parseFloat(this.price);
        int CategoryID = Integer.parseInt(this.category);
        String imagePath = RESOURCES_PATH.substring(1) + "/" + filename;
        ProductDTO dto = new ProductDTO(productName, price, imagePath, CategoryID);

        if (dao.insertProduct(dto)) {
            for (int i = 0; i < checkedSizeList.size(); i++) {
                int sizeID = Integer.parseInt(checkedSizeList.get(i).toString());
                dao.insertProductSize(count + 1, sizeID);
            }
            return true;
        }

        return false;

    }

    @Override
    public void setServletContext(ServletContext sc) {
        context = sc;
    }

}
