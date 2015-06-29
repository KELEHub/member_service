<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="showLatestNewsDetail" class="modal" style="width: 800px;">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>公告内容</h3>
	</div>
	<div class="modal-body">
		<div class="widget-box">
			<div class="widget-content nopadding">
					<div class="control-group">
					<label class="control-label">公告标题</label>
						<div class="controls">
							<input type="text" value="${result.title}"/>
						</div>
<!--					<h1>${result.title}</h1>-->
					</div>
					<div class="control-group">
<!--					${result.content}-->
					</div>
			</div>
		</div>
	</div>
</div>