<%--
  Created by IntelliJ IDEA.
  User: Chung Junbin
  Date: 2017/2/4 11:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>文档正被编辑</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css"/>
</head>
<body>

<div>
    <div>
        ${editor} 正在编辑 ${loginUser.doc.docName} 文档，请选择：
        <div>
            <a href="${pageContext.request.contextPath}/index/read/only">以只读的方式打开该文档</a><br/>
            <a href="${pageContext.request.contextPath}/index/edit">重新尝试以写方式打开</a><br/>
            <a href="javascript:window.opener=null;window.open('','_self');window.close();">停止请求并关闭当前页面</a>
        </div>
    </div>
</div>

<script type="text/javascript">
</script>
</body>
</html>
