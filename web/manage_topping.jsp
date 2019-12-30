<%-- 
    Document   : manage_topping
    Created on : Aug 26, 2019, 6:54:58 PM
    Author     : Loi Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <div class="float-left">
            <s:url action="manage-product" var="productLink">
            </s:url>
            <s:url action="manage-category" var="categoryLink">
            </s:url>
            <s:url action="manage-size" var="sizeLink">
            </s:url>
            <s:url action="manage-topping" var="toppingLink">
            </s:url>
            <s:url action="manage-user" var="userLink">
            </s:url>
            <div class="menu-title"><s:a href="%{productLink}"><i class="fas fa-glass-martini"></i> Manage Product</s:a></div>
            <div class="menu-title"><s:a href="%{categoryLink}"><i class="fas fa-list-alt"></i> Manage Category</s:a></div>
            <div class="menu-title"><s:a href="%{sizeLink}"><i class="fas fa-circle"></i> Manage size</s:a></div>
            <div class="menu-title-selected"><i class="fas fa-lemon"></i> Manage Topping</div>
            <div class="menu-title"><s:a href="%{userLink}"><i class="fas fa-user"></i> Manage User</s:a></div>
        </div>
        <div class="float-right">
            <div id="tab-bar">
                <s:url action="manage-topping" var="list">
                    <s:param name="action" value="'list'"/>
                </s:url>
                <s:url action="manage-topping" var="add">
                    <s:param name="action" value="'add'"/>
                </s:url>
                <div class="tab">
                    <s:a href="%{list}">
                        Danh sách
                    </s:a>
                </div>
                <div class="tab">
                    <s:a href="%{add}">
                        Thêm sản phẩm
                    </s:a>
                </div>
                <div class="tab">Edit</div>
                <div class="tab">
                    <s:form action="manage-topping" theme="simple" method="GET">
                        <s:select name="status" label="Status" list="{'True', 'False'}" onchange="submitStatus()"/>
                        <s:hidden name="action" value="list"/>
                        <s:submit cssClass="btnStatus" value="submit" cssStyle="display:none;"/>
                    </s:form>
                </div>
            </div>
            <s:if test="%{action == 'list'}">
                <table cellspacing="0" class="manager-table">
                    <thead>
                        <tr class="heading">
                            <th>Code</th>
                            <th>Name</th>
                            <th>Giá</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="list" status="counter" >
                            <tr>

                                <td>
                                    <s:property value="toppingID"/>
                                </td>
                                <td> 
                                    <s:property value="toppingName"/>
                                </td>
                                <td> 
                                    <s:property value="price"/>
                                </td>
                                <td>
                                    <div class="submit-edit" onclick="submitEdit(<s:property value="%{#counter.count-1}"/>)">
                                        <i class="far fa-edit"></i>
                                    </div>
                                    <div class="submit-delete" onclick="submitDetele(<s:property value="%{#counter.count-1}"/>)">    
                                        <i class="fa fa-trash-alt" aria-hidden="true" style="color:#ff6f69"></i>
                                    </div>
                                    <s:form action="manage-topping" method="POST">
                                        <s:hidden name="toppingID" value="%{toppingID}"/>
                                        <s:hidden name="action" value="edit"/>
                                        <s:submit cssClass="btnEdit" value="edit" name="submitAction" cssStyle="display: none;"/>
                                    </s:form>
                                    <s:form action="topping-action" method="POST">
                                        <s:hidden name="toppingID" value="%{toppingID}"/>
                                        <s:submit cssClass="btnDelete" value="delete" name="submitAction" cssStyle="display: none;"/>
                                    </s:form>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <div class="pagination" id="pageLink"></div>
            </s:if>
            <s:elseif test="%{action == 'add'}">
                <div class="checkbox">
                    <s:form action="topping-action" method="POST">
                        <s:textfield name="toppingName" label="Tên Danh mục"/>
                        <s:textfield name="price" label="Giá"/>
                        <s:hidden name="action" value="insert"/>
                        <s:submit value="Thêm" cssClass="basic"/>
                    </s:form>    
                </div>
            </s:elseif>
            <s:elseif test="%{action == 'edit'}">
                <div class="checkbox">
                    <s:form action="topping-action" method="POST">
                        <s:textfield name="toppingName" label="Tên Topping" value="%{dto.toppingName}"/>
                        <s:textfield name="price" label="Giá" value="%{dto.price}"/>
                        <s:select name="status" label="Trạng thái" value="%{dto.status}" list="{'True','False'}"/>
                        <s:hidden name="toppingID" value="%{dto.toppingID}"/>
                        <s:hidden name="action" value="edit"/>
                        <s:submit value="Cập nhật" cssClass="basic"/>
                    </s:form>    
                </div>
            </s:elseif>
        </div>

        <script>
            var page = "<s:property value="page"/>";
            if (page == "")
            {
                page = 1;
            }
            pageSize = <s:property value="pageSize"/>;
            if (page > pageSize)
            {
                page = pageSize;
            } else if (page <= 0)
            {
                page = 1;
            }
            
            var action = "<s:property value="action"/>";
            var tabClass = document.getElementsByClassName("tab");
            if (action == null || action == "list")
            {
                tabClass[0].classList.add("tabbed");
            } else if (action == "add")
            {
                tabClass[1].classList.add("tabbed");
            } else if (action == "edit")
            {
                tabClass[2].classList.add("tabbed");
            }

            function submitDetele(pos)
            {
                var btndelete = document.getElementsByClassName("btnDelete")[pos];
                btndelete.click();
            }

            function submitEdit(pos)
            {
                var btnEdit = document.getElementsByClassName("btnEdit")[pos];
                btnEdit.click();
            }
            function setPageLink(pageSize)
            {
                var topping = "<s:property value="topping"/>";
                var pagination = document.getElementById("pageLink");
                for (var i = 1; i <= pageSize; i++) {
                    if (i == 1)
                    {
                        var link = document.createElement("A");
                        var dot = document.createElement("A");
                        link.setAttribute("href", "?topping=" + topping + "&page=" + i);
                        link.setAttribute("class", "paging");
                        dot.setAttribute("href", "#");
                        dot.setAttribute("class", "pagingDot");
                        var textnode = document.createTextNode(i);
                        link.appendChild(textnode);
                        var textnode2 = document.createTextNode("...");
                        dot.appendChild(textnode2);
                        pagination.appendChild(link);
                        pagination.appendChild(dot);
                    } else if (i == pageSize)
                    {
                        var link = document.createElement("A");
                        var dot = document.createElement("A");
                        link.setAttribute("href", "?topping=" + topping + "&page=" + i);
                        link.setAttribute("class", "paging");
                        dot.setAttribute("href", "#");
                        dot.setAttribute("class", "pagingDot");
                        var textnode = document.createTextNode(i);
                        link.appendChild(textnode);
                        var textnode2 = document.createTextNode("...");
                        dot.appendChild(textnode2);
                        pagination.appendChild(dot);
                        pagination.appendChild(link);
                    } else
                    {
                        var link = document.createElement("A");
                        link.setAttribute("href", "?topping=" + topping + "&page=" + i);
                        link.setAttribute("class", "paging");
                        var textnode = document.createTextNode(i);
                        link.appendChild(textnode);
                        pagination.appendChild(link);
                    }
                }
            }



            function pagination(pageSize)
            {
                var pageProperty = document.getElementsByClassName("paging");
                var pageDot = document.getElementsByClassName("pagingDot");
                pageProperty[page - 1].classList.add("active");

                if (pageSize < 8)
                {
                    pageDot[0].style.display = 'none';
                    pageDot[1].style.display = 'none';
                } else
                {
                    if (page <= 4)
                    {
                        pageDot[0].style.display = "none";
                        for (var i = 0; i < pageSize; i++) {
                            if (i > 5 && i != pageSize - 1)
                            {
                                pageProperty[i].style.display = 'none';
                            }
                        }
                    } else if (page > 4 && page < pageSize - 3)
                    {
                        tmp = page - 1;
                        for (var i = 0; i < pageSize; i++) {
                            if (i >= tmp - 2 && i <= tmp + 2)
                            {
                            } else
                            {
                                if (i != 0 && i != pageSize - 1)
                                {
                                    pageProperty[i].style.display = 'none';
                                }
                            }
                        }
                    } else if (page >= pageSize - 3 && page <= pageSize)
                    {
                        pageDot[1].style.display = "none";
                        for (var i = 0; i < pageSize; i++) {
                            if (i < pageSize - 6 && i != 0)
                            {
                                pageProperty[i].style.display = 'none';
                            }
                        }
                    }
                }
            }
            function submitStatus()
            {
                var btnSubmit = document.getElementsByClassName("btnStatus")[0];
                btnSubmit.click();
            }
            setPageLink(pageSize);
            pagination(pageSize);
        </script>
    </body>
</html>
