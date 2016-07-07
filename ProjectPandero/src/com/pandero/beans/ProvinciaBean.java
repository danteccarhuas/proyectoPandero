package com.pandero.beans;


public class ProvinciaBean {
	private int idprovincia;
	private String descripcion_provincia;
	private DistritoBean iddistrito;
	
	public int getIdprovincia() {
		return idprovincia;
	}
	public void setIdprovincia(int idprovincia) {
		this.idprovincia = idprovincia;
	}
	public String getDescripcion_provincia() {
		return descripcion_provincia;
	}
	public void setDescripcion_provincia(String descripcion_provincia) {
		this.descripcion_provincia = descripcion_provincia;
	}
	public DistritoBean getIddistrito() {
		return iddistrito;
	}
	public void setIddistrito(DistritoBean iddistrito) {
		this.iddistrito = iddistrito;
	}
	
	
}
