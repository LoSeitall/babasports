<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../head.jsp" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>--%>
    <title>babasport-list</title>
    <script src="../../../jquery-1.11.3.js" type="application/javascript"></script>
    <script type="text/javascript">
        $(function() {
            $("#mdelete").click(function() {
                array = new Array();
                $("input[type='checkbox']:gt(0):checked").each(
                    function() {array.push($(this).parent().next().text());});
                if (array == 0) {
                    alert("请勾选!!");
                } else {
                    location.href = "/brand/muliDeleteBrand?ids="+ array;
                }
            });
        });
    </script>
</head>
<body>
<div class="box-positon">
    <div class="rpos">当前位置: 品牌管理 - 列表</div>
    <form class="ropt">
        <input class="add" type="button" value="添加" onclick="javascript:window.location.href='/brand/toBrandInsert'"/>
    </form>
    <div class="clear"></div>
</div>
<div class="body-box">
    <form action="/brand/selectByCondition" method="post" style="padding-top:5px;">
        品牌名称: <input type="text" name="name" value="${name}"/>
        <select name="isDisplay">
           <option value="1"  <c:if test="${isDisplay=='1'}">selected="selected"</c:if> >是</option>
           <option value="0"  <c:if test="${isDisplay=='0'}">selected="selected"</c:if> >否</option>
        </select>
        <input type="submit" class="query" value="查询"/>
    </form>
    <table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
        <thead class="pn-lthead">
        <tr>
            <th width="20"><input type="checkbox" onclick="checkBox('ids',this.checked)"/></th>
            <th>品牌ID</th>
            <th>品牌名称</th>
            <th>品牌图片</th>
            <th>品牌描述</th>
            <th>排序</th>
            <th>是否可用</th>
            <th>操作选项</th>
        </tr>
        </thead>
        <tbody class="pn-ltbody">
        <c:forEach items="${pu.list}" var="brand">
            <tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
                <td align="center"><input type="checkbox" value="${brand.id}" name="ids" id="ids"/></td>
                <td align="center">${brand.id}</td>
                <td align="center">${brand.name}</td>
                <td align="center"><img width="40" height="40" src="${brand.imgUrl}"/></td>
                <td align="center">${brand.description}</td>
                <td align="center">${brand.sort}</td>
                <td align="center">${brand.strDis}</td>

                <td align="center">
                    <a class="pn-opt" href="/brand/toBrandEdit?id=${brand.id}">修改</a><c:if test="${brand.isDisplay}">|<a class="pn-opt"
                                                                     onclick="if(!confirm('您确定删除吗？')) {return false;}"
                                                                     href="/brand/brandDelete?id=${brand.id}&name=${name}&isDisplay=${isDisplay}">删除</a></c:if>
                </td>
            </tr>
        </c:forEach>
        <%--<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">--%>
            <%--<td><input type="checkbox" value="7" name="ids"/></td>--%>
            <%--<td align="center">7</td>--%>
            <%--<td align="center">喜悦瑜伽</td>--%>
            <%--<td align="center"><img width="40" height="40" src="/images/pic/ppp1.jpg"/></td>--%>
            <%--<td align="center"></td>--%>
            <%--<td align="center">99</td>--%>
            <%--<td align="center">是</td>--%>
            <%--<td align="center">--%>
                <%--<a class="pn-opt" href="#">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}"--%>
                                                       <%--href="#">删除</a>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">--%>
            <%--<td><input type="checkbox" value="6" name="ids"/></td>--%>
            <%--<td align="center">6</td>--%>
            <%--<td align="center">丹璐斯</td>--%>
            <%--<td align="center"><img width="40" height="40" src="/images/pic/ppp2.jpg"/></td>--%>
            <%--<td align="center"></td>--%>
            <%--<td align="center">99</td>--%>
            <%--<td align="center">是</td>--%>
            <%--<td align="center">--%>
                <%--<a class="pn-opt" href="#">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}"--%>
                                                       <%--href="#">删除</a>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">--%>
            <%--<td><input type="checkbox" value="5" name="ids"/></td>--%>
            <%--<td align="center">5</td>--%>
            <%--<td align="center">菩媞</td>--%>
            <%--<td align="center"><img width="40" height="40" src="/images/pic/ppp3.jpg"/></td>--%>
            <%--<td align="center"></td>--%>
            <%--<td align="center">99</td>--%>
            <%--<td align="center">是</td>--%>
            <%--<td align="center">--%>
                <%--<a class="pn-opt" href="#">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}"--%>
                                                       <%--href="#">删除</a>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">--%>
            <%--<td><input type="checkbox" value="4" name="ids"/></td>--%>
            <%--<td align="center">4</td>--%>
            <%--<td align="center">伊朵莲</td>--%>
            <%--<td align="center"><img width="40" height="40" src="/images/pic/ppp4.jpg"/></td>--%>
            <%--<td align="center"></td>--%>
            <%--<td align="center">99</td>--%>
            <%--<td align="center">是</td>--%>
            <%--<td align="center">--%>
                <%--<a class="pn-opt" href="#">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}"--%>
                                                       <%--href="#">删除</a>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">--%>
            <%--<td><input type="checkbox" value="3" name="ids"/></td>--%>
            <%--<td align="center">3</td>--%>
            <%--<td align="center">梵歌纳（vangona）</td>--%>
            <%--<td align="center"><img width="40" height="40" src="/images/pic/ppp5.jpg"/></td>--%>
            <%--<td align="center"></td>--%>
            <%--<td align="center">99</td>--%>
            <%--<td align="center">是</td>--%>
            <%--<td align="center">--%>
                <%--<a class="pn-opt" href="#">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}"--%>
                                                       <%--href="#">删除</a>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">--%>
            <%--<td><input type="checkbox" value="2" name="ids"/></td>--%>
            <%--<td align="center">2</td>--%>
            <%--<td align="center">凯速（KANSOON）</td>--%>
            <%--<td align="center"><img width="40" height="40" src="/images/pic/ppp6.jpg"/></td>--%>
            <%--<td align="center"></td>--%>
            <%--<td align="center">99</td>--%>
            <%--<td align="center">是</td>--%>
            <%--<td align="center">--%>
                <%--<a class="pn-opt" href="#">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}"--%>
                                                       <%--href="#">删除</a>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">--%>
            <%--<td><input type="checkbox" value="1" name="ids"/></td>--%>
            <%--<td align="center">1</td>--%>
            <%--<td align="center">依琦莲</td>--%>
            <%--<td align="center"><img width="40" height="40" src="/images/pic/ppp7.jpg"/></td>--%>
            <%--<td align="center"></td>--%>
            <%--<td align="center">99</td>--%>
            <%--<td align="center">是</td>--%>
            <%--<td align="center">--%>
                <%--<a class="pn-opt" href="#">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}"--%>
                                                       <%--href="#">删除</a>--%>
            <%--</td>--%>
        <%--</tr>--%>

        </tbody>
    </table>
    <div class="page pb15">
	<span class="r inb_a page_b">
     ${pu.str}
