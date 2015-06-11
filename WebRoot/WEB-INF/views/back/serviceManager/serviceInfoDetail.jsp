<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="serviceInfoDetailDiv" class="modal hide">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>详细信息</h3>
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