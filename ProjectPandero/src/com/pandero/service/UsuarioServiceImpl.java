package com.pandero.service;

import java.util.List;

import com.pandero.beans.EnlaceBean;
import com.pandero.beans.TrabajadorBean;
import com.pandero.dao.FabricaDao;
import com.pandero.dao.UsuarioDao;

public class UsuarioServiceImpl implements UsuarioService {

	FabricaDao factoria = FabricaDao.getFactorty(FabricaDao.MYSQL);
	UsuarioDao dao = factoria.getUsuarioDao();



	public TrabajadorBean login(TrabajadorBean bean) throws Exception {
		return dao.login(bean);
	}


	public List<EnlaceBean> traerEnlacesDeUsuario(TrabajadorBean bean)throws Exception {
		return dao.traerEnlacesDeUsuario(bean);

	}

}
