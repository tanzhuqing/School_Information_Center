<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Database" %>
<%@ page import="xlh.Global" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="../js/tree/lTREE.js"></script>

<SCRIPT language=javascript>
function current()
{
  var trsreplace = '';//替换内容
  document.getElementById('Menu' + trsreplace).style.display = "block";
}
function allhidden()
{
  var intLocality = 2;//层总数
  while (intLocality > 0)
  {
    document.getElementById('Menu' + intLocality).style.display = "none";
    intLocality = intLocality - 1;
  }
}
function OpenMenu(MenuID)
{  
  var obj = document.getElementById('Menu' + MenuID); 
  if(obj.style.display == "none") 
  { 
    allhidden();
    obj.style.display = "block";//没有展开的标题
    //document.getElementById('Menu3').style.display = "none";
  } 
  else 
  {
    obj.style.display = "none"; 
  }
} 
</SCRIPT>
<div id="center_left">
      <table border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td valign="top" class="leftMenuOff" id="mname_m1" onclick="changeMenu('m1','3');">新闻发布</td>
        </tr>
        <tr id="menu_m1">
          <td>
          	<ul class="subleftMenu">
            <li><a href="fz_newsadd.jsp" target="right">新闻发布</a></li>
          </ul>
         </td>
        </tr>

      </table>
    </div>