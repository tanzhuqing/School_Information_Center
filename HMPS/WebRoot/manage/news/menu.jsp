<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="../../config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>未名网站管理系统--模块管理</title>

<style type="text/css">
<!--
#cd ul {
	margin: 0px;
	padding: 0px;
	list-style-type: none;
}
#cd li {
	float: left;
	padding-right: 10px;
}
tabale {
	font-family: "宋体";
	font-size: 12px;
	line-height: 22px;
	color: #666666;
	text-decoration: none;
}
.text {
	font-family: "宋体";
	font-size: 12px;
	line-height: 25px;
	color: #666666;
	text-decoration: none;
}
body {
	margin: 0px;
	padding: 0px;
}
.tb_line1 {
	font-family: "宋体";
	font-size: 12px;
	line-height: 20px;
	font-weight: bold;
	color: #445055;
	text-decoration: none;
	background-image: url(../images/tb_bg.gif);
	background-repeat: repeat-x;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-right-style: solid;
	border-bottom-style: solid;
	border-right-color: #dddddd;
	border-bottom-color: #c3c3c3;
}
.bg {
	font-family: "宋体";
	font-size: 12px;
	line-height: 22px;
	font-weight: normal;
	color: #666666;
	text-decoration: none;
	border: 1px solid #c6c6c6;
}
.bg_nr {
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #dddddd;
}
.bg_nr a {
	font-family: "宋体";
	font-size: 12px;
	line-height: 22px;
	color: #666666;
	text-decoration: none;
}
.bg_nr a:hover {
	text-decoration: underline;
}
-->
</style>
</head>

<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="65%" style="padding:6px 10px;">
				<div id="cd" style="float: left"><ul><li><a href="#"><img src="../images/coin_xj.gif" border="0" /></a></li>
                <li><a href="#"><img src="../images/coin_bj.gif" border="0" /></a></li>
                <li><a href="#"><img src="../images/coin_dfb.gif" border="0" /></a></li>
                <li><a href="#"><img src="../images/coin_fb.gif" border="0" /></a></li>
                <li><a href="#"><img src="../images/coin_yl.gif" border="0" /></a></li>
                <li><a href="#"><img src="../images/coin_fz.gif" border="0" /></a></li>
                <li><a href="#"><img src="../images/coin_yy.gif" border="0" /></a></li>
                <li><a href="#"><img src="../images/coin_sc.gif" border="0" /></a></li>
                <li><a href="#"><img src="../images/coin_dr.gif" border="0" /></a></li>
				</ul>
				</div>				</td>
			</tr>
			<tr>
				<td style="padding:2px 10px;">
				<div class="text" style="float:left;">列出:
					<select>
					<option value="ALL" selected="true">所有文档</option>
					<option value="ADD">我创建的文档</option>
					<option value="WORKFLOW">流转中的文档</option>
					<option value="TOPUBLISH">待发布的文档</option>
					<option value="PUBLISHED">已发布的文档</option>
					<option value="OFFLINE">已下线的文档</option>
					<option value="ARCHIVE">已归档的文档</option>
                    <option value="Editer">所有编辑投稿</option>
                    <option value="Member">所有会员投稿</option>
					</select>
		   &nbsp;从 <input type="text" id="StartDate" style="width:90px;" > 至 <input type="text" id="EndDate"
					style="width:90px; "> &nbsp;关键词: <input
					name="Keyword" type="text" id="Keyword"> <input
					type="button" name="Submitbutton" id="Submitbutton" value="查询"></div>				</td>
			</tr>
			<tr>
				<td
					style="padding-top:2px;padding-left:6px;padding-right:6px;padding-bottom:2px;">
				
					<table width="100%" cellpadding="2" cellspacing="0" class="bg">
