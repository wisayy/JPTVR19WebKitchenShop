
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список покупателей</title>
    </head>
    <body>
        <h1>Список покупателей</h1>
        <ol>
            <c:forEach var="customer" items="${listCustomers}">
                <li>"${customer.firstname}". ${customer.lastname}. ${customer.phone}. ${customer.wallet}</li>
            </c:forEach>
        </ol>
    </body>
</html>
