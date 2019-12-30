package milk_tea.actions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import milk_tea.dtos.ToppingDTO;
import mlik_tea.daos.ToppingDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Loi Nguyen
 */
public class ManageTopping extends ActionSupport {
    private final String LIST = "list";
    private final String ADD = "add";
    public final String EDIT = "edit";

    private String status;
    private List<ToppingDTO> list;
    private ToppingDTO dto;
    private String action;
    private String page;
    private double pageSize;
    private String toppingID;
    public ManageTopping() {
        
    }
    
    @Action(value = "/manage-topping",
            results = {
                @Result(name = SUCCESS, location = "/manage_topping.jsp")
                ,
                                @Result(name = INPUT, location = "/manage_topping.jsp")
                ,
                                @Result(name = "failed", location = "failed.jsp")

            }
    )
    public String execute() throws Exception {
        if (action == null) {
            action = LIST;
        }

        if (action.equals(LIST)) {
            ToppingDAO dao = new ToppingDAO();
            pageSize = Math.ceil(dao.getAmountOfTopping() / 20f);
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
            list = dao.getToppingFromBeginToEnd(begin, end, status);
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
            ToppingDAO dao = new ToppingDAO();
            int toppingID = Integer.parseInt(this.toppingID);
            dto = dao.getToppingByID(toppingID);
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

    public List<ToppingDTO> getList() {
        return list;
    }

    public void setList(List<ToppingDTO> list) {
        this.list = list;
    }

    public ToppingDTO getDto() {
        return dto;
    }

    public void setDto(ToppingDTO dto) {
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

    public String getToppingID() {
        return toppingID;
    }

    public void setToppingID(String toppingID) {
        this.toppingID = toppingID;
    }
    
}
