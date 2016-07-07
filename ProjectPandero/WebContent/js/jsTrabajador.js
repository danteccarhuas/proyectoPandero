$(document).ready(function(e){
	
	/* Valida las etiquedas del formulario */
	$("#frm_trabajador").bootstrapValidator({
		message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		"nombres" : {
			validators : {
				notEmpty : {
					message : 'Ingrese Nombre por favor.'
				},
				regexp : {
					regexp : /^([A-Za-zñÑáéíóúüÜÁÉÍÓÚÿ\s]+)$/,
					message : 'Solo Caracteres alfabético.'
				}
			}
		},
		"apellidos" : {
			validators : {
				notEmpty : {
					message : 'Ingrese Apellidos por favor.'
				},
				regexp : {
					regexp : /^([A-Za-zñÑáéíóúüÜÁÉÍÓÚÿ\s]+)$/,
					message : 'Solo Caracteres alfabético.'
				}
			}
		},
		"dni" : {
			validators : {
				notEmpty : {
					message : 'Ingrese DNI por favor.'
				},
				stringLength : {
					min : 8,
					max : 8,
					message : 'El DNI tiene 08 cifras maximo.'
				},
				integer : {
					message : 'Ingrese solo numeros.'
				}
			}
		},
		"login" : {
			validators : {
				notEmpty : {
					message : 'Ingrese loguin por favor.'
				}
			}
		},
		"password" : {
			validators : {
				notEmpty : {
					message : 'Ingrese password por favor.'
				}
			}
		},
		"idRol" : {
			validators : {
				notEmpty : {
					message : 'Seleccione un rol por favor.'
				}
			}
		},
		
	}
});
	
	//Activar Panel DatosTrabajador y Desactivar Panel ConsultarTrabajador
	$('#btn_nuevo').on('click', function () {		
		 $('#tab1').prop( "disabled", true ).addClass('disabled');
		 $('#tab2').prop( "disabled", false ).removeClass('disabled');
		 $('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
		 $('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');		
		 $('#hiddenindaccion').val(1); 
	});	
	
	//Activar Panel ConsultarTrabajador y Desactivar PanelDatosTrabajador
	$('#btn_salir').on('click', function () {
		$("#frm_trabajador").data('bootstrapValidator').resetForm(true);/*Limpiamos todos los controles del formulario frm_trabajador */
		$('#tab1').prop( "disabled", false ).removeClass('disabled');
		$('#tab2').prop( "disabled", true ).addClass('disabled');
		$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').prev('li').find('a').trigger('click');  
		$("#mensajeAlerta").html("");
		$("#paginador").html("");/*Limpiar los numero de paginacion*/
		//
		$("#btn_enviar").prop("disabled", false);
		
		$('#tab1:checked')?initGrilla():false;
	});
	

	
	//Asigna la class form-control de bootstrap para el tener su estilo
	$('#idrol').addClass('form-control');
	$('#idusuario').addClass('form-control');
	
	//Carga estilo de manito al pasar en el icon de guardar telefono y direccion
	$('.input-group').mouseover(function() {
		$('.input-group').css('cursor','pointer');	
	});
	
	
	$('#frm_trabajador').on('success.form.bv', function(e) {
		$('#hiddenInsert_UpdateTrabajador').val()==0?RegistrarTrabajador():ActualizarTrabajador();
		/*deshabilitar button btn_enviar*/
		$("#btn_enviar").prop("disabled", true);
		e.preventDefault();
	});
	
	$('#btn_buscar').click(function(e){
		$("#paginador").html("");/*Limpiar los numero de paginacion*/
		$('#ModalLoading').modal('show');
		initGrilla();		
		$('#ModalLoading').modal('hide');
	});
	
});

//Trae TotalRegistroSocio desde a BD para la paginacion
function initGrilla(){
	var cod=$('#txt_codigo_buscar').val()==""?"":$('#txt_codigo_buscar').val();
	var trab=$('#txt_nombre_apellido_buscar').val()==""?"":$('#txt_nombre_apellido_buscar').val();
	var dni=$('#txt_dni_buscar').val()==""?"":$('#txt_dni_buscar').val();
	$.ajax({
		url : 'TotalRegistrosTrabajador.action',
		type:'get',
		data:{"bean.idtrabajador":cod,"bean.nombres":trab,"bean.DNI":dni},
		contentType: "application/json; charset=utf-8",
		dataType:'json',
		success:function(result){
			var TotalRegistro=result;
			creaPaginador(TotalRegistro);
		}
	});
	
}


//Crea la Paginacion con el tota de Registros Traidos anteriormente desde a BD
function creaPaginador(totalItems)
{
	paginador = $(".pagination");
	totalPaginas = Math.ceil(totalItems/itemsPorPagina);
	$('<li><a href="#" class="first_link"><</a></li>').appendTo(paginador);
	$('<li><a href="#" class="prev_link">«</a></li>').appendTo(paginador);
	
	var pag = 0;
	while(totalPaginas > pag)
	{
		$('<li><a href="#" class="page_link">'+(pag+1)+'</a></li>').appendTo(paginador);
		pag++;
	}	
	if(numerosPorPagina > 1)
	{
		$(".page_link").hide();
		$(".page_link").slice(0,numerosPorPagina).show();
	}		
	$('<li><a href="#" class="next_link">»</a></li>').appendTo(paginador);
	$('<li><a href="#" class="last_link">></a></li>').appendTo(paginador);

	paginador.find(".page_link:first").addClass("active");
	paginador.find(".page_link:first").parents("li").addClass("active");

	paginador.find(".prev_link").hide();

	paginador.find("li .page_link").click(function()
	{
		var irpagina =$(this).html().valueOf()-1;
		cargaPagina(irpagina);
		return false;
	});

	paginador.find("li .first_link").click(function()
	{
		var irpagina =0;
		cargaPagina(irpagina);
		return false;
	});
	paginador.find("li .prev_link").click(function()
	{
		var irpagina =parseInt(paginador.data("pag")) -1;
		cargaPagina(irpagina);
		return false;
	});

	paginador.find("li .next_link").click(function()
	{
		var irpagina =parseInt(paginador.data("pag")) +1;
		cargaPagina(irpagina);
		return false;
	});

	paginador.find("li .last_link").click(function()
	{
		var irpagina =totalPaginas -1;
		cargaPagina(irpagina);
		return false;
	});
	cargaPagina(0);
}


//Carga a grilla con los datos paginados desde a BD
function cargaPagina(pagina){
	var desde = pagina * itemsPorPagina;	
	var cod=$('#txt_codigo_buscar').val()==""?"":$('#txt_codigo_buscar').val();
	var trab=$('#txt_nombre_apellido_buscar').val()==""?"":$('#txt_nombre_apellido_buscar').val();
	var dni=$('#txt_dni_buscar').val()==""?"":$('#txt_dni_buscar').val();

	$.ajax({
		url : 'ListarTrabajador.action',
		type : 'get',
		data : {"bean.idtrabajador":cod,"bean.nombres":trab,"bean.DNI":dni,"bean.paginador.limit":itemsPorPagina,
			"bean.paginador.offset":desde},
		dataType : 'json',
		success : function(result) {
			var lista=result;
			$("#rellenar").html("");
			var trHTML = '';
			if (lista.length > 0){ 
				for ( var i = 0; i < lista.length; i++) {
					trHTML += '<tr><td>'
						+ lista[i]['idtrabajador']
						+ '</td><td>'
						+ lista[i]['nombres']	
						+ '</td><td>'
						+ lista[i]['DNI']
						+ '</td><td>'
						+ lista[i]['idusuario']['login']	
						+ '</td><td>'
						+ lista[i]['idusuario']['password']	
						+ '</td><td>'
						+ lista[i]['rol']['descripcion']
						+ '</td><td><a href="" data-id="'+lista[i]['idtrabajador']+'" class="RecuperarTrabajador btn btn-info"><span class="fa fa-pencil-square-o "></span></a></td>'+
						'<td><a  data-id="'+lista[i]['idtrabajador']+'" class="btn btn-danger"><span class="EliminarTrabajador fa fa-trash-o" ></span></a></td></tr>';
				}
				
				$('#rellenar').append(trHTML);
			}			
		}
	});
	
	if(pagina >= 1)
	{
		paginador.find(".prev_link").show();
	}
	else
	{
		paginador.find(".prev_link").hide();
	}
	if(pagina <(totalPaginas- numerosPorPagina))
	{
		paginador.find(".next_link").show();
	}else
	{
		paginador.find(".next_link").hide();
	}

	paginador.data("pag",pagina);

	if(numerosPorPagina>1)
	{
		$(".page_link").hide();
		if(pagina < (totalPaginas- numerosPorPagina))
		{
			$(".page_link").slice(pagina,numerosPorPagina + pagina).show();
		}
		else{
			if(totalPaginas > numerosPorPagina)
				$(".page_link").slice(totalPaginas- numerosPorPagina).show();
			else
				$(".page_link").slice(0).show();

		}
	}
	paginador.children().removeClass("active");
	paginador.children().eq(pagina+2).addClass("active");	
}
	
