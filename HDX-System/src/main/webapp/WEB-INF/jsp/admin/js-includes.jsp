<script type="text/javascript">
  var hdxContextRoot = "${ctx}";
</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="${ctx}/js/xeditable.min.js"></script>
<script src="${ctx}/js/admin/utilities.js"></script>
<script src="${ctx}/js/admin/init.js"></script>
<script src="${ctx}/js/admin/${param.which}.js"></script>
<script src="${ctx}/js/admin/menu.js"></script>
<%
	if ("true".equals(request.getParameter("i18n"))) {
%><script src="${ctx}/js/admin/i18n.js"></script>
<%
	}
%>
