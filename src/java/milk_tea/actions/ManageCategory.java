/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import milk_tea.dtos.CategoryDTO;
import mlik_tea.daos.CategoryDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class ManageCategory extends ActionSupport {

    private final String LIST = "list";
    private final String ADD = "add";
    public final String EDIT = "edit";

    private String status;
    private List<CategoryDTO> list;
    private CategoryDTO dto;
    private String action;
    private String page;
    private double pageSize;
    private String categoryID;

    public ManageCategory() {
    }

    
    @Action(value = "/manage-category",
            results = {
                @Result(name = SUCCESS, location = "/manage_category.jsp")
                ,
                                @Result(name = INPUT, location = "/manage_category.jsp")
                ,
                                @Result(name = "failed", location = "failed.jsp")

            }
    )
    public String execute() throws Exception {
        if (action == null) {
            action = LIST;
        }

        if (action.equals(LIST)) {
            CategoryDAO dao = new CategoryDAO();
            pageSize = Math.ceil(dao.getAmountOfCategory() / 20f);
            if (page == null) {
                page = "1";
            }
            int page = Integer.parseInt(this.page);
            if (page < 1) {
                page = 1;
            }
            if(status == null)
            {
                status= "true";
            }
            int begin = page * 20 - 20;
            int end = page * 20;
            list = dao.getCategoryFromBeginToEnd(begin, end, status);
            if (list != null) {
                return SUCCESS;
            }
        }
        else if(action.equals(ADD))
        {
            return SUCCESS;
        }
        else if(action.equals(EDIT))
        {
            CategoryDAO dao = new CategoryDAO();
            int categoryID = Integer.parseInt(this.categoryID);
            dto = dao.getCategoryByID(categoryID);
            if(dto != null)
            {
                return SUCCESS;
            }
        }

        return "fail";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CategoryDTO> getList() {
        return list;
    }

    public void setList(List<CategoryDTO> list) {
        this.list = list;
    }

    public CategoryDTO getDto() {
        return dto;
    }

    public void setDto(CategoryDTO dto) {
        this.dto = dto;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

}
