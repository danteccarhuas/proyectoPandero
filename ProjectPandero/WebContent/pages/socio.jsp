<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!-- prefix="sb" uri="/struts-bootstrap-tags" -->
<style>
#c {
	display: none;
}
</style>

<link href="${pageContext.request.contextPath}/css/jquery.modal.css" type="text/css" rel="stylesheet" />

<script src="${pageContext.request.contextPath}/js/jsPersonaAsociada.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.modal.js"></script>

<div class="container-fluid">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				Mantenimiento Socio
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
							data-toggle="tab">Consulta Socio</a></li>
						<li id="eventotab2primary" class="disabled"><a id="tab2"
							href="#tab2primary" data-toggle="tab">Datos Socio</a></li>
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
											<table id="miTabla_BuscarSocio" class="table table-bordered">
												<thead>
													<tr>
														<th>Codigo</th>
														<th>Nombres y Apellidos</th>
														<th>Documento</th>
														<th>Usuario</th>
														<th>Contraseña</th>
														<th>Imagen</th>
														<th colspan="2">Acción</th>
													</tr>
												</thead>
												<tbody id="rellenar">
													<!-- <tr>
														<td>SOC0001</td>
														<td>Ddsadsa</td>
														<td>70116576</td>
														<td>sa</td>
														<td>sql</td>
														<td>
															 <img src="${pageContext.request.contextPath}/images/face.png" class="img-rounded img-circle" alt="Cinque Terre" width="40" height="40"> 
														</td>
														<td></td>	
													</tr> -->
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
							<form class="form-horizontal" action="RegistrarSocio" id="frm_socio" method="post" accept-charset="UTF-8">
								<input type="hidden" id="IdCorrelTelefono" value="0" />
								<input type="hidden" id="IdCorrelDireccion" value="0" />
								<input type="hidden" id="hiddencodsocio" />
								<div class="row">
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-4"
												for="txt_cod_soc_guardar">Cod. Socio:</label>
											<div class="col-lg-8">
												<input class="form-control" type="text"
													id="txt_cod_soc_guardar"
													name="txt_cod_soc_guardar" disabled="disabled" />
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
												for="txt_ape_paterno">Ape. Paterno:</label>
											<div class="col-lg-9">
												<input class="form-control" type="text"
													id="txt_ape_paterno" name="apellido_paterno"
													placeholder="Apellido Paterno" />
											</div>
										</div>

									</div>
								</div>
								
								<div class="row">
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-4"
												for="txt_ape_materno">Ape. Materno:</label>
											<div class="col-lg-8">
												<input class="form-control" type="text"
													id="txt_ape_materno" name="apellido_materno"
													placeholder="Apellido Materno" />
											</div>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-3"
												for="txt_email">Correo Elect.:</label>
											<div class="col-lg-9">
												<input class="form-control" type="text"
													id="txt_email" name="correo"
													placeholder="Correo Electrónico" />
											</div>
										</div>

									</div>
									<div class="col-lg-4">
										<div class="form-group">

											<label class="control-label col-lg-3" for="cboPais">Pais:</label>
											<div class="col-lg-9">
												<select class="form-control" id="cboPais" name="idPais">
												</select>
											</div>

										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-4"
												for="cboTipoDocumento">Tipo Doc:</label>
											<div class="col-lg-8">
												<select class="form-control" id="cboTipoDocumento" name="idtipodocumento">
												</select>
											</div>
										</div>

									</div>
									<div class="col-lg-4">
										<div class="form-group" id="div_documento">
											<label class="control-label col-lg-3"
												for="txt_dni">Núm. Doc.:</label>
											<div class="col-lg-9" id="divdni">
												<input class="form-control" type="text"
													id="txt_dni" name="txt_dni"
													placeholder="Número Documento" disabled/> 
											</div>
											
											<div class="col-lg-9" id="divPasaporte" style="display: none;">
												<input class="form-control"
													type="text" id="txt_pasaporte" name="txt_pasaporte"
													placeholder="Número Documento" />
											</div>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-3" for="txt_telefono">Teléfono:</label>
											<div class="col-lg-9">
												<div class="input-group">
													<input class="form-control" type="text" id="txt_telefono"
														 name="txt_telefono" placeholder="Teléfono" /><span
														class="input-group-addon span_agregar_guarda_telefono"><i
														class="glyphicon glyphicon-floppy-disk"></i></span>
												</div>

												<div class="dataTable_wrapper"
													style="overflow-y: scroll; height: 80px;">
													<table
														class="table table-striped table-bordered table-hover"
														id="dataTables_telefonos">
														<thead>
															<tr>
																<th>Teléfono</th>
																<th colspan="2">Accion</th>
															</tr>
														</thead>
														<tbody id="rellenarTelefonos"></tbody>
													</table>
												</div>
											</div>
										</div>

									</div>
								</div>

								<div class="row">
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-4"
												for="cboDepartamento">Departamento:</label>
											<div class="col-lg-8">
												<select class="form-control" id="cboDepartamento" name="iddepartamento">
												</select>
											</div>
										</div>
									</div>

									<div class="col-lg-4">
										<div class="form-group">
											<div class="col-md-offset-3 col-lg-9">
												<select class="form-control" id="cboProvincia" name="idprovincia">
													<option value="">[Seleccione]</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<div class="col-md-offset-3 col-lg-9">
												<select class="form-control" id="cboDistrito" name="iddistrito">
													<option value="">[Seleccione]</option>
												</select>
											</div>
										</div>

									</div>
								</div>
								<div>
									<h6></h6>
								</div>
								<div class="row">
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-4"
												>Huella:</label>
											<div class="col-lg-8">
												<s:file id="txt_huella" cssClass="form-control"  name="socio.huella"  />
												<label class="control-label col-lg-6" id="lblHUella"></label> 
												<a href="" id="btnDescargarHuella" type="button" class="btn btn-info">Descargar</a>

												<!-- <input class="form-control" type="file"
													id="txt_huella" name="huella"/> -->
											</div>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-3"
												>Firma:</label>
											<div class="col-lg-9">
												<!--<s:file id="txt_firma" cssClass="form-control"  name="socio.firma"  />-->
												<!-- <label class="control-label col-lg-6" id="lblFirma"></label> --> 
												<!-- <a href="" id="btnDescargarFirma" type="button" class="btn btn-info">Descargar</a> -->
												<!-- <input class="form-control" type="file"
													id="txt_firma" name="firma"/> -->
												
												<s:hidden id="idHiddenFirma" name="imgFirma" value="" />
												<canvas id="canvas" width="250" height="200"></canvas>
												<div class="col-lg-12">
													<input type="color" id="color" value="#FF9200">
													<input type="button" id="bt-clear" value="CLEAR">
													<!-- <button id="bt-save">SAVE</button> -->
												</div>
												<img alt="firma" src=""id="firm_socio_aux" class="form-control" 
												style="padding: 17px 12px; width: 255px; height: 234px; display: none;" >	
											</div>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label class="control-label col-lg-3">Requisitos:</label>
											<div class="col-lg-9">
												<s:file id="txt_requisitos" cssClass="form-control"  name="socio.requisito_asociarse"  />
												<label class="control-label col-lg-6" id="lblRequisitosAsociarse"></label> 
												<a href="" id="btnDescargarReqAsociarse" type="button" class="btn btn-info">Descargar</a>
												<!-- <input class="form-control" type="file" id="txt_requisitos"
													name="requisito_asociarse" /> -->
											</div>
										</div>
									</div>

								</div>
								<div>
									<h6></h6>
								</div>
								
								<div class="row">
									<div class="col-lg-6">

										<div class="form-group">
											<label class="control-label col-lg-3" for="txt_firma">Dirección:</label>
											<div class="col-lg-9">
												<div class="input-group">
													<input class="form-control" type="text"
														name="direccion" id="txt_direccion"
														placeholder="Dirección"><span
														class="input-group-addon span_agregar_guarda_direccion"><i
														class="glyphicon glyphicon-floppy-disk"></i></span>
												</div>

												<div class="dataTable_wrapper"
													style="overflow-y: scroll; height: 80px;">
													<table
														class="table table-striped table-bordered table-hover"
														id="dataTables_direcciones">
														<thead>
															<tr>
																<th>Dirección</th>
																<th colspan="2">Accion</th>
															</tr>
														</thead>
														<tbody id="rellenardirecciones"></tbody>
													</table>
												</div>
											</div>
										</div>

									</div>
									<div class="col-lg-6 form-group">
										<textarea class="form-control" id="txt_motivo_actualizacion"
											name="motivo_actualizacion" rows="4"
											placeholder="Motivo de Actualización" style="max-width: 500px;"></textarea>
									</div>

								</div>
								
								<div>
									<h6></h6>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div id="accordion" role="tablist"
											aria-multiselectable="true">
											<div class="panel panel-default">
												<div class="panel-heading" role="tab" id="headingOne">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion"
															href="#collapseOne" aria-expanded="true"
															aria-controls="collapseOne">Agregar Asociados</a>
													</h4>
												</div>
												<div id="collapseOne" class="panel-collapse collapse on"
													role="tabpanel" aria-labelledby="headingOne">
													<div class="row">
														<div class="col-md-push-11 col-md-1">
															<button class="btn btn-success"
																id="btnViewModalPersonaAsociar">
																<span class="glyphicon glyphicon-plus"></span>
															</button>
														</div>
													</div>
													<div class="dataTable_wrapper" style="overflow-y: scroll; height: 120px;">
														<table
															class="table table-striped table-bordered table-hover"
															id="dataTables_example">
															<thead>
																<tr>
																	<th>Nombres</th>
																	<th>Ape. Paterno</th>
																	<th>APe.Materno</th>
																	<th>Documento</th>
																	<th>Tip. Documento</th>
																	<th>Parentesco</th>
																	<th colspan="2">Accion</th>
																</tr>
															</thead>
															<tbody id="rellenarAsociados"></tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div>
									<h6></h6>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-4">
											<label class="control-label col-lg-3"
												for="txt_apellidos_guardar">Foto:</label>
										</div>
										<div class="col-md-8">
										</div>
									</div>
									<div class="col-md-12">

										<div class="col-lg-4" id="div_Foto">
											<div class="form-group">
												<div class="col-lg-9" >
													<s:hidden id="idHidden" name="imagen" value="" />
													<video id='v' style="width: 286px; height: 234px;"
														class="form-control">
													</video>
													<canvas id='c'></canvas>
													<div>
														<h6></h6>
													</div>
													<button id='t' type="button" class="btn btn-success btn-lg">
														<span class="fa fa-camera"></span>
													</button>
												</div>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<div class="col-lg-9">
													<img id='img' name="imgFoto"
														style="padding: 17px 12px; width: 286px; height: 234px;"
														class="form-control" src="" height="230px">
													<s:hidden id="idHidden" value="" />
												</div>
											</div>
										</div>

									</div>
									
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

