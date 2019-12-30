<%-- 
    Document   : insert_product
    Created on : Aug 10, 2019, 12:43:51 PM
    Author     : Loi Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/> 
        <div class="section-title">QUẢN LÝ</div>
        <div class="float-left">
        </div>
        <div class="float-right">
            <div id="tab-bar">
                <div class="tab">Product list</div>
                <div class="tab tabbed">Thêm sản phẩm</div>
                <div class="tab">Edit</div>
            </div>
            <div class="checkbox"/>
            <s:form action="insert-product" method="POST" enctype="UTF-8">
                    <s:textfield name="productName" label="Tên Sản Phẩm"/>
                    <s:textfield name="price" label="Giá" />
                    <s:select name="category" label="Danh Mục" list="categoryList"/>
                    <s:file name="imageFile" label="Ảnh mô tả"/>
                    <s:checkboxlist name="checkedSizeList" label="Size" list="sizeList"/>
                    <s:submit name="action" value="Thêm" cssClass="basic"/>
                </s:form>    
        </div>
    </div>
</body>
</html>
