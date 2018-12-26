<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>自动化测试管理平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- basic styles -->
     <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
     <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
     <link rel="stylesheet" href="assets/css/material-design-iconic-font-2.2.0/css/material-design-iconic-font.css" />
     <link rel="stylesheet" href="assets/css/pagination.css">
     
     
     <script src="assets/js/date-time/moment.min.js"></script>
     <script type="text/javascript" src="assets/js/jquery.pagination.js"></script>
     <script src="assets/js/jquery-1.9.1.js"></script>
     <script src="assets/js/index.js"></script>
     
    
    <!-- page specific plugin styles -->

				<!-- fonts -->
		
				<link rel="stylesheet" href="fonts.googleapis.com/css?family=Open+Sans:400,300" />
		
				<!-- ace styles -->
				<link rel="stylesheet" href="assets/css/ace.min.css" />
				<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
				<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
    <script src="assets/js/ace-extra.min.js"></script>
 
  </head>
  <body>
    <div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							自动化测试管理平台
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header pull-left -->

					<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">

							<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<span class="user-info">
									<h5>当前用户:XXX</h5>
								</span>
							</a>
							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header  pull-right-->
			</div><!-- /.navbar-container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
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
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->

						<ul class="nav nav-list">
						<li class="active">
							<a href="autoTest.jsp" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text"> 自动化测试平台 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

					 <ul class="submenu">
								<li>
									<a href="testCaseManage.jsp" class="dropdown-toggle">
										<i class="icon-double-angle-right"></i>
										测试用例管理
											<b class="arrow icon-angle-down"></b>
									</a>
									
									<ul class="submenu">
										<li>
											<a href="testUseCaseList.jsp">
												<i class="icon-leaf"></i>
												测试用例列表
											</a>
									 </li>
									</ul> 
							</li>
									
  						<li>
									<a href="testScriptManage.jsp" class="dropdown-toggle">
										<i class="icon-double-angle-right"></i>
									   项目信息管理
										<b class="arrow icon-angle-down"></b>
									</a>
										<ul class="submenu">
									 <li>
											<a href="projectManage.jsp">
												<i class="icon-leaf"></i>
												项目管理
											</a>
										</li>

										<li>
											<a href="testScriptDefine.jsp" class="dropdown-toggle">
												<i class="icon-pencil"></i>
												模块管理
											</a>
										</li>
									</ul>
								</li>

								
								<li>
									<a href="testPlanManage.jsp" class="dropdown-toggle">
										<i class="icon-double-angle-right"></i>
										测试计划管理
										<b class="arrow icon-angle-down"></b>
									</a>

									<ul class="submenu">
									 <li>
											<a href="testPlanManage_1.jsp">
												<i class="icon-leaf"></i>
												测试计划管理
											</a>
										</li>
									</ul>
								</li>
								
								<li>
								  <a href="controlPanel.jsp" >
								  <i class="icon-double-angle-right"></i>
										控制面板
								  </a>
								</li>
							</ul>
						</li>
						

						<li>
							<a href="other.jsp" class="dropdown-toggle">
								<i class="icon-file-alt"></i>

								<span class="menu-text">
									其他
									<span class="badge badge-primary ">5</span>
								</span>

								<b class="arrow icon-angle-down"></b>
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

								<li>
									<a href="testUseCaseDetail.jsp">
										<i class="icon-double-angle-right"></i>
										测试用例详情
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>



 <div class="main-content">
	<div class="page-content">
	 
		 <div class="content_main container-fluid">
     <div class="form-group" style="padding-left:80px;">
					  	<label><h3>案例详细信息</h3></label>
					</div>  
       
<!--返回-->
       	    <div class="buttons" style="padding-left:80px;">
															<a href="testUseCaseDetail.jsp">
                <button type="button" class="btn btn-primary">返回</button>
             </a>
             </div>
             
     <!-- 详情信息 -->
     <div class="content_main container-fluid">
    <div id="iframe_home" class="iframe cur">
        <div class="table-responsive">
            <table class="table table-bordered table-hover" style="margin-left:80px;width:auto">
            <thead>
            <tr>
                <th>
                   <input type="checkbox">
                </th>
                <th>序号</th>
                <th>案例id</th>
                <th>用例名称</th>
                <th>案例名称</th>
                <th>案例状态</th>
                <th>案例类型</th>
                <th>案例路径</th>
                <th>创建时间</th>
                <th>修改时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                   <input type="checkbox">
                </td>
                <td>1</td>
                <td>0001</td>
                <td>撤单</td>
                <td>撤单</td>
                <td>有效</td>
                <td>接口</td>
                <td>
                  www.baidu.com 
                </td>
                <td>2018-05-10</td>
                <td>2018-05-11</td>
                  <td>
                    <a href="exampleDetail.jsp"><button type="button" class="btn btn-info btn-xs">查询</button></a>
 																			<button ng-click="showDlg(true)" class="btn btn-info btn-xs" data-toggle="modal" data-target="#changePermission" >修改</button>
                    <button ng-click="delUser()" class="btn btn-info btn-xs" data-toggle="modal" data-target="#delModel">删除</button>
                </td>
            </tr>
            
              <tr>
                <td>
                   <input type="checkbox">
                </td>
                <td>1</td>
                <td>0001</td>
                <td>撤单</td>
                <td>撤单</td>
                <td>有效</td>
                <td>接口</td>
                <td>
                  www.baidu.com 
                </td>
                <td>2018-05-10</td>
                <td>2018-05-11</td>
                  <td>
                    <a href="exampleDetail.jsp"><button type="button" class="btn btn-info btn-xs">查询</button></a>
 																			<button ng-click="showDlg(true)" class="btn btn-info btn-xs" data-toggle="modal" data-target="#changePermission" >修改</button>
                    <button ng-click="delUser()" class="btn btn-info btn-xs" data-toggle="modal" data-target="#delModel">删除</button>
                </td>
            </tr>

            </tbody>
        </table>
        </div>
<div class="content_main container-fluid">

								<div class="pages" style="padding-left: 100px;">
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

	</div><!-- /.page-content -->
</div><!-- /.main-content --> 
</div><!-- /.main-container-inner -->
</div><!--/.main-container  -->

		<!-- basic scripts -->

		<script src="http://cdn.bootcss.com/jquery/2.0.3/jquery.min.js"></script>

		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<script src="assets/js/typeahead-bs2.min.js"></script>
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
  <script src="assets/js/jquery.pagination.js"></script>
  
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
 <script type="text/javascript">

    $(document).ready(function() {
        $("#Pagination").pagination("15");
    });

</script>

  </body>
</html>
