package com.pandero.service;

import java.util.List;

import com.pandero.beans.CuotaBean;
import com.pandero.beans.DetalleBean;
import com.pandero.beans.ObligacionBean;
import com.pandero.beans.FacturaBean;
import com.pandero.dao.FabricaDao;
import com.pandero.dao.PagoDao;

public class PagoServicelmpl implements PagoService{

	FabricaDao factoria = FabricaDao.getFactorty(FabricaDao.MYSQL);
	PagoDao dao = factoria.getPagoDao();

	@Override
	public ObligacionBean traObligacion(String dni) throws Exception {
		
		return dao.traObligacion(dni);
	}

	@Override
	public List<CuotaBean> cuotasDeObligacion(String idObligacion) throws Exception {
		// TODO Auto-generated method stub
		return dao.cuotasDeObligacion(idObligacion);
	}

	@Override
	public String calculaMora(String fecVencimiento, String fecActual, double montoPagar, double tasaMoratoriaAnual)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.calculaMora(fecVencimiento, fecActual, montoPagar, tasaMoratoriaAnual);
	}

	@Override
	public int cambia_estadoCuota(int cambio) throws Exception {
		return dao.cambia_estadoCuota(cambio);
	}

	@Override
	public List<CuotaBean> cuotasDeObligacion_sinFechaParse(String idObligacion) throws Exception {
		// TODO Auto-generated method stub
		return dao.cuotasDeObligacion_sinFechaParse(idObligacion);
	}

	@Override
	public int registraFactura(FacturaBean factura) throws Exception {
		// TODO Auto-generated method stub
		return dao.registraFactura(factura);
	}

	@Override
	public int registraDetalle(DetalleBean detalle) throws Exception {
		// TODO Auto-generated method stub
		return dao.registraDetalle(detalle);
	}

	@Override
	public int obtieneIdFactura() throws Exception {
		// TODO Auto-generated method stub
		return dao.obtieneIdFactura();
	}

	@Override
	public int cambiaEstadoObligacion(String idobligacion) throws Exception {
		// TODO Auto-generated method stub
		return dao.cambiaEstadoObligacion(idobligacion);
	}

	@Override
	public List<CuotaBean> cuotasDeObligacion2(String idObligacion) throws Exception {
		// TODO Auto-generated method stub
		return dao.cuotasDeObligacion2(idObligacion);
	}

	@Override
	public ObligacionBean traObligacion_sinVencimiento(String dni) throws Exception {
		// TODO Auto-generated method stub
		return dao.traObligacion_sinVencimiento(dni);
	}

	@Override
	public List<FacturaBean> ObtenerPagos(String idObligacion) throws Exception {
		// TODO Auto-generated method stub
		return dao.ObtenerPagos(idObligacion);
	}

}
