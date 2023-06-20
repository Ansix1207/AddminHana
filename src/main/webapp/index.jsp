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
  <form method="post" action="loanInfo.do"><input type="submit" value="대출 현황"></form>
  <form method="get" action="depositInfo.do"><input type="submit" value="예금 현황"></form>
  <form method="post" action="savingsInfo.do"><input type="submit" value="적금 현황"></form>
</body>
</html>
