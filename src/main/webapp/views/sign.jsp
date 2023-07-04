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
  <script>
      function check() {
          let name = document.querySelector("#name");
          let rrn1 = document.querySelector("#rrn1");
          let rrn2 = document.querySelector("#rrn2");
          let mobile = document.querySelector("#inputMobile");
          let address = document.querySelector("#inputAddress");

          if (name.value === "") {
              name.classList.add("is-invalid");
          } else {
              name.classList.remove("is-invalid");
          }

          if (mobile.value === "") {
              mobile.classList.add("is-invalid");
          }
          let zipCode = document.querySelector("#inputZip");
          if (zipCode.value === "") {
              zipCode.classList.add("is-invalid");
          }
          mobile.addEventListener("change", function () {
              if (mobile.value.length > 10) {
                  mobile.classList.remove("is-invalid"); // 길이가 0 이상이면 "is-invalid" 클래스를 제거합니다.
              } else {
                  mobile.classList.add("is-invalid");
              }
          });

          zipCode.addEventListener("change", function () {
              if (zipCode.value.length > 0) {
                  zipCode.classList.remove("is-invalid"); // 길이가 0 이상이면 "is-invalid" 클래스를 제거합니다.
              } else if (zipCode.value === "") {
                  zipCode.classList.add("is-invalid");
              }
          });

          if (address.value === "") {
              address.classList.add("is-invalid");
          } else {
              address.classList.remove("is-invalid");
          }

          if (name.value === "" || rrn1.value === "" || rrn2.value === "" || mobile.value === "" || address.value === "" || zipCode.value === "") {
              alert("빠진 정보를 입력해주세요.");
          } else {
              const form = document.getElementById('signForm');
              form.submit();
          }
      }

      function submitForm() {
          if (document.getElementById('name').value.length < 1) {
              alert("이름을 입력해야합니다.")
              return;
          }
          if (document.getElementById('rrn1').value.length < 6) {
              alert("주민 등록번호 앞자리를 모두 입력해야 합니다.")
              return;
          }
          if (document.getElementById('rrn2').value.length < 7) {
              alert("주민 등록번호 뒷자리를 모두 입력해야 합니다.")
              return;
          }
          const form = document.getElementById('signForm');

          // 필요한 input 요소 추가

          var inputRRN = document.createElement('input');
          inputRRN.setAttribute('type', 'text');
          inputRRN.setAttribute('name', 'valid_rrn');
          let rrn_p1 = document.getElementById('rrn1').value;
          let rrn_p2 = document.getElementById('rrn2').value // 예시로 고정된 값 설정
          if (!(rrn_p1.trim() == 0) && !(rrn_p2.trim() == 0)) {
              inputRRN.setAttribute("value", rrn_p1 + '-' + rrn_p2);
          }
          form.appendChild(inputRRN);

          form.submit();
          var validRrnInput = form.elements['valid_rrn']; // name이 'valid_rrn'인 요소 가져오기

          if (validRrnInput) {
              validRrnInput.parentNode.removeChild(validRrnInput); // 요소 삭제
              form.removeAttribute('valid_rrn');
          }
      }


      function execDaumPostcode() {
          new daum.Postcode({
              oncomplete: function (data) {
                  // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                  // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                  // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                  var addr = ''; // 주소 변수
                  var extraAddr = ''; // 참고항목 변수

                  //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                  if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                      addr = data.roadAddress;
                  } else { // 사용자가 지번 주소를 선택했을 경우(J)
                      addr = data.jibunAddress;
                  }

                  // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                  if (data.userSelectedType === 'R') {
                      // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                      // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                      if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                          extraAddr += data.bname;
                      }
                      // 건물명이 있고, 공동주택일 경우 추가한다.
                      if (data.buildingName !== '' && data.apartment === 'Y') {
                          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                      }
                      // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                      if (extraAddr !== '') {
                          extraAddr = ' (' + extraAddr + ')';
                      }
                      // 조합된 참고항목을 해당 필드에 넣는다.
                      document.getElementById("extraAddress").value = extraAddr;

                  } else {
                      document.getElementById("extraAddress").value = '';
                  }

                  // 우편번호와 주소 정보를 해당 필드에 넣는다.
                  document.getElementById('inputZip').value = data.zonecode;
                  document.getElementById("inputAddress").value = addr;
                  // 커서를 상세주소 필드로 이동한다.
                  document.getElementById("inputAddress2").focus();
              }
          }).open();
      }

  </script>
</body>
</html>
