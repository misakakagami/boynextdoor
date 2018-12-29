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
<script src="assets/js/ace-extra.min.js"></script> 
</head>
<#include "index-left.ftl" />
<body>
<div class="main-content">
	<div class="page-content">
	 
		<div class="content_main container-fluid">
		    <div class="form-group" style="padding-left:80px;">
				<label><h3>执行结果列表</h3></label>
			</div>  
	       
			<!--返回-->
	       	<div class="buttons" style="padding-left:80px;">
				<a href="/plan/planList"><button type="button" class="btn btn-primary">返回计划列表</button></a>             
	        </div>
            <br/>
			<!-- 总体信息 -->
			<div class="profile-user-info profile-user-info-striped" style="margin-left:80px;">
				<div class="profile-info-row">
					<div class="profile-info-name"> 历史折线图 </div>
			
					<div class="profile-info-value">
					<span class="editable" id="username">
						<#list planResultOutDto.historyAllResult as allH>
						${allH}&nbsp;
						</#list>
						<br/>
						<#list planResultOutDto.historyErrResult as errH>
						${errH}&nbsp;
						</#list>
					</span>
					</div>
				</div>
			
				<div class="profile-info-row">
					<div class="profile-info-name"> 总执行次数 </div>
			
					<div class="profile-info-value">
						<span class="editable" id="username">${planResultOutDto.excNum}</span>
					</div>
				</div>
			</div>
            <br/>
		
			<!-- 详情信息 -->
        	<#list planResultOutDto.resultList as rl>
			<div class="profile-user-info profile-user-info-striped" style="margin-left:80px;">
				<div class="profile-info-row">
					<div class="profile-info-name"> 执行概要 </div>
					<div class="profile-info-value">
		        		<span>名称：${rl.planResultName!''}</span><br/>
		        		<span>案例总数：${rl.sumUseCase}</span>&nbsp;&nbsp;&nbsp;&nbsp;
		        		<span style="color:red">失败总数：${rl.errUseCase}</span><br/>
		        		<span>状态<#if rl.status?? && rl.status == 'r'>
					                <span style="color:red">执行中</span>
					            <#else>
					                <span style="color:green">执行完毕</span>
					            </#if></span>
					</div>
				</div>
				
				<div class="profile-info-row">
					<div class="profile-info-name"> 执行日志 </div>
					<div class="profile-info-value">
						<div class="content_main container-fluid">
							<div id="iframe_home" class="iframe cur">
			        			<div class="table-responsive">
						            <table class="table table-bordered table-hover" style="margin-left:80px;width:auto">
						            <thead>
						            <tr>
						                <th>用例名称</th>
						                <th>日志下载</th>
						                <th>成功案例</th>
						                <th>失败案例</th>
						                <th>案例总数</th>
						                <th>执行时间</th>
						                <th>失败信息</th>
						                <th>图表</th>
						            </tr>
						            </thead>
						            <#list rl.caseList as cl>            
						            <tbody>
						            <tr>
						                <td><a href="/useCase/useCaseDetail?useCaseId=${cl.useCaseId}">${cl.useCaseName}</a></td>
						                <td><a href="/plan/downloadLog?logId=${cl.logId}">点击下载日志</a></td>
						                <td>${cl.sumExample-cl.errExample}</td>
						                <td>${cl.errExample}</td>
						                <td>${cl.sumExample}</td>
						                <td>${cl.logTime?string('YYYY-MM-dd')}</td>
						                <td><textarea>${cl.logName}</textarea></td>
						                <td>O</td>
						            </tr>            
						            </tbody>
						            </#list>
						        	</table>
								</div>
						    </div>
					    </div>
				    </div>
				</div>
			</div>
		    <br/>
		    </#list>
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
