<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="${contextPath}/static/common/js/jquery-1.4.4.min.js"></script>
<script src="${contextPath}/static/page/js/common.js"></script>
<script src="${contextPath}/static/page/js/login.js"></script>
<script type="text/javascript">
	var contextPath = '${contextPath}';
</script>
</head>
<body>
<form id="formData">
${msg}
<hr>
<div>user name:</div><input type="text" name="username" />
<hr>
<div>password:</div><input type="password" name="password" />
</form>
<hr>
<button onclick="loginIn()">登录</button>
</body>
</html>