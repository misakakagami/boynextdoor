/**
 * js文件
 */

var baseURL = window.location.protocol + "//" + window.location.host;

var projectListURL = "/projectModel/getProjectList";
var addProjectURL = "/projectModel/insertProject";
var updateProjectURL = "/projectModel/updateProject";
var deleteProjectURL = "/projectModel/deleteProject";

/**
 * 提交后重定向
 * 10:项目列表
 * 11:增加项目
 * 12:修改项目
 * 13:删除项目
 */
function redirectTo(target, param){
	switch (target) {
	case 10:
		window.location.href = baseURL + projectListURL;
		break;
	case 11:
		window.location.href = baseURL + addProjectURL;
		break;
	case 12:
		window.location.href = baseURL + updateProjectURL;
		break;
	case 13:
		window.location.href = baseURL + deleteProjectURL;
		break;

	default:
		break;
	}
	return false;
}

/**
 * 新建项目ajax
 * @returns
 */
function submitAddProject() {
    //这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#addProject")[0]);
    $.ajax({
        //接口地址
        url: baseURL + addProjectURL ,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            //成功的回调
            if(data=="addProjectSuccess"){
                alert("新建项目成功");
                redirectTo(10, null);
            }
        },
        error: function (returndata) {
           //请求异常的回调
           // modals.warn("网络访问失败，请稍后重试!");
           alert("新建项目出错");
           redirectTo(10, null);
        }
    });
}

/**
 * 修改项目ajax
 * @returns
 */
function submitUpdateProject() {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#updateProject")[0]);
	$.ajax({
		//接口地址
		url: baseURL + updateProjectURL ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="updateProjectSuccess"){
				alert("修改项目成功");
//				redirectTo(10, null);
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("修改项目出错");
//			redirectTo(10, null);
		}
	});
}

/**
 * 删除项目ajax
 * @returns
 */
function submitDeleteProject(projectId) {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#deleteProject"+projectId)[0]);
	$.ajax({
		//接口地址
		url: baseURL + deleteProjectURL ,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="deleteProjectSuccess"){
				alert("弃用项目成功");
				redirectTo(10, null);
			} else if(data.indexOf("faild")!=-1){
				alert("项目id为： "+data.substr(5)+" 未删除成功");
				redirectTo(10, null);
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("弃用项目出错");
			redirectTo(10, null);
		}
	});
}
