<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/ServiceManagerController/showServiceManager.do" method="POST" id="serviceManagerForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>报单中心信息</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>账号</th>
								<th>姓名</th>
								<th>积分余额</th>
								<th>电话号码</th>
								<th>开户银行</th>
								<th>邮政编码</th>
								<th>推荐报单中心编号</th>
								<th>本月新报单人数</th>
								<th>历史报单人数</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.number}</td>
									<td>${item.name}</td>
									<td>${item.shoppingMoney}</td>
									<td>${item.phoneNumber}</td>
									<td>${item.bankName}</td>
									<td>${item.postNumber}</td>
									<td>${item.recommendNumber}</td>
									<td>${item.serviceCount}</td>
									<td>${item.serviceSum}</td>
									<td>
									<a href="#myModal1" data-toggle="modal" class="btn btn-large" onclick="editNotice('${item.id}','${item.title}','${item.category}','${item.content}')">详细信息</a>
									<a href="#myModal2" data-toggle="modal" class="btn btn-large" onclick="editNotice('${item.id}','${item.title}','${item.category}','${item.content}')">后台充值</a>
									<button class="btn btn-large" onclick="doDeleteNotice('${item.id}')">禁用</button>
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

<div id="myModal1" class="modal hide">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>修改公告</h3>
	</div>
	<div class="widget-content nopadding">
		<form action="/NoticeManagerController/editNotice.do" method="post"
			id="editNoticeForm" class="form-horizontal">
			<input type="hidden" class="span11" name="noticeId" id="noticeListId" />
			<div class="control-group">
				<label class="control-label">
					标题:
				</label>
				<div class="controls">
					<input type="text" class="span11" name="title"
						id="editNotice_title" style="width:220;"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					类型:
				</label>
				<div class="controls">
					<select name="category" id="editNotice_category">
						<option>
							公告
						</option>
						<option>
							服务协议
						</option>
						<option>
							常见问题
						</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					内容:
				</label>
<!--				<div class="widget-content">-->
					<div class="control-group">
						<div class="controls">
							<textarea class="textarea_editor span12" rows="6" name="content"
								id="editNotice_content" style="width:220;"></textarea>
						</div>
					</div>
<!--				</div>-->
			</div>
		</form>
	</div>
	<div class="form-actions">	
	<button type="submit" class="btn btn-success" onclick="saveNotice('editNoticeForm')">
		确认修改
	</button>
</div>
</div>