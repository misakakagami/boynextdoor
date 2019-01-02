<html>
<head>
<title>planDetail</title>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/addPlan.js"></script>
</head>
<body>
<center>
planDetail page
<br/>
<a href="/plan/planList">返回计划列表</a>
<br/>
计划详情
<br/>
<form id="updatePlan">
计划id：${planDetailOutDto.planId}<input type="hidden" name="planDto.planId" value="${planDetailOutDto.planId}" />
<br/>
计划名：<input type="text" name="planDto.planName" value="${planDetailOutDto.planName}" />
<br/>
计划描述：<textarea name="planDto.planIntro" >${planDetailOutDto.planIntro}</textarea>
<br/>
计划状态：${planDetailOutDto.planStatus}<input type="hidden" name="planDto.planStatus" value="${planDetailOutDto.planStatus}" />
<br/>
计划类型：<select name="planDto.planType">
    <option value="0" <#if (planDetailOutDto.planType)==0>selected="selected"</#if>>执行一次</option>
    <option value="1" <#if (planDetailOutDto.planType)==1>selected="selected"</#if>>定时执行</option>
</select>
<br/>
定时间隔：<select name="planDto.planInterval">
    <option value="9" >请选择</option>
    <option value="0" <#if (planDetailOutDto.planInterval)==0>selected="selected"</#if>>每1分钟一次</option>
    <option value="1" <#if (planDetailOutDto.planInterval)==1>selected="selected"</#if>>每5分钟一次</option>
    <option value="2" <#if (planDetailOutDto.planInterval)==2>selected="selected"</#if>>每10分钟一次</option>
    <option value="3" <#if (planDetailOutDto.planInterval)==3>selected="selected"</#if>>每30分钟一次</option>
    <option value="4" <#if (planDetailOutDto.planInterval)==4>selected="selected"</#if>>每1小时一次</option>
    <option value="5" <#if (planDetailOutDto.planInterval)==5>selected="selected"</#if>>每12小时一次</option>
    <option value="6" <#if (planDetailOutDto.planInterval)==6>selected="selected"</#if>>每天一次</option>
    <option value="7" <#if (planDetailOutDto.planInterval)==7>selected="selected"</#if>>每周一次</option>
    <option value="8" <#if (planDetailOutDto.planInterval)==8>selected="selected"</#if>>每月一次</option>
</select>（计划类型为定时时，默认每天12点执行）
<br/>
开始执行时刻：${planDetailOutDto.planStartTime?string('yyyy-MM-dd HH:mm:ss')}
<br/>
<input type="submit" value="修改" onclick="submitUpdatePlan();return false;" />
</form>
<br/>
<br/>
添加用例：
<br/>
<form id="addUseCaseToPlan">
项目：<select id="projectList" onchange="getModelIdNameList()">
    <option value="-1">请选择</option>
    <#list planDetailOutDto.projectIdNameList as pro>
        <option value="${pro.projectId}">${pro.projectName}</option>
    </#list>
</select>
模块：<select id="modelList" onchange="getUseCaseIdNameList()" name="modelId">
        <option value="-1">需要先选择项目</option>
</select>
用例：<select id="useCaseList" name="useCaseId">
        <option value="-1">需要先选择模块</option>
</select>
<br/>
<input type="submit" onclick="submitAddUseCaseToPlan('${planDetailOutDto.planId}');return false;" value="添加" />
</form>
<br/>
<br/>
已有用例：
<table id="planUseCaseList" border="1">
    <thead>
        <tr>
            <th>
                项目名称
            </th>
            <th>
                模块名称
            </th>
            <th>
                用例名称
            </th>
            <th>
                用例状态
            </th>
            <th>
                操作
            </th>
        </tr>
    </thead>
    <tbody>
    <#list planDetailOutDto.planUseCaseList as pl>
        <tr>
            <td>
                ${pl.projectName}
            </td>
            <td>
                ${pl.modelName}
            </td>
            <td>
                ${pl.useCaseName}
            </td>
            <td>
                ${pl.useCaseStatus}
            </td>
            <td>
                <form id="deleteUPR${pl.useCaseId}">
                    <input type="hidden" name="useCasePlanRelationId" value="${pl.useCasePlanRelationId}">
                    <a href="javascript:void(0)" onclick="submitDeleteUPR('${planDetailOutDto.planId}','${pl.useCaseId}');return false;">删除</a>
                </form>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
<br/>
<br/>

<br/>
<br/>
copyright 红魔馆
</center>
</body>
</html>
