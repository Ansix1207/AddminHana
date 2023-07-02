<%--
  Created by IntelliJ IDEA.
  User: 하나로H008
  Date: 2023-06-19
  Time: 오후 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
  String alertMessage = (String) request.getParameter("message");
  if (alertMessage != null && alertMessage.equals("customerLoginFail")) {
    alertMessage = "손님 정보를 확인해주세요.";
  }
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
  <title>Addmin Hana - main.jsp</title>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="common/navbar.jsp" %>
    </nav>

    <jsp:include page="common/toastSuccess.jsp" flush="false">
      <jsp:param name="title" value="로그인 성공"/>
      <jsp:param name="subtitle" value="Success"/>
      <jsp:param name="description" value="로그인에 성공했습니다. "/>
    </jsp:include>

    <jsp:include page="common/toastFail.jsp" flush="false">
      <jsp:param name="title" value="로그인 실패"/>
      <jsp:param name="subtitle" value="Fail"/>
      <jsp:param name="description" value="<%=alertMessage%>"/>
    </jsp:include>

    <main class="d-flex flex-column justify-content-center gap-2 mx-4 w-100">
      <form name="customerLoginForm" action="<%=contextPath%>/customer/profile" method="post"
            class="input-group input-group-lg flex-nowrap w-75">
          <span class="input-group-text" id="addon-wrapping">
            <i class="fas fa-magnifying-glass"></i>
          </span>
        <input
                id="rrnInput"
                name="customerRRN"
                type="text"
                class="form-control"
                placeholder="주민등록번호 입력"
                aria-label="customerRRN"
                aria-describedby="addon-wrapping"
                maxlength="14"
                minlength="11"
        />
      </form>
      <div class="input-group input-group-lg flex-nowrap">
        <form name="customerLoginForm" action="main" method="post"
              class="input-group input-group-lg flex-nowrap w-75">
          <span class="input-group-text" id="addon-wrapping2">
            <i class="fas fa-magnifying-glass"></i>
          </span>
          <input
                  id="productInput"
                  name="searchProduct"
                  type="text"
                  class="form-control"
                  placeholder="상품 검색"
                  aria-label="Username"
                  aria-describedby="addon-wrapping2"
          />
        </form>
      </div>

      <div class="accordion w-75" id="accordionExample">
        <div class="accordion-item">
          <h2 class="accordion-header">
            <button class="accordion-button bg-secondary text-bg-secondary" type="button" data-bs-toggle="collapse"
                    data-bs-target="#collapseOne"
                    aria-expanded="true" aria-controls="collapseOne">
              자산 정보 가이드 #1
            </button>
          </h2>
          <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong>손님의 자산 정보를 알려줍니다.</strong><br />
              :시계_방향_화살표:(reload button)을 누르면 총 예금/적금/대출액 및 자산 차트가 표시됩니다.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header">
            <button class="accordion-button bg-secondary text-bg-secondary collapsed" type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
              가입 상품 가이드 #2
            </button>
          </h2>
          <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong>손님이 가입한 상품을 한 눈에 보여줍니다.</strong><br />
              상품 이름, 만기일, 이자율, 잔액을 확인할 수 있습니다.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header">
            <button class="accordion-button bg-secondary text-bg-secondary collapsed" type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
              추천 대출 상품 #3
            </button>
          </h2>
          <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong>손님의 나이대, 성별, 직업에 기반한 추천 상품이 표시됩니다.</strong><br />
              같은 나이대와 성별, 직업을 가진 다른 손님들이 가장 많이 가입한 상품 2개를 추천합니다.
            </div>
          </div>
        </div>
      </div>

    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
  <script>
      const alertMessage = '<%=request.getParameter("message")%>';
      const $descriptionForm = document.querySelector('.descriptionForm')
      const $toastSuccess = document.getElementById('toastSuccess')
      const $toastFailure = document.getElementById('toastFail')

      document.addEventListener("DOMContentLoaded", () => {
          if (alertMessage == null || alertMessage == "null") {
              return;
          } else {
              triggerToast($toastFailure);
          }
      })

      const triggerToast = ($target) => {
          const toast = new bootstrap.Toast($target);
          toast.show();
      }

      window.onkeydown = function (event) {
          const kcode = event.key;
          if (kcode == "refresh") {
              history.replaceState({}, null, location.pathname);
          }
      }
  </script>
</body>
</html>
