<html>
<head>
<title>modelDetail</title>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/addUseCase.js"></script>
</head>
<body>
<center>
useCaseDetail page
<br/>
<a href="/useCase/getUseCaseList<#if useCaseDetailOutDto.modelId??>?modelId=${useCaseDetailOutDto.modelId}</#if>">返回用例列表</a>
<br/>
用例详情
<br/>
<form id="updateUseCase">
项目：${useCaseDetailOutDto.projectName}
<input type="hidden" name="useCaseDto.projectId" value="${useCaseDetailOutDto.projectId}" />
模块：${useCaseDetailOutDto.modelName}
<input type="hidden" name="useCaseDto.modelId" value="${useCaseDetailOutDto.modelId}" />
<input type="hidden" name="useCaseDto.useCaseId" value="${useCaseDetailOutDto.useCaseId}" />
<input type="hidden" name="modelId" value="${useCaseDetailOutDto.modelId}" />
<br/>
用例名：<input type="text" name="useCaseDto.useCaseName" value="${useCaseDetailOutDto.useCaseName}">
<br/>
用例类型：<select name="useCaseDto.useCaseType">
    <option value="1" <#if useCaseDetailOutDto.useCaseType==1>selected="selected"</#if>>接口</option>
    <option value="2" <#if useCaseDetailOutDto.useCaseType==2>selected="selected"</#if>>页面</option>
</select>
<br/>
用例状态：${useCaseDetailOutDto.useCaseStatus}<input type="hidden" name="useCaseDto.useCaseStatus" value="${useCaseDetailOutDto.useCaseStatus}" />
<br/>
用例描述：<textarea name="useCaseDto.useCaseContent" >${useCaseDetailOutDto.useCaseContent}</textarea>
<br/>
修改时间：${useCaseDetailOutDto.updateTime?string('yyyy-MM-dd')}&nbsp;&nbsp;${useCaseDetailOutDto.updateUserId}
<br/>
创建时间：${useCaseDetailOutDto.createTime?string('yyyy-MM-dd')}&nbsp;&nbsp;${useCaseDetailOutDto.createUserId}
<br/>
<input type="submit" value="修改" onclick="submitUpdateUseCase();return false;" />
</form>
<br/>
<br/>
脚本：
<div id="scriptArea">
    <#if useCaseDetailOutDto.scriptUrl??>
        <a href="/useCase/downloadScript?scriptId=${useCaseDetailOutDto.scriptId}">下载</a>当前脚本
        <br/>
    <#else>
    无脚本，请
    </#if>
</div>
<form id="uploadScript" enctype="multipart/form-data">
<input type="file" id="scriptFile" name="scriptFile" />
<input type="hidden" name="useCaseId" value="${useCaseDetailOutDto.useCaseId}" />
<input type="submit" value="上传脚本" onclick="uploadScript();return false;" />
</form>
<br/>
<a href="/useCase/getScriptList?useCaseId=${useCaseDetailOutDto.useCaseId}">查看脚本历史版本</a>
<br/>
<br/>
案例：
<div id="exampleArea">
    <#if useCaseDetailOutDto.exampleUrl??>
        <a href="/useCase/downloadExample?exampleId=${useCaseDetailOutDto.exampleId}">下载</a>当前案例
        <br/>
    <#else>
    无案例，请
    </#if>
</div>
<form id="uploadExample" enctype="multipart/form-data">
<input type="file" id="exampleFile" name="exampleFile" />
<input type="hidden" name="useCaseId" value="${useCaseDetailOutDto.useCaseId}" />
<input type="submit" value="上传案例" onclick="uploadExample();return false;" />
</form>
<br/>
<a href="/useCase/getExampleList?useCaseId=${useCaseDetailOutDto.useCaseId}">查看案例历史版本</a>

<br/>
<br/>
copyright 红魔馆
</center>
</body>
</html>
