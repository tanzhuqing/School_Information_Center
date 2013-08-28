<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
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
	
	
	function chanageImg() 
	{
		var img = document.getElementById("imgVerify");
		img.src = "<%=request.getContextPath()%>/checknumber";
	}
	
	function check_fields(){
        var folder= $("[name='password']").val();
        var username= $("[name='loginName']").val();
        var verifycode = $("[name='verifycode']").val();
        if($.trim(username)==""){
        	alert("账号不能为空！！！");
        	return false;
        }
        else if($.trim(folder)==""){
            alert("密码不能为空！！！");
            return false;
        }
        else if($.trim(verifycode)==""){
        	alert("验证码不能为空！！！");
        	return false;
        }
        
    }
	
    
</script>
</head>
<body>
	<div data-role="page" id="login">
		<div data-role="header" data-theme="b">
			<h1>校园移动门户</h1>
		</div>


		<div data-role="content" valign="center">
			<form action="" method="get" id="form" data-ajax="false">
				<div>
					<label for="loginName">用户名：</label> <input type="text" width="60px" id="loginName" name="loginName" value="">
				</div>
				
				<div>
					<label for="password">密 码：</label> 
					<input type="password" width="60px" id="password" name="password" value="">
				</div>
				
				<div>
					<label for="verifycode">验证码：</label> <input type="text" width="60px" id="verifycode" name="verifycode" value="">
				</div>
				<br>
				<div>
					<img width="90" height="24" src="<%=request.getContextPath()%>/checknumber" id="imgVerify" border="0" onclick="this.src=this.src+'?'" style="cursor:pointer;"/>
					<label>点击图片刷新</label>
				</div>
				<br>
				<div data-inline='true' class="ui-block-a">
					<input type="submit" value="登陆" onclick="return check_fields();">
				</div>
			</form>
		</div>

	</div>
</body>
</html>