<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
<script type="text/javascript">
  $(document).ready(function() {
    $("#userId").focus();
  });
</script>
</head>
<body style="padding: 10px;">
	<nav class="navbar navbar-default" role="navigation" ng-controller="MenuCtrl">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<span class="navbar-brand">HDX / admin</span>
		</div>
	</nav>
	<h3>Login</h3>
	<form method="POST" action="" role="form">
		<div class="form-group" style="width: 200px;">
			<label for="userId">User id</label> <input type="text" name="userId" id="userId" class="form-control" required />
		</div>
		<div class="form-group" style="width: 200px;">
			<label for="password">Password</label> <input type="password" name="password" id="password" class="form-control" required />
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</body>
</html>