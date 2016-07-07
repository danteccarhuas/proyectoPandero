package com.pandero.beans;

public class PaginadorBean {
	private int limit;
	private int offset;
	private int TotalRegistro;
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotalRegistro() {
		return TotalRegistro;
	}
	public void setTotalRegistro(int totalRegistro) {
		TotalRegistro = totalRegistro;
	}
	
}
