<%-- 
    Document   : cart
    Created on : Aug 13, 2019, 11:40:15 PM
    Author     : Loi Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="resources/js/numeral.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body onload="setup()">

        <s:include value="header.jsp"/>
        <div class="section-title">GIỎ HÀNG</div>
        <s:if test="%{#session.CART.items.size > 0}">
            <table cellspacing="0" class="cart-table">
                <thead>
                    <tr class="heading">
                        <th align="left" colspan="2" id="aaa">Sản Phẩm</th>
                        <th align="left">Giá</th>
                        <th align="left">Số lượng</th>
                        <th align="left">Tổng</th>
                        <th align="left"></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.CART.items" var="item" status="counter">
                        <s:form action="delete" method="POST" theme="simple">
                            <tr>
                                <td class="image">
                                    <img src=" <s:property value="#item.key.imagePath"/>"/>
                                </td>
                                <td class="info">
                                    <p class="name">
                                        <s:property value="#item.key.productName"/>-Size <s:property value="#item.key.size.sizeName"/>
                                    </p>
                                    <s:if test="%{#item.key.checkedToppingList.size == 0}">
                                        <s:hidden name="toppingIDList" value="null" theme="simple"/>
                                    </s:if>
                                    <s:else>
                                        <s:iterator value="#item.key.checkedToppingList">
                                            <p class="topping">
                                                <s:property value="toppingName"/>
                                                <s:hidden name="toppingIDList" value="%{toppingID}" theme="simple"/>
                                            </p>
                                        </s:iterator>
                                    </s:else>
                                </td>
                                <td class="price">
                                    <s:property value="#item.key.price"/>
                                    <s:hidden value="%{#item.key.price}" name="price"/>
                                </td>   

                                <td class="quantity-cart">
                                    <input type="button" value="-" class="cart-moins" onclick="minusPrice(<s:property value="%{#counter.count-1}"/>)"/>
                                    <input type="number" onkeydown="disableEnter(event)" onkeyup="updateValue(<s:property value="%{#counter.count-1}"/>)" onblur="focusout(<s:property value="%{#counter.count-1}"/>)" min="1" size="25" value="<s:property value="#item.value"/>" class="cart-quantity"/>
                                    <input type="button" value="+" class="cart-plus" onclick="plusPrice(<s:property value="%{#counter.count-1}"/>)"/>
                                </td>
                                <td>
                                    <div class="total">
                                        0
                                    </div>
                                </td> 
                                <td class="delete">
                                    <s:hidden name="sizeID" value="%{#item.key.size.sizeID}"/>
                                    <s:hidden name="productName" value="%{#item.key.productName}"/>
                                    <s:hidden name="productID" value="%{#item.key.productID}"/>
                                    <s:hidden name="price" value="%{#item.key.price}"/>
                                    <s:hidden name="quantity" value="%{#item.value}"/>
                                    <s:submit cssClass="btnDelete" name="action" value="delete" theme="simple" cssStyle="display:none;"/>
                                    <div class="submit-delete" on onclick="submitDelete(<s:property value="%{#counter.count-1}"/>)">
                                        <i class="fa fa-trash-alt" aria-hidden="true" style="color:#ff6f69"></i>
                                    </div>
                                </td> 
                            </tr>
                        </s:form>
                    </s:iterator>
                    <tr style="border: none;">
                        <td colspan="6" align="right">
                            <s:form action="update-cart" method="POST" theme="simple">
                                <s:iterator value="#session.CART.items">
                                    <s:hidden value="%{value}" name="quantityList" theme="simple"/>
                                </s:iterator>
                                <s:submit cssClass="basic" value="Cập nhật" name="action"/>
                            </s:form>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div id="total-info">
                <div id="total-cart-title">
                    <p class="title">Tổng giỏ hàng</p>
                </div>
                <div id="subtotal">
                    <p class="title">Tạm tính</p>
                    <p class="content">5000Đ</p>
                </div>
                <div id="ship-fee">
                    <p class="title">Phí giao hàng</p>
                    <p class="content">Free</p>
                </div>
                <div id="promote">
                    <p class="title">Mã giảm giá</p>
                    <p class="content">
                        <s:form theme="simple">
                            <s:textfield name="promote" theme="simple" cssClass="promote-input"/> 
                        </s:form>
                    </p>
                </div>
                <div id="total-cart">
                    <p class="title">Tổng cộng</p>
                    <p class="content">500000Đ</p>
                </div>
                <div id="payment">
                    <s:form action="delete-cart" method="POST" theme="simple">
                        <s:submit action="update-cart" value="Xóa giỏ hàng" name="action" theme="simple" cssClass="delete-cart"/>
                    </s:form>
                    <s:form action="get-address" method="POST" theme="simple">
                        <s:hidden name="username" value="%{#session.USERINFO.username}"/>
                        <s:submit value="Xác nhận" name="action" theme="simple" cssClass="basic"/>
                    </s:form>
                </div>
            </div>
        </s:if>
        <s:else>
            <div class="no-item">
                Giỏ hàng trống!
            </div>
        </s:else>
        <script>

            function disableEnter(e)
            {
                if (e.keyCode == 13)
                {
                    e.preventDefault();

                    return false;
                }

            }
            function submitDelete(pos)
            {
                var sb = document.getElementsByClassName("btnDelete")[pos];
                sb.click();
            }

            function minusPrice(pos)
            {
                var txtQuantity = document.getElementsByClassName("cart-quantity")[pos];
                var txtHiddenQuantity = document.getElementsByName("quantity")[pos];
                var txtHiddenQuantityList = document.getElementsByName("quantityList")[pos];
                var quantity = txtQuantity.getAttribute("value");
                if (quantity > 1) {
                    quantity = quantity - 1;
                    txtQuantity.setAttribute("value", quantity);
                    txtQuantity.value = quantity;
                    txtHiddenQuantity.value = quantity;
                    txtHiddenQuantityList.value = quantity;
                }
                updatePrice();
            }

            function plusPrice(pos)
            {
                var txtQuantity = document.getElementsByClassName("cart-quantity")[pos];
                var txtHiddenQuantity = document.getElementsByName("quantity")[pos];
                var txtHiddenQuantityList = document.getElementsByName("quantityList")[pos];
                var quantity = txtQuantity.getAttribute("value");
                quantity++;
                txtQuantity.setAttribute("value", quantity);
                txtQuantity.value = quantity;
                txtHiddenQuantity.value = quantity;
                txtHiddenQuantityList.value = quantity;
                updatePrice();
            }

            function updateValue(pos)
            {
                var txtQyantity = document.getElementsByClassName("cart-quantity")[pos];
                var txtHiddenQuantity = document.getElementsByName("quantity")[pos];
                var txtHiddenQuantityList = document.getElementsByName("quantityList")[pos];
                txtQyantity.setAttribute("value", txtQyantity.value);
                txtHiddenQuantity.value = txtQyantity.value;
                txtHiddenQuantityList.value = txtQyantity.value;
                updatePrice();
            }

            function focusout(pos) {
                var txtQyantity = document.getElementsByClassName("cart-quantity")[pos];
                var txtHiddenQuantity = document.getElementsByName("quantity")[pos];
                var txtHiddenQuantityList = document.getElementsByName("quantityList")[pos];
                if (txtQyantity.getAttribute("value") < 1)
                {
                    alert("Số lượng phải lớn hơn hoặc bằng 1");
                    txtQyantity.value = 1;
                    txtQyantity.setAttribute("value", txtQyantity.value);
                    txtHiddenQuantity.value = txtQyantity.value;
                    txtHiddenQuantityList.value = txtQyantity.value;

                } else if (txtQyantity.getAttribute("value") > 100) {
                    alert("Số lượng không vượt quá 100 sản phẩm");
                    txtQyantity.value = 100;
                    txtQyantity.setAttribute("value", txtQyantity.value);
                    txtHiddenQuantity.value = txtQyantity.value;
                    txtHiddenQuantityList.value = txtQyantity.value;

                }

                updatePrice();
            }

            function updatePrice()
            {
                var totalCart = 0;
                var txtTotals = document.getElementsByClassName("total");
                for (var i = 0; i < txtTotals.length; i++) {
                    var quantity = document.getElementsByClassName("cart-quantity")[i].value;
                    var price = document.getElementsByName("price")[i].value;
                    totalCart = totalCart + (Number(quantity) * Number(price));
                    txtTotals[i].innerHTML = numeral(Number(quantity) * Number(price)).format('0,0') + "đ";
                }
                var count = document.getElementsByClassName("content").length;
                var txtSubTotal = document.getElementsByClassName("content")[0];
                txtSubTotal.innerHTML = numeral(totalCart).format('0,0') + "₫";
                var txtTotalCart = document.getElementsByClassName("content")[count - 1];
                txtTotalCart.innerHTML = numeral(totalCart).format('0,0') + "₫";

            }
            function setup() {
            <s:iterator value="#session.CART.items" var="item" status="counter">
                var price = <s:property value="#item.key.price"/>
                var price = numeral(price).format('0,0') + "₫";
                document.getElementsByClassName("price")[<s:property value="%{#counter.count -1}"/>].innerHTML = price;
            </s:iterator>
                updatePrice();
            }

        </script>

    </body>
</html>
