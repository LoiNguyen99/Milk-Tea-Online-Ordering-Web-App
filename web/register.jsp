<%-- 
    Document   : Register
    Created on : Aug 18, 2019, 9:41:53 PM
    Author     : Loi Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <s:include value="header.jsp"/>
        <div id ="inner-register-body">
            <div class="form-body">
                <div class="section-title">
                    <a href="login.jsp">
                        ĐĂNG NHẬP
                    </a>
                    </font>
                     / ĐĂNG KÝ
                </div>    
                <div class="form-center">
                    <s:form action="register" method="POST" enctype="UTF-8">
                        <s:textfield name="username" label="Tên tài khoản"/>
                        <s:password name="password" label="Mật khẩu"/>
                        <s:password name="confirm" label="Xác nhận mật khẩu"/>
                        <s:textfield name="fullname" label="Họ và Tên"/>
                        <s:textfield name="phoneNo" label="Số điện thoại"/>
                        <s:textfield name="address" label="Địa chỉ giao hàng"/>
                        <s:submit cssClass="basic" value="Đăng ký" name="action"/>
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
