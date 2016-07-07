package com.pandero.dao;

import java.util.List;

import com.pandero.beans.DepartamentoBean;
import com.pandero.beans.DistritoBean;
import com.pandero.beans.ProvinciaBean;



public interface UbigeoDAO {
	public abstract List<DepartamentoBean> traeDepartamentos() throws Exception;
	public abstract List<ProvinciaBean> traeProvincias(int iddepartamento) throws Exception;
	public abstract List<DistritoBean> traeDistritos(int idprovincia) throws Exception;

}
