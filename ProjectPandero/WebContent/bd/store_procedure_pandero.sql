drop procedure if exists usp_Ins_Socio;
DELIMITER //

CREATE PROCEDURE usp_Ins_Socio( 
IN  p_nombres VARCHAR(15), 
IN  p_apellido_paterno VARCHAR(45),
IN 	p_apellido_materno VARCHAR(45),
IN	p_documento varchar(45),
IN	p_correo varchar(45),
In	p_fotoBytes longblob,
In	p_huellaBytes longblob,
IN	p_huellaFileName VARCHAR(200),
IN	p_huellaContentType VARCHAR(45),
In	p_firmaBytes longblob,
IN	p_requisito_asociarseBytes longblob,
IN	p_requisito_asociarseFileName VARCHAR(200),
IN	p_requisito_asociarseContentType VARCHAR(45),
IN	p_idtipoDocumento int(11),
IN	p_idPais int(11),
IN	p_iddepartamento int(11),
In	p_departamento varchar(45),
IN	p_idprovincia int(11),
In	p_provincia varchar(45),
IN	p_iddistrito int(11),
In	p_distrito varchar(45),
IN	p_idtrabajador varchar(25),
IN	p_login varchar(25),
IN	p_password varchar(45),
out p_idsocio VARCHAR(45)
     )
BEGIN
	 declare v_idusuario int(11);
	 declare v_idsocio varchar(25);
	 declare v_idubigeo int(11);
	 set v_idusuario=0;
	 set v_idsocio='';
	 set v_idubigeo=0;
	
	insert into usuario values
	(null,p_login,p_password);

	select max(idusuario) into v_idusuario
	from usuario;
	
	insert into usuario_rol values
	(v_idusuario,3);

	insert into ubigeo values
	(null,p_iddepartamento,p_departamento,p_idprovincia,p_provincia,p_iddistrito,p_distrito);
	
	select max(idubigeo) into v_idubigeo
	from ubigeo;
	
	select  concat('Soc',CAST(RIGHT(CONCAT('0000' , CAST(RTRIM(CAST(RIGHT(IFNULL(MAX(idsocio),0),4) 
	as signed integer)+1)AS CHAR(1000) CHARACTER SET utf8) ),4)AS CHAR(1000) CHARACTER SET utf8)) into v_idsocio
	from socio;

    INSERT INTO socio
         (
		idsocio,
		nombres,
		apellido_paterno,
		apellido_materno,
		documento,
		correo,
		fotoBytes,
		huellaBytes,
		firmaBytes,
		requisito_asociarseBytes,
		codigo_barra,
		codigo_qr,
		idestado,
		idusuario,
		idtipoDocumento,
		idPais,
		idubigeo,
		idtrabajador,
		motivo_actualizacion,
		huellaFileName,
		huellaContentType,
		requisito_asociarseFileName,
		requisito_asociarseContentType
         )
    VALUES 
         (
		v_idsocio, 
		p_nombres,
		p_apellido_paterno,
		p_apellido_materno,	
		p_documento,
		p_correo,
		p_fotoBytes,
		p_huellaBytes,
		p_firmaBytes,
		p_requisito_asociarseBytes,
		'',
		'',
		1,
		v_idusuario,	
		p_idtipoDocumento,
		p_idPais,
		v_idubigeo,
		p_idtrabajador,
		'',
		p_huellaFileName,
		p_huellaContentType,
		p_requisito_asociarseFileName,
		p_requisito_asociarseContentType
         ); 
	
	set p_idsocio=v_idsocio;
END //
DELIMITER ;


drop procedure if exists usp_Ins_Telefonos;
DELIMITER //

create procedure usp_Ins_Telefonos(
IN  p_telefono VARCHAR(15), 
IN  p_idsocio VARCHAR(25)
)
BEGIN
	    INSERT INTO telefono_socio
         (
		idtelefono_socio,
		telefono,
		idsocio
         )
    VALUES 
         (
		null,
		p_telefono, 
		p_idsocio
         ); 

