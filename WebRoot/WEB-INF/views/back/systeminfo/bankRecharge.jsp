<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/BankRechargeController/show.do" method="POST" id="updateRechargeForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>会员账户充值</h5>
				</div>
				<div class="widget-content nopadding">
					<form class="form-horizontal"
						action="/BankRechargeController/serch.do" method="POST"
						id="serchRechargeForm">
						<div class="control-group">
							<label class="control-label">会员账号：</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="number" value="${bean.number}"
										class="span11">
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<input type="submit" value="检索"
							onclick="serchUser('serchRechargeForm')"
							class="btn btn-success">
					</div>
					<div class="control-group">
							<label class="control-label">${bean.error}</label>
						</div>
				</div>
			  <div class="widget-content nopadding">
					<form class="form-horizontal"
						action="/BankRechargeController/set.do" method="POST"
						id="rechargeForm">
						<div class="control-group">
							<label class="control-label">会员账号：</label>
							<div class="controls">
								<div class="input-append">
									<label>${bean.userNumber}</label>
									<input type="hidden" name="userNumber" value="${bean.userNumber}"
										class="span11">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">会员姓名：</label>
							<div class="controls">
								<div class="input-append">
									<label>${bean.userName}</label>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">葛粮币：</label>
							<div class="controls">
								<div class="input-append">
								<input type="text" name="crmMoney" value="${bean.crmMoney}"
										class="span11">
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<input type="submit" value="充值"
							onclick="saveParameter('rechargeForm')"
							class="btn btn-success">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
