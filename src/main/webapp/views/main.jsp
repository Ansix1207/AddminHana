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

    <main class="d-flex flex-column justify-content-center gap-2 mx-4">
      <form name="customerLoginForm" action="profile" method="post"
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
      <div class="input-group input-group-lg flex-nowrap w-75">
          <span class="input-group-text" id="addon-wrapping2">
            <i class="fas fa-magnifying-glass"></i>
          </span>
        <input
                id="searchProduct"
                type="text"
                class="form-control"
                placeholder="상품 검색"
                aria-label="Username"
                aria-describedby="addon-wrapping2"
        />
        <datalist id="product">
          <option value="급여하나 월복리 적금">급여하나 월복리 적금</option>
          <option value="하나 아이키움 적금">하나 아이키움 적금</option>
          <option value="(내맘) 적금">(내맘) 적금</option>
          <option value="주거래하나 월복리 적금">주거래하나 월복리 적금</option>
          <option value="연금하나 월복리 적금">연금하나 월복리 적금</option>
          <option value="펫사랑 적금">펫사랑 적금</option>
          <option value="하나 장병내일준비 적금">하나 장병내일준비 적금</option>
          <option value="내집마련 더블업(Double-Up)적금">
            내집마련 더블업(Double-Up)적금
          </option>
          <option value="주택청약종합저축">주택청약종합저축</option>
          <option value="희망저축계좌">희망저축계좌</option>

          <option value="평생 군인 적금">평생 군인 적금</option>
          <option value="자유적금">자유적금</option>
          <option value="함께하는 사랑 적금">함께하는 사랑 적금</option>
          <option value="하나 미소드림 적금">하나 미소드림 적금</option>
          <option value="행복나눔 적금">행복나눔 적금</option>
          <option value="369 정기예금">369 정기예금</option>
          <option value="고단위 플러스">고단위 플러스</option>
          <option value="행복knowhow 연금예금">행복knowhow 연금예금</option>
          <option value="머니박스 통장">머니박스 통장</option>
          <option value="급여하나 통장">급여하나 통장</option>

          <option value="주거래 하나 통장">주거래 하나 통장</option>
          <option value="영하나플러스 통장">영하나플러스 통장</option>
          <option value="사업자 주거래 우대통장">사업자 주거래 우대통장</option>
          <option value="연금 하나 통장">연금 하나 통장</option>
          <option value="행복나눔 통장">행복나눔 통장</option>
          <option value="수퍼플러스">수퍼플러스</option>
          <option value="보통예금">보통예금</option>
          <option value="프리미엄 직장인론">프리미엄 직장인론</option>
          <option value="BEST 신용대출">BEST 신용대출</option>
          <option value="공무원클럽대출">공무원클럽대출</option>

          <option value="의료인 클럽대출">의료인 클럽대출</option>
          <option value="닥터클럽대출 - 골드">닥터클럽대출 - 골드</option>
          <option value="전문직 클럽대출">전문직 클럽대출</option>
          <option value="로이어클럽대출">로이어클럽대출</option>
          <option value="하나 수의사 클럽대출">하나 수의사 클럽대출</option>
          <option value="하나 새희망홀씨">하나 새희망홀씨</option>
          <option value="하나 사잇돌 중금리대출">하나 사잇돌 중금리대출</option>
          <option value="햇살론15">햇살론15</option>
          <option value="AI대출">AI대출</option>
          <option value="행복연금대출">행복연금대출</option>

          <option value="공무원가계자금대출">공무원가계자금대출</option>
          <option value="하나 징검다리론">하나 징검다리론</option>
          <option value="주택담보대출">주택담보대출</option>
          <option value="예금담보대출">예금담보대출</option>
          <option value="하나햇살론뱅크">하나햇살론뱅크</option>
          <option value="EV오토론">EV오토론</option>
          <option value="1Q오토론">1Q오토론</option>
          <option value="하나 아파트론">하나 아파트론</option>
          <option value="하나 월상환액 고정형 모기지론">
            하나 월상환액 고정형 모기지론
          </option>
          <option value="하나 내집연금 연계 대출">하나 내집연금 연계 대출</option>
        </datalist>
      </div>
      <div class="alert alert-secondary w-75" role="alert">
        <h4 class="alert-heading">가이드</h4>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatem
          et magni quas ducimus quisquam tempore error qui. Veniam accusamus
          neque hic blanditiis culpa corporis animi, ratione quis? Odio, velit
          placeat?
        </p>
        <hr/>
        <p class="mb-0">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatem
          et magni quas ducimus quisquam tempore error qui. Veniam accusamus
          neque hic blanditiis culpa corporis animi, ratione quis? Odio, velit
          placeat?
        </p>
        <hr/>
        <p class="mb-0">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatem
          et magni quas ducimus quisquam tempore error qui. Veniam accusamus
          neque hic blanditiis culpa corporis animi, ratione quis? Odio, velit
          placeat?
        </p>
        <hr/>
        <p class="mb-0">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatem
          et magni quas ducimus quisquam tempore error qui. Veniam accusamus
          neque hic blanditiis culpa corporis animi, ratione quis? Odio, velit
          placeat?
        </p>
        <hr/>
        <p class="mb-0">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatem
          et magni quas ducimus quisquam tempore error qui. Veniam accusamus
          neque hic blanditiis culpa corporis animi, ratione quis? Odio, velit
          placeat?
        </p>
      </div>
    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
  <script src="<%=contextPath%>/resources/js/main.js"></script>
</body>
</html>
