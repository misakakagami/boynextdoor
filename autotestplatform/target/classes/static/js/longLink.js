/**
 * js文件
 */

var baseURL = window.location.protocol + "//" + window.location.host;


/**
 * 刷新计划状态ajax
 * @returns
 */
function refreshPlanStaus(planId) {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData();
	formData.append("planId", planId);
	$.ajax({
		//接口地址
		url: baseURL + "/plan/refreshPlanStatus" ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			var dataDict = eval(data);
			var trStr = "";
			$("#planStatusTd"+planId).html("");
			if(dataDict==null){
				$("#planStatusTd"+planId).html("<span style=\"color:red\">弃用</span>");
				$("#excBtn"+planId).html("<span style=\"color:grey\">已弃用</span>");
			}else if(dataDict==-1){
				$("#planStatusTd"+planId).html("<span style=\"color:red\">弃用</span>");
				$("#excBtn"+planId).html("<span style=\"color:grey\">已弃用</span>");
			} else if (dataDict==0){
				$("#planStatusTd"+planId).html("<span style=\"color:green\">正常/未执行</span>");
				$("#excBtn"+planId).html("<a href=\"javascript:void(0)\" onclick=\"runPlan('"+planId+"');return false;\"><button type=\"button\" class=\"btn btn-info btn-xs\" >执行</button></a>");
			} else {
				$("#planStatusTd"+planId).html("<span style=\"color:yellow\">执行中</span>");
				$("#excBtn"+planId).html("<button type=\"button\" class=\"btn btn-info btn-xs\" onclick=\"stopPlan('"+planId+"');return fales;\" >停止</button></a>");
			}
			return false;
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
//			alert("获取用例列表出错");
		}
	});
}

function refreshPlanStatusRun(){
	var psList = document.getElementsByName("planStatusClass");
	for(var i=0;i<psList.length;i++){
		refreshPlanStaus(psList[i].id.substring(12));
	}
}

setInterval('refreshPlanStatusRun()', 1000);

refreshPlanStatusRun();
