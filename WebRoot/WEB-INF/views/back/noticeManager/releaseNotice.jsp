<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="row-fluid">
	<div class="span6">
		<div class="widget-box">
			<div class="widget-title">
				<span class="icon"> <i class="icon-align-justify"></i> </span>
				<h5>
					发布最新公告
				</h5>
				<form action="/NoticeManagerController/show.do"  method="post" id="showNoticeForm" ></form>
			</div>
			<div class="widget-content nopadding">
				<form action="/NoticeManagerController/releaseNotice.do" method="post" id="releaseNoticeForm" class="form-horizontal">
					<div class="control-group">
						<label class="control-label">
							标题:
						</label>
						<div class="controls">
							<input type="text" class="span11" placeholder="标题内容" name="title" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							类型:
						</label>
						<div class="controls">
							<select name="category">
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
						<div class="controls">
							<div class="widget-content">
								<div class="control-group">
									<form>
										<div class="controls">
											<textarea class="textarea_editor span12" rows="6"
												placeholder="请输入公告内容 ..." name="content"></textarea>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="form-actions">
	<button type="submit" class="btn btn-success" onclick="saveNotice('releaseNoticeForm')">
		确认发布
	</button>
</div>
