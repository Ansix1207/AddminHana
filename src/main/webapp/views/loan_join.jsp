<%--
  Created by IntelliJ IDEA.
  User: 하나로H012
  Date: 2023-06-16
  Time: 오후 5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <title>Title</title>
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/nav.css">
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous"
  />
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/loan_join.css ">
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/base.css ">

  <script
          src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
          crossorigin="anonymous"
  ></script>
</head>
<body>
  <div class="wrap">
    <%@ include file="common/navbar.jsp" %>

    <main>

      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-7">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
              <div class="card-header"><h3 class="text-center font-weight-light my-4">대출 가입</h3></div>
              <div class="card-body">
                <form>

                  <div class="form-floating mb-3" style="margin-top: 50px">
                    <label for="inputEmail">상품명</label>
                    <input class="form-control" name="inputEmail" type="email" id="inputEmail">
                  </div>

                  <div class="form-floating mb-3">
                    <label for="inputName">이름</label>
                    <input class="form-control" name="inputName" type="text">
                  </div>

                  <div class="form-floating mb-3">
                    <label for="inputName">직장</label>
                    <input class="form-control" name="inputName" type="text">
                  </div>

                  <div class="form-floating mb-3">
                    <label for="inputName">연소득</label>
                    <input class="form-control" name="inputName" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label for="inputName">담보액</label>
                    <input class="form-control" name="inputName" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label for="inputName">신용등급</label>
                    <input class="form-control" name="inputName" type="text">
                  </div>


                  <div class="mt-4 mb-0">
                    <div class="d-grid"><a class="btn btn-primary btn-block" href="login.html">가입 심사</a></div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>


    </main>


  </div>
  <script
          src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"
  ></script>
</body>
</html>
