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
<link rel="stylesheet" href="/assets/css/reset.css">  <!-- 样式初始化 -->
<link rel="stylesheet" href="/css/jquery.mCustomScrollbar.min.css"> <!-- 滚动条样式化 -->


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
<script src="/js/addModel.js"></script>
</head>
<#include "index-left.ftl" />
<body>

<div class="content_main container-fluid">
    <div id="iframe_home" class="iframe cur">
   <div>
				<!-- form表单 start-->
				<form class="form-inline" id="getModelListForm" name="" action="/projectModel/getModelList" method="get">
					<div class="form-group" style="padding-left: 80px;">
						<label><h3>模块列表</h3></label>
					</div>
					<br>

					<div class="form-group" style="padding-left: 80px;">
						<label for="search"><h4>查询条件</h4></label>
					</div>
					<br>

					<div class="form-group" style="padding-left: 80px;">
						<label for="projectname">项目名称</label> <select id="select" name="projectId"
							style="width: 150px; heigth: 20px;" onchange="b()">
							<option value=""<#if !modelListOutDto.projectId??>selected="selected"</#if>>全部项目</option>
							<#list modelListOutDto.projectIdNameList as pro>
        <option value="${pro.projectId}" <#if modelListOutDto.projectId?? && pro.projectId==modelListOutDto.projectId>selected="selected"</#if>>${pro.projectName}</option>
    </#list>
						</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					
						<div class="form-group" style="padding-left: 80px;">
						<label for="projectname">模块名称</label> 
						<input type="text" name="searchContent" value="${(modelListOutDto.searchContent)!''}" />
					</div>
					<input type="hidden" name="pageDto.pageNow" value="1" />
							</form>
				<!-- form表单 end-->
				</div>
					<br> 

					<div class="buttons" style="padding-left: 270px;">
						<p>
							<button type="button" class="btn btn-primary" onclick="getModelList()">查询</button>
							<button type="button" class="btn btn-primary" data-toggle="modal"
								data-target="#Addjurisdiction">增加</button>
						</p>
					</div>

					<!--table start -->
		
							<div class="table-responsive">
								<table class="table table-bordered table-hover"
									style="margin-left: 270px; width: auto">
									<thead>
										<tr>
											<th><input type="checkbox"></th>
											<th>模块id</th>
											<th>模块名称</th>
                                            <th>项目名称</th>
											<th>模块网址</th>
											<th>模块状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
    <#list modelListOutDto.modelListOutDtoList as mol>
										<tr>
											<td><input type="checkbox"></td>
                                            <td>${mol.modelId}</td>
											<td>${mol.modelName}</td>
											<td><a href="/projectModel/getProjectDetail?projectDetailInDto.projectId=${mol.projectId}">${mol.projectName}</a></td>
											<td><a href="${mol.modelUrl}">关联网址</a></td>
											<td><#if mol.modelStatus?? && mol.modelStatus == -1>
                                                    <span style="color:red">弃用</span>
                                                <#else>
                                                    <span style="color:green">正常</span>
                                                </#if></td>
													<form id="deleteModel${mol.modelId}">
											<td><a href="/projectModel/getModelDetail?modelId=${mol.modelId}"><button type="button" class="btn btn-info btn-xs">详情</button></a>
													<a href="/useCase/getUseCaseList?modelId=${mol.modelId}&projectId=${mol.projectId}"><button type="button" class="btn btn-info btn-xs">下属用例</button></a>
												<button ng-click="showDlg(true)" class="btn btn-info btn-xs" data-toggle="modal" data-target="#changePermission">修改</button>
                <input type="hidden" name="modelIdList[0]" value="${mol.modelId}">
                <a href="javascript:void(0)" onclick="submitDeleteModel('${mol.modelId}', '${mol.projectId}');return false;"><button  class="btn btn-info btn-xs"
                                                    data-toggle="modal" data-target="#delModel">标记弃用</button></a>
												</td>
            </form>
										</tr>
    </#list>

									</tbody>
								</table>
							</div>

							<div class="content_main container-fluid">
							

								<div class="pages" style="padding-left: 300px;">
									<div id="Pagination"></div>
									<div id="Pagination"><div class="pagination"><a class="current prev"><i></i>上一页</a><a class="current">1</a><a href="http://www.baidu.com">2</a><a href="http://www.baidu.com" class="next">下一页 <i></i></a></div></div>
									<div class="searchPage">
										<span class="page-sum">共<strong class="allPage">${modelListOutDto.pageDto.pageSum}</strong>页
										</span> <span class="page-go">跳转<input type="text">页
										</span> <a href="javascript:;" class="page-btn">GO</a>
									</div>
								</div>
								
								<div class=""
									style="margin-right: 270px; height: 85px; line-height: 85px; ">
									<span>共 ${modelListOutDto.pageDto.count} 条记录，</span> <span>当前显示第 ${modelListOutDto.pageDto.pageNow} 页</span>
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
								<h4 class="modal-title">添加模块</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form" id="addModel">
<input type="hidden" name="projectId" value="${modelListOutDto.projectId!''}" />
									<div class="form-group">
										<label for="selectproject" class="col-sm-3 control-label">项目名称：</label>
										<div class="col-sm-9">
											<select name="modelDto.projectId" class="form-control" id="selectproject">
												<#list modelListOutDto.projectIdNameList as pro>
												<option value="${pro.projectId}" <#if modelListOutDto.projectId?? && pro.projectId==modelListOutDto.projectId>selected="selected"</#if>>${pro.projectName}</option>
												</#list>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="selectCar2" class="col-sm-3 control-label">模块名称：</label>
										<div class="col-sm-9">
											<input type="text" name="modelDto.modelName" />
										</div>
									</div>
									<div class="form-group">
										<label for="selectCar" class="col-sm-3 control-label">关联链接：</label>
										<div class="col-sm-9">
											<input type="text" name="modelDto.modelUrl" />
										</div>
									</div>

								</form>
							</div>
							<div class="modal-footer">
								<a onclick="submitAddModel()" class="btn btn-primary"
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
                <h4 class="modal-title">修改模块信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="updateModel" >
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


			
		</div>
		<!-- /.main-container-inner -->
	</div>
	<!--/.main-container  -->

	<!-- basic scripts -->
	<script src="http://cdn.bootcss.com/jquery/2.0.3/jquery.min.js"></script>

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='/assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"/script>");
		if ("ontouchend" in document)
			document
					.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>

	<script src="/assets/js/typeahead-bs2.min.js"></script>
	<script src="/assets/js/ace-elements.min.js"></script>
	<script src="/assets/js/ace.min.js"></script>
	<script src="/assets/js/bootstrap.min.js"></script>
	<script src="/assets/js/jquery.pagination.js"></script>

	<script type="text/javascript">
		jQuery(function($) {
			$('#loading-btn').on(ace.click_event, function() {
				var btn = $(this);
				btn.button('loading')
				setTimeout(function() {
					btn.button('reset')
				}, 2000)
			});

			$('#id-button-borders').attr('checked', 'checked').on('click',
					function() {
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
			$("#Pagination").pagination("${modelListOutDto.pageDto.pageSum}");
		});
	</script>

</body>
</html>
