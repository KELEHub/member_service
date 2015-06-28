<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="showdisagreecharge" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>请输入拒绝充值理由:</h3>
	</div>
	<div class="modal-body">
		<div class="widget-box">
			<div class="widget-content nopadding">
				<form action="/charge/disagreeCharge.do" id="disagreeChargeForm" method="post">
				    <input type="hidden" name="id" value="${form.id }">
				    <textarea rows="" cols="" name="refuseReason" style="width: 300px;"></textarea>
				</form>
			</div>
		</div>
	</div>
	<div class="modal-footer">
	  <button data-dismiss="modal" class="save" type="button" onclick="saveWithFormAddRefeshTable('disagreeChargeForm','showdisagreecharge')">确定</button>
	</div>
</div>