package com.pandero.beans;


public class DepartamentoBean {
	private int iddepartamento;
	private String descripcion_departamento;
	private ProvinciaBean idprovincia; 
	
	public int getIddepartamento() {
		return iddepartamento;
	}
	public void setIddepartamento(int iddepartamento) {
		this.iddepartamento = iddepartamento;
	}
	public String getDescripcion_departamento() {
		return descripcion_departamento;
	}
	public void setDescripcion_departamento(String descripcion_departamento) {
		this.descripcion_departamento = descripcion_departamento;
	}
	public ProvinciaBean getIdprovincia() {
		return idprovincia;
	}
	public void setIdprovincia(ProvinciaBean idprovincia) {
		this.idprovincia = idprovincia;
	}
	
	
	
}
