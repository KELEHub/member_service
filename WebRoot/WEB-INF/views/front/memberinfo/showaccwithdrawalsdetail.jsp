<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="showAccWithdrawalsDetail" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>提现描述信息.</h3>
	</div>
	<div class="modal-body">
		<div class="widget-box">
			<h2>提现银行卡信息:</h2>
			<div class="widget-content nopadding">
				<font style="color: red;font-size: 20px;">
				   ${result.withdrawalsBackInfo }
				</font>
			</div>
		</div>
	</div>
	<div class="modal-footer">
	</div>
</div>