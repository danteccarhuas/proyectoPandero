package com.pandero.dao;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.pandero.beans.DepartamentoBean;
import com.pandero.beans.DistritoBean;
import com.pandero.beans.ProvinciaBean;
import com.pandero.beans.UbigeoBean;

public class MySqlUbigeo implements UbigeoDAO {
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DepartamentoBean> traeDepartamentos() throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<UbigeoBean>();
		try {
			lista = session.selectList("dawi.SQL_traeDepartamentos");
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ProvinciaBean> traeProvincias(int iddepartamento) throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<UbigeoBean>();
		try {
			lista = session.selectList("dawi.SQL_traeProvincias", iddepartamento);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DistritoBean> traeDistritos(int idprovincia) throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<UbigeoBean>();
		try {
			lista = session.selectList("dawi.SQL_traeDistritos",idprovincia);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
