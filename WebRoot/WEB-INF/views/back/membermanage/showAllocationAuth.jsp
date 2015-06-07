<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="showAllocationAuth" class="modal hide">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>
		   角色信息:${form.id}【${form.roleDsc}】
		</h3>
	</div>
	<div class="modal-body">
	   <div style="width:90%;height:300px; overflow-y:scroll; ">
	 	<ul id="showAllocationAuthTree" class="ztree" overflow:auto;">
	 	</ul>
	 	</div>
	</div>
	<div  class="modal-footer">
		<button data-dismiss="modal" class="save" type="button" onclick="saveAllocationAuth()">保存分配</button>
	</div>
	<input type="hidden" id="authMenuRoleId" value="${form.id}"/>
	<input type="hidden" id="authMenuIds" value="${result}"/>
	<input type="hidden" id="existMenuIds" value="${result}"/>
</div>