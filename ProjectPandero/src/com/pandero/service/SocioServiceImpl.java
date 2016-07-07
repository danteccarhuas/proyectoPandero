package com.pandero.service;

import java.util.List;

import com.pandero.beans.DireccionBean;
import com.pandero.beans.PaisBean;
import com.pandero.beans.ParentescoBean;
import com.pandero.beans.PersonaAsociadaBean;
import com.pandero.beans.SocioBean;
import com.pandero.beans.TelefonoBean;
import com.pandero.beans.TipoDocumentoBean;
import com.pandero.dao.FabricaDao;
import com.pandero.dao.SocioDao;

public class SocioServiceImpl implements SocioService {

	FabricaDao factoria = FabricaDao.getFactorty(FabricaDao.MYSQL);
	SocioDao dao = factoria.getSocioDao();
	
	public List<TipoDocumentoBean> listaTipoDocumento() throws Exception {
		// TODO Auto-generated method stub
		return dao.listaTipoDocumento();
	}

	public List<PaisBean> listaPais() throws Exception {
		// TODO Auto-generated method stub
		return dao.listaPais();
	}

	public String InsertaSocio(SocioBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.InsertaSocio(bean);
	}

	@Override
	public List<ParentescoBean> listaParentesco() throws Exception {
		// TODO Auto-generated method stub
		return dao.listaParentesco();
	}

	@Override
	public int InsertaCodeBarra_CodeQR(SocioBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.InsertaCodeBarra_CodeQR(bean);
	}

	@Override
	public List<SocioBean> listaSocio(SocioBean bean) throws Exception {
		
		return dao.listaSocio(bean);
	}

	@Override
	public int TotalRegistroSocio(SocioBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.TotalRegistroSocio(bean);
	}

	@Override
	public SocioBean ObtenerImagenPorPK(String idsocio) throws Exception {
		// TODO Auto-generated method stub
		return dao.ObtenerImagenPorPK(idsocio);
	}

	@Override
	public SocioBean ObtenerSocio(String idsocio) throws Exception {
		// TODO Auto-generated method stub
		return dao.ObtenerSocio(idsocio);
	}

	@Override
	public SocioBean ObtenerHuellaPorPK(String idsocio) throws Exception {
		// TODO Auto-generated method stub
		return dao.ObtenerHuellaPorPK(idsocio);
	}

	@Override
	public SocioBean ObtenerFirmaPorPK(String idsocio) throws Exception {
		// TODO Auto-generated method stub
		return dao.ObtenerFirmaPorPK(idsocio);
	}

	@Override
	public SocioBean ObtenerReqAsociarsePorPK(String idsocio) throws Exception {
		// TODO Auto-generated method stub
		return dao.ObtenerReqAsociarsePorPK(idsocio);
	}

	@Override
	public int ActualizaSocio(SocioBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.ActualizaSocio(bean);
	}

	@Override
	public int EliminarTelefonoSocio(TelefonoBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.EliminarTelefonoSocio(bean);
	}

	@Override
	public int EliminarDireccionSocio(DireccionBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.EliminarDireccionSocio(bean);
	}

	@Override
	public int EliminarPersonaAsociadaSocio(PersonaAsociadaBean bean)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.EliminarPersonaAsociadaSocio(bean);
	}

	@Override
	public int DarBajaSocio(String idsocio) throws Exception {
		// TODO Auto-generated method stub
		return dao.DarBajaSocio(idsocio);
	}

}
