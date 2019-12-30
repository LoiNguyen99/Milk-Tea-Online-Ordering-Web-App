<%-- 
    Document   : product_details
    Created on : Aug 11, 2019, 8:59:32 PM
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
        <script src="resources/js/numeral.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <s:include value="header.jsp"/>
        <div class="infor">
            <div class="imgages">
                <img src="<s:property value="dto.imagePath"/>"/>

            </div>
            <div class="details">
                <div id="product-name"><s:property value="dto.productName"/></div>
                <div id="product-price"><s:property value="fomartedPrice"/>₫</div>
                <div id="brand">Mã: <s:property value="productID"/> | Thức ăn cho Chó</div>
                <s:form action="add" method="POST">
                    <div class="radio-size">
                        <label class="label-title">
                            Size
                        </label>

                        <div class="radio">
                            <s:radio name="sizeRadio" list="dto.sizeList" onclick="setSize()" theme="simple"/>
                        </div>
                    </div>
                    <div class="quantity">
                        <label class="label-title">
                            Topping
                        </label>

                        <div class="checkbox">
                            <s:checkboxlist name="checkedToppingList" list="toppingList" onclick="addToppingPrice()" theme="simple"/>
                        </div>
                    </div>

                    <div class="quantity">
                        <label class="label-title">
                            Số lượng
                        </label>
                        <div id="input-div"> 
                            <input type="button" value="-" id="moins" onclick="minusPrice()"/>
                            <input type="number" inputmode="" min="1" size="25" value="1" id="count" onkeyup="updateValue()" onblur="focusout()"/>
                            <input type="button" value="+" id="plus" onclick="plusPrice()"/>
                        </div>
                    </div> 
                    <div class="quantity">
                        <label class="label-title">
                            Tổng cộng
                        </label>
                        <div id="total-price"> 
                            <s:property value="fomartedPrice"/>₫
                        </div>
                    </div>
                    <s:hidden name="productID" value="%{productID}"/>
                    <s:hidden name="productName" value="%{dto.productName}"/>
                    <s:hidden name="imagePath" value="%{dto.imagePath}"/>
                    <s:hidden name="price" value="%{dto.price}" id="hiddenPrice"/>
                    <s:hidden name="quantity" value="1" id="hiddenQuantity"/>
                    <s:submit cssClass="basic" value="Thêm"  name="action" align="left"/>
                </s:form>
            </div>

        </div>
        <div class="description">
            <h2>Mô tả sản phẩm</h2>
            <div>
                <s:property value="dto.description"/>
                sadasd dasdjaldjasl ajdlsajdlkasjdlakd alkdjaskl djsalkdj askdjal ajlkdjaskldjaslkdaskld ljkldjaklsdjalksd kl
                adnkasjd kasjdalsk dsalkd  lakjdlkasd lakjdlkasd
            </div>
        </div>
        <script type="text/javascript">
            //http://numeraljs.com/

            var sizeElement = document.getElementsByName("sizeRadio");
            if (sizeElement.length > 0)
            {
                sizeElement[0].checked = true;
            }

            function minusPrice()
            {
                var txtQuantity = document.getElementById("count");
                var txtHiddenQuantity = document.getElementById("hiddenQuantity");
                var quantity = txtQuantity.getAttribute("value");
                if (quantity > 1) {
                    quantity = quantity - 1;
                    txtQuantity.setAttribute("value", quantity);
                    txtQuantity.value = quantity;
                    txtHiddenQuantity.setAttribute("value", quantity);
                }
                addToppingPrice();
            }

            function plusPrice()
            {
                var txtQuantity = document.getElementById("count");
                var txtHiddenQuantity = document.getElementById("hiddenQuantity");
                var quantity = txtQuantity.getAttribute("value");
                quantity++;
                txtQuantity.setAttribute("value", quantity);
                txtQuantity.value = quantity;
                txtHiddenQuantity.setAttribute("value", quantity);
                addToppingPrice();
            }
            
            function updateValue()
            {
                var txtQyantity = document.getElementById("count");
                var txtHiddenQuantity = document.getElementById("hiddenQuantity");
                txtQyantity.setAttribute("value", txtQyantity.value);
                txtHiddenQuantity.setAttribute("value", txtQyantity.getAttribute("value"));
                addToppingPrice();
            }
            
            function focusout() {
                var txtQyantity = document.getElementById("count");
                var txtHiddenQuantity = document.getElementById("hiddenQuantity");
                if (txtQyantity.getAttribute("value") < 1)
                {
                    alert("Số lượng phải lớn hơn hoặc bằng 1");
                    txtQyantity.value = 1;
                    txtQyantity.setAttribute("value", txtQyantity.value);
                    txtHiddenQuantity.setAttribute("value", txtQyantity.value);
                } else if(txtQyantity.getAttribute("value") > 100)
                {
                    alert("Số lượng không vượt quá 100 sản phẩm");
                    txtQyantity.value = 100;
                    txtQyantity.setAttribute("value", txtQyantity.value);
                    txtHiddenQuantity.setAttribute("value", txtQyantity.value);
                }
                addToppingPrice();
            }
            function setSize()
            {
                var classPrice = document.getElementById("product-price");
                var classHiddenPrice = document.getElementById("hiddenPrice");

                if (sizeElement.length == 3)
                {
                    for (var i = 0; i < sizeElement.length; i++) {
                        if (i == 0 && sizeElement[i].checked)
                        {
                            var price = <s:property value="dto.price"/>;
                            classHiddenPrice.setAttribute('value', price);
                            classPrice.innerHTML = numeral(price).format('0,0') + "₫";

                        } else if (i == 1 && sizeElement[i].checked)
                        {
                            var price = <s:property value="dto.price"/>;
                            price = price + 10000;
                            classHiddenPrice.setAttribute('value', price);
                            classPrice.innerHTML = numeral(price).format('0,0') + "₫";

                        } else if (i == 2 && sizeElement[i].checked)
                        {
                            var price = <s:property value="dto.price"/>;
                            price = price + 20000;
                            classHiddenPrice.setAttribute('value', price);
                            classPrice.innerHTML = numeral(price).format('0,0') + "₫";

                        }

                    }
                } else if (sizeElement.length == 2)
                {
                    for (var i = 0; i < sizeElement.length; i++) {
                        if (i == 0 && sizeElement[i].checked)
                        {

                            var price = <s:property value="dto.price"/>;
                            classHiddenPrice.setAttribute('value', price);
                            classPrice.innerHTML = numeral(price).format('0,0') + "₫";


                        } else if (i == 1 && sizeElement[i].checked)
                        {
                            var price = <s:property value="dto.price"/>;
                            price = price + 10000;
                            classHiddenPrice.setAttribute('value', price);
                            classPrice.innerHTML = numeral(price).format('0,0') + "₫";
                        }
                    }
                }

                addToppingPrice();

            }

            function addToppingPrice()
            {
                var arry = [];
            <s:iterator value="toppingList" status="counter" var="topping">
                arry.push("<s:property value="#topping.value.price"/>");
            </s:iterator>
                var totalToppingPrice = 0;
                for (var i = 1; i <= arry.length; i++) {
                    var checkbox = document.getElementById("checkedToppingList-" + i);
                    if (checkbox.checked == true)
                    {
                        totalToppingPrice = Number(totalToppingPrice) + Number(arry[i - 1]);
                    }
                }
                var productPrice = document.getElementById("hiddenPrice").getAttribute("value");
                var quantity = document.getElementById("hiddenQuantity").getAttribute("value");
                var total = (totalToppingPrice + Number(productPrice)) * Number(quantity);
                document.getElementById("total-price").innerHTML = numeral(total).format('0,0') + "₫";
            }

        </script>
    </body>
</html>
