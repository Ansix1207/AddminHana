<%--
  Created by IntelliJ IDEA.
  User: 하나로H008
  Date: 2023-06-19
  Time: 오후 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="true" %>
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

    <main class="d-flex flex-column flex-start gap-3 pt-4 mx-4 w-100">
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

      <%-- 가이드 컴포넌트 --%>
      <div class="accordion w-75 adminGuideComponent mt-4" id="accordionExample"></div>

    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
  <script>
      const alertMessage = '<%=request.getParameter("message")%>';
  </script>
  <script>
      const guideList = [
          {
              title: "신규 손님 가입",
              header: "신규 손님 가입이 가능합니다.",
              description: [
                  "필요한 정보를 입력합니다.",
                  "이름, 주민번호, 도로명 주소, 상세주소, 휴대폰번호, 직업은 필수입력 항목입니다."
              ]
          },
          {
              title: "가입 손님 리스트",
              header: "가입된 손님의 리스트를 보여줍니다.",
              description: [
                  "10 entries pes page의 숫자를 통해 1페이지당 몇 명의 손님을 표시할지 선택가능합니다.",
                  "검색창을 통해 손님의 이름을 검색할 수 있습니다.",
                  "페이지 선택을 통해 손님 리스트 page를 이동할수 있습니다."
              ]
          }, {
              title: "프로필 페이지",
              header: "손님에 대한 정보를 한눈에 보여줍니다.",
              description: [
                  "프로필 : 손님의 요약 정보를 보여줍니다.",
                  "자산 현황 : 자산 액수와 계좌 리스트 정보를 보여주며, 차트를 통해 한눈에 자산 현황을 보여줍니다.",
                  "새로고침 버튼을 누르면 예금/적금/대출 차트가 현재시점 기준으로 새로고침됩니다.",
                  "특이사항 : 손님에 대한 메모를 적어둘 수 있습니다. 메모 작성 후 수정 버튼을 누르면 데이터가 수정됩니다."
              ]
          }, {
              title: "신규 상품 가입",
              header: "상품 가입이 가능합니다.",
              description: [
                  "이름, 비밀번호, 상품종류 등에 대한 정보를 입력 후 '상품 가입'을 클릭하면 신규 상품 가입이 가능합니다.",
              ]
          }, {
              title: "손님 자산 현황",
              header: "손님의 자산 정보와 추천 상품을 보여줍니다.",
              description: [
                  "자산 현황 : 🔃(reload button)을 누르면 총 예금/적금/대출액 및 자산 차트가 표시됩니다.",
                  "추천 상품 : 손님의 나이대, 성별, 직업에 기반한 추천 상품이 표시됩니다.",
                  "          같은 나이대와 성별, 직업을 가진 다른 손님들이 가장 많이 가입한 상품 2개를 추천합니다."
              ]
          }, {
              title: "창구 업무",
              header: "창구업무를 누르면 입금, 출금, 계좌이체를 진행할 수 있습니다.",
              description: [
                  "입금은 활성화된 계좌에만 진행할 수 있습니다.",
                  "출금과 계좌이체에는 계좌와 비밀번호를 입력한 뒤 정보가 일치하면 거래를 진행할 수 있습니다.",
                  "출금이 필요한 거래에는 입출금통장(보통예금) 상품만 출금이 가능합니다."
              ]
          }, {
              title: "상품 리스트",
              header: "예금/적금/대출 상품 리스트를 보여줍니다.",
              description: [
                  ""
              ],
          }
      ]
      const $adminGuideComponent = document.querySelector(".adminGuideComponent");
      let innerHTML = "";
      guideList.forEach((guide, idx) => {
          const {title, header, description} = guide;
          innerHTML += `
          <div class="accordion-item">
              <h2 class="accordion-header">
                  <button class="accordion-button bg-secondary text-bg-secondary ${idx == 0 ? "" : "collapsed"}" type="button" data-bs-toggle="collapse"
                          data-bs-target="#collapse${idx}"
                          aria-expanded="${idx == 0 ? "true" : "false"}" aria-controls="collapse${idx}">
                      #${idx + 1}. ${title}
                  </button>
              </h2>
              <div id="collapse${idx}" class="accordion-collapse collapse ${idx == 0 ? "show" : ""}" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <strong>${header}</strong><br/>
                  ${description.map((sentence) => `- ${sentence} <br/>`).join("")}
                </div>
              </div>
          </div>
          `
      })
      $adminGuideComponent.innerHTML = innerHTML;

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
