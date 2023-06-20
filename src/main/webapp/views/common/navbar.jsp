<%--
  Created by IntelliJ IDEA.
  User: minseonKwon
  Date: 2023/06/15
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hana.teamfour.addminhana.entity.EmployeeEntity" %>
<div class="sb-sidenav accordion sb-sidenav-dark">
  <div class="sb-sidenav-logo">AddMin 하나</div>
  <div class="sb-sidenav-menu">
    <div class="nav">
      <div class="sb-sidenav-menu-heading">고객</div>
      <a class="nav-link" href="#">
        <div class="sb-nav-link-icon">
          <i class="fas fa-tachometer-alt"></i>
        </div>
        신규 상품 가입
      </a>

      <div class="sb-sidenav-menu-heading">상품</div>
      <a class="nav-link" href="#">
        <div class="sb-nav-link-icon">
          <i class="fas fa-chart-area"></i>
        </div>
        예금 현황
      </a>
      <a class="nav-link" href="#">
        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
        적금 현황
      </a>
      <a class="nav-link" href="#">
        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
        대출 현황
      </a>
    </div>
  </div>
  <div class="sb-sidenav-footer">
    <div class="small">현재 상담 중인 고객:</div>
    권민선님
  </div>
  <div class="sb-sidenav-footer">
    <div class="small">로그인된 행원:</div>
    <%
      if (request.getSession().getAttribute("login") != null) {
        EmployeeEntity user = (EmployeeEntity) request.getSession().getAttribute("login");
    %>
    <div><%=user.getE_name()%>
    </div>
    <%}%>
    <a class="btn btn-dark" href="<%=contextPath%>/logout">로그아웃</a>
  </div>

</div>
