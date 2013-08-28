<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>校园移动门户</title>

<link href="./jquery-mobile/jquery.mobile-1.0.1.css" rel="stylesheet"
	type="text/css" />
<script src="./jquery-mobile/jquery-1.7.1.js" type="text/javascript"></script>
<script src="./jquery-mobile/jquery.mobile-1.0.1.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).bind("mobileinit", function() {
		$.mobile.page.prototype.options.addBackBtn = true;
	});
</script>
<%
	String userName = (String)session.getAttribute("userName");
	if (userName == null){
		System.out.println("userName is null");
	} else {
		System.out.println(userName);
	}
 %>
</head>
<body>
	<div data-role="page" id="home">
		<div data-role="header" data-theme="b">
			<h1>校园移动门户</h1>

		</div>
		<div data-role="content" data-theme="c">
			<div class="ui-grid-b">
				<a href="#news_page" class="ui-block-a" target="_blank">
					<div align="center">
						<img alt="" src="icons/bat.ico" width="90" height="90">

						<p>新闻</p>
					</div> </a> <a
					href="http://sso.hrbeu.edu.cn/cas/login?service=http://opac.hrbeu.edu.cn:8080/reader/redr_info.php"
					class="ui-block-b" target="_blank">
					<div align="center">
						<img alt="" src="icons/broom.ico" width="90" height="90">

						<p>图书馆</p>
					</div> </a> <a
					href="http://sso.hrbeu.edu.cn/cas/login?service=http://cwcx.hrbeu.edu.cn/wingsoft/main.jsp"
					target="_blank" class="ui-block-c">
					<div align="center">
						<img alt="" src="icons/candle.ico" width="90" height="90">

						<p>财务系统</p>
					</div> </a>
			</div>
			<div class="ui-grid-b">
				<a href="#education_ifo" class="ui-block-a" target="_blank">
					<div align="center">
						<img alt="" src="icons/castle.ico" width="90" height="90">

						<p>教务信息</p>
					</div> </a> <a
					href="http://sso.hrbeu.edu.cn/cas/login?service=http://mail.hrbeu.edu.cn/?q=base"
					target="_blank" class="ui-block-b">
					<div align="center">
						<img alt="" src="icons/cat.ico" width="90" height="90">

						<p>邮件</p>
					</div> </a> <a
					href="http://sso.hrbeu.edu.cn/cas/login?service=http://ecard.hrbeu.edu.cn/"
					rel="external" class="ui-block-c" target="_blank">
					<div align="center">
						<img alt="" src="icons/Pumpkin.ico" width="90" height="90">

						<p>一卡通</p>
					</div> </a>
			</div>
			<div class="ui-grid-b">
				<a href="department.html" rel="external" class="ui-block-a"
					target="_blank">
					<div align="center">
						<img alt="" src="icons/web.ico" width="90" height="90">

						<p>黄页</p>
					</div> </a>
				<!-- <a href="#campus_map" class="ui-block-b" target="_blank">
                <div align="center">
                    <img alt="" src="icons/witchsoup.ico" width="90" height="90">

                    <p>校园地图</p>
                </div>
            </a> -->


					<a href="http://sso.hrbeu.edu.cn/cas/logout" class="ui-block-b">
						<div align="center">
							<img alt="" src="icons/witchsoup.ico" width="90" height="90">

							<!-- <p>校园地图</p> -->
							<p>登出</p>
						</div> 
					</a>
					
			</div>
		</div>
		<div data-role="footer" data-theme="c" class="footer-text"
			align="center">

			<small>&copy;2012 网络信息中心</small>
		</div>

	</div>





</body>
</html>
