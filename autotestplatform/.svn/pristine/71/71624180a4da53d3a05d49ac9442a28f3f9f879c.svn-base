/**
 * js文件
 */

var baseURL = window.location.protocol + "//" + window.location.host;

/**
 * 新建ajax
 * @returns
 */
function createFile(path) {
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData();
	var fileName = document.getElementById("directoryName").value;
	formData.append("path", path + "/" + fileName);
	$.ajax({
		//接口地址
		url: baseURL + "/pymanage/createFile",
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="success"){
				alert("新建成功");
				window.location.href = baseURL + "/pymanage/findList?path=" + path;
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("新建出错");
		}
	});
}

/**
 * 删除ajax
 * @returns
 */
function submitDeleteFile(path) {
	if(!confirm("确认要删除吗？")){
		return false;
	}
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#delete" + path)[0]);
	$.ajax({
		//接口地址
		url: baseURL + "/pymanage/deleteFile" ,
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
				window.location.href = baseURL + "/pymanage/findList?path=" + path;
			}
		},
		error: function (returndata) {
			//请求异常的回调
			// modals.warn("网络访问失败，请稍后重试!");
			alert("删除失败");
		}
	});
}

/**
 * 上传ajax
 * @returns
 */
function uploadFile(path) {
	var fileObj = document.getElementById("uploadFile" + path).files[0];
	if (typeof(fileObj) == "undefined" ||  fileObj.size <=0) {
		alert("请先选择文件");
		return false;
	}
	//这里唯一需要注意的就是这个form-add的id
	var formData = new FormData($("#upload" + path)[0]);
	$.ajax({
		//接口地址
		url: baseURL + "/pymanage/uploadFile" ,
		type: 'POST',
		encType: "multipart/form-data",
		cache: false,
		data: formData,
		contentType: false,
		processData: false,
		success: function (data) {
			//成功的回调
			if(data=="success"){
				alert("上传成功");
				window.location.href = baseURL + "/pymanage/findList?path=" + path;
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