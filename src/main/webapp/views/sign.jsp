<%@ page import="hana.teamfour.addminhana.DTO.CustomerSignDTO" %><%--
  Created by IntelliJ IDEA.
  User: ansix
  Date: 2023/06/19
  Time: 3:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
  boolean isCheck = false;
  if (request.getAttribute("customerSignDTO") != null) {
    request.removeAttribute("valid_rrn");
    CustomerSignDTO customerSignDTO = (CustomerSignDTO) request.getAttribute("customerSignDTO");
    if (request.getAttribute("message").equals("중복되지 않았습니다")) {
      isCheck = true;
    }
  }
  String message = (String) request.getAttribute("message");
  String extraAddress = request.getParameter("extraAddress");
  String inputZip = request.getParameter("inputZip");
  String c_address1 = request.getParameter("c_address1");
  String c_address2 = request.getParameter("c_address2");
  String c_mobile = request.getParameter("c_mobile");
  String c_description = request.getParameter("c_description");
  //null처리
  if (c_address1 == null) {
    c_address1 = "";
  }
  if (c_address2 == null) {
    c_address2 = "";
  }
  if (c_mobile == null) {
    c_mobile = "";
  }
  if (c_description == null) {
    c_description = "";
  }
  if (extraAddress == null) {
    extraAddress = "";
  }
  if (inputZip == null) {
    inputZip = "";
  }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/nav.css">
  <title>Addmin Hana - sign.jsp</title>

</head>
<body>
  <%
    if (request.getAttribute("message") != null) {
      // 처리 완료 후 메시지 출력
      request.removeAttribute("message");
      out.println("<script>");
      out.println("var alertMessage = '" + message + "';");
      out.println("alert(alertMessage);");
      out.println("</script>");
    }
  %>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="common/navbar.jsp" %>
    </nav>
    <main class="mx-auto d-flex vh-100 align-items-center">
      <div class="w-75 mx-auto mb-5">
        <form class="row g-3 needs-validation" id="signForm" action="sign" method="post">
          <div class="col-md-12">
            <label for="name" class="form-label">이름 / NAME</label>
            <input type="text" class="form-control" id="name" name="c_name"
                   value="${customerSignDTO.c_name}"           <% if (isCheck) { %> readonly
                   style="background: lightgray" <%}%>
                   default=""/>
          </div>
          <div class="col-md-6">
            <label for="rrn1" class="form-label">주민번호 / RRN 앞자리</label>
          </div>
          <div class="col-md-6">
            <label for="rrn2" class="form-label">주민번호 / RRN 뒷자리</label>
          </div>
          <div class="col-md-5">
            <input type="text" class="form-control" maxlength="6" id="rrn1" name="c_rrn1"
                   value="${fn:split(customerSignDTO.c_rrn, '-')[0]}"  <% if (isCheck) { %> readonly
                   style="background: lightgray" <%}%>
                   oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
          </div>
          <div class="col-md-1 justify-content-center text-center fs-4">-</div>
          <div class="col-md-5">
            <input type="text" class="form-control" maxlength="7" id="rrn2" name="c_rrn2"
                   value="${fn:split(customerSignDTO.c_rrn, '-')[1]}" default=""<% if (isCheck) { %> readonly
                   style="background: lightgray" <%}%>
                   oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
          </div>
          <div class="col-md-1">
            <button type="button" class="btn btn-primary form-control" id="search" onclick="submitForm()">조회</button>
          </div>
          <div class="col-md-5">
            <label for="inputZip" class="form-label">우편번호</label>
            <input type="text" class="form-control" id="inputZip" name="inputZip"
                   value="<%=inputZip%>" default="">
          </div>
          <div class="col-5">
            <label for="inputAddress2" class="form-label">참고항목</label>
            <input type="text" class="form-control" id="extraAddress" placeholder="참고항목" name="extraAddress"
                   value="<%=extraAddress%>" default="">
          </div>
          <div class="col-md-2 d-flex align-items-end">
            <button type="button" class="btn btn-primary flex-grow-1" onclick="execDaumPostcode()">우편번호 찾기</button>
          </div>
          <div class="col-8">
            <label for="inputAddress" class="form-label">도로명 주소</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="" name="c_address1"
                   value="<%=c_address1%>" default="">
          </div>
          <div class="col-4">
            <label for="inputAddress2" class="form-label">상세주소</label>
            <input type="text" class="form-control" id="inputAddress2" placeholder="1동 101호, 2층, 지하" name="c_address2"
                   value="<%=c_address2%>" default="">
          </div>
          <div class="col-md-8">
            <label for="inputMobile" class="form-label">휴대폰번호</label>
            <input type="text" class="form-control" id="inputMobile" name="c_mobile"
                   value="<%=c_mobile%>" default="">
          </div>
          <div class="col-md-4">
            <label for="inputState" class="form-label">직업</label>
            <select id="inputState" class="form-select" name="c_job">
              <option value="일반" ${"일반".equals(request.getAttribute("job")) ? 'selected' : ''}>일반</option>
              <option value="직장인" ${"직장인".equals(request.getAttribute("job")) ? 'selected' : ''}>직장인</option>
              <option value="사업자" ${"사업자".equals(request.getAttribute("job")) ? 'selected' : ''}>사업자</option>
              <option value="전문직" ${"전문직".equals(request.getAttribute("job")) ? 'selected' : ''}>전문직</option>
              <option value="공무원" ${"공무원".equals(request.getAttribute("job")) ? 'selected' : ''}>공무원</option>
            </select>
          </div>
          <div class="form-floating">
            <textarea class="form-control" placeholder="Leave a comment here" name="c_description"
                      id="floatingTextarea2"
                      style="height: 100px"><%=c_description%></textarea>
            <label for="floatingTextarea2">특이사항</label>
          </div>
          <% if (isCheck) { %>
          <button type="button" class="btn btn-primary col-12" id="signButton" onclick="check()">신규 손님 가입</button>
          <%} %>
        </form>
      </div>

    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"/>
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="<%=contextPath%>/resources/sign.js"></script>
</body>
</html>
