<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="showChargeDetail" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>充值描述信息.</h3>
	</div>
	<div class="modal-body">
<!-- 		<div class="widget-box"> -->
<!-- 			<div class="widget-title"> -->
<!-- 				<span class="icon"> <i class="icon-th"></i> -->
<!-- 				</span> -->
<!-- 				<h5> -->
<!-- 					<font style="color: red">提现会员信息</font> -->
<!-- 				</h5> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="widget-box">
		    <h2></h2>
			<div class="widget-content nopadding">
				<font style="color: red;font-size: 20px;">
				  ${result.chageBackInfo }
				</font>
			</div>
			<h2></h2>
			<div class="widget-content nopadding">
				<font style="color: red;font-size: 20px;">
				   ${result.chageMessage }
				</font>
			</div>
		</div>
	</div>
	<div class="modal-footer">
	</div>
</div>