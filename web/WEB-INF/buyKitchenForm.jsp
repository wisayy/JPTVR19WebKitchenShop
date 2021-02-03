<%-- 
    Document   : takeOnBookForm
    Created on : 08.12.2020, 10:07:51
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Покупка кухонной мебели</title>
    
    </head>
    <body>
        <h1>Покупка кухонной мебели</h1>
        <p>${info}</p>
        <form action="buyKitchen" method="POST">
            Список мебели:<br>
            <select name="kitchenProductId">
                <option value="">Выберите мебель</option>
                <c:forEach var="kitchenProduct" items="${listKitchens}">
                    <option value="${kitchenProduct.id}">"${kitchenProduct.product}". ${kitchenProduct.price}</option>
                </c:forEach>
            </select>
            Список покупателей:<br>
            <select name="customerId">
                <option value="">Выберите покупателя</option>
                <c:forEach var="customer" items="${listCustomers}">
                    <option value="${customer.id}">"${customer.firstname}". ${customer.lastname}. ${customer.phone}. ${customer.wallet}</option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Купить мебель">
        </form>
    </body>
</html>
