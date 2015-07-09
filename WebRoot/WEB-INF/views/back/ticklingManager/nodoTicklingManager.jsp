<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	
	ndPanel_replyTickling_content = new nicEditor({fullPanel : true}).panelInstance('replyTickling_content');
	ndinstance1_replyTickling_content = ndPanel_replyTickling_content.nicInstances[0];
	
	ndPanel_replyTickling_replyContent = new nicEditor({fullPanel : true}).panelInstance('replyTickling_replyContent');
	ndinstance1_replyTickling_replyContent = ndPanel_replyTickling_replyContent.nicInstances[0];
</script>
<div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/TicklingManagerController/showNotdoTicklingManager.do" method="POST" id="notdoTicklingManagerForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>会员留言处理</h5>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>会员账号</th>
								<th>标题</th>
								<th>日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>${item.memberNumber}</td>
									<td>${item.title}</td>
									<td>${item.ticklingDate}</td>
									<td>
									<a href="#myModal" data-toggle="modal" class="btn" onclick="replyTickling('${item.id}')">回复</a>
									<button class="btn" onclick="deleteTickling('${item.id}')">删除</button>
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

<div id="myModal" class="modal hide" style="width: 800px;height: 500;overflow: auto;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>会员留言回复</h3>
	</div>
	<div class="widget-content nopadding">
		<form action="/TicklingManagerController/replyTickling.do" method="post"
			id="replyTicklingForm" class="form-horizontal">
			<input type="hidden" class="span11" name="id" id="replyTickling_id" />
			<div class="control-group">
				<label class="control-label">
					标题:
				</label>
				<div class="controls">
					<input type="text" class="span11" name="title" readonly="readonly"
						id="replyTickling_title" style="width:220;height:25"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					内容:
				</label>
				<div class="control-group">
					<div class="controls">
						<textarea class="textarea_editor span12" rows="6" name="content" readonly="readonly"
							id="replyTickling_content" style="width: 500px;height:300px;" ></textarea>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					回复内容:
				</label>
				<div class="control-group">
					<div class="controls">
						<textarea class="textarea_editor span12" rows="6"
							name="replyContent" id="replyTickling_replyContent"
							style="width: 500px;height:300px;" ></textarea>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="form-actions">	
	<button type="submit" class="btn btn-success" onclick="saveTickling('replyTicklingForm')">
		确认回复
	</button>
</div>
</div>