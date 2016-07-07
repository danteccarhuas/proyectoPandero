<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<link href="${pageContext.request.contextPath}/css/pago.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/jquery.modal.css" type="text/css" rel="stylesheet" />

<script src="${pageContext.request.contextPath}/js/jsPago.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.modal.js"></script>

<div class="container-fluid">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12" >
			<h1 class="page-header">
				REGISTRAR PAGO DE OBLIGACION
				<!--  <small>Statistics Overview</small> -->
			</h1>

		</div>
	</div>
	<div class="row">
		<div class="col-lg-12" style="width: 761px;">
			<div class="panel with-nav-tabs panel-primary">
				<div class="panel-heading">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tab1primary" 
							data-toggle="tab">Periodica con vencimiento</a></li>
						<li><a href="#tab2primary" 
							data-toggle="tab">Periodica sin vencimiento</a></li>
						<li><a href="#tab3primary"
							data-toggle="tab">Unica</a></li>
					</ul>
				</div>
				<div class="panel-body">
					<div class="tab-content">
						<!-- Empienza TabPanel 1(Periodica con vencimiento) -->
						<div class="tab-pane fade in active" id="tab1primary">
						
							<form action="registraPago" method="post" id="formPago">

								<label class="lblcodPago">Cod.Pago:</label> 
								<input class="txtPago" type="text" name="" disabled="disabled"> 
								<label class="lblFechaPago">Fecha de pago: </label> 
								
								<input class="txtFechaPago" type="text" name="fechaPago" disabled="disabled" id="txtFechaAtual">
								<!-- <input type="hidden" id="txt_fecha_actual" name="fechaPago"> -->
							
								<fieldset>
							
									<legend>Datos de Obligacion</legend>
									<label class="lblDni">DNI de socio:</label>
									<input required="required" class="txtDni" type="text" name="" id="dniSocio"> 
									<button class="btn btn-primary" id="btnViewModalPago" style="position: relative;right: -7px;width: 78px;height: 34px;">
										<label style="position: relative;left: -9px">Consultar</label>
									</button>
									<!-- <input class="btnConsultar" type="submit" value="Consultar"> --> 
									<label class="lblSocio" >Socio:</label> 
									<input required="required" class="txtSocio" type="text" disabled="disabled" id="txt_socio">
									<br>
									<br>
								    <label class="lblID" >ID Obligacion:</label>
								    <input required="required" class="txtID" type="text" disabled="disabled" id="txt_idObligacion">
								    <input type="hidden" id="txt_obligacion" name="idobligaciones">
									<label class="lblDescripcion" >Descripcion:</label>
									<input required="required" class="txtDescripcion" type="text" disabled="disabled"id="txt_descripcion">
									<br>
									<br> 
							
								</fieldset>
								<fieldset>
									<legend>Pagador</legend>
									<label>Documento:</label>
									<input required="required" style="position: relative;left: 17px;" type="text" name="dniPagador">
									<label style="position: relative;left: 100px;">Nombres:</label>
									<input required="required" style="position: relative;left: 175px;" type="text" name="nombresPagador">
								</fieldset>
								<fieldset>
									<legend>Detalle de Pago</legend>
									<div class="dataTable_wrapper" style="overflow-y: scroll;width:704px; height: 234px; position: relative;top: 9px;">
										<table
											class="table table-striped table-bordered table-hover"
											id="tabla2" style="position: relative;top: 0px;">
											<thead>
												<tr>
													<th>idCuota</th>
													<th>Monto</th>
													<th>Fecha Vencimiento</th>
													<th>Mora</th>
													<th>Total</th>
												</tr>
											</thead>
											<tbody id="rellenarDetalleFactura"></tbody>
										</table>
									</div>
								</fieldset>
								<br> 
								
								<input type="hidden" id="txt_mora" name="mora">
								<input type="hidden" id="txt_vencimiento" name="fechaVencimiento">
								<input type="hidden" id="txt_cuota" name="idcuota">
								
								<label class="lblTotal">Total a pagar:</label>
								<input class="txtTotal" type="text" disabled="disabled" id="txt_Total" name="montoPagado">
								<input type="hidden" id="txt_montoPagado" name="montoPagado">
								<br>
								<br>
								<!-- <input type="submit" value="Registrar" class="btn btn-primary"> --> 
								<input type="reset" value="Nuevo" class="btn btn-primary">
								<button id="btn_enviar" class="btn btn-primary">
											Registrar <span class="fa  fa-save"></span>
								</button>
								
							</form>
							
						</div><!-- Termina TabPanel 1 -->
						
						<!-- Empieza TabPanel 2 (Periodica sin vencimiento) -->
						<div class="tab-pane fade" id="tab2primary">
							<form action="registraPago_1" method="post" id="formPago_1">

								<label class="lblcodPago">Cod.Pago:</label> 
								<input class="txtPago" type="text" name="" disabled="disabled"> 
								<label class="lblFechaPago">Fecha de pago: </label> 
								
								<input class="txtFechaPago" type="text" name="fechaPago_1" disabled="disabled" id="txtFechaAtual_1">
							
							
								<fieldset>
							
									<legend>Datos de Obligacion</legend>
									<label class="lblDni">DNI de socio:</label>
									<input required="required" class="txtDni" type="text" name="" id="dniSocio_1"> 
									<button class="btn btn-primary" id="btnViewModalPago_1" style="position: relative;right: -7px;width: 78px;height: 34px;">
										<label style="position: relative;left: -9px">Consultar</label>
									</button>
									
									<label class="lblSocio" >Socio:</label> 
									<input class="txtSocio" type="text" disabled="disabled" id="txt_socio_1">
									<br>
									<br>
								    <label class="lblID" >ID Obligacion:</label>
								    <input class="txtID" type="text" disabled="disabled" id="txt_idObligacion_1">
								    <input type="hidden" id="txt_obligacion_1" name="idobligaciones_1">
									<label class="lblDescripcion" >Descripcion:</label>
									<input class="txtDescripcion" type="text" disabled="disabled"id="txt_descripcion_1">
									<br>
									<br> 
							
								</fieldset>
								<fieldset>
									<legend>Pagador</legend>
									<label>Documento:</label>
									<input required="required" style="position: relative;left: 17px;" type="text" name="dniPagador_1">
									<label style="position: relative;left: 100px;">Nombres:</label>
									<input required="required" style="position: relative;left: 175px;" type="text" name="nombresPagador_1">
								</fieldset>
								
								<fieldset>
									<legend>Monto a pagar</legend>
									<label>Ingrese monto</label>
									<input required="required" type="text" name="nuevoMonto">
								</fieldset>
								<br> 

								<input type="reset" value="Nuevo" class="btn btn-primary">
								<button id="btn_enviar_1" class="btn btn-primary">
											Registrar <span class="fa  fa-save"></span>
								</button>
								
							</form>

						</div><!-- Termina TabPanel 2 -->
						
						<!-- Empieza TabPanel 3 (unica) -->
						<div class="tab-pane fade" id="tab3primary">
							<form action="registraPago_2" method="post">

								<label class="lblcodPago">Cod.Pago:</label> 
								<input class="txtPago" type="text" name="" disabled="disabled"> 
								<label class="lblFechaPago">Fecha de pago: </label> 
								
								<input class="txtFechaPago" type="text" disabled="disabled" id="txtFechaAtual">
								
							
								<fieldset>
							
									<legend>Datos de Obligacion</legend>
									<label class="lblDni">DNI de socio:</label>
									<input class="txtDni" type="text" name="" id="dniSocio_2"> 
									<button class="btn btn-primary" id="btnViewModalPago_2" style="position: relative;right: -7px;width: 78px;height: 34px;">
										<label style="position: relative;left: -9px">Consultar</label>
									</button>
									
									<label class="lblSocio" >Socio:</label> 
									<input class="txtSocio" type="text" disabled="disabled" id="txt_socio_2">
									<br>
									<br>
								    <label class="lblID" >ID Obligacion:</label>
								    <input class="txtID" type="text" disabled="disabled" id="txt_idObligacion_2">
								    <input type="hidden" id="txt_obligacion_2" name="idobligaciones_2">
									<label class="lblDescripcion" >Descripcion:</label>
									<input class="txtDescripcion" type="text" disabled="disabled"id="txt_descripcion_2">
									<br>
									<br> 
									<label class="lblID" >Monto:</label>
								    <input style="position: relative;right: -48px" type="text" disabled="disabled" id="txt_monto">
									<label style="position: relative;left: 132px" class="lblDescripcion" >F.Vencimiento:</label>
									<input style="position: relative;left: 172px" type="text" disabled="disabled" id="txt_vencimiento">
									<br>
									<br> 
									
								</fieldset>
								<input type="submit" value="Registrar" class="btn btn-primary"> 
								<input type="reset" value="Nuevo" class="btn btn-primary">
								
							</form>
							
						</div><!-- Termina TabPanel 3 -->
					</div>
				</div>
			</div>	
		</div>
	</div>
