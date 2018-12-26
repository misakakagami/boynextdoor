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
     <link rel="stylesheet" href="/assets/css/bootstrap-timepicker.css" />
     <link rel="stylesheet" href="/assets/css/bootstrap-editable.css" />
     <link rel="stylesheet" href="/assets/css/datepicker.css" />
     <link rel="stylesheet" href="/assets/css/bootstrap.min.css" />
     
     <script src="/assets/js/bootstrap.min.js"></script>
     <script src="/assets/js/date-time/bootstrap-datepicker.min.js"></script>
     <script src="/assets/js/date-time/bootstrap-timepicker.min.js"></script>
     <script src="/assets/js/date-time/daterangepicker.min.js"></script>
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
				</div><!-- /.navbar-header -->

					<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">

							<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<span class="user-info">
									<h5>当前用户:${(Session.loginUser.userNick)!'游客'}</h5>
								</span>
							</a>
							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="/loginOut">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
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
											<a href="/projectModel/getProjectList">
												<i class="icon-leaf"></i>
												项目管理
											</a>
										</li>

										<li>
											<a href="/projectModel/getModelList" class="dropdown-toggle">
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

								<li>
									<a href="projectMessageMaintance.jsp">
										<i class="icon-double-angle-right"></i>
										项目信息维护
									</a>
								</li>
								
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
