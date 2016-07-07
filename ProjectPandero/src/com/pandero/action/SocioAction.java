package com.pandero.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.Gson;
import com.onbarcode.barcode.Codabar;
import com.onbarcode.barcode.QRCode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pandero.beans.DepartamentoBean;
import com.pandero.beans.DireccionBean;
import com.pandero.beans.DistritoBean;
import com.pandero.beans.PaginadorBean;
import com.pandero.beans.PaisBean;
import com.pandero.beans.ParentescoBean;
import com.pandero.beans.PersonaAsociadaBean;
import com.pandero.beans.ProvinciaBean;
import com.pandero.beans.SocioBean;
import com.pandero.beans.TelefonoBean;
import com.pandero.beans.TipoDocumentoBean;
import com.pandero.beans.TrabajadorBean;
import com.pandero.beans.UbigeoBean;
import com.pandero.beans.UsuarioBean;
import com.pandero.service.SocioService;
import com.pandero.service.SocioServiceImpl;
import com.pandero.util.Constantes;
@ParentPackage(value = "dawi")
public class SocioAction extends ActionSupport {

	//objeto que permita debbug en consola(permite hacer seguimiento de codigo)
	private static final Log log = LogFactory.getLog(SocioAction.class);

	// crear una sesion
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	private static final long serialVersionUID = 1L;
	
	//propiedades de Persona Asociada
	private int idpersonaAsociada;
	private String nombres;
	private String apellido_paterno;
	private String apellido_materno;
	private String documento;
	private int idtipodocumento;
	private int idparentesco;
	
	//propiedades de Socio
	private String correo,direccion;
	private byte[] barcode;
	private byte[] qrcode;
	private int iddepartamento,idprovincia,iddistrito,idPais;
	private String telefono;
	private String idsocio;
	private String motivo_actualizacion;
	private SocioBean socio;
	
	/*Imagen foto y firma que viene del cliente*/
	private String imagen;
	private String imgFirma;
	
	//propiedad para validar si agrega o modifica telefono, direccion
	private int agrega_edita_telefono;
	private int agrega_edita_direccion;
	
	//IdCorrelavitvo de telefono y direccion
	private int idcorrelativoTelefono;
	private int idcorrelativoDireccion;
	
	//Almacena PersonaAsociada para ser guardada en la session
	static private List<PersonaAsociadaBean> data=new ArrayList<PersonaAsociadaBean>();
	
	//Almacena Telefono para ser guardada en la session
	static private List<TelefonoBean> lstTelefono=new ArrayList<TelefonoBean>();
	
	//Almacena Direccion para ser guardada en la session
	static private List<DireccionBean> lstDireccion=new ArrayList<DireccionBean>();
	
	//Clase para recibir foto que viene de la bd y que se envian al browser
	private InputStream imagenFoto;
	private InputStream imagenHuella;
	private InputStream imagenFirma;
	
	private InputStream descargarHuella;
	private InputStream descargarFirma;
	private InputStream descargarReqAsociarse;
	
	/*Los nombre que tomaran al descargar los imagenes y el doc requerimientoAsociarse*/
	private String huellafileName;
	private String firmafileName;
	private String reqAsociarsefileName;
	
	//Paginador
	private PaginadorBean paginador;
	
	/*valida documento modal*/
	private int valida_document;
	
	//Instanciamos el SocioServiceImpl
	SocioService service=new SocioServiceImpl();
	
