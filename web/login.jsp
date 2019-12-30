<%-- 
    Document   : login
    Created on : Aug 19, 2019, 10:48:56 AM
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
        <s:include value="header.jsp"/>
        <div id ="inner-login-body">
            <div class="form-body">
                <div class="section-title">
                    ĐĂNG NHẬP / 
                    <font color ="#969696">
                    <a href="register.jsp">
                        ĐĂNG KÝ
                    </a>
                    </font>
                </div>  
                
                <div class="form-center">
                    <s:form action="login" method="POST" enctype="UTF-8">
                        <s:textfield name="username" label="Tên tài khoản"/>
                        <s:password name="password" label="Mật khẩu"/>
                        <s:submit cssClass="basic" value="Đăng Nhập" name="action"/>
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
