package com.pandero.dao;

import java.util.List;

import com.pandero.beans.EnlaceBean;
import com.pandero.beans.TrabajadorBean;



public interface UsuarioDao {

	public TrabajadorBean  login(TrabajadorBean bean) throws Exception;
	//public TrabajadorBean traeTrabajador(TrabajadorBean bean) throws Exception;
	public List<EnlaceBean> traerEnlacesDeUsuario(TrabajadorBean bean) throws Exception;
	
}