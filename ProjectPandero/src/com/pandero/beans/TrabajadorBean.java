package com.pandero.beans;

public class TrabajadorBean {
	private String idtrabajador;
	private String nombres,apellidos,DNI;
	private UsuarioBean idusuario;
	private RolBean rol;
	private PaginadorBean paginador;

		
	
	public PaginadorBean getPaginador() {
		return paginador;
	}
	public void setPaginador(PaginadorBean paginador) {
		this.paginador = paginador;
	}
	public RolBean getRol() {
		return rol;
	}
	public void setRol(RolBean rol) {
		this.rol = rol;
	}
	public String getIdtrabajador() {
		return idtrabajador;
	}
	public void setIdtrabajador(String idtrabajador) {
		this.idtrabajador = idtrabajador;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public UsuarioBean getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(UsuarioBean idusuario) {
		this.idusuario = idusuario;
	}
	
	
}
