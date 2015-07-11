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
	<form  action="/acc/showacccharge.do" method="POST" id="accChargeForm">
	</form>
	<h1>
		账户充值
	</h1>
</div>
<script type="text/javascript">
   //页面加载js做的事情
</script>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
				<form action="/acc/doacccharge.do" method="POST"
					id="updateMemberInfo">
					<input type="hidden" name="number" value="${result.number}"/>
					<div class="control-group">
						<label class="control-label">余额</label>
						<div class="controls">
							<input type="text" value="${result.crmMoney }" disabled="disabled"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">备注</label>
						 <textarea rows="5" cols="500" style="display: none;" name="chageBackInfo">姓名: ${result.name} > 银行名称:${result.bankName} > 银行账号:${result.bankCard} > 银行地址:${result.bankProvince} ${result.bankCity} ${result.bankCounty} ${result.bankAddress} > 联系电话:${result.phoneNumber}</textarea>
						<div class="controls">
						   <div style="width: 600px;border:1px solid #ccc">
						   基本格式要求：1、会员***,由***银行向公司尾号****的**银行转账*****元，申请充值
						   <br/>2、会员***，在公司***服务站，由尾号****的***银行卡刷卡***元，申请充值
                  			<br/>3、会员***，在公司***服务站，交现金****元，收款人***。申请充值
                  			<br/>以上信息可复制粘贴在留言处进行修改。
						   </div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">充值金额</label>
						<div class="controls">
							<input type="text" name="chageAmt" value="" autocomplete="off"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">留言</label>
						<div class="controls">
						 <textarea rows="4" cols="500" style="width: 600px;resize:none;" name="chageMessage"></textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">二级密码</label>
						<div class="controls">
							<input type="password" placeholder="请输入您的交易二级密码" name="protectPassword" value="" autocomplete="off"/>
						</div>
					</div>
				</form>
				<div class="modal-footer">
						<button data-dismiss="modal" class="save" type="button"
							onclick="doAccCharge('updateMemberInfo')">确认充值</button>
				</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
				   <table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>流水号</th>
								<th>日期</th>
								<th>充值金额</th>
								<th>手续费</th>
								<th>实际金额</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${charList }">
								<tr class="gradeX">
									<td>${item.number}</td>
									<td>${item.tradeNo}</td>
									<td>${item.chargeDate }</td>
									<td>${item.chageAmt }</td>
									<td>${item.chargesurplus }</td>
									<td>${item.realGetAmt }</td>
									<td>
									<c:if test="${item.status eq 0}">未处理</c:if> 
									<c:if test="${item.status eq 1}">已处理</c:if>
									<c:if test="${item.status eq 2}">被拒绝</c:if>
									</td>
									<td><button class="btn" onclick="showAccChargeDetail('${item.id}')">备注信息</button>
									<c:if test="${item.status eq 2}">
										<button class="btn" onclick="showAccChargeRefuseReason('${item.id}')">拒绝理由</button>
									</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>