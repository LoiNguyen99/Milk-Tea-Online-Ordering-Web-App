<%-- 
    Document   : home
    Created on : Aug 10, 2019, 3:35:56 PM
    Author     : Loi Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>       
        <div class="section-title">MILK TEA</div>
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
    </body>
</html>
