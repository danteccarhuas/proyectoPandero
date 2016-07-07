package com.pandero.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pandero.beans.EnlaceBean;
import com.pandero.beans.TrabajadorBean;
import com.pandero.beans.UsuarioBean;
import com.pandero.service.UsuarioService;
import com.pandero.service.UsuarioServiceImpl;
@ParentPackage(value = "dawi")
public class LoginAction extends ActionSupport {
	
	private String login, password;
	
	private static final long serialVersionUID = 1L;
	
	private UsuarioService service = new UsuarioServiceImpl();
	
	//Creacion de Session
	private Map<String, Object> session=(Map<String, Object>)ActionContext.getContext().getSession();
	@SuppressWarnings("rawtypes")
	private SessionMap sessionLogout = (SessionMap)ActionContext.getContext().getSession();
	
	@SuppressWarnings("unused")
	@Action(value = "/login", results = { 	@Result(location = "t_menu_intranet", 	name = "success", 	type = "tiles"),
			  								@Result(location = "t_login", 		name = "login", 	type = "tiles")})
	public String login() throws Exception {
		String valor_retorno="";
		
		UsuarioBean idusuario=new UsuarioBean();
		idusuario.setLogin(login);
		idusuario.setPassword(password);
		
		TrabajadorBean bean=new TrabajadorBean();
		bean.setIdusuario(idusuario);
		
		bean=service.login(bean);
		
		if(bean!=null){
			idusuario.setIdusuario(bean.getIdusuario().getIdusuario());
			bean.setIdusuario(idusuario);
			session.put("TrabajadorLogueado", bean);
			
			List<EnlaceBean> lst_Enlace=service.traerEnlacesDeUsuario(bean);
			session.put("objUsuarioMenus", lst_Enlace);
			session.put("objUsuarioLogueado", bean);
			valor_retorno="success";
		}else if(bean==null){
			int mensaje = 1;
			session.put("validaLogin", mensaje);
			valor_retorno="login";
		}
	return valor_retorno;	
	}
	
	@Action(value = "/a_login", results = {
			@Result(location = "t_login", name = "success", type = "tiles")
			})
	public String logout() throws Exception {
		sessionLogout.invalidate();
		//session.remove("validaLogin");
		return SUCCESS;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
