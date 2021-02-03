<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="editKitchen" method="POST">
            <select id="kitchenChoice" name="kitchenProduct">
                <option value="">Выберите товар</option>
                <c:forEach var="kitchenProduct" items="${listKitchens}">
                    <option value="${kitchenProduct.id}" id="${kitchenProduct.id}">${kitchenProduct.product}. ${kitchenProduct.price}</option>
                </c:forEach>
            </select>
                <br>
                Название мебели<input type="text" name="product" placeholder="Введите название" value="${kitchenProduct.product}"><br>
                Цена мебели<input type="text" name="price" placeholder="Введите цену" value="${kitchenProduct.price}"><br>
            <input type="submit" name="submit" value="Изменить">
        </form>
    </body>
    
    <script>
        kitchenChoice = document.getElementById("kitchenChoice");
        listKitchens = '${listKitchens}';
        
        productInput = document.getElementById("product");
        priceInput = document.getElementById("price");
        
        kitchenChoice.addEventListener("change", () => {
           option = document.getElementById(kitchenChoice.value);
           obj = option.innerHTML.split(" ");
           
           console.log(obj[0]);
           
           productInput.value = obj[0];
           priceInput.value = obj[4];
        });
    </script>
</html>