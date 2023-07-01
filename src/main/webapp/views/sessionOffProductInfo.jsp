<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 하나로H012
  Date: 2023-06-19
  Time: 오후 5:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page import="hana.teamfour.addminhana.entity.ProductEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hana.teamfour.addminhana.DTO.ProductDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
  ArrayList<ProductDTO> productDTOs = (ArrayList<ProductDTO>) request.getAttribute("productDTOs");

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
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/sessionOffProductInfo.css">
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/base.css ">
  <title>금융상품조회</title>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="common/navbar.jsp" %>
    </nav>
    <main>
      <form action="loaninquery" method="GET">
        <div class="input-group">
          <input class="form-control" type="text" name="q" value="${param.q}" aria-describedby="btnNavbarSearch"/>
          <input class="btn2 btn-sunghee btn-search" type="submit" value="검색"/>
        </div>
      </form>
      <%--            그래프 넣기   --%>
      <div>
        <canvas id="myChart"></canvas>
        <%

          Map<String, Integer> accountCountMap = (Map<String, Integer>) request.getAttribute("accountCountMap");
          // accountCountMap을 사용하여 필요한 작업 수행
          // 예: 특정 카테고리의 계좌 개수 출력
          Integer count1 = accountCountMap.get("보통예금");
          Integer count2 = accountCountMap.get("정기예금");
          Integer count3 = accountCountMap.get("자유적금");
          Integer count4 = accountCountMap.get("정기적금");
          Integer count5 = accountCountMap.get("신용대출");
          Integer count6 = accountCountMap.get("담보대출");

        %>
      </div>

      <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

      <script>
          const ctx = document.getElementById('myChart');
          new Chart(ctx, {
              type: 'bar',
              data: {
                  labels: ['보통예금', '정기예금', '자유적금', '${param.q}' + '상품', '정기적금', '신용대출', '담보대출'],
                  datasets: [{
                      label: '분류별 금융상품개수',
                      data: [<%= count1 %>, <%= count2 %>, <%= count3 %>, ${count}, <%= count4 %>, <%= count5 %>, <%= count6 %>],
                      borderWidth: 1
                  }]
              },
              options: {
                  scales: {
                      y: {
                          beginAtZero: true
                      }
                  }
              }
          });
      </script>
      <div class="list1">
        <div class="card-header">
          <h4> &nbsp 추천 금융상품 리스트</h4>
          <h3> &nbsp ${count}개의 상품이 검색되었습니다</h3>
        </div>
        <ol class="list-group list-group-numbered" id="pages">
          <%
            for (int i = 0; i < productDTOs.size(); i++) {
          %>
          <li class="productItem list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto ">
              <div class="fw-bold">
                <div>
                  <h4><span><%=productDTOs.get(i).getP_name()%></span></h4>
                  <span>금리 <%=productDTOs.get(i).getP_interestrate()%> %</span><br>
                  <span><%=productDTOs.get(i).getP_description()%></span>
                </div>
              </div>
            </div>
            <%
              if (request.getSession().getAttribute("customerSession") != null) {%>
            <%--            세션이 있을 때는 가입 버튼이 나올수 있게 해두었다--%>
            <a href=<%=contextPath%>/customer/loanjoin>
              <button class="modify">가입</button>
            </a>
            <%}%>
          </li>
          <%
            }
          %>
        </ol>
        <div class=center-content>
          <%--        페이지네이션--%>
          <c:set var="page" value="${(param.p == null)?1:param.p}"/>
          <c:set var="startNum" value="${page-(page-1)%5}"/>
          <c:set var="lastNum" value="${Math.floor(count/5)}"/>
          <%--          이전 페이지--%>
          <ul class="-list- center">
            <c:if test="${startNum>1}">
              <a class="btn-next" href="?p=${startNum-5}&q=${param.q}"> <</a>
            </c:if>
            <c:if test="${startNum<=1}">
              <span class="btn-next" onclick="alert('디음페이지가 없습니다')"><</span>
            </c:if>
            <%--페이지 번호--%>
            <c:forEach var="i" begin="0" end="4">
              <li class="pagination">
                <a class="orange bold" style="text-decoration: none;"
                   href="?p=${startNum+i}&q=${param.q}"> ${startNum+i}
                </a>
              </li>
            </c:forEach>
            <%--          다음 페이지--%>
            <c:if test="${startNum+5<lastNum}">
              <a href="?p=${startNum+5}&q=${param.q}" class="btn-next"> > </a>
            </c:if>
            <c:if test="${startNum+5>=lastNum}">
              <span class="btn-next" onclick="alert('디음페이지가 없습니다')">> </span>
            </c:if>
          </ul>
        </div>
      </div>
    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
</body>
</html>
