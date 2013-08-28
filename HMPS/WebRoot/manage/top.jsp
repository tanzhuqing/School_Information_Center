<%@ page contentType="text/html;charset=gb2312" %>
<%
String path = request.getContextPath();
%>
<div id="top">
  <div id="logo"><img src="images/logo.jpg" width="364" height="67" /></div>
  <div id="top_right">
    <div id="dlxx">当前用户：admin 【<a href="#">修改密码</a> | <a href="../../logout.jsp">退出登录</a>】</div>
    <div id="menu">
    <dl>
    <dt><a href="<%=path%>/manage/index/index.jsp">首页</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="#">站点管理</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="<%=path%>/manage/news/index.jsp">新闻管理</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="#">校园美景</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="#">空闲教室</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="#">常用电话</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="#">系统管理</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    </dl>
    </div>
  </div>
</div>