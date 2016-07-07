package com.pandero.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.pandero.beans.RolBean;
import com.pandero.beans.TrabajadorBean;
import com.pandero.beans.UsuarioBean;

public class MySqlTrabajadorDAO implements TrabajadorDAO {

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
	public String insertaTrabajador(TrabajadorBean bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		String salida = "";
		
		try {
			salida = (String)session.selectOne("dawi.SQL_insertaTrabajador",bean);
			salida=bean.getIdtrabajador();
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return salida;
	}

	@Override
	public int actualizaTrabajador(TrabajadorBean bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		int salida =-1;
		
		try {
			salida=(int)session.update("SQL_actualizaTrabajador",bean);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return salida;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<UsuarioBean> listaUsuario() throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista=new ArrayList<UsuarioBean>();
		try {
			lista = session.selectList("dawi.SQL_traerusuario");
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<RolBean> listaRol() throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista=new ArrayList<RolBean>();
		try {
			lista = session.selectList("dawi.SQL_traerRol");
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}



	@Override
	public int eliminaTrabajador(int idTrabajador) throws Exception {
		SqlSession session = sqlMapper.openSession();
		int salida = -1;
		try {
			salida = session.delete("idEliminaTrabajador", idTrabajador);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		return salida;
	}

	@Override
	public int TotalRegistroTrabajador(TrabajadorBean bean) throws Exception {
		SqlSession session=sqlMapper.openSession();
		int TotalRegistro = 0;
		try {
			session.selectOne("dawi.SQL_TotalRegistroTrabajador", bean);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return TotalRegistro=bean.getPaginador().getTotalRegistro();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrabajadorBean> listaTrabajador(TrabajadorBean bean)
			throws Exception {
		SqlSession session=sqlMapper.openSession();
		List<TrabajadorBean> data=new ArrayList<TrabajadorBean>();
		try {
			data=session.selectList("dawi.SQL_ListaTrabajador", bean);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public TrabajadorBean ObtenerTrabajador(String idTrabajador) throws Exception {
		SqlSession session = sqlMapper.openSession();
		TrabajadorBean bean = null;
		try {
			bean=(TrabajadorBean)session.selectOne("dawi.SQL_ObtenerTrabajador", idTrabajador);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		return bean;
	}

}
