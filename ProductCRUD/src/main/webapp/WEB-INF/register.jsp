<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 8/11/2023
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <jsp:include page="../layout/head.jsp"></jsp:include>

</head>
<body>
<div style="max-width: 50%" class="container">
    <form action="" method="post">
        <h2 class="text-center">Register</h2>
        <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" placeholder="example@gmail.com" class="form-control" id="email" name="email" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" placeholder="**********" class="form-control" name="password" id="password">
        </div>
        <div class="mb-3">
            <label for="repassword" class="form-label">Password</label>
            <input type="password" placeholder="**********" class="form-control" name="repassword" id="repassword">
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Remember me</label>
        </div>
        <button type="submit" class="btn btn-primary" name="action" value="LOGIN">Register</button>
        <p>Bạn đã có tài khoản ? <a href="<%=request.getContextPath()%>/HomeController?action=LOGIN">Đăng nhập</a></p>
    </form>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>

</body>
</html>
