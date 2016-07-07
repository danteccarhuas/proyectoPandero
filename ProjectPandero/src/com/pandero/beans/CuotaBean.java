package com.pandero.beans;

public class CuotaBean {

	private int idcorrelativo;
	private int idcuota;
	private String fechaPago;
	private double monto;
	private String idObligacionAux;
	private EstadoBean estado;
	private Mora mora;

	
	public Mora getMora() {
		return mora;
	}
	public void setMora(Mora mora) {
		this.mora = mora;
	}
	public int getIdcuota() {
		return idcuota;
	}

	public void setIdcuota(int idcuota) {
		this.idcuota = idcuota;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}


	public String getIdObligacionAux() {
		return idObligacionAux;
	}

	public void setIdObligacionAux(String idObligacionAux) {
		this.idObligacionAux = idObligacionAux;
	}

	public int getIdcorrelativo() {
		return idcorrelativo;
	}

	public void setIdcorrelativo(int idcorrelativo) {
		this.idcorrelativo = idcorrelativo;
	}

	public EstadoBean getEstado() {
		return estado;
	}

	public void setEstado(EstadoBean estado) {
		this.estado = estado;
	}

}