</div><!-- Termina el contenedor -->



<!--/****************************************************************************************************/-->


<!-- VENTANA MODAL 1-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"  disabled="disable">
	<div class="modal-dialog" style="width: 80%;">
		<div class="modal-content">

			<div class="modal-header">

				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-titulo" id="myModalLabel">OBLIGACION CON CUOTAS</h4>
			</div>
			<div class="modal-body" id="container_form_modal_bod" style="height:500px;">

				<form class="simple_form" method="post"
					action="AgregarPersonaAsociada" id="frm_Obligacion_con_cuotas">
					
					<div class="row">
						<div
							class="form-group col-sm-6 col-md.6 form-horizontal form-widgets">
							<label for="socio" class="col-sm-3 control-label">Socio:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_socio" disabled="disabled">
							</div>
						</div>
						<div
							class="form-group col-sm-6 col-md.6 form-horizontal form-widgets">
							<label for="id_obligacion" class="col-sm-3 control-label">ID Obligacion:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_id_obligacion" disabled="disabled">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-6">
							<div class="form-group  form-horizontal form-widgets">
								<label for="descripcion" class="col-sm-3 control-label">Descripcion:</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="txt_descripcion" disabled="disabled">
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-6">
							<div class="form-group  form-horizontal form-widgets">
								<label for="monto" class="col-sm-3 control-label">Monto:</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="txt_monto" disabled="disabled">
								</div>
							</div>
						</div>

					</div>
					<br>	

					<div class="row">
						<div class="col-sm-6 col-md-6">
							<div class="form-group  form-horizontal form-widgets">
								<label for="cuotas" class="col-sm-3 control-label">Cuotas:</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="txt_cuotas" disabled="disabled">
								</div>
							</div>
						</div>
						
						<div class="col-sm-6 col-md-6">
							<div class="form-group  form-horizontal form-widgets">
								<label for="vencimiento" class="col-sm-3 control-label">Venci. Obligacion:</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="txt_vencimiento" disabled="disabled">
								</div>
							</div>
						</div>
					</div> 	
					<div class="dataTable_wrapper" style="overflow-y: scroll; height: 234px; position: relative;top: 9px;">
								<table
									class="table table-striped table-bordered table-hover"
									id="tabla1" style="position: relative;top: 0px;">
					 
									<thead>
										<tr>
											<th>ID Couta</th>
											<th>Monto</th>
											<th>F. Vencimiento</th>
											<th>Mora</th>
											<th>Estado</th>
											<th>Seleccionar</th>
										</tr>
									</thead>
									<tbody id="rellenarCuotas">
									
									</tbody>
								</table>
					</div>
	
					<div class="form-group col-sm-6 form-horizontal form-widgets" style="position: relative;top: 32px;left: 441px;">
						<div class="col-sm-9">
							<button  class="btn btn-success" data-dismiss="modal">
								ENVIAR
							</button>
						</div>
					</div>
					
				</form>

			</div>
		</div>
	</div>
