<!-- style>
ul.nav li.dropdown:hover > ul.dropdown-menu {
    display: block;    
}
</style -->
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

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li class="dropdown" ng-class="{ active: isActive('${ctx}/admin/status') }"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Status <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li ng-class="{ active: isActive('${ctx}/admin/status/datasets') }"><a href="${ctx}/admin/status/datasets/">Detected CKAN datasets</a></li>
					<li ng-class="{ active: isActive('${ctx}/admin/status/resources') }"><a href="${ctx}/admin/status/resources/">Detected CKAN resources</a></li>
					<li class="divider"></li>
					<li ng-class="{ active: isActive('${ctx}/admin/status/manuallyTriggerDatasetsDetection') }"><a href="${ctx}/admin/status/manuallyTriggerDatasetsDetection">Manually trigger datasets
							detection</a></li>
					<li ng-class="{ active: isActive('${ctx}/admin/status/manuallyTriggerResourcesDetection') }"><a href="${ctx}/admin/status/manuallyTriggerResourcesDetection">Manually trigger resources
							detection</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav">
			<li class="dropdown" ng-class="{ active: isActive('${ctx}/admin/curated') }"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Curated data <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li ng-class="{ active: isActive('${ctx}/admin/curated/importsfromckan') }"><a href="${ctx}/admin/curated/importsfromckan/">Imports From CKAN</a></li>
					<li class="divider"></li>
					<li ng-class="{ active: isActive('${ctx}/admin/curated/sources') }"><a href="${ctx}/admin/curated/sources/">Sources</a></li>
					<li ng-class="{ active: isActive('${ctx}/admin/curated/entityTypes') }"><a href="${ctx}/admin/curated/entityTypes/">Entity types</a></li>
					<li ng-class="{ active: isActive('${ctx}/admin/curated/entities') }"><a href="${ctx}/admin/curated/entities/">Entities</a></li>
					<li ng-class="{ active: isActive('${ctx}/admin/curated/indicatorTypes') }"><a href="${ctx}/admin/curated/indicatorTypes/">Indicator types</a></li>
                    <li ng-class="{ active: isActive('${ctx}/admin/curated/indicators') }"><a href="${ctx}/admin/curated/indicators/">Indicators</a></li>
                    <li ng-class="{ active: isActive('${ctx}/admin/curated/units') }"><a href="${ctx}/admin/curated/units/">Units</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav">
			<li class="dropdown" ng-class="{ active: isActive('${ctx}/admin/dictionaries') }"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Dictionaries <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li ng-class="{ active: isActive('${ctx}/admin/dictionaries/regions') }"><a href="${ctx}/admin/dictionaries/regions/">Regions</a></li>
					<li ng-class="{ active: isActive('${ctx}/admin/dictionaries/sources') }"><a href="${ctx}/admin/dictionaries/sources/">Sources</a></li>
					<li ng-class="{ active: isActive('${ctx}/admin/dictionaries/indicatorTypes') }"><a href="${ctx}/admin/dictionaries/indicatorTypes/">Indicator Types</a></li>
				</ul></li>
		</ul>
		<!-- form class="navbar-form navbar-left" role="search">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form -->
		<ul class="nav navbar-nav navbar-right">
			<li><a style="padding-right: 0px;">Logged in as ${SESSION_PARAM_UID}</a></li>
			<li><a style="padding-left: 5px;" href="${ctx}/logout/">(log out)</a></li>
			<li class="dropdown" ng-class="{ active: isActive('${ctx}/admin/misc') }"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li ng-class="{ active: isActive('${ctx}/admin/misc/users') }"><a href="${ctx}/admin/misc/users/">Manage users</a></li>
					<li ng-class="{ active: isActive('${ctx}/admin/misc/languages') }"><a href="${ctx}/admin/misc/languages/">Manage languages</a></li>
					<li ng-class="{ active: isActive('${ctx}/admin/misc/test') }"><a href="${ctx}/admin/misc/test/">Test</a></li>
					<li class="divider"></li>
					<li ng-class="{ active: showTestZone }">
						<div class="checkbox" style="margin-left: 20px;">
							<label> <input type="checkbox" ng-click="toggleTestZone()"> Show test zone
							</label>
						</div>
					</li>
				</ul></li>
		</ul>
	</div>
	<!-- /.navbar-collapse -->
</nav>
