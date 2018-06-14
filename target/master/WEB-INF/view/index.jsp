<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<%--<script src="../js/test.js"/>--%>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>

<body>
<h2>User Info</h2>
<p>登录账号</p>
用户名： ${user.userName}</br>
密码： ${user.userPassword}</br>


<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/test.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
       // alert(1123);
       // da();
    });
</script>
</body>
</html>