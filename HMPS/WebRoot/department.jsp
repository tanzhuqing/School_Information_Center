<%--
  User: Yingtao.Q
  Date: 12-3-24
  Time: 下午7:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,user-scalable=no">
    <title>Heu Mobile Portal</title>
    <link href="jquery-mobile/jquery.mobile-1.0.1.min.css" rel="stylesheet" type="text/css"/>
    <script src="jquery-mobile/jquery-1.7.1.js" type="text/javascript"></script>
    <script src="department.js" type="text/javascript"></script>
    <script src="jquery-mobile/jquery.mobile-1.0.1.min.js" type="text/javascript"></script>


</head>

<body>
<div data-role="page" id="jiguan">

    <div data-role="header">
        <h1>电话号码</h1>
        <a href='javascript:refresh();' data-icon="refresh" id="jiguanFresh" data-theme="c" class="ui-btn-left"
           data-iconpos="notext"></a>
    </div>

    <div data-role="content">
        <p id="jiguanConsole"></p>
        <ul data-role="list-view" data-inset="true" id="jiguanList">
        </ul>
    </div>

    <div data-role="footer" data-position="fixed" data-id="toolbar">
        <div data-role="navbar">
            <ul>
                <li><a class="ui-btn-active" href="#jiguan" data-icon="home" data-transition="fade">机关部门</a></li>
                <li><a href="#yuanxi" data-icon="grid" data-transition="fade">教学院系</a></li>
                <li><a href="#zhishu" data-icon="info" data-transition="fade">直属单位</a></li>
            </ul>
        </div>
    </div>

</div>


</body>
</html>