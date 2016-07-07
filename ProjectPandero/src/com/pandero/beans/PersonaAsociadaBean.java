package com.pandero.beans;

public class PersonaAsociadaBean {
	private String idpersonaAsociada;  
	private String nombres;
	private String apellido_paterno;
	private String apellido_materno,documento;
	private TipoDocumentoBean tipo_documento;
	private ParentescoBean parentesco;
	private int idCorrelativo;
	private String idsocio_aux;
	private int valida_document;
	
	
	
	public int getValida_document() {
		return valida_document;
	}
	public void setValida_document(int valida_document) {
		this.valida_document = valida_document;
	}
	public String getIdpersonaAsociada() {
		return idpersonaAsociada;
	}
	public void setIdpersonaAsociada(String idpersonaAsociada) {
		this.idpersonaAsociada = idpersonaAsociada;
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
	public TipoDocumentoBean getTipo_documento() {
		return tipo_documento;
	}
	public void setTipo_documento(TipoDocumentoBean tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	public ParentescoBean getParentesco() {
		return parentesco;
	}
	public void setParentesco(ParentescoBean parentesco) {
		this.parentesco = parentesco;
	}
	public int getIdCorrelativo() {
		return idCorrelativo;
	}
	public void setIdCorrelativo(int idCorrelativo) {
		this.idCorrelativo = idCorrelativo;
	}
	public String getIdsocio_aux() {
		return idsocio_aux;
	}
	public void setIdsocio_aux(String idsocio_aux) {
		this.idsocio_aux = idsocio_aux;
	}
	
	

}
