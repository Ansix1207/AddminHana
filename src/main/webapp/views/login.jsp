<%--
  Created by IntelliJ IDEA.
  User: 하나로H008
  Date: 2023-06-19
  Time: 오후 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"/>
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/nav.css">
  <title>Addmin Hana - login.jsp</title>
</head>
<body class="bg-dark">
  <div class="container">
    <div class="row justify-content-center vh-100 align-items-center">
      <div class="col-lg-5">
        <div class="card shadow-lg border-0 rounded-lg">
          <div class="card-header">
            <h3 class="text-center font-weight-light my-4">AddMin 하나</h3>
          </div>
          <div class="card-body">
            <form name="frm" method="post">
              <div class="form-floating mb-3">
                <input
                        class="form-control"
                        id="inputId"
                        name="id"
                        type="text"
                        placeholder="행원 Id"/>
                <label for="inputId">행원 Id</label>
              </div>
              <div class="form-floating mb-3">
                <input
                        class="form-control"
                        id="inputPassword"
                        name="pw"
                        type="password"
                        placeholder="비밀번호"/>
                <label for="inputPassword">비밀번호</label>
              </div>

              <div class="d-flex align-items-center justify-content-end mt-4 mb-0">
                <input class="btn btn-dark" type="submit" value="로그인"/>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
  <script>
      <% if(request.getAttribute("isLoginSuccess") == "false") {%>
      alert("로그인 정보가 올바르지 않습니다.");
      <%}%>
  </script>
</body>
</html>
