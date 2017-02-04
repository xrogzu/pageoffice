<%--
  Created by IntelliJ IDEA.
  User: Chung Junbin
  Date: 2017/2/4 10:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>登陆</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css"/>
</head>
<body>
<div id="errorMsg">
    <c:if test="${not empty errorMsg}">
        <span style="color:red; font-size: 24px;">${errorMsg}</span>
    </c:if>
</div>
<div id="loginContainer">
    <form id="loginForm" method="post">
        <p>
            <label>用户：
                <input type="text" name="username"/>
            </label>
        </p>
        <p>
            <label>密码：
                <input type="password" name="password"/>
            </label>
        </p>
        <p>
            <button type="submit">登陆</button>
            <button type="reset">重置</button>
        </p>
    </form>
</div>
</body>
</html>
