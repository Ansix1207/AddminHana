<%--
  Created by IntelliJ IDEA.
  User: chaedongim
  Date: 2023/06/22
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
  <div id="toastSuccess" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header">
      <img src="${pageContext.request.contextPath}/resources/images/png-transparent-circle-correct-mark-success-tick-yes-check-flat-actions-icon-thumbnail.png"
           class="rounded me-2" alt="success" width="20px" height="20px">
      <strong class="me-auto">${param.title}</strong>
      <small>${param.subtitle}</small>
      <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body">
      ${param.description}
    </div>
  </div>
</div>
