<%@ page contentType="text/html;charset=gbk"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.heu.hmps.core.dao.NewPublicDao"%>

<%
//新闻列表

String href = "http://125.223.113.81:8080/";
NewPublicDao dao=new NewPublicDao();
dao.creatDBconn();
ArrayList list=dao.getList("select NewsID,NewsPicUrl,NewsTitle,NewsAuthor,NewsDate from heu_news order by NewsID desc limit 0,8");
%>
{"newslist":[
<%for (int i = 0; i < list.size(); i++) {
	HashMap stmap=(HashMap)list.get(i);
if(i>0){
out.print(",");
}
%>
{
 "ID":"<%=stmap.get("NewsID")%>",
 "Title":"<%=stmap.get("NewsTitle")%>",
 "Fbr":"<%=stmap.get("NewsAuthor")%>",
 "Fbrq":"<%=stmap.get("NewsDate")%>",
 "ImgUrl":"<%=stmap.get("NewsPicUrl")%>"
 }
 <%}%>
 ]}
<%
dao.closeDBConn();
%>