<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 引入头部和左边 -->
 <jsp:include page="index-left.jsp"/>
 
 <div class="main-content">
	<div class="page-content">
	 
	 <!-- form表单 start-->
 <form class="form-inline" id="#" name="" method="get">
		<div class="form-group" style="padding-left:80px;">
		  	<label for="search" ><h4>查询条件</h4></label>
		</div>  	
		<br>
  			  	
		 <div class="form-group" style="padding-left:80px;">
			<label for="planname" >计划名称</label>
 			 <input type="text"  id="planname" placeholder="请输入计划名称"/>&nbsp;&nbsp;&nbsp;&nbsp;
	 	 </div>	 
      
	      <div class="form-group" >
	    			<label for="projectname"  >项目名称</label>
	      			<input type="text"   id="projectname" placeholder="请输入项目名称"/>&nbsp;&nbsp;&nbsp;&nbsp;
	      </div>			
 
	      <div class="form-group">
	    			<label for="plantime">计划运行时间</label>
	    			<!--指定 date标记-->  
           <div class='input-group date' >  
                <input type='text' id='datepicker' class="form-control" placeholder="请输入计划运行时间"/>  
                <span class="input-group-addon">  
                    <span class="glyphicon glyphicon-calendar"></span>  
                </span>  
            </div>  
	      	</div>			

           <br>
           <br>
			<div class="row" style="margin-left:80px;max-width:80%; height:70px;" >
					
					<p>
						<button class="btn">查询 </button>
						<input type="button" class="btn" onclick="javascript:location.href='<%=basePath%>saveAndUpdatePlan.jsp'" value="增加"/>
						<input type="button" class="btn" onclick="javascript:location.href='<%=basePath%>saveAndUpdatePlan.jsp'" value="修改"/>
						<button class="btn">删除</button>
						<button class="btn">即时执行</button>
						<button class="btn">定时执行</button>
						<button class="btn">停止执行</button>
					</p>
						
				<!-- PAGE CONTENT ENDS -->
		  </div><!-- /.row -->
		   
		<div class="form-group" style="padding-left:80px;">
		  	<label for="search" ><h4>查询结果列表</h4></label>
		</div>
		
		<!--table start -->
		<style>
		  th  {text-align:center;}
    td {text-align:center;}
   </style>
		<table border="1" cellspacing="0" cellpadding="0" style="margin-left:80px;" width="80%">
		  <thead>
		    <tr>
		      <th style="width:40px;height:40px;"></th>
		      <th style="width:40px;height:40px;">序号</th>
		      <th style="width:80px;height:40px;">计划名称</th>
		      <th style="width:120px;height:40px;">项目说明</th>
		      <th style="width:120px;height:40px;">定时参数</th>
		      <th style="width:120px;height:40px;">上一次运行状态</th>
		      <th style="width:120px;height:40px;">上一次运行时间</th>
		      <th style="width:80px;height:40px;">执行人</th>
		      <th style="width:80px;height:40px;">项目名称</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach items="${caplist}" var="mr">
		      <tr>
		       <td><input type="radio"> </td>
		       <td>${mr.id}</td>
		       <td>${mr.name}</td>
		       <td>${mr.capacity}</td>
		       <td>${mr.projection}</td>
		       <td>${mr.floor}</td>
		       <td>${mr.re_condition}</td>
		       <td>${mr.re_condition}</td>
		      </tr>
     </c:forEach>
     
		    <tr>
		      <td> <input type="radio"> </td>
		      <td>1</td>
		      <td>Tanmay</td>
		      <td>Bangalore</td>
		      <td>560001</td>
		      <td>Tanmay</td>
		      <td>Bangalore</td>
		      <td>560001</td>
		      <td>560001</td>
		    </tr>
		    
		     <tr>
		      <td> <input type="radio"> </td>
		      <td>2</td>
		      <td>Tanmay</td>
		      <td>Bangalore</td>
		      <td>560001</td>
		      <td>Tanmay</td>
		      <td>Bangalore</td>
		      <td>560001</td>
		      <td>560001</td>
		    </tr>
		 
		  </tbody>
		</table>
 <!-- table end -->
    <br>
    <br>
		<div class="row"  style="margin-left:0px;  text-align: center;">
		 <ul class="pager">
	          <li><a href="#">首页</a>
	             <a href="#">上一页</a>
	             <a href="#">下一页</a>
	             <a href="#">尾页</a>
	             </li>
    </ul>
		<!-- PAGE CONTENT ENDS -->
        </div><!-- /.row -->
    </form>
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
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->

		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>

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
    $(function () {
        $(".datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            clearBtn: true,//清除按钮
            todayBtn: true,//今日按钮
            format: "yyyy-mm-dd",//日期格式，
            showButtonPanel: true
        });
    });
</script>
 
				

 

  </body>
</html>
