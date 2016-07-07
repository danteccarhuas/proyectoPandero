<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>.: Sistema Pandero</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/sb-admin.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/plugins/morris.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css" />
<link href="${pageContext.request.contextPath}/css/tab-panel.css" rel="stylesheet">

	
			<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/plugins/morris/raphael.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/morris/morris.min.js"></script>
	<!--<script
		src="${pageContext.request.contextPath}/js/plugins/morris/morris-data.js"></script>-->
	<script src="${pageContext.request.contextPath}/js/bootstrapValidator.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-filestyle.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
	

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand text-capitalize" href="${pageContext.request.contextPath}/a_menu">Bienvenido :<s:property value="#session.objUsuarioLogueado.nombres" /></a>
			</div>
			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav">


				<li class="dropdown"><a href="#" class="dropdown-toggle text-capitalize"
					data-toggle="dropdown"><img src="${pageContext.request.contextPath}/images/loginUser.png" class="img-rounded img-circle center-block" alt="Cinque Terre" width="60" height="60"></a>
					<ul class="dropdown-menu">


						<li><a href="${pageContext.request.contextPath}/a_login"><i class="fa fa-fw fa-power-off"></i> Log
								Out</a></li>
					</ul>
				</li>
			</ul>
			<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav side-nav">
					<s:iterator value="#session.objUsuarioMenus" var="currentEntry">
					
							<li>
								<a href="javascript:;" data-toggle="collapse"
									data-target="#<s:property value="idenlace" />"><i class="<s:property value="icon_left" />">
									</i>
										<s:property value="descripcion" /> <i class="<s:property value="icon_right" />">
									</i>
								</a>
								<!--   #session.objUsuarioMenus.listsub_enlace -->
								<ul id="<s:property value="idenlace" />" class="collapse">
									<s:iterator value="#currentEntry.listsub_enlace" var="innerEntry">
									
										<li><a href="${pageContext.request.contextPath}/<s:property value="%{#innerEntry.url}" />"><s:property value="%{#innerEntry.descripcion_sub_enlace}"/></a></li>
									</s:iterator>
								</ul>
							</li>
						
					</s:iterator>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>

		<div id="page-wrapper">

			<tiles:insertAttribute name="bodymenu" />
		
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	
</body>

</html>