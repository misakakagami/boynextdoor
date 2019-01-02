<html>
<head>
<title>projectList</title>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/addProject.js"></script>
</head>
<body>
<center>
<br/>
user:${(Session.loginUser.userName)!'游客'}
<br/>
projectList page
<br/>
<table border="1">
    <tr>
        <th>
            项目名称
        </th>
        <th>
            项目网址
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
    <#list projectListOutDto.projectDtoList as pro>
    <tr>
        <td>
            <a href="/projectModel/getProjectDetail?projectId=${pro.projectId}">${pro.projectName}</a>
        </td>
        <td>
            <a href="${pro.projectUrl}">项目网址</a>
        </td>
        <td>
            ${pro.createTime?string('YYYY-MM-dd')}
        </td>
        <td>
            <#if pro.projectMode?? && pro.projectMode == -1>
                <span style="color:red">弃用</span>
            <#else>
                <span style="color:green">正常</span>
            </#if>
        </td>
        <td>
            <a href="/projectModel/getProjectDetail?projectId=${pro.projectId}">查看项目详情</a>&nbsp;
            <a href="/projectModel/getModelList?projectId=${pro.projectId}">查看下属模块</a>&nbsp;
            <form id="deleteProject${pro.projectId}">
                <input type="hidden" name="projectIdList[0]" value="${pro.projectId}">
                <a href="javascript:void(0)" onclick="submitDeleteProject('${pro.projectId}');return false;">标记弃用</a>
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
<form id="addProject">
项目名：<input type="text" name="projectDto.projectName">
<br/>
项目地址：<input type="text" name="projectDto.projectUrl">
<br/>
<input type="button" onclick="submitAddProject();return false;" value="增加" />
</form>

<br/>
<br/>
copyright 红魔馆
</center>
</body>
</html>
