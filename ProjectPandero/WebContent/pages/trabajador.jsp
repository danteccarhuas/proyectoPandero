<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<style>
#c {
	display: none;
}
</style>

<link href="${pageContext.request.contextPath}/css/jquery.modal.css" type="text/css" rel="stylesheet" />


<script src="${pageContext.request.contextPath}/js/jsTrabajador.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.modal.js"></script>



<div class="container-fluid">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				Mantenimiento Trabajador
				<!--  <small>Statistics Overview</small> -->
			</h1>

		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel with-nav-tabs panel-primary">
				<div class="panel-heading">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tab1primary" id="tab1"
							data-toggle="tab">Consulta Trabajador</a></li>
						<li id="eventotab2primary" class="disabled"><a id="tab2"
							href="#tab2primary" data-toggle="tab">Datos Trabajador</a></li>
					</ul>
				</div>
				<div class="panel-body">
					<div class="tab-content">
						
						<div class="tab-pane fade in active" id="tab1primary">
							<div class="row">
								<div class="form-group">
									<label class="col-lg-1" for="txt_codigo_buscar">Codigo:</label>
									<div class="col-lg-2">
										<input class="form-control" type="text"
											id="txt_codigo_buscar" name="txt_codigo_buscar"
											placeholder="Codigo" />
									</div>

									<label class="col-lg-1" for="txt_nombre_apellido_buscar">Nombres:</label>
									<div class="col-lg-8">
										<input class="form-control" type="text"
											id="txt_nombre_apellido_buscar"
											name="txt_nombre_apellido_buscar"
											placeholder="Nombre y/o apellidos" />
									</div>
								</div>
							</div>
							<br/>
							<div class="row">
								<div class="form-group">

									<label class="control-label col-lg-1" for="txt_dni_buscar">DNI:</label>
									<div class="col-lg-2" id="etiquetadni">
										<input class="form-control" type="text" id="txt_dni_buscar"
											name="txt_dni_buscar" placeholder="DNI" />
									</div>
									<div class="col-lg-3">
										<button id="btn_buscar" class="btn btn-primary">
											Buscar <span class="fa fa-search"></span>
										</button>
										<button id="btn_nuevo" class="btn btn-primary">
											Nuevo <span class="fa fa-file-o"></span>
										</button>
									</div>
								</div>
							</div>
							<br/>
							<div class="row">
								<div class="form-group">
									<div class="col-lg-12">
										<div class="table-responsive">
											<table id="miTabla_BuscarTrabajador" class="table table-bordered">
												<thead>
													<tr>
														<th>Codigo</th>
														<th>Nombres y Apellidos</th>
														<th>DNI</th>
														<th>Usuario</th>
														<th>Contraseña</th>
														<th>Rol</th>
														<th colspan="2">Acción</th>
													</tr>
												</thead>
												<tbody id="rellenar">

												</tbody>
											</table>
											<div class="col-md-12 text-center">
												<ul class="pagination" id="paginador"></ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="tab-pane fade" id="tab2primary">
							<br/>
							<form class="form-horizontal" id="frm_trabajador" method="post" accept-charset="UTF-8" role="form">
								<input type="hidden" id="txt_idusuario">
								<div class="row">
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-4"
												for="txt_cod_tra_guardar">Cod. Trabajador:</label>
											<div class="col-lg-8">
												<input class="form-control" type="text"
													id="txt_cod_tra_guardar"
													name="txt_cod_tra_guardar" disabled="disabled" />
											</div>
										</div>
									</div>
									<div class="col-lg-4">
									
										<div class="form-group">
											<label class="control-label col-lg-3"
												for="txt_nombre_guardar">Nombres:</label>
											<div class="col-lg-9">
												<input class="form-control" type="text"
													id="txt_nombre_guardar" name="nombres"
													placeholder="Nombres" />
											</div>
										</div>

									</div>
									<div class="col-lg-4">
										<div class="form-group">

											<label class="control-label col-lg-3"
												for="txt_ape_guardar">Apellidos:</label>
											<div class="col-lg-9">
												<input class="form-control" type="text"
													id="txt_ape_guardar" name="apellidos"
													placeholder="Apellidos" />
											</div>
										</div>

									</div>
								</div>
								
								<div class="row">
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-4"
												for="txt_dni_guardar">Dni:</label>
											<div class="col-lg-8">
												<input class="form-control" type="text"
													id="txt_dni_guardar" name="dni"
													placeholder="Dni" />
											</div>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-3"
												for="txt_login">Login:</label>
											<div class="col-lg-9">
												<input class="form-control" type="text"
													id="txt_login" name="login"
													placeholder="Login" />
											</div>
										</div>

									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-3"
												for="txt_numerodoc">Password:</label>
											<div class="col-lg-9">
												<input class="form-control" type="password"
													id="txt_password" name="password"
													placeholder="Password" />
											</div>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">

											<label class="control-label col-lg-4" for="cboRol">Rol:</label>
											<div class="col-lg-8">
												<select class="form-control" id="cboRol" name="idRol">
												</select>
											</div>

										</div>
									</div>
									
								</div>	
								<div>
									<h6></h6>
								</div>
								<div class="form-group">
									<div class="col-md-3 col-md-offset-1">
										<button id="btn_salir" type="reset" class="btn btn-primary">Salir</button>
										<button id="btn_enviar" class="btn btn-primary">
											Guardar <span class="fa  fa-save"></span>


										</button>

									</div>
									<div class="col-md-7" id="mensajeAlerta"></div>
								</div>
							</form>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true" disable="disable">
	<div class="modal-dialog" style="width: 80%;">
		<div class="modal-content">

			<div class="modal-header">

				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-titulo" id="myModalLabel">Agregar Trabajador</h4>
			</div>
			<div class="modal-body" id="container_form_modal_bod">

				<s:form class="simple_form" method="post"
					action="registraTrabajador" id="frm_AgregaTrabajador">
					<input type="hidden" id="IdCorrelativoTrabajador" value="0" />
					<div class="row">
						<div
							class="form-group col-sm-6 col-md.6 form-horizontal form-widgets">
							<label for="nombres" class="col-sm-3 control-label">Nombres:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_nom_asoc"
									name="nombres">
							</div>
						</div>
						<div
							class="form-group col-sm-6 col-md.6 form-horizontal form-widgets">
							<label for="apellido_paterno" class="col-sm-3 control-label">Ape.
								Paterno:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_ape_pat_asoc"
									name="apellido_paterno">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-6">
							<div class="form-group  form-horizontal form-widgets">
								<label for="apellido_materno" class="col-sm-3 control-label">Ape.
									Materno:</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="txt_ape_mat_asoc"
										name="apellido_materno">
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-6">
							<div class="form-group col-sm-7 form-horizontal form-widgets">
								<label for="selTip_Ident" class="col-sm-5 control-label">Tipo
									Identific.:</label>
								<div class="col-sm-7">
									<select class="form-control" id="cboTipDocModal" name="idtipodocumento">
									</select>
								</div>
							</div>
							<div class="form-group col-sm-5 inputDNI_Modal">
								<div class="col-sm-12">
									<input class="form-control" placeholder="documento"
										type="text" name="documento" id="txtDni_modal">
								</div>
							</div>

						</div>

					</div>

					<div class="row">
						<div class="form-group col-sm-6 form-horizontal form-widgets">
							<label for="selVend" class="col-sm-3 control-label">Parentesco:</label>
							<div class="col-sm-9">
									
									<select class="form-control" id="cboParentesco" name="idparentesco">
									</select>	

							</div>
						</div>

						<div class="form-group col-sm-6 form-horizontal form-widgets">
							<div class="col-sm-9">

								<button class="btn btn-success"
									id="btnAgregar_ModificarPersonasAsociadas">
									<span class="glyphicon glyphicon-floppy-disk"></span>
								</button>

							</div>
						</div>
					</div>

				</s:form>

			</div>
		</div>
	</div>
</div>

<!-- Modal Start here-->
<div class="modal fade bs-example-modal-sm" id="ModalLoading"
	tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<span class="glyphicon glyphicon-time"> </span>Por favor,
					espere....
				</h4>
			</div>
			<div class="modal-body">
				<div class="progress">
					<div
						class="progress-bar progress-bar-info progress-bar-striped active"
						style="width: 100%"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Modal ends Here -->
	<!--flag para Registrar y Modificar Socio-->
	
	<input type="hidden" id="hiddenInsert_UpdateTrabajador" value="0"/>
			<!-- 
	hiddenInsert_UpdateTrabajador = 0 : Significa Se Registrara Socio
	hiddenInsert_UpdateTrabajador = 1 : Significa Se Modificara Socio
	 -->