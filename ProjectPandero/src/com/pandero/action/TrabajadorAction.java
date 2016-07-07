package com.pandero.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.pandero.beans.RolBean;
import com.pandero.beans.TrabajadorBean;
import com.pandero.beans.UsuarioBean;
import com.pandero.service.TrabajadorService;
import com.pandero.service.TrabajadorServiceImpl;


@SuppressWarnings("serial")
@ParentPackage(value = "dawi")
public class TrabajadorAction extends ActionSupport{
	
	private TrabajadorService service=new TrabajadorServiceImpl();
	
	private static final Log log = LogFactory.getLog(TrabajadorAction.class);
	
	private List<RolBean> lstRol;
	
	private String id;
	
	private List<TrabajadorBean> lstTrabajador;
	
	private UsuarioBean usuario;
	
	private TrabajadorBean bean;
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/ModificarTrabajador", results = { @Result(location = "t_mantener_socio", name = "success", type = "tiles")})
	public String ModificarTrabajador() throws Exception{
		log.info("Info En Action RegistrarSocio");
		try {
			
			int salida=service.actualizaTrabajador(bean);
			String jsonString="";
			if(salida!=-1 && salida!=0){
				Gson gson = new Gson();
				jsonString=gson.toJson(salida);
			}
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json");
			response.getWriter().write(jsonString);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/RecuperarTrabajador", results = {@Result(name = "success", type="json")})
	public String RecuperarTrabajador() {
		try {
		
		bean=service.ObtenerTrabajador(bean.getIdtrabajador());
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(bean);
		  
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString );
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/ListarTrabajador", results = {@Result(name = "success", type="json")})
	public String ListarTrabajador() {
		try {
			
		List<TrabajadorBean> listTrabajador=new ArrayList<TrabajadorBean>();
		listTrabajador=service.listaTrabajador(bean);
		
		if(listTrabajador==null){
			listTrabajador=new ArrayList<TrabajadorBean>();			
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(listTrabajador);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "/TotalRegistrosTrabajador", results = {@Result(name = "success", type="json")})
	public String TotalRegistrosTrabajador() {
		try {
		int TotalRegistroTrabajador=0;
		
		TotalRegistroTrabajador=service.TotalRegistroTrabajador(bean);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(TotalRegistroTrabajador);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Action(value="/registraTrabajador",results={@Result(location="/trabajador.jsp",name="success")	})
	public String registraTrabajador(){
		log.info("En registra Trabajador");
		try {
			String salida="";
			salida=service.insertaTrabajador(bean);
			//variable para crear un response al ajax que llama esta Action
			HttpServletResponse response=ServletActionContext.getResponse();
			String json1="";
			if(salida!=""){
			json1= new Gson().toJson(salida);
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 	
			}				
			response.getWriter().write(json1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@Action(value="/cargaRol",results={@Result(name="success",type="json")})
	public String cargaRol(){
		try {
			
			lstRol = service.listaRol();
			//variable para crear un response al ajax que llama esta Action
			HttpServletResponse response=ServletActionContext.getResponse();
			String json1= new Gson().toJson(lstRol);
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 			
			response.getWriter().write(json1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Action(value="/eliminaTrabajador",results={@Result(name="success",location="/trabajador.jsp")})
	public String eliminaTrabajador(){
		try {
			service.eliminaTrabajador(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<RolBean> getLstRol() {
		return lstRol;
	}

	public void setLstRol(List<RolBean> lstRol) {
		this.lstRol = lstRol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public TrabajadorBean getBean() {
		return bean;
	}
	public void setBean(TrabajadorBean bean) {
		this.bean = bean;
	}
	public List<TrabajadorBean> getLstTrabajador() {
		return lstTrabajador;
	}

	public void setLstTrabajador(List<TrabajadorBean> lstTrabajador) {
		this.lstTrabajador = lstTrabajador;
	}
	
	public UsuarioBean getUsuario() {
		return usuario;
	}



	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}
}
