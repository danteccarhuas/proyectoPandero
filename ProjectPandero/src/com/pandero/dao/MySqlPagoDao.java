package com.pandero.dao;

import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.pandero.beans.CuotaBean;
import com.pandero.beans.DetalleBean;
import com.pandero.beans.ObligacionBean;
import com.pandero.beans.FacturaBean;
import com.pandero.beans.SocioBean;
import com.pandero.beans.TipoDocumentoBean;

public class MySqlPagoDao implements PagoDao{

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
	public ObligacionBean traObligacion(String dni) throws Exception {
		
		SqlSession session = sqlMapper.openSession();
		ObligacionBean x =null;
		try {
			x = (ObligacionBean)session.selectOne("dawi.SQL_traeObligacion",dni);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return x;
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CuotaBean> cuotasDeObligacion(String idObligacion) throws Exception {
		

		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<CuotaBean>();
		try {
			lista = session.selectList("dawi.SQL_cuotasDeObligacion",idObligacion);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CuotaBean> cuotasDeObligacion2(String idObligacion) throws Exception {
		

		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<CuotaBean>();
		try {
			lista = session.selectList("dawi.SQL_cuotasDeObligacion2",idObligacion);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CuotaBean> cuotasDeObligacion_sinFechaParse(String idObligacion) throws Exception {
		

		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<CuotaBean>();
		try {
			lista = session.selectList("dawi.SQL_cuotasDeObligacion_sinFechaParse",idObligacion);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
	}


	@Override
	public String calculaMora(String fecVencimiento, String fecActual, double montoPagar, double tasaMoratoriaAnual){
		
		double salida = -1;
		String salida_aux="";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date dateFecVencimiento = sdf.parse(fecVencimiento);
			//String vencimiento=sdf.format(dateFecVencimiento);
			//dateFecVencimiento=sdf.parse(vencimiento);
			Date dateFecPago = sdf.parse(fecActual);

			if (dateFecVencimiento.equals(dateFecPago)) {
				return String.valueOf(montoPagar);
			} else {
				// milisegundos desde el 1 de enero de 1970
				// https://docs.oracle.com/javase/7/docs/api/java/util/Date.html
				// Returns the number of milliseconds
				long longFecVencimiento = dateFecVencimiento.getTime();
				long longFecPago = dateFecPago.getTime();

				if (longFecPago < longFecVencimiento) {
					return String.valueOf(montoPagar);
				} else {
					long resta = longFecPago - longFecVencimiento;

					long diasTardanza = resta / (1000 * 60 * 60 * 24);

					// Pasa la tasa de año a dias
					// tasaDias = (1 + tasaAños)^(1/360)-1
					double tasaDias = Math.pow((1 + tasaMoratoriaAnual),
							(1.0 / 360)) - 1;

					// interes = montoPagar*(1 + tasaDias)^(dias)
					/*NumberFormat nf = NumberFormat.getNumberInstance();
					nf.setMaximumFractionDigits(1);
					nf.setMinimumFractionDigits(1);*/
					
					
					salida = montoPagar* Math.pow((1 + tasaDias), diasTardanza);
					
					System.out.println(salida);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.valueOf(salida);
	}
	
	@Override
	public int cambia_estadoCuota(int cambio) throws Exception {
		SqlSession session = sqlMapper.openSession();
		int salida = -1;
		try {
				salida = (int)session.update("SQL_cambiaEstadoCuota", cambio);
				session.commit();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally{
			session.close();
		}
		return salida;
	}
	
	@Override
	public int registraFactura(FacturaBean factura) throws Exception {
		SqlSession session = sqlMapper.openSession();
		int salida = 0;
		try {
			salida = session.insert("SQL_insertaFactura",factura);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
	
		} finally {
			session.close();
		}
		return salida;
	}

	@Override
	public int registraDetalle(DetalleBean detalle) throws Exception {
		SqlSession session = sqlMapper.openSession();
		int salida = 0;
		try {
			salida = session.insert("SQL_insertaDetalle",detalle);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
	
		} finally {
			session.close();
		}
		return salida;
	}

	@Override
	public int obtieneIdFactura() throws Exception {
		SqlSession session = sqlMapper.openSession();
		int x =0;
		try {
			x = (int) session.selectOne("dawi.SQL_obtieneIdFactura");
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return x;
	}



	@Override
	public int cambiaEstadoObligacion(String idobligacion) throws Exception {
		SqlSession session = sqlMapper.openSession();
		int salida = -1;
		try {
				salida = (int)session.update("SQL_cambiaEstadoObligacion", idobligacion);
				session.commit();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally{
			session.close();
		}
		return salida;
	}



	@Override
	public ObligacionBean traObligacion_sinVencimiento(String dni) throws Exception {
		SqlSession session = sqlMapper.openSession();
		ObligacionBean x =null;
		try {
			x = (ObligacionBean)session.selectOne("dawi.SQL_traeObligacion_sinVencimiento",dni);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return x;
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<FacturaBean> ObtenerPagos(String idObligacion) throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<FacturaBean>();
		try {
			lista = session.selectList("dawi.SQL_ObtenerPagos",idObligacion);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
	}

}
