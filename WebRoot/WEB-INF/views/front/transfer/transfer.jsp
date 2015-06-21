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
						葛粮币转账
					</h5>
				</div>
				<div class="widget-content nopadding">
					<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label">
								葛粮币余额：
							</label>
							<div class="controls">
								<div class="input-append">
									<label class="control-label">
										${goldmoneybalance }
									</label>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								转至会员账号：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="toUserNumber" id="toUserNumber" value="${toUserNumber}">
									<input type="submit" value="查看"
										onclick="selectData()" class="btn btn-success">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								对方姓名：
							</label>
							<div class="controls">
								<div class="input-append">
								<input type="text" name="toUserName" id="toUserName" disabled="true" value="${toUserName}">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								转账金额：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="toGoldMoney" value="${toGoldMoney}">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								二级密码：
							</label>
							<div class="controls">
								<div class="input-append">
									<input type="password" name="payPassword"  value="${payPassword}">
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<input type="submit" value="确认转账"
							onclick="saveBank('transferForm')" class="btn btn-success">
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
