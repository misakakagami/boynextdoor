<html>
<head>
<title>exampleList</title>
<script src="/js/jquery-1.9.0.min.js"></script>
</head>
<body>
<center>
exampleList page
<br/>
user:${(Session.loginUser.userName)!'游客'}
<br/>
<a href="/useCase/useCaseDetail<#if useCaseId?? || exampleListOutDto.useCaseId??>?useCaseId=${useCaseId!exampleListOutDto.useCaseId}</#if>">返回用例详情</a>
<br/>
<table border="1">
    <tr>
        <th>
            案例id
        </th>
        <th>
            案例名称
        </th>
        <th>
            案例路径
        </th>
        <th>
            案例状态
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
    <#list exampleListOutDto.exampleList as ex>
    <tr>
        <td>
            ${ex.exampleId}
        </td>
        <td>
            ${ex.exampleName}
        </td>
        <td>
            ${ex.exampleUrl}
        </td>
        <td>
            <#if ex.exampleStatus?? && ex.exampleStatus == -1>
                <span style="color:red">历史版本</span>
            <#else>
                <span style="color:green">当前版本</span>
            </#if>
        </td>
        <td>
            ${ex.createTime?string('YYYY-MM-dd')}
        </td>
        <td>
            ${ex.createUserId}
        </td>
        <td>
            <a href="/useCase/downloadExample?exampleId=${ex.exampleId}">下载</a>&nbsp;删除
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
