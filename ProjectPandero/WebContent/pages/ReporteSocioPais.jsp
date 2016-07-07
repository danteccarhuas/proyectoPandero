	<%@ taglib prefix="s" uri="/struts-tags"%>
	<link rel="stylesheet" type="text/css" media="screen" href="css/screen.css" />
	<div id="idPrincipal">			
			<div id="idCuerpo">
				<div id="idMenu">
				<ul>
						<li>-Reportes-</li>										
				</ul>
				</div>
				<div id="idCentralConMenu">
					<div id="idDivReporte" align="left">					
						<s:form id="idForm" action="reporteSociosPais" target="idFrameReporte">
						<div class="col-lg-4">
							<div class="form-group">

								<label class="control-label col-lg-3" for="cboPais">Pais:</label>
								<div class="col-lg-9">
									<select class="form-control" id="cboPais" name="idPais">
									</select>
								</div>

							</div>
						</div>

					<div class="row">
						<div class="col-md-push-11 col-md-1">
							<button class="btn btn-success" id="btnConsultarSocioPais">
								Consultar
							</button>
						</div>
					</div>
				</s:form>					
						<iframe name="idFrameReporte"  id="idFrameReporte" ></iframe>									
					</div> 			
				</div>
			</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jsReportes.js"></script>




