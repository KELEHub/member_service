<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	ndPanel_releaseTickling_content = new nicEditor({fullPanel : true}).panelInstance('releaseTickling_content');
	ndinstance1_releaseTickling_content = ndPanel_releaseTickling_content.nicInstances[0];
	
	ndPanel_inbox_content = new nicEditor({fullPanel : true}).panelInstance('inbox_content');
	ndinstance1_inbox_content = ndPanel_inbox_content.nicInstances[0];
	
	ndPanel_replyContent = new nicEditor({fullPanel : true}).panelInstance('replyContent');
	ndinstance1_replyContent = ndPanel_replyContent.nicInstances[0];
</script>
<div id="content-header">
	<div id="breadcrumb">
		<a href="#" title="Go to Home" class="tip-bottom"><i
			class="icon-home"></i> Home</a>
		<a href="#" class="current">Buttons &amp; icons</a>
	</div>
	<form action="/InboxController/showInbox.do" method="POST"
		id="inboxForm">
	</form>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i> </span>
					<h5>
						站内信
					</h5>
				</div>
				<div class="form-actions">
				<a href="#myModal" data-toggle="modal" class="btn">发布留言</a>
				</div>
				<div class="widget-content nopadding">
					<table id="testexample1" class="table table-bordered data-table">
						<thead>
							<tr>
								<th>
									标题
								</th>
								<th>
									留言日期
								</th>
								<th>
									状态
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result}">
								<tr class="gradeX">
									<td>
										${item.title}
									</td>
									<td>
										${item.ticklingDate}
									</td>
									<td>
									<c:if test="${item.state eq 0}">未回复</c:if> 
									<c:if test="${item.state eq 1}">已回复</c:if>
									</td>
									<td>
									<a href="#myModal2" data-toggle="modal" class="btn" onclick="checkInboxInfo('${item.id}')">查看</a>
									<button class="btn" onclick="frontDeleteDoneTickling('${item.id}')">删除</button>
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

<div id="myModal" class="modal hide" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>发布留言</h3>
	</div>
	<div class="widget-content nopadding">
		<form action="/InboxController/releaseTickling.do" method="post"
			id="releaseTicklingForm" class="form-horizontal">
<!--			<input type="hidden" class="span11" name="id" id="replyTickling_id" />-->
			<div class="control-group">
				<label class="control-label">
					标题:
				</label>
				<div class="controls">
					<input type="text" class="span11" name="title"
						id="releaseTickling_title" style="width:220;height:25"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					内容:
				</label>
				<div class="control-group">
					<div class="controls">
						<textarea class="textarea_editor span12" rows="6" name="content"
							id="releaseTickling_content" style="width: 500px;height:300px;overflow: auto;"></textarea>
					</div>
				</div>
			</div>
		</form>
	</div>
			<div class="form-actions">	
	<button type="submit" class="btn btn-success" onclick="releaseTickling('releaseTicklingForm')">
		确认发布
	</button>
</div>
</div>

<div id="myModal2" class="modal hide" style="width: 800px;height: 500;overflow: auto;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">
			×
		</button>
		<h3>
			留言查看
		</h3>
	</div>
	<div class="widget-content nopadding">
	<form class="form-horizontal">
		<div class="control-group">
			<label class="control-label">
				标题:
			</label>
			<div class="controls">
				<input type="text" class="span11" name="title" readonly="readonly"
					id="inbox_title" style="width:220;height:25"/>
			</div>
		</div>
		<div class="control-group">
				<label class="control-label">
					内容:
				</label>
				<div class="control-group">
					<div class="controls">
					<textarea class="textarea_editor span12" rows="6" name="content"
							id="inbox_content" style="width: 500px;height:300px;" readonly="readonly"></textarea>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					回复内容:
				</label>
				<div class="control-group">
					<div class="controls">
					<textarea class="textarea_editor span12" rows="6" name="content"
							id="replyContent" style="width: 500px;height:300px;" readonly="readonly"></textarea>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>