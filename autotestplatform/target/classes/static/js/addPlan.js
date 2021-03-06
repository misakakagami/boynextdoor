/**
 * js文件
 */

var baseURL = window.location.protocol + "//" + window.location.host;

var planListURL = "/plan/planList";
var planDetailURL = "/plan/planDetail";
var addPlanURL = "/plan/insertPlan";
var updatePlanURL = "/plan/updatePlan";
var deletePlanURL = "/plan/deletePlan";

/**
 * 提交后重定向
 */
function redirectTo(target, param){
	switch (target) {
	case 40:
		if(param!=null){
			window.location.href = baseURL + planListURL+"?"+param;
			return false;
		}
		window.location.href = baseURL + planListURL;
		break;
	case 41:
		window.location.href = baseURL + planDetailURL+"?"+param;
		break;

	default:
		break;
	}
	return false;
}

/**
 * 新建计划ajax
 * @returns
 */
function submitAddPlan() {
    //这里唯一需要注意的就是这个form-add的id
	var formData = new FormData();
	var planName = document.getElementsByName("planDto.planName")[0].value;
	var planIntro = document.getElementsByName("planDto.planIntro")[0].value;
	var planTime = document.getElementsByName("planDto.planInterval")[0].value;
	formData.append("planDto.planName",planName);
	formData.append("planDto.planIntro",planIntro);
	formData.append("planDto.planInterval",planTime);
	formData.append("planDto.planType","0");
	formData.append("planDto.planType","1");
    $.ajax({
        //接口地址
        url: baseURL + addPlanURL ,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            //成功的回调
            if(data=="addPlanSuccess"){
                alert("新建计划成功");
                redirectTo(40, null);
            }
        },
        error: function (returndata) {
           //请求异常的回调
           // modals.warn("网络访问失败，请稍后重试!");
//           alert(JSON.stringify(returndata));
           alert("新建计划出错");
           redirectTo(40, null);
        }
    });
}

/**
 * 修改计划ajax
 * @returns
 */
function submitUpdatePlan() {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#updatePlan")[0]);
	$.ajax({
		//接口地址
		url: baseURL + updatePlanURL ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="updatePlanSuccess"){
				alert("修改计划成功");
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("修改计划出错");
		}
	});
}

/**
 * 删除计划ajax
 * @returns
 */
function submitDeletePlan(planId) {
	if(!confirm("弃用此计划吗？")){
		return false;
	}
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#deletePlan"+planId)[0]);
	$.ajax({
		//接口地址
		url: baseURL + deletePlanURL ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="deletePlanSuccess"){
				alert("弃用计划成功");
				redirectTo(40, null);
			} else if(data.indexOf("faild")!=-1){
				alert("计划id为： "+data.substr(5)+" 未删除成功");
				redirectTo(40, null);
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("弃用计划出错");
			redirectTo(40, null);
		}
	});
}

/**
 * 联动模块ajax
 * @returns
 */
function getModelIdNameList() {
	var formData = new FormData();
	formData.append("projectId", $("#projectList option:selected").val());
	if(formData.get("projectId")==-1){
		$("#modelList").empty();
		$("#modelList").prepend('<option value="-1" selected="selected" disabled="disabled">需要先选择项目</option>');
		$("#useCaseList").empty();
		$("#useCaseList").prepend('<option value="-1" selected="selected" disabled="disabled">需要先选择模块</option>');
	}else{
		//这里唯一需要注意的就是这个form-add的id
		$.ajax({
			//接口地址
			url: baseURL + "/projectModel/modelIdName" ,
			type: 'POST',
			data: formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success: function (data) {
				//成功的回调
				var dataDict = eval(data);
				$("#modelList").empty();
				$("#useCaseList").empty();
				if(dataDict.length>0){
					for(var i=0;i<dataDict.length;i++){
						$("#modelList").prepend('<option value="'+dataDict[i].modelId+'">'+dataDict[i].modelName+'</option>');
					}
					$("#modelList").prepend('<option value="-1" selected="selected">请选择模块</option>');
					$("#useCaseList").prepend('<option value="-1" selected="selected" disabled="disabled">需要先选择模块</option>');
				}else{
					$("#modelList").prepend('<option value="-1" selected="selected" disabled="disabled">当前项目无下属模块</option>');
					$("#useCaseList").prepend('<option value="-1" selected="selected" disabled="disabled">需要先选择模块</option>');
				}
			},
			error: function (returndata) {
				//请求异常的回调
				// modals.warn("网络访问失败，请稍后重试!");
				alert("获取模块列表出错");
			}
		});
	}
}

/**
 * 联动用例ajax
 * @returns
 */
function getUseCaseIdNameList() {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData();
	formData.append("modelId", $("#modelList option:selected").val());
	$.ajax({
		//接口地址
		url: baseURL + "/useCase/useCaseIdName" ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			var dataDict = eval(data);
			$("#useCaseList").empty();
			if(dataDict.length>0){
				for(var i=0;i<dataDict.length;i++){
					$("#useCaseList").prepend('<option value="'+dataDict[i].useCaseId+'">'+dataDict[i].useCaseName+'</option>');
				}
				$("#useCaseList").prepend('<option value="-1" selected="selected">请选择用例</option>');
			}else{
				$("#useCaseList").prepend('<option value="-1" selected="selected" disabled="disabled">当前模块无下属用例</option>');
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("获取用例列表出错");
		}
	});
}

/**
 * 增加计划用例ajax
 * @returns
 */
function submitAddUseCaseToPlan(planId) {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#addUseCaseToPlan")[0]);
	formData.append("planId", planId);
	$.ajax({
		//接口地址
		url: baseURL + "/plan/insertUseCasePlan" ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="success"){
				alert("增加成功");
				refreshUPR(planId);
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("增加出错");
		}
	});
}

/**
 * 删除计划用例ajax
 * @returns
 */
function submitDeleteUPR(planId, useCaseId) {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#deleteUPR"+useCaseId)[0]);
	formData.append("planId", planId);
	$.ajax({
		//接口地址
		url: baseURL + "/plan/deleteUseCasePlanRelation" ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="success"){
				alert("删除成功");
				refreshUPR(planId);
			} else {
				alert("未删除成功");
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("删除出错");
		}
	});
}

/**
 * 刷新计划用例ajax
 * @returns
 */
function refreshUPR(planId) {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData();
	formData.append("planId", planId);
	$.ajax({
		//接口地址
		url: baseURL + "/plan/refreshUseCasePlanRelation" ,
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
			$("#planUseCaseList tbody").html("");
			for(var i=0;i<dataDict.length;i++){
				trStr = '<tr><td>'+dataDict[i]["projectName"]+'</td><td>'+dataDict[i]["modelName"]+'</td><td>'+
					dataDict[i]["useCaseName"]+'</td><td>'+dataDict[i]["useCaseStatus"]+'</td><td><form id="deleteUPR'+
					dataDict[i]["useCaseId"]+'"><input type="hidden" name="useCasePlanRelationId" value="'+
					dataDict[i]["useCasePlanRelationId"]+'"><a href="javascript:void(0)" onclick="submitDeleteUPR(\''+
					planId+'\',\''+dataDict[i]["useCaseId"]+'\');return false;">删除</a></form></td></tr>';
				$("#planUseCaseList tbody").append(trStr);
			}
			return false;
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("获取用例列表出错");
		}
	});
}

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
				$("#excBtn"+planId).html("<a href=\"/plan/runPlan?planId="+planId+"&status=1\"><button type=\"button\" class=\"btn btn-info btn-xs\" >执行</button></a>");
			} else {
				$("#planStatusTd"+planId).html("<span style=\"color:yellow\">执行中</span>");
				$("#excBtn"+planId).html("<a href=\"/plan/stopPlan?planId="+planId+"&status=0\"><button type=\"button\" class=\"btn btn-info btn-xs\" >停止</button></a>");
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


/**
 * 执行计划ajax
 * @returns
 */
function runPlan(planId) {
	if(!confirm("是否执行任务？计划id："+planId)){
		return false;
	}
	var formData = new FormData();
	formData.append("planId",planId);
	formData.append("status","1");
    $.ajax({
        //接口地址
        url: "/plan/runPlan",
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            //成功的回调
//            if(data=="addPlanSuccess"){
//                alert("新建计划成功");
                redirectTo(40, null);
//            }
        },
        error: function (returndata) {
           //请求异常的回调
           // modals.warn("网络访问失败，请稍后重试!");
//           alert(JSON.stringify(returndata));
//           alert("新建计划出错");
           redirectTo(40, null);
        }
    });
}

/**
 * 停止计划ajax
 * @returns
 */
function stopPlan(planId) {
	if(!confirm("是否停止任务？计划id："+planId)){
		return false;
	}
	var formData = new FormData();
	formData.append("planId",planId);
	formData.append("status","0");
	$.ajax({
		//接口地址
		url: "/plan/stopPlan",
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
//            if(data=="addPlanSuccess"){
//                alert("新建计划成功");
                redirectTo(40, null);
//            }
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
//           alert(JSON.stringify(returndata));
//           alert("新建计划出错");
           redirectTo(40, null);
		}
	});
}
