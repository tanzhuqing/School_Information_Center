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


    function check_fields(){
        var folder= $("[name='password']").val();
        var username= $("[name='loginName']").val();
        if($.trim(username)==""){
            alert("账号不能为空！！！");
            return false;
        }
        else if($.trim(folder)==""){
            alert("密码不能为空！！！");
            return false;
        }

    }
</script>
</head>
<body>


	<div data-role="page" id="login">
		<div data-role="header" data-theme="b">
			<h1>校园移动门户</h1>
			<!-- <a href="#login" data-icon="grid" data-theme="a" class="ui-btn-right" data-rel="dialog" data-transition="flip">登录</a> -->
		</div>


		<div data-role="content">
			<form action="systemAction_login.doo" method="post" id="form" data-ajax="false">
				<div>
					<label for="loginName">用户名：</label> <input
						type="text" width="60px" id="loginName" name="loginName" value="">
				</div>
				
				<div>
					<label for="password">密 码：</label> <input
						type="password" width="60px" id="password" name="password"
						value="">
					<!-- <input type="password" id="password" name="password" 
					value="password" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
					style="color:#999999"> -->
				</div>
				
				<div data-inline='true' class="ui-block-a">
					<input type="submit" value="提交" onclick="return check_fields();">
				</div>

			</form>
		</div>

	</div>


</body>
</html>