END //
DELIMITER ;


drop procedure if exists usp_Ins_Direcciones;
DELIMITER //

create procedure usp_Ins_Direcciones(
IN  p_direccion VARCHAR(15), 
IN  p_idsocio VARCHAR(25)
)
BEGIN
	    INSERT INTO direccion_socio
         (
		iddireccion_socio,
		direccion,
		idsocio
         )
    VALUES 
         (
		null,
		p_direccion, 
		p_idsocio
         ); 

END //
DELIMITER ;


drop procedure if exists usp_Ins_Asociado;
DELIMITER //

CREATE PROCEDURE usp_Ins_Asociado( 
IN  p_nombres VARCHAR(15), 
IN  p_apellido_paterno VARCHAR(45),
IN 	p_apellido_materno VARCHAR(45),
IN	p_documento varchar(45),
IN	p_idtipoDocumento int(11),
IN	p_idparentesco int(11),
In	p_idsocio VARCHAR(25)
     )
BEGIN
	 declare v_idpersonaAsociada varchar(25);
	 set v_idpersonaAsociada='';
	
	select  concat('Asoc',CAST(RIGHT(CONCAT('0000' , CAST(RTRIM(CAST(RIGHT(IFNULL(MAX(idpersonaAsociada),0),4) 
	as signed integer)+1)AS CHAR(1000) CHARACTER SET utf8) ),4)AS CHAR(1000) CHARACTER SET utf8)) into v_idpersonaAsociada
	from personaasociada;

    INSERT INTO personaasociada
         (
		idpersonaAsociada,
		nombres,
		apellido_paterno,
		apellido_materno,
		documento,
		idtipoDocumento,
		idparentesco,
		idsocio
         )
    VALUES 
         (
		v_idpersonaAsociada, 
		p_nombres,
		p_apellido_paterno,
		p_apellido_materno,	
		p_documento,	
		p_idtipoDocumento,
		p_idparentesco,
		p_idsocio
         ); 
END //
DELIMITER ;


drop procedure if exists usp_UpdateCodeBarra_CodeQR;
DELIMITER //
CREATE PROCEDURE usp_UpdateCodeBarra_CodeQR(
in	p_idsocio varchar(25),
IN  p_codigo_barra longblob,
in	p_codigo_qr longblob
     )
BEGIN 

	UPDATE socio 
	SET codigo_barra=p_codigo_barra,
		codigo_qr=p_codigo_qr
	 WHERE 
		idsocio = p_idsocio;
END //
DELIMITER ;


drop procedure if exists usp_Cons_DatosSocio;
DELIMITER //
CREATE  PROCEDURE usp_Cons_DatosSocio(
IN	p_idsocio varchar(25),
IN	p_socio varchar(45),
IN	p_documento varchar(45),
IN	p_limit int,
IN	p_desde int

)
BEGIN 
	PREPARE STMT FROM  "
   select	s.idsocio, 
			concat(s.nombres,' ',s.apellido_paterno,' ',s.apellido_materno) socio,
			s.documento,
			u.login,
			u.password
	from socio s inner join usuario u
	on s.idusuario=u.idusuario
	where (s.idsocio =? OR '' = ?) and 
		(CONCAT(s.nombres,s.apellido_paterno,s.apellido_materno ) like CONCAT(""%"",?,""%"") 
		or ''=CONCAT(""%"",?,""%""))
		and (s.documento=? or ''=?)
		and s.idestado = 1 order by s.idsocio asc limit ? offset ?";
	
	SET @p_idsocio = p_idsocio; 
	SET @p_socio = p_socio; 
	SET @p_documento = p_documento; 
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 


	EXECUTE STMT USING @p_idsocio,@p_idsocio,@p_socio,@p_socio,@p_documento,@p_documento,
						@p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;

