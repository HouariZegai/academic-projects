<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <meta charset="UTF-8">
    <title>ADL - TP2</title>
    <style>
      input[type="text"], input[type="password"], input[type="number"] {
          margin-bottom: 10px;
          display: block;
          font-size: 22px;
      }
    </style>
  </head>
  <body>
    <h1>Employee Management System</h1>
    <form action="Employe" method="POST">
      <input type="number" name="id" placeholder="id" />
      <input type="text" name="username" placeholder="Username" />
      <input type="password" name="password" placeholder="Password" />
      <input type="text" name="phone" placeholder="Phone" />
      <input type="submit" name="add" value="add" />
      <input type="submit" name="edit" value="edit" />
      <input type="submit" name="delete" value="delete" />
</form>
  </body>
</html>
