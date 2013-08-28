<%--
  User: Yingtao.Q
  Date: 11-9-3
  Time: 下午5:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>校园信息门户</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <LINK href="${ctx}/css/font.css" type=text/css rel=stylesheet>
    <LINK rel="StyleSheet" href="${ctx}/css/mainstyle.css" type="text/css"/>
    <script src="${ctx}/js/jquery.js" type="text/javascript"></script>
    <script src="${ctx}/js/table.js" type="text/javascript"></script>
</head>
<body>
<form id="mainForm" action="${ctx}/news/newsAction_listNews.action" method="post">
    <input type="hidden" name="pagesofSchool.pageNo" id="pageNo" value="${pagesofSchool.pageNo}"/>

    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="left" valign="top" height="15"></td>
        </tr>
        <tr>
            <td height="17" align="center" valign="top">
                <table width="96%" border="0" align="center" cellpadding="0"
                       cellspacing="0">
                    <tr align="left" valign="middle">
                        <td width="3%">
                            <img height="16" src="${ctx}/images/arrow_11.gif" width="16"/></td>
                        <td width="17%" align="center" class="font3">
                            <strong>新闻管理 </strong></td>
                        <td valign="middle">
                            <table width="100%" height="14" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td width="4%" align="right"><img src="${ctx}/images/arrow_12.gif" width="30"
                                                                      height="14"/></td>
                                    <td bgcolor="#D6D6D6" width="96%"></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
  <table width="90%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" valign="top" >  <div id="message"  style="color:#FF0000;"><s:actionmessage/></div></td>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="11%" cellspacing="0" cellpadding="0" border="0" align="center">
                    <tr>
                        <td width="12%" height="40" align="right" valign="middle">
                            <img width="18" height="17" src="${ctx}/images/open.gif">
                        </td>
                        <td width="88%" align="center" valign="middle">
                            新闻管理
                        </td>
                    </tr>
                    
                </table></td>
  </tr>
</table>              

                <table width="100%">
                    <tr>
                        <td>
                            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0"
                                   class="biankuang1">
                                <tr>
                                    <td width="80%" height="10" bgcolor="#F2F2F2"></td>
                                </tr>
                                <tr>
                                    <td height="6" bgcolor="#F2F2F2"></td>
                                </tr>
                            </table>
                            <table width="96%" border="0" align="center" cellpadding="0"
                                   cellspacing="0">
                                <tr>
                                    <td height="10"></td>
                                </tr>
                            </table>


                            <table width="100%">
                                <tr>
                                    <td>
                                        <table width="96%" border="0" align="center" cellpadding="0"
                                               cellspacing="0">
                                            <tr>
                                                <td width="3%" height="25">
                                                <span><img src="${ctx}/images/aucclass.gif" width="15"
                                                           height="15"/> </span>
                                                </td>
                                                <td width="48%" align="left">新闻管理</td>
                                                <td width="49%" align="left">
                                                    <div align="right"><a
                                                            href="${ctx}/news/newsAction_addPNews.action">添加新闻</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>

                                        <table width="96%" border="0" align="center" cellpadding="1" cellspacing="1"
                                               class="tabmain">
                                            <tbody>
                                            <tr>
                                                <td align="center" valign="top">
                                                    <table width="100%" border="0" cellspacing="0" cellpadding="0"
                                                           id="tab3">
                                                        <tr>
                                                            <td width="10%" height="28" align="center" class="title">
                                                                                                                                                                                选择
                                                            </td>

                                                            <td width="15%" align="center" class="title">标题</td>
                                                            <td width="9%" align="center" class="title">日期</td>
                                                            <td width="10%" align="center" class="title">作者</td>
                                                            <td width="17%" align="center" class="title">操作</td>
                                                        </tr>
                                                        <s:iterator value="newsList">
                                                            <tr>
                                                                <td height="25" align="center"><input type="checkbox"
                                                                                                      name="ids"
                                                                                                      value="${id}"/>
                                                                </td>
                                                        
                                                                <td align="center">${title}&nbsp;</td>
                                                                <td align="center">${date}&nbsp;</td>
                                                                <td align="center">${author}&nbsp;</td>

                                                                <td align="center">&nbsp;
                                                                    <a href="${ctx}/baseData/baseDataAction_editPSchool?id=${id}">修改</a>&nbsp;
                                                                    <a href="#" onclick="deleteItem('${ctx}/baseData/baseDataAction_deleteSchool?id=${id}');">删除</a>
                                                                </td>
                                                            </tr>
                                                        </s:iterator>
                                                        <tr>
                                                            <td height="25" colspan="7" align="center">&nbsp;</td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr bgcolor="#F2F2F2" height="25">
                                                <td height="14" colspan="10">

                                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                        <tr>
                                                            <td width="25%" align="left"><input type="checkbox"
                                                                                                name="nids"
                                                                                                value="0"
                                                                                                onclick='chkall("mainForm", this, "ids")'/>
                                                                全选<img src="${ctx}/images/delete.jpg" width="13"
                                                                       height="13"><a href="#"
                                                                                      onclick="deleteItems('ids', '${ctx}/baseData/baseDataAction_deleteMoreSchools');">删除 </a>
                                                            </td>
                                                            <td width="30%">&nbsp;
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </table>

                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<script language="javascript">

    change("tab3", "#F5F3E7", "#FAFAFA", "#FFFFD7");

</script>
</body>
</html>
