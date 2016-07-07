package com.pandero.service;

import java.util.List;

import com.pandero.beans.RolBean;
import com.pandero.beans.TrabajadorBean;
import com.pandero.beans.UsuarioBean;
import com.pandero.dao.MySqlTrabajadorDAO;

public class TrabajadorServiceImpl implements TrabajadorService{
	MySqlTrabajadorDAO dao=new MySqlTrabajadorDAO();
	@Override
	public String insertaTrabajador(TrabajadorBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertaTrabajador(bean);
	}

	@Override
	public int actualizaTrabajador(TrabajadorBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.actualizaTrabajador(bean);
	}

	@Override
	public List<UsuarioBean> listaUsuario() throws Exception {
		// TODO Auto-generated method stub
		return dao.listaUsuario();
	}

	@Override
	public List<RolBean> listaRol() throws Exception {
		// TODO Auto-generated method stub
		return dao.listaRol();
	}

	@Override
	public int eliminaTrabajador(int idTrabajador) throws Exception {
		// TODO Auto-generated method stub
		return dao.eliminaTrabajador(idTrabajador);
	}

	@Override
	public int TotalRegistroTrabajador(TrabajadorBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.TotalRegistroTrabajador(bean);
	}

	@Override
	public List<TrabajadorBean> listaTrabajador(TrabajadorBean bean)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.listaTrabajador(bean);
	}

	@Override
	public TrabajadorBean ObtenerTrabajador(String idTrabajador) throws Exception {
		// TODO Auto-generated method stub
		return dao.ObtenerTrabajador(idTrabajador);
	}

}
