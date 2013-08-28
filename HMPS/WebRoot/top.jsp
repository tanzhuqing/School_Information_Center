<%--
  User: Yingtao.Q
  Date: 11-8-29
  Time: 上午11:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>移动校园门户管理平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }
        -->
    </style>
    <link rel="StyleSheet" href="${ctx}/css/mainstyle.css" type="text/css"/>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="141">
    <tr>
        <td width="379" align="left"><img src="${ctx}/images/main_01.jpg" width="379" height="141" /></td>
        <td align="center" background="${ctx}/images/main_02.jpg"><img src="${ctx}/images/main_04.jpg" width="356" height="141" /></td>
        <td width="389" height="141" align="right" valign="bottom" background="${ctx}/images/main_06.jpg">
            <table width="389" border="0" cellspacing="0" cellpadding="0" height="88">
                <tr>
                    <td height="70">&nbsp;</td>
                </tr>
                <tr>
                    <td height="18">
                        <table width="389" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="76">&nbsp;</td>
                                <td width="84" align="left" class="t1">您好：${userName}</td>
                                <td width="65" align="left" class="t1"><strong>| </strong><a
                                        href="${ctx}/system/userAction_editPUserPassword.action?id=${userID}"
                                        target="main">修改密码</a></td>
                                <td width="164" align="left" class="t1"><a
                                        href="${ctx}/system/systemAction_logout.action" target="_parent">退出系统</a></td>
                            </tr>
                            <tr><td height="22"></td></tr>
                        </table>                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>