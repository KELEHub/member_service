<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="showRefuseReason1" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>充值拒绝理由.</h3>
	</div>
	<div class="modal-body">
		<div class="widget-box">
		    <h2></h2>
			<div class="widget-content nopadding">
				<font style="color: red;font-size: 20px;">
				  ${result.refuseReason }
				</font>
			</div>
		</div>
	</div>
	<div class="modal-footer">
	</div>
</div>