package com.pandero.service;

import java.util.List;

import com.pandero.beans.EstadoBean;
import com.pandero.beans.ObligacionBean;
import com.pandero.beans.PaisBean;
import com.pandero.beans.SocioBean;
import com.pandero.beans.TipoObligacionBean;
import com.pandero.dao.FabricaDao;
import com.pandero.dao.ReporteDAO;




public class ReporteServiceImpl implements ReporteService{

	FabricaDao factoria = FabricaDao.getFactorty(FabricaDao.MYSQL);
	ReporteDAO dao = factoria.getReporteDao();
	
	//Reporte 1 -- Muestra a los clientes segun Pais
	@Override
	public List<SocioBean> consultaSocios(String idPais) throws Exception {
		return dao.consultaSocios(idPais);
	}
	@Override
	public List<PaisBean> listPais() throws Exception {
		return dao.listPais();
	}
	
	
	//Reporte 2 -- Muestra las obligaciones registradas en determinadas fechas
	
	
	@Override
	public List<ObligacionBean> listaObligacionPorTipo(String idtipo_obligacion)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.listaObligacionPorTipo(idtipo_obligacion);
	}
	
	
	//Reporte 3 -- Muestra las obligaciones segun tipo
	@Override
	public List<TipoObligacionBean> listaTipoObligacion() throws Exception {
		return dao.listaTipoObligacion();
	}
	@Override
	public List<EstadoBean> listaEstado() throws Exception {
		// TODO Auto-generated method stub
		return dao.listaEstado();
	}
	@Override
	public List<ObligacionBean> consultaObligacionPorEstadoDePago(String estado) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaObligacionPorEstadoDePago(estado);
	}



}
