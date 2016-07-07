package com.pandero.beans;

import java.util.List;

public class EnlaceBean {
	private int idenlace;
	private String descripcion,icon_left,icon_right;
	private List<Sub_EnlaceBean> listsub_enlace;
	private int idusuario_axu;
	
	
	
	public int getIdusuario_axu() {
		return idusuario_axu;
	}
	public void setIdusuario_axu(int idusuario_axu) {
		this.idusuario_axu = idusuario_axu;
	}
	public int getIdenlace() {
		return idenlace;
	}
	public void setIdenlace(int idenlace) {
		this.idenlace = idenlace;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIcon_left() {
		return icon_left;
	}
	public void setIcon_left(String icon_left) {
		this.icon_left = icon_left;
	}
	public String getIcon_right() {
		return icon_right;
	}
	public void setIcon_right(String icon_right) {
		this.icon_right = icon_right;
	}
	public List<Sub_EnlaceBean> getListsub_enlace() {
		return listsub_enlace;
	}
	public void setListsub_enlace(List<Sub_EnlaceBean> listsub_enlace) {
		this.listsub_enlace = listsub_enlace;
	}
	
	
	
}
