/**
 * js文件
 */

var baseURL = window.location.protocol + "//" + window.location.host;

var modelListURL = "/projectModel/getModelList";
var addModelURL = "/projectModel/insertModel";
var updateModelURL = "/projectModel/updateModel";
var deleteModelURL = "/projectModel/deleteModel";

/**
 * 提交后重定向
 * 10:项目列表
 * 11:增加项目
 * 12:修改项目
 * 13:删除项目
 */
function redirectTo(target, param){
	switch (target) {
	case 20:
		if(param!=null){
			window.location.href = baseURL + modelListURL + "?" + param;
			return false;
		}
		window.location.href = baseURL + modelListURL;
		break;

	default:
		break;
	}
	return false;
}


/**
 * 新建模块ajax
 * @returns
 */
function submitAddModel() {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#addModel")[0]);
	if(formData.get('modelDto.projectId')==null){
		alert("需要先建立所属项目！");
		return;
	}
	$.ajax({
		//接口地址
		url: baseURL + addModelURL ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="addModelSuccess"){
				alert("新建模块成功");
				redirectTo(20, "projectId="+formData.get('modelDto.projectId'));
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("新建模块出错");
			redirectTo(20, "projectId="+formData.get('modelDto.projectId'));
		}
	});
}

/**
 * 修改模块ajax
 * @returns
 */
function submitUpdateModel() {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#updateModel")[0]);
	$.ajax({
		//接口地址
		url: baseURL + updateModelURL ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="updateModelSuccess"){
				alert("修改模块成功");
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
			alert("修改模块出错");
//			if(projectId!=null){
//				redirectTo(20, "projectId="+projectId);
//			}else{
//				redirectTo(20, null);
//			}
		}
	});
}

/**
 * 删除模块ajax
 * @returns
 */
function submitDeleteModel(modelId, projectId) {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#deleteModel"+modelId)[0]);
	$.ajax({
		//接口地址
		url: baseURL + deleteModelURL ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="deleteModelSuccess"){
				alert("删除模块成功");
				if(projectId!=null){
					redirectTo(20, "projectId="+projectId);
				}else{
					redirectTo(20, null);
				}
			}else if(data.indexOf("faild")!=-1){
				alert("模块id： "+data.substr(5)+" 删除失败");
				if(projectId!=null){
					redirectTo(20, "projectId="+projectId);
				}else{
					redirectTo(20, null);
				}
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("删除模块出错");
			if(projectId!=null){
				redirectTo(20, "projectId="+projectId);
			}else{
				redirectTo(20, null);
			}
		}
	});
}

function getModelList(){
	var form = document.getElementById("getModelListForm");
//	form.
	form.submit();
}

function jumpPage(){
	alert("a");
}
