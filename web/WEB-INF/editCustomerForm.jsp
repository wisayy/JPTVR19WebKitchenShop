<%-- 
    Document   : newjsp
    Created on : Jan 27, 2021, 9:03:46 AM
    Author     : pupil
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<h1>Список покупателей</h1>
        <form action="editCustomer" method="POST">
            <select id="customerChoice" name="customer">
                <option value="">Выберите покупателя</option>
                <c:forEach var="customer" items="${listCustomer}">
                    <option value="${customer.id}" id="${customer.id}">${customer.firstname} ${customer.lastname} ${customer.phone} ${customer.wallet}</option>
                </c:forEach>
            </select>
            <br>
            Имя пользователя<input type="text" name="firstname" id="firstname" placeholder="Введите имя"><br>
            Фамилия пользователя<input type="text" name="lastname" id="lastname" placeholder="Введите фамилию"><br>
            Номер телефона<input type="text" name="phone" id="phone" placeholder="Введите телефон"><br>
            Количество денег на счету пользователя<input type="text" name="wallet" id="wallet" placeholder="Введитена сколько хотите пополнить счет"><br>
            <input type="submit" name="submit" value="Изменить">
        </form>
    </body>
    
    <script>
        customerChoice = document.getElementById("customerChoice");
        listCustomer = '${listCustomer}';
        
        firstnameInput = document.getElementById("firstname");
        lastnameInput = document.getElementById("lastname");
        phoneInput = document.getElementById("phone");
        walletInput = document.getElementById("wallet");
        
        customerChoice.addEventListener("change", () => {
           option = document.getElementById(customerChoice.value);
           obj = option.innerHTML.split(" ");
           
           console.log(obj[0]);
           
           firtsnameInput.value = obj[0];
           lastnameInput.value = obj[1];
           phoneInput.value = obj[2];
           walletInput.value = obj[3];
        });
       
    </script>
</html>
