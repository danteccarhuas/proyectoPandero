package com.pandero.service;

import java.util.List;

import com.pandero.beans.DepartamentoBean;
import com.pandero.beans.DistritoBean;
import com.pandero.beans.ProvinciaBean;
import com.pandero.dao.FabricaDao;
import com.pandero.dao.UbigeoDAO;

public class UbigeoServiceImpl implements UbigeoService{
	FabricaDao factoria = FabricaDao.getFactorty(FabricaDao.MYSQL);
	UbigeoDAO dao = factoria.getUbigeoDao();
	
	public List<DepartamentoBean> traeDepartamentos() throws Exception {
		return dao.traeDepartamentos();
	}
	public List<ProvinciaBean> traeProvincias(int iddepartamento)throws Exception {
		return dao.traeProvincias(iddepartamento);
	}
	public List<DistritoBean> traeDistrito(int idprovincia)	throws Exception {
		return dao.traeDistritos(idprovincia);
	}
	

}
