<%--
  Created by IntelliJ IDEA.
  User: minseonKwon
  Date: 2023/06/15
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hana.teamfour.addminhana.DTO.CustomerSessionDTO" %>
<%@ page import="hana.teamfour.addminhana.DTO.EmployeeDTO" %>
<%
  boolean flag = request.getSession().getAttribute("customerSession") != null;
%>
<div class="sb-sidenav accordion sb-sidenav-dark">

  <div class="sb-sidenav-logo"><a href="<%=contextPath%>/"
                                  style="text-decoration: none; color: white;">AddMin 하나</a>
  </div>

  <div class="sb-sidenav-menu">
    <div class="nav">
      <div class="sb-sidenav-menu-heading">손님</div>
      <a class="nav-link" href="<%=contextPath%>/sign ">
        <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>
        신규 손님 가입
      </a>
      <a class="nav-link" href="<%=contextPath%>/customerList?page=1&size=10 ">
        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
        가입 손님 리스트
      </a>
      <div class="sb-sidenav-menu-heading">업무</div>
      <%
        if (flag) {
      %>
      <a class="nav-link" href="<%=contextPath%>/customer/profile">
        <div class="sb-nav-link-icon">
          <i class="fas fa-id-card"></i>
        </div>
        프로필
      </a>
      <%--      <a class="nav-link" href="<%=contextPath%>/customer/loanjoin">--%>
      <%--        <div class="sb-nav-link-icon">--%>
      <%--          <i class="fas fa-angles-right"></i>--%>
      <%--        </div>--%>
      <%--        신규 상품 가입--%>
      <%--      </a>--%>
      <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
         data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
        <div class="sb-nav-link-icon"><i class="fas fa-list"></i></div>
        손님 자산 현황
        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
      </a>
      <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
           data-bs-parent="#sidenavAccordion">
        <nav class="sb-sidenav-menu-nested nav">
          <a class="nav-link" href="<%=contextPath%>/customer/depositInfo">
            <div class="sb-nav-link-icon">
              <i class="fas fa-money-bill-transfer"></i>
            </div>
            예금 현황
          </a>
          <a class="nav-link" href="<%=contextPath%>/customer/savingsInfo">
            <div class="sb-nav-link-icon"><i class="fas fa-piggy-bank"></i></div>
            적금 현황
          </a>
          <a class="nav-link" href="<%=contextPath%>/customer/loanInfo">
            <div class="sb-nav-link-icon"><i class="fas fa-landmark"></i></div>
            대출 현황
          </a>
        </nav>
      </div>
      <% } %>
      <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
         data-bs-target="#collapseLayouts2" aria-expanded="false" aria-controls="collapseLayouts2">
        <div class="sb-nav-link-icon"><i class="fas fa-wallet"></i></div>
        창구 업무
        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
      </a>
      <div class="collapse" id="collapseLayouts2" aria-labelledby="headingOne"
           data-bs-parent="#sidenavAccordion">
        <nav class="sb-sidenav-menu-nested nav">
          <a class="nav-link" href="<%=contextPath%>/deposit">
            <div class="sb-nav-link-icon">
              <i class="fas fa-plus"></i>
            </div>
            입금
          </a>
          <a class="nav-link" href="<%=contextPath%>/withdraw">
            <div class="sb-nav-link-icon"><i class="fas fa-minus"></i></div>
            출금
          </a>
          <a class="nav-link" href="<%=contextPath%>/transfer">
            <div class="sb-nav-link-icon"><i class="fas fa-right-left"></i></div>
            계좌이체
          </a>
        </nav>
      </div>

      <div class="sb-sidenav-menu-heading">상품</div>
      <a class="nav-link" href="<%=contextPath%>/loaninquery?q=예금">
        <div class="sb-nav-link-icon">
          <i class="fas fa-money-bill-transfer"></i>
        </div>
        예금 상품 리스트
      </a>
      <a class="nav-link" href="<%=contextPath%>/loaninquery?q=적금">
        <div class="sb-nav-link-icon"><i class="fas fa-piggy-bank"></i></div>
        적금 상품 리스트
      </a>
      <a class="nav-link" href="<%=contextPath%>/loaninquery?q=대출">
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
    <div class="small">현재 상담 중인 손님:</div>
    <div class="d-flex justify-content-between"><span><%=customerSession.getC_name()%>&nbsp;님</span>
      <a class="btn btn-light btn-sm" style="--bs-btn-font-size: .50rem; display: inline-block"
         href="<%=contextPath%>/logout/customer">거래종료</a>
    </div>
  </div>
  <%}%>
  <div class="sb-sidenav-footer-employee">
    <div class="small">로그인된 행원:</div>
    <%
      if (request.getSession().getAttribute("login") != null) {
        EmployeeDTO employee = (EmployeeDTO) request.getSession().getAttribute("login");
    %>
    <div class="d-flex justify-content-between"><span><%=employee.getE_name()%></span>
      <a class="btn btn-dark btn-sm" style="--bs-btn-font-size: .50rem; display: inline-block"
         href="<%=contextPath%>/logout/user">로그아웃</a>
    </div>
    <%}%>
  </div>
</div>
