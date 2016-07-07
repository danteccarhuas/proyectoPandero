package com.pandero.dao;

import java.util.List;

import com.pandero.beans.EstadoBean;
import com.pandero.beans.ObligacionBean;
import com.pandero.beans.PaisBean;
import com.pandero.beans.SocioBean;
import com.pandero.beans.TipoObligacionBean;




public interface ReporteDAO {
	
	//Combos
		public abstract List<PaisBean> listPais() throws Exception;
		public abstract List<TipoObligacionBean> listaTipoObligacion() throws Exception;
		public abstract List<EstadoBean> listaEstado() throws Exception;
		
	//Reporte 1 -- Muestra a los clientes segun Pais
		
		public abstract List<SocioBean> consultaSocios(String idPais) throws Exception;
		
	//Reporte 2 -- Muestra las obligaciones segun tipo
		
		public abstract List<ObligacionBean> listaObligacionPorTipo(String idtipo_obligacion)throws	Exception;
		
	// Reporte 3
		
		public abstract List<ObligacionBean> consultaObligacionPorEstadoDePago(String estado) throws	Exception;
		
		
		
	
}
