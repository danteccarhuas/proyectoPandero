package com.pandero.beans;

public class FacturaBean {

	private int idfactura;
	private double Total;
	private String fecha_registro;
	private ObligacionBean obligacion;
	private String dniPagador,nombresPagador;
	
	public double getTotal() {
		return Total;
	}
	public void setTotal(double total) {
		Total = total;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getDniPagador() {
		return dniPagador;
	}
	public void setDniPagador(String dniPagador) {
		this.dniPagador = dniPagador;
	}
	public String getNombresPagador() {
		return nombresPagador;
	}
	public void setNombresPagador(String nombresPagador) {
		this.nombresPagador = nombresPagador;
	}
	public int getIdfactura() {
		return idfactura;
	}
	public void setIdfactura(int idfactura) {
		this.idfactura = idfactura;
	}
	public ObligacionBean getObligacion() {
		return obligacion;
	}
	public void setObligacion(ObligacionBean obligacion) {
		this.obligacion = obligacion;
	}

}
