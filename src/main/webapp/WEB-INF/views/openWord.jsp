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
    <po:PageOfficeCtrl id="wordDoc"/>
    <input type="hidden" name="docName" value="${docName}"/>
    <input type="hidden" name="docPath" value="${docPath}">
</div>

<script type="text/javascript">

    //设置 word 文档全屏
    function fullScreen() {
        document.getElementById("wordDoc").FullScreen = true;
    }

    //取消全屏
    function cancelFullScreen() {
        document.getElementById("wordDoc").FullScreen = false;
    }

    //保存 word
    function saveFile() {
        document.getElementById("wordDoc").WebSave();
    }

    // 页面打开之后执行该方法
    function afterOpened() {
        document.getElementById("wordDoc").SetEnableFileCommand(4, false); //禁止另存
        document.getElementById("wordDoc").SetEnableFileCommand(5, false); //禁止打印
        document.getElementById("wordDoc").SetEnableFileCommand(6, false); //禁止页面设置
        document.getElementById("wordDoc").SetEnableFileCommand(8, false); //禁止打印预览
    }

</script>
</body>
</html>
