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
    <title>${loginUser.doc.docName}</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css"/>
</head>
<body>

<%-- 建议将 div 改成 form --%>
<span style="font-size: 24px;align-content: center;color: blue;float: right;">
    <%--欢迎您！${loginUser.nickname}--%>
    <a href="${pageContext.request.contextPath}/logout">登出</a></span>
<div class="officeContainer" style="width: 100%;height: 100%;">
    <po:PageOfficeCtrl id="excelCtrl"/>
</div>

<script type="text/javascript">

    // 全屏
    function fullScreen() {
        document.getElementById("excelCtrl").FullScreen = true;
    }

    // 取消全屏
    function cancelFullScreen() {
        document.getElementById("excelCtrl").FullScreen = false;
    }

</script>
</body>
</html>
