<%@ page contentType="text/html;charset=gb2312" %>
<%
String path = request.getContextPath();
%>
<div id="top">
  <div id="logo"><img src="images/logo.jpg" width="364" height="67" /></div>
  <div id="top_right">
    <div id="dlxx">��ǰ�û���admin ��<a href="#">�޸�����</a> | <a href="../../logout.jsp">�˳���¼</a>��</div>
    <div id="menu">
    <dl>
    <dt><a href="<%=path%>/manage/index/index.jsp">��ҳ</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="#">վ�����</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="<%=path%>/manage/news/index.jsp">���Ź���</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="#">У԰����</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="#">���н���</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="#">���õ绰</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    <dt><a href="#">ϵͳ����</a></dt>
    <dd><img src="images/menu_line.jpg" /></dd>
    </dl>
    </div>
  </div>
</div>