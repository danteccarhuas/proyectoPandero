package com.pandero.dao;

import java.util.List;

import com.pandero.beans.CuotaBean;
import com.pandero.beans.DetalleBean;
import com.pandero.beans.ObligacionBean;
import com.pandero.beans.FacturaBean;

public interface PagoDao {

	public abstract ObligacionBean traObligacion(String dni) throws Exception;
	public abstract ObligacionBean traObligacion_sinVencimiento(String dni) throws Exception;
	public abstract List<CuotaBean> cuotasDeObligacion(String idObligacion) throws Exception;
	public abstract List<FacturaBean> ObtenerPagos(String idObligacion) throws Exception;
	public abstract List<CuotaBean> cuotasDeObligacion2(String idObligacion) throws Exception;
	public abstract List<CuotaBean> cuotasDeObligacion_sinFechaParse(String idObligacion) throws Exception;
	public String calculaMora(String fecVencimiento,String fecActual,double montoPagar,double tasaMoratoriaAnual) throws Exception;

	public abstract int registraFactura(FacturaBean factura) throws Exception;
	public abstract int registraDetalle(DetalleBean detalle) throws Exception;
	public abstract int obtieneIdFactura() throws Exception;
	public abstract int cambia_estadoCuota(int cambio) throws Exception;
	public abstract int cambiaEstadoObligacion(String idobligacion)throws Exception;
	
}