drop procedure if exists usp_TotaRegist_Socio;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_Socio(
IN	p_idsocio varchar(25),
IN	p_socio varchar(45),
IN	p_documento varchar(45),
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select count(s.idsocio) into v_TOTALREGISTRO
	from socio s
	where 
		(s.idsocio =p_idsocio OR '' = p_idsocio) and 
		(CONCAT(s.nombres,' ',s.apellido_paterno,' ',s.apellido_materno ) like CONCAT("%",p_socio,"%") 
		or ''=p_socio)
		and (s.documento=p_documento or ''=p_documento)
		and idestado=1;


	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;

drop procedure if exists usp_obt_DatosSocio;
DELIMITER //

CREATE PROCEDURE usp_obt_DatosSocio(
IN p_idsocio varchar(25)
     )
BEGIN 

   select s.idsocio,s.nombres,s.apellido_paterno,s.apellido_materno,s.correo,
		s.idPais,s.idtipoDocumento,s.documento,u.iddepartamento,u.idprovincia,u.iddistrito,
		s.huellaFileName,s.requisito_asociarseFileName,s.motivo_actualizacion
    from socio s inner join ubigeo u
	on s.idubigeo=u.idubigeo inner join tipodocumento td
	on s.idtipoDocumento=td.idtipoDocumento
	where s.idsocio=p_idsocio
	and s.idestado=1
	order by s.idsocio;
END //
DELIMITER ;


drop procedure if exists usp_obt_TelefonosSocio;
DELIMITER //

CREATE PROCEDURE usp_obt_TelefonosSocio(
IN p_idsocio varchar(25)
     )
BEGIN 

   select t.idtelefono_socio,t.telefono
    from telefono_socio t inner join socio s
	on s.idsocio=t.idsocio
	where t.idsocio=p_idsocio
	order by t.idtelefono_socio;
END //
DELIMITER ;


drop procedure if exists usp_obt_DireccionesSocio;
DELIMITER //

CREATE PROCEDURE usp_obt_DireccionesSocio(
IN p_idsocio varchar(25)
     )
BEGIN 

   select d.iddireccion_socio,d.direccion
    from direccion_socio d inner join socio s
	on s.idsocio=d.idsocio
	where d.idsocio=p_idsocio
	order by d.iddireccion_socio;
END //
DELIMITER ;


drop procedure if exists usp_obt_PersonaAsociadas;
DELIMITER //

CREATE PROCEDURE usp_obt_PersonaAsociadas(
IN p_idsocio varchar(25)
     )
BEGIN 

   select asoc.idpersonaAsociada,asoc.nombres,asoc.apellido_paterno,asoc.apellido_materno,
			asoc.documento,asoc.idtipoDocumento,td.descripcion,asoc.idparentesco,p.descripcion parentesco
    from personaasociada asoc inner join socio s
	on s.idsocio=asoc.idsocio inner join parentesco p
	on p.idparentesco=asoc.idparentesco inner join tipodocumento td
	on asoc.idtipoDocumento=td.idtipoDocumento
	where asoc.idsocio=p_idsocio
	order by asoc.idpersonaAsociada;
END //
DELIMITER ;


drop procedure if exists usp_obt_FotoSocio;
DELIMITER //

CREATE PROCEDURE usp_obt_FotoSocio(
IN p_idsocio varchar(25)
     )
BEGIN 

   select s.fotoBytes
    from socio s
	where s.idsocio=p_idsocio; 
END //
DELIMITER ;

drop procedure if exists usp_obt_HuellaSocio;
DELIMITER //

CREATE PROCEDURE usp_obt_HuellaSocio(
IN p_idsocio varchar(25)
     )
BEGIN 

   select s.huellaBytes,s.huellaFileName
    from socio s
	where s.idsocio=p_idsocio; 
END //
DELIMITER ;

drop procedure if exists usp_obt_FirmaSocio;
DELIMITER //

CREATE PROCEDURE usp_obt_FirmaSocio(
IN p_idsocio varchar(25)
     )
BEGIN 

   select s.firmaBytes
    from socio s
	where s.idsocio=p_idsocio; 
END //
DELIMITER ;


drop procedure if exists usp_obt_ReqAsociarse;
DELIMITER //

CREATE PROCEDURE usp_obt_ReqAsociarse(
IN p_idsocio varchar(25)
     )
BEGIN 

   select s.requisito_asociarseBytes,
			s.requisito_asociarseFileName
    from socio s
	where s.idsocio=p_idsocio; 
END //
DELIMITER ;


drop procedure if exists usp_Update_Telefonos;
DELIMITER //

CREATE PROCEDURE usp_Update_Telefonos(
IN	p_idtelefono_socio int(11),
IN	p_telefono varchar(15),
IN	p_idsocio varchar(25)
     )
BEGIN 

	update telefono_socio
	set telefono=p_telefono
	where idtelefono_socio=p_idtelefono_socio and idsocio=p_idsocio;
END //
DELIMITER ;

drop procedure if exists usp_Update_Direcciones;
DELIMITER //

CREATE PROCEDURE usp_Update_Direcciones(
IN	p_iddireccion_socio int(11),
IN	p_direccion varchar(45),
IN	p_idsocio varchar(25)
     )
BEGIN 

	update direccion_socio
	set direccion=p_direccion
	where iddireccion_socio=p_iddireccion_socio and idsocio=p_idsocio;
END //
DELIMITER ;


drop procedure if exists usp_Update_PersonasAsociadas;
DELIMITER //

CREATE PROCEDURE usp_Update_PersonasAsociadas(
IN	p_idpersonaAsociada varchar(25),
IN	p_nombres varchar(45),
IN	p_apellido_paterno varchar(45),
IN	p_apellido_materno varchar(45),
IN	p_documento varchar(45),
IN	p_idtipoDocumento int(11),
IN	p_idparentesco int(11),
IN	p_idsocio varchar(25)
     )
BEGIN 

	update personaasociada
	set nombres=p_nombres,
		apellido_paterno=p_apellido_paterno,
		apellido_materno=p_apellido_materno,
		documento=p_documento,
		idtipoDocumento=p_idtipoDocumento,
		idparentesco=p_idparentesco
	where idpersonaAsociada=p_idpersonaAsociada and idsocio=p_idsocio;
END //
DELIMITER ;

drop procedure if exists usp_UpdateMotivoSocio;
DELIMITER //

CREATE PROCEDURE usp_UpdateMotivoSocio(
IN	p_motivo_actualizacion varchar(200),
IN	p_idsocio varchar(25)
     )
BEGIN 

	update socio
	set motivo_actualizacion=p_motivo_actualizacion
	where idsocio=p_idsocio;
END //
DELIMITER ;


drop procedure if exists usp_Delete_Telefono;
DELIMITER //

create procedure usp_Delete_Telefono(
IN  p_idtelefono_socio int(11), 
IN  p_idsocio VARCHAR(25)
)
BEGIN

	delete from telefono_socio
	where idtelefono_socio=p_idtelefono_socio and idsocio=p_idsocio;

END //
DELIMITER ;

drop procedure if exists usp_Delete_Direccion;
DELIMITER //

create procedure usp_Delete_Direccion(
IN  p_iddireccion_socio int(11), 
IN  p_idsocio VARCHAR(25)
)
BEGIN

	delete from direccion_socio
	where iddireccion_socio=p_iddireccion_socio and idsocio=p_idsocio;

END //
DELIMITER ;


drop procedure if exists usp_Delete_PersonaAsociada;
DELIMITER //

create procedure usp_Delete_PersonaAsociada(
IN  p_idpersonaAsociada VARCHAR(25), 
IN  p_idsocio VARCHAR(25)
)
BEGIN

	delete from personaasociada
	where idpersonaAsociada=p_idpersonaAsociada and idsocio=p_idsocio;

END //
DELIMITER ;


drop procedure if exists usp_Obtener_Enlace;
DELIMITER //

create procedure usp_Obtener_Enlace(
IN  p_login VARCHAR(45), 
IN  p_password VARCHAR(45)
)
BEGIN

	SELECT	distinct(e.idenlace) as idenlace,
			e.descripcion as descripcion,
			e.icon_left as icon_left, 
			e.icon_right as icon_right 
		from usuario u inner join usuario_rol ur
		on u.idusuario=ur.idusuario inner join rol r
		on ur.idrol= r.idrol inner join permiso per
		on per.idrol=r.idrol inner join sub_enlace se
		on se.idsub_enlace= per.idsub_enlace inner join enlace e
		on se.idenlace=e.idenlace
		where u.login=p_login and u.password=p_password
		order by e.idenlace asc;	

END //
DELIMITER ;


drop procedure if exists usp_Obtener_SubEnlace;
DELIMITER //

create procedure usp_Obtener_SubEnlace(
IN  p_idenlace int(11), 
IN  p_idusuario int(11)
)
BEGIN

		SELECT	se.idsub_enlace as idsub_enlace,
				se.descripcion_sub_enlace as descripcion_sub_enlace,
				se.url as url	
			from sub_enlace se inner join permiso per
			on se.idsub_enlace=per.idsub_enlace inner join usuario_rol ur
			on per.idrol=ur.idrol inner join usuario u
			on ur.idusuario=u.idusuario
		where se.idenlace=p_idenlace and u.idusuario=p_idusuario;

END //
DELIMITER ;


drop procedure if exists usp_Obtener_DatosLogueado;
DELIMITER //

create procedure usp_Obtener_DatosLogueado(
IN  p_login VARCHAR(45), 
IN  p_password VARCHAR(45)
)
BEGIN

	select	idtrabajador,
			concat(nombres,' ',apellidos) as usuario,
			u.idusuario
		from trabajador t inner join usuario u
		on t.idusuario=u.idusuario
		where u.login=p_login and u.password=p_password;

END //
DELIMITER ;

drop procedure if exists usp_Ins_Trabajador;
DELIMITER //

CREATE PROCEDURE usp_Ins_Trabajador( 
IN  p_nombres VARCHAR(15), 
IN  p_apellidos VARCHAR(45),
IN 	p_DNI VARCHAR(45),
IN	p_login varchar(45),
IN	p_password varchar(45),
IN	p_idrol int(11),
out p_idtrabajador VARCHAR(25)
     )
BEGIN
	 declare v_idusuario int(11);
	 declare v_idtrabajador varchar(25);

	 set v_idusuario=0;
	 set v_idtrabajador='';
	
	insert into usuario values
	(null,p_login,p_password);

	select max(idusuario) into v_idusuario
	from usuario;
	
	insert into usuario_rol values
	(v_idusuario,p_idrol);

	
	
	select  concat('Trab',CAST(RIGHT(CONCAT('0000' , CAST(RTRIM(CAST(RIGHT(IFNULL(MAX(idtrabajador),0),4) 
	as signed integer)+1)AS CHAR(1000) CHARACTER SET utf8) ),4)AS CHAR(1000) CHARACTER SET utf8)) into v_idtrabajador
	from trabajador;

    INSERT INTO trabajador
         (
		idtrabajador,
		nombres,
        apellidos,
        DNI,
        idusuario
         )
    VALUES 
         (
	v_idtrabajador,    
	p_nombres  ,
    p_apellidos ,
	p_DNI,
    v_idusuario
         ); 
	
	set p_idtrabajador=v_idtrabajador;
END //
DELIMITER ;


drop procedure if exists usp_TotaRegist_Trabajador;
DELIMITER //
CREATE  PROCEDURE usp_TotaRegist_Trabajador(
IN	p_idtrabajador varchar(25),
IN	p_trabajador varchar(45),
IN	p_DNI varchar(45),
OUT P_TOTALREGISTRO INT
)
BEGIN 
	declare v_TOTALREGISTRO INT;
	set v_TOTALREGISTRO=0;

	select count(t.idtrabajador) into v_TOTALREGISTRO
	from trabajador t
	where 
		(t.idtrabajador =p_idtrabajador OR '' = p_idtrabajador) and 
		(CONCAT(t.nombres,' ',t.apellidos) like CONCAT("%",p_trabajador,"%") 
		or ''=p_trabajador)
		and (t.DNI=p_DNI or ''=p_DNI);


	set P_TOTALREGISTRO = v_TOTALREGISTRO;
END //
DELIMITER ;

drop procedure if exists usp_Cons_DatosTrabajador;
DELIMITER //
CREATE  PROCEDURE usp_Cons_DatosTrabajador(
IN	p_idtrabajador varchar(25),
IN	p_trabajador varchar(45),
IN	p_DNI varchar(45),
IN	p_limit int,
IN	p_desde int

)
BEGIN 
	PREPARE STMT FROM  "
   select	t.idtrabajador, 
			concat(t.nombres,' ',t.apellidos) trabajador,
			t.DNI,
			u.login,
			u.password,
			r.descripcion
	from trabajador t inner join usuario u
	on t.idusuario=u.idusuario inner join usuario_rol ur
    on u.idusuario=ur.idusuario inner join rol r
    on ur.idrol=r.idrol
	where (t.idtrabajador =? OR '' = ?) and 
		(CONCAT(t.nombres,' ',t.apellidos) like CONCAT(""%"",?,""%"") 
		or ''=CONCAT(""%"",?,""%""))
		and (t.DNI=? or ''=?)
		order by t.idtrabajador asc limit ? offset ?";
	
	SET @p_idtrabajador = p_idtrabajador; 
	SET @p_trabajador = p_trabajador; 
	SET @p_DNI = p_DNI; 
	SET @p_limit = p_limit; 
	SET @p_desde = p_desde; 


	EXECUTE STMT USING @p_idtrabajador,@p_idtrabajador,@p_trabajador,@p_trabajador,@p_DNI,@p_DNI,
						@p_limit,@p_desde;
	DEALLOCATE PREPARE STMT;
END //
DELIMITER ;



drop procedure if exists usp_obt_DatosTrabajador;
DELIMITER //


CREATE PROCEDURE usp_obt_DatosTrabajador(
IN p_idtrabajador varchar(25)
     )
BEGIN 

   select t.idtrabajador,t.nombres,t.apellidos,t.DNI,u.idusuario,u.login,u.password,ur.idrol
    from trabajador t inner join usuario u 
	on t.idusuario=u.idusuario inner join usuario_rol ur
    on u.idusuario=ur.idusuario
	where t.idtrabajador=p_idtrabajador
	order by t.idtrabajador;
END //
DELIMITER ;



drop procedure if exists usp_Update_Trabajador;
DELIMITER //

CREATE PROCEDURE usp_Update_Trabajador(
IN	p_idtrabajador varchar(25),
IN	p_nombres varchar(45),
IN	p_apellidos varchar(45),
IN	p_DNI varchar(45),
IN	p_idusuario int(11),
IN	p_login varchar(45),
IN	p_password varchar(45),
IN	p_idrol int(11)
     )
BEGIN 

	UPDATE trabajador
	SET nombres=p_nombres,apellidos=p_apellidos,DNI=p_DNI
	where idtrabajador=p_idtrabajador;
    
	update usuario
    set login=p_login,password=p_password
    where idusuario=p_idusuario;
    

END //
DELIMITER ;

drop procedure if exists usp_DaBajaSocio;
DELIMITER //

CREATE PROCEDURE usp_DaBajaSocio(
IN	p_idsocio varchar(25)
     )
BEGIN 

	UPDATE socio
	SET idestado=0
	where idsocio=p_idsocio;
    

END //
DELIMITER ;