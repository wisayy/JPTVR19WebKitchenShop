
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Новая мебельная кухня</title>
  </head>
  <body>
    <h1>Добавить новую мебельную кухню</h1>
    <p>${info}</p>
    <br>
    <a href="index.jsp">Главная страница</a>
    <form action="createKitchen" method="POST">
      Продукт <input type="text" name="product"><br>
      Цена <input type="text" name="price"><br>
      <input type="submit" name="submit" value="Добавить">
    </form>
  </body>
</html>
