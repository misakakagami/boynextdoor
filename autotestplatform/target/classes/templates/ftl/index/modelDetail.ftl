<html>
<head>
<title>modelDetail</title>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/addModel.js"></script>
</head>
<body>
<center>
modelDetail page
<br/>
<a href="/projectModel/getModelList<#if modelDetailOutDto.projectId??>?projectId=${modelDetailOutDto.projectId}</#if>">返回模块列表</a>
<br/>
模块详情
<br/>
<form id="updateModel">
模块id：${modelDetailOutDto.modelId}<input type="hidden" name="modelDto.modelId" value="${modelDetailOutDto.modelId}" />
项目id：${modelDetailOutDto.projectId}<input type="hidden" name="modelDto.projectId" value="${modelDetailOutDto.projectId}" />
项目id：${modelDetailOutDto.projectName}
<input type="hidden" name="projectId" value="${modelDetailOutDto.projectId}" />
<br/>
项目名：<input type="text" name="modelDto.modelName" value="${modelDetailOutDto.modelName}">
<br/>
项目地址：<input type="text" name="modelDto.modelUrl" value="${modelDetailOutDto.modelUrl}">
<br/>
项目状态：${modelDetailOutDto.modelStatus}<input type="hidden" name="modelDto.modelStatus" value="${modelDetailOutDto.modelStatus}" />
<br/>
修改时间：${modelDetailOutDto.updateTime?string('yyyy-MM-dd')}&nbsp;&nbsp;${modelDetailOutDto.updateUserId}
<br/>
创建时间：${modelDetailOutDto.createTime?string('yyyy-MM-dd')}&nbsp;&nbsp;${modelDetailOutDto.createUserId}
<br/>
<input type="submit" value="修改" onclick="submitUpdateModel();return false;" />
</form>

<br/>
<br/>
copyright 红魔馆
</center>
</body>
</html>
