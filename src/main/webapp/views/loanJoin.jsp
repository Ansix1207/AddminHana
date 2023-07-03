<%--
  Created by IntelliJ IDEA.
  User: 하나로H012
  Date: 2023-06-16
  Time: 오후 5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="hana.teamfour.addminhana.entity.ProductEntity" %>
<%@ page import="hana.teamfour.addminhana.DTO.CustomerSessionDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hana.teamfour.addminhana.DTO.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();

  CustomerSessionDTO customerSessionDTO = (CustomerSessionDTO) request.getAttribute("customerSessionDTO");
  ProductDTO productDTO = (ProductDTO) request.getAttribute("productDTO");
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
  <title>금융상품가입</title>
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
                <div class="card-header"><h3 class="text-center font-weight-light my-4">금융상품가입</h3></div>
                <div class="card-body">
                  <div class="form-floating mb-3">
                    <label>성명</label>
                    <input class="form-control" name="C_NAME" value="<%=customerSessionDTO.getC_name()%>" type="text" readonly>
                  </div>
                  <div class="form-floating mb-3">
                    <input class="form-control" name="ACC_ID" value="3333" type="hidden">
                  </div>
                  <div class="form-floating mb-3">
                    <input class="form-control" name="ACC_CID" value="<%=customerSessionDTO.getC_id()%>" type="hidden">
                  </div>
                  <div class="form-floating mb-3">
                    <label>비밀번호</label>
                    <input class="form-control" name="ACC_PASSWORD" value="${ACC_PASSWORD}" type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>상품종류</label>
                    <input class="form-control" name="ACC_P_CATEGORY" value="<%=productDTO.getP_category()%>" type="text" readonly>
                  </div>
                  <div class="form-floating mb-3">
                    <label>상품명 </label>
                    <input class="form-control" name="ACC_P_NAME" value="<%=productDTO.getP_name()%>" type="text" readonly>
                  </div>
                  <div class="form-floating mb-3">
                    <label>금액</label>
                    <input class="form-control" name="ACC_BALANCE" value="${ACC_BALANCE}"
                           type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <label>담보가액</label>
                    <input class="form-control" name="ACC_COLLATERALVALUE" value="<%=productDTO.getP_collateralrate()%>"
                           type="text">
                  </div>
                  <div class="form-floating mb-3">
                    <input class="form-control" name="ACC_INTEREST_DAY" value="1" type="hidden">
                  </div>
                  <div class="form-floating mb-3">
                    <label>상품번호</label>
                    <input class="form-control" name="ACC_PID" value="<%=productDTO.getP_id()%>" type="text" readonly>
                  </div>
                  <div class="form-floating mb-3">
                    <label>이자율</label>
                    <input class="form-control" name="ACC_INTERESTRATE" value="<%=productDTO.getP_interestrate()%>%" type="text" readonly>
                  </div>
                  <div class="form-floating mb-3">
                    <label>계약기간</label>
                    <input class="form-control" name="ACC_P_Month" value="<%=productDTO.getP_contract_month()%>개월" type="text" readonly>
                  </div>
                  <div>
                  </div>
                  <div class="d-grid">
                    <button onclick="reloadPage()" type="submit" class="btn btn-primary btn-block" id="signButton">
                      상품 가입
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <script>
        function reloadPage() {
            location.reload();
        }
    </script>
    <script>
        function formatCurrency(input) {
            let value = input.value.replace(/[^0-9]/g, '');
            let formattedValue = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
            input.value = formattedValue + '원';
        }
    </script>
</body>
</html>