</div><!-- Termina Modal 1 -->



<!--Ventana Modal 2-->
<div class="modal fade" id="myModal_2" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"  disabled="disable">
	<div class="modal-dialog" style="width: 80%;">
		<div class="modal-content">

			<div class="modal-header">

				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-titulo" id="myModalLabel">OBLIGACION SIN VENCIMIENTO</h4>
			</div>
			<div class="modal-body" id="container_form_modal_bod">

				<s:form class="simple_form" method="post"
					action="AgregarPersonaAsociada" id="frm_AgregaAsociado">
					<input type="hidden" id="IdCorrelativoPerAsoc" value="0" />
					<div class="row">
						<div
							class="form-group col-sm-6 col-md.6 form-horizontal form-widgets">
							<label for="nombres" class="col-sm-3 control-label">Socio:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_socio_1"
									name="nombres" disabled="disabled">
							</div>
						</div>
						<div
							class="form-group col-sm-6 col-md.6 form-horizontal form-widgets">
							<label for="apellido_paterno" class="col-sm-3 control-label">ID Obligacion:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="txt_id_obligacion_1"
									name="apellido_paterno" disabled="disabled">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-6">
							<div class="form-group  form-horizontal form-widgets">
								<label for="apellido_materno" class="col-sm-3 control-label">Descripcion:</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="txt_descripcion_1"
										name="apellido_materno" disabled="disabled">
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-6">
							<div class="form-group  form-horizontal form-widgets">
								<label for="apellido_materno" class="col-sm-3 control-label">Monto Acumulado:</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="txt_monto_1"
										name="apellido_materno" disabled="disabled">
								</div>
							</div>
						</div>

					</div>
					<br>	
					
					<div class="dataTable_wrapper" style="overflow-y: scroll; height: 120px;">
						<fieldset>
							<legend>Pagos anteriores</legend>
								<table
									class="table table-striped table-bordered table-hover"
									id="dataTables_example" style="position: relative;top: -61px;">
									<thead>
										<tr>
											<th>ID Pago</th>
											<th>Monto</th>
											<th>F. Pago</th>
										</tr>
									</thead>
									<tbody id="rellenarPagosAnteriores"></tbody>
								</table>
						</fieldset>
					</div>

				</s:form>

			</div>
		</div>
	</div>
</div><!-- Termina modal 2 -->