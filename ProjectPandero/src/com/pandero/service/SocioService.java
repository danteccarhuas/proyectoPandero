package com.pandero.service;

import java.util.List;

import com.pandero.beans.DireccionBean;
import com.pandero.beans.PaisBean;
import com.pandero.beans.ParentescoBean;
import com.pandero.beans.PersonaAsociadaBean;
import com.pandero.beans.SocioBean;
import com.pandero.beans.TelefonoBean;
import com.pandero.beans.TipoDocumentoBean;

public interface SocioService {

	public abstract String InsertaSocio(SocioBean bean) throws Exception;
	
	public abstract List<TipoDocumentoBean> listaTipoDocumento() throws Exception;
	public abstract List<PaisBean> listaPais() throws Exception;
	public List<ParentescoBean> listaParentesco() throws Exception;
	public int InsertaCodeBarra_CodeQR(SocioBean bean) throws Exception;
	public List<SocioBean> listaSocio(SocioBean bean) throws Exception;
	public int TotalRegistroSocio(SocioBean bean)throws Exception;
	public SocioBean ObtenerImagenPorPK(String idsocio)throws Exception;
	public SocioBean ObtenerSocio(String idsocio)throws Exception;
	public SocioBean ObtenerHuellaPorPK(String idsocio)throws Exception;
	public SocioBean ObtenerFirmaPorPK(String idsocio)throws Exception;
	public SocioBean ObtenerReqAsociarsePorPK(String idsocio)throws Exception;
	public int ActualizaSocio(SocioBean bean) throws Exception;
	public int EliminarTelefonoSocio(TelefonoBean bean) throws Exception;
	public int EliminarDireccionSocio(DireccionBean bean) throws Exception;
	public int EliminarPersonaAsociadaSocio(PersonaAsociadaBean bean) throws Exception;
	public int DarBajaSocio(String idsocio) throws Exception;
}
