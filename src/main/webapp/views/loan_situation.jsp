<%--
  Created by IntelliJ IDEA.
  User: 하나로H017
  Date: 2023-06-15
  Time: 오후 5:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Title</title>
  <link rel="stylesheet" href="../resources/css/reset.css">
  <link rel="stylesheet" href="../resources/css/nav.css">
  <link href="../resources/css/loan_situation.css" rel="stylesheet">
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous"
  />
  <script
          src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
          crossorigin="anonymous"
  ></script>
</head>
<body>
<div class="wrap">
  <%--<%@ include file="common/nav.jsp" %>--%>
  <main>
    <div class="container">
      <div class="row">
        <div class="col">
          <div class="statistics_situation card">
            <div class="card-body">
              <h1 class="card-title"><%-- 손님 이름 --%>님의 대출 현황</h1>
              <div class="asset_info">
                <h3>자산 정보</h3>
                <p><span>총 대출액</span> <span>₩<%-- 손님의 대출 자산 --%></span></p>
                <div class="statistics_graph">
                  <%-- 손님의 대출 자산 현황 그래프 --%>
                  <div class="chartWrap">
                    <div class="chart">
                      <div class="chart-bar" data-deg="50"></div>
                      <div class="chart-bar" data-deg="95"></div>
                      <div class="chart-bar" data-deg="200"></div>
                      <div class="chart-bar" data-deg="15"></div>
                    </div>
                  </div>
                </div>
              </div>
                <div class="signedup_product">
                  <h3>가입 상품</h3>
                  <div><%-- 가입된 상품 리스트 --%></div>
                </div>
              </div>
            </div>
            <div class="search_box">
              <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
            </div>
          </div>
          <div class="col">
            <div class="recommend_product card">
              <div class="card-body">
                <h1 class="card-title">추천 대출 상품</h1>
                <h3>추천</h3>
                <div>
                  <%-- 추천 대출 상품 리스트 --%>
                </div>
              </div>
            </div>
          </div>
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
