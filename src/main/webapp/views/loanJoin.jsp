<%--
  Created by IntelliJ IDEA.
  User: 하나로H012
  Date: 2023-06-16
  Time: 오후 5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="hana.teamfour.addminhana.entity.ProductEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hana.teamfour.addminhana.DTO.ProductJoinDTO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hana.teamfour.addminhana.DTO.AccountDTO" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
//  if (request.getAttribute("productJoinDTO") != null) {
//    request.removeAttribute("valid_rrn");
//    ProductJoinDTO productJoinDTO = (ProductJoinDTO) request.getAttribute("productJoinDTO");
//    System.out.println("In loanJoin.jsp : " + productJoinDTO);
//  }
//  String category = request.getAttribute("category").toString();
  ProductJoinDTO productJoinDTO = (ProductJoinDTO) request.getAttribute("productJoinDTO");


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
      <form action="loanjoin" method="POST">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-7">
              <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header"><h3 class="text-center font-weight-light my-4">대출 가입</h3></div>
                <div class="card-body">
                  <div class="form-floating mb-3">
                    <label>Acc_id</label>
                    <input class="form-control" name="Acc_id" value="${Acc_id}" type="text" default="1">
                  </div>
                  <div class="form-floating mb-3">
                    <label>Acc_cid</label>
                    <input class="form-control" name="Acc_cid" value="${Acc_cid}" type="text"
                           default="2">
                  </div>
                  <div class="form-floating mb-3">
                    <label>Acc_date</label>
                    <input class="form-control" name="Acc_date" value="${Acc_date}" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>Acc_balance</label>
                    <input class="form-control" name="Acc_balance" value="${Acc_balance}" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>비밀번호</label>
                    <input class="form-control" name="ACC_PASSWORD" value="${ACC_PASSWORD}" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>Acc_pid</label>
                    <input class="form-control" name="Acc_pid" value="${Acc_pid}" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>Acc_p_category</label>
                    <input class="form-control" name="Acc_p_category" value="${Acc_p_category}"
                           type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>Acc_pname</label>
                    <input class="form-control" name="Acc_pname" value="${Acc_pname}" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>이자율</label>
                    <input class="form-control" name="ACC_INTERESTRATE" value="${ACC_INTERESTRATE}"
                           type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>담보가액</label>
                    <input class="form-control" name="ACC_COLLATERALVALUE" value="${ACC_COLLATERALVALUE}"
                           type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>이자일</label>
                    <input class="form-control" name="ACC_INTEREST_DAY" value="${ACC_INTEREST_DAY}"
                           type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>약정일</label>
                    <input class="form-control" name="ACC_CONTRACT_MONTH" value="${ACC_CONTRACT_MONTH}"
                           type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>만기일</label>
                    <input class="form-control" name="ACC_MATURITYDATE" value="${ACC_MATURITYDATE}"
                           type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>활성화여부</label>
                    <input class="form-control" name="ACC_ISACTIVE" value="${ACC_ISACTIVE}" type="text">
                  </div>
                  <div class="d-grid">
                  </div>
                  <button type="submit" class="btn btn-primary btn-block" id="signButton">신규 손님 가입</button>
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
