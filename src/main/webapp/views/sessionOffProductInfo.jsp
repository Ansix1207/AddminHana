<%--
  Created by IntelliJ IDEA.
  User: 하나로H012
  Date: 2023-06-19
  Time: 오후 5:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="hana.teamfour.addminhana.entity.ProductEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();

  ArrayList<ProductEntity> productEntity = new ArrayList<>();
  productEntity = (ArrayList<ProductEntity>) request.getAttribute("productEntity");

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
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/sessionOffProductInfo.css">
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/base.css ">
  <title>Title</title>
</head>
<body>
  <div class="wrap">
    <%@ include file="common/navbar.jsp" %>
    <main>
      <div class="input-group">
        <input class="form-control" type="text"
               aria-describedby="btnNavbarSearch"/>
        <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
      </div>
      <div class="col-lg-6">
        <div class="card mb-4">
          <div class="card-header">
            <i class="fas fa-chart-bar me-1"></i>
            20대 남성 예금현황 통계
          </div>
          <div class="card-body">
            <canvas id="myBarChart" width="100%" height="50"></canvas>
          </div>
          <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>
      </div>

      <div class=list>
        <div class="card-header">
          <h4> &nbsp; 추천 대출 상품 리스트</h4>
          <%
            for (int i = 0; i < productEntity.size(); i++) {
          %>
          <div>
            <span>상품 이름 <%=productEntity.get(i).getP_name()%></span>
            <span>금리 <%=productEntity.get(i).getP_interestrate()%></span>
            <span>한도액 <%=productEntity.get(i).getP_limit()%></span>
          </div>
          <%
            }
          %>
        </div>
        <ol class="list-group list-group-numbered">
          <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
              <div class="fw-bold">Subheading</div>
              Content for list item
            </div>
            <button class="join">가입</button>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
              <div class="fw-bold">Subheading</div>
              Content for list item
            </div>
            <button class="join">가입</button>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
              <div class="fw-bold">Subheading</div>
              Content for list item
            </div>
            <button class="join">가입</button>
          </li>
        </ol>
      </div>
    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
</body>
</html>
