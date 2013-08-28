<%--
  User: Yingtao.Q
  Date: 11-9-3
  Time: 下午7:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>新闻管理</title>
    <%@ include file="/common/meta.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <LINK href="${ctx}/css/font.css" type=text/css rel=stylesheet>
    <link href="${ctx}/css/mainstyle.css" type="text/css" rel="StyleSheet"/>
    <link href="${ctx}/js/validator/css/jquery.validate.css" type="text/css" rel="stylesheet"/>
    <script src="${ctx}/js/jquery.js" type="text/javascript"></script>
    <script src="${ctx}/js/validator/jquery.validate.js" type="text/javascript"></script>
    <script src="${ctx}/js/validator/messages_cn.js" type="text/javascript"></script>
    <script>
        $(document).ready(function() {
            //聚焦第一个输入框
            $("#noticeType").focus();
            //为inputForm注册validate函数
            $("#inputForm").validate({
                rules: {
                    loginName: {
                        <%--remote: "user!checkLoginName.action?oldLoginName=" + encodeURIComponent('${loginName}')--%>
                    },
                    passwordConfirm: {
                        equalTo:"#password"
                    },
                    checkedRoleIds:"required"
                },
                messages: {
                    passwordConfirm: {
                        equalTo: "输入与上面相同的密码"
                    }
                }
            });
        });
    </script>
</head>
<body>
<s:if test="id == 0">
<form id="inputForm" action="${ctx}/news/newsAction_addNews.action" method="post" enctype="multipart/form-data">
    </s:if>
    <s:else>
    <form id="inputForm" action="${ctx}/news/newsAction_editNews.action" method="post">
        <input type="hidden" name="news.id" value="<s:property value='news.id'/> "/>
        </s:else>

        <table width="100%" border="0" cellspacing="0" cellpadding="0">

            <tr>
                <td height="15" align="left" valign="top">&nbsp;</td>
            </tr>
            <tr>
                <td height="17" align="center">
                    <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr align="left" valign="middle">
                            <td width="3%" height="16">
                                <img height="16" src="${ctx}/images/arrow_11.gif" width="16"/></td>
                            <td width="17%" height="16" align="center" class="font3">
                                <strong>新闻管理 </strong></td>
                            <td height="16" align="right" valign="top">
                                <table width="100%" height="14" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="4%" height="14" align="right"><img src="${ctx}/images/arrow_12.gif" width="30" height="14"/></td>
                                        <td width="96%" height="14">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                              <td height="14" align="left" valign="top"  bgcolor="#D6D6D6"></td>
                                           </tr>
                                         </table>
                                      </td>
                                    </tr>
                              </table>
                            </td>
                        </tr>
                  </table>
                    <table width="13%" cellspacing="0" cellpadding="0" border="0" align="center">
                        <tr>
                            <td width="12%" height="40" valign="middle">
                                <img width="18" height="17" src="${ctx}/images/open.gif">
                            </td>
                            <td width="88%" align="center" valign="middle">
                                添加新闻
                            </td>
                        </tr>
                    </table>

                    <table width="96%" cellpadding="0" cellspacing="1" class="tab5">
                        <caption>&nbsp;
                        </caption>
                        <tr>
                            <th width="30%" align="left">标题：</th>
                            <td width="70%" align="left">
                                <input type="text" class="input_box1 required" name="news.title" id="title"
                                       value="<s:property value='news.title'/>"
                                       size="50"/></td>
                        </tr>
                        
                        <tr>
                            <th width="30%" align="left">作者：</th>
                            <td width="70%" align="left">
                                <input type="text" class="input_box1 required" name="news.author" id="author"
                                       value="<s:property value='news.author'/>"
                                       size="50"/></td>
                        </tr>
                        <tr>
                            <th width="30%" align="left">日期：</th>
                            <td width="70%" align="left">
                                <input type="text" class="input_box1 required" name="news.date" id="date"
                                       value="<s:property value='news.date'/>"
                                       size="50"/></td>
                        </tr>
                        <tr>
                            <th width="30%" align="left">图片：</th>
                            <td width="70%" align="left">
                                <input type="file"  name="image" /></td>

                        </tr>
                        <tr>
                            <th width="30%" align="left">图片：</th>
                            <td width="70%" align="left">
                                <input type="file"  name="image" /></td>

                        </tr>
                        <tr>
                            <th width="30%" align="left">图片：</th>
                            <td width="70%" align="left">
                                <input type="file"  name="image" /></td>

                        </tr>
                        
     
                        <tr>
                            <td colspan="2" align="center"><input name="submit" type="submit" onclick="" value="提交 "/>&nbsp;&nbsp;&nbsp;&nbsp;<input
                                    type="button" onclick="history.back()" value="返回 "/></td>
                        </tr>

                    </table>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
