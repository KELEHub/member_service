<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div style="overflow-x:hidden; height:85%">
<div id="content-header">
	<div id="breadcrumb">
		<form action="/shoping/showShopingCart.do" method="POST" id="tocartform">
	</form>
	</div>
</div>
<div id="content-header" style="margin-top: 100px;">
<div class="controls controls-row">
			<div class="control-group" style="float: left;margin-left: 100px;">
				<img src="${pt.productTarget}"    style="width: 300px"/>
			</div>
			<div class="control-group" style="float: left;margin-left: 200px; margin-top:30px;">
			<input type="hidden" name="productNumber" id="productNumber"  value="${pt.productNumber}">
				<label class="control-label">${pt.productName}</label>
				<br>
				<label class="control-label">编号：${pt.productNumber}</label>
				<br>
				<label class="control-label">售价：${pt.productPrice}</label>
				<br>
				<input type="submit" value="加入购物车" onclick="addshoping()"
			class="btn btn-success">
			</div>
			<div class="control-group" style="float: left;margin-left: 200px;margin-top:30px;"">
			     <label class="control-label">选择服务站：</label>
				<select name="serverNumber" id="serverNumber" onchange="changeServer();">
				<option value="space" ></option>
				<c:forEach var="item" items="${result }">
						<option value="${item.id}" >${item.number}</option>
				</c:forEach>
					</select>
					<br>
					<label  class="control-label">电话：</label>
					<input type="text" id="phone" disabled="true" style="width: 240px;height: 40px">
					<br>
					<label  class="control-label">地址</label>
					<input type="text" id="address" disabled="true" style="width: 400px;height: 40px">
					<br>
					<label class="control-label">库存</label>
					<input type="text" id="firm" disabled="true" style="width: 240px;height: 40px">
			</div>
</div>
<br>
<div  style="width: 800px;height:auto;position: relative;padding: 15px;overflow-y: auto;margin-top:60px;">
<div >
	<label class="control-label">商品介绍</label>
</div>
	<div >
	<label class="control-label"> ${pt.productIntroduction}</label>
	</div>
</div>
</div>
</div>