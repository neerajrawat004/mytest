<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
This is the main body of the file
<form:form method="post"  action="formsubmit" modelAttribute="formdata">
<div style="width:30%"> Enter the name
<form:input type="text" path="name" placeholder="enter complete name" maxlength="30" style="float:right"/></div>
<br>
<div style="width:30%">Enter email-id
<form:input type="email" placeholder="emailid" path="email" maxlength="40" style="float:right"/></div><br>
<input type="submit">
</form:form>
</body>
</html>