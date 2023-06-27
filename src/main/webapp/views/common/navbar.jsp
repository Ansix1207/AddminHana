<%--
  Created by IntelliJ IDEA.
  User: minseonKwon
  Date: 2023/06/15
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hana.teamfour.addminhana.entity.EmployeeEntity" %>
<%@ page import="hana.teamfour.addminhana.DTO.CustomerSummaryDTO" %>
<%@ page import="hana.teamfour.addminhana.DTO.CustomerSessionDTO" %>
<%
  boolean flag = request.getSession().getAttribute("customerSession") != null;
%>
<div class="sb-sidenav accordion sb-sidenav-dark">

  <div class="sb-sidenav-logo"><a href="<%=contextPath%>/"
                                  style="text-decoration: none; color: white;">AddMin 하나</a>
  </div>

  <div class="sb-sidenav-menu">
    <div class="nav">
      <div class="sb-sidenav-menu-heading">고객</div>
      <%--  TODO: 고객 세션이 존재할 때 코드 추가 --%>
      <%
        if (flag) {
      %>
      <a class="nav-link" href="#">
        <div class="sb-nav-link-icon">
          <i class="fas fa-id-card"></i>
        </div>
        프로필
      </a>
      <a class="nav-link" href="<%=contextPath%>/sign">
        <div class="sb-nav-link-icon">
          <i class="fas fa-angles-right"></i>
        </div>
        신규 상품 가입
      </a>
      <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
         data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
        <div class="sb-nav-link-icon"><i class="fas fa-list"></i></div>
        고객 계좌 현황
        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
      </a>
      <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
           data-bs-parent="#sidenavAccordion">
        <nav class="sb-sidenav-menu-nested nav">
          <a class="nav-link" href="<%=contextPath%>/depositInfo">
            <div class="sb-nav-link-icon">
              <i class="fas fa-money-bill-transfer"></i>
            </div>
            예금 현황
          </a>
          <a class="nav-link" href="<%=contextPath%>/savingsInfo">
            <div class="sb-nav-link-icon"><i class="fas fa-piggy-bank"></i></div>
            적금 현황
          </a>
          <a class="nav-link" href="<%=contextPath%>/loanInfo">
            <div class="sb-nav-link-icon"><i class="fas fa-landmark"></i></div>
            대출 현황
          </a>
        </nav>
      </div>
      <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
         data-bs-target="#collapseLayouts2" aria-expanded="false" aria-controls="collapseLayouts2">
        <div class="sb-nav-link-icon"><i class="fas fa-wallet"></i></div>
        창구 업무
        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
      </a>
      <div class="collapse" id="collapseLayouts2" aria-labelledby="headingOne"
           data-bs-parent="#sidenavAccordion">
        <nav class="sb-sidenav-menu-nested nav">
          <a class="nav-link" href="#">
            <div class="sb-nav-link-icon">
              <i class="fas fa-plus"></i>
            </div>
            입금
          </a>
          <a class="nav-link" href="#">
            <div class="sb-nav-link-icon"><i class="fas fa-minus"></i></div>
            출금
          </a>
          <a class="nav-link" href="#">
            <div class="sb-nav-link-icon"><i class="fas fa-right-left"></i></div>
            계좌이체
          </a>
        </nav>
      </div>
      <%} else {%>
      <a class="nav-link" href="#">
        <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>
        신규 고객 가입
      </a>
      <%}%>

      <div class="sb-sidenav-menu-heading">상품</div>
      <a class="nav-link" href="#">
        <div class="sb-nav-link-icon">
          <i class="fas fa-money-bill-transfer"></i>
        </div>
        예금 상품 리스트
      </a>
      <a class="nav-link" href="#">
        <div class="sb-nav-link-icon"><i class="fas fa-piggy-bank"></i></div>
        적금 상품 리스트
      </a>
      <a class="nav-link" href="#">
        <div class="sb-nav-link-icon"><i class="fas fa-landmark"></i></div>
        대출 상품 리스트
      </a>

    </div>
  </div>
  <%
    if (flag) {
      CustomerSessionDTO customerSession = (CustomerSessionDTO) request.getSession().getAttribute("customerSession");
  %>
  <div class="sb-sidenav-footer">
    <div class="small">현재 상담 중인 고객:</div>
    <div class="d-flex justify-content-end"><span><%=customerSession.getC_name()%>&nbsp;님</span>
      <a class="btn btn-light btn-sm" style="--bs-btn-font-size: .50rem; display: inline-block"
         href="<%=contextPath%>/logout/customer">세션아웃</a>
    </div>
  </div>
  <%}%>
  <div class="sb-sidenav-footer-employee">
    <div class="small">로그인된 행원:</div>
    <%
      if (request.getSession().getAttribute("login") != null) {
        EmployeeEntity user = (EmployeeEntity) request.getSession().getAttribute("login");
    %>
    <div class="d-flex"><span><%=user.getE_name()%></span>
      <a class="btn btn-dark btn-sm" style="--bs-btn-font-size: .50rem; display: inline-block"
         href="<%=contextPath%>/logout/user">로그아웃</a>
    </div>
    <%}%>

  </div>

</div>
