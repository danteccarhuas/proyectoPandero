package com.pandero.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.pandero.beans.EnlaceBean;
import com.pandero.beans.Sub_EnlaceBean;
import com.pandero.beans.TrabajadorBean;

public class MySqlUsuario implements UsuarioDao {
	
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

	@Override
	public TrabajadorBean login(TrabajadorBean bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		TrabajadorBean trabajador = null;
		try {
			trabajador = (TrabajadorBean) session.selectOne("dawi.SQL_login", bean);
			return trabajador;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnlaceBean> traerEnlacesDeUsuario(TrabajadorBean bean)
			throws Exception {
		SqlSession session=sqlMapper.openSession();
		try {
			List<EnlaceBean> lstEnlace= session.selectList("dawi.SQL_Enlace", bean);
			//Obtener Sub_Enlace por enlace
			for (int i = 0; i < lstEnlace.size(); i++) {
				lstEnlace.get(i).setIdusuario_axu(bean.getIdusuario().getIdusuario());
				lstEnlace.get(i).setListsub_enlace(traerSub_Enlaces_po_Enlace(lstEnlace.get(i)));
			}
			return lstEnlace;
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Sub_EnlaceBean> traerSub_Enlaces_po_Enlace(EnlaceBean bean){
		SqlSession session=sqlMapper.openSession();
		try {
			List<Sub_EnlaceBean> lst_subEnlace=session.selectList("dawi.SQL_Sub_Enlace",bean);
			
			return lst_subEnlace;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

}
