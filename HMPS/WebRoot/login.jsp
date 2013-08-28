<%--
  User: Yingtao.Q
  Date: 12-3-23
  Time: 下午5:32
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>移动校园门户管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<script type="text/javascript">
	function chanageImg() 
	{
		var img = document.getElementById("imgVerify");
		img.src = "<%=request.getContextPath()%>/checknumber";
	}
</script>

</head>

<body bgcolor="#F2F2F2">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="270">&nbsp;</td>
				</tr>
				<tr>
					<td><div style="background-image:url(${ctx}/images/Login_bg_002.jpg); width:100%; background-repeat:repeat-x;" align="center">
							<div style="background-image:url(${ctx}/images/Login_bg_001.jpg); background-position:center; background-repeat:no-repeat; height:350px;">
								<table width="400" border="0" cellspacing="0" cellpadding="0">
									<form action="${ctx}/system/systemAction_login.action" method="post">
										<tr>
											<td width="80" height="103">&nbsp;</td>
											<td>&nbsp;</td>
											<td width="115">&nbsp;</td>
										</tr>
										<tr>
											<td rowspan="2">&nbsp;</td>
											<td rowspan="2" align="left">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="57"><input type="text" name="loginName"
															id="loginName"
															style="border:0px; width:150px; font-size:16px; font-family:'黑体';" />
														</td>
													</tr>
													<tr>
														<td height="57"><input type="password"
															name="password" id="password"
															style="border:0px; width:150px; font-size:14px; font-family:'黑体';" />
														</td>
													</tr>
													<tr>
														<td height="57"><input name="checkNumber" type="text"
															id="verifycode"
															style="border:0px; width:150px; font-size:16px; font-family:'黑体';"
															maxlength="6" />
														</td>
													</tr>
												</table></td>
											<td height="57" align="center" valign="bottom"><input
												type="image" src="${ctx}/images/Login_btn_003.jpg" value=""
												style="width:115px; height:105px; border:0px;" />
											</td>
										</tr>
										<tr>
											<td height="57" align="center">
												<a href="${ctx}/login.jsp" onclick="chanageImg()" title="看不清楚，换一张。">
												<img width="90" height="24" src="<%=request.getContextPath()%>/checknumber" id="imgVerify" border="0" /></a>
											</td>
										</tr>
									</form>
								</table>
							</div>
						</div></td>
				</tr>
			</table>
</body>
</html>