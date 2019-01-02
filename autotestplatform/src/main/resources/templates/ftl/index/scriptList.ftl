<html>
<head>
<title>scriptList</title>
<script src="/js/jquery-1.9.0.min.js"></script>
</head>
<body>
<center>
scriptList page
<br/>
user:${(Session.loginUser.userName)!'游客'}
<br/>
<a href="/useCase/useCaseDetail<#if useCaseId?? || scriptListOutDto.useCaseId??>?useCaseId=${useCaseId!scriptListOutDto.useCaseId}</#if>">返回用例详情</a>
<br/>
<table border="1">
    <tr>
        <th>
            脚本id
        </th>
        <th>
            脚本名称
        </th>
        <th>
            脚本路径
        </th>
        <th>
            脚本状态
        </th>
        <th>
            上传时间
        </th>
        <th>
            上传者
        </th>
        <th>
            操作
        </th>
    </tr>
    <#list scriptListOutDto.scriptList as sc>
    <tr>
        <td>
            ${sc.scriptId}
        </td>
        <td>
            ${sc.scriptName}
        </td>
        <td>
            ${sc.scriptUrl}
        </td>
        <td>
            <#if sc.scriptStatus?? && sc.scriptStatus == -1>
                <span style="color:red">历史版本</span>
            <#else>
                <span style="color:green">当前版本</span>
            </#if>
        </td>
        <td>
            ${sc.createTime?string('YYYY-MM-dd')}
        </td>
        <td>
            ${sc.createUserId}
        </td>
        <td>
            <a href="/useCase/downloadScript?scriptId=${sc.scriptId}">下载</a>&nbsp;删除
        </td>
    </tr>
    </#list>
</table>
<br/>
<br/>
copyright 红魔馆
</center>
</body>
</html>
