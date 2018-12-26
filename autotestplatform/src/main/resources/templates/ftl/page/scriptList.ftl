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
<link rel="stylesheet"
    href="/assets/css/material-design-iconic-font-2.2.0/css/material-design-iconic-font.css" />
<link rel="stylesheet" href="/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="/assets/css/bootstrap-editable.css" />
<link rel="stylesheet" href="/assets/css/datepicker.css" />

<link rel="stylesheet" href="/assets/css/pagination.css">
<link rel="stylesheet" href="/assets/css/reset.css">
<!-- 样式初始化 -->
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
<!-- 滚动条样式化 -->
<script src="/assets/js/date-time/moment.min.js"></script>

<link rel="stylesheet"
    href="/fonts.googleapis.com/css?family=Open+Sans:400,300" />
<!-- ace styles -->
<link rel="stylesheet" href="/assets/css/ace.min.css" />
<link rel="stylesheet" href="/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="/assets/css/ace-skins.min.css" />
<script src="/assets/js/ace-extra.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="/assets/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="/assets/js/date-time/daterangepicker.min.js"></script>
<script src="/assets/js/date-time/moment.min.js"></script>
<script type="text/javascript" src="/assets/js/jquery.pagination.js"></script>
<script src="/assets/js/jquery-1.9.1.js"></script>
<script src="assets/js/ace-extra.min.js"></script>
<script src="/assets/js/index.js"></script>
 
  </head>
  <#include "index-left.ftl" />
 
  <body>
 <div class="main-content">
	<div class="page-content">
		<div class="content_main container-fluid">
			<div class="form-group" style="padding-left:80px;">
			  	<label><h3>脚本详细信息</h3></label>
			</div> 
	<!--返回-->
			<div class="buttons" style="padding-left:80px;">
				<a href="/useCase/useCaseDetail<#if useCaseId?? || scriptListOutDto.useCaseId??>?useCaseId=${useCaseId!scriptListOutDto.useCaseId}</#if>">			
                <button type="button" class="btn btn-primary">返回用例详情</button></a>
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
                <th>脚本id</th>
                <th>脚本名称</th>
                <th>脚本路径</th>
                <th>脚本状态</th>                
                <th>创建时间</th>
                <th>修改时间</th>
                <th>上传者</th>                
                <th>操作</th>
            </tr>
            </thead>
            <#list scriptListOutDto.scriptList as sc>            
            <tbody>
            <tr>
                <td>
                   <input type="checkbox">
                </td>
                <td>${sc.scriptId}</td>
                <td>${sc.scriptName}</td>
                <td>${sc.scriptUrl}</td>
                <td>
					 <#if sc.scriptStatus?? && sc.scriptStatus == -1>
		                <span style="color:red">历史版本</span>
		            <#else>
		                <span style="color:green">当前版本</span>
		            </#if>
		        </td>
                <td>${sc.createTime?string('YYYY-MM-dd')}</td>
                <td>${sc.updateTime?string('YYYY-MM-dd')}</td>
                <td>${sc.createUserId}</td>                
                  <td>
                    <a href="/useCase/downloadScript?scriptId=${sc.scriptId}"><button type="button" class="btn btn-info btn-xs">下载</button></a> 																		
                </td>
            </tr>
            </tbody>
            </#list>
        </table>
        
					</div>
       
     				<div class="content_main container-fluid">

								<div class="pages" style="padding-left: 80px;">
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
</div>

		<!-- basic scripts -->

		<script src="http://cdn.bootcss.com/jquery/2.0.3/jquery.min.js"></script>

		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<script src="/assets/js/typeahead-bs2.min.js"></script>
		<script src="/assets/js/ace-elements.min.js"></script>
		<script src="/assets/js/ace.min.js"></script>
		<script src="/assets/js/bootstrap.min.js"></script>
  <script src="/assets/js/jquery.pagination.js"></script>
  
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
