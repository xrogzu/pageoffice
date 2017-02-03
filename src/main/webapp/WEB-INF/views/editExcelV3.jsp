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

<%-- 建议将 div 改成 form --%>
<span style="font-size: 24px;align-content: center;color: blue;">${username}</span>
<div class="officeContainer" style="width: 100%;height: 100%;">
    <po:PageOfficeCtrl id="excelCtrl"/>
    <input type="hidden" name="docName" value="${docName}"/>
    <input type="hidden" name="docPath" value="${docPath}">
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

    // 保存
    function saveFile() {
        document.getElementById("excelCtrl").WebSave();
    }

</script>
</body>
</html>
