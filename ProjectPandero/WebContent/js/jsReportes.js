$(window).load(function() {
		
	$.ajax({
		url : 'cargaPais.action',
		type : 'post',
		data : '',
		dataType : 'json',
		success : function(result) {
			var data1=result;
			console.log(data1);
			var Combo1=document.getElementById('cboPais');
			Combo1.options[0]= new Option('[Seleccione]','');
			for(var i=0;i<data1.length;i++){
				Combo1.options[Combo1.length]= new Option(data1[i].descripcion,data1[i].idPais);
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
	});
});


$(window).load(function() {
	
	$.ajax({
		url : 'cargaTipoObligacion.action',
		type : 'post',
		data : '',
		dataType : 'json',
		success : function(result) {
			var data1=result;
			console.log(data1);
			var Combo1=document.getElementById('cboTipoObligacion');
			Combo1.options[0]= new Option('[Seleccione]','');
			for(var i=0;i<data1.length;i++){
				Combo1.options[Combo1.length]= new Option(data1[i].descripcion,data1[i].idtipo_obligacion);
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error status code: " + xhr.status);
			alert("Error details: " + thrownError);
		}
	});
});