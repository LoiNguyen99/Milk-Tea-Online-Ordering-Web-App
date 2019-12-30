/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milk_tea.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import milk_tea.dtos.SizeDTO;
import mlik_tea.daos.ProductDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class ManageSize extends ActionSupport {
    
    private final String LIST = "list";
    private final String ADD = "add";
    public final String EDIT = "edit";

    private String status;
    private List<SizeDTO> list;
    private SizeDTO dto;
    private String action;
    private String page;
    private double pageSize;
    private String sizeID;

    public ManageSize() {
    }

    
    @Action(value = "/manage-size",
            results = {
                @Result(name = SUCCESS, location = "/manage_size.jsp")
                ,
                                @Result(name = INPUT, location = "/manage_size.jsp")
                ,
                                @Result(name = "failed", location = "failed.jsp")

            }
    )
    public String execute() throws Exception {
        if (action == null) {
            action = LIST;
        }

        if (action.equals(LIST)) {
            ProductDAO dao = new ProductDAO();
            pageSize = Math.ceil(dao.getAmountOfSize() / 20f);
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
            list = dao.getSizeFromBeginToEnd(begin, end, status);
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
            ProductDAO dao = new ProductDAO();
            int sizeID = Integer.parseInt(this.sizeID);
            dto = dao.getAllPropertyOfSizeByID(sizeID);
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

    public List<SizeDTO> getList() {
        return list;
    }

    public void setList(List<SizeDTO> list) {
        this.list = list;
    }

    public SizeDTO getDto() {
        return dto;
    }

    public void setDto(SizeDTO dto) {
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

    public String getSizeID() {
        return sizeID;
    }

    public void setSizeID(String sizeID) {
        this.sizeID = sizeID;
    }
    
}
