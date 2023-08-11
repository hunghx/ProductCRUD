<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 8/11/2023
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav justify-content-end p-4 me-5">
  <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="#">Home</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">Product</a>
  </li>
  <c:choose>
    <c:when test="${not empty user_login}">
      <img style="width: 50px ; height: 50px;border-radius: 50%;object-fit: cover" src="<%=request.getContextPath()%>/image/${user_login.avatar}" alt="">
      <span>${user_login.email}</span>
      <a href="<%=request.getContextPath()%>/HomeController?action=LOGOUT">LogOut</a>
    </c:when>
    <c:otherwise>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/HomeController?action=LOGIN">Login</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/HomeController?action=REGISTER">Register</a>
      </li>
    </c:otherwise>
  </c:choose>

</ul>