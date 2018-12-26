<html>
<head>
<title>planList</title>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/addPlan.js"></script>
</head>
<body>
<center>
<br/>
user:${(Session.loginUser.userName)!'游客'}
<br/>
<a href="/projectModel/getProjectList">项目列表</a>
<br/>
planList page
<br/>
<table border="1">
    <tr>
        <th>
            计划id
        </th>
        <th>
            计划名称
        </th>
        <th>
            更新时间
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
    <#list planListOutDto.planListDetailDtoList as pl>
    <tr>
        <td>
            ${pl.planId}
        </td>
        <td>
            <a href="/plan/planDetail?planId=${pl.planId}">${pl.planName}</a>
        </td>
        <td>
            ${pl.updateTime?string('YYYY-MM-dd HH:mm:ss')}
        </td>
        <td>
            ${pl.createTime?string('YYYY-MM-dd HH:mm:ss')}
        </td>
        <td>
            <#if pl.planStatus?? && pl.planStatus == -1>
                <span style="color:red">弃用</span>
            <#elseif pl.planStatus?? && pl.planStatus == 0>
                <span style="color:green">正常/未执行</span>
            <#elseif pl.planStatus?? && pl.planStatus == 1>
                <span style="color:yellow">执行中</span>
            </#if>
        </td>
        <td>
            <a href="/plan/planDetail?planId=${pl.planId}">查看计划详情</a>&nbsp;
            <form id="deletePlan${pl.planId}">
                <input type="hidden" name="planIdList[0]" value="${pl.planId}">
                <a href="javascript:void(0)" onclick="submitDeletePlan('${pl.planId}');return false;">标记弃用</a>
            </form>|
            <#if pl.planStatus == 0 >
                <a href="/plan/runPlan?planId=${pl.planId}&status=1">执行</a>
            <#elseif pl.planStatus == -1 >
               <span style="color:grey">已弃用</span>
            <#elseif pl.planStatus != 0 >
                <a href="/plan/stopPlan?planId=${pl.planId}&status=0">停止</a>
            </#if>
        </td>
    </tr>
    </#list>
</table>
<br/>
<br/>
<br/>

新建计划
<br/>
<form id="addPlan">
计划名：<input type="text" name="planDto.planName">
<br/>
计划描述：<input type="text" name="planDto.planIntro">
<br/>
计划类型：<select name="planDto.planType">
<option value="0">立即执行</option>
<option value="1">定时执行</option>
</select>
<br/>
执行次数：<input type="text" name="planDto.planSumTimes">（暂无作用）
<br/>
定时间隔：<select name="planDto.planInterval">
    <option value="9" >请选择</option>
    <option value="0" >每1分钟一次</option>
    <option value="1" >每5分钟一次</option>
    <option value="2" >每10分钟一次</option>
    <option value="3" >每30分钟一次</option>
    <option value="4" >每1小时一次</option>
    <option value="5" >每12小时一次</option>
    <option value="6" >每天一次</option>
    <option value="7" >每周一次</option>
    <option value="8" >每月一次</option>
</select>（计划类型为定时时，默认每天12点执行）
<br/>
<input type="button" onclick="submitAddPlan();return false;" value="创建" />
</form>

<br/>
<br/>
copyright 红魔馆
</center>
</body>
</html>
