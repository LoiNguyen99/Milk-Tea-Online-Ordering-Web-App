<%-- 
    Document   : error
    Created on : Dec 11, 2019, 3:19:12 PM
    Author     : Loi Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>       
        <div class="section-title">SOMETHING WRONG!!!!!</div>
        <div class="no-item">
            <s:property value="%{#request.ERROR}"/>
        </div>
    </body>
</html>
