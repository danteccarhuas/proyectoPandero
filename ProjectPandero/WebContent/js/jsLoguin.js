$(document).ready(function(e){
	

	
	/* Valida las etiquedas del formulario */
	$("#frmLogin").bootstrapValidator({
		message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		"login" : {
			validators : {
				notEmpty : {
					message : 'Ingrese Usuario por favor.'
				}
			}
		},
		"password" : {
			validators : {
				notEmpty : {
					message : 'Ingrese Password por favor.'
				}
			}
		}		
	}
});
	
	//Loguearse
	/*$('#idLoginSubmit').click(function(e){
		//e.preventDefault();
		$.ajax({
	        type: 'POST',
	        url:$('#frmLogin').attr('action'),
	        data:{login:$('#txt_usuario').val(),password:$('#txt_password').val()},
	        dataType: 'json',
	        success: function(data){
	        	console.log(data);
	            /*if(eval==1){
	            	ObtenerPersonasAsociadas();
	            }
	            
	        },
			error : function(xhr, ajaxOptions, thrownError) {
				alert("Error status code: " + xhr.status);
				alert("Error details: " + thrownError);
			}
	    });
		e.preventDefault();
	});	*/
});