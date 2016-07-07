package com.pandero.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.pandero.beans.ObligacionBean;
import com.pandero.beans.SocioBean;
import com.pandero.service.ReporteService;
import com.pandero.service.ReporteServiceImpl;

@SuppressWarnings("serial")
@ParentPackage(value = "dawi1")
public class ReporteAction  extends ActionSupport {
	
			private String estado;
			private String idPais;			
			private String idtipoObligacion;
			private List<SocioBean> lstSocios;			
			private List<ObligacionBean> lstOblicaciones;
			private List<ObligacionBean> lstOblicaciones2;
			private ReporteService reporteService = new ReporteServiceImpl();
			 
	//reporte 1
	@Action(value="/reporteSociosPais",results={
			@Result(name="success",type="jasper", params={
					"location","/reportes/ReporteSocioPorPais.jasper",
					"dataSource","lstSocios",
					"format","PDF",
			})
	})
	 
	public String listaSocios(){
		try {
			System.out.println("lleuge "+ idPais);
	lstSocios = reporteService.consultaSocios(idPais);			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return SUCCESS;
	}	
	
	@Action(value="/ReporteObligacionPorTipo",results={
			@Result(name="success",type="jasper", params={
					"location","/reportes/ReporteObligacionPorTipo.jasper",
					"dataSource","lstOblicaciones",
					"format","PDF",
			})
	})
	
	public String listaObligaciones(){
		try {
			System.out.println("lleuge "+ idtipoObligacion);
			lstOblicaciones = reporteService.listaObligacionPorTipo(idtipoObligacion);						
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return SUCCESS;
	}

	
	
	
	
	
	

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	public List<SocioBean> getLstSocios() {
		return lstSocios;
	}
	public void setLstSocios(List<SocioBean> lstSocios) {
		this.lstSocios = lstSocios;
	}






	public String getIdtipoObligacion() {
		return idtipoObligacion;
	}

	public void setIdtipoObligacion(String idtipoObligacion) {
		this.idtipoObligacion = idtipoObligacion;
	}

	public List<ObligacionBean> getLstOblicaciones() {
		return lstOblicaciones;
	}



	public void setLstOblicaciones(List<ObligacionBean> lstOblicaciones) {
		this.lstOblicaciones = lstOblicaciones;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<ObligacionBean> getLstOblicaciones2() {
		return lstOblicaciones2;
	}

	public void setLstOblicaciones2(List<ObligacionBean> lstOblicaciones2) {
		this.lstOblicaciones2 = lstOblicaciones2;
	}
	
	
	
	
}
