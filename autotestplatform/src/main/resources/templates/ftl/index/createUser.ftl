<html>
<head>
<title>createUser</title>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/addProject.js"></script>
</head>
<body>
<center>
<form action="/createUser" method="post">
username:<input type="text" name="user.userName" />
<br/>
password:<input type="password" name="user.password" />
<br/>
nickName:<input type="text" name="user.nickName" />
<br/>
<input type="submit" value="submit" />
</form>
<br/>
<br/>
${(Session.userName)!''}
<br/>
<br/>
copyright koumukandd
</center>
</body>
</html>
