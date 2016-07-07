package com.pandero.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pandero.beans.PaisBean;
import com.pandero.beans.ParentescoBean;
import com.pandero.beans.TipoDocumentoBean;
import com.pandero.beans.TipoObligacionBean;
import com.pandero.service.ReporteService;
import com.pandero.service.ReporteServiceImpl;
import com.pandero.service.SocioService;
import com.pandero.service.SocioServiceImpl;
@SuppressWarnings("serial")
@ParentPackage(value="dawi1")

/* 
 *  type="json"
 *  La accion va retornar un archivo plano
 *  de formato json con la data del arraylist
 * 
 */

public class ComboActionReporte extends ActionSupport{

	private List<TipoDocumentoBean> lstTipoDocumento;
	private List<PaisBean> lstPais ;
	private List<TipoObligacionBean> lstTipoObligacion;
	private List<ParentescoBean> lstParentesco ;
	private ReporteService serviceReport = new ReporteServiceImpl();
	private static final long serialVersionUID = 7968544374444173511L;
	private static final Log log = LogFactory.getLog(LoginAction.class);
	
	private Map<String, Object> session=ActionContext.getContext().getSession();
	
	//por defecto(por documentacion) en el filtro
	
	
	
	
	@Action(value = "/cargaTipoObligacion")
	public String cargaTipoObligacion() {
		System.out.println("Carga combo cargaTipoObligacion");
		try {
			lstTipoObligacion = serviceReport.listaTipoObligacion();
			//variable para crear un response al ajax que llama esta Action
			HttpServletResponse response=ServletActionContext.getResponse();
			
			String bothJson= new Gson().toJson(lstTipoObligacion);
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 				
			response.getWriter().write(bothJson);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		System.out.println("Carga combo fin");
		return null;		
	}
	
	@Action(value = "/cargaPais")
	public String cargaPais() {
		System.out.println("Carga combo cargaPais");
		try {
			lstPais = serviceReport.listPais();
			//variable para crear un response al ajax que llama esta Action
			HttpServletResponse response=ServletActionContext.getResponse();
			
			String bothJson= new Gson().toJson(lstPais);
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 				
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	

	/*
	@Action(value="/cargaCombos",	results={@Result(name="success", type="json")})
	public String cargaCombos(){
		log.info("En ComboAction");
		
		SocioService service = new SocioServiceImpl();
		try {
			lstTipoDocumento = service.listaTipoDocumento();
			session.put("ListTipoDocmentos", lstTipoDocumento);
			
		//lstPais= service.listaPais();
			
			lstParentesco=service.listaParentesco();
			session.put("ListParentesco", lstParentesco);

			//variable para crear un response al ajax que llama esta Action
			HttpServletResponse response=ServletActionContext.getResponse();
			
			String json1= new Gson().toJson(lstTipoDocumento);
			String json2= new Gson().toJson(lstPais);
			String json3= new Gson().toJson(lstParentesco);
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json1+","+json2+","+json3+"]";				
			response.getWriter().write(bothJson);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Log getLog() {
		return log;
	}

	public List<TipoDocumentoBean> getLstTipoDocumento() {
		return lstTipoDocumento;
	}


	public void setLstTipoDocumento(List<TipoDocumentoBean> lstTipoDocumento) {
		this.lstTipoDocumento = lstTipoDocumento;
	}




	public List<ParentescoBean> getLstParentesco() {
		return lstParentesco;
	}




	public void setLstParentesco(List<ParentescoBean> lstParentesco) {
		this.lstParentesco = lstParentesco;
	}


	public List<TipoObligacionBean> getLstTipoObligacion() {
		return lstTipoObligacion;
	}


	public void setLstTipoObligacion(List<TipoObligacionBean> lstTipoObligacion) {
		this.lstTipoObligacion = lstTipoObligacion;
	}
	
	

}
