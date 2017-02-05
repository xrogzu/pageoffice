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
    <meta charset="UTF-8">
    <title>数鹏通运维知识库</title>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/login-css.css" rel="stylesheet"/>
    <script type="text/javascript">
        function getCookie(c_name) {
            if (document.cookie.length > 0) {
                c_start = document.cookie.indexOf(c_name + "=");
                if (c_start != -1) {
                    c_start = c_start + c_name.length + 1;
                    c_end = document.cookie.indexOf(";", c_start);
                    if (c_end == -1) c_end = document.cookie.length;
                    return unescape(document.cookie.substring(c_start, c_end));
                }
            }
            return "";
        }
        function oncheckuser() {
            var username = document.securityForm.j_username.value;
            var password = document.securityForm.password.value;
            if (username == "") {
                document.getElementById('message').innerHTML = "<font color='red'>用户不能为空.</font>";
                return;
            } else if (password == "") {
                document.getElementById('message').innerHTML = "<font color='red'>密码不能为空.</font>";
                return;
            } else if (getPsdWrongNumber() === 3) {
                document.getElementById('message').innerHTML = "<font color='red'>密码错误3次,已被锁定.</font>";
                return;
            } else {
                var userandpass = trim(username) + trim(password);
                document.securityForm.j_password.value = userandpass;
                document.securityForm.submit();
            }
        }
        function getPsdWrongNumber() {
            return null;
        }

        //删除左右两端的空格
        function trim(str) {
            return str.replace(/(^\s*)|(\s*$)/g, "");
        }

        function keyEvenSubmit(e) {
            var key = window.event ? e.keyCode : e.which;
            if (key == 13) {
                oncheckuser();
            }
        }

        function keyEvenFocus(e) {
            var key = window.event ? e.keyCode : e.which;
            if (key == 13 && trim(document.securityForm.j_username.value)) {
                document.securityForm.password.focus();
            }
        }
        window.onload = function () {
            // form 表单中已经指明 action 了，这里不需要导向默认的首页了
            //            document.securityForm.action = "index.html";
            var username = document.securityForm.j_username.value;
            if (username == "") {
                document.securityForm.j_username.focus();
            } else {
                document.securityForm.password.focus();
            }
            var theme = getCookie('linkcm_omc');
            document.securityForm.j_theme.value = theme;

        }
    </script>
</head>
<body>

<div class="login-box">
    <div class="login-table">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/resources/img/login/logo.png"/>
        </div>
        <div class="login-left-box">
            <div class="login-ban">
                <h1>运维
                    <small>信息</small>
                </h1>
                <h1>数据
                    <small>安全</small>
                </h1>
            </div>
        </div>
        <div class="login-right-box">
            <div class="login-title">欢迎登录</div>
            <form id="securityForm" name="securityForm" action="${pageContext.request.contextPath}/login" method="post">
                <table width="100%" height="120">
                    <tr height="40">
                        <td align="left">
                            <input type="hidden" id="j_theme" name="j_theme"/>

                            <div class="user input">
                                <label>用户名</label>
                                <input type='text' onkeypress="keyEvenFocus(event)" id='j_username' name='j_username'
                                       class="required"/>
                            </div>
                        </td>
                    </tr>
                    <tr height="40">
                        <td align="left">
                            <input type="hidden" id="j_password" name="j_password" value=""/>

                            <div class="password input">
                                <label>密&nbsp;&nbsp;&nbsp;码</label>
                                <input onkeypress="keyEvenSubmit(event)" type='password' id='password' name='password'
                                       class="required"/>
                            </div>
                            <div id="message"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>

                        </td>
                    </tr>
                    <tr height="40">
                        <td style="text-align: center;">
                            <input type="button" onkeypress="keyEvenSubmit(this)" value="登录" class="login-button1"
                                   onclick="oncheckuser();"/>
                        </td>
                    </tr>

                </table>
            </form>
        </div>
        <div class="footer">
            <span>广东省生态中心</span>
            <span>技术支持：数鹏通（LinkCM）科技</span>
            <span>企业QQ800：2853098227</span>
            <span>值班电话：18818401760</span>
        </div>
    </div>
</div>

<c:if test="${not empty requestScope.errorMsg}">
    <script type="text/javascript">
        document.getElementById('message').innerHTML = "${errorMsg}";
    </script>
</c:if>

</body>
</html>
