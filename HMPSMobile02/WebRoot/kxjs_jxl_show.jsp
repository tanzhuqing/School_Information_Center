<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*" %>

<%
Vector v = new Vector();
Properties p = null;
int i = 0;

//*******************************测试数据开始
p = new Properties();
p.setProperty("id", "1");
p.setProperty("name", "第1教学楼");
p.setProperty("count", "32");
v.add(p);

p = new Properties();
p.setProperty("id", "2");
p.setProperty("name", "第2教学楼");
p.setProperty("count", "42");
v.add(p);

p = new Properties();
p.setProperty("id", "3");
p.setProperty("name", "第3教学楼");
p.setProperty("count", "52");
v.add(p);

p = new Properties();
p.setProperty("id", "4");
p.setProperty("name", "第4教学楼");
p.setProperty("count", "62");
v.add(p);

p = new Properties();
p.setProperty("id", "5");
p.setProperty("name", "第5教学楼");
p.setProperty("count", "12");
v.add(p);

p = new Properties();
p.setProperty("id", "6");
p.setProperty("name", "第6教学楼");
p.setProperty("count", "22");
v.add(p);
//***************************************测试数据结束
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>空闲教室----教学楼</title>
    <link href="./jquery-mobile/jquery.mobile-1.0.1.css" rel="stylesheet" type="text/css"/>
    <script src="./jquery-mobile/jquery-1.7.1.js" type="text/javascript"></script>
    <script src="./department.js" type="text/javascript"></script>
    <script src="./jquery-mobile/jquery.mobile-1.0.1.js" type="text/javascript"></script>
</head>
<body>
<div data-role="page" id="home">
	<div data-role="header" data-theme="b"><a href="index.html" target="_self" data-role="button" data-icon="back">主页</a>
        <h1>空闲教室----教学楼</h1>
    </div>
    <div data-role="content" data-theme="c">
        <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
           <%for (i = 0; i < v.size(); i++)
             {%>
	   <li><a href="kxjs_js_show.jsp?jxl=<%=(((Properties) v.get(i)).getProperty("id"))%>&period=0" target="_self" data-transition="pop">☆ <%=(((Properties) v.get(i)).getProperty("name"))%> <span class="ui-li-count">总共<%=(((Properties) v.get(i)).getProperty("count"))%>间</span></a></li>
           <%}%>
        </ul>
    	</div>
        <div data-role="footer" data-theme="c" class="footer-text" align="center">
        	<br>
            <br>
            <small>&copy;2012 网络信息中心</small>
        </div>
    
</div>

</div>
	             
</body>	
</html>
