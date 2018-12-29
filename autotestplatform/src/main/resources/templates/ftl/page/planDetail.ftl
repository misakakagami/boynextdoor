<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>自动化测试管理平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- basic styles -->
     <link rel="stylesheet" href="/assets/css/font-awesome.min.css" />
     <link rel="stylesheet" href="/assets/css/bootstrap.min.css" />
     <link rel="stylesheet" href="/assets/css/material-design-iconic-font-2.2.0/css/material-design-iconic-font.css" />
     <link rel="stylesheet" href="/assets/css/pagination.css">
     
     
     <script src="/assets/js/date-time/moment.min.js"></script>
     <script type="text/javascript" src="/assets/js/jquery.pagination.js"></script>
     <script src="/assets/js/jquery-1.9.1.js"></script>
     <script src="/assets/js/index.js"></script>
     
    
    <!-- page specific plugin styles -->

				<!-- fonts -->
		
				<link rel="stylesheet" href="fonts.googleapis.com/css?family=Open+Sans:400,300" />
		
				<!-- ace styles -->
				<link rel="stylesheet" href="/assets/css/ace.min.css" />
				<link rel="stylesheet" href="/assets/css/ace-rtl.min.css" />
				<link rel="stylesheet" href="/assets/css/ace-skins.min.css" />
    <script src="/assets/js/ace-extra.min.js"></script>
 
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/addPlan.js"></script>
  </head>
<#include "index-left.ftl" />
  <body>
 <div class="main-content">
	<div class="page-content">
	 
	 <!-- form表单 start-->
  <div class="form-group" style="padding-left:80px;">
		  	<label><h3>计划详细信息</h3></label>
		</div>  	
		<br>
<a href="/plan/planList">返回计划列表</a>
		   
<form id="updatePlan">
	 <!-- 项目详情 -->
		<div class="profile-user-info profile-user-info-striped" style="margin-left:80px;">
			<div class="profile-info-row">
				<div class="profile-info-name"> 计划id </div>

				<div class="profile-info-value">
					<span class="editable" id="username">${planDetailOutDto.planId}</span>
					<input type="hidden" name="planDto.planId" value="${planDetailOutDto.planId}" />
				</div>
			</div>

			<div class="profile-info-row">
				<div class="profile-info-name"> 计划名称 </div>

					<div class="profile-info-value">
					<span class="editable" id="username"><input type="text" name="planDto.planName" value="${planDetailOutDto.planName}" /></span>
				</div>
			</div>

			<div class="profile-info-row">
				<div class="profile-info-name"> 计划说明</div>

				<div class="profile-info-value">
					<span class="editable" id="age"><textarea name="planDto.planIntro" >${planDetailOutDto.planIntro}</textarea></span>
				</div>
			</div>

           	<div class="profile-info-row">
				<div class="profile-info-name"> 计划类型</div>

				<div class="profile-info-value">
					<span class="editable" id="signup">
						<select name="planDto.planType">
						    <option value="0" <#if (planDetailOutDto.planType)==0>selected="selected"</#if>>执行一次</option>
						    <option value="1" <#if (planDetailOutDto.planType)==1>selected="selected"</#if>>定时执行</option>
						</select></span>
				</div>
			</div>
			
			<div class="profile-info-row">
				<div class="profile-info-name"> 计划状态</div>
				<input type="hidden" name="planDto.planStatus" value="${planDetailOutDto.planStatus}" />
				<div class="profile-info-value">
					<span class="editable" id="planStatusTd${planDetailOutDto.planId}" name="planStatusClass">
						<#if planDetailOutDto.planStatus?? && planDetailOutDto.planStatus == -1>
			                <span style="color:red">弃用</span>
			            <#elseif planDetailOutDto.planStatus?? && planDetailOutDto.planStatus == 0>
			                <span style="color:green">正常/未执行</span>
			            <#elseif planDetailOutDto.planStatus?? && planDetailOutDto.planStatus == 1>
			                <span style="color:yellow">执行中</span>
			            </#if>
					</span>
				</div>
			</div>

				<div class="profile-info-row">
				<div class="profile-info-name"> 计划开始时间</div>

				<div class="profile-info-value">
					<span class="editable" id="signup">${planDetailOutDto.planStartTime?string('yyyy-MM-dd HH:mm:ss')}</span>
				</div>
				</div>
				
				<!--
				<div class="profile-info-row">
				<div class="profile-info-name"> 计划执行次数</div>

				<div class="profile-info-value">
					<span class="editable" id="signup">20</span>
				</div>
				</div>
		-->
				
				<div class="profile-info-row">
				<div class="profile-info-name"> 执行间隔</div>

				<div class="profile-info-value">
					<select name="planDto.planInterval">
    <option value="9" >请选择</option>
    <option value="0" <#if (planDetailOutDto.planInterval)==0>selected="selected"</#if>>每1分钟一次</option>
    <option value="1" <#if (planDetailOutDto.planInterval)==1>selected="selected"</#if>>每5分钟一次</option>
    <option value="2" <#if (planDetailOutDto.planInterval)==2>selected="selected"</#if>>每10分钟一次</option>
    <option value="3" <#if (planDetailOutDto.planInterval)==3>selected="selected"</#if>>每30分钟一次</option>
    <option value="4" <#if (planDetailOutDto.planInterval)==4>selected="selected"</#if>>每1小时一次</option>
    <option value="5" <#if (planDetailOutDto.planInterval)==5>selected="selected"</#if>>每12小时一次</option>
    <option value="6" <#if (planDetailOutDto.planInterval)==6>selected="selected"</#if>>每天一次</option>
    <option value="7" <#if (planDetailOutDto.planInterval)==7>selected="selected"</#if>>每周一次</option>
    <option value="8" <#if (planDetailOutDto.planInterval)==8>selected="selected"</#if>>每月一次</option>
