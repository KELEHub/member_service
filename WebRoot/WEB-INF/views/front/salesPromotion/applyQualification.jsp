<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/ApplyQualificationController/showApplyQualification.do" method="POST" id="applyQualificationForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
		<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>申请资格</h5>
				</div>
				<div class="widget-content nopadding">
					<form action="/NoticeManagerController/releaseNotice.do"
						method="post" id="releaseNoticeForm" class="form-horizontal">
						<div class="control-group">
							<label class="control-label">
								申请会员账号:
							</label>
							<div class="controls">
								<input type="text" class="span11" name="title" />
							</div>
							<div class="form-actions">
								<button type="submit" class="btn btn-success"
									onclick="saveNotice('releaseNoticeForm')">
									查看
								</button>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								申请会员账号信息:
							</label>
							<div class="controls">
								<input type="text" class="span11" name="title" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								推荐理由说明:
							</label>
							<div class="controls">
								<textarea class="textarea_editor span12" rows="6" name="content"></textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								二级密码:
							</label>
							<div class="controls">
								<input type="text" class="span11" name="title" />
							</div>
						</div>
					</form>
					<div class="form-actions">
						<button type="submit" class="btn btn-success"
							onclick="saveNotice('releaseNoticeForm')">
							确认申请
						</button>
					</div>
				</div>
			</div>
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>申请记录</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>申请会员账号</th>
								<th>申请日期</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.number}</td>
									<td>${item.applyDate}</td>
									<td>${item.state}</td>
									<td>
									<c:if test="${item.state eq 0}">
										<button class="btn btn-large" onclick="deleteApplyQualification('${item.id}')">删除</button>
									</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>