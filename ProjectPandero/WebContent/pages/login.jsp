<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/login.css" />
<title>.: Sistema Pandero</title>
<s:head />

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">Inicia sesión para acceder
					Sistema de Pandero</h1>
				<div class="account-wall">
					<img class="profile-img"
						src="${pageContext.request.contextPath}/images/loginUser.png"
						alt="">
					<form id="frmLogin" class="form-signin"
						accept-charset="UTF-8" role="form" method="POST" action="login">
						<div class="form-group">
							<input type="text" id="txt_usuario" placeholder="usuario"
								class="form-control" name="login" />
						</div>
						<div class="form-group">
							<input type="password" id="txt_password" placeholder="Password"
								class="form-control" name="password" />
						</div>
						<button id="idLoginSubmit" class="btn btn-lg btn-primary btn-block" type="submit">
						Ingresar
						</button>
						<br/>
						<core:if test="${session.validaLogin==1}">
							<div class="form-group">
								<div class="col-md-12">
									<div class="alert alert-info" >
									<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>
									Usuario y/o password
										incorrectos.</div>
								</div>
							</div>
						</core:if>
						<br/>
					</form>
				</div>			
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrapValidator.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jsLoguin.js"></script>
</body>
</html>








