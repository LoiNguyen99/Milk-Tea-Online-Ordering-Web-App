<%-- 
    Document   : products
    Created on : Aug 16, 2019, 12:27:48 AM
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
        <div class="acb">
            <s:url action="product" id="allLink">
                <s:param name="category" value="'all'"/>
            </s:url>
            <s:url action="product" id="trasua">
                <s:param name="category" value="'tra-sua'"/>
            </s:url>
            <s:url action="product" id="trasuibot">
                <s:param name="category" value="'tra-sui-bot'"/>
            </s:url>
            <s:url action="product" id="tratuoi">
                <s:param name="category" value="'tra-tuoi'"/>
            </s:url>
            <s:a cssClass="circle-menu" href="%{allLink}">
                Tất cả
            </s:a>

            <s:a cssClass="circle-menu" href="%{trasua}" theme="simple">Trà sữa</s:a>
            <s:a cssClass="circle-menu" href="%{trasuibot}" theme="simple">Trà sủi bọt</s:a>
            <s:a cssClass="circle-menu" href="%{tratuoi}" theme="simple">Trà tươi</s:a>
            </div>
        <s:if test="%{productList != null}"> 
            <div class="product-rows">
                <s:iterator value="productList" status="counter" id="xER">
                    <div class="product">
                        <img src="<s:property value="imagePath"/>"/>
                        <div class="product-inf">
                            <div class="product-name">
                                <s:url action="product-details" id="detailLink">
                                    <s:param name="productID" value="productID"/>
                                </s:url>                               
                                <s:a href="%{detailLink}">
                                    <s:property value="productName"/>
                                </s:a>
                            </div>
                            <div class="product-price">
                                <s:property value="price"/> VND
                            </div>
                            <button class="like"><i class="far fa-heart"></i> <p>156</p></button> 
                            <div class="add-to-cart">
                                <s:url action="AddToCartAction" id="AddToCart">
                                    <s:param name="productid" value="productid"/>
                                    <s:param name="location" value="'index'"/>
                                    <s:param name="quantity" value="1"/>
                                </s:url> 
                                <s:a href="%{AddToCart}">
                                    MUA
                                </s:a>
                            </div>
                        </div>
                    </div>
                </s:iterator>
            </div>
        </s:if>

        <script>

            var categoryname = "<s:property value="category"/>";
            var circlemenu = document.getElementsByClassName("circle-menu");
            if (categoryname == "all")
            {
                circlemenu[0].style.color = "#2fce98";
            } else if (categoryname == "tra-sua")
            {
                circlemenu[1].style.color = "#2fce98";
            } else if (categoryname == "tra-sui-bot")
            {
                circlemenu[2].style.color = "#2fce98";
            } else if (categoryname == "tra-tuoi")
            {
                circlemenu[3].style.color = "#2fce98";
            }

        </script>
    </body>
</html>
