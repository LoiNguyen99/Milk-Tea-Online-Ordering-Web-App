<%-- 
    Document   : address
    Created on : Aug 26, 2019, 10:22:16 PM
    Author     : Loi Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin giao hàng</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <div class="section-title">Thông tin giao hàng</div>
        <s:form action="insert-cart" method="POST">
            <s:textfield name="phoneNo" value="%{dto.phoneNo}" label="Số điện thoại"/>
            <s:textfield name="address" value="%{dto.address}" label="Địa chỉ"/>
            <s:submit value="Đặt Hàng" cssClass="basic"/>
        </s:form>
    </body>
</html>