<tr>
							<td width="5%" height="20" class="tb_line1" drag="true">ID</td>
					  <td width="3%" height="20" class="tb_line1"><input type="checkbox" name="checkbox" id="checkbox" /></td>
			<td width="5%" class="tb_line1">属性</td>
		  <td width="48%" class="tb_line1" sortfield="title" direction="">标题</td>
		  <td width="7%" class="tb_line1">创建者</td>
		  <td width="17%" class="tb_line1">状态</td>
		  <td width="15%" class="tb_line1">发布时间</td>
					  </tr>
						<tr>
							<td class="bg_nr">1</td>
							<td class="bg_nr">
							  <input type="checkbox" name="checkbox2" id="checkbox2" />
							</td>
							<td class="bg_nr" algin="center"><img src="../images/icon1.gif" width="13" height="13" /></td>
							<td class="bg_nr" style="${TitleStyle}"><a href="#">标题名称</a></td>
							<td class="bg_nr">创建者</td>
							<td class="bg_nr">状态</td>
							<td class="bg_nr" title="${PublishDate}">发布时间</td>
						</tr>
                        <tr>
							<td class="bg_nr">1</td>
							<td class="bg_nr">
							  <input type="checkbox" name="checkbox2" id="checkbox2" />
							</td>
							<td class="bg_nr" algin="center"><img src="../images/icon1.gif" width="13" height="13" /></td>
							<td class="bg_nr" style="${TitleStyle}"><a href="#">标题名称</a></td>
							<td class="bg_nr">创建者</td>
							<td class="bg_nr">状态</td>
							<td class="bg_nr" title="${PublishDate}">发布时间</td>
						</tr>
                        <tr>
							<td class="bg_nr">1</td>
							<td class="bg_nr">
							  <input type="checkbox" name="checkbox2" id="checkbox2" />
							</td>
							<td class="bg_nr" algin="center"><img src="../images/icon1.gif" width="13" height="13" /></td>
							<td class="bg_nr" style="${TitleStyle}"><a href="#">标题名称</a></td>
							<td class="bg_nr">创建者</td>
							<td class="bg_nr">状态</td>
							<td class="bg_nr" title="${PublishDate}">发布时间</td>
						</tr>
                        <tr>
							<td class="bg_nr">1</td>
							<td class="bg_nr">
							  <input type="checkbox" name="checkbox2" id="checkbox2" />
							</td>
							<td class="bg_nr" algin="center"><img src="../images/icon1.gif" width="13" height="13" /></td>
							<td class="bg_nr" style="${TitleStyle}"><a href="#">标题名称</a></td>
							<td class="bg_nr">创建者</td>
							<td class="bg_nr">状态</td>
							<td class="bg_nr" title="${PublishDate}">发布时间</td>
						</tr>
                        <tr>
							<td class="bg_nr">1</td>
							<td class="bg_nr">
							  <input type="checkbox" name="checkbox2" id="checkbox2" />
							</td>
							<td class="bg_nr" algin="center"><img src="../images/icon1.gif" width="13" height="13" /></td>
							<td class="bg_nr" style="${TitleStyle}"><a href="#">标题名称</a></td>
							<td class="bg_nr">创建者</td>
							<td class="bg_nr">状态</td>
							<td class="bg_nr" title="${PublishDate}">发布时间</td>
						</tr>
                        <tr>
							<td class="bg_nr">1</td>
							<td class="bg_nr">
							  <input type="checkbox" name="checkbox2" id="checkbox2" />
							</td>
							<td class="bg_nr" algin="center"><img src="../images/icon1.gif" width="13" height="13" /></td>
							<td class="bg_nr" style="${TitleStyle}"><a href="#">标题名称</a></td>
							<td class="bg_nr">创建者</td>
							<td class="bg_nr">状态</td>
							<td class="bg_nr" title="${PublishDate}">发布时间</td>
						</tr>
					  <tr>
							<td colspan="9" align="left">分页信息</td>
					  </tr>
				  </table>
				</td>
			</tr>
		</table>
</body>
</html>
