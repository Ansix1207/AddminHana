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
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="common/navbar.jsp" %>
    </nav>
    <main>
      <form action="입금" class="p-5 bg-white" id="depositForm" method="post">
        <h2 class="h4 text-black mb-5">입금</h2>
        <div class="row form-group">
          <div class="col-md-8">
            <label class="text-black" for="counterpart_id">입금 계좌</label>
            <input type="text" id="counterpart_id" name="counterpart_id" class="form-control">
          </div>
        </div>
        <div class="row form-group">
          <div class="col-md-8">
            <label class="text-black" for="t_amount">거래금액</label>
            <input type="number" id="t_amount" name="t_amount" class="form-control">
          </div>
        </div>

        <div class="row form-group">
          <div class="col-md-8">
            <label class="text-black" for="message">거래내용</label>
            <textarea name="message" id="message" cols="20" rows="7" class="form-control"
                      placeholder="거래내용이 있으면 입력해주세요."></textarea>
          </div>
        </div>

        <div class="row form-group">
          <div class="col-md-8 mt-2">
            <input type="button" id="btn" name="btn" value="거래" onclick="submitForm()"
                   class="btn btn-primary text-white form-control">
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
          var form = document.getElementById('depositForm');
          if (message.length == 0) {
              document.getElementById('message').value = '창구 입금 ';
          }
          form.submit();
      }
  </script>
</body>
</html>
