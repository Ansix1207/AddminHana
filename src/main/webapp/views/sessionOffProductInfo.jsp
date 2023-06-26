<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <title>금융상품조회</title>

</head>
<body>
  <div class="wrap">
    <%@ include file="common/navbar.jsp" %>
    <main>
      <form action="loaninquery" method="GET">
        <div class="input-group">
          <input class="form-control" type="text" name="q" value="${param.q}" aria-describedby="btnNavbarSearch"/>
          <input class="btn btn-search" type="submit" value="검색"/>
          <%--          <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>--%>
        </div>
      </form>
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
      <div class="list1">
        <div class="card-header">
          <h4> &nbsp 추천 대출 상품 리스트</h4>
        </div>
        <ol class="list-group list-group-numbered" id="pages">
          <%
            for (int i = 0; i < productEntity.size(); i++) {
          %>
          <li class="productItem list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto ">
              <div class="fw-bold">
                <div>
                  <h4><span><%=productEntity.get(i).getP_name()%></span></h4>
                  <span>금리 <%=productEntity.get(i).getP_interestrate()%> %</span><br>
                  <span><%=productEntity.get(i).getP_description()%></span>
                </div>
              </div>
            </div>
            <button class="modify">조회</button>
          </li>
          <%
            }
          %>
        </ol>
        <div class=center-content>
          <%--        페이지네이션--%>
          <c:set var="page" value="${(param.p == null)?1:param.p}"/>
          <c:set var="startNum" value="${page-(page-1)%5}"/>
          <c:set var="lastNum" value="17"/>
          <%--          이전 페이지--%>
          <ul class="-list- center">
            <c:if test="${startNum>1}">
              <a class="btn-next" href="?p=${startNum-5}&q=${param.q}"> <</a>
            </c:if>
            <c:if test="${startNum<=1}">
              <span class="btn-next" onclick="alert('디음페이지가 없습니다')"><</span>
            </c:if>
            <%--페이지 번호--%>
            <c:forEach var="i" begin="0" end="4">
              <li class="pagination"><a class="orange bold" style="text-decoration: none;"
                                        href="?p=${startNum+i}&q=${param.q}"> ${startNum+i}</a></li>
            </c:forEach>
            <%--          다음 페이지--%>
            <c:if test="${startNum+5<lastNum}">
              <a href="?p=${startNum+5}&q=${param.q}" class="btn-next"> > </a>
            </c:if>
            <c:if test="${startNum+5>=lastNum}">
              <span class="btn-next" onclick="alert('디음페이지가 없습니다')">> </span>
            </c:if>
          </ul>
        </div>
      </div>
    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
</body>
</html>
