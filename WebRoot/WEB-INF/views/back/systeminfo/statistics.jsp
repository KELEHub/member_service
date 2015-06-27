<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
			<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>统计数据</h5>
				</div>
				<div class="widget-content nopadding">
				<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label">累计单量：</label>
							<div class="controls">
								<div class="input-append">
									<label>${countBill}</label>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">累计充值：</label>
							<div class="controls">
								<div class="input-append">
									<label >${countRecharge}</label>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">累计结算：</label>
							<div class="controls">
								<div class="input-append">
										<label >${countFromgifts}</label>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">累计提现：</label>
							<div class="controls">
								<div class="input-append">
										<label>${countPointcash}</label>
								</div>
							</div>
						</div>
					</div>
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>期次</th>
								<th>新增单量</th>
								<th>新增充值</th>
								<th>新增结算</th>
								<th>新增提现</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result }">
								<tr class="gradeX">
									<td>${item.dateNumber}</td>
									<td>${item.countBill }</td>
									<td>${item.countRecharge }</td>
									<td>${item.countFromgifts }</td>
									<td>${item.countPointcash }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>