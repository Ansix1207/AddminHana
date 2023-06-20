<%@ page import="hana.teamfour.addminhana.DTO.CustomerSummaryDTO" %><%-- Created by IntelliJ IDEA. User: chaedongim Date: 2023/06/19 Time: 9:26 AM To change this template use File |
Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
  CustomerSummaryDTO customerSummaryDTO = (CustomerSummaryDTO) request.getAttribute("customerSummaryDTO");

%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous"
  />
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/nav.css"/>
  <title>Addmin Hana - profile</title>
  <style>
    main {
      padding: 3rem;
    }

    .card {
      margin: 1rem;
      width: 100%;
      box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.5);
    }

    .card-title {
      font-weight: 500;
    }

    .profileSummary {
    }

    .summaryCustomerName {
      font-size: 1.25rem;
      margin-right: 1rem;
      font-weight: bold;
    }
  </style>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="common/navbar.jsp" %>
    </nav>
    <main class="grid grid-cols-12 w-full">
      <div class="col-span-8 p-4">
        <div class="card profileSummary">
          <div class="card-body">
            <h5 class="card-title">Profile</h5>
            <h6 class="card-subtitle mb-2">
              <span class="summaryCustomerName">${customerSummaryDTO.c_name}O O O 손님</span>
              <span>${customerSummaryDTO.c_age}만 35세 / ${customerSummaryDTO.c_gender}여 / ${customerSummaryDTO.c_job}직장인</span>
            </h6>
            <p class="card-text">880528 - 2******</p>
          </div>
        </div>
        <div class="card profileSummary">
          <div class="card-body">
            <h5 class="card-title">Profile</h5>
            <h6 class="card-subtitle mb-2"><span class="summaryCustomerName">O O O 손님</span><span>만 35세 / 여 / 직장인</span>
            </h6>
            <p class="card-text">880528 - 2******</p>
          </div>
        </div>
        <div class="card profileSummary">
          <div class="card-body">
            <h5 class="card-title">Profile</h5>
            <h6 class="card-subtitle mb-2"><span class="summaryCustomerName">O O O 손님</span><span>만 35세 / 여 / 직장인</span>
            </h6>
            <p class="card-text">880528 - 2******</p>
          </div>
        </div>

        <div class="card assetSummary">
          <div class="card-body">
            <h5 class="card-title">Profile</h5>
            <h6 class="card-subtitle mb-2">Card subtitle</h6>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's
              content.</p>
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
          </div>
        </div>

        <div class="card customerSpecial">
          <div class="card-body">
            <h5 class="card-title">자산 정보</h5>
            <h6 class="card-subtitle mb-2"><span>총 자산</span><span>원 52,115,623</span></h6>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's
              content.</p>
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
          </div>
        </div>
      </div>

      <div class="col-span-4 p-4">
        <div class="card recommendationProducts">
          <div class="card-body">
            <h5 class="card-title">Profile</h5>
            <h6 class="card-subtitle mb-2">Card subtitle</h6>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's
              content.</p>
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
          </div>
        </div>
        <div class="card accountCalendar">
          <div class="card-body">
            <h5 class="card-title">Profile</h5>
            <h6 class="card-subtitle mb-2">Card subtitle</h6>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's
              content.</p>
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
          </div>
        </div>
        <div class="card customerLogout">
          <div class="card-body">
            <h5 class="card-title">Profile</h5>
            <h6 class="card-subtitle mb-2">Card subtitle</h6>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's
              content.</p>
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
          </div>
        </div>
      </div>
    </main>
  </div>
  <script
          src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"
  ></script>
</body>
</html>
