<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Buttons &amp; icons</a></div>
    <form action="/FAQController/showFAQ.do" method="POST" id="FAQForm">
	</form>
  </div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"><i class="icon-th"></i>
					</span>
					<h5>
						常见问题
					</h5>
				</div>
				<center>
				<div class="control-group">
					<div class="controls">
						<textarea class="textarea_editor span12" rows="6" name="content" readonly="readonly"
							id="FAQ_content" style="width: 100%; height: 50%; resize:none;">${result.content}</textarea>
					</div>
				</div>
				</center>
			</div>
		</div>
	</div>
</div>