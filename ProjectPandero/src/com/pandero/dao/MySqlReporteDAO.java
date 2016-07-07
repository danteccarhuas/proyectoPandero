package com.pandero.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.pandero.beans.EstadoBean;
import com.pandero.beans.ObligacionBean;
import com.pandero.beans.PaisBean;
import com.pandero.beans.SocioBean;
import com.pandero.beans.TipoObligacionBean;




public class MySqlReporteDAO implements ReporteDAO{
	
	SqlSessionFactory sqlMapper = null;
	{
		String archivo = "ConfiguracionIbatis.xml";
		try {
			Reader reader = Resources.getResourceAsReader(archivo);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/********************************************************************************************
	 * 
	 * 				Reporte 1 -- Muestra a los clientes segun Pais
	 * 
	 ********************************************************************************************/	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<EstadoBean> listaEstado() throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<EstadoBean>();
		try {
			lista = session.selectList("dawi1.SQL_traeEstado");
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<PaisBean> listPais() throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<PaisBean>();
		try {
			lista = session.selectList("dawi1.SQL_traerPais");
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<SocioBean> consultaSocios(String idPais) throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<SocioBean>();
		try {
			lista =session.selectList("dawi.SQL_reportesConsultaSocio", idPais);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	/********************************************************************************************
	 * 
	 * 		Reporte 2 -- Muestra las obligaciones registradas en determinadas fechas
	 * 
	 ********************************************************************************************/	

	
	
	/********************************************************************************************
	 * 
	 * 				Reporte 3 -- Muestra las obligaciones segun tipo
	 * 
	 ********************************************************************************************/	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ObligacionBean> listaObligacionPorTipo(String idtipo_obligacion)throws Exception {
	SqlSession session = sqlMapper.openSession();
	List lista = new ArrayList<ObligacionBean>();
	try {
		lista =session.selectList("dawi.SQL_reportesConsultaObligacion", idtipo_obligacion);
		return lista;
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		session.close();
	}
	return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TipoObligacionBean> listaTipoObligacion() throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<TipoObligacionBean>();
		try {
			lista = session.selectList("dawi1.SQL_listaTipoObligacion");
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}




	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ObligacionBean> consultaObligacionPorEstadoDePago(String estado) throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<ObligacionBean>();
		try {
			lista =session.selectList("dawi1.SQL_reporteObligacionPorEstadoDePago", estado);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

}
