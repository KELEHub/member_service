<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="showOrderListDetail" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>收货人详情.</h3>
	</div>
	<div class="modal-body">
		<div class="widget-box">
			<div class="widget-content nopadding">
			<font style="color: red;font-size: 20px;">
			收货信息:【姓名:${result.linkName}】 » 【${result.linkProvince} ${result.linkCity} ${result.linkCounty} ${result.linkAddress}】 » 【邮政编码:${result.postNumber}】 » 【联系电话:${result.phoneNumber}】
			</font>
			</div>
		</div>
	</div>
	<div class="modal-footer">
	</div>
</div>