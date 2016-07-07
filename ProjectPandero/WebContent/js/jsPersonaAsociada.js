$(document).ready(function(e){
	
	/*Limpiar firma de canvaas*/
	//document.getElementById('bt-clear').onmouseup = resetCanvas;
	$('#bt-clear').click(function(e) {
		resetCanvas();
		return e.preventDefault();
	});
	
	
	/*Validar solo numero telefono*/
	$('#txt_telefono').keypress(function(e){
        var test = /^[\-]?[0-9]{1,7}?$/;
        return test.test(this.value+String.fromCharCode(e.which));
	});
	
	//Activar Panel DatosSocio y Desactivar Panel ConsultarSocio
	$('#btn_nuevo').on('click', function () {		
		 $('#tab1').prop( "disabled", true ).addClass('disabled');
		 $('#tab2').prop( "disabled", false ).removeClass('disabled');
		 $('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
		 $('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');		
		 $('#hiddenindaccion').val(1); 
		 
			//Mostramos el input type file
			$('#txt_huella').show();
			//$('#txt_firma').show();
			$('.bootstrap-filestyle').show();
			$('#txt_requisitos').show();
			$('#canvas').show();
			$('#color').show();
			$('#bt-clear').show();
		 
		 /*Cambiar Name input documento*/
		$('#txt_dni').attr('name', 'txt_dni');
		$('#txt_pasaporte').attr('name', 'txt_pasaporte');
		 
		 $('#div_Foto').show();
		 //Cargar Camara si el panel DatosSocio esta Checked
		 $('#tab2:checked')?
				 $(function() {
					 /*Cargar Camara*/
					 CargarCamara();
					/*Cargar el canvas de la firma*/
					new tiemposcambian.GuardandoPNGs().init();	
				})
				 :false;
		 
		 /*ocultar button de descargas y sus label*/
		 $('#lblHUella').hide();
		 $('#btnDescargarHuella').hide();
		 //$('#lblFirma').hide();
		 //$('#btnDescargarFirma').hide();
		 $('#lblRequisitosAsociarse').hide();
		 $('#btnDescargarReqAsociarse').hide();
		 $('#firm_socio_aux').hide();
		 
		 
			/*Se habilitaran los input text y lo select que no se que fueron deshabilitados*/
			$('#txt_nombre_guardar, #txt_ape_paterno, #txt_ape_materno,'+ 
				'#txt_email, #cboPais, #cboTipoDocumento, #txt_numerodoc, #cboDepartamento,'+ 
				'#cboProvincia, #cboDistrito').prop('disabled',false);
			/*ocultar el textarea de motivo de actuazacion de socio*/
		 $('#txt_motivo_actualizacion').hide();
		 		
		/*Limpiar Firma*/
		resetCanvas();
		
		/*habilitar button btn_enviar*/
		$("#btn_enviar").prop("disabled", false);
	});	
	
	//Activar Panel ConsultarSocio y Desactivar PanelDatosSocio
	$('#btn_salir').on('click', function () {
		$("#frm_socio").data('bootstrapValidator').resetForm(true);/*Limpiamos todos los controles del formulario frm_socio */
		/*Limpiar la foto*/
		$('#img').attr('src', '');
		$('#firm_socio_aux').attr('src', '');
		$('#tab1').prop( "disabled", false ).removeClass('disabled');
		$('#tab2').prop( "disabled", true ).addClass('disabled');
		$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').prev('li').find('a').trigger('click');  
		$("#mensajeAlerta").html("");
		$("#paginador").html("");/*Limpiar los numero de paginacion*/
		
		$("#btn_enviar").prop("disabled", false);
		
		$('#tab1:checked')?initGrilla():false;
		/*var video = document.querySelector('#v');
		video.stop()*/
		
		/*limpiar hidden hiddenValidaDocumentoModal y hiddenValidaDocumento*/			
		$('#hiddenValidaDocumentoModal').val("");
		$('#hiddenValidaDocumento').val("");
		
		/*ocultar div de tipo de documento si no estan ocultos*/
		$('#divdni').hide();
		$('#divPasaporte').hide();
		$('#divdni').show();
		$('#txt_dni').prop("disabled",true);
		
		
		/*Resetear si tiene validacion*/
		$('#frm_AgregaAsociado').data('bootstrapValidator').resetField($('#txtPasaporte_modal'), true);
		$('#frm_AgregaAsociado').data('bootstrapValidator').resetField($('#txtDni_modal'), true);
		$('#frm_socio').data('bootstrapValidator').resetField($('#txt_dni'), true);
		$('#frm_socio').data('bootstrapValidator').resetField($('#txt_pasaporte'), true);
		
		
		
		$('#hiddenInsert_UpdateSocio').val(0);/*Setear hiddenInsert_UpdateSocio a 0 Significar que Registrara Socio*/

		//va un servidor a eliminar los datos en sessiones(Telefonos,direccion,asociados)
		$.ajax({
			url:'EliminarListadoEnSessiones.action',
			type : 'post',
			data : {},
			dataType : 'json',
			success : function(result) {
				if(result==1){
					ListarDirecciones(0);
					ListarPersona(0);
					ListarTelefonos(0);
					return;
				}
			}
		});
	});
	
	//Muetra el Modal
	$('#btnViewModalPersonaAsociar').click(function(e) {
		$("#myModal").modal({
			keyboard : false,
			backdrop : 'static'

		});
		$('#myModal').on('hidden.bs.modal', function(){
			
			$('#divpasaporteModal').hide();
			$('#divdniModal').hide();
			$('#divdniModal').show();
			$('#txtDni_modal').prop("disabled",true);
			
			/*Limpiamos todos los controles del formulario frm_AgregaAsociado */
		    $(this).find($('form'))[0].reset();

	        //Removing the error elements from the from-group
	        $('#frm_AgregaAsociado .form-group').removeClass('has-error has-feedback');
	        $('#frm_AgregaAsociado .form-group').removeClass('has-success has-feedback');
	        $('#frm_AgregaAsociado .form-group').find('small.help-block').hide();
	        $('#frm_AgregaAsociado .form-group').find('i.form-control-feedback').hide();
		});
		e.preventDefault();
	});
	
	//Asigna la class form-control de bootstrap para el tener su estilo
	$('#idtipodocumento').addClass('form-control');
	$('#idparentesco').addClass('form-control');
	
	$('#frm_AgregaAsociado').on('success.form.bv', function(e) {
		//cambiar el name de input documento
		$('#hiddenValidaDocumentoModal').val()==0?$('#txtDni_modal').attr('name', 'documento'):$('#txtPasaporte_modal').attr('name', 'documento');
		if($('#txt_nom_asoc').val()!="" && $('#txt_ape_pat_asoc').val()!="" && $('#txt_ape_mat_asoc').val()!=""
			&& $('#cboTipDocModal').val()!="" && $('input[name="documento"]').val()!="" && $('#cboParentesco').val()!=""){
				Add_UpdatePersonaAsociada();
				//e.preventDefault();
		}
		e.preventDefault();
	});
	
	/* Valida las etiquedas del formulario Modal del PersonaAsociociada*/
	$("#frm_AgregaAsociado").bootstrapValidator({
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
						regexp : /^([A-Za-zÒ—·ÈÌÛ˙¸‹¡…Õ”⁄ˇ\s]+)$/,
						message : 'Solo Caracteres alfabÈtico.'
					}
				}
			},
			"apellido_paterno" : {
				validators : {
					notEmpty : {
						message : 'Ingrese Apellido Paterno por favor.'
					},
					regexp : {
						regexp : /^([A-Za-zÒ—·ÈÌÛ˙¸‹¡…Õ”⁄ˇ\s]+)$/,
						message : 'Solo Caracteres alfabÈtico.'
					}
				}
			},
			"apellido_materno" : {
				validators : {
					notEmpty : {
						message : 'Ingrese Apellido Materno por favor.'
					},
					regexp : {
						regexp : /^([A-Za-zÒ—·ÈÌÛ˙¸‹¡…Õ”⁄ˇ\s]+)$/,
						message : 'Solo Caracteres alfabÈtico.'
					}
				}
			},
			"txt_ape_mat_asoc" : {
				validators : {
					notEmpty : {
						message : 'Ingrese Apellido Materno por favor.'
					},
					regexp : {
						regexp : /^([A-Za-zÒ—·ÈÌÛ˙¸‹¡…Õ”⁄ˇ\s]+)$/,
						message : 'Solo Caracteres alfabÈtico.'
					}
				}
			},
			"idtipodocumento" : {
				validators : {
					notEmpty : {
						message : 'Seleccione un Tipo Documento por favor.'
					}
				}
			},
			"txtDni_modal" : {
				validators : {
					notEmpty : {
						message : 'Ingrese DNI por favor.'
					},
					stringLength : {
						min : 8,
						max : 8,
						message : 'DNI tiene 08 cifras m·ximo.'
					},
					integer : {
						message : 'Ingrese solo n˙meros.'
					}
				}
			},
			"txtPasaporte_modal" : {
				validators : {
					notEmpty : {
						message : 'Ingrese Pasaporte por favor.'
					},
					stringLength : {
						min : 11,
						max : 11,
						message : 'Pasaporte tiene 11 cifras m·ximo.'
					},
					integer : {
						message : 'Ingrese solo n˙meros.'
					}
				}
			},
			"idparentesco" : {
				validators : {
					notEmpty : {
						message : 'Seleccione un Parentesco por favor.'
					}
				}
			},
		}
	});
	
	//Obtener Data PersonaAsociada para Modificar
	
	$(document).on("click",".RecuperarPersonaAsociada",function(e) {
		
		var codigo_persoAsociada= $(this).data('id');
		$.ajax({
			url:'RecuperarPersonaAsociada.action',
			type : 'post',
			data : {idpersonaAsociada:codigo_persoAsociada},
			dataType : 'json',
			success : function(result) {
				
				$("#hiddenAgregar_Editar").val(1);//Seteamos el flag a 1, significa que se modificara los datos de PersonaAsociada
				var data1=result;
				
				//hidden para recuperar luego para el actualizar PersonaAsociada
				$('#IdCorrelativoPerAsoc').val(data1.idCorrelativo);
				
				$(".modal-body #frm_AgregaAsociado #txt_nom_asoc").val(data1.nombres);
				$(".modal-body #frm_AgregaAsociado #txt_ape_pat_asoc").val(data1.apellido_paterno);
				$(".modal-body #frm_AgregaAsociado #txt_ape_mat_asoc").val(data1.apellido_materno);
				$(".modal-body #frm_AgregaAsociado #cboTipDocModal").val(data1.tipo_documento.idtipodocumento);
				$(".modal-body #frm_AgregaAsociado #txtDni_modal").val(data1.documento);

				if(data1.valida_document==0){
					$(".modal-body #frm_AgregaAsociado #txtDni_modal").val(data1.documento);
					$('#hiddenValidaDocumentoModal').val(0);
				}else if(data1.valida_document==1){
					$(".modal-body #frm_AgregaAsociado #txtPasaporte_modal").val(data1.documento);
					$('#hiddenValidaDocumentoModal').val(1);
				}

				$(".modal-body #frm_AgregaAsociado #cboParentesco").val(data1.parentesco.idparentesco);
			}
		});
		e.preventDefault();
		$("#myModal").modal({
			keyboard : false,
			backdrop : 'static'

		});
	});
	
	
	//Eliminar PersonaAsociada en Session
	$(document).on("click",".EliminarPersonaAsociada",function(e) {
		
		var codigo_persoAsociada= $(this).data('id');
		var idSocio=$('#txt_cod_soc_guardar').val();
		$('#ModalLoading').modal('show');
		$.ajax({
			url:'EliminarPersonaAsociada.action',
			type : 'post',
			data : {idpersonaAsociada:codigo_persoAsociada,idsocio:idSocio},
			dataType : 'json',
			success : function(result) {
				$('#ModalLoading').modal('hide');
				if(result==1){
					ObtenerPersonasAsociadas();
				}
			}
		});
		e.preventDefault();
	});
	
	//Obtener Ubigeo de Provincias a partir de un departamento
	$('#cboDepartamento').change(function() {
		var combodepartamento = $("#cboDepartamento").val();
		CargarComboDependiente(combodepartamento, 'cboProvincia','ProyectoPand/cargaProvincia.action');
	});
	
	//Obtener Ubigeo de Distritos a partir de una Provincia
	$('#cboProvincia').change(function() {
		var comboprovincia = $("#cboProvincia").val();
		CargarComboDependiente(comboprovincia, 'cboDistrito','ProyectoPand/cargaDistrito.action');
	});
	
	/* Valida las etiquedas del formulario del Socio*/
	$("#frm_socio").bootstrapValidator({
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
					regexp : /^([A-Za-zÒ—·ÈÌÛ˙¸‹¡…Õ”⁄ˇ\s]+)$/,
					message : 'Solo Caracteres alfabÈtico.'
				}
			}
		},
		"apellido_paterno" : {
			validators : {
				notEmpty : {
					message : 'Ingrese Apellido Paterno por favor.'
				},
				regexp : {
					regexp : /^([A-Za-zÒ—·ÈÌÛ˙¸‹¡…Õ”⁄ˇ\s]+)$/,
					message : 'Solo Caracteres alfabÈtico.'
				}
			}
		},
		"apellido_materno" : {
			validators : {
				notEmpty : {
					message : 'Ingrese Apellido Materno por favor.'
				},
				regexp : {
					regexp : /^([A-Za-zÒ—·ÈÌÛ˙¸‹¡…Õ”⁄ˇ\s]+)$/,
					message : 'Solo Caracteres alfabÈtico.'
				}
			}
		},
		"correo" : {
			validators : {
				notEmpty : {
					message : 'Ingrese Correo ElectrÛnico por favor.'
				},
				emailAddress : {
					message : 'El Correo Electronico ingresado no es v·lida.'
				}
			}
		},
		"idPais" : {
			validators : {
				notEmpty : {
					message : 'Seleccione un Pais por favor.'
				}
			}
		},
		"idtipodocumento" : {
			validators : {
				notEmpty : {
					message : 'Seleccione un Tipo Documento por favor.'
				}
			}
		},
		"txt_dni" : {
			validators : {
				notEmpty : {
					message : 'Ingrese DNI por favor.'
				},
				stringLength : {
					min : 8,
					max : 8,
					message : 'DNI tiene 08 cifras m·ximo.'
				},
				integer : {
					message : 'Ingrese solo n˙meros.'
				}
			}
		},
		"txt_pasaporte" : {
			validators : {
				notEmpty : {
					message : 'Ingrese Pasaporte por favor.'
				},
				stringLength : {
					min : 11,
					max : 11,
					message : 'Pasaporte tiene 11 cifras m·ximo.'
				},
				integer : {
					message : 'Ingrese solo n˙meros.'
				}
			}
		},
		"socio.huella" : {
			validators: {
				notEmpty : {
					message : 'Seleccione una Imagen por favor.'
				},
                file: {
                    extension: 'jpeg,jpg,png',
                    type: 'image/jpeg,image/png',
                    maxSize: 2097152,   // 2048 * 1024
                    message: 'El archivo Seleccionado no es valido.'
                }
            }
		},
		/*"socio.firma" : {
			validators: {
				notEmpty : {
					message : 'Seleccione una Imagen por favor.'
				},
                file: {
                    extension: 'jpeg,jpg,png',
                    type: 'image/jpeg,image/png',
                    maxSize: 2097152,   // 2048 * 1024
                    message: 'El archivo Seleccionado no es valido.'
                }
            }
		},*/
		"socio.requisito_asociarse" : {
			validators: {
				notEmpty : {
					message : 'Seleccione un archivo PDF por favor.'
				},
                file: {
                    extension: 'pdf',
                    type: 'application/pdf',
                    maxSize: 2097152,   // 2048 * 1024
                    message: 'El archivo Seleccionado no es valido.'
                }
            }
		},
		"iddepartamento" : {
			validators : {
				notEmpty : {
					message : 'Seleccione un Departamento por favor.'
				}
			}
		},
		"idprovincia" : {
			validators : {
				notEmpty : {
					message : 'Seleccione una Provincia por favor.'
				}
			}
		},
		"iddistrito" : {
			validators : {
				notEmpty : {
					message : 'Seleccione un Distrito por favor.'
				}
			}
		},
		"motivo_actualizacion" : {
			validators : {
				notEmpty : {
					message : 'Ingrese Motivo de ActualizaciÛn'
				}
			}
		}
	}
});
	
	$('#frm_socio').on('success.form.bv', function(e) {
		$('#hiddenInsert_UpdateSocio').val()==0?RegistrarSocio():ActualizarSocio();
		/*deshabilitar button btn_enviar*/
		$("#btn_enviar").prop("disabled", true);
		e.preventDefault();
	});
	
	
	/*input de documentos del frm_socio*/
	$("#cboTipoDocumento").change(function(){			
		var s= this.options[this.selectedIndex].value;
		if(s==1){
			$('#divdni').show();
			$('#frm_socio').data('bootstrapValidator').resetField($('#txt_pasaporte'), true);
			$('#divPasaporte').hide();
			$('#hiddenValidaDocumento').val(0);
			$('#txt_dni').prop("disabled",false);    

		}else if(s==2){
			$('#frm_socio').data('bootstrapValidator').resetField($('#txt_dni'), true);
			$('#hiddenValidaDocumento').val(1);
			$('#divdni').hide();
			$('#divPasaporte').show();
		}else if(s==""){
			$('#hiddenValidaDocumento').val()==0?$(function() {
				$('#frm_socio').data('bootstrapValidator').resetField($('#txt_dni'), true);
				$('#txt_dni').prop("disabled",true);
			}) :$(function(){
				$('#frm_socio').data('bootstrapValidator').resetField($('#txt_pasaporte'), true);
				$('#divPasaporte').hide();
				$('#divdni').show();
				$('#txt_dni').prop("disabled",true);
			});
		}
		
	}); 
	
	/*valida los input del modal frm_Asociada*/
	$("#cboTipDocModal").change(function(){			
		var s= this.options[this.selectedIndex].value;
		if(s==1){
			$('#divdniModal').show();
			$('#frm_AgregaAsociado').data('bootstrapValidator').resetField($('#txtPasaporte_modal'), true);
			$('#divpasaporteModal').hide();
			$('#hiddenValidaDocumentoModal').val(0);
			$('#txtDni_modal').prop("disabled",false);    

		}else if(s==2){
			$('#frm_AgregaAsociado').data('bootstrapValidator').resetField($('#txtDni_modal'), true);
			$('#hiddenValidaDocumentoModal').val(1);
			$('#divdniModal').hide();
			$('#divpasaporteModal').show();
		}else if(s==""){
			$('#hiddenValidaDocumentoModal').val()==0?$(function() {
				$('#frm_AgregaAsociado').data('bootstrapValidator').resetField($('#txtDni_modal'), true);
				$('#txtDni_modal').prop("disabled",true);
			}) :$(function(){
				$('#frm_AgregaAsociado').data('bootstrapValidator').resetField($('#txtPasaporte_modal'), true);
				$('#divpasaporteModal').hide();
				$('#divdniModal').show();
				$('#txtDni_modal').prop("disabled",true);
			});
		}
		
	}); 
		
	/*Style de los input type file*/
	$('#txt_huella').filestyle({
		buttonText : 'Huella',
		buttonName : 'btn-primary'								
	});
	/*$('#txt_firma').filestyle({
		buttonText : 'Firma',
		buttonName : 'btn-primary'								
	});*/
	$('#txt_requisitos').filestyle({
		buttonText : 'Requisitos',
		buttonName : 'btn-primary'								
	});
	
	/*RegistrarSocio*/
	function RegistrarSocio(){
		/*transformar la firma canvas a imagen*/
		var data = canvas.toDataURL('image/png');
		var varHiddenFirma = document.getElementById("idHiddenFirma");
		varHiddenFirma.setAttribute('value', data);
		
		//cambiar el name de input documento
		$('#hiddenValidaDocumento').val()==0?$('#txt_dni').attr('name', 'documento'):$('#txt_pasaporte').attr('name', 'documento');
		
		var file=new FormData($('#frm_socio')[0]);
		$('#ModalLoading').modal('show');
		$.ajax({
			type : "post",
			url : $('#frm_socio').attr('action'),
			data : file,
	        contentType: false,
	        processData: false,
			dataType : 'json',
			success : function(resultado) {
				$('#ModalLoading').modal('hide');
				
				if(resultado!=""){
					$('#tab2primary #frm_socio #txt_cod_soc_guardar').val(resultado);
					var mensaje = "<div class='alert alert-success'>Se registraron los datos del Socio '"+resultado+"'</div>";
					$('#mensajeAlerta').html(mensaje);
					$('#txt_dni').attr('name', 'txt_dni');
					$('#txt_pasaporte').attr('name', 'txt_pasaporte');
				}
			},
			error : function(xrh, ajaxOptions, thrownError) {
				alert("Error status code: " + xrh.status);
				alert("Error details: " + thrownError);
			}
		});
	}
	
	
	/*Actualiza Socio*/
	function ActualizarSocio(){
		$('#hiddenInsert_UpdateSocio').val(0);/*Setear hiddenInsert_UpdateSocio a 0 Significar que Registrara Socio*/
		var idsocio=$('#txt_cod_soc_guardar').val();
		var motivo_acualizacion_socio=$('#txt_motivo_actualizacion').val()==""?"":$('#txt_motivo_actualizacion').val();
		
		$('#ModalLoading').modal('show');
		$.ajax({
			type : "get",
			url : 'ModificarSocio.action',
			data : {idsocio:idsocio,motivo_actualizacion:motivo_acualizacion_socio},
	        contentType: false,
			dataType : 'json',
			success : function(resultado) {
				$('#ModalLoading').modal('hide');
				
				if(resultado!="" && resultado!=-1){
						
					var mensaje = "<div class='alert alert-success'>Se actualizaron los datos del Socio</div>";
					$('#mensajeAlerta').html(mensaje);
				}
			},
			error : function(xrh, ajaxOptions, thrownError) {
				alert("Error status code: " + xrh.status);
				alert("Error details: " + thrownError);
			}
		});		
	}
	
	//Carga estilo de manito al pasar en el icon de guardar telefono y direccion
	$('.input-group').mouseover(function() {
		$('.input-group').css('cursor','pointer');	
	});
	
	//function para agregar y modificar telefono
	$('.span_agregar_guarda_telefono').click(function(e) {		
		var telef=$('#txt_telefono').val()==""?"":$('#txt_telefono').val();
				
		if (telef==""){
			modal({
				type: 'alert',
				title: 'InformaciÛn',
				text: "Por favor Ingresa TelÈfono!"  
			});	
			return ;
		}
		else if(telef !=""){
			
			$.ajax({
				url:'Agrega_Modifica_Telefono.action',
				type:'get',
				data:{idcorrelativoTelefono:$('#IdCorrelTelefono').val(),telefono:telef,agrega_edita_telefono:$('#hiddenAgregar_Editar_telefono').val()},
				contentType: "application/json; charset=utf-8",
				dataType:'json',
				success:function(result){
					//var eval = JSON.parse(result);

					$('#hiddenAgregar_Editar_telefono').val(eval);
					if(result==1 || result==0){
						ObtenerTodosTelefonos();
					}
					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("Error status code: " + xhr.status);
					alert("Error details: " + thrownError);
				}
			});
			e.preventDefault();
		}	
	});
	
	
	//function para agregar y modificar direccion
	$('.span_agregar_guarda_direccion').click(function(e) {		
		var direc=$('#txt_direccion').val()==""?"":$('#txt_direccion').val();
				
		if (direc==""){
			modal({
				type: 'alert',
				title: 'InformaciÛn',
				text: "Por favor Ingresa DirecciÛn!"  
			});	
			return ;
		}
		else if(direc !=""){
			
			$.ajax({
				url:'Agrega_Modifica_Direccion.action',
				type:'get',
				data:{idcorrelativoDireccion:$('#IdCorrelDireccion').val(),direccion:direc,agrega_edita_direccion:$('#hiddenAgregar_Editar_direccion').val()},
				contentType: "application/json; charset=utf-8",
				dataType:'json',
				success:function(result){
					//var eval = JSON.parse(result);

					$('#hiddenAgregar_Editar_direccion').val(eval);
					if(result==1 || result==0){
						ObtenerTodosDirecciones();
					}
					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("Error status code: " + xhr.status);
					alert("Error details: " + thrownError);
				}
			});
			e.preventDefault();
		}
	});
	
	//function para recupera direccion en session para modificar
	$(document).on("click",".RecuperarDireccion",function(e) {
		
		var idCorrelativoDireccion= $(this).data('id');
		$.ajax({
			url:'RecuperarDireccion.action',
			type : 'post',
			data : {idcorrelativoDireccion:idCorrelativoDireccion},
			dataType : 'json',
			success : function(result) {
				
				$("#hiddenAgregar_Editar_direccion").val(1);//Seteamos el flag a 1, significa que se modificara la direccion
				var data1=result;
				
				//hidden para recuperar el IdCorrelDireccion luego para el actualizar direccion
				$('#IdCorrelDireccion').val(data1.idcorrelativo);
				
				$("#frm_socio #txt_direccion").val(data1.direccion);
			}
		});
		e.preventDefault();
	});
	
	//Eliminar Direccion en Session
	$(document).on("click",".EliminarDireccion",function(e) {
		
		var idCorrelativoDireccion= $(this).data('id');
		var idSocio=$('#txt_cod_soc_guardar').val();
		$.ajax({
			url:'EliminarDireccion.action',
			type : 'get',
			data : {idcorrelativoDireccion:idCorrelativoDireccion,idsocio:idSocio},
			dataType : 'json',
			success : function(result) {
				
				if(result==1){
					ObtenerTodosDirecciones();
				}
			}
		});
		e.preventDefault();
	});
	
	
	//function para recupera telefono en session para modificar
	$(document).on("click",".RecuperarTelefono",function(e) {
		
		var idCorrelativoTelefono= $(this).data('id');
		$.ajax({
			url:'RecuperarTelefono.action',
			type : 'post',
			data : {idcorrelativoTelefono:idCorrelativoTelefono},
			dataType : 'json',
			success : function(result) {
				
				$("#hiddenAgregar_Editar_telefono").val(1);//Seteamos el flag a 1, significa que se modificara el telefono
				var data1=result;
				
				//hidden para recuperar el idCorrelativoTelefono luego para el actualizar telefono
				$('#IdCorrelTelefono').val(data1.idcorrelativo);
				
				$("#frm_socio #txt_telefono").val(data1.telefono);
			}
		});
		e.preventDefault();
	});
	
	//Eliminar Telefono en Session
	$(document).on("click",".EliminarTelefono",function(e) {
		
		var idCorrelativoTelefono= $(this).data('id');
		var idSocio=$('#txt_cod_soc_guardar').val();
		$.ajax({
			url:'EliminarTelefono.action',
			type : 'get',
			data : {idcorrelativoTelefono:idCorrelativoTelefono,idsocio:idSocio},
			dataType : 'json',
			success : function(result) {
				
				if(result==1){
					ObtenerTodosTelefonos();
				}
			}
		});
		e.preventDefault();
	});
	
	//Dar Baja Socio
	$(document).on("click",".DarBajaSocio",function(e) {
		
		$('#hiddencodsocio').val($(this).data('id'));
        $("#modalRemove").modal({
            keyboard: false
        });
	});
	
    $(".removeBtn").click(function (e) {
        $("#modalRemove").modal("hide");
        var idSocio = $('#hiddencodsocio').val();
		$.ajax({
			url:'DarBajasSocio.action',
			type : 'get',
			data : {idsocio:idSocio},
			dataType : 'json',
			success : function(result) {
				if(result==1){
					$("#paginador").html("");/*Limpiar los numero de paginacion*/
					initGrilla();	
				}
			}
		});

    });
	
	
	$('#btn_buscar').click(function(e){
		$("#paginador").html("");/*Limpiar los numero de paginacion*/
		$('#ModalLoading').modal('show');
		initGrilla();		
		$('#ModalLoading').modal('hide');
	});
	
	//Function para obtener los datos del socio y sus asociados para modificar sus datos
	$(document).on("click",".RecuperarSocio",function(e) {
		
		$('#hiddenInsert_UpdateSocio').val(1);/*Setear hiddenInsert_UpdateSocio a 1 Significar que modificara Socio*/
		
		var idSocio= $(this).data('id');
		$.ajax({
			url:'RecuperarSocio.action',
			type : 'post',
			data : {idsocio:idSocio},
			dataType : 'json',
			success : function(result) {
				var data=result;
				$('#tab1').prop( "disabled", true ).addClass('disabled');
				$('#tab2').prop( "disabled", false ).removeClass('disabled');
				$('#eventotab2primary').prop( "disabled", false ).removeClass('disabled');/*Quitamos el disabled del tab eventotab2primary*/
				$('.nav-tabs > .active').prop( "disabled", true ).addClass('disabled').next('li').find('a').trigger('click');
				
				$("#tab2primary #txt_cod_soc_guardar").val(data.idsocio);
				$("#tab2primary #txt_nombre_guardar").val(data.nombres);
				$("#tab2primary #txt_ape_paterno").val(data.apellido_paterno);
				$("#tab2primary #txt_ape_materno").val(data.apellido_materno);
				$("#tab2primary #txt_email").val(data.correo);
				$("#tab2primary #cboPais").val(data.idPais.idPais);
				$("#tab2primary #cboTipoDocumento").val(data.idtipoDocumento.idtipodocumento);
				data.documento==1?$(function() {
					$("#tab2primary #txt_dni").val(data.documento);
					$('#divdni').show();
					$('#hiddenValidaDocumento').val(0);
					$('#txt_dni').prop('disable',true);
				})
				:$(function() {
					$("#tab2primary #txt_pasaporte").val(data.documento);
					$('#divPasaporte').show();
					$('#divdni').hide();
					$('#hiddenValidaDocumento').val(1);
					$('#txt_pasaporte').prop("disabled",true);   
				});
				$("#tab2primary #txt_motivo_actualizacion").val(data.motivo_actualizacion);
				$("#tab2primary #cboDepartamento").val(data.idubigeo.iddepartamento.iddepartamento).change();
				$("#hiddenvalueprovincia").val(data.idubigeo.idprovincia.idprovincia);
				$("#hiddenvaluedistrito").val(data.idubigeo.iddistrito.iddistrito);
				programarCargaPronvincia();
				
				ListarTelefonos(data.telefonos);
				ListarDirecciones(data.direcciones);
				ListarPersona(data.lstpersonaAsociadas);
				
				var imgHuella=document.getElementById("txt_huella");
				imgHuella.src = "verHuella?idsocio="+data.idsocio;
				console.log(imgHuella.src);
				
				var imgFirma=document.getElementById("firm_socio_aux");
				imgFirma.src = "verFirma?idsocio="+data.idsocio;
				console.log(imgFirma.src);
				
				var imgFoto=document.getElementById("img");
				imgFoto.src = "verFoto?idsocio="+data.idsocio;
				
				//Ocultar Camara para no modificar
				$('#div_Foto').hide();
				
				//Ocultar el input type file y el canvas de fima y button de para limpiar canvas firma
				$('#txt_huella').hide();
				$('#canvas').hide();
				$('#color').hide();
				$('#bt-clear').hide();
				$('#txt_requisitos').hide();
				$('.bootstrap-filestyle').hide();
				
				$('#lblHUella').text(data.huellaFileName);
				$('#lblFirma').text("Fima Socio");
				$('#lblRequisitosAsociarse').text(data.requisito_asociarseFileName);
				
				var DescargarHuella=document.getElementById("btnDescargarHuella");
				DescargarHuella.href = "descargarHuella?idsocio="+data.idsocio;
				
				var DescargarReqAsociarse=document.getElementById("btnDescargarReqAsociarse");
				DescargarReqAsociarse.href = "descargarReqAsociarse?idsocio="+data.idsocio;
				
				/*Deshabilitar los input text y lo select que no se modificaran*/
				$('#txt_nombre_guardar, #txt_ape_paterno, #txt_ape_materno,'+ 
					'#txt_email, #cboPais, #cboTipoDocumento, #txt_numerodoc, #cboDepartamento,'+ 
					'#cboProvincia, #cboDistrito').prop('disabled',true);
				
				 /*mostrar button de descargas y sus label*/
				 $('#lblHUella').show();
				 $('#btnDescargarHuella').show();
				 //$('#lblFirma').show();
				 /*Mostrar Imagen Firma*/
				 $('#firm_socio_aux').show();
				 //$('#btnDescargarFirma').show();
				 $('#lblRequisitosAsociarse').show();
				 $('#btnDescargarReqAsociarse').show();
				 
				 /*Mostrar textarea de motivo de actualizar*/
				 $('#txt_motivo_actualizacion').show();

			}
		});
		e.preventDefault();
	});
	
});

//Cargar Combos Dependiente en Cascada Ubigeo
function CargarComboDependiente(cboFiltro, cbocascada, action) {
	
	cbocascada = document.getElementById(cbocascada);
	
	LimpiarCombo(cbocascada);
	/*if($("#cboDepartamento > option>[value='"+$('#cboDepartamento').val()!=""+"']").prop('selected',true)){
		//$("div.id_100 > select > option[value=" + value + "]").prop("selected",true);
		alert($('#cboDepartamento').val()!=""+ "entre");
		LimpiarComboDistrito('cboDistrito');
	}*/
	$.ajax({
		type : "post",
		url : action,
		data : {idvalue : cboFiltro},
		dataType : 'json',
		success : function(resultado) {				
			var data = resultado[0];				
			if (data.length > 0) {
				llenarCombo(data, cbocascada);
			}
		},
		error : function(xrh, ajaxOptions, thrownError) {
			alert("Error status code: " + xrh.status);
			alert("Error details: " + thrownError);
		}
	});
}

//Limpiar los Combos Dependientes
function LimpiarCombo(cbocascada) {
	while (cbocascada.length > 0) {
		cbocascada.remove(cbocascada.length - 1);
	}
}


//Limpiar Combo Distrito
/*function LimpiarComboDistrito(cbodistrito) {
	cbodistrito = document.getElementById(cbodistrito);
	while (cbodistrito.length > 0) {
		cbodistrito.remove(cbodistrito.length - 1);
	}
}*/

//function que comienza a llenar combos Dependientes
function llenarCombo(result, cbocascada) {
	if (cbocascada.id == 'cboProvincia') {
		cbocascada.options[0] = new Option('[Seleccione]', '');
		for ( var i = 0; i < result.length; i++) {
			cbocascada.options[cbocascada.length] = new Option(result[i].descripcion_provincia, result[i].idprovincia);
		}

	}
	if (cbocascada.id == 'cboDistrito') {
		cbocascada.options[0] = new Option('[Seleccione]', '');
		for ( var i = 0; i < result.length; i++) {
			cbocascada.options[cbocascada.length] = new Option(result[i].descripcion_distrito, result[i].iddistrito);
		}
	}
}


//function para ListarPersonaAsociada
function ObtenerPersonasAsociadas(){
	$.ajax({
		url:'listPersonaAsociada.action',
		type:'post',
		data:{},
		contentType: "application/json; charset=utf-8",
		dataType:'json',
		success:function(result){
			var resultado=result;
			
			ListarPersona(resultado);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
		
});
}

//funtion para Agregar y Modificar PersonaAsociada
function Add_UpdatePersonaAsociada(){
	alert($('#hiddenValidaDocumentoModal').val());
	if($('#hiddenAgregar_Editar').val()==0){
		//Agregar Persona Asociadas
	    $.ajax({
	        type: 'POST',
	        url:$('#frm_AgregaAsociado').attr('action'),
	        data:$('#frm_AgregaAsociado').serialize(),
	        dataType: 'json',
	        success: function(data){
	        	$('#myModal').modal('hide');
	        	if(data==1){
	        		$('#txtDni_modal').attr('name', 'txtDni_modal');
	        		$('#txtPasaporte_modal').attr('name', 'txtPasaporte_modal');
	        		
	        		$('#divdniModal').hide();
	        		$('#divpasaporteModal').hide();
	        		$('#divdniModal').show();
	        		$('#txtDni_modal').prop("disabled",true);
	        		
		            ObtenerPersonasAsociadas();
	        	}
	            
	        },
			error : function(xhr, ajaxOptions, thrownError) {
				alert("Error status code: " + xhr.status);
				alert("Error details: " + thrownError);
			}
	    });

	    return false;
	}else if($('#hiddenAgregar_Editar').val()==1){
		//Actualizar Persona Asociadas
		
		var idpersonaAsociada=$('#IdCorrelativoPerAsoc').val();
		//$('#ModalLoading').modal('show');
	    $.ajax({
	        type: 'POST',
	        url:'ActualizarPersonaAsociada.action',
	        data:$('#frm_AgregaAsociado').serialize()+'&idpersonaAsociada='+idpersonaAsociada,
	        dataType: 'json',
	        success: function(data){
	        	//$('#ModalLoading').modal('hide');
	        	$("#hiddenAgregar_Editar").val(0);//Seteamos el flag a 0, significa que agergara PersonaAsociada
	            console.log(eval);
	            $('#myModal').modal('hide');
	            if(data==1){
	            	ObtenerPersonasAsociadas();
	            }
	            
	        },
			error : function(xhr, ajaxOptions, thrownError) {
				alert("Error status code: " + xhr.status);
				alert("Error details: " + thrownError);
			}
	    });

	    return false;
	}
}

function ListarPersona(data){
	$('#rellenarAsociados').html('');
	var trHTML = '';
	if (data.length > 0){
		for ( var i = 0; i < data.length; i++) {
			
			trHTML += '<tr><td>'
				+ data[i]['nombres']
				+ '</td><td>'
				+ data[i]['apellido_paterno']
				+ '</td><td>'
				+ data[i]['apellido_materno']
				+ '</td><td>'
				+ data[i]['documento']
				+ '</td><td>'
				+ data[i]['tipo_documento']['descripcion']
				+ '</td><td>'
				+ data[i]['parentesco']['descripcion']
				+ '</td><td><a data-id="'+data[i]['idCorrelativo']+'" class="RecuperarPersonaAsociada btn btn-info"><span class="glyphicon glyphicon-edit"></span> </a></td>'
				+ '</td><td><a  data-id="'+data[i]['idCorrelativo']+'" class="EliminarPersonaAsociada btn btn-danger"><span class="glyphicon glyphicon-trash" ></span></a></td></tr>';
		}
		$('#rellenarAsociados').append(trHTML);
	}
	$('#dataTables_example_length').css('display','none');
	$('#dataTables_example_filter').css('display','none');
}

//Activar Camare
function CargarCamara() {
	var varHidden = document.getElementById("idHidden");
	var video = document.querySelector('#v'), 
		canvas = document.querySelector('#c'), 
		btn = document.querySelector('#t'), 
		img = document.querySelector('#img');
	
	navigator.getUserMedia = (navigator.getUserMedia
			|| navigator.webkitGetUserMedia
			|| navigator.mozGetUserMedia || navigator.msGetUserMedia);
	
	if (navigator.getUserMedia) {
		navigator.getUserMedia({
			video : true
		}, function(stream) {
			video.src = window.URL.createObjectURL(stream);
			video.play();
		}, function(e) {
			console.log(e);
		});
	}else{
		alert('Tienes un navegador obsoleto');
	}
	
	video.addEventListener('loadedmetadata', function() {
		canvas.width = video.videoWidth;
		canvas.height = video.videoHeight;
	}, false);
	
	btn.addEventListener('click', function() {

		canvas.getContext('2d').drawImage(video, 0, 0);
		var imgData = canvas.toDataURL('image/png');
		img.setAttribute('src', imgData);
		varHidden.setAttribute('value', imgData);
	});
	
}

//Var para definir la pagination de la grilla desde la BD
var paginador;
var totalPaginas;
var itemsPorPagina = 3;
var numerosPorPagina = 10;

/* Se ejecuta cuando carga por primera vez la pagina */
$(window).load(function() {
	
	//Verificar si Panel Consultar Socio esta Checked
	$('#tab1:checked')?initGrilla():

	
	//Listar Telefonos, Direccion si hay data
	ObtenerTodosDirecciones();
	ObtenerTodosTelefonos();
	
	/*Desabilitamos el tab eventotab2primary*/
	$('#eventotab2primary').click(function(event){
	        if ($(this).hasClass('disabled')) {
	            return false;
	        }
	 });
	
	//CargaCombos TipoDocumento y Pais
	$.ajax({
		url : 'ProyectoPand/cargaCombos.action',
		type : 'post',
		data : '',
		contentType: "application/json; charset=utf-8",
		dataType : 'json',
		
		success : function(result) {
			var data1=result[0], data2=result[1],data3=result[2];
			
			Combo1=document.getElementById('cboTipoDocumento');
			Combo1.options[0]= new Option('[Seleccione]','');
			for(var i=0;i<data1.length;i++){
				Combo1.options[Combo1.length]= new Option(data1[i].descripcion,data1[i].idtipodocumento);
			}
			
			Combo3=document.getElementById('cboTipDocModal');
			Combo3.options[0]= new Option('[Seleccione]','');
			for(var i=0;i<data1.length;i++){
				Combo3.options[Combo3.length]= new Option(data1[i].descripcion,data1[i].idtipodocumento);
			}
			
			Combo2=document.getElementById('cboPais');
			Combo2.options[0]= new Option('[Seleccione]','');
			for(var i=0;i<data2.length;i++){
				Combo2.options[Combo2.length]= new Option(data2[i].descripcion,data2[i].idPais);
			}
			
			Combo4=document.getElementById('cboParentesco');
			Combo4.options[0]= new Option('[Seleccione]','');
			for(var i=0;i<data3.length;i++){
				Combo4.options[Combo4.length]= new Option(data3[i].descripcion,data3[i].idparentesco);
			}
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
	});
	
	//CargaCombo Ubigeo
	$.ajax({
		url : 'ProyectoPand/cargaDepartamento.action',
		type : 'post',
		data : '',
		dataType : 'json',
		success : function(result) {
			var data1=result[0];
			Combo1=document.getElementById('cboDepartamento');
			Combo1.options[0]= new Option('[Seleccione]','');
			for(var i=0;i<data1.length;i++){
				Combo1.options[Combo1.length]= new Option(data1[i].descripcion_departamento,data1[i].iddepartamento);
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
	});
});


//function para ObtenerTodosTelefonos para Listar
function ObtenerTodosTelefonos(){
	$.ajax({
		url:'listTelefonos.action',
		type:'POST',
		data:{},
		contentType: "application/json; charset=utf-8",
		dataType:'json',
		success:function(result){
			var resultado=result;
	
			ListarTelefonos(resultado);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
		
});
}

//function para ListarTelefonos
function ListarTelefonos(data){
	$('#rellenarTelefonos').html('');
	var trHTML = '';
	if (data.length > 0){
		for ( var i = 0; i < data.length; i++) {
			
			trHTML += '<tr><td>'
				+ data[i]['telefono']
				+ '</td><td><a data-id="'+data[i]['idcorrelativo']+'" class="RecuperarTelefono btn btn-info"><span class="glyphicon glyphicon-edit"></span> </a></td>'
				+ '</td><td><a  data-id="'+data[i]['idcorrelativo']+'" class="EliminarTelefono btn btn-danger"><span class="glyphicon glyphicon-trash" ></span></a></td></tr>';
		}
		$('#rellenarTelefonos').append(trHTML);
	}
	$('#dataTables_telefonos_length').css('display','none');
	$('#dataTables_telefonos_filter').css('display','none');
}


//function para ObtenerTodosDirecciones para Listar
function ObtenerTodosDirecciones(){
	$.ajax({
		url:'listDirecciones.action',
		type:'POST',
		data:{},
		dataType:'json',
		cache :  false , 
		success:function(result){
	
			ListarDirecciones(result);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
		
});
}

//function para ListarDirecciones
function ListarDirecciones(data){
	$('#rellenardirecciones').html('');
	var trHTML = '';
	if (data.length > 0){
		for ( var i = 0; i < data.length; i++) {
			
			trHTML += '<tr><td>'
				+ data[i]['direccion']
				+ '</td><td><a data-id="'+data[i]['idcorrelativo']+'" class="RecuperarDireccion btn btn-info"><span class="glyphicon glyphicon-edit"></span> </a></td>'
				+ '</td><td><a  data-id="'+data[i]['idcorrelativo']+'" class="EliminarDireccion btn btn-danger"><span class="glyphicon glyphicon-trash" ></span></a></td></tr>';
		}
		$('#rellenardirecciones').append(trHTML);
	}
	$('#dataTables_direcciones_length').css('display','none');
	$('#dataTables_direcciones_filter').css('display','none');
}



//ListarSocio
/*function ListarSocio(){
	var cod=$('#txt_codigo_buscar').val()==""?"":$('#txt_codigo_buscar').val();
	var soc=$('#txt_nombre_apellido_buscar').val()==""?"":$('#txt_nombre_apellido_buscar').val();
	var doc=$('#txt_dni_buscar').val()==""?"":$('#txt_dni_buscar').val();
	$.ajax({
		url:'ListarSocios.action',
		type:'get',
		data:{idsocio:cod,nombres:soc,documento:doc},
		contentType: "application/json; charset=utf-8",
		dataType:'json',
		success:function(result){
			
			CargarGrilla(resultado);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
	});
}*/

//Trae TotalRegistroSocio desde a BD para la paginacion
function initGrilla(){
	var cod=$('#txt_codigo_buscar').val()==""?"":$('#txt_codigo_buscar').val();
	var soc=$('#txt_nombre_apellido_buscar').val()==""?"":$('#txt_nombre_apellido_buscar').val();
	var doc=$('#txt_dni_buscar').val()==""?"":$('#txt_dni_buscar').val();
	$.ajax({
		url : 'TotalRegistrosSocio.action',
		type:'get',
		data:{idsocio:cod,nombres:soc,documento:doc},
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
	$('<li><a href="#" class="prev_link">´</a></li>').appendTo(paginador);
	
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
	$('<li><a href="#" class="next_link">ª</a></li>').appendTo(paginador);
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
	var soc=$('#txt_nombre_apellido_buscar').val()==""?"":$('#txt_nombre_apellido_buscar').val();
	var doc=$('#txt_dni_buscar').val()==""?"":$('#txt_dni_buscar').val();
	$.ajax({
		url : 'ListarSocios.action',
		type : 'get',
		data : {idsocio:cod,nombres:soc,documento:doc,"paginador.limit":itemsPorPagina,"paginador.offset":desde},
		dataType : 'json',
		success : function(result) {
			var lista=result;
			$("#rellenar").html("");
			var trHTML = '';
			if (lista.length > 0){ 
				for ( var i = 0; i < lista.length; i++) {
					trHTML += '<tr><td>'
						+ lista[i]['idsocio']
						+ '</td><td>'
						+ lista[i]['nombres']	
						+ '</td><td>'
						+ lista[i]['documento']
						+ '</td><td>'
						+ lista[i]['idusuario']['login']	
						+ '</td><td>'
						+ lista[i]['idusuario']['password']	
						+ '</td><td>'
						
						+'<img src="verFoto?idsocio='+lista[i]['idsocio']+'" class="img-rounded img-circle center-block" alt="Cinque Terre" width="60" height="60">' 	
						+ '</td><td><a href="" data-id="'+lista[i]['idsocio']+'" class="RecuperarSocio btn btn-info"><span class="fa fa-pencil-square-o "></span></a></td>'+
						'<td><a  data-id="'+lista[i]['idsocio']+'" class="DarBajaSocio btn btn-danger"><span class="fa fa-trash-o" ></span></a></td></tr>';
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

/*Metodo para setar los combo de ubigeo*/

//CargaProvincia antes de setear
function programarCargaPronvincia(){	
	    setTimeout(function(){setearProvincia();},1000); // 1000ms = 1s
	}
//setea provincia
function setearProvincia(){
	$("#tab2primary #cboProvincia").val($("#hiddenvalueprovincia").val());
	CargarComboDependiente($("#hiddenvalueprovincia").val(), 'cboDistrito','ProyectoPand/cargaDistrito.action');
	programarDistrito();
}
//CargaDistrito antes de setear
function programarDistrito(){	
    setTimeout(function(){setearDistrito();},1000); // 1000ms = 1s
}
//setea distrito
function setearDistrito(){
	$("#tab2primary #cboDistrito").val($("#hiddenvaluedistrito").val());
}

/*variables para la firma*/
var lastX, lastY;
var ctx;
var canvas;

var tiemposcambian = tiemposcambian || {};
tiemposcambian.GuardandoPNGs = (function() {
	var mousePressed = false;

	  function init() {
		// init canvas
		  canvas = document.getElementById('canvas');
		  ctx = canvas.getContext('2d');
		  resetCanvas();
		  
		  // button events
		  //document.getElementById('bt-clear').onmouseup = resetCanvas;
		// canvas events
	    canvas.onmousedown = function(e) {
	        draw(e.layerX, e.layerY);
	        mousePressed = true;
	      };
      canvas.onmousemove = function(e) {
          if (mousePressed) {
            draw(e.layerX, e.layerY);
          }
        };
	      
    canvas.onmouseup = function(e) {
        mousePressed = false;
      };
          
      canvas.onmouseleave = function(e) {
          mousePressed = false;
        };
	  }
	  
	  function draw(x, y) {
		    if (mousePressed) {
		      ctx.beginPath();
		      ctx.strokeStyle = document.getElementById('color').value;
		      ctx.lineWidth = 1;
		      ctx.lineJoin = 'round';
		      ctx.moveTo(lastX, lastY);
		      ctx.lineTo(x, y);
		      ctx.closePath();
		      ctx.stroke();
		    }
		    lastX = x; lastY = y;
		  }
	  
	  return {
		    'init': init
		  };
});

function resetCanvas() {
    // just repaint canvas white
    ctx.fillStyle = '#EEEEEE';
    ctx.fillRect(0, 0, canvas.width, canvas.height);
  }
