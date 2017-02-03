<%--
  Created by IntelliJ IDEA.
  User: Chung Junbin
  Date: 2017/2/3 16:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="po" uri="http://java.pageoffice.cn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Excel在线浏览V3</title>
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

<%--<a id="href" href="${link}">点击</a>--%>
<a id="href" href="${link}"></a>

<script type="text/javascript">

    window.onload = function (event) {
        document.getElementById("href").click();
    }

</script>
</body>
</html>
