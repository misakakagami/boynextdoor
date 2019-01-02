<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>自动化测试管理平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- basic styles -->
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="assets/css/material-design-iconic-font-2.2.0/css/material-design-iconic-font.css" />
<link rel="stylesheet" href="assets/css/pagination.css">
<link rel="stylesheet" href="assets/css/reset.css">  <!-- 样式初始化 -->
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css"> <!-- 滚动条样式化 -->


<script src="assets/js/date-time/moment.min.js"></script>


<!-- page specific plugin styles -->

<!-- fonts -->

<link rel="stylesheet"
	href="fonts.googleapis.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
<script src="assets/js/ace-extra.min.js"></script>

</head>
<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i
						class="icon-leaf"></i> 自动化测试管理平台
				</small>
				</a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header pull-left -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">

					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <span class="user-info">
								<h5>当前用户:XXX</h5>
						</span>
					</a>
						<ul
							class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#"> <i class="icon-off"></i> 退出
							</a></li>
						</ul></li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header  pull-right-->
		</div>
		<!-- /.navbar-container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="icon-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="icon-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="icon-group"></i>
						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span>

						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->

				<ul class="nav nav-list">
					<li class="active"><a href="autoTest.jsp"
						class="dropdown-toggle"> <i class="icon-desktop"></i> <span
							class="menu-text"> 自动化测试平台 </span> <b
							class="arrow icon-angle-down"></b>
					</a>

						<ul class="submenu">
							<li><a href="testCaseManage.jsp" class="dropdown-toggle">
									<i class="icon-double-angle-right"></i> 测试用例管理 <b
									class="arrow icon-angle-down"></b>
							</a>

								<ul class="submenu">
									<li><a href="testUseCaseList.jsp"> <i
											class="icon-leaf"></i> 测试用例列表
									</a></li>
								</ul></li>

							<li><a href="testScriptManage.jsp" class="dropdown-toggle">
									<i class="icon-double-angle-right"></i> 项目信息管理 <b
									class="arrow icon-angle-down"></b>
							</a>
								<ul class="submenu">
									<li><a href="projectManage.jsp"> <i class="icon-leaf"></i>
											项目管理
									</a></li>

									<li><a href="modelManage.jsp" class="dropdown-toggle">
											<i class="icon-pencil"></i> 模块管理
									</a></li>
								</ul></li>


							<li><a href="testPlanManage.jsp" class="dropdown-toggle">
									<i class="icon-double-angle-right"></i> 测试计划管理 <b
									class="arrow icon-angle-down"></b>
							</a>

								<ul class="submenu">
									<li><a href="testPlanManage_1.jsp"> <i
											class="icon-leaf"></i> 测试计划管理
									</a></li>
								</ul></li>

							<li><a href="controlPanel.jsp"> <i
									class="icon-double-angle-right"></i> 控制面板
							</a></li>
						</ul></li>


					<li><a href="other.jsp" class="dropdown-toggle"> <i
							class="icon-file-alt"></i> <span class="menu-text"> 其他 <span
								class="badge badge-primary ">5</span>
						</span> <b class="arrow icon-angle-down"></b>
					</a>

		<ul class="submenu">
								<li>
									<a href="planDetail.jsp">
										<i class="icon-double-angle-right"></i>
										测试计划详细信息
									</a>
								</li>




							<li><a href="projectDetail.jsp"> <i
									class="icon-double-angle-right"></i> 项目详细信息
							</a></li>
							
							<li>
									<a href="modelDetail.jsp">
										<i class="icon-double-angle-right"></i>
										模块详细信息
									</a>
								</li>

							<li><a href="testUseCaseDetail.jsp"> <i
									class="icon-double-angle-right"></i> 测试用例详情
							</a></li>
						</ul></li>
				</ul>
				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
			</div>



<div class="content_main container-fluid">
    <div id="iframe_home" class="iframe cur">
   <div>
				<!-- form表单 start-->
				<form class="form-inline" id="#" name="" method="get">
					<div class="form-group" style="padding-left: 80px;">
						<label><h3>项目列表</h3></label>
					</div>
					<br>

					<div class="form-group" style="padding-left: 80px;">
						<label for="search"><h4>查询条件</h4></label>
					</div>
					<br>

					<div class="form-group" style="padding-left: 80px;">
						<label for="projectname">项目名称</label> <select id="select"
							style="width: 150px; heigth: 20px;" onchange="b()">
							<option value="1">中台系统</option>
							<option value="2">核心系统</option>
						</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					
							</form>
				<!-- form表单 end-->
				</div>
					<br> 

					<div class="buttons" style="padding-left: 270px;">
						<p>
							<button type="button" class="btn btn-primary">查询</button>
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
											<th>序号</th>
											<th>项目id</th>
											<th>项目名称</th>
											<th>项目路径</th>
											<th>项目状态</th>
											<th>模块路径</th>
											<th>创建人</th>
											<th>修改人</th>
											<th>创建时间</th>
											<th>修改时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="checkbox"></td>
											<td>1</td>
											<td>0001</td>
											<td>中台系统</td>
											<td>www.baidu.com</td>
											<td>有效</td>
											<td>www.baidu.com</td>
											<td>玛利亚</td>
											<td>杰克</td>
											<td>2018-05-10</td>
											<td>2018-05-11</td>
											<td><a href="projectDetail.jsp"><button
														type="button" class="btn btn-info btn-xs">查询</button></a>
												<button ng-click="showDlg(true)" class="btn btn-info btn-xs"
													data-toggle="modal" data-target="#changePermission">修改</button>
												<button ng-click="delUser()" class="btn btn-info btn-xs"
													data-toggle="modal" data-target="#delModel">删除</button></td>
										</tr>

									</tbody>
								</table>
							</div>

							<div class="content_main container-fluid">
							

								<div class="pages" style="padding-left: 400px;">
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
								<h4 class="modal-title">添加用例</h4>
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
								<a onclick="urlSubmit()" class="btn btn-primary"
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


			
		</div>
		<!-- /.main-container-inner -->
	</div>
	<!--/.main-container  -->

	<!-- basic scripts -->
	<script src="http://cdn.bootcss.com/jquery/2.0.3/jquery.min.js"></script>

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"/script>");
		if ("ontouchend" in document)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>

	<script src="assets/js/typeahead-bs2.min.js"></script>
	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.pagination.js"></script>

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
			$("#Pagination").pagination("15");
		});
	</script>

</body>
</html>
