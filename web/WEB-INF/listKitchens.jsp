<%-- 
    Document   : listBooks
    Created on : 04.12.2020, 10:03:17
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список мебели</title>
    </head>
    <body>
        <h1>Список мебели</h1>
        <ol>
            <c:forEach var="kitchenProduct" items="${listKitchens}">
                <li>"${kitchenProduct.product}". ${kitchenProduct.price}</li>
            </c:forEach>
        </ol>
    </body>
</html>
