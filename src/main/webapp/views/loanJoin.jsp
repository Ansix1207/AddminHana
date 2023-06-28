<%--
  Created by IntelliJ IDEA.
  User: 하나로H012
  Date: 2023-06-16
  Time: 오후 5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="hana.teamfour.addminhana.entity.ProductEntity" %>
<%@ page import="java.util.ArrayList" %>
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
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"/>
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/nav.css">
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/loanJoin.css ">
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/base.css ">
  <title>대출상품가입</title>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="common/navbar.jsp" %>
    </nav>
    <main>
      <form action="loaninquery" method="GET">

        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-7">
              <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header"><h3 class="text-center font-weight-light my-4">대출 가입</h3></div>
                <div class="card-body">
                  <%--                  <div class="form-floating mb-3" style="margin-top: 50px">--%>
                  <%--                    <label>고객번호</label>--%>
                  <%--                    <input class="form-control" name="idNum" value="${param.idNum}" type="text">--%>
                  <%--                  </div>--%>
                  <div class="form-floating mb-3">
                    <label>비밀번호</label>
                    <input class="form-control" name="ACC_PASSWORD" value="${param.ACC_PASSWORD}" type="text">
                  </div>

                  <div class="form-floating mb-3">
                    <label>이자율</label>
                    <input class="form-control" name="ACC_INTERESTRATE" value="${param.ACC_INTERESTRATE}" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>담보가액</label>
                    <input class="form-control" name="ACC_COLLATERALVALUE" value="${param.ACC_COLLATERALVALUE}"
                           type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>이자일</label>
                    <input class="form-control" name="ACC_INTEREST_DAY" value="${param.ACC_INTEREST_DAY}" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>약정일</label>
                    <input class="form-control" name="ACC_CONTRACT_MONTH" value="${param.ACC_CONTRACT_MONTH}"
                           type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>만기일</label>
                    <input class="form-control" name="ACC_MATURITYDATE" value="${param.ACC_MATURITYDATE}" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>활성화여부</label>
                    <input class="form-control" name="ACC_ISACTIVE" value="${param.ACC_ISACTIVE}" type="text">
                  </div>
                  <div class="d-grid"><a class="btn1 btn-block" href="login.html">가입 심사</a></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
</body>
</html>
