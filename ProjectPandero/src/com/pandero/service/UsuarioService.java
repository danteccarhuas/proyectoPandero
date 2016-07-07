package com.pandero.service;

import java.util.List;

import com.pandero.beans.EnlaceBean;
import com.pandero.beans.TrabajadorBean;

public interface UsuarioService {
	

	public TrabajadorBean login(TrabajadorBean bean) throws Exception;
	//public TrabajadorBean traeTrabajador(String idUsuario) throws Exception;
	public List<EnlaceBean> traerEnlacesDeUsuario(TrabajadorBean bean) throws Exception;


}