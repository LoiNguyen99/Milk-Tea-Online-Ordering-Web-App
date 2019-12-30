<%-- 
    Document   : header
    Created on : Aug 10, 2019, 3:37:15 PM
    Author     : Loi Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/style.css" rel="stylesheet" type="text/css"> 
        <link href="resources/css/fontawesome/css/all.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="header-menu">
            <h1 id="logo">
                <s:url action="home" id="homeLink">
                </s:url>
                <s:url action="manage-product" id="manageLink">
                </s:url>
                <s:a href="%{homeLink}">BOBAPOP</s:a>
                </h1>
                <ul>
                <s:url action="product" id="productLink">
                    <s:param name="category" value="'all'"/>
                </s:url>
                <li><s:a href="%{productLink}"> Menu </s:a> </li>
                    <li> About us </li>
                    <s:if test="%{#session.USERINFO.roleID == 1}">
                    <li>
                        <s:a href="%{manageLink}"> Quản Lý </s:a> </li>
                    </s:if>
            </ul>
            <!--            <div class="search-container">
                            <form>
                                <input type="text" id="search" name="i1"/>
                                <input type="submit" id="search" value="Search"/>
                            </form>
                        </div>-->
            <div class="heart-link">
                <div class="circle">
                    <a href="search.jsp" style="color: white;">
                        <i class="fas fa-search"></i>
                    </a>
                </div>
            </div>
            <div class="heart-link">
                <div class="circle">
                    <i class="fas fa-heart"></i>
                </div>
            </div>
            <div class="cart-link">
                <div class="circle">
                    <a href="cart.jsp">
                        <i class="fa fa-shopping-basket"></i>
                    </a>
                </div>
            </div>
            <s:if test="%{#session.USERINFO != null}">

                <div class="user" onclick="callProfile()">
                    <s:url action="profile" var="profileLink">
                    </s:url>
                    <div class="circle">
                        <s:a href="%{profileLink}">
                            <i class="fas fa-user" style="font-size: 24px"></i>
                        </s:a>
                    </div>
                    <div class="username">
                        <s:a id="profile" href="%{profileLink}">
                            <s:property value="#session.USERINFO.username"/>
                        </s:a>
                    </div>
                </div>
            </s:if>
            <s:else>
                <div class="user">
                    <div class="circle">
                        <a href="login.jsp">
                            <i class="fas fa-user" style="font-size: 24px"></i>
                        </a>
                    </div>
                </div>
            </s:else>
        </div>
        <script>
            function callProfile() {
                var usernameClass = document.getElementById("profile");
                if (usernameClass != null)
                {
                    usernameClass.click();
                }
            }
        </script>
    </body>
</html>