<!-- Modal de agregarPersonaAsociada -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true" disable="disable">
	<div class="modal-dialog" style="width: 80%;">
		<div class="modal-content">

			<div class="modal-header">

				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-titulo" id="myModalLabel">Agregar Asociados</h4>
			</div>
			<div class="modal-body" id="container_form_modal_bod">

				<form class="simple_form" method="post"
					action="AgregarPersonaAsociada" id="frm_AgregaAsociado">
					<input type="hidden" id="IdCorrelativoPerAsoc" value="0" />

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
							class="form-group col-sm-6 col-md-6 form-horizontal form-widgets">
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
								<div class="col-sm-12" id="divdniModal">
									<input class="form-control" placeholder="documento"
										type="text" name="txtDni_modal" id="txtDni_modal" disabled>
								</div>
								<div class="col-sm-12" id="divpasaporteModal"
									style="display: none;">
									<input class="form-control" placeholder="documento" type="text"
										name="txtPasaporte_modal" id="txtPasaporte_modal">
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

				</form>

			</div>
		</div>
	</div>
</div>

<!-- Modal de agregarPersonaAsociada -->

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
	<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    Mensaje de
                    Confirmacion
                </h4>
            </div>
            <div class="modal-body">
                Esta seguro que desea eliminar el
                registro seleccionado?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger removeBtn">Remove</button>
            </div>
        </div>
    </div>
</div>

<!--flag para Agregar y Modificar PersonaAsociada en Session-->
	
	<input type="hidden" id="hiddenAgregar_Editar" value="0"/>
			<!-- 
	hiddenAgregar_Editar = 0 : Significa Se agregara persoAsociada
	hiddenAgregar_Editar = 1 : Significa Se modificara persoAsociada
	 -->
	 
	 
	<!--flag para Agregar y Modificar Telefono en Session de Socio-->
	
	<input type="hidden" id="hiddenAgregar_Editar_telefono" value="0"/>
			<!-- 
	hiddenAgregar_Editar_telefono = 0 : Significa Se agregara telefono
	hiddenAgregar_Editar_telefono = 1 : Significa Se modificara telefono
	 -->
	 
	<!--flag para Agregar y Modificar Direccion en Session de Socio-->
	
	<input type="hidden" id="hiddenAgregar_Editar_direccion" value="0"/>
			<!-- 
	hiddenAgregar_Editar_direccion = 0 : Significa Se agregara direccion
	hiddenAgregar_Editar_direccion = 1 : Significa Se modificara direccion
	 -->
	
	<!--flag para setear provincia y distrito del socio(Ubigeo)-->
	<input type="hidden" id="hiddenvalueprovincia" value="0" />
	<input type="hidden" id="hiddenvaluedistrito" value="0" />
	
	
	<!--flag para Registrar y Modificar Socio-->
	
	<input type="hidden" id="hiddenInsert_UpdateSocio" value="0"/>
			<!-- 
	hiddenInsert_UpdateSocio = 0 : Significa Se Registrara Socio
	hiddenInsert_UpdateSocio = 1 : Significa Se Modificara Socio
	 -->
	
	<input type="hidden" id="hiddenValidaDocumento" />
				<!-- 
	hiddenValidaDocumento = 0 : Significa que va validar DNI
	hiddenValidaDocumento = 1 : Significa que va validar Pasaporte
	 -->
	<input type="hidden" id="hiddenValidaDocumentoModal"/>
	<!-- 
	hiddenValidaDocumentoModal = 0 : Significa que va validar DNI
	hiddenValidaDocumentoModal = 1 : Significa que va validar Pasaporte
	 -->