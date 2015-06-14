<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="editeGiftsDetails" class="modal hide" style="width: 400px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">
		</button>
	</div>
	<div class="modal-body">
			<form class="form-horizontal"
				action="/GiftsDetailsController/set.do" method="POST"
				id="editeGiftsForm">
				<input type="hidden" name="id" value="${bean.id}">
				<table class="table table-bordered table-striped">
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">会员账号:</td>
						<td>${bean.number}</td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">礼包类型：</td>
						<td>${bean.typeGift}</td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">礼包名称：</td>
						<td>${bean.name}</td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">发送期次：</td>
						<td><input type="text" name="pointNumber" value="${bean.pointNumber}"></td>
					</tr>
				</table>
			</form>
	</div>
	<div class="modal-footer">
		<button data-dismiss="modal" class="save" type="button"
			onclick="saveGiftsDetail('editeGiftsForm')">
			确认修改
		</button>
	</div>
</div>