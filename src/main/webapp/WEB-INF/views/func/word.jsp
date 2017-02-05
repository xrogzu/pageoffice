<%--
  Created by IntelliJ IDEA.
  User: Chung Junbin
  Date: 2017/1/16 10:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="po" uri="http://java.pageoffice.cn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Word文档</title>
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

<div class="centerContainer" style="width: 100%;height: 100%;">
    <po:PageOfficeCtrl id="wordCtrl"/>
</div>

<script type="text/javascript">

    //设置 word 文档全屏
    function fullScreen() {
        document.getElementById("wordCtrl").FullScreen = true;
    }

    //取消全屏
    function cancelFullScreen() {
        document.getElementById("wordCtrl").FullScreen = false;
    }

    //保存 word
    function saveFile() {
        document.getElementById("wordCtrl").WebSave();

        var msg = document.getElementById("wordCtrl").CustomSaveResult;

        alert(msg);
    }

</script>
</body>
</html>
