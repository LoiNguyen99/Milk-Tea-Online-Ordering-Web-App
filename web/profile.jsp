<%-- 
    Document   : profile
    Created on : Aug 20, 2019, 10:14:49 AM
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

        <s:if test="%{#session.USERINFO != null}">
            <s:include value="header.jsp"></s:include>
                <div id="profile-menu">
                    <div class="menu-title-selected"><i class="fas fa-user"></i> Thông tin cá nhân</div>
                    <div class="menu-title"><a><i class="fas fa-heart"></i> Danh sách yêu thích</a></div>
                    <div class="menu-title"><a><i class="fas fa-list-alt"></i> Đơn Hàng của tôi</a></div>

                <s:url action="logout" var="logoutLink">

                </s:url>
                <div class="menu-title">
                    <s:a href="%{logoutLink}" theme="simple">
                        <i class="fas fa-sign-out-alt"></i> Đăng xuất
                    </s:a>
                </div>
            </div>
            <div id="profile-info">
                <div id="profile-info-title">
                    Thông tin cá nhân
                </div>
                <div id="profile-info-content">
                    <s:form action="edit-profile" method="POST">
                        <s:textfield name="username" id="username" label="Tên tài khoản" value="%{dto.username}" disabled="true"/>
                        <s:textfield name="fullname" id="fullname" label="Họ và Tên" value="%{dto.fullname}" disabled="true"/>
                        <s:textfield name="phoneNo" id="phoneNo" label="Số điện thoại" value="%{dto.phoneNo}" disabled="true"/>
                        <s:textfield name="address" id="address" label="Địa chỉ giao hàng" value="%{dto.address}" disabled="true"/>
                        <s:submit align="right" id="edit" theme="simple" onclick="editOn()" value="Sửa"></s:submit> 
                        <s:submit  id="exit" value="Hủy" theme="simple" onclick="editOff()"/>
                        <s:submit  id="save" value="Lưu" theme="simple"/>
                    </s:form>
                    <s:submit  id="edit-pass" theme="simple" onclick="editPassOn()" value="Đổi mật khẩu"></s:submit> 

                        <div id="pass-form" style="display: none;">

                        <s:form action="change-password" method="POST">
                            <s:password name="password" id="password" label="Mật khẩu cũ" value="hah"/>
                            <s:password name="newPassword" id="newPassword" label="Mât khẩu mới"/> 
                            <s:hidden name="username" value="%{dto.username}"/>
                            <s:submit id="exit-pass" value="Hủy" theme="simple" onclick="editPassOff()"/>
                            <s:submit id="save-pass" value="Lưu" theme="simple"/>
                        </s:form>

                    </div>
                </div>
            </div>
        </s:if>
        <s:else>
            <s:action name="home" executeResult="true"/>
        </s:else>
        <s:if test="%{#request.MESS != null}">
            <div class="messDialog">
                <s:property value="#request.MESS"/>
                <div class="close" onclick="CloseDialog()">
                    <i class="fas fa-times"></i>
                </div>
            </div>
        </s:if>
    </div>
    <script>
        document.getElementById("edit").addEventListener("click", function (event) {
            event.preventDefault()
        });
        document.getElementById("exit").addEventListener("click", function (event) {
            event.preventDefault()
        });
        document.getElementById("edit-pass").addEventListener("click", function (event) {
            event.preventDefault()
        });
        document.getElementById("exit-pass").addEventListener("click", function (event) {
            event.preventDefault()
        });
        function editOn()
        {
            document.getElementById("edit").style.display = "none";
            document.getElementById("save").style.display = "inline-block";
            document.getElementById("exit").style.display = "inline-block";
            document.getElementById("fullname").disabled = false;
            document.getElementById("phoneNo").disabled = false;
            document.getElementById("address").disabled = false;
        }
        function editOff()
        {
            document.getElementById("edit").style.display = "inline-block";
            document.getElementById("save").style.display = "none";
            document.getElementById("exit").style.display = "none";
            document.getElementById("fullname").disabled = true;
            document.getElementById("phoneNo").disabled = true;
            document.getElementById("address").disabled = true;
        }
        function editPassOn()
        {
            document.getElementById("edit-pass").style.display = "none";
            document.getElementById("pass-form").style.display = "inline-block";
        }
        function editPassOff()
        {
            document.getElementById("edit-pass").style.display = "inline-block";
            document.getElementById("pass-form").style.display = "none";

        }
        function CloseDialog() {
            document.getElementsByClassName("messDialog")[0].style.display = "none";
        }
    </script>
</body>
</html>
