<html>
<head>
<title>useCaseList</title>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/addUseCase.js"></script>
</head>
<body>
<center>
useCaseList page
<br/>
user:${(Session.loginUser.userName)!'游客'}
<br/>
<a href="/plan/planList">计划列表</a>
<br/>
<a href="/projectModel/getModelList<#if useCaseListOutDto.projectId??>?projectId=${useCaseListOutDto.projectId}</#if>">返回模块列表</a>
<br/>
<table border="1">
    <tr>
        <th>
            用例名称
        </th>
        <th>
            项目名称
        </th>
        <th>
            模块名称
        </th>
        <th>
            更新时间
        </th>
        <th>
            状态
        </th>
        <th>
            操作
        </th>
    </tr>
    <#list useCaseListOutDto.testCaseInfoList as uc>
    <tr>
        <td>
            ${uc.useCaseName}
        </td>
        <td>
            <a href="/projectModel/getProjectDetail?projectDetailInDto.projectId=${uc.projectId}">${uc.projectName}</a>
        </td>
        <td>
            <a href="/projectModel/getModelDetail?modelDetailInDto.modelId=${uc.modelId}">${uc.modelName}</a>
        </td>
        <td>
            ${uc.updateTime?string('YYYY-MM-dd')}
        </td>
        <td>
            <#if uc.useCaseStatus?? && uc.useCaseStatus == -1>
                <span style="color:red">弃用</span>
            <#else>
                <span style="color:green">正常</span>
            </#if>
        </td>
        <td>
            <a href="/useCase/useCaseDetail?useCaseId=${uc.useCaseId}">查看用例详情</a>
            <form id="deleteUseCase${uc.useCaseId}">
                <input type="hidden" name="useCaseIdList[0]" value="${uc.useCaseId}">
                <a href="javascript:void(0)" onclick="submitDeleteUseCase('${uc.useCaseId}', '${uc.modelId}');return false;">标记弃用</a>
            </form>
        </td>
    </tr>
    </#list>
</table>
<br/>
<br/>
<br/>

增加用例
<br/>
<form id="addUseCase">
<select name="useCaseDto.modelId">
    <#list useCaseListOutDto.modelIdNameList as mol>
        <option value="${mol.modelId}" <#if useCaseListOutDto.modelId?? && mol.modelId==useCaseListOutDto.modelId>selected="selected"</#if>>${mol.modelName}</option>
    </#list>
</select>
<input type="hidden" name="projectId" value="${useCaseListOutDto.projectId!''}" />
<input type="hidden" name="modelId" value="${useCaseListOutDto.modelId!''}" />
用例名：<input type="text" name="useCaseDto.useCaseName">
<br/>
用例类型：<select name="useCaseDto.useCaseType">
    <option value="1">接口</option>
    <option value="2">页面</option>
</select>
<br/>
用例描述：<textarea name="useCaseDto.useCaseContent" ></textarea>
<br/>
<input type="submit" onclick="submitAddUseCase()" value="增加" />
</form>

<br/>
<br/>
copyright 红魔馆
</center>
</body>
</html>
