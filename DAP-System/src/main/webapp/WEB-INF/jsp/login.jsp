<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
</head>
<body>
<h2>Login</h2>
<form method="POST" action="">	
	<label for="userId">User id</label>
  	<input type="text" name="userId" id="userId" />
  	
  	<label for="password">Password</label>
  	<input type="password" name="password" id="password"/>
  	
  	<input type="submit" value="submit" />
  	
  	</form>
</body>
</html>