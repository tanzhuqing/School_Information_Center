<%--
  User: Yingtao.Q
  Date: 11-8-29
  Time: 上午11:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
    <link rel="StyleSheet" href="${ctx}/css/mainstyle.css" type="text/css"/>
    <link rel="StyleSheet" href="${ctx}/js/dtree/dtree.css" type="text/css"/>
    <script type="text/javascript" src="${ctx}/js/dtree/dtree.js"></script>
</head>
<body bgcolor="#dedfde">
<div align="center">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
        <tr>
            <td valign="top" background="${ctx}/images/bg-left.jpg" height="512">
                <table width="100%" height="59" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="9%" rowspan="2">&nbsp;</td>
                        <td width="82%" height="5"></td>
                        <td width="9%" rowspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="left" valign="top">
                            <div class="dtree">
                                <script type="text/javascript">
                                    <%--var menu = <%=(String) request.getSession().getAttribute("menu")%>;--%>
                                    <%--document.write(menu);--%>
                                    d = new dTree('d');

                                    d.add(0,-1,'系统菜单');
                                    d.add(1,0,'新闻管理','');
                                    d.add(2,0,'信息公告','');
                                    d.add(3,0,'电话管理','');
                                    d.add(4,0,'信息推送','');
                                    d.add(5,0,'空闲教室','');
                                    d.add(6,0,'学校概况','');
                                    d.add(7,0,'周边环境','');
                                    d.add(8,0,'系统管理','');
                                    
                                    d.add(10,7,'周边环境','ShowReport.wx?PAGEID=surroundingPage','', 'main');
                                    d.add(11,6,'学校简介','ShowReport.wx?PAGEID=tag_01','', 'main');
                                    d.add(12,6,'院系介绍','ShowReport.wx?PAGEID=tag_03','', 'main');
                                    d.add(13,6,'校园风光','ShowReport.wx?PAGEID=tag_04','', 'main');
                                    d.add(14,1,'新闻管理','ShowReport.wx?PAGEID=newslist','', 'main');
                                    d.add(15,2,'公告管理','ShowReport.wx?PAGEID=info','', 'main');
                                    d.add(16,8,'用户管理','ShowReport.wx?PAGEID=user','', 'main');
                                    d.add(17,3,'电话管理','ShowReport.wx?PAGEID=telephone','', 'main');
                                    d.add(18,4,'信息推送','ShowReport.wx?PAGEID=xxlq','', 'main');
                                    d.add(19,5,'空闲教室','xc2408/jxz.jsp','', 'main');
                                    
                                    document.write(d);

                                </script>

                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td valign="top" height="25"><img src="${ctx}/images/left1.jpg" height="25" width="198"/></td>
        </tr>
    </table>

</div>
</body>
</html>