</select>（计划类型为定时时，默认每天12点执行）
					<span class="editable" id="signup">
</span>
				</div>
			</div>
				
			<div class="profile-info-row">
				<div class="profile-info-name"> 计划修改确认</div>

				<div class="profile-info-value">
					<span class="editable" id="signup">
						<button type="button" class="btn btn-info" data-dismiss="modal"  value="修改" onclick="submitUpdatePlan();return false;" >修改</button>
					</span>
				</div>
			</div>
			
			<div class="profile-info-row">
				<div class="profile-info-name"> 执行计划</div>

				<div class="profile-info-value">
					<span class="editable" id="excBtn${planDetailOutDto.planId}" >
						<#if planDetailOutDto.planStatus == 0 >
				              <button type="button" class="btn btn-info" data-dismiss="modal" onclick="runPlan(${planDetailOutDto.planId});return fales;" >执行</button>
				          <#elseif planDetailOutDto.planStatus == -1 >
				             <span style="color:grey">已弃用</span>
				          <#elseif planDetailOutDto.planStatus != 0 >
				              <a href="/plan/stopPlan?planId=${planDetailOutDto.planId}&status=0">
				              <button type="button" class="btn btn-info" data-dismiss="modal" onclick="stopPlan(${planDetailOutDto.planId});return fales;" >停止</button></a>
				          </#if>
					</span>
				</div>
			</div>
</form>
		
		<!--
				<div class="profile-info-row">
				<div class="profile-info-name"> 计划创建时间</div>

				<div class="profile-info-value">
					<span class="editable" id="signup">2018-05-08</span>
				</div>
			</div>
			
				<div class="profile-info-row">
				<div class="profile-info-name"> 计划修改时间</div>

				<div class="profile-info-value">
					<span class="editable" id="signup">2018-05-10</span>
				</div>
			</div>
			
			<div class="profile-info-row">
				<div class="profile-info-name"> 计划创建人</div>

				<div class="profile-info-value">
					<span class="editable" id="signup">张三</span>
				</div>
			</div>
			
				<div class="profile-info-row">
				<div class="profile-info-name"> 计划修改人</div>

				<div class="profile-info-value">
					<span class="editable" id="signup">李四</span>
				</div>
			</div>
			-->
           		<div class="profile-info-row">
					<div class="profile-info-name"> 用例详细信息</div>

					<div class="profile-info-value">
				
				
