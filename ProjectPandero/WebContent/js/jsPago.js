$(document).ready(function(e){
	
	var f = new Date()
	$("#txtFechaAtual").val(f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear());
	var f = new Date()
	$("#txtFechaAtual_1").val(f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear());
	
	$('#btnViewModalPago_1').click(function(e) {
		
		var dni_Socio = $('#dniSocio_1').val();
		if(dni_Socio==""){
			alert("Ingrese DNI de socio")
		}
		
		$.ajax({
			url:'ObtenerObligacion_1.action',
			type : 'post',
			data : {dni_Socio_1:dni_Socio},
			dataType : 'json',
			success : function(result) {
				
				var objeto=result;
				
				$("#myModal_2").modal({
					keyboard : false,
					backdrop : 'static'

				});
				
				$(".modal-body #txt_socio_1").val(objeto.socio.nombres+" "+objeto.socio.apellido_paterno+" "+ objeto.socio.apellido_materno);
				$(".modal-body #txt_id_obligacion_1").val(objeto.idobligaciones);
				$(".modal-body #txt_descripcion_1").val(objeto.descripcion);
				//$(".modal-body #txt_monto_1").val(objeto.montoTotal);
				
				$("#txt_socio_1").val(objeto.socio.nombres+" "+objeto.socio.apellido_paterno+" "+ objeto.socio.apellido_materno);
				$("#txt_idObligacion_1").val(objeto.idobligaciones); //Imprime el el type "text"
				$("#txt_obligacion_1").val(objeto.idobligaciones);//Imprime el el type "hidden"
				$("#txt_descripcion_1").val(objeto.descripcion);
				
				var f = new Date()
				$("#txtFechaAtual_1").val(f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear());//(text)
				ObtenerPagosAnteriores();
			
			}//Cierra el success
		});	//Cierra el ajax
		e.preventDefault();
		
	});//Cierra la funcion "#btnViewModalPago"
	
	$('#btn_enviar_1').click(function(e){
		RegistrarSocio_1();
	});
	$('#btn_enviar').click(function(e){
		RegistrarSocio();
	});
	
	$('#btnViewModalPago').click(function(e) {
		
		var dni_Socio = $('#dniSocio').val();
		$('#rellenarDetalleFactura').html('');
		
		if(dni_Socio==""){
			alert("Ingrese DNI de socio")
		}
		
		$.ajax({
			url:'ObtenerObligacion.action',
			type : 'post',
			data : {dni_Socio:dni_Socio},
			dataType : 'json',
			success : function(result) {
				//console.log(result);
				var objeto=result;
				
				if(objeto.estado.idestado==3){
					alert("La obligacion ya esta pagada");
				}
				else{
				$("#myModal").modal({
					keyboard : false,
					backdrop : 'static'

				});
					$(".modal-body #txt_socio").val(objeto.socio.nombres+" "+objeto.socio.apellido_paterno+" "+ objeto.socio.apellido_materno);
					$(".modal-body #txt_id_obligacion").val(objeto.idobligaciones);
					$(".modal-body #txt_descripcion").val(objeto.descripcion);
					$(".modal-body #txt_monto").val(objeto.montoTotal);
					$(".modal-body #txt_cuotas").val(objeto.cuotas);
					$(".modal-body #txt_vencimiento").val(objeto.fecha_vencimiento);
					
					$("#txt_socio").val(objeto.socio.nombres+" "+objeto.socio.apellido_paterno+" "+ objeto.socio.apellido_materno);
					$("#txt_idObligacion").val(objeto.idobligaciones); //Imprime el el type "text"
					$("#txt_obligacion").val(objeto.idobligaciones);//Imprime el el type "hidden"
					$("#txt_descripcion").val(objeto.descripcion);
					
										
					//Vuelve a imprimir en el type "text" y "hidden" la fecha actual del sistema
					var f = new Date()
					$("#txtFechaAtual").val(f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear());//(text)
					$("#txt_fecha_actual").val(f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear());//(hidden)
					//Llama a un metodo para traer las cuotas
					ObtenerCuotas();
			
				}
			}//Cierra el success
		});	//Cierra el ajax
		e.preventDefault();
		
	});//Cierra la funcion "#btnViewModalPago"
	
}); //Cierra "$(document).ready(function(e)"		

/*RegistrarSocio*/
function RegistrarSocio(){
	
	if($("#txt_idObligacion").val()==""){
		alert("Seleccione consultar para encontrar datos del socio");
	}
	
	else{
		$.ajax({
			type : "post",
			url : $('#formPago').attr('action'),
			data : file,
	        contentType: false,
	        processData: false,
			dataType : 'json',
			success : function(resultado) {
				
				//alert("Pago realizado exitosamente");
		
			},
			error : function(xrh, ajaxOptions, thrownError) {
				alert("Error status code: " + xrh.status);
				alert("Error details: " + thrownError);
			}
		});
	}
}

/*RegistrarSocio*/
function RegistrarSocio_1(){
	
	if($("#txt_idObligacion_1").val()==""){
		alert("Seleccione consultar para encontrar datos del socio");
	}
	
	else{
		$.ajax({
			type : "post",
			url : $('#formPago_1').attr('action'),
			data : file,
	        contentType: false,
	        processData: false,
			dataType : 'json',
			success : function(resultado) {
				
				//alert("Pago realizado exitosamente");
		
			},
			error : function(xrh, ajaxOptions, thrownError) {
				alert("Error status code: " + xrh.status);
				alert("Error details: " + thrownError);
			}
		});
	}
}

function ObtenerPagosAnteriores(){
	var dni_Socio = $('#dniSocio_1').val();
	$.ajax({
		url:'ObtenerPagosAnteriores.action',
		type:'post',
		data:{dni_Socio_1:dni_Socio},
		dataType:'json',
		success:function(result){
			console.log(result);
			var resultado=result;
			ListarPagosAnteriores(resultado);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
		
});
}

function ListarPagosAnteriores(data){
	
	$('#rellenarPagosAnteriores').html('');
	var trHTML = '';
	var monto=0;
	if (data.length > 0){
		for ( var i = 0; i < data.length; i++) {
			
			trHTML += '<tr><td>'
				+ data[i]['idfactura']
				+ '</td><td>'
				+ parseFloat(data[i]['Total'])
				+ '</td><td>'
				+ data[i]['fecha_registro']
				+ '</td>'
			
				monto+=parseFloat(data[i]['Total'])
		}
		$("#txt_monto_1").val(monto);
		$('#rellenarPagosAnteriores').append(trHTML);
	}
}

function ObtenerCuotas(){
	var dni_Socio = $('#dniSocio').val();
	$.ajax({
		url:'cuotasDeObligacion.action',
		type:'post',
		data:{dni_Socio:dni_Socio},
		dataType:'json',
		success:function(result){
			console.log(result);
			var resultado=result;
			ListarCuota(resultado);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
		
});
}

function ListarCuota(data){
	
	$('#rellenarCuotas').html('');
	var trHTML = '';
	if (data.length > 0){
		for ( var i = 0; i < data.length; i++) {
			
			trHTML += '<tr><td>'
				+ data[i]['idcuota']
				+ '</td><td>'
				+ data[i]['monto']
				+ '</td><td>'
				+ data[i]['fechaPago']
				+ '</td><td>'
				+ parseFloat(data[i]['mora']['mora']).toFixed(2)
				+ '</td><td>'
				+ data[i]['estado']['descripcion']
				+ '</td>';
			if(i==0){
				//trHTML +='<td class="check"><input name="" type="checkbox"></td></tr>';
				trHTML +='<td><input onClick="ObtenerCuotasSeleccionadas_check(this)" type="checkbox" id="'+data[i]['idcuota']+'" name="seleccione" value="'+data[i]['idcuota']+'"/></td></tr>';
			}else{
				trHTML +='<td><input onClick="ObtenerCuotasSeleccionadas_check(this)" type="checkbox" id="'+data[i]['idcuota']+'" name="seleccione" value="'+data[i]['idcuota']+'"/></td></tr>';
			}
			

		}
		$('#rellenarCuotas').append(trHTML);
	}
}

//XD

/*function seleccionaTR(elemento) {
	while (elemento.tagName != "TR") elemento = elemento.parentNode;
	elemento.style.backgroundColor = "blue";
	}

$('.cuot_select:checked').each(
	    function() {
	        alert("El checkbox con valor " + $(this).val() + " está seleccionado");
	    }
);*/

function ObtenerCuotasSeleccionadas_check(element){
	var id=$(element).val();
	
	$.ajax({
		url:'ObtenerCuotasSeleccionadas_check.action',
		type:'get',
		data:{"bean.idcuota":id},
		dataType:'json',
		success:function(result){
			ListarDetalleFactura(result);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
		
	});
}

function ListarDetalleFactura(data){
	
	$('#rellenarDetalleFactura').html('');
	var trHTML = '';
	var total=0;
	if (data.length > 0){
		for ( var i = 0; i < data.length; i++) {
			
			trHTML += '<tr><td>'
				+ data[i]['idcuota']
				+ '</td><td>'
				+ data[i]['monto']
				+ '</td><td>'
				+ data[i]['fecha_vencimiento']
				+ '</td><td>'
				+ parseFloat(data[i]['mora']).toFixed(2)
				+ '</td><td>'
				+ (parseFloat(data[i]['mora'])+parseFloat(data[i]['monto'])).toFixed(2)
				+ '</td></tr>';	
			
			total+=parseFloat(data[i]['mora'])+parseFloat(data[i]['monto'])
			
			//$("#txt_mora").val(parseFloat(data[i]['mora']['mora']).toFixed(2));
			//$("#txt_vencimiento").val(data[i]['fecha_vencimiento']);
			//$("#txt_cuota").val(data[i]['idcuota']);
			//$("#txt_montoPagado").val((parseFloat(data[i]['mora'])+parseFloat(data[i]['monto'])).toFixed(2));
		}
		$("#txt_Total").val(total.toFixed(2));
		$("#txt_montoPagado").val(total.toFixed(2));
		$('#rellenarDetalleFactura').append(trHTML);
		
	}
	
}