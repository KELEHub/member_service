<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
            <div class="widget-box">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
          <h5>Form Elements</h5>
        </div>
        <div class="widget-content nopadding">
          <form class="form-horizontal">
            <div class="control-group">
              <label class="control-label">Tooltip Input</label>
              <div class="controls">
                <input type="text" placeholder="Hover for tooltip鈥� data-title="A tooltip for the input" class="span11 tip" data-original-title="">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Type ahead Input</label>
              <div class="controls">
                <input type="text" placeholder="Type here for auto complete鈥� style="margin: 0 auto;" data-provide="typeahead" data-items="4" data-source="[&quot;Alabama&quot;,&quot;Alaska&quot;,&quot;Arizona&quot;,&quot;Arkansas&quot;,&quot;California&quot;,&quot;Colorado&quot;,&quot;Ahmedabad&quot;,&quot;India&quot;,&quot;Florida&quot;,&quot;Georgia&quot;,&quot;Hawaii&quot;,&quot;Idaho&quot;,&quot;Illinois&quot;,&quot;Indiana&quot;,&quot;Iowa&quot;,&quot;Kansas&quot;,&quot;Kentucky&quot;,&quot;Louisiana&quot;,&quot;Maine&quot;,&quot;Maryland&quot;,&quot;Massachusetts&quot;,&quot;Michigan&quot;,&quot;Minnesota&quot;,&quot;Mississippi&quot;,&quot;Missouri&quot;,&quot;Montana&quot;,&quot;Nebraska&quot;,&quot;Nevada&quot;,&quot;New Hampshire&quot;,&quot;New Jersey&quot;,&quot;New Mexico&quot;,&quot;New York&quot;,&quot;North Dakota&quot;,&quot;North Carolina&quot;,&quot;Ohio&quot;,&quot;Oklahoma&quot;,&quot;Oregon&quot;,&quot;Pennsylvania&quot;,&quot;Rhode Island&quot;,&quot;South Carolina&quot;,&quot;South Dakota&quot;,&quot;Tennessee&quot;,&quot;Texas&quot;,&quot;Utah&quot;,&quot;Vermont&quot;,&quot;Virginia&quot;,&quot;Washington&quot;,&quot;West Virginia&quot;,&quot;Wisconsin&quot;,&quot;Wyoming&quot;]" class="span11">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Prepended Input</label>
              <div class="controls">
                <div class="input-prepend"> <span class="add-on">#</span>
                  <input type="text" placeholder="prepend" class="span11">
                </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Appended Input</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" placeholder="5.000" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group warning">
              <label class="control-label" for="inputWarning">Input with warning</label>
              <div class="controls">
                <input type="text" id="inputWarning" class="span11">
                <span class="help-inline">Something may have gone wrong</span> </div>
            </div>
            <div class="control-group error">
              <label class="control-label" for="inputError">Input with error</label>
              <div class="controls">
                <input type="text" id="inputError" class="span11">
                <span class="help-inline">Please correct the error</span> </div>
            </div>
            <div class="control-group info">
              <label class="control-label" for="inputInfo">Input with info</label>
              <div class="controls">
                <input type="text" id="inputInfo" class="span11">
                <span class="help-inline">Username is already taken</span> </div>
            </div>
            <div class="control-group success">
              <label class="control-label" for="inputSuccess">Input with success</label>
              <div class="controls">
                <input type="text" id="inputSuccess" class="span11">
                <span class="help-inline">Woohoo!</span> </div>
            </div>
          </form>
        </div>
      </div>
      </div>
	</div>
</div>
<div id="myModal" class="modal hide">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">×</button>
		<h3>添加角色</h3>
	</div>
	<div class="modal-body">
		<form action="/rolemanage/insertRole.do" method="POST" id="saveRoleForm">
			<div class="control-group">
				<label class="control-label">角色名称</label>
				<div class="controls">
					<input type="text"  placeholder="角色名称" name="roleNm" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">角色描述</label>
				<div class="controls">
					<input type="text" placeholder="角色描述" name="roleDsc"/>
				</div>
			</div>
		</form>
		<button class="btn btn-large" onclick="saveRole('saveRoleForm')">保存</button>
	</div>
</div>