<%@ page import="java.util.ArrayList" %>
<%@ page import="hana.teamfour.addminhana.entity.ProductEntity" %>
<%@ page import="hana.teamfour.addminhana.DTO.CustomerSummaryDTO" %>
<%@ page import="hana.teamfour.addminhana.DTO.AccountDTO" %>
<%@ page import="hana.teamfour.addminhana.DTO.AssetDTO" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
Created by IntelliJ IDEA.
User: jiyou
Date: 2023-06-17
Time: 오후 7:01
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();

  // 손님 정보
  CustomerSummaryDTO customerSummaryDTO = (CustomerSummaryDTO) request.getAttribute("customerSummaryDTO");
  String customerName = customerSummaryDTO.getC_name();
  Integer ageRange = ((int) customerSummaryDTO.getC_age() / 10) * 10;
  String gender = customerSummaryDTO.getC_gender() == 'M' ? "남성" : "여성";
  String job = customerSummaryDTO.getC_job();

  // 계좌 정보
  ArrayList<AccountDTO> accountDTO = (ArrayList<AccountDTO>) request.getAttribute("accountDTO");

  // 계좌 카테고리 (예금/적금/대출)
  String category = request.getAttribute("category").toString();

  // 자산 정보
  AssetDTO assetDTO = (AssetDTO) request.getAttribute("assetDTO");
  Integer[] accountBalance = assetDTO.getBalance_sum();
  Integer asset = 0;
  String[] assetCategory;
  String balance = "잔액";

  if (category.equals("예금")) {
    assetCategory = new String[] {"보통", "정기"};
    if (assetDTO.getAss_deposit() != null) {
        asset = assetDTO.getAss_deposit();
    }
  } else if (category.equals("적금")) {
    assetCategory = new String[] {"자유", "정기"};
    if (assetDTO.getAss_savings() != null) {
        asset = assetDTO.getAss_savings();
    }
  } else {
    assetCategory = new String[] {"신용", "담보"};
    if (assetDTO.getAss_loan() != null) {
        asset = assetDTO.getAss_loan();
    }
    balance = "대출잔액";
  }

  // 추천 상품
  ArrayList<ProductEntity> recByAge = (ArrayList<ProductEntity>) request.getAttribute("recByAge");
  ArrayList<ProductEntity> recByGender = (ArrayList<ProductEntity>) request.getAttribute("recByGender");
  ArrayList<ProductEntity> recByJob = (ArrayList<ProductEntity>) request.getAttribute("recByJob");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Hana - info</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"/>
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" type="text/css" href="<%=contextPath%>/resources/css/base.css">
  <link rel="stylesheet" type="text/css" href="<%=contextPath%>/resources/css/nav.css">
  <link rel="stylesheet" type="text/css" href="<%=contextPath%>/resources/css/sessionOnAccInfo.css">
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div class="wrap">
  <nav id="layoutSidenav_nav">
  <%@ include file="common/navbar.jsp" %>
  </nav>
  <main class="grid grid-cols-2 w-full">
    <div class="col-span-1 p-4 h-full">
      <div class="card statisticsSituation h-full">
        <div class="card-body">
          <span class="componentTitle"><%=customerName%> 님의 <%=category%> 현황</span>
          <div class="mb-4 assetInfo">
            <h5 class="card-title mt-3 mb-2">자산 정보</h5>
            <p><span>총 <%=category%>액</span> <span class="card-text">₩ <%=asset%></span></p>
            <div class="statisticsChart">
              <%-- 손님의 대출 자산 현황 그래프 --%>
              <canvas id="assetChart"></canvas>
            </div>
          </div>
          <div class="signedupProduct">
            <h5 class="card-title mt-3 mb-2">가입 상품</h5>
            <%-- 가입된 상품 리스트 --%>
            <ul>
              <%
                for (AccountDTO account : accountDTO) {
              %>
              <li>
                <div class="productName"><%=account.getAcc_pname()%></div>
                <span>만기일 <%=account.getAcc_maturitydate()%></span>
                <span>이자율 <%=account.getAcc_interestrate()%>%</span>
                <span><%=balance%> <%=account.getAcc_balance()%>원</span>
              </li>
              <%
                }
              %>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="col-span-1 p-4 h-full">
      <div class="recommendProduct card h-full">
        <div class="card-body">
          <span class="componentTitle">추천 <%=category%> 상품</span>
          <div>
            <%-- 추천 대출 상품 리스트 --%>
            <ul class="recommendList">
              <li class="recommendTitle mt-3 mb-2"><%=ageRange%>대가 가장 많이 가입한</li>
              <%
                for (int i = 0; i < recByAge.size(); i++) {
              %>
              <li>
                <div class="productName"><%=recByAge.get(i).getP_name()%></div>
                <span>이자율 <%=recByAge.get(i).getP_interestrate()%>%</span>
              </li>
              <%
                }
              %>
            </ul>
            <ul class="recommendList">
              <li class="recommendTitle mt-3 mb-2"><%=gender%>이 가장 많이 가입한</li>
              <%
                for (int i = 0; i < recByGender.size(); i++) {
              %>
              <li>
                <div class="productName"><%=recByGender.get(i).getP_name()%></div>
                <span>이자율 <%=recByGender.get(i).getP_interestrate()%>%</span>
              </li>
              <%
                }
              %>
            </ul>
            <ul class="recommendList">
              <li class="recommendTitle mt-3 mb-2"><%=job%> 손님을 위한</li>
              <%
                for (int i = 0; i < recByJob.size(); i++) {
              %>
              <li>
                <div class="productName"><%=recByJob.get(i).getP_name()%></div>
                <span>이자율 <%=recByJob.get(i).getP_interestrate()%>%</span>
              </li>
              <%
                }
              %>
            </ul>
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
    // 자산이 없을 때 빈 도넛 차트를 그리기 위한 플러그인
    const plugin = {
        id: 'emptyDoughnut',
        afterDraw(chart, args, options) {
            const {datasets} = chart.data;
            const {color, width, radiusDecrease} = options;
            let hasData = false;

            for (let i = 0; i < datasets.length; i += 1) {
                const dataset = datasets[i];
                for (let j=0; j < dataset.data.length; j += 1) {
                    hasData |= (dataset.data[j] != 0);
                }
            }

            if (!hasData) {
                const {chartArea: {left, top, right, bottom}, ctx} = chart;
                const centerX = (left + right) / 2;
                const centerY = (top + bottom) / 2;
                const r = Math.min(right - left, bottom - top) / 2;

                ctx.beginPath();
                ctx.lineWidth = width || 2;
                ctx.strokeStyle = color || '#BF5AD8';
                ctx.arc(centerX, centerY, (r - radiusDecrease || 0), 0, 2 * Math.PI);
                ctx.stroke();
            }
        }
    };

    data = {
        datasets: [{
            backgroundColor: ['#BF5AD8','#9E37D1'],
            data: [<%=accountBalance[0]%>, <%=accountBalance[1]%>]
        }],
        // 라벨의 이름이 툴팁처럼 마우스가 근처에 오면 나타남
        labels: ['<%=assetCategory[0]%>', '<%=assetCategory[1]%>']
    };

    // 도넛형 차트
    var ctx = document.getElementById("assetChart");
    var myDoughnutChart = new Chart(ctx, {
        type: 'doughnut',
        data: data,
        options: {
            plugins: {
                legend: {
                    position: 'right',
                    labels: {
                        font: {
                            size: 15
                        }

                    },
                },
                emptyDoughnut: {
                    color: '#BF5AD8',
                    width: 1,
                    radiusDecrease: 20
                }
            }

        },
        plugins: [plugin]
    });
</script>
</body>
</html>