	@SuppressWarnings("unchecked")
	@Action(value = "/DarBajasSocio")
	public String DarBajasSocio() {
		try {
		
		int salida=service.DarBajaSocio(idsocio);
		
		Gson gson = new Gson();
		String jsonString="";
		if(salida==1){
			jsonString= gson.toJson(1);	
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "/EliminarListadoEnSessiones", results = { @Result(location = "t_mantener_socio", name = "success", type = "tiles")})
	public String EliminarListadoEnSessiones() throws Exception{
		log.info("Info En Action EliminarListadoEnSessiones");
		try {
			lstDireccion = (ArrayList<DireccionBean>) session.get("direccionesSocio");
			if(lstDireccion==null){
				lstDireccion=new ArrayList<DireccionBean>();
			}else if(lstDireccion.size()>0){
				lstDireccion.clear();
			}
			session.put("direccionesSocio", lstDireccion);		
			
			lstTelefono = (ArrayList<TelefonoBean>) session.get("telefonosSocio");
			if(lstTelefono==null){
				lstTelefono=new ArrayList<TelefonoBean>();
			}else if(lstTelefono.size()>0){
				lstTelefono.clear();
			}
			
			session.put("telefonosSocio", lstTelefono);
			
			data = (ArrayList<PersonaAsociadaBean>) session.get("detallePersonaAsociada");
			if(data==null){
				data=new ArrayList<PersonaAsociadaBean>();
			}else if(data.size()>0){
				data.clear();
			}
			session.put("direccionesSocio", data);
			
			String jsonString="";
			if(lstDireccion.size()==0 && lstTelefono.size()==0 && data.size()==0){
				Gson gson = new Gson();
				jsonString=gson.toJson(1);
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
	@Action(value = "/ModificarSocio")
	public String ModificarSocio() throws Exception{
		log.info("Info En Action ModificarSocio");
		try {
			SocioBean bean=new SocioBean();
			bean.setIdsocio(idsocio);
			bean.setMotivo_actualizacion(motivo_actualizacion);
			List<TelefonoBean> lstTelefono=(List<TelefonoBean>)session.get("telefonosSocio");
			List<DireccionBean> lstDirecciones=(List<DireccionBean>)session.get("direccionesSocio");
			List<PersonaAsociadaBean> lstpersonaAsociadas=(List<PersonaAsociadaBean>)session.get("detallePersonaAsociada");
			
			bean.setTelefonos(lstTelefono);
			bean.setDirecciones(lstDirecciones);
			bean.setLstpersonaAsociadas(lstpersonaAsociadas);
			
			
			for (int i = 0; i < lstTelefono.size(); i++) {
				lstTelefono.get(i).setIdSocioaux(bean.getIdsocio());
			}
			
			for (int e = 0; e < lstDirecciones.size(); e++) {
				lstDirecciones.get(e).setIdSocioaux(bean.getIdsocio());
			}
			
			for (int f = 0; f < lstpersonaAsociadas.size(); f++) {
				lstpersonaAsociadas.get(f).setIdsocio_aux(bean.getIdsocio());
			}
			
			int salida=service.ActualizaSocio(bean);
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
		
	
	
	//inputName->ES el objeto donde se envia los bytes
	// al browser
	@Action(value = "/descargarReqAsociarse", 
			results = { @Result(
							params={"contentType", 
									"${extension}", 
									"inputName",
									"descargarReqAsociarse",    
									"contentDisposition",
									"attachment; filename=\"${reqAsociarsefileName}\"", 
									"bufferSize", "1024" 
									}, 
							name = "success", type="stream") })
	public String descargarReqAsociarse() throws Exception {
		try {
			
			SocioBean bean=new SocioBean();
			bean=service.ObtenerReqAsociarsePorPK(idsocio);
			
			descargarReqAsociarse = new ByteArrayInputStream(bean.getRequisito_asociarseBytes());
			reqAsociarsefileName = bean.getRequisito_asociarseFileName();
			 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//inputName->ES el objeto donde se envia los bytes
	// al browser
	@Action(value = "/descargarFirma", 
			results = { @Result(
							params={"contentType", 
									"${extension}", 
									"inputName",
									"descargarFirma",    
									"contentDisposition",
									"attachment; filename=\"${firmafileName}\"", 
									"bufferSize", "1024" 
									}, 
							name = "success", type="stream") })
	public String descargarFirma() throws Exception {
		try {
			
			SocioBean bean=new SocioBean();
			bean=service.ObtenerFirmaPorPK(idsocio);
			
			descargarFirma = new ByteArrayInputStream(bean.getFirmaBytes());
			firmafileName = bean.getFirmaFileName();
			 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	//inputName->ES el objeto donde se envia los bytes
	// al browser
	@Action(value = "/descargarHuella", 
			results = { @Result(
							params={"contentType", 
									"${extension}", 
									"inputName",
									"descargarHuella",    
									"contentDisposition",
									"attachment; filename=\"${huellafileName}\"", 
									"bufferSize", "1024" 
									}, 
							name = "success", type="stream") })
	public String descargarHuella() throws Exception {
		try {
			
			SocioBean bean=new SocioBean();
			bean=service.ObtenerHuellaPorPK(idsocio);
			
			descargarHuella = new ByteArrayInputStream(bean.getHuellaBytes());
			huellafileName = bean.getHuellaFileName();
			 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	//type="stream" --> Se envia un trama de bytes
	//"inputName","imagenData" -->El objeto que tiene la trama de bytes

	@Action(value = "/verFirma", 
			results = { @Result(
							params={"inputName","imagenFirma"}, 
							name = "success", type="stream") })
	public String verFirma() throws Exception {
		try {
			
			SocioBean bean=new SocioBean();
			bean=service.ObtenerFirmaPorPK(idsocio);
			imagenFirma = new ByteArrayInputStream(bean.getFirmaBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	//type="stream" --> Se envia un trama de bytes
	//"inputName","imagenData" -->El objeto que tiene la trama de bytes

	@Action(value = "/verHuella", 
			results = { @Result(
							params={"inputName","imagenHuella"}, 
							name = "success", type="stream") })
	public String verHuella() throws Exception {
		try {
			
			SocioBean bean=new SocioBean();
			bean=service.ObtenerHuellaPorPK(idsocio);
			imagenHuella = new ByteArrayInputStream(bean.getHuellaBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/RecuperarSocio")
	public String RecuperarSocio() {
		try {
		SocioBean bean=new SocioBean();
		
		bean=service.ObtenerSocio(idsocio);
		
		/*Verificamos si esta instanciado el arreglo sino lo instanciamos*/
		if(lstTelefono.size()>0){
			lstTelefono.clear();
		}
		
		/*Primero Agregar los idCorrelativo a cada telefono para modidicar*/
		for (int e = 0; e < bean.getTelefonos().size(); e++) {
			int codigo = generaCorrelativo(2);
			codigo++;
			bean.getTelefonos().get(e).setIdcorrelativo(codigo);
			/*Agregamos los telefonos al arreglo*/
			lstTelefono.add(bean.getTelefonos().get(e));
		}
		
		//agregamos a la session
		session.put("telefonosSocio", lstTelefono);
		
		/*Verificamos si esta instanciado el arreglo sino lo instanciamos*/
		if(lstDireccion.size()>0){
			lstDireccion.clear();
		}
		/*Primero Agregar los idCorrelativo a cada direccion para modidicar*/
		for (int f = 0; f < bean.getDirecciones().size(); f++) {
			int codigo = generaCorrelativo(3);
			codigo++;
			bean.getDirecciones().get(f).setIdcorrelativo(codigo);
			/*Agregamos las direcciones al arreglo*/
			lstDireccion.add(bean.getDirecciones().get(f));
		}
		
		//agregamos a la session
		session.put("direccionesSocio", lstDireccion);
		
		/*Verificamos si esta instanciado el arreglo sino lo instanciamos*/
		if(data.size()>0){
			data.clear();
		}
		
		/*Primero Agregar los idCorrelativo a personaAsociada para modidicar*/
		for (int i = 0; i < bean.getLstpersonaAsociadas().size(); i++) {
			int codigo = generaCorrelativo(4);
			codigo++;
			bean.getLstpersonaAsociadas().get(i).setIdCorrelativo(codigo);
			/*Ponemos en Memory las personasAsociadas*/
			data.add(bean.getLstpersonaAsociadas().get(i));
		}
		
		//agregamos a la session
		session.put("detallePersonaAsociada", data);
		
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
	
	
	//type="stream" --> Se envia un trama de bytes
	//"inputName","imagenData" -->El objeto que tiene la trama de bytes

	@Action(value = "/verFoto", 
			results = { @Result(
							params={"inputName","imagenFoto"}, 
							name = "success", type="stream") })
	public String verFoto() throws Exception {
		try {
			
			SocioBean bean=new SocioBean();
			bean=service.ObtenerImagenPorPK(idsocio);
			imagenFoto = new ByteArrayInputStream(bean.getFotoBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "/TotalRegistrosSocio", results = {@Result(name = "success", type="json")})
	public String TotalRegistrosSocio() {
		try {
		int TotalRegistroSocio=0;
		SocioBean bean=new SocioBean();
		bean.setIdsocio(idsocio);
		bean.setNombres(nombres);
		bean.setDocumento(documento);
		
		//List<SocioBean> listSocio=new ArrayList<SocioBean>();
		TotalRegistroSocio=service.TotalRegistroSocio(bean);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(TotalRegistroSocio);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "/ListarSocios", results = {@Result(name = "success", type="json")})
	public String ListarSocios() {
		try {
		
		SocioBean bean=new SocioBean();
		bean.setIdsocio(idsocio);
		bean.setNombres(nombres);
		bean.setDocumento(documento);
		bean.setPaginador(paginador);
		
		List<SocioBean> listSocio=new ArrayList<SocioBean>();
		listSocio=service.listaSocio(bean);
		
		if(listSocio==null){
			listSocio=new ArrayList<SocioBean>();			
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(listSocio);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/Agrega_Modifica_Direccion", results = { @Result(location = "t_mantener_socio", name = "success", type = "tiles"),
			  @Result(location = "t_login", name = "login", type = "tiles")})
	public String Agrega_Modifica_Direccion() throws Exception{
		try {
			DireccionBean bean=new DireccionBean();
			if(lstDireccion==null){
				lstDireccion=new ArrayList<DireccionBean>();
			}
		
		if(agrega_edita_direccion==0){
			int codigo = generaCorrelativo(3);
			codigo++;
			bean.setIdcorrelativo(codigo);
			bean.setDireccion(direccion);
			lstDireccion.add(bean);
			
		}else if(agrega_edita_direccion==1){
			agrega_edita_direccion=0;
			bean.setIdcorrelativo(idcorrelativoDireccion);
			bean.setDireccion(direccion);
			
			lstDireccion= (ArrayList<DireccionBean>) session.get("direccionesSocio");
			
			for (DireccionBean obj : lstDireccion) {
				if(obj.getIdcorrelativo()==bean.getIdcorrelativo()){
					bean.setIddireccion_socio(obj.getIddireccion_socio());
					lstDireccion.set(lstDireccion.indexOf(obj), bean);
					break;
				}
				
			}
		}
		
		session.put("direccionesSocio", lstDireccion);

		Gson gson = new Gson();
		String jsonString = gson.toJson(agrega_edita_direccion);
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("application/json");
		response.getWriter().write(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/listDirecciones", results = {@Result(name = "success", type="json")})
	public String listDirecciones() {
		try {
		List<DireccionBean> datalist = (ArrayList<DireccionBean>)session.get("direccionesSocio");
		
		if(datalist==null){
			datalist=new ArrayList<DireccionBean>();			
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(datalist);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "/RecuperarDireccion", results = {@Result(name = "success", type="json")})
	public String RecuperarDireccion() {
		try {
		List<DireccionBean> datalist = (ArrayList<DireccionBean>) session.get("direccionesSocio");
		
		DireccionBean bean=new DireccionBean();
		
		for (DireccionBean obj : datalist) {
			if(obj.getIdcorrelativo()==idcorrelativoDireccion){
				bean.setIdcorrelativo(idcorrelativoDireccion);
				bean.setDireccion(obj.getDireccion());
				
				break;
			}
			
		}
		
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
	@Action(value = "/EliminarDireccion", results = {@Result(name = "success", type="json")})
	public String EliminarDireccion() {
		try {
		lstDireccion = (ArrayList<DireccionBean>) session.get("direccionesSocio");
			
		Iterator<DireccionBean> it = lstDireccion.iterator();
		while (it.hasNext()) {
			DireccionBean user = it.next();
			if(user.getIddireccion_socio()!=0){
				user.setIdSocioaux(idsocio);
				service.EliminarDireccionSocio(user);
				it.remove();
				break;
			}else{
			  if (user.getIdcorrelativo()==idcorrelativoDireccion) {
				    it.remove();
				    break;
				  }	
			}
		}
		
		session.put("direccionesSocio", lstDireccion);
		Gson gson = new Gson();
		String jsonString = gson.toJson(1);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/EliminarTelefono", results = {@Result(name = "success", type="json")})
	public String EliminarTelefono() {
		try {
		lstTelefono = (ArrayList<TelefonoBean>) session.get("telefonosSocio");
		
		Iterator<TelefonoBean> it = lstTelefono.iterator();
		while (it.hasNext()) {
			TelefonoBean user = it.next();
			if(user.getIdtelefono_socio()!=0){
				user.setIdSocioaux(idsocio);
				service.EliminarTelefonoSocio(user);
				it.remove();
				break;
			}else{
			  if (user.getIdcorrelativo()==idcorrelativoTelefono) {
				    it.remove();
				    break;	
			}
		  }
		}
		
		session.put("telefonosSocio", lstTelefono);
		Gson gson = new Gson();
		String jsonString = gson.toJson(1);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/RecuperarTelefono", results = {@Result(name = "success", type="json")})
	public String RecuperarTelefono() {
		try {
		List<TelefonoBean> dataTelef = (ArrayList<TelefonoBean>) session.get("telefonosSocio");
		
		TelefonoBean bean=new TelefonoBean();
		
		for (TelefonoBean obj : dataTelef) {
			if(obj.getIdcorrelativo()==idcorrelativoTelefono){
				bean.setIdcorrelativo(idcorrelativoTelefono);
				bean.setTelefono(obj.getTelefono());
				break;
			}
			
		}
		
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
	@Action(value = "/Agrega_Modifica_Telefono", results = { @Result(location = "t_mantener_socio", name = "success", type = "tiles")})
	public String Agrega_Modifica_Telefono() throws Exception{
		try {
		TelefonoBean bean=new TelefonoBean();
		
		if(agrega_edita_telefono==0){
			int codigo = generaCorrelativo(2);
			codigo++;
			bean.setIdcorrelativo(codigo);
			bean.setTelefono(telefono);
			lstTelefono.add(bean);
			
		}else if(agrega_edita_telefono==1){
			agrega_edita_telefono=0;
			bean.setIdcorrelativo(idcorrelativoTelefono);
			System.out.println(idcorrelativoTelefono);
			bean.setTelefono(telefono);
			
			lstTelefono= (ArrayList<TelefonoBean>) session.get("telefonosSocio");
			
			for (TelefonoBean obj : lstTelefono) {
				if(obj.getIdcorrelativo()==bean.getIdcorrelativo()){
					bean.setIdtelefono_socio(obj.getIdtelefono_socio());
					lstTelefono.set(lstTelefono.indexOf(obj), bean);
					break;
				}
				
			}
		}
		
		session.put("telefonosSocio", lstTelefono);

		Gson gson = new Gson();
		String jsonString = gson.toJson(agrega_edita_telefono);
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("application/json");
		response.getWriter().write(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "/listTelefonos", results = {@Result(name = "success", type="json")})
	public String listTelefonos() {
		try {
		List<TelefonoBean> dataTelef = (ArrayList<TelefonoBean>)session.get("telefonosSocio");
		
		if(dataTelef==null){
			dataTelef=new ArrayList<TelefonoBean>();			
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(dataTelef);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/RegistrarSocio")
	public String RegistrarSocio() throws Exception{
		log.info("Info En Action RegistrarSocio");
		try {
		socio.setHuellaBytes(Constantes.getBytesFromFile(socio.getHuella()));
		List<DepartamentoBean> lstDepartamento = new ArrayList<DepartamentoBean>();
		lstDepartamento=(List<DepartamentoBean>)session.get("lstDepartamento");

		List<ProvinciaBean> lstProvincia = new ArrayList<ProvinciaBean>();
		lstProvincia=(List<ProvinciaBean>)session.get("lstProvincia");

		List<DistritoBean> lstDistrito = new ArrayList<DistritoBean>();
		lstDistrito=(List<DistritoBean>)session.get("lstDistrito");
			
		SocioBean bean=new SocioBean();
		bean.setNombres(nombres);
		bean.setApellido_paterno(apellido_paterno);
		bean.setApellido_materno(apellido_materno);
		bean.setDocumento(documento);
		bean.setCorreo(correo);
		
		PaisBean beanPais=new PaisBean();
		beanPais.setIdPais(idPais);
		bean.setIdPais(beanPais);
		
		TipoDocumentoBean idtipoDocumento=new TipoDocumentoBean();
		idtipoDocumento.setIdtipodocumento(idtipodocumento);
		bean.setIdtipoDocumento(idtipoDocumento);

		UbigeoBean idubigeo=new UbigeoBean();
		DepartamentoBean beanDepartamento=new DepartamentoBean();
		beanDepartamento.setIddepartamento(iddepartamento);
		
		for (DepartamentoBean departamentoBean : lstDepartamento) {
			if(departamentoBean.getIddepartamento()==beanDepartamento.getIddepartamento()){
				beanDepartamento.setDescripcion_departamento(departamentoBean.getDescripcion_departamento());
				break;
			}
		}
		
		idubigeo.setIddepartamento(beanDepartamento);
		
		ProvinciaBean beanProvincia=new ProvinciaBean();
		beanProvincia.setIdprovincia(idprovincia);
		
		for (ProvinciaBean provinciaBean : lstProvincia) {
			if(provinciaBean.getIdprovincia()==beanProvincia.getIdprovincia()){
				beanProvincia.setDescripcion_provincia(provinciaBean.getDescripcion_provincia());
				break;
			}
		}
		
		idubigeo.setIdprovincia(beanProvincia);
		
		DistritoBean beanDistrito=new DistritoBean();
		beanDistrito.setIddistrito(iddistrito);
		
		for (DistritoBean distritoBean : lstDistrito) {
			if(distritoBean.getIddistrito()==beanDistrito.getIddistrito()){
				beanDistrito.setDescripcion_distrito(distritoBean.getDescripcion_distrito());
				break;
			}
		}
		
		idubigeo.setIddistrito(beanDistrito);
		
		bean.setIdubigeo(idubigeo);
		
		imagen=imagen.substring(imagen.indexOf(',')+1, imagen.length()-1);
		byte[] byteImagen = new Base64().decode(imagen);
		bean.setFotoBytes(byteImagen);
		
		imgFirma=imgFirma.substring(imgFirma.indexOf(',')+1, imgFirma.length()-1);
		byte[] byteFirmaVirtual = new Base64().decode(imgFirma);
		bean.setFirmaBytes(byteFirmaVirtual);
		
		bean.setHuellaBytes(Constantes.getBytesFromFile(socio.getHuella()));
		bean.setHuellaFileName(socio.getHuellaFileName());
		bean.setHuellaContentType(socio.getHuellaContentType());
		
		bean.setRequisito_asociarseBytes(Constantes.getBytesFromFile(socio.getRequisito_asociarse()));
		bean.setRequisito_asociarseFileName(socio.getRequisito_asociarseFileName());
		bean.setRequisito_asociarseContentType(socio.getRequisito_asociarseContentType());

		List<TelefonoBean> lstTelefono=(List<TelefonoBean>)session.get("telefonosSocio");
		List<DireccionBean> lstDirecciones=(List<DireccionBean>)session.get("direccionesSocio");
		List<PersonaAsociadaBean> lstpersonaAsociadas=(List<PersonaAsociadaBean>)session.get("detallePersonaAsociada");
		TrabajadorBean trabajador=(TrabajadorBean)session.get("TrabajadorLogueado");
		
		bean.setTelefonos(lstTelefono);
		bean.setDirecciones(lstDirecciones);
		bean.setLstpersonaAsociadas(lstpersonaAsociadas);
		bean.setIdtrabajador(trabajador);
		
		UsuarioBean usuario=new UsuarioBean();
		usuario.setLogin(nombres);
		usuario.setPassword(documento);
		
		bean.setIdusuario(usuario);
		
		String salida="";
		salida=service.InsertaSocio(bean);
		
		int Idbarcode_codeqr=0;
		
		Idbarcode_codeqr=Integer.parseInt(salida.substring(3));
		
		//Ruta del archivo generado
		HttpServletRequest req1 = ServletActionContext.getRequest();//Ruta del servidor
		String rutaBarraFile = req1.getServletContext().getRealPath("/") +
						"images\\barra";
		
		Codabar barcode=new Codabar();
		barcode.setData(String.valueOf(Idbarcode_codeqr));
		barcode.drawBarcode(rutaBarraFile);
		File barra = new File(rutaBarraFile);
		
		
		//Ruta del archivo generado
		HttpServletRequest req2 = ServletActionContext.getRequest();//Ruta del servidor
		String rutaQRFile = req2.getServletContext().getRealPath("/") +
						"images\\qr";
		
		QRCode qrcode = new QRCode(); 
		qrcode.setData(String.valueOf(Idbarcode_codeqr)); 
		qrcode.drawBarcode(rutaQRFile); 
		
		File qr = new File(rutaQRFile);
		
		bean.setBarcode(Constantes.getBytesFromFile(barra));
		bean.setQrcode(Constantes.getBytesFromFile(qr));
		
		int resultado =service.InsertaCodeBarra_CodeQR(bean);
		
		String jsonString="";
		if(resultado!=-1 && resultado!=0){
			Gson gson = new Gson();
			jsonString= gson.toJson(salida);	
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	@Action(value = "/LoadSocio", results = { 	@Result(location = "t_socio", 	name = "success", 	type = "tiles")})
	public String LoadSocio() throws Exception {
	
	return  "success";
	}
	
	@Action(value = "/AgregarPersonaAsociada")
	public String AgregarPersonaAsociada() throws Exception{
		try {
		PersonaAsociadaBean bean=new PersonaAsociadaBean();
		
		int codigo = generaCorrelativo(4);
		codigo++;
		bean.setIdCorrelativo(codigo);
		bean.setNombres(nombres);
		bean.setApellido_paterno(apellido_paterno);
		bean.setApellido_materno(apellido_materno);
		
		
		TipoDocumentoBean tipo_documento=new TipoDocumentoBean();
		tipo_documento.setIdtipodocumento(idtipodocumento);
		bean.setTipo_documento(tipo_documento);
		
		ParentescoBean parentesco=new ParentescoBean();
		parentesco.setIdparentesco(idparentesco);
		bean.setParentesco(parentesco);
		
		bean.setDocumento(documento);
		bean.setValida_document(valida_document);
		
		data.add(bean);
		
		session.put("detallePersonaAsociada", data);

		Gson gson = new Gson();
		String jsonString = gson.toJson(1);
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("application/json");
		response.getWriter().write(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "/listPersonaAsociada", results = {@Result(name = "success", type="json")})
	public String listPersonaAsociada() {
		try {
		List<PersonaAsociadaBean> data = (ArrayList<PersonaAsociadaBean>) session.get("detallePersonaAsociada");
		
		if(data.size()!=0){
			List<TipoDocumentoBean> lstTipoDocumento=new ArrayList<TipoDocumentoBean>();
			lstTipoDocumento=(List<TipoDocumentoBean>)session.get("ListTipoDocmentos");
			
			List<ParentescoBean> lstParentesco=new ArrayList<ParentescoBean>();
			lstParentesco=(List<ParentescoBean>)session.get("ListParentesco");
			
			for (PersonaAsociadaBean bean : data) {
				for (int i = 0; i < lstTipoDocumento.size(); i++) {
					if(lstTipoDocumento.get(i).getIdtipodocumento()==bean.getTipo_documento().getIdtipodocumento()){
						TipoDocumentoBean tipo_documento=new TipoDocumentoBean();
						tipo_documento.setIdtipodocumento(lstTipoDocumento.get(i).getIdtipodocumento());
						tipo_documento.setDescripcion(lstTipoDocumento.get(i).getDescripcion());
						bean.setTipo_documento(tipo_documento);
						break;
					}
				}
				for (int i = 0; i < lstParentesco.size(); i++) {
					if(lstParentesco.get(i).getIdparentesco()==bean.getParentesco().getIdparentesco()){
						ParentescoBean parentesco=new ParentescoBean();
						parentesco.setIdparentesco(lstParentesco.get(i).getIdparentesco());
						parentesco.setDescripcion(lstParentesco.get(i).getDescripcion());
						bean.setParentesco(parentesco);
						break;
					}
				}
			}
		}/*else{
			data=new ArrayList<PersonaAsociadaBean>();
		}*/
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(data);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "/RecuperarPersonaAsociada", results = {@Result(name = "success", type="json")})
	public String RecuperarPersonaAsociada() {
		try {
		List<PersonaAsociadaBean> data = (ArrayList<PersonaAsociadaBean>) session.get("detallePersonaAsociada");
		
		PersonaAsociadaBean bean=new PersonaAsociadaBean();
		
		for (PersonaAsociadaBean obj : data) {
			if(obj.getIdCorrelativo()==idpersonaAsociada){
				bean.setIdCorrelativo(idpersonaAsociada);
				bean.setNombres(obj.getNombres());
				bean.setApellido_paterno(obj.getApellido_paterno());
				bean.setApellido_materno(obj.getApellido_materno());
				bean.setDocumento(obj.getDocumento());
				
				TipoDocumentoBean tipo_documento=new TipoDocumentoBean();
				tipo_documento.setIdtipodocumento(obj.getTipo_documento().getIdtipodocumento());
				bean.setTipo_documento(tipo_documento);
				
				ParentescoBean parentesco=new ParentescoBean();
				parentesco.setIdparentesco(obj.getParentesco().getIdparentesco());
				bean.setParentesco(parentesco);
				bean.setValida_document(obj.getValida_document());
				break;
			}
			
		}
		
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
	@Action(value = "/ActualizarPersonaAsociada", results = {@Result(name = "success", type="json")})
	public String ActualizarPersonaAsociada() {
		try {
		data= (ArrayList<PersonaAsociadaBean>) session.get("detallePersonaAsociada");
		
		PersonaAsociadaBean bean=new PersonaAsociadaBean();
		bean.setIdCorrelativo(idpersonaAsociada);
		bean.setNombres(nombres);
		bean.setApellido_paterno(apellido_paterno);
		bean.setApellido_materno(apellido_materno);
		
		
		TipoDocumentoBean tipo_documento=new TipoDocumentoBean();
		tipo_documento.setIdtipodocumento(idtipodocumento);
		bean.setTipo_documento(tipo_documento);
		
		ParentescoBean parentesco=new ParentescoBean();
		parentesco.setIdparentesco(idparentesco);
		bean.setParentesco(parentesco);
		
		bean.setDocumento(documento); 
		
		
		for (PersonaAsociadaBean obj : data) {
			if(obj.getIdCorrelativo()==bean.getIdCorrelativo()){
				bean.setIdpersonaAsociada(obj.getIdpersonaAsociada());
				data.set(data.indexOf(obj), bean);
				break;
			}
			
		}
	
		session.put("detallePersonaAsociada", data);

		Gson gson = new Gson();
		String jsonString = gson.toJson(1);
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "/EliminarPersonaAsociada", results = {@Result(name = "success", type="json")})
	public String EliminarPersonaAsociada() {
		try {
		data = (ArrayList<PersonaAsociadaBean>) session.get("detallePersonaAsociada");
			
		Iterator<PersonaAsociadaBean> it = data.iterator();
		while (it.hasNext()) {
			PersonaAsociadaBean user = it.next();
			if(user.getIdpersonaAsociada()!=null){
				user.setIdsocio_aux(idsocio);
				service.EliminarPersonaAsociadaSocio(user);
				it.remove();
				break;
			}else if(idpersonaAsociada!=0){
			  if (user.getIdCorrelativo()==idpersonaAsociada) {
				    it.remove();
				    break;
				  }
			}
		}
		
		session.put("detallePersonaAsociada", data);
		Gson gson = new Gson();
		String jsonString = gson.toJson(1);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 public int generaCorrelativo(int value){
		 	int mayor =0;
		 	if(value==2){
		 		for(int i =0; i<lstTelefono.size(); i++){
		 			if(i==0){
		 				mayor = lstTelefono.get(i).getIdcorrelativo();
		 			}else{
		 				if(lstTelefono.get(i).getIdcorrelativo()>mayor)
		 					mayor = lstTelefono.get(i).getIdcorrelativo();
		 			}
		 		}
		 	}else if(value==3){
		 		for(int e =0; e<lstDireccion.size(); e++){
		 			if(e==0){
		 				mayor = lstDireccion.get(e).getIdcorrelativo();
		 			}else{
		 				if(lstDireccion.get(e).getIdcorrelativo()>mayor)
		 					mayor = lstDireccion.get(e).getIdcorrelativo();
		 			}
		 		}
		 	}else if(value==4){
		 		for(int h =0; h<data.size(); h++){
		 			if(h==0){
		 				mayor = data.get(h).getIdCorrelativo();
		 			}else{
		 				if(data.get(h).getIdCorrelativo()>mayor)
		 					mayor = data.get(h).getIdCorrelativo();
		 			}
		 		}
		 	}
		 	return mayor;
		 }
 
	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellido_paterno() {
		return apellido_paterno;
	}


	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}


	public String getApellido_materno() {
		return apellido_materno;
	}


	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public int getIdtipodocumento() {
		return idtipodocumento;
	}


	public void setIdtipodocumento(int idtipodocumento) {
		this.idtipodocumento = idtipodocumento;
	}


	public int getIdparentesco() {
		return idparentesco;
	}


	public void setIdparentesco(int idparentesco) {
		this.idparentesco = idparentesco;
	}

	public int getIdpersonaAsociada() {
		return idpersonaAsociada;
	}

	public void setIdpersonaAsociada(int idpersonaAsociada) {
		this.idpersonaAsociada = idpersonaAsociada;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public byte[] getBarcode() {
		return barcode;
	}

	public void setBarcode(byte[] barcode) {
		this.barcode = barcode;
	}

	public byte[] getQrcode() {
		return qrcode;
	}

	public void setQrcode(byte[] qrcode) {
		this.qrcode = qrcode;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getIddepartamento() {
		return iddepartamento;
	}

	public void setIddepartamento(int iddepartamento) {
		this.iddepartamento = iddepartamento;
	}

	public int getIdprovincia() {
		return idprovincia;
	}

	public void setIdprovincia(int idprovincia) {
		this.idprovincia = idprovincia;
	}

	public int getIddistrito() {
		return iddistrito;
	}

	public void setIddistrito(int iddistrito) {
		this.iddistrito = iddistrito;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getAgrega_edita_telefono() {
		return agrega_edita_telefono;
	}

	public void setAgrega_edita_telefono(int agrega_edita_telefono) {
		this.agrega_edita_telefono = agrega_edita_telefono;
	}


	public int getIdcorrelativoTelefono() {
		return idcorrelativoTelefono;
	}


	public void setIdcorrelativoTelefono(int idcorrelativoTelefono) {
		this.idcorrelativoTelefono = idcorrelativoTelefono;
	}


	public int getIdcorrelativoDireccion() {
		return idcorrelativoDireccion;
	}


	public void setIdcorrelativoDireccion(int idcorrelativoDireccion) {
		this.idcorrelativoDireccion = idcorrelativoDireccion;
	}


	public int getAgrega_edita_direccion() {
		return agrega_edita_direccion;
	}


	public void setAgrega_edita_direccion(int agrega_edita_direccion) {
		this.agrega_edita_direccion = agrega_edita_direccion;
	}

	public String getIdsocio() {
		return idsocio;
	}

	public void setIdsocio(String idsocio) {
		this.idsocio = idsocio;
	}

	public PaginadorBean getPaginador() {
		return paginador;
	}

	public void setPaginador(PaginadorBean paginador) {
		this.paginador = paginador;
	}

	public InputStream getImagenFoto() {
		return imagenFoto;
	}

	public void setImagenFoto(InputStream imagenFoto) {
		this.imagenFoto = imagenFoto;
	}



	public InputStream getImagenHuella() {
		return imagenHuella;
	}



	public void setImagenHuella(InputStream imagenHuella) {
		this.imagenHuella = imagenHuella;
	}



	public InputStream getImagenFirma() {
		return imagenFirma;
	}



	public void setImagenFirma(InputStream imagenFirma) {
		this.imagenFirma = imagenFirma;
	}

	public SocioBean getSocio() {
		return socio;
	}


	public void setSocio(SocioBean socio) {
		this.socio = socio;
	}


	public InputStream getDescargarHuella() {
		return descargarHuella;
	}


	public void setDescargarHuella(InputStream descargarHuella) {
		this.descargarHuella = descargarHuella;
	}


	public InputStream getDescargarFirma() {
		return descargarFirma;
	}


	public void setDescargarFirma(InputStream descargarFirma) {
		this.descargarFirma = descargarFirma;
	}


	public InputStream getDescargarReqAsociarse() {
		return descargarReqAsociarse;
	}


	public void setDescargarReqAsociarse(InputStream descargarReqAsociarse) {
		this.descargarReqAsociarse = descargarReqAsociarse;
	}

	public String getHuellafileName() {
		return huellafileName;
	}

	public void setHuellafileName(String huellafileName) {
		this.huellafileName = huellafileName;
	}

	public String getFirmafileName() {
		return firmafileName;
	}

	public void setFirmafileName(String firmafileName) {
		this.firmafileName = firmafileName;
	}

	public String getReqAsociarsefileName() {
		return reqAsociarsefileName;
	}

	public void setReqAsociarsefileName(String reqAsociarsefileName) {
		this.reqAsociarsefileName = reqAsociarsefileName;
	}



	public String getMotivo_actualizacion() {
		return motivo_actualizacion;
	}



	public void setMotivo_actualizacion(String motivo_actualizacion) {
		this.motivo_actualizacion = motivo_actualizacion;
	}

	public String getImgFirma() {
		return imgFirma;
	}

	public void setImgFirma(String imgFirma) {
		this.imgFirma = imgFirma;
	}

	public int getValida_document() {
		return valida_document;
	}

	public void setValida_document(int valida_document) {
		this.valida_document = valida_document;
	}

	
	
}
