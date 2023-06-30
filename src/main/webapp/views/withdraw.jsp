<%--
  Created by IntelliJ IDEA.
  User: ansix
  Date: 2023/06/27
  Time: 1:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
  String message = (String) request.getAttribute("alert_message");
  String checkflag = (String) request.getAttribute("checkflag");
  String pagetype = (String) request.getAttribute("page");
  String withdrawType = "";
  if (pagetype == "withdraw") {
    withdrawType = "disabled";
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
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/nav.css">
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/base.css">
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <title>Admin Hana - tc.jsp</title>
</head>
<body>
  <% if (message != null) { %>
  <script>
      var alertMessage = '<%= message.replace("'", "\\'").replace("\n", "\\n") %>';
      alert(alertMessage);
  </script>
  <% } %>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="common/navbar.jsp" %>
    </nav>
    <main>
      <form action="withdraw" method="post" id="withdrawForm" class="p-5 bg-white">
        <h2 class="h4 text-black mb-5">출금</h2>
        <div class="row form-group">
          <div class="col-md-5 mb-3 mb-md-0">
            <label class="text-black" for="acc_id">출금 계좌</label>
            <input type="text" id="acc_id" name="acc_id"
                   value="${acc_id}" <% if (checkflag != null && checkflag.equals("1")) { %>
                   readonly <%}%>
                   class="form-control">
            <!-- 나머지 코드 생략 -->
          </div>
          <div class="col-md-2 mb-3 mb-md-0 form-group">
            <label class="text-black" for="acc_password">계좌 비밀번호</label>
            <input type="text" id="acc_password" value="${acc_password}" maxlength="4" name="acc_password"
                   class="form-control">
          </div>
          <div class="col-md-1 mb-3 mb-md-0 form-group d-flex align-items-end">
            <input type="button" value="입력" onclick="checkPwd()" class="btn btn-primary text-white form-control">
          </div>
        </div>
        <div class="row form-group">
          <div class="col-md-8">
            <label class="text-black" for="acc_balance">계좌 잔고</label>
            <input type="text" id="acc_balance" name="acc_balance" value="${acc_balance}" readonly="true"
                   class="form-control">
          </div>
        </div>
        <div class="row form-group">
          <div class="col-md-8">
            <label class="text-black" for="t_amount">거래금액</label>
            <input type="text" id="t_amount" name="t_amount" value="${t_amount}" class="form-control">
          </div>
        </div>

        <div class="row form-group">
          <div class="col-md-8">
            <label class="text-black" for="message">거래내용</label>
            <textarea name="message" id="message" cols="20" rows="7" class="form-control"
                      placeholder="거래내용이 있으면 입력해주세요.">${message}</textarea>
          </div>
        </div>

        <div class="row form-group">
          <div class="col-md-8 mt-2">
            <input type="button" value="거래" onclick="submitForm()" class="btn btn-primary text-white form-control">
          </div>
        </div>
      </form>
    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
  <script>
      function submitForm() {
          let message = document.getElementById('message').value;
          var form = document.getElementById('withdrawForm');
          if (message.length == 0) {
              document.getElementById('message').value = '창구 출금 ';
          }
          form.submit();
      }

      function checkPwd() {
          var form = document.getElementById('withdrawForm');

          var isCheck = document.createElement('input');
          isCheck.setAttribute('type', 'text');
          isCheck.setAttribute('name', 'isCheck');
          if (document.getElementById('acc_password').value.trim() <= 3) {
              alert("비밀번호 4자리를 모두 입력해주세요.")
              return;
          }
          isCheck.setAttribute('value', 'check');
          form.appendChild(isCheck);
          form.submit();
          var isCheckInput = form.elements['isCheck']; // name이 'valid_rrn'인 요소 가져오기
          if (isCheckInput) {
              isCheckInput.parentNode.removeChild(isCheckInput); // 요소 삭제
              form.removeAttribute('isCheck');
          }
      }
  </script>
</body>
</html>
