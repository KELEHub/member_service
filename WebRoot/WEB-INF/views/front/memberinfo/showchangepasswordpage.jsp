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
		修改密码
	</h1>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
				<h1>
					修改登录密码
				</h1>
				<form action="/MemberLoginController/change1password.do" method="POST"
					id="update1password">
					<input type="hidden" name="userId" value="${userid}"/>
					<div class="control-group">
						<label class="control-label">新密码</label>
						<div class="controls">
							<input type="password" placeholder="新登录密码" name="onelevelpassword" value=""/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">确认密码</label>
						<div class="controls">
							<input type="password" placeholder="确认新登录密码" name="onelevelpasswordconfirm" value=""/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">二级密码</label>
						<div class="controls">
							<input type="password" placeholder="输入二级密码" name="second1Password" value=""/>
						</div>
					</div>
				</form>
				<div class="modal-footer">
						<button data-dismiss="modal" class="save" type="button"
							onclick="change1Password('update1password')">确认修改</button>
				</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-content nopadding">
				<h1>
					修改二级密码
				</h1>
				<form action="/MemberLoginController/change2password.do" method="POST"
					id="update2password">
					<input type="hidden" name="userId" value="${userid}"/>
					<div class="control-group">
						<label class="control-label">新密码</label>
						<div class="controls">
							<input type="password" placeholder="新二级密码" name="twolevelpassword" value=""/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">确认密码</label>
						<div class="controls">
							<input type="password" placeholder="确认新二级密码" name="twolevelpasswordconfirm" value=""/>
						</div>
					</div>

				</form>
				<div class="modal-footer">
						<button data-dismiss="modal" class="save" type="button"
							onclick="chenge2password('update2password')">确认修改二级密码</button>
				</div>
			   </div>
			</div>
		</div>
	</div>
</div>