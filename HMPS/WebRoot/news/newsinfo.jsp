<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="cn.heu.hmps.core.dao.NewPublicDao"%>
<%
	String NewsID=request.getParameter("id");
	
	NewPublicDao dao=new NewPublicDao();
	dao.creatDBconn();
	HashMap stmap=(HashMap)dao.getMap_new("select * from heu_news where NewsID='"+NewsID+"'");
	dao.closeDBConn();
	
	String nr="";
	String title="";
	
	if(!stmap.isEmpty()){
		title=stmap.get("NewsTitle").toString();
		nr=stmap.get("NewsContent").toString();
		nr=nr.replaceAll("src=\"HeuNewsPic/", "src=\"http://news.color5164.com/HeuNewsPic/");
	}
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,user-scalable=no">
    <title>Heu Mobile Portal</title>

    <style type="text/css" rel="stylesheet">
        body {
            margin: 15px auto;
        }
        p {
            margin: 15px 15px 15px 15px;
        }
        img{MAX-WIDTH: 100%!important;HEIGHT: auto!important;width:expression(this.width > 100 ? "100px" : this.width)!important;}
    </style>

</head>

<body>
<div data-role="page" id="jiguanPage">

    <P><%=title%></P>
	<%=nr%>
</div>


</body>
</html>
