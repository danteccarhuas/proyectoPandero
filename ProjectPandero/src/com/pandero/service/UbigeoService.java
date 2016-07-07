package com.pandero.service;

import java.util.List;

import com.pandero.beans.DepartamentoBean;
import com.pandero.beans.DistritoBean;
import com.pandero.beans.ProvinciaBean;

public interface UbigeoService {
	public abstract List<DepartamentoBean> traeDepartamentos() throws Exception;
	public abstract List<ProvinciaBean> traeProvincias(int iddepartamento) throws Exception;
	public abstract List<DistritoBean> traeDistrito(int idprovincia) throws Exception;
	
}
