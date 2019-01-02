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
<script src="/js/addProject.js"></script>
  </head>
<#include "index-left.ftl" />
  <body>

 <div class="main-content">
	<div class="page-content">
	 
	 <!-- form表单 start-->
  <div class="form-group" style="padding-left:80px;">
		  	<label><h3>项目详细信息</h3></label>
		</div>  	
		<br>
<a href="/projectModel/getProjectList">返回项目列表</a>
		   
	 <!-- 项目详情 -->
			<div class="profile-user-info profile-user-info-striped" style="margin-left:80px;">
				<div class="profile-info-row">
					<div class="profile-info-name"> 项目id </div>

					<div class="profile-info-value">
						<span class="editable" id="username">${projectDetailOutDto.projectId}</span>
					</div>
				</div>

				<div class="profile-info-row">
					<div class="profile-info-name"> 项目名称 </div>

						<div class="profile-info-value">
						<span class="editable" id="username">${projectDetailOutDto.projectName}</span>
					</div>
				</div>
				
				<div class="profile-info-row">
					<div class="profile-info-name"> 项目路径 </div>

						<div class="profile-info-value">
						<span class="editable" id="username"><a href="${projectDetailOutDto.projectUrl}" >项目地址</a></span>
					</div>
				</div>

				<div class="profile-info-row">
					<div class="profile-info-name"> 项目状态</div>

					<div class="profile-info-value">
						<span class="editable" id="signup">${projectDetailOutDto.projectMode}</span>
					</div>
				</div>

			
					<div class="profile-info-row">
					<div class="profile-info-name"> 项目创建时间</div>

					<div class="profile-info-value">
						<span class="editable" id="signup">${projectDetailOutDto.createTime?string('yyyy-MM-dd')}</span>
					</div>
				</div>
				
					<div class="profile-info-row">
					<div class="profile-info-name"> 项目修改时间</div>

					<div class="profile-info-value">
						<span class="editable" id="signup">${projectDetailOutDto.updateTime?string('yyyy-MM-dd')}</span>
					</div>
				</div>
				
				<div class="profile-info-row">
					<div class="profile-info-name"> 项目创建人</div>

					<div class="profile-info-value">
						<span class="editable" id="signup">${projectDetailOutDto.createUserId}</span>
					</div>
				</div>
				
					<div class="profile-info-row">
					<div class="profile-info-name"> 项目修改人</div>

					<div class="profile-info-value">
						<span class="editable" id="signup">${projectDetailOutDto.updateUserId}</span>
					</div>
				</div>
												
           <!--
           		<div class="profile-info-row">
					<div class="profile-info-name"> 模块详细信息</div>
	
					<div class="profile-info-value">
						 <a href="modelDetail.jsp"><button type="button" class="btn btn-info btn-xs" >查询</button></a>&nbsp;&nbsp;&nbsp;
						 <a href="javascript:;" class="file">上传
							    <input type="file" name="" id="">
							</a>
					</div>
				</div>
				-->
           
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
