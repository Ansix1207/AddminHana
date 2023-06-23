<%--
  Created by IntelliJ IDEA.
  User: ansix
  Date: 2023/06/19
  Time: 3:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <title>Addmin Hana - sign.jsp</title>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="common/navbar.jsp" %>
    </nav>

    <main class="mx-auto d-flex vh-100 align-items-center">
      <div class="w-75 mx-auto mb-5">
        <form id="hide_form" action="sign" method="post" style="display: none">
          <input type="text" class="form-control" id="hide_rrn" name="hide_rrn">
        </form>
        <form class="row g-3 needs-validation" action="sign" method="post">
          <div class="col-md-12">
            <label for="name" class="form-label">이름 / NAME</label>
            <input type="text" class="form-control" id="name" name="c_name">
          </div>
          <div class="col-md-6">
            <label for="rrn1" class="form-label">주민번호  / RRN 앞자리</label>
          </div>
          <div class="col-md-6">
            <label for="rrn2" class="form-label">주민번호  / RRN 뒷자리</label>
          </div>
          <div class="col-md-5">
            <input type="text" class="form-control" maxlength="6" id="rrn1" name="c_rrn1">
          </div>
          <div class="col-md-1 justify-content-center text-center fs-4">-</div>
          <div class="col-md-5">
            <input type="text" class="form-control" maxlength="7" id="rrn2" name="c_rrn2">
          </div>
          <div class="col-md-1 text-center fs-2 mx-auto">
            <button type="button" class="btn btn-primary" id="search">조회</button>
          </div>
          <div class="col-md-10">
            <label for="inputZip" class="form-label">우편번호</label>
            <input type="text" class="form-control" id="inputZip">
          </div>
          <div class="col-md-2 d-flex align-items-end">
            <button type="button" class="btn btn-primary flex-grow-1">우편번호 찾기</button>
          </div>
          <div class="col-8">
            <label for="inputAddress" class="form-label">도로명 주소</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="" name="c_address1">
          </div>
          <div class="col-4">
            <label for="inputAddress2" class="form-label">상세주소</label>
            <input type="text" class="form-control" id="inputAddress2" placeholder="1동 101호, 2층, 지하" name="c_address2">
          </div>
          <div class="col-md-8">
            <label for="inputMobile" class="form-label">휴대폰번호</label>
            <input type="text" class="form-control" id="inputMobile" name="c_mobile">
          </div>
          <div class="col-md-4">
            <label for="inputState" class="form-label">직업</label>
            <select id="inputState" class="form-select" name="c_job">
              <option selected value="일반">일반</option>
              <option value="직장인">직장인</option>
              <option value="사업자">사업자</option>
              <option value="전문직">전문직</option>
              <option value="공무원">공무원</option>
            </select>
          </div>
          <div class="form-floating">
            <textarea class="form-control" placeholder="Leave a comment here" name="c_description" id="floatingTextarea2"
                      style="height: 100px"></textarea>
            <label for="floatingTextarea2">특이사항</label>
          </div>
          <button type="submit col-12" class="btn btn-primary">신규 손님 가입</button>
          <%--            <input type="submit col-12" class="btn btn-primary"  value="회원 가입"/>--%>
        </form>
      </div>

    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
  <script>
    document.getElementById('search').onclick =  ()=> {
        var rrn = document.getElementById('rrn1').value + '-' +document.getElementById('rrn2').value;
        document.getElementById('hide_rrn').value = rrn .toString();
        document.getElementById('hide_form').submit();        //form엘리먼트 생성
        // form.setAttribute('rrn',);
    };
  </script>
</body>
</html>
