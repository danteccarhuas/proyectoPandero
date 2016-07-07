package com.pandero.beans;

public class DetalleBean {

	private int idfactura,idcuota;
	private double monto,mora;
	private String fecha_vencimiento;
	
	
	public int getIdfactura() {
		return idfactura;
	}
	public void setIdfactura(int idfactura) {
		this.idfactura = idfactura;
	}
	public int getIdcuota() {
		return idcuota;
	}
	public void setIdcuota(int idcuota) {
		this.idcuota = idcuota;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public double getMora() {
		return mora;
	}
	public void setMora(double mora) {
		this.mora = mora;
	}
	public String getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	public void setFecha_vencimiento(String fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
}