<form id="addUseCaseToPlan">
项目：<select id="projectList" onchange="getModelIdNameList()">
    <option value="-1">请选择</option>
    <#list planDetailOutDto.projectIdNameList as pro>
        <option value="${pro.projectId}">${pro.projectName}</option>
    </#list>
</select>
模块：<select id="modelList" onchange="getUseCaseIdNameList()" name="modelId">
        <option value="-1">需要先选择项目</option>
</select>
用例：<select id="useCaseList" name="useCaseId">
        <option value="-1">需要先选择模块</option>
</select>
<input type="submit" class="btn btn-info" data-dismiss="modal" onclick="submitAddUseCaseToPlan('${planDetailOutDto.planId}');return false;" value="添加" />
</form>
已有用例：
				<div class="table-responsive">
					<table id="planUseCaseList" class="table table-bordered table-hover"
						style="margin-left: 270px; width: auto">
						<thead>
							<tr>
								<th><input type="checkbox"></th>
								<th>项目名称</th>
								<th>模块名称</th>
                                <th>用例名称</th>
								<th>用例状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
    <#list planDetailOutDto.planUseCaseList as pl>
							<tr>
								<td><input type="checkbox"></td>
								<td>${pl.projectName}</td>
                                <td>${pl.modelName}</td>
								<td>${pl.useCaseName}</td>
								<td>${pl.useCaseStatus}</td>
								<td><form id="deleteUPR${pl.useCaseId}">
                    <input type="hidden" name="useCasePlanRelationId" value="${pl.useCasePlanRelationId}">
                    <a href="javascript:void(0)" onclick="submitDeleteUPR('${planDetailOutDto.planId}','${pl.useCaseId}');return false;">删除</a>
                </form></td>
        </form>
							</tr>
</#list>

						</tbody>
					</table>
				</div>
					</div>
				</div>
				
           
    <!-- form表单 end-->
	</div><!-- /.page-content -->
</div><!-- /.main-content --> 
</div><!-- /.main-container-inner -->
</div><!--/.main-container  -->

		<!-- basic scripts -->

		<!--[if !IE]> -->

		<script src="http://cdn.bootcss.com/jquery/2.0.3/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="/assets/js/bootstrap.min.js"></script>
		<script src="/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->

		<script src="/assets/js/ace-elements.min.js"></script>
		<script src="/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<!-- 上传按钮样式 -->
		<style data-for="result" type="text/css" id="css_newi_result">
						  .file {
				    position: absolute;
				    display: inline-block;
				    background: #D0EEFF;
				    padding: 3px 9px;
				    overflow: hidden;
				    color: #1E88C7;
				    text-decoration: none;
				    text-indent: 0;
				    line-height: 20px;
				}
				.file input {
				    position: absolute;
				    font-size: 100px;
				    right: 0;
				    top: 0;
				    opacity: 0;
				}
				.file:hover {
				    background: #d9edf7;
				    border-color: #78C3F3;
				    color: #004974;
				    text-decoration: none;
				}
	</style>
	

		<script type="text/javascript">
			jQuery(function($) {
				$('#loading-btn').on(ace.click_event, function () {
					var btn = $(this);
					btn.button('loading')
					setTimeout(function () {
						btn.button('reset')
					}, 2000)
				});
			
				$('#id-button-borders').attr('checked' , 'checked').on('click', function(){
						$('#default-buttons .btn').toggleClass('no-border');
				});
			})
			</script>
			<script type="text/javascript">
        function a(){
        	$('#projectname').val($('select option:selected').text());
        }
        function b(){
        	$('#projectname').val($('select option:selected').text());
        }
        
   </script>
 
 

  </body>
</html>
<script src="/js/longLink.js"></script>
