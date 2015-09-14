<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	
	ndPanel2_checkTickling_content = new nicEditor({fullPanel : true}).panelInstance('checkTickling_content');
	ndinstance12_checkTickling_content = ndPanel2_checkTickling_content.nicInstances[0];
	
	ndPanel2_checkTickling_replyContent = new nicEditor({fullPanel : true}).panelInstance('checkTickling_replyContent');
	ndinstance12_checkTickling_replyContent = ndPanel2_checkTickling_replyContent.nicInstances[0];
</script>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/TicklingManagerController/showDoneTicklingManager.do" method="POST" id="doneTicklingManagerForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
		<div class="widget-content nopadding">
					<form class="form-horizontal"  action="/TicklingManagerController/searchDoneTicklingManager.do" method="POST"
						id="searchTickForm">
						<div class="control-group">
							<label class="control-label">会员账号：</label>
							<div class="controls">
								<div class="input-append">
										<input type="text" name="memberNumber" id="memberNumber"
										class="span11">
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<input type="submit" value="检索"
							onclick="searchUserHistory('searchTickForm')"
							class="btn btn-success">
					</div>
				</div>
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i></span>
					<h5>会员留言记录</h5>
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
									<td>${item.replyDate}</td>
									<td>
									<a href="#myModal" data-toggle="modal" class="btn" onclick="checkTickling('${item.id}')">查看</a>
									<button class="btn" onclick="deleteDoneTickling('${item.id}')">删除</button>
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
		<button data-dismiss="modal" class="close" type="button">
			×
		</button>
		<h3>
			历史留言查看
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
					id="checkTickling_title" style="width:220;height:25"/>
			</div>
		</div>
		<div class="control-group">
				<label class="control-label">
					内容:
				</label>
				<div class="control-group">
					<div class="controls">
						<textarea class="textarea_editor span12" rows="6" name="content"
							id="checkTickling_content" style="width: 500px;height:300px;"></textarea>
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
							name="replyContent" id="checkTickling_replyContent"
							style="width: 500px;height:300px;"></textarea>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>