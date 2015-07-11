<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	ndPanel = new nicEditor({fullPanel : true}).panelInstance('releaseNotice_context');
	ndinstance = ndPanel.nicInstances[0];
</script>

<div class="container-fluid">
<div class="row-fluid"  style="width: 850px;">
	<div class="span6" style="width: 100%;">
		<div class="widget-box">
			<div class="widget-title">
				<span class="icon"> <i class="icon-align-justify"></i> </span>
				<h5>
					发布最新公告
				</h5>
				<form action="/NoticeManagerController/initNoticeEdit.do"  method="post" id="showNoticeForm" ></form>
			</div>
			<div class="widget-content nopadding">
<!--				<form action="/NoticeManagerController/releaseNotice.do"-->
<!--					method="post" id="releaseNoticeForm" class="form-horizontal">-->
					<div class="control-group" style="margin-left: 0.5cm">
						<label class="control-label">
							标题:
						</label>
						<div class="controls">
							<input type="text" class="span11" name="title" id="releaseNotice_title"/>
						</div>
					</div>
					<div class="control-group" style="margin-left: 0.5cm">
						<label class="control-label">
							类型:
						</label>
						<div class="controls">
							<select name="category" id="releaseNotice_category">
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
						<label class="control-label" style="margin-left: 0.5cm">
							内容:
						</label>
						<div class="widget-content">
							<div class="control-group">
								<div class="controls">
								<textarea cols="50" id="releaseNotice_context" name="content"  style="width: 750px; height: 300px; border: 1px solid #000;"></textarea>
<!--									<textarea class="textarea_editor span12" rows="6"-->
<!--										name="content"></textarea>-->
								</div>
							</div>
						</div>
					</div>
<!--				</form>-->
			</div>
		</div>
	</div>
</div>
</div>
<div class="form-actions">
	<button type="submit" class="btn btn-success" onclick="saveNotice(document.getElementById('releaseNotice_title').value,document.getElementById('releaseNotice_category').value,ndinstance.getContent())">
		确认发布
	</button>
</div>