<%-- 		<c:if test="${pu.isFirstPage==false }"><a href='${rname }&amp;isShow=0&amp;pageNo=1&amp;size=8'><font size="2">首页</font></a></c:if>
	
		<c:if test="${pu.hasPreviousPage}"><a href='"+requestname+"&amp;isShow=0&amp;pageNo=${pu.pageNum-1 }&amp;size=8'><font size="2">上一页</font></a></c:if>
		
		<c:forEach items="${pu.navigatepageNums}" var="pagenum">
			 <c:if test="${pagenum == pu.pageNum}">
             	 <strong>${pageNum }</strong>
             </c:if>
             <c:if test="${pagenum != pu.pageNum}">
                 <a href="${rname}?&amp;isShow=0&amp;pageNo=2">${pagenum }</a>
             </c:if>
		</c:forEach>
		<!-- <strong>1</strong>
		<a href="/product/list.do?&amp;isShow=0&amp;pageNo=2">2</a>
	
		<a href="/product/list.do?&amp;isShow=0&amp;pageNo=3">3</a>
	
		<a href="/product/list.do?&amp;isShow=0&amp;pageNo=4">4</a>
	
		<a href="/product/list.do?&amp;isShow=0&amp;pageNo=5">5</a>
	 -->
		<c:if test="${pu.hasNextPage}"><a href="${rname }?&amp;isShow=0&amp;pageNo=${pu.pageNum+1 }"><font size="2">下一页</font></a></c:if>
	
		<c:if test="${pu.isLastPage==false}"><a href="${rname }?&amp;isShow=0&amp;pageNo=5"><font size="2">尾页</font></a></c:if>
	
		共<var>${pu.pages }</var>页 到第<input type="text" size="3" id="PAGENO"/>页 <input type="button"
                                                                           onclick="javascript:window.location.href = '${rname}?&amp;isShow=0&amp;pageNo=' + $('#PAGENO').val() "
                                                                           value="确定" class="hand btn60x20" id="skip"/>  --%>
	
	</span>
    </div>
    <div style="margin-top:15px;"><input class="del-button" type="button" value="删除" id="mdelete" onclick="if(!confirm('是否确认删除?')){return false;}"/></div>
    ${msg}
</div>
</body>
</html>