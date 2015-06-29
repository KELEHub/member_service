<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>公告内容</h3>
	</div>
<div class="modal-body">
	<div class="widget-box">
		<center>
			<div class="widget-content nopadding">
				<div class="control-group">
					<h3>
						${result.title}
					</h3>
				</div>
				<br/><br/>
				<div class="control-group">
					${result.content}
				</div>
			</div>
		</center>
	</div>
</div>
