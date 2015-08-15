<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="roleDetails" class="modal hide" style="width: 400px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">
		</button>
	</div>
	<div class="modal-body">
			<form class="form-horizontal"
				action="/rolemanage/editeContent.do" method="POST"
				id="editeRoleForm">
				<input type="hidden" name="id" value="${bean.id}">
				<table class="table table-bordered table-striped">
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">角色名称：</td>
						<td><input type="text" name="roleNm" value="${bean.roleNm}"></td>
					</tr>
					<tr class="odd gradeX">
						<td width="120" style="text-align: right;">角色描述：</td>
						<td><input type="text" name="roleDsc" value="${bean.roleDsc}"></td>
					</tr>
				</table>
			</form>
	</div>
	<div class="modal-footer">
		<button data-dismiss="modal" class="save" type="button"
			onclick="saveRoleContentDetail('editeRoleForm')">
			确认修改
		</button>
	</div>
</div>