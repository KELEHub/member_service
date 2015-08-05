<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
// ['save','bold','italic','underline','left','center','right','justify','ol','ul','fontSize','fontFamily','fontFormat','indent','outdent','image','upload','link','unlink','forecolor','bgcolor']
	frontndPanel = new nicEditor({fullPanel : true}).panelInstance('front_releaseNotice_context');
	frontndPanel = frontndPanel.nicInstances[0];
	frontndPanel.setContent($("#hiddenDivContents").html());
	
	$("#frontLastestNews").find("div[unselectable]").each(function(){
		$(this).css('display','none'); 
	});
</script>
<div style="overflow-x:hidden; height:85%">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>公告内容</h3>
	</div>
<div  style="width: 800px;height:auto;position: relative;padding: 15px;overflow-y: auto;">
	<div class="widget-box">
		<div class="widget-content nopadding">
			<div class="control-group">
				<h3 align="center">
					${result.title}
				</h3>
			</div>
			<br />
			<br />
			<div class="control-group" id="frontLastestNews">
				<div id="front_releaseNotice_context" style="width: 750px; overflow:auto;"></div>
<!-- 					<textarea style="background:transparent;border-style:none;width: 750px;" id="front_releaseNotice_context"></textarea> -->
<!-- 			<textarea cols="50" id="front_releaseNotice_context" name="content"  style="width: 90%; overflow:auto; border: 1px solid #000;"></textarea> -->
			</div>
		</div>
	</div>
	<div id="hiddenDivContents" style="display: none;">
	 ${result.content}
	</div>
</div>
</div>
