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
									留言内容
								</th>
								<th>
									留言日期
								</th>
								<th>
									回复内容
								</th>
								<th>
									状态
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
										${item.content}
									</td>
									<td>
										${item.ticklingDate}
									</td>
									<td>
										${item.replyContent}
									</td>
									<td>
										${item.state}
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

<div id="myModal" class="modal hide">
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
							id="releaseTickling_content" style="width: 220;"></textarea>
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