<%--
  User: Yingtao.Q
  Date: 12-3-14
  Time: 上午8:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script language="JavaScript" src="js/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="js/ICrypto.js" type="text/javascript"></script>
<html>
<head>
    <%--<script type="text/javascript">--%>
        <%--alert("okok");--%>
    <%--</script>--%>
</head>
<%--<%--%>
    <%--String user = "quyingtao";--%>
    <%--String password = "";--%>
    <%--String newAddress = "mail.hrbeu.edu.cn/?q=base";--%>

<%--%>--%>


<body>
<form id="casForm" method="post" action="mail.hrbeu.edu.cn/?q=base">
</form>
<script type="text/javascript">
    ICrypto_De("http://portal.hrbeu.edu.cn", "0Hyrgutf2ejB21XSbg60pg==");

    function sungard_login(v_) {
        jQuery.ajax({
            url: "",
            type: "POST",
            dataType: "html",
            cache: false,
            data: ({ user: 'quyingtao', password: v_, domain_name: 'hrbeu.edu.cn'
                //go: 'http://mail.hrbeu.edu.cn/user/?q=base', login_ssl: '0'
            }),
            beforeSend: function() {
//                window.location = "http://mail.hrbeu.edu.cn/mailLogin.php?sName=quyingtao&sPwd=0Hyrgutf2ejB21XSbg60pg==&loginType=js";              //&casAddress=http://sso.hrbeu.edu.cn/cas/login?service=http://mail.hrbeu.edu.cn/?q=base
            },
            complete: function() {
                window.location = "http://mail.hrbeu.edu.cn/?q=base";                     //   "http://www.hrbeu.edu.cn"
            },
            success: function(txt) {
                alert("ok");
                if (txt && txt.indexOf("您填写的用户名或密码错误") <= 0) {
                    var f = document.getElementById('casForm');
                    f.submit();
                } else {
                    alert("用户名或密码不正确!\n\n请在门户中修改邮件系统的帐户映射：\n    功能入口：门户导航栏-->帐号管理");
                    //location.href="/wingsoft/index.jsp";
                    self.opener = null;
                    self.close();
                }
            },
            error: function() {
            }
        });
    }
</script>







</body>
</html>