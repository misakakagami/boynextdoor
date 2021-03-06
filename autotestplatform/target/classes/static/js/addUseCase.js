/**
 * js文件
 */

var baseURL = window.location.protocol + "//" + window.location.host;

var modelListURL = "/projectModel/getModelList";
var addModelURL = "/projectModel/insertModel";
var updateModelURL = "/projectModel/updateModel";
var deleteModelURL = "/projectModel/deleteModel";

var useCaseListURL = "/useCase/getUseCaseList";
var useCaseDetailURL = "/useCase/useCaseDetail";
var addUseCaseURL = "/useCase/insertUseCase";
var updateUseCaseURL = "/useCase/updateUseCase";
var deleteUseCaseURL = "/useCase/deleteUseCase";

/**
 * 提交后重定向
 * 10:项目列表
 * 11:增加项目
 * 12:修改项目
 * 13:删除项目
 */
function redirectTo(target, param){
	switch (target) {
	case 30:
		if(param!=null){
			window.location.href = baseURL + useCaseListURL + "?" + param;
			return false;
		}
		window.location.href = baseURL + useCaseListURL;
		break;
	case 31:
		if(param!=null){
			window.location.href = baseURL + useCaseDetailURL + "?" + param;
			return false;
		}
		window.location.href = baseURL + useCaseDetailURL;
		break;

	default:
		break;
	}
	return false;
}


/**
 * 新建用例ajax
 * @returns
 */
function submitAddUseCase() {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#addUseCase")[0]);
	if(formData.get('useCaseDto.modelId')==null){
		alert("需要先建立所属模块！");
		return;
	}
	$.ajax({
		//接口地址
		url: baseURL + addUseCaseURL ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="addUseCaseSuccess"){
				alert("新建模块成功");
				var param = "modelId="+formData.get('useCaseDto.modelId');
				if(formData.get('projectId')!=""){
					param += "&useCaseListOutDto.projectId="+formData.get('projectId');
				}
				redirectTo(30, param);
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("新建模块出错");
		}
	});
}

/**
 * 修改用例ajax
 * @returns
 */
function submitUpdateUseCase() {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#updateUseCase")[0]);
	$.ajax({
		//接口地址
		url: baseURL + updateUseCaseURL ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="updateUseCaseSuccess"){
				alert("修改用例成功");
//				if(projectId!=null){
//					redirectTo(20, "projectId="+projectId);
//				}else{
//					redirectTo(20, null);
//				}
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("修改用例出错");
//			if(projectId!=null){
//				redirectTo(20, "projectId="+projectId);
//			}else{
//				redirectTo(20, null);
//			}
		}
	});
}

/**
 * 删除用例ajax
 * @returns
 */
function submitDeleteUseCase(useCaseId, modelId) {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#deleteUseCase"+useCaseId)[0]);
	$.ajax({
		//接口地址
		url: baseURL + deleteUseCaseURL ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="deleteUseCaseSuccess"){
				alert("删除模块成功");
				if(modelId!=null){
					redirectTo(30, "modelId=" + modelId);
				}else{
					redirectTo(30, null);
				}
			}else if(data.indexOf("faild")!=-1){
				alert("模块id： " + data.substr(5) + " 删除失败");
				if(modelId != null){
					redirectTo(30, "modelId=" + modelId);
				}else{
					redirectTo(30, null);
				}
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("删除模块出错");
			if(modelId!=null){
				redirectTo(30, "modelId=" + modelId);
			}else{
				redirectTo(30, null);
			}
		}
	});
}

/**
 * 上传脚本ajax
 * @returns
 */
function uploadScript() {
	var fileObj = document.getElementById("scriptFile").files[0];
	if (typeof(fileObj) == "undefined" ||  fileObj.size <=0) {
		alert("请先选择脚本文件");
		return false;
	}
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#uploadScript")[0]);
	$.ajax({
		//接口地址
		url: baseURL + "/useCase/uploadScript" ,
		type: 'POST',
		encType: "multipart/form-data",
		data: formData,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data!="faild"){
				alert("上传成功");
				$("#scriptArea").html("<a href='/useCase/downloadScript?scriptId="+data+"'>下载</a>当前脚本<br/>");
			}else{
				alert("上传失败，请检查脚本是否包含\"excelName\"字符串");
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("上传失败");
		}
	});
}

/**
 * 上传案例ajax
 * @returns
 */
function uploadExample() {
	var fileObj = document.getElementById("exampleFile").files[0];
	if (typeof(fileObj) == "undefined" ||  fileObj.size <=0) {
		alert("请先选择案例文件");
		return false;
	}
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#uploadExample")[0]);
	$.ajax({
		//接口地址
		url: baseURL + "/useCase/uploadExample" ,
		type: 'POST',
		encType: "multipart/form-data",
		cache: false,
		data: formData,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data!="faild"){
				alert("上传成功");
				$("#exampleArea").html("<a href='/useCase/downloadExample?exampleId="+data+"'>下载</a>当前案例<br/>");
			}else{
				alert("上传失败");
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("上传失败");
		}
	});
}