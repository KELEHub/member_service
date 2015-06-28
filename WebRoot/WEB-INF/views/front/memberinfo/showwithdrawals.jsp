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
	<form  action="/acc/showaccwithdrawals.do" method="POST" id="accChargeForm">
	</form>
	<h1>
		申请提现
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
				<form action="/acc/doaccwithdrawals.do" method="POST"
					id="doaccwithdrawals">
					<input type="hidden" name="id" value="${result.id}"/>
					<input type="hidden" name="number" value="${result.number}"/>
					<div class="control-group">
						<label class="control-label">积分余额</label>
						<div class="controls">
							<input type="text" value="${result.shoppingMoney}" disabled="disabled"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">备注</label>
						<div class="controls">
						   <textarea rows="5" cols="500" style="width: 600px;" disabled="disabled" name="withdrawalsBackInfo">姓名: ${result.name} > 银行名称:${result.bankName} > 银行账号:${result.bankCard} > 银行地址:${result.bankProvince}${result.bankCity}${result.bankCounty}${result.bankAddress} > 联系电话:${result.phoneNumber}</textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">提现金额</label>
						<div class="controls">
							<input type="text" name="withdrawalsAmt" value="" autocomplete="off"/>
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
							onclick="doAccWithdrawals('doaccwithdrawals')">确认提现</button>
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
								<th>提现金额</th>
								<th>手续费</th>
								<th>实际金额</th>
								<th>余额</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${withdrawalsList}">
								<tr class="gradeX">
									<td>${item.number}</td>
									<td>${item.tradeNo}</td>
									<td>${item.tradeDate }</td>
									<td>${item.tradeAmt }</td>
									<td>${item.tradeFee }</td>
									<td>${item.realGetAmt }</td>
									<td>${item.balanceAmt }</td>
									<td>
									<c:if test="${item.status eq 0}">未处理</c:if> 
									<c:if test="${item.status eq 1}">已处理</c:if>
									<c:if test="${item.status eq 2}">被拒绝</c:if>
									</td>
									<td><button class="btn" onclick="showAccWithdrawalsDetail('${item.id}')">提现备注</button>
									<c:if test="${item.status eq 2}">
										<button class="btn" onclick="showAccWithDrawalsRefuseReason('${item.id}')">拒绝理由</button>
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