<%-- 
    Document   : all_product
    Created on : Aug 23, 2019, 11:57:52 PM
    Author     : Loi Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Management</title>
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
            <div class="menu-title-selected"><i class="fas fa-glass-martini"></i> Manage Product</div>
            <div class="menu-title"><s:a href="%{categoryLink}"><i class="fas fa-list-alt"></i> Manage Category</s:a></div>
            <div class="menu-title"><s:a href="%{sizeLink}"><i class="fas fa-circle"></i> Manage size</s:a></div>
            <div class="menu-title"><s:a href="%{toppingLink}"><i class="fas fa-lemon"></i> Manage Topping</s:a></div>
            <div class="menu-title"><s:a href="%{userLink}"><i class="fas fa-user"></i> Manage User</s:a></div>
        </div>
        <div class="float-right">
            <div id="tab-bar">
                <s:url action="manage-product" var="list">
                    <s:param name="action" value="'list'"/>
                </s:url>
                <s:url action="manage-product" var="add">
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
                    <s:form action="manage-product" theme="simple" method="GET">
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
                            <th></th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Size</th>
                            <th>Create Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="listDTO" status="counter" >
                            <tr>
                                <td class="image">
                                    <img src="<s:property value="imagePath"/>" width="50px" height="50px"/>
                                </td>
                                <td>
                                    <s:property value="productID"/>
                                </td>
                                <td> <s:property value="productName"/></td>
                                <td> <s:property value="price"/></td>
                                <td> <s:property value="categoryName"/></td>
                                <td>
                                    <s:iterator value="sizeList" var="size" status="counter">
                                        <s:property value="#size.value"/> 
                                    </s:iterator>
                                </td>
                                <td> <s:property value="createDate"/></td>
                                <td>
                                    <div class="submit-edit" onclick="submitEdit(<s:property value="%{#counter.count-1}"/>)">
                                        <i class="far fa-edit"></i>
                                    </div>
                                    <div class="submit-delete" onclick="submitDetele(<s:property value="%{#counter.count-1}"/>)">    
                                        <i class="fa fa-trash-alt" aria-hidden="true" style="color:#ff6f69"></i>
                                    </div>
                                    <s:form action="manage-product" method="POST">
                                        <s:hidden name="productID" value="%{productID}"/>
                                        <s:hidden name="action" value="edit"/>
                                        <s:submit cssClass="btnEdit" value="edit" name="submitAction" cssStyle="display: none;"/>
                                    </s:form>
                                    <s:form action="product-action" method="POST">
                                        <s:hidden name="productID" value="%{productID}"/>
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
                    <s:form action="insert-product" method="POST">
                        <s:textfield name="productName" label="Tên Sản Phẩm"/>
                        <s:textfield name="price" label="Giá" />
                        <s:select name="category" label="Danh Mục" list="categoryList" value="2"/>
                        <s:file name="imageFile" label="Ảnh mô tả"/>
                        <s:checkboxlist name="checkedSizeList" label="Size" list="sizeList"/>
                        <s:submit name="action" value="Thêm" cssClass="basic"/>
                    </s:form>    
                </div>
            </s:elseif>
            <s:elseif test="%{action == 'edit'}">
                <div class="checkbox">
                    <s:form action="product-action" method="POST">
                        <s:textfield name="productName" label="Tên Sản Phẩm" value="%{dto.productName}"/>
                        <s:textfield name="price" label="Giá" value="%{dto.price}"/>
                        <s:select name="category" label="Danh Mục" list="categoryList" value="dto.categoryID"/>
                        <s:file name="imageFile" label="Ảnh mô tả"/>
                        <s:checkboxlist name="checkedSizeList" label="Size" list="sizeList" value="dto.sizeList.keys"/>
                        <s:select name="status" label="Trạng thái" value="%{dto.status}" list="{'True','False'}"/>
                        <s:hidden name="productID" value="%{dto.productID}"/>
                        <s:submit name="submitAction" value="Thêm" cssClass="basic"/>
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
            var status = "<s:property value="dto.status"/>";
            if (status == 0)
            {
                document.getElementsByTagName("option")[1].checked;
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
                var category = "<s:property value="category"/>";
                var pagination = document.getElementById("pageLink");
                for (var i = 1; i <= pageSize; i++) {
                    if (i == 1)
                    {
                        var link = document.createElement("A");
                        var dot = document.createElement("A");
                        link.setAttribute("href", "?category=" + category + "&page=" + i);
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
                        link.setAttribute("href", "?category=" + category + "&page=" + i);
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
                        link.setAttribute("href", "?category=" + category + "&page=" + i);
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
