<html>
<head>
<title>projectDetail</title>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/addProject.js"></script>
</head>
<body>
<center>
projectDetail page
<br/>
<a href="/projectModel/getProjectList">返回项目列表</a>
<br/>
项目详情
<br/>
<form id="updateProject">
项目id：${projectDetailOutDto.projectId}<input type="hidden" name="projectDto.projectId" value="${projectDetailOutDto.projectId}" />
<br/>
项目名：<input type="text" name="projectDto.projectName" value="${projectDetailOutDto.projectName}">
<br/>
项目地址：<input type="text" name="projectDto.projectUrl" value="${projectDetailOutDto.projectUrl}">
<br/>
项目状态：${projectDetailOutDto.projectMode}<input type="hidden" name="projectDto.projectMode" value="${projectDetailOutDto.projectMode}" />
<br/>
修改时间：${projectDetailOutDto.updateTime?string('yyyy-MM-dd')}&nbsp;&nbsp;${projectDetailOutDto.updateUserId}
<br/>
创建时间：${projectDetailOutDto.createTime?string('yyyy-MM-dd')}&nbsp;&nbsp;${projectDetailOutDto.createUserId}
<br/>
<input type="submit" value="修改" onclick="submitUpdateProject();return false;" />
</form>

<br/>
<br/>
copyright 红魔馆
</center>
</body>
</html>
