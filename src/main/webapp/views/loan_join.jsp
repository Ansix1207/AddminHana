<%--
  Created by IntelliJ IDEA.
  User: 하나로H012
  Date: 2023-06-14
  Time: 오후 5:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<h1>대출가입</h1>
<table>
  <tr>
    <td>이름</td>
    <td><input type="name"></td>
  </tr>
  <tr>
    <td>직장</td>
    <td><input type="job"></td>
  </tr>
  <tr>
    <td>연소득</td>
    <td><input type="income"></td>
  </tr>
  <tr>
    <td>담보</td>
    <td><input type="guarantee"></td>
  </tr>
  <tr>
    <td>신용등급</td>
    <td><input type="credit_rate"></td>
  </tr>
  <tr>
    <td>가입할 상품</td>
    <td><input type="financial_product"></td>
  </tr>
</table>

<button> 대출 심사</button>
</body>
</html>
