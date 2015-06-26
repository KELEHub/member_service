<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<base href="<%=basePath%>">
<div id="showMemberOrderProductDetail" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>产品信息修改.</h3>
	</div>
	<div class="modal-body">
		<div class="widget-box">
			<div class="widget-content nopadding">
				<form action="/product/updateProduct.do" method="POST"
					id="updateProductForm">
					<input type="hidden" name="id" value="${result.id }"/>
					<div class="control-group">
						<label class="control-label">套餐名称</label>
						<div class="controls">
							<input type="text" placeholder="套餐名称" name="productName" value="${result.productName }"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">套餐介绍</label>
						<div class="controls">
						<textarea rows="12" cols="24" name="productIntroduction" >
						    ${result.productIntroduction }
						</textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">图片</label>
						<div class="controls">
							 <a class="upfile" href="${result.productTarget }"
									rel="lightbox" id="linkboxfileupload9"> 
							  <img src="${result.productTarget }"
									width="100px">		
							</a>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">套餐类型</label>
						<div class="controls">
						<select name="productCategory" >
								<option value="1" <c:if test="${result.productCategory eq 1}">selected</c:if> >注册赠送</option>
								<option value="2" <c:if test="${result.productCategory eq 2}">selected</c:if>>复消赠送</option>
							</select>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal-footer">
	</div>
</div>