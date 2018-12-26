<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>自动化测试管理平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- basic styles -->
<link rel="stylesheet" href="/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
    href="/assets/css/material-design-iconic-font-2.2.0/css/material-design-iconic-font.css" />
<link rel="stylesheet" href="/assets/css/pagination.css">
<link rel="stylesheet" href="/assets/css/reset.css">
<!-- 样式初始化 -->
<link rel="stylesheet" href="/css/jquery.mCustomScrollbar.min.css">
<!-- 滚动条样式化 -->


<script src="/assets/js/date-time/moment.min.js"></script>


<!-- page specific plugin styles -->

<!-- fonts -->

<link rel="stylesheet"
    href="fonts.googleapis.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->
<link rel="stylesheet" href="/assets/css/ace.min.css" />
<link rel="stylesheet" href="/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="/assets/css/ace-skins.min.css" />
<script src="/assets/js/ace-extra.min.js"></script>

<script src="/js/jquery-1.9.0.min.js"></script>
</head>
<#include "index-left.ftl" />
<body>
	
			
<div class="content_main container-fluid">
    <div id="iframe_home" class="iframe cur">
   <div>
				<!-- form表单 start-->
  <div class="form-group" style="padding-left:80px;">
		  	<label><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;测试计划管理</h3></label>
		</div>  	
		<br>
		<!--
		<div class="form-group" style="padding-left:80px;">
		  	<label for="search" ><h4>查询条件</h4></label>
		</div>  	
		<br>
  			  	
		 	<div class="form-group" style="padding-left: 80px;">
						<label for="projectname">项目名称</label> <select id="select"
							style="width: 150px; heigth: 20px;" onchange="b()">
							<#list planListOutDto.planListDetailDtoList as pl>
								<option value="${pl.planId}">${pl.planName}中台系统</option>
							</#list>
						</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					
						<div class="form-group" style="padding-left: 80px;">
						<label for="projectname">模块名称</label> <select id="select"
							style="width: 150px; heigth: 20px;" onchange="b()">
							<option value="1">在线承保</option>
							<option value="2">产品平台</option>
								<option value="3">通知书模块</option>
						</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
 
	      <div class="form-group">
	    			<label for="plantime">计划运行时间</label>
	    			<!--指定 date标记-->  <!--
                <input type='text' id='datepicker'  placeholder="请输入计划运行时间"/>  
	      	</div>			
-->
           <br>
           <br>

					<div class="row" style="margin-left:270px;max-width:80%; height:70px;" >
					
					<p>
					<!--
						<button class="btn btn-primary">查询 </button>
						-->
						<button type="button" class="btn btn-primary" data-toggle="modal"
								data-target="#Addjurisdiction">增加</button>
						<!--
						<input type="checkbox" />执行中的
						<input type="checkbox" />未执行的
						-->
					</p>
						
				<!-- PAGE CONTENT ENDS -->
		  </div><!-- /.row -->

					<!--table start -->
		
							<div class="table-responsive">
								<table class="table table-bordered table-hover"
									style="margin-left: 270px; width: auto">
									<thead>
										<tr>
											<th><input type="checkbox"></th>
											<th>计划id</th>
											<th>计划名称</th>
											<th>计划类型</th>
											<th>计划状态</th>
											<th>创建人</th>
											<th>修改人</th>
											<th>创建时间</th>
											<th>修改时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<#list planListOutDto.planListDetailDtoList as pl>
									<tbody>
										<tr>
											<td><input type="checkbox"></td>
											<td>${pl.planId}</td>
											<td><a href="/plan/planDetail?planId=${pl.planId}">${pl.planName}</a></td>
											<td>${pl.planType}</td>
											<td><#if pl.planStatus?? && pl.planStatus == -1>
									                <span style="color:red">弃用</span>
									            <#elseif pl.planStatus?? && pl.planStatus == 0>
									                <span style="color:green">正常/未执行</span>
									            <#elseif pl.planStatus?? && pl.planStatus == 1>
									                <span style="color:yellow">执行中</span>
									            </#if>
									        </td>
											<td>${pl.createUserId}</td>
											<td>${pl.updateUserId}</td>
											<td> ${pl.createTime?string('YYYY-MM-dd HH:mm:ss')}</td>
											<td> ${pl.updateTime?string('YYYY-MM-dd HH:mm:ss')}</td>
								            <form id="deletePlan${pl.planId}">
											<td><a href="/plan/planDetail?planId=${pl.planId}"><button
														type="button" class="btn btn-info btn-xs">查看计划详情</button></a>
												<button ng-click="showDlg(true)" class="btn btn-info btn-xs"
													data-toggle="modal" data-target="#changePermission">修改</button>
                                                <input type="hidden" name="planIdList[0]" value="${pl.planId}">										    
												<a href="javascript:void(0)" onclick="submitDeletePlan('${pl.planId}');return false;">
												<button class="btn btn-info btn-xs">标记弃用</button></a>
												<#if pl.planStatus == 0 >
										              <a href="/plan/runPlan?planId=${pl.planId}&status=1">
										              <button type="button" class="btn btn-info btn-xs" >执行</button></a>
										          <#elseif pl.planStatus == -1 >
										             <span style="color:grey">已弃用</span>
										          <#elseif pl.planStatus != 0 >
										              <a href="/plan/stopPlan?planId=${pl.planId}&status=0">
										              <button type="button" class="btn btn-info btn-xs" >停止</button></a>
										          </#if>														
											</td>													
											</form>
										</tr>
									</tbody>
									</#list>
								</table>
							</div>

							<div class="content_main container-fluid">
							

								<div class="pages" style="padding-left: 300px;">
									<div id="Pagination"></div>
									<div class="searchPage">
										<span class="page-sum">共<strong class="allPage">15</strong>页
										</span> <span class="page-go">跳转<input type="text">页
										</span> <a href="javascript:;" class="page-btn">GO</a>
									</div>
								</div>
								
								<div class=""
									style="margin-right: 270px; height: 85px; line-height: 85px; ">
									<span>共 45 条记录，</span> <span>当前显示第 1 页</span>
								</div>
		
							</div>



						</div>
					</div>
					<!--./content_main container-fluid  -->			
 
 
 	<!--添加模态框开始-->
				<div class="modal fade" id="Addjurisdiction">
					<div class="modal-dialog">
						<div class="modal-content message_align" style="width: auto;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<h4 class="modal-title">添加计划</h4>
							</div>
							
                            <div class="modal-body" style="height: 300px;">
                                <form id="addPlan" class="form-horizontal" role="form" >
                                    <div class="form-group">
                                        <label for="selectproject" class="col-sm-3 control-label">计划名称：</label>
                                        <div class="col-sm-9">
                                            <input type="text" name="planDto.planName">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="selectCar" class="col-sm-3 control-label">计划描述：</label>
                                        <div class="col-sm-9">
                                            <textarea rows="5" cols="50" name="planDto.planIntro"></textarea>                                            
                                        </div>
                                    </div>
                                    <!--
                                    <div class="form-group">
                                        <label for="selectCar" class="col-sm-3 control-label">计划类型：</label>
                                        <div class="col-sm-9">
                                            <select name="planDto.planType">
                                            <option value="0">立即执行</option>
                                            <option value="1">定时执行</option>
                                            </select>
                                        </div>
                                    </div>-->
                                    <input type="hidden" name="planDto.planType" value="1" />
                                     <div class="form-group">
                                        <label for="selectCar" class="col-sm-3 control-label">定时间隔：</label>
                                        <div class="col-sm-9">
                                            <select name="planDto.planInterval">
                                            <option value="9" >请选择</option>
                                            <option value="0" >每1分钟一次</option>
                                            <option value="1" >每5分钟一次</option>
                                            <option value="2" >每10分钟一次</option>
                                            <option value="3" >每30分钟一次</option>
                                            <option value="4" >每1小时一次</option>
                                            <option value="5" >每12小时一次</option>
                                            <option value="6" >每天一次</option>
                                            <option value="7" >每周一次</option>
                                            <option value="8" >每月一次</option>
                                            </select>（计划类型为定时时，默认每天12点执行）
                                        </div>
                                    </div>
                                </form>
                            </div>
														
							
							<div class="modal-footer">
								<a onclick="submitAddPlan();return false;" class="btn btn-primary"
									data-dismiss="modal">提交</a>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">取消</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
				<!--添加模态框结束-->
   
              
              <!--修改模态框开始-->
<div class="modal fade" id="changePermission">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title">修改测试用例</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                   <div class="form-group">
						<label for="selectproject" class="col-sm-3 control-label">项目名称：</label>
						<div class="col-sm-9">
							<select name="" class="form-control" id="selectproject">
								<option value="请选择项目名称">请选择项目名称</option>
								<option value="中台系统">中台系统</option>
								<option value="=核心系统">核心系统</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="selectCar2" class="col-sm-3 control-label">模块名称：</label>
						<div class="col-sm-9">
							<select name="" class="form-control" id="selectCar2">
								<option value="请选择模块名称">请选择模块名称</option>
								<option value="建议书系统">建议书系统</option>
								<option value="客户管理">客户管理</option>
								<option value="用户管理">用户管理</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="selectCar" class="col-sm-3 control-label">用例类型：</label>
						<div class="col-sm-9">
							<select name="" class="form-control" id="selectCar2">
								<option value="请选择模块名称">请选择用例类型</option>
								<option value="接口">接口</option>
								<option value="页面">页面</option>
							</select>
						</div>
					</div>
	
	
	
	
					<div class="form-group">
						<label for="selectCar" class="col-sm-3 control-label">用例名称：</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="menuName">
						</div>
					</div>
	
					<div class="form-group">
						<label for="selectCar" class="col-sm-3 control-label">用例说明：</label>
						<div class="col-sm-9">
							<textarea rows="5" cols="50"></textarea>
						</div>
					</div>
	
				</form>
            </div>
            <div class="modal-footer">
                <a  onclick="urlSubmit()" class="btn btn-info" data-dismiss="modal">提交</a>
                <button type="button"  class="btn btn-info" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--修改模态框结束-->
 

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
			window.jQuery || document.write("<script src='/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="/assets/js/bootstrap.min.js"></script>
		<script src="/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->

	
    <script src="/assets/js/typeahead-bs2.min.js"></script>
    <script src="/assets/js/ace-elements.min.js"></script>
    <script src="/assets/js/ace.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/jquery.pagination.js"></script>

		<!-- inline scripts related to this page -->

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
		function a() {
			$('#projectname').val($('select option:selected').text());
		}
		function b() {
			$('#projectname').val($('select option:selected').text());
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#Pagination").pagination("15");
		});
	</script>
				

 

  </body>
<script src="/js/addPlan.js"></script>
</html>
