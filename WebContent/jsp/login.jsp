<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<strong>验证码:</strong>&nbsp;&nbsp;
<input type="text" class="form-control" required name="codevalidate" style="width: 100px;display: inline">
 <img id="codevalidate" src="${pageContext.request.contextPath }/valid" width="90" height="20" style="margin-left: 10px" onclick="changeUrl()">

</body>
</html>