package com.pandero.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.pandero.beans.DireccionBean;
import com.pandero.beans.PaisBean;
import com.pandero.beans.ParentescoBean;
import com.pandero.beans.PersonaAsociadaBean;
import com.pandero.beans.SocioBean;
import com.pandero.beans.TelefonoBean;
import com.pandero.beans.TipoDocumentoBean;



public class MySqlSocioDao  implements SocioDao{

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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TipoDocumentoBean> listaTipoDocumento() throws Exception {
		// TODO Auto-generated method stub
		
		
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<TipoDocumentoBean>();
		try {
			lista = session.selectList("dawi.SQL_traerTipoDocumento");
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PaisBean> listaPais() throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<PaisBean>();
		try {
			lista = session.selectList("dawi.SQL_traerPais");
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	public String InsertaSocio(SocioBean bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		String salida = "";
		
		try {
			salida = (String)session.selectOne("dawi.SQL_insertaSocio",bean);
			
			for (TelefonoBean item : bean.getTelefonos()) {
				/*setear id socio en idSocioAuxiliar*/
				item.setIdSocioaux(bean.getIdsocio());
				//Insert Telefono
				session.insert("dawi.SQL_insertaTelefonos", item);
			}
			for(DireccionBean item:bean.getDirecciones()){
				/*setear id socio en idSocioAuxiliar*/
				item.setIdSocioaux(bean.getIdsocio());
				//Insert Direcciones
				session.insert("dawi.SQL_insertaDirecciones", item);
			}	
			for (PersonaAsociadaBean item : bean.getLstpersonaAsociadas()) {
				/*setear id socio en idSocioAuxiliar*/
				item.setIdsocio_aux(bean.getIdsocio());
				//Insert PersonaAsociada
				session.insert("dawi.SQL_insertaPersonaAsociada", item);
			}
			
			salida=bean.getIdsocio();
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return salida;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ParentescoBean> listaParentesco() throws Exception {
		SqlSession session=sqlMapper.openSession();
		List<ParentescoBean> lstParentesco=new ArrayList<ParentescoBean>();
		try {
			lstParentesco=session.selectList("dawi.SQL_traerParentesco");
			return lstParentesco;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	@Override
	public int InsertaCodeBarra_CodeQR(SocioBean bean) {
		SqlSession session=sqlMapper.openSession();
		int salida=-1;
		try {
			salida = session.update("dawi.SQL_updateCodeBarra_CodeQR",bean);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return salida;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SocioBean> listaSocio(SocioBean bean) throws Exception {
		SqlSession session=sqlMapper.openSession();
		List<SocioBean> data=new ArrayList<SocioBean>();
		try {
			data=session.selectList("dawi.SQL_ListaSocio", bean);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	@Override
	public int TotalRegistroSocio(SocioBean bean) throws Exception {
		SqlSession session=sqlMapper.openSession();
		int TotalRegistro = 0;
		try {
			session.selectOne("dawi.SQL_TotalRegistroSocio", bean);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return TotalRegistro=bean.getPaginador().getTotalRegistro();
	}
	@Override
	public SocioBean ObtenerImagenPorPK(String idsocio) throws Exception {
		SqlSession session = sqlMapper.openSession();
		SocioBean bean = null;
		try {
			bean = (SocioBean)session.selectOne("dawi.ObtenerFotoPorPk", idsocio);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		return bean;
	}
	@Override
	public SocioBean ObtenerSocio(String idsocio) throws Exception {
		SqlSession session = sqlMapper.openSession();
		SocioBean bean = null;
		try {
			bean=(SocioBean)session.selectOne("dawi.SQL_ObtenerSocio", idsocio);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		return bean;
	}
	@Override
	public SocioBean ObtenerHuellaPorPK(String idsocio) throws Exception {
		SqlSession session = sqlMapper.openSession();
		SocioBean bean = null;
		try {
			bean = (SocioBean)session.selectOne("dawi.ObtenerHuellaPorPK", idsocio);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		return bean;
	}
	@Override
	public SocioBean ObtenerFirmaPorPK(String idsocio) throws Exception {
		SqlSession session = sqlMapper.openSession();
		SocioBean bean = null;
		try {
			bean = (SocioBean)session.selectOne("dawi.ObtenerFirmaPorPK", idsocio);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		return bean;
	}
	@Override
	public SocioBean ObtenerReqAsociarsePorPK(String idsocio) throws Exception {
		SqlSession session = sqlMapper.openSession();
		SocioBean bean = null;
		try {
			bean = (SocioBean)session.selectOne("dawi.ObtenerReqAsociarsePorPK", idsocio);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		return bean;
	}
	@Override
	public int ActualizaSocio(SocioBean bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		int salida = -1;
		try {
				for (TelefonoBean item : bean.getTelefonos()) {
					if(item.getIdtelefono_socio()!=0){
						salida+=(int)session.update("SQL_actualizaTelefono", item);
					}else if(item.getIdtelefono_socio()==0){
						salida+=session.insert("dawi.SQL_insertaTelefonos", item);
					}
				}
				for (DireccionBean item : bean.getDirecciones()) {
					if(item.getIddireccion_socio()!=0){
						salida+=session.update("SQL_actualizaDireccion", item);
					}else if(item.getIddireccion_socio()==0){
						salida+=session.insert("dawi.SQL_insertaDirecciones", item);
					}
				}
				for (PersonaAsociadaBean item : bean.getLstpersonaAsociadas()) {
					if(item.getIdpersonaAsociada()!=null){
						salida+=session.update("SQL_actualizaPersonaAsociada", item);
					}else if(item.getIdpersonaAsociada()==null){
						salida+=session.insert("dawi.SQL_insertaPersonaAsociada", item);
					}
				}
				
				salida+=(int)session.update("SQL_insertaMotivoUpdateSocio",bean);
				
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
	public int EliminarTelefonoSocio(TelefonoBean bean) throws Exception {
		int salida=0;
		SqlSession session = sqlMapper.openSession();
		try {
			salida=(int)session.delete("SQL_DeleteTelefono", bean);
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
	public int EliminarDireccionSocio(DireccionBean bean) throws Exception {
		int salida=0;
		SqlSession session = sqlMapper.openSession();
		try {
			salida=(int)session.delete("SQL_DeleteDireccion", bean);
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
	public int EliminarPersonaAsociadaSocio(PersonaAsociadaBean bean)
			throws Exception {
		int salida=0;
		SqlSession session = sqlMapper.openSession();
		try {
			salida=(int)session.delete("SQL_DeletePersonaAsociada", bean);
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
	public int DarBajaSocio(String idsocio) throws Exception {
		int salida=0;
		SqlSession session = sqlMapper.openSession();
		try {
			salida=(int)session.update("SQL_DarBajaSocio", idsocio);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		return salida;
	}
	
}