//Var para definir la pagination de la grilla desde la BD
var paginador;
var totalPaginas;
var itemsPorPagina = 3;
var numerosPorPagina = 10;

	
	/* Se ejecuta cuando carga por primera vez la pagina */
	$(window).load(function() {
		
		//CargaCombos TipoDocumento y Pais
		$.ajax({
			url : 'cargaRol.action',
			type : 'post',
			data : '',
			contentType: "application/json; charset=utf-8",
			dataType : 'json',
			success : function(result) {
				var data1=result;
				
				Combo1=document.getElementById('cboRol');
				Combo1.options[0]= new Option('[Seleccione]','');
				for(var i=0;i<data1.length;i++){
					Combo1.options[Combo1.length]= new Option(data1[i].descripcion,data1[i].idRol);
				}
				
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("Error status code: " + xhr.status);
				alert("Error details: " + thrownError);
			}});
		//Verificar si Panel Consultar Socio esta Checked
		$('#tab1:checked')?initGrilla():false;
		
		/*Desabilitamos el tab eventotab2primary*/
		$('#eventotab2primary').click(function(event){
		        if ($(this).hasClass('disabled')) {
		            return false;
		        }
		 });
	});
	
	/*RegistrarTrabajador*/
	function RegistrarTrabajador(){
		$('#ModalLoading').modal('show');
		
		$.ajax({
			type : "post",
			url : "registraTrabajador.action",
			data :{"bean.nombres":$('#txt_nombre_guardar').val(),"bean.apellidos":$('#txt_ape_guardar').val()
				,"bean.DNI":$('#txt_dni_guardar').val(),"bean.idusuario.login":$('#txt_login').val(),
				"bean.idusuario.password":$('#txt_password').val(),
				"bean.rol.idRol":$('#cboRol').val()},
			dataType : 'json',
			success : function(resultado) {
				$('#ModalLoading').modal('hide');
				
				if(resultado!=""){
					$('#tab2primary #frm_trabajador #txt_cod_tra_guardar').val(resultado);
					var mensaje = "<div class='alert alert-success'>Se registraron los datos del Trabajador '"+resultado+"'</div>";
					$('#mensajeAlerta').html(mensaje);
				}
			},
			error : function(xrh, ajaxOptions, thrownError) {
				alert("Error status code: " + xrh.status);
				alert("Error details: " + thrownError);
			}
		});
	}
	
	function ActualizarTrabajador(){
		$('#hiddenInsert_UpdateTrabajador').val(0);/*Setear hiddenInsert_UpdateSocio a 0 Significar que Registrara Socio*/
		var idtrabajador=$('#txt_cod_tra_guardar').val();
		var nombres=$('#txt_nombre_guardar').val();
		var apellidos=$('#txt_ape_guardar').val();
		var DNI=$('#txt_dni_guardar').val();
		var login=$('#txt_login').val();
		var password=$('#txt_password').val();
		var idrol=$('#cboRol').val();
		
		var idusuario=$('#txt_idusuario').val();
		
		$('#ModalLoading').modal('show');
		$.ajax({
			type : "get",
			url : 'ModificarTrabajador.action',
			data : {"bean.idtrabajador":idtrabajador,"bean.nombres":nombres,"bean.apellidos":apellidos,
				"bean.DNI":DNI,"bean.idusuario.idusuario":idusuario,"bean.idusuario.login":login,
				"bean.idusuario.password":password,"bean.rol.idRol":idrol},
	        contentType: false,
			dataType : 'json',
			success : function(resultado) {
				$('#ModalLoading').modal('hide');
				
				if(resultado!="" && resultado!=-1){
						
					var mensaje = "<div class='alert alert-success'>Se actualizaron los datos del Trabajador</div>";
					$('#mensajeAlerta').html(mensaje);
				}
			},
			error : function(xrh, ajaxOptions, thrownError) {
				alert("Error status code: " + xrh.status);
				alert("Error details: " + thrownError);
			}
		});	
	}
	
	//Function para obtener los datos del socio y sus asociados para modificar sus datos
	$(document).on("click",".RecuperarTrabajador",function(e) {
		
		$('#hiddenInsert_UpdateTrabajador').val(1);/*Setear hiddenInsert_UpdateTrabajador a 1 Significar que modificara Trabajador*/
		
		var idTrabajador= $(this).data('id');
		$.ajax({
			url:'RecuperarTrabajador.action',
			type : 'post',
			data : {"bean.idtrabajador":idTrabajador},
			dataType : 'json',
			success : function(result) {
				var data=result;
				$('#tab1').prop( "disabled", true ).addClass('disabled');
				$('#tab2').prop( "disabled", false ).removeClass('disabled');
				$('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
				$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');
				
				$("#tab2primary #txt_cod_tra_guardar").val(data.idtrabajador);
				$("#tab2primary #txt_nombre_guardar").val(data.nombres);
				$("#tab2primary #txt_ape_guardar").val(data.apellidos);
				$("#tab2primary #txt_dni_guardar").val(data.DNI);
				$("#tab2primary #txt_login").val(data.idusuario.login);
				$("#tab2primary #txt_password").val(data.idusuario.password);
				$("#tab2primary #cboRol").val(data.rol.idRol);
				
				$('#txt_idusuario').val(data.idusuario.idusuario);

			},
			error : function(xrh, ajaxOptions, thrownError) {
				alert("Error status code: " + xrh.status);
				alert("Error details: " + thrownError);
			}
		});
		return false;
	});
	
	
	
	
	



