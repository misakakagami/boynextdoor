<html>
<head>
<title>modelList</title>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/addModel.js"></script>
</head>
<body>
<center>
modelList page
<br/>
user:${(Session.loginUser.userName)!'游客'}
<br/>
<a href="/plan/planList">计划列表</a>
<br/>
<a href="/projectModel/getProjectList">返回项目列表</a>
<br/>
<table border="1">
    <tr>
        <th>
            模块id
        </th>
        <th>
            模块名称
        </th>
        <th>
            项目名称
        </th>
        <th>
            模块网址
        </th>
        <th>
            创建时间
        </th>
        <th>
            状态
        </th>
        <th>
            操作
        </th>
    </tr>
    <#list modelListOutDto.modelListOutDtoList as mol>
    <tr>
        <td>
            ${mol.modelId}
        </td>
        <td>
            ${mol.modelName}
        </td>
        <td>
            <a href="/projectModel/getProjectDetail?projectDetailInDto.projectId=${mol.projectId}">${mol.projectName}</a>
        </td>
        <td>
            <a href="${mol.modelUrl}">模块网址</a>
        </td>
        <td>
            ${mol.createTime?string('YYYY-MM-dd')}
        </td>
        <td>
            <#if mol.modelStatus?? && mol.modelStatus == -1>
                <span style="color:red">弃用</span>
            <#else>
                <span style="color:green">正常</span>
            </#if>
        </td>
        <td>
            <a href="/projectModel/getModelDetail?modelId=${mol.modelId}">查看模块详情</a>&nbsp;
            <a href="/useCase/getUseCaseList?modelId=${mol.modelId}&projectId=${mol.projectId}">查看下属用例</a>&nbsp;
            <form id="deleteModel${mol.modelId}">
                <input type="hidden" name="modelIdList[0]" value="${mol.modelId}">
                <a href="javascript:void(0)" onclick="submitDeleteModel('${mol.modelId}', '${mol.projectId}');return false;">标记弃用</a>
            </form>
        </td>
    </tr>
    </#list>
</table>
<br/>
<br/>
<br/>

增加项目
<br/>
<form id="addModel">
<select name="modelDto.projectId">
    <#list modelListOutDto.projectIdNameList as pro>
        <option value="${pro.projectId}" <#if modelListOutDto.projectId?? && pro.projectId==modelListOutDto.projectId>selected="selected"</#if>>${pro.projectName}</option>
    </#list>
</select>
<input type="hidden" name="projectId" value="${modelListOutDto.projectId!''}" />
项目名：<input type="text" name="modelDto.modelName">
<br/>
项目地址：<input type="text" name="modelDto.modelUrl">
<br/>
<input type="submit" onclick="submitAddModel()" value="增加" />
</form>

<br/>
<br/>
copyright 红魔馆
</center>
</body>
</html>
