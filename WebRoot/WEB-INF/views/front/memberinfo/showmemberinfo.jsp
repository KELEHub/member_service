<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="content-header">
	<div id="breadcrumb">
		<a href="#" title="Go to Home" class="tip-bottom"><i
			class="icon-home"></i> Home</a> <a href="#" class="current">Buttons
			&amp; icons</a>
	</div>
	<h1>
		我的基本信息
	</h1>
</div>
<script type="text/javascript">
	initMemberInfoUpdateSelect();
</script>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
				<form action="/MemberLoginController/updateMemberInfo.do" method="POST"
					id="updateMemberInfo">
					<input type="hidden" name="id" value="${result.id}"/>
					<div class="control-group">
						<label class="control-label">会员账号</label>
						<div class="controls">
							<input type="text" value="${result.number }" disabled="disabled"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">姓名</label>
						<div class="controls">
							<input type="text"  value="${result.name }" disabled="disabled"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">身份证号</label>
						<div class="controls">
							<input type="text" value="${result.identity }" disabled="disabled"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">电话号码</label>
						<div class="controls">
							<input type="text" placeholder="请输入您的 电话号码" name="phoneNumber" value="${result.phoneNumber}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">电子邮箱</label>
						<div class="controls">
							<input type="text" placeholder="请输入您的电子邮箱" name="email" value="${result.email}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">开户银行</label>
						<div class="controls">
								<select name="bankName" id="bankName">
								  <c:forEach var="item" items="${bank}">
								  	<option value="${item.bankName }" <c:if test="${result.bankName eq item.bankName }">selected="selected"</c:if>>${item.bankName }</option>
								  </c:forEach>
								</select> 
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">分行名称</label>
						<div class="controls">
						   <input type="hidden" id="bankProvince" value="${result.bankProvince}"/>
						    <input type="hidden" id="bankCity" value="${result.bankCity}"/>
						    <input type="hidden" id="bankCounty" value="${result.bankCounty}"/>
						   <select id="select_province" name="bankProvince"></select> 
						   <select id="select_city"  name="bankCity"></select>
						   <select id="select_area"  name="bankCounty"></select>
						   <input type="text" name="bankAddress" value="${result.bankAddress }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">银行账号</label>
						<div class="controls">
							<input type="text" placeholder="请输入银行卡号" name="bankCard" value="${result.bankCard}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">邮政编码</label>
						<div class="controls">
							<input type="text" placeholder="请输入邮政编码" name="postNumber" value="${result.postNumber}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">联系地址</label>
						<div class="controls">
						    <input type="hidden" id="linkProvince" value="${result.linkProvince}"/>
						    <input type="hidden" id="linkCity" value="${result.linkCity}"/>
						    <input type="hidden" id="linkCounty" value="${result.linkCounty}"/>
						  		<select id="select_province1" name="linkProvince"></select>
							    <select id="select_city1" name="linkCity"></select>
								<select id="select_area1" name="linkCounty"></select>
								<input type="text" name="linkAddress" value="${result.linkAddress }">
						</div>
					</div>
				</form>
				<div class="modal-footer">
						<button data-dismiss="modal" class="save" type="button"
							onclick="saveMemberInfo('updateMemberInfo')">确认修改</button>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>