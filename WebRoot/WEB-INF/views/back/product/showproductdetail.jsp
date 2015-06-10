<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="showProductDetail" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>产品信息修改.</h3>
	</div>
	<div class="modal-body">
		<div class="widget-box">
			<div class="widget-content nopadding">
				<form action="/product/updateProduct.do" method="POST"
					id="updateProductForm">
					<input type="text" name="id" value="${result.id }"/>
					<div class="control-group">
						<label class="control-label">套餐名称</label>
						<div class="controls">
							<input type="text" placeholder="套餐名称" name="productName" value="${result.productName }"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">套餐介绍</label>
						<div class="controls">
							<input type="text" placeholder="套餐介绍" name="productIntroduction" value="${result.productIntroduction }"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">图片</label>
						<div class="controls">
							<input type="text" placeholder="图片路径" name="productTarget" value="${result.productTarget }"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">套餐类型</label>
						<div class="controls">
							<input type="text" placeholder="套餐类型" name="productCategory" value="${result.productCategory }"/>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button data-dismiss="modal" class="save" type="button" onclick="saveWithFormAddRefeshTable('updateProductForm')">确认修改</button>
	</div>
</div>