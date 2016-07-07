package com.pandero.beans;

import java.io.File;
import java.util.List;

public class ObligacionBean {

	private String idobligaciones;
	private String fecha_registro;
	private String fecha_vencimiento;
	private int cuotas;
	private String descripcion;
	private double tasa_interes;
	private double montoTotal;
	private TipoObligacionBean tipo;
	private TrabajadorBean trabajador;
	
	//reporte
	private String Tipodescripcion,nombres,apellido_paterno;
	
	private EstadoBean estado;
	
	private String motivo_actualizacion;
	
	private SocioBean socio;
	
	private List<CuotaBean> totalCuotas;
	
	private PaginadorBean paginador;

	private File requisitos;
	private byte[] requisitosBytes;
	private String requisitosFileName;
	private String requisitosContentType;

	public String getIdobligaciones() {
		return idobligaciones;
	}

	public void setIdobligaciones(String idobligaciones) {
		this.idobligaciones = idobligaciones;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public double getTasa_interes() {
		return tasa_interes;
	}

	public void setTasa_interes(double tasa_interes) {
		this.tasa_interes = tasa_interes;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public TipoObligacionBean getTipo() {
		return tipo;
	}

	public void setTipo(TipoObligacionBean tipo) {
		this.tipo = tipo;
	}

	public TrabajadorBean getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(TrabajadorBean trabajador) {
		this.trabajador = trabajador;
	}

	public SocioBean getSocio() {
		return socio;
	}

	public void setSocio(SocioBean socio) {
		this.socio = socio;
	}

	public File getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(File requisitos) {
		this.requisitos = requisitos;
	}
	public List<CuotaBean> getTotalCuotas() {
		return totalCuotas;
	}

	public void setTotalCuotas(List<CuotaBean> totalCuotas) {
		this.totalCuotas = totalCuotas;
	}

	public PaginadorBean getPaginador() {
		return paginador;
	}

	public void setPaginador(PaginadorBean paginador) {
		this.paginador = paginador;
	}

	public String getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(String fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoBean getEstado() {
		return estado;
	}

	public void setEstado(EstadoBean estado) {
		this.estado = estado;
	}

	public String getMotivo_actualizacion() {
		return motivo_actualizacion;
	}

	public void setMotivo_actualizacion(String motivo_actualizacion) {
		this.motivo_actualizacion = motivo_actualizacion;
	}

	public String getRequisitosFileName() {
		return requisitosFileName;
	}

	public void setRequisitosFileName(String requisitosFileName) {
		this.requisitosFileName = requisitosFileName;
	}

	public String getRequisitosContentType() {
		return requisitosContentType;
	}

	public void setRequisitosContentType(String requisitosContentType) {
		this.requisitosContentType = requisitosContentType;
	}

	public byte[] getRequisitosBytes() {
		return requisitosBytes;
	}

	public void setRequisitosBytes(byte[] requisitosBytes) {
		this.requisitosBytes = requisitosBytes;
	}

	public String getTipodescripcion() {
		return Tipodescripcion;
	}

	public void setTipodescripcion(String tipodescripcion) {
		Tipodescripcion = tipodescripcion;
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
	
	
	

}
