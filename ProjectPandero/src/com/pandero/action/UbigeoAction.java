package com.pandero.action;

import java.util.ArrayList;
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
import com.pandero.beans.DepartamentoBean;
import com.pandero.beans.DistritoBean;
import com.pandero.beans.ProvinciaBean;
import com.pandero.service.UbigeoService;
import com.pandero.service.UbigeoServiceImpl;

@ParentPackage(value = "dawi")
public class UbigeoAction extends ActionSupport {

	private static final long serialVersionUID = 7968544374444173511L;
	private static final Log log = LogFactory.getLog(UbigeoAction.class);
	
	private List<DepartamentoBean> lstDepartamento = new ArrayList<DepartamentoBean>();
	private List<ProvinciaBean> lstProvincia = new ArrayList<ProvinciaBean>();
	private List<DistritoBean> lstDistrito = new ArrayList<DistritoBean>();
	

	private Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
	private UbigeoService service= new UbigeoServiceImpl();
	
	private int idvalue;

	
	@SuppressWarnings("unchecked")
	@Action(value = "/cargaDepartamento", results = { @Result(name="success",type="json") })
	public String cargaDepartamento() throws  Exception
	{  
		log.info("Dentro de carga departamento origen");
		
		lstDepartamento = service.traeDepartamentos();
		
		//variable para crear un response al ajax que llama esta Action
		HttpServletResponse response=ServletActionContext.getResponse();
		
		session.put("lstDepartamento", lstDepartamento);
		String json1= new Gson().toJson(lstDepartamento);	
		response.setContentType("application/json"); 
		response.setCharacterEncoding("utf-8"); 
		String bothJson = "["+json1+"]";				
		response.getWriter().write(bothJson);
		

		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/cargaProvincia", results = { @Result(name="success",type="json") })
	public String cargaProvincia() throws  Exception
	{  
		log.info("Provincia : " + idvalue);
		
		lstProvincia = service.traeProvincias(idvalue);
		
		//variable para crear un response al ajax que llama esta Action
		HttpServletResponse response=ServletActionContext.getResponse();
		
		session.put("lstProvincia", lstProvincia);
		String json1= new Gson().toJson(lstProvincia);	
		response.setContentType("application/json"); 
		response.setCharacterEncoding("utf-8"); 
		String bothJson = "["+json1+"]";				
		response.getWriter().write(bothJson);
		

		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "/cargaDistrito", results = { @Result(name="success",type="json") })
	public String cargaDistrito() throws  Exception
	{  
		log.info("Provincia : " + idvalue);
		
		lstDistrito = service.traeDistrito(idvalue);
		
		//variable para crear un response al ajax que llama esta Action
		HttpServletResponse response=ServletActionContext.getResponse();
		session.put("lstDistrito", lstDistrito);
		String json1= new Gson().toJson(lstDistrito);	
		response.setContentType("application/json"); 
		response.setCharacterEncoding("utf-8"); 
		String bothJson = "["+json1+"]";				
		response.getWriter().write(bothJson);
		

		return null;
	}
	
	
	
	public List<DepartamentoBean> getLstDepartamento() {
		return lstDepartamento;
	}


	public void setLstDepartamento(List<DepartamentoBean> lstDepartamento) {
		this.lstDepartamento = lstDepartamento;
	}


	public List<ProvinciaBean> getLstProvincia() {
		return lstProvincia;
	}


	public void setLstProvincia(List<ProvinciaBean> lstProvincia) {
		this.lstProvincia = lstProvincia;
	}


	public List<DistritoBean> getLstDistrito() {
		return lstDistrito;
	}


	public void setLstDistrito(List<DistritoBean> lstDistrito) {
		this.lstDistrito = lstDistrito;
	}


	public int getIdvalue() {
		return idvalue;
	}


	public void setIdvalue(int idvalue) {
		this.idvalue = idvalue;
	}
	
	
}
