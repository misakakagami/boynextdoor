<html>
<head>
<title>fileList</title>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/fileManage.js"></script>
</head>
<body>
<center>
基类管理 page
<br/>
user:${(Session.loginUser.userName)!'游客'}
<br/>
<br/>
<a href="/pymanage/findList">返回上级</a>
<br/>
<table border="1">
    <tr>
        <th>
            文件名
        </th>
        <th>
            路径
        </th>
        <th>
            文件类型
        </th>
        <th>
            操作
        </th>
    </tr>
    <#list fileListOutDto.fileList as fl>
    <tr>
        <td>
            <#if fl.fileType == "file">
                ${fl.fileName}
            <#else>
                <a href="/pymanage/findList?path=${fl.path}">${fl.fileName}</a>
            </#if>
        </td>
        <td>
            ${fl.path}
        </td>
        <td>
            <#if fl.fileType == "file">
                文件
            <#else>
                文件夹
            </#if>
        </td>
        <td>
            <a href="/pymanage/downloadFile?path=${fl.path}">下载</a>&nbsp;
            <form id="delete${fl.path}">
                <input type="hidden" name="path" value="${fl.path}" />
                <input type="submit" onclick="submitDeleteFile('${fl.path}');return false;" value="删除" />
            </form>
            <#if fl.fileType == "file">
                <form id="upload${fl.path}">
                    <input type="file" name="file" id="uploadFile${fl.path}" />
                    <input type="hidden" name="path" value="${fl.path}" />
                    <input type="submit" onclick="uploadFile('${fl.path}');return false;" value="重新上传" />
                </form>
            </#if>
        </td>
    </tr>
    </#list>
</table>
<br/>
<br/>
在当前目录创建文件夹
<br/>
<form id="createDirectory">
    <input type="text" id="directoryName" />
    <input type="submit" onclick="createFile('${fileListOutDto.nowPath}');return false;" value="创建文件夹" />
</form>
<br/>
<br/>
在当前目录上传新文件
<br/>
<form id="uploadNewFile">
    <input type="file" name="file" id="uploadNewFile" />
    <input type="hidden" name="path" value="${fileListOutDto.nowPath}" />
    <input type="submit" onclick="uploadFile('NewFile');return false;" value="上传" />
</form>
<br/>
<br/>
copyright 红魔馆
</center>
</body>
</html>
