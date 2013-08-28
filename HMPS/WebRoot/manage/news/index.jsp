<%@ page contentType="text/html;charset=gb2312" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>网站管理系统</title>
</SCRIPT>
<script src="common.js"></script>
<link href="css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<jsp:include page="../top.jsp" />
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="203" valign="top">
    	<jsp:include page="left.jsp" />
    	</td>
    <td valign="top"><div id="center_right">
      <div id="right_top">
        <div id="right_top_left"><img src="images/kuang_top_left.jpg" width="4" height="28" /></div>
        <span><img src="images/coin_jt.gif" /></span><span>当前位置：新闻管理</span>
        <div id="right_top_right"><img src="images/kuang_top_right.jpg" width="4" height="28" /></div>
      </div>
      
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="if_kuang">
          <tr>
            <td>
            <div id="right_main">
            <iframe id="_MainArea" name="right" frameborder="0" width="100%" height="442" src="right.jsp" scrolling="auto" allowtransparency="true"></iframe>
            </div>
            </td>
          </tr>
        </table>
      
    </div>
    	
    	
    	</td>
  </tr>
</table>
<div id="foot">版权信息</div>
</body>
</html>