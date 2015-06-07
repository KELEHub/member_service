<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="container-fluid" id="content-header">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"> <i class="icon-align-justify"></i> </span>
					<h5>
						<a>每周报单限制</a>
					</h5>
					  <form action="/LimitDeclarationController/show.do" method="POST" id="searchLimiteForm">
	                </form>
				</div>
				<div class="widget-content nopadding">
					<form class="form-horizontal"
						action="/LimitDeclarationController/set.do" method="POST"
						id="declarationForm">
						<div class="control-group">
							<label class="control-label">报单进度比：</label>
							<div class="controls">
								<div class="input-append">
									<label>${bean.declarationIndex}</label>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">最大报单额：</label>
							<div class="controls">
								<div class="input-append">
									<input type="text" name="maxDeclaration" value="${bean.maxDeclaration}"
										class="span11">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">累计报单额：</label>
							<div class="controls">
								<div class="input-append">
										<label>${bean.declarationAll}</label>
								</div>
							</div>
						</div>
					</form>
					<div class="form-actions">
						<input type="submit" value="设定"
							onclick="saveLimeteDeclaration('declarationForm')"
							class="btn btn-success">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
