package com.pandero.beans;

import java.io.File;
import java.io.InputStream;
import java.util.List;


public class SocioBean {
	
	private String idsocio,nombres,apellido_paterno,apellido_materno,documento,correo;
	private List<TelefonoBean> telefonos;
	private List<DireccionBean> direcciones;
	private TipoDocumentoBean idtipoDocumento;
	private PaisBean idPais;
	private UbigeoBean idubigeo;
	private UsuarioBean idusuario;
	
	private String idpersonaAsociada_aux;
	
	//reglas de struts
	//private File CCC;
	//private String CCCFileName;
	//private String CCCContentType;
	//private byte[] CCCCBytes;
	
	//1->huella
	private File huella;
	private String huellaFileName;
	private String huellaContentType;
	private byte[] huellaBytes;//ENVIA A LA BD
	
	//2->firma
	private File firma;
	private String firmaFileName;
	private String firmaContentType;
	private byte[] firmaBytes;//ENVIA A LA BD
	
	//3->foto
	private File foto;
	private String fotoFileName;
	private String fotoContentType;
	private byte[] fotoBytes;//ENVIA A LA BD
	
	//4->requisitos
	private File requisito_asociarse;
	private String requisito_asociarseFileName;
	private String requisito_asociarseContentType;
	private byte[] requisito_asociarseBytes;
	
	//5->Firma
	private File imagenFirma;
	private String imagenFirmaFileName;
	private String imagenFirmaContentType;
	private String imagenFirmaBytes;
	
	
	private byte[] barcode,qrcode;//ENVIA A LA BD 
	
	private List<PersonaAsociadaBean> lstpersonaAsociadas;
	private TrabajadorBean idtrabajador;
	private PaginadorBean paginador;
	private InputStream imageFoto;
	private String motivo_actualizacion;
	
	public String getIdsocio() {
		return idsocio;
	}
	public void setIdsocio(String idsocio) {
		this.idsocio = idsocio;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public List<TelefonoBean> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<TelefonoBean> telefonos) {
		this.telefonos = telefonos;
	}
	public List<DireccionBean> getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(List<DireccionBean> direcciones) {
		this.direcciones = direcciones;
	}
	public TipoDocumentoBean getIdtipoDocumento() {
		return idtipoDocumento;
	}
	public void setIdtipoDocumento(TipoDocumentoBean idtipoDocumento) {
		this.idtipoDocumento = idtipoDocumento;
	}
	public PaisBean getIdPais() {
		return idPais;
	}
	public void setIdPais(PaisBean idPais) {
		this.idPais = idPais;
	}
	public UbigeoBean getIdubigeo() {
		return idubigeo;
	}
	public void setIdubigeo(UbigeoBean idubigeo) {
		this.idubigeo = idubigeo;
	}
	public UsuarioBean getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(UsuarioBean idusuario) {
		this.idusuario = idusuario;
	}
	public File getHuella() {
		return huella;
	}
	public void setHuella(File huella) {
		this.huella = huella;
	}
	public String getHuellaFileName() {
		return huellaFileName;
	}
	public void setHuellaFileName(String huellaFileName) {
		this.huellaFileName = huellaFileName;
	}
	public String getHuellaContentType() {
		return huellaContentType;
	}
	public void setHuellaContentType(String huellaContentType) {
		this.huellaContentType = huellaContentType;
	}
	public byte[] getHuellaBytes() {
		return huellaBytes;
	}
	public void setHuellaBytes(byte[] huellaBytes) {
		this.huellaBytes = huellaBytes;
	}
	public File getFirma() {
		return firma;
	}
	public void setFirma(File firma) {
		this.firma = firma;
	}
	public String getFirmaFileName() {
		return firmaFileName;
	}
	public void setFirmaFileName(String firmaFileName) {
		this.firmaFileName = firmaFileName;
	}
	public String getFirmaContentType() {
		return firmaContentType;
	}
	public void setFirmaContentType(String firmaContentType) {
		this.firmaContentType = firmaContentType;
	}
	public byte[] getFirmaBytes() {
		return firmaBytes;
	}
	public void setFirmaBytes(byte[] firmaBytes) {
		this.firmaBytes = firmaBytes;
	}
	public byte[] getBarcode() {
		return barcode;
	}
	public void setBarcode(byte[] barcode) {
		this.barcode = barcode;
	}
	public byte[] getQrcode() {
		return qrcode;
	}
	public void setQrcode(byte[] qrcode) {
		this.qrcode = qrcode;
	}
	public List<PersonaAsociadaBean> getLstpersonaAsociadas() {
		return lstpersonaAsociadas;
	}
	public void setLstpersonaAsociadas(List<PersonaAsociadaBean> lstpersonaAsociadas) {
		this.lstpersonaAsociadas = lstpersonaAsociadas;
	}
	public TrabajadorBean getIdtrabajador() {
		return idtrabajador;
	}
	public void setIdtrabajador(TrabajadorBean idtrabajador) {
		this.idtrabajador = idtrabajador;
	}
	public File getFoto() {
		return foto;
	}
	public void setFoto(File foto) {
		this.foto = foto;
	}
	public String getFotoFileName() {
		return fotoFileName;
	}
	public void setFotoFileName(String fotoFileName) {
		this.fotoFileName = fotoFileName;
	}
	public String getFotoContentType() {
		return fotoContentType;
	}
	public void setFotoContentType(String fotoContentType) {
		this.fotoContentType = fotoContentType;
	}
	public byte[] getFotoBytes() {
		return fotoBytes;
	}
	public void setFotoBytes(byte[] fotoBytes) {
		this.fotoBytes = fotoBytes;
	}
	public String getIdpersonaAsociada_aux() {
		return idpersonaAsociada_aux;
	}
	public void setIdpersonaAsociada_aux(String idpersonaAsociada_aux) {
		this.idpersonaAsociada_aux = idpersonaAsociada_aux;
	}
	public PaginadorBean getPaginador() {
		return paginador;
	}
	public void setPaginador(PaginadorBean paginador) {
		this.paginador = paginador;
	}
	public InputStream getImageFoto() {
		return imageFoto;
	}
	public void setImageFoto(InputStream imageFoto) {
		this.imageFoto = imageFoto;
	}
	public byte[] getRequisito_asociarseBytes() {
		return requisito_asociarseBytes;
	}
	public void setRequisito_asociarseBytes(byte[] requisito_asociarseBytes) {
		this.requisito_asociarseBytes = requisito_asociarseBytes;
	}
	public File getRequisito_asociarse() {
		return requisito_asociarse;
	}
	public void setRequisito_asociarse(File requisito_asociarse) {
		this.requisito_asociarse = requisito_asociarse;
	}
	public String getRequisito_asociarseFileName() {
		return requisito_asociarseFileName;
	}
	public void setRequisito_asociarseFileName(String requisito_asociarseFileName) {
		this.requisito_asociarseFileName = requisito_asociarseFileName;
	}
	public String getRequisito_asociarseContentType() {
		return requisito_asociarseContentType;
	}
	public void setRequisito_asociarseContentType(
			String requisito_asociarseContentType) {
		this.requisito_asociarseContentType = requisito_asociarseContentType;
	}
	public String getMotivo_actualizacion() {
		return motivo_actualizacion;
	}
	public void setMotivo_actualizacion(String motivo_actualizacion) {
		this.motivo_actualizacion = motivo_actualizacion;
	}
	public File getImagenFirma() {
		return imagenFirma;
	}
	public void setImagenFirma(File imagenFirma) {
		this.imagenFirma = imagenFirma;
	}
	public String getImagenFirmaFileName() {
		return imagenFirmaFileName;
	}
	public void setImagenFirmaFileName(String imagenFirmaFileName) {
		this.imagenFirmaFileName = imagenFirmaFileName;
	}
	public String getImagenFirmaContentType() {
		return imagenFirmaContentType;
	}
	public void setImagenFirmaContentType(String imagenFirmaContentType) {
		this.imagenFirmaContentType = imagenFirmaContentType;
	}
	public String getImagenFirmaBytes() {
		return imagenFirmaBytes;
	}
	public void setImagenFirmaBytes(String imagenFirmaBytes) {
		this.imagenFirmaBytes = imagenFirmaBytes;
	}
	
	
}
