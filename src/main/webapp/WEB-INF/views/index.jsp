<%--
  Created by IntelliJ IDEA.
  User: Zhong Junbin
  Date: 2017/2/3 20:54
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.zhuozhengsoft.pageoffice.PageOfficeLink" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        html, body {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>

<div>
    使用 PageOfficeLink 打开，必须在 JSP 中使用 PageOfficeLink 类直接生成 URL
    <br/>
    <%--
        // 下面这种方法添加了 contextPath，导致 contextPath 重复添加，因此必须去掉
        <a href="<%=PageOfficeLink.openWindow(request, request.getContextPath()
         + "/pol/open", "width:100%;height:100%;")%>">
    --%>
    <%-- 下面的方式才是正确的 --%>
    <%--<a href="<%=PageOfficeLink.openWindow(request, "/pol/open", "width:100%;height:100%;")%>">--%>
    <a href="${link}">
        POL 打开数据源.xlsx
    </a>
</div>

<script type="text/javascript">
</script>
</body>
</html>
