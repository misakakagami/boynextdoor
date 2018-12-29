<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>自动化测试平台登录</title>
<meta name="author" content="DeathGhost">
<link rel="stylesheet" type="text/css" href="/a_data/style.css" >
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="/a_data/jquery.js"></script>
<script src="/a_data/verificationNumbers.js" ></script>
<script src="/a_data/Particleground.js" ></script>
<script>
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
  //验证码
  createCode();
});
</script>
</head>
<body><canvas class="pg-canvas" width="1600" height="362"></canvas>
<dl class="admin_login">
<dt>
<strong>研发一体化平台</strong>
<em>Autotest platform</em>
</dt>
<form action="/login" method="post">
    <dd class="user_icon">
    <input placeholder="账号" class="login_txtbx" type="text" name="userName" />
    </dd>
    <dd class="pwd_icon">
    <input placeholder="密码" class="login_txtbx" type="password" name="passWord" />
    </dd>
    <dd class="val_icon">
    <div class="checkcode">
    <input id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx" type="text">
    <canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
    </div>
    <input value="验证码核验" class="ver_btn" onclick="validate();" type="button">
    </dd>
    <dd>
    <input value="立即登陆" class="submit_btn" type="submit">
</form>
</dd>
<span style="color:red">${errorMessage!''}</span>
<dd>
<!--
<p>© 2018-2018 红魔馆 版权所有</p>
<p>新日暮里18-118</p>
-->
</dd>
</dl>
<script type="text/javascript" src="/a_data/su.js"></script>


</body></html>