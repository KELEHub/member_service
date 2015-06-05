<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div class="container-fluid" id="content-header">
	<div class="row-fluid">
		<div class="span12">
            <div class="widget-box">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
          <h5><a>系统参数设置</a></h5>
          <form action="/ParameterController/show.do" method="POST" id="searchParameterForm">
	      </form>
        </div>
        <div class="widget-content nopadding">
          <form class="form-horizontal" action="/ParameterController/set.do" method="POST" id="parameterSetForm">
            <div class="control-group">
              <label class="control-label">天数:</label>
              <div class="controls">
                <div class="input-prepend"> <span class="add-on">#</span>
                  <input type="text" name="dayCount" value="${bean.dayCount}" class="span11">
                </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">是否提现:</label>
              <div class="controls">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">提现最高限额:</label>
                <div class="controls">
                <div class="input-append">
                  <input type="text" name="goldMax" value="${bean.goldMax}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">提现最小限额:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="goldMin" value="${bean.goldMin}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputWarning">提现手续费:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="goldTake" value="${bean.goldTake}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputError">积分转账最大金额:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="scoreMax" value="${bean.scoreMax}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputInfo">积分转账最小金额:</label>
              <div class="controls">
                <div class="input-append">
                  <input type="text" name="scoreMin" value="${bean.scoreMin}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputSuccess">积分转账手续费:</label>
               <div class="controls">
                <div class="input-append">
                  <input type="text" name="scoreTake" value="${bean.scoreTake}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputSuccess">将金币转换最小金额:</label>
               <div class="controls">
                <div class="input-append">
                  <input type="text" name="glbMin" value="${bean.glbMin}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputSuccess">将金币转换手续费:</label>
               <div class="controls">
                <div class="input-append">
                  <input type="text" name="glbTake" value="${bean.glbTake}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputSuccess">积分充值最小金额:</label>
               <div class="controls">
                <div class="input-append">
                  <input type="text" name="scoreInMin" value="${bean.scoreInMin}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputSuccess">积分充值手续费:</label>
               <div class="controls">
                <div class="input-append">
                  <input type="text" name="scoreInTake" value="${bean.scoreInTake}" class="span11">
                  <span class="add-on">$</span> </div>
              </div>
            </div>
          </form>
          <div class="form-actions">
                <input type="submit" value="设定" onclick="saveParameter('parameterSetForm')" class="btn btn-success">
          </div>
        </div>
      </div>
      </div>
	</div>
</div>
