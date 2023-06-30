<%@ page import="hana.teamfour.addminhana.DTO.CustomerDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="hana.teamfour.addminhana.DTO.CustomerDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chaedongim
  Date: 2023/07/01
  Time: 4:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
  List<CustomerDTO> customerList = new ArrayList<>();
  List<CustomerDTO> tempList = (List<CustomerDTO>) request.getAttribute("customerList");
  if (tempList != null) {
    customerList = tempList;
  }
  Integer customerCount = (Integer) request.getAttribute("customerCount");
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
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/customerList.css">
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <title>Admin Hana - customerList.jsp</title>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="common/navbar.jsp" %>
    </nav>

    <main>
      <div class="card m-4">
        <div class="card-header">
          <svg class="svg-inline--fa fa-table me-1" aria-hidden="true" focusable="false" data-prefix="fas"
               data-icon="table" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg="">
            <path fill="currentColor"
                  d="M64 256V160H224v96H64zm0 64H224v96H64V320zm224 96V320H448v96H288zM448 256H288V160H448v96zM64 32C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64H448c35.3 0 64-28.7 64-64V96c0-35.3-28.7-64-64-64H64z"></path>
          </svg>
          가입 손님 리스트
        </div>

        <div class="card-body">
          <div class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
            <div class="datatable-top">
              <div class="datatable-dropdown">
                <label>
                  <select class="datatable-selector">
                    미구현
                    <option value="5">5</option>
                    <option value="10" selected="">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                  </select> entries per page
                </label>
              </div>
              <div class="datatable-search">
                미구현
                <input class="datatable-input" placeholder="Search..." type="search" title="Search within table"
                       aria-controls="datatablesSimple">
              </div>
            </div>
            <div class="datatable-container">
              <table id="datatablesSimple" class="datatable-table">
                <thead>
                <tr>
                  <th data-sortable="true">
                    <a href="#" class="datatable-sorter">손님 id</a></th>
                  <th data-sortable="true">
                    <a href="#" class="datatable-sorter">이름</a>
                  </th>
                  <th data-sortable="true">
                    <a href="#" class="datatable-sorter">주민등록 번호</a>
                  </th>
                  <th data-sortable="true">
                    <a href="#" class="datatable-sorter">성별</a></th>
                  <th data-sortable="true">
                    <a href="#" class="datatable-sorter">주소</a></th>
                  <th data-sortable="true">
                    <a href="#" class="datatable-sorter">휴대폰 번호</a>
                  </th>
                  <th data-sortable="true">
                    <a href="#" class="datatable-sorter">직업</a>
                  </th>
                  <th data-sortable="true">
                    <a href="#" class="datatable-sorter">추천 직원 id</a>
                  </th>
                </tr>
                </thead>
                <tbody>
                <%
                  for (int i = 0; i < customerList.size(); i++) {
                %>
                <tr data-index="<%=i%>">
                  <td><%=customerList.get(i).getC_id()%>
                  </td>
                  <td><%=customerList.get(i).getC_name()%>
                  </td>
                  <td><%=customerList.get(i).getC_rrn()%>
                  </td>
                  <td><%=customerList.get(i).getC_gender()%>
                  </td>
                  <td><%=customerList.get(i).getC_address()%>
                  </td>
                  <td><%=customerList.get(i).getC_mobile()%>
                  </td>
                  <td><%=customerList.get(i).getC_job()%>
                  </td>
                  <td><%=customerList.get(i).getE_id()%>
                  </td>
                    <%
                  }
                %>
                </tbody>
              </table>
            </div>
            <div class="datatable-bottom">
              <div class="datatable-info">Showing <%=customerList.size()%> Customer of <%=customerCount%> Customers
              </div>
              <nav class="datatable-pagination">
                <ul class="datatable-pagination-list">
                  <li class="datatable-pagination-list-item datatable-hidden datatable-disabled">
                    <a data-page="1" class="datatable-pagination-list-item-link">‹</a>
                  </li>
                  <li class="datatable-pagination-list-item datatable-active">
                    <a data-page="1" class="datatable-pagination-list-item-link">1</a>
                  </li>
                  <li class="datatable-pagination-list-item">
                    <a data-page="2" class="datatable-pagination-list-item-link">2</a>
                  </li>
                  <li class="datatable-pagination-list-item">
                    <a data-page="3" class="datatable-pagination-list-item-link">3</a>
                  </li>
                  <li class="datatable-pagination-list-item">
                    <a data-page="4" class="datatable-pagination-list-item-link">4</a>
                  </li>
                  <li class="datatable-pagination-list-item">
                    <a data-page="5" class="datatable-pagination-list-item-link">5</a>
                  </li>
                  <li class="datatable-pagination-list-item">
                    <a data-page="6" class="datatable-pagination-list-item-link">6</a>
                  </li>
                  <li class="datatable-pagination-list-item">
                    <a data-page="2" class="datatable-pagination-list-item-link">›</a>
                  </li>
                </ul>
              </nav>
            </div>
          </div>
        </div>
      </div>

    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
</body>
</html>
