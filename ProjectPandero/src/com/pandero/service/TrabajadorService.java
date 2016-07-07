package com.pandero.service;

import java.util.List;

import com.pandero.beans.RolBean;
import com.pandero.beans.TrabajadorBean;
import com.pandero.beans.UsuarioBean;

public interface TrabajadorService {
	
	public abstract String insertaTrabajador(TrabajadorBean bean) throws Exception;
	public abstract int actualizaTrabajador(TrabajadorBean bean) throws Exception;
	public abstract List<UsuarioBean> listaUsuario() throws Exception;
	public abstract List<RolBean> listaRol() throws Exception;
	public int eliminaTrabajador (int idTrabajador) throws Exception;
	public int TotalRegistroTrabajador(TrabajadorBean bean) throws Exception;
	public List<TrabajadorBean> listaTrabajador(TrabajadorBean bean) throws Exception;
	public TrabajadorBean ObtenerTrabajador(String idTrabajador) throws Exception;;
}
