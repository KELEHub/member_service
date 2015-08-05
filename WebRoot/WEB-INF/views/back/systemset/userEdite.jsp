<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div style="overflow-x:hidden; height:85%">
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/userEditeController/show.do" method="POST" id="updateUserCountForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>会员账户修改</h5>
				</div>
				<div class="widget-content nopadding">
					<form class="form-horizontal"
						action="/userEditeController/serch.do" method="POST"
						id="serchUserForm">
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
							onclick="serchUser('serchUserForm')"
							class="btn btn-success">
					</div>
					<div class="control-group">
							<label class="control-label">${bean.error}</label>
						</div>
				</div>
			  <div class="widget-content nopadding">
					<form class="form-horizontal"
						action="/userEditeController/set.do" method="POST"
						id="editeUserForm">
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
							<label class="control-label">葛粮币：</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="crmMoney" value="${bean.crmMoney}" onfocusout="formatter(this)"
										class="span11">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">葛粮币累计：</label>
							<div class="controls">
								<div class="input-append">
								<input type="text" name="crmAccumulative" value="${bean.crmAccumulative}" onfocusout="formatter(this)"
										class="span11">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">积分：</label>
							<div class="controls">
								<div class="input-append">
								<input type="text" name="shoppingMoney" value="${bean.shoppingMoney}" onfocusout="formatter(this)"
										class="span11">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">积分累计：</label>
							<div class="controls">
								<div class="input-append">
										<input type="text" name="shoppingAccumulative" value="${bean.shoppingAccumulative}" onfocusout="formatter(this)"
										class="span11">
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<input type="submit" value="修改"
							onclick="saveParameter('editeUserForm')"
							class="btn btn-success">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
