<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="content-header">
	<div id="breadcrumb">
		<a href="#" title="Go to Home" class="tip-bottom"><i
			class="icon-home"></i> Home</a>
		<a href="#" class="current">Buttons &amp; icons</a>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i> </span>
					<h5>
						积分转换葛粮币
					</h5>
				</div>
				<div class="widget-content nopadding">
					<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label">
								积分余额：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="shoppingMoney" id="shoppingMoney" disabled="true" value="${shoppingMoney}">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								转换金额：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="toCmrMoney" id="toCmrMoney" value="${toCmrMoney}" onfocusout="formatter(this)">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								二级密码：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="password" name="payPassword" id="payPassword" value="${payPassword}">
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<input type="submit" value="确认转换"
							onclick="saveconvert()" class="btn btn-success">
					</div>
					<div class="controls controls-row">
						<div class="control-group" style="float: left; margin-left: 10px;">
							<label class="control-label" style="color: red">
								${error }
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
