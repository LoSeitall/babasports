<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>babasport-list</title>
    <script type="text/javascript">
        //上架
        function isShow1() {
            //请至少选择一个
            var size = $("input[name='ids']:checked").size();
            if (size == 0) {
                alert("请至少选择一个");
                return;
            }
            //你确定删除吗
            if (!confirm("你确定上架吗")) {
                return;
            }
            //提交 Form表单
            $("#jvForm").attr("action", "/product/groundingProducts");
            $("#jvForm").attr("method", "post");
            $("#jvForm").submit();

        }

        //下架
        function isShow0() {
            //请至少选择一个
            var size = $("input[name='ids']:checked").size();
            if (size == 0) {
                alert("请至少选择一个");
                return;
            }
            //你确定删除吗
            if (!confirm("你确定下架吗")) {
                return;
            }
            //提交 Form表单
            $("#jvForm").attr("action", "/product/undercarriageProducts");
            $("#jvForm").attr("method", "post");
            $("#jvForm").submit();

        }

        //去修改页面
        function toUpdatePage(id, pageNo, pageSize) {

            window.location.href = "/product/toUpdateProduct?id=" + id + "&pageNo=" + pageNo + "&size=" + pageSize;

        }
        //去查看页面
         function toShowPage(id, pageNo, pageSize) {

            window.location.href = "/product/toShowProduct?id=" + id + "&pageNo=" + pageNo + "&size=" + pageSize;

        }
        

        //批量删除
        function optDelete() {
            var size = $("input[name='ids']:checked").size();
            if (size == 0) {
                alert("请至少选择一个");
                return;
            }
            //你确定删除吗
            if (confirm("你确定删除吗？")) {
                $('#jvForm').attr("action", "/product/deleteProducts").submit();
            }
        }
    </script>
</head>
<body>
<div class="box-positon">
    <div class="rpos">当前位置: 商品管理 - 列表</div>
    <form class="ropt">
        <input class="add" type="button" value="添加" onclick="window.location.href='/product/toProductAdd'"/>
    </form>
    <div class="clear"></div>
</div>
<div class="body-box">
    <form action="/product/productList" method="post" style="padding-top:5px;">
        名称: <input type="text" name="name" value="${product.name }"/>
        <select name="brandId">
            <option value="0">请选择品牌</option>
            <c:forEach items="${blist }" var="brand">
                <option value="${brand.id }" <c:if
                        test="${product.brandId==brand.id }"> selected="selected"</c:if>>${brand.name }</option>
            </c:forEach>

        </select>
        <select name="isShow">
            <option value="2">请选择</option>
            <option value="1" <c:if test="${product.isShow==true }"> selected="selected"</c:if>>上架</option>
            <option value="0" <c:if test="${product.isShow==false }"> selected="selected"</c:if>>下架</option>
        </select>
        <input type="submit" class="query" value="查询"/>
    </form>
    <form id="jvForm" method="get">
        <table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
            <thead class="pn-lthead">
            <tr>
                <th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
                <th>商品编号</th>
                <th>商品名称</th>
                <th>图片</th>
                <th width="4%">新品</th>
                <th width="4%">热卖</th>
                <th width="4%">推荐</th>
                <th width="4%">上下架</th>
                <th width="12%">操作选项</th>
            </tr>
            </thead>
            <tbody class="pn-ltbody">
            <c:forEach items="${pu.list }" var="product1">
                <tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
                    <td align="center"><input type="checkbox" name="ids" class="ids" value="${product1.id }"/></td>
                    <td align="center">${product1.id }</td>
                    <td align="center">${product1.name }</td>
                    <td align="center"><img width="50" height="50" src="${product1.imgUrls[0] }"/></td>
                    <td align="center"><c:if test="${product1.isNew==true }">是</c:if><c:if
                            test="${product1.isNew==false }">否</c:if></td>
                    <td align="center"><c:if test="${product1.isHot==true }">是</c:if><c:if
                            test="${product1.isHot==false }">否</c:if></td>
                    <td align="center"><c:if test="${product1.isCommend==true }">是</c:if><c:if
                            test="${product1.isCommend==false }">否</c:if></td>
                    <td align="center"><c:if test="${product1.isShow==true }">上架</c:if><c:if
                            test="${product1.isShow==false }">下架</c:if></td>
                    <td align="center">
                        <a href="#" class="pn-opt" onclick="toShowPage(${product1.id },${pu.pageNo },${pu.pageSize })">查看</a> | <a href="javascript:;" class="pn-opt"
                                                               onclick="toUpdatePage(${product1.id },${pu.pageNo },${pu.pageSize })">修改</a>
                        <c:if test="${product1.isDel==true }">| <a href="javascript:;"
                             onclick="if(confirm('您确定删除吗？')) {window.location.href='/product/deleteProduct?brandId=${product1.brandId }&name=${pname }&isShow=${product1.isShow }&id=${product1.id}&pageNo=${pu.pageNo }&size=8'}"
                             class="pn-opt">删除</a></c:if> | <a href="javascript:;" onclick="window.location.href='/product/skuList?id=${product1.id}'"
                                                        class="pn-opt">库存</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="page pb15">
	<span class="r inb_a page_b">
	
	     <c:forEach items="${pu.pageView }" var="p">
             ${p}
         </c:forEach>
	
	</span>
        </div>
        <div style="margin-top:15px;"><input class="del-button" type="button" value="删除" onclick="optDelete();"/><input
                class="add" type="button" value="上架" onclick="isShow1();"/><input class="del-button" type="button"
                                                                                  value="下架" onclick="isShow0();"/>
        </div>
        <input type="hidden" name="name" value="${product.name }"/>
        <input type="hidden" name="isShow" value="${product.isShow }"/>
        <input type="hidden" name="pageNo" value="${pu.pageNo}"/>
        <input type="hidden" name="size" value="8"/>
        <input type="hidden" name="brandId" value="${product.brandId }"/>
    </form>
</div>
</body>
</html>