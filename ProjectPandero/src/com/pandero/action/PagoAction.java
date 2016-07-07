package com.pandero.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.opensymphony.xwork2.validator.annotations.CustomValidator;
import com.pandero.beans.CuotaBean;
import com.pandero.beans.DepartamentoBean;
import com.pandero.beans.DetalleBean;
import com.pandero.beans.DireccionBean;
import com.pandero.beans.DistritoBean;
import com.pandero.beans.EstadoBean;
import com.pandero.beans.Mora;
import com.pandero.beans.ObligacionBean;
import com.pandero.beans.FacturaBean;
import com.pandero.beans.PaisBean;
import com.pandero.beans.PersonaAsociadaBean;
import com.pandero.beans.ProvinciaBean;
import com.pandero.beans.SocioBean;
import com.pandero.beans.TelefonoBean;
import com.pandero.beans.TipoDocumentoBean;
import com.pandero.beans.TrabajadorBean;
import com.pandero.beans.UbigeoBean;
import com.pandero.beans.UsuarioBean;
import com.pandero.service.PagoService;
import com.pandero.service.PagoServicelmpl;
import com.pandero.util.Constantes;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

@ParentPackage(value = "dawi")
public class PagoAction extends ActionSupport{
	
		private String dni_Socio;
		PagoService service=new PagoServicelmpl();
		
		//Session
		private Map<String, Object> session = ActionContext.getContext().getSession();
		static private List<CuotaBean> cuotas_session=new ArrayList<CuotaBean>();
		static private List<CuotaBean> cuotas_session_sin_fechaParse=new ArrayList<CuotaBean>();
		static private List<DetalleBean> listdetalle_factura=new ArrayList<DetalleBean>();

		//objeto que permita debbug en consola(permite hacer seguimiento de codigo)
		private static final Log log = LogFactory.getLog(SocioAction.class);
		
		//
		private CuotaBean bean;
		private String fechaVencimiento;
		private double montoPagado;
		private double mora;
		private String idobligaciones;
		private int idcuota;
		private String dniPagador,nombresPagador;//
		
		//
		private String dni_Socio_1;
		private double nuevoMonto;
		private String dniPagador_1,nombresPagador_1;
		private String idobligaciones_1;
		
		
		//Action registraPago
		@SuppressWarnings("unchecked")
		@Action(value = "/registraPago", results = { @Result(location = "t_registrar_pago", name = "success", type = "tiles"),
				  @Result(location = "t_login", name = "login", type = "tiles")})
		public String registraPago() throws Exception{
			log.info("Info En Action registraPago");
			try {
				

					FacturaBean pago=new FacturaBean();
					
					pago.setTotal(montoPagado);
					pago.setDniPagador(dniPagador);
					pago.setNombresPagador(nombresPagador);
					ObligacionBean obligacion=new ObligacionBean();
					obligacion.setIdobligaciones(idobligaciones);
					pago.setObligacion(obligacion);
					
					//Registra la cabecera de la factura
					service.registraFactura(pago);
					//Recupera el maximo idfactura de la tabla factura
					int idfactura=service.obtieneIdFactura();
					for(DetalleBean d: listdetalle_factura){
						DetalleBean detalle=new DetalleBean();
						detalle.setIdfactura(idfactura);
						detalle.setIdcuota(d.getIdcuota());
						detalle.setMonto(d.getMonto());
						detalle.setFecha_vencimiento(d.getFecha_vencimiento());
						detalle.setMora(d.getMora());
				  
						//Registra el detalle de la factura
						int salida= service.registraDetalle(detalle);
						int resultado=service.cambia_estadoCuota(d.getIdcuota());
						
					}
					
					List<CuotaBean> x=service.cuotasDeObligacion2(idobligaciones);
					int a=0;
					for(int i=0;i<x.size();i++){
							
						if(x.get(i).getEstado().getIdestado()==2){
							a++;
						}
						if(a==x.size()){
							service.cambiaEstadoObligacion(idobligaciones);
						}
					}
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		
		}//Termina action registraPago
		
		//Action registraPago_1
		@SuppressWarnings("unchecked")
		@Action(value = "/registraPago_1", results = { @Result(location = "t_registrar_pago", name = "success", type = "tiles"),
				  @Result(location = "t_login", name = "login", type = "tiles")})
		public String registraPago_1() throws Exception{
			log.info("Info En Action registraPago_1");
			try {
			
					FacturaBean pago=new FacturaBean();
					
					pago.setTotal(nuevoMonto);
					pago.setDniPagador(dniPagador_1);
					pago.setNombresPagador(nombresPagador_1);
					ObligacionBean obligacion=new ObligacionBean();
					obligacion.setIdobligaciones(idobligaciones_1);
					pago.setObligacion(obligacion);
					
					//Registra la cabecera de la factura
					service.registraFactura(pago);
									
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		
		}//Termina action registraPago_1
		@SuppressWarnings("unchecked")
		@Action(value = "/ObtenerCuotasSeleccionadas_check", results = { @Result(name="success",type="json") })
		public String ObtenerCuotasSeleccionadas_check() throws  Exception
		{  
			//System.out.println(bean.getIdcuota());
			List<CuotaBean> data = (ArrayList<CuotaBean>) session.get("Session_Cuoatas");
			//Lista de cuotas que iran al detalle factura
			//ArrayList<CuotaBean> llenar_factura= new ArrayList<CuotaBean>();
			
			for (CuotaBean c : data) {
				DetalleBean obj=new DetalleBean();
				if(c.getIdcuota()==bean.getIdcuota()){
					obj.setIdcuota(c.getIdcuota());
					obj.setMonto(c.getMonto());
					obj.setFecha_vencimiento(c.getFechaPago());
					obj.setMora(c.getMora().getMora());
					//llenar_factura.add(obj);
					listdetalle_factura.add(obj);
					break;
				}
			}
			
			//agregamos a la session el arreglo de cuotas
			session.put("Session_detalle_factura", listdetalle_factura);
			//List<CuotaBean> detalle= new ArrayList<CuotaBean>();
			//detalle=(List<CuotaBean>) session.get("Session_detalle_factura");
			
			HttpServletResponse response=ServletActionContext.getResponse();
			
			String json1= new Gson().toJson(listdetalle_factura);	
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8");			
			response.getWriter().write(json1);
			
			return null;
		}
		
		//Action ObtenerObligacion
		@Action(value = "/ObtenerObligacion", results = { @Result(name="success",type="json") })
		public String ObtenerObligacion() throws  Exception
		{  
			listdetalle_factura.clear();
			
			ObligacionBean obligacion= new ObligacionBean();
			obligacion=service.traObligacion(dni_Socio);
			
			HttpServletResponse response=ServletActionContext.getResponse();
			
			String json1= new Gson().toJson(obligacion);	
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8");			
			response.getWriter().write(json1);
			
			return null;
		}//Termina action ObtenerObligacion
		
		//Action ObtenerPagosAnteriores
		@Action(value = "/ObtenerObligacion_1", results = { @Result(name="success",type="json") })
		public String ObtenerObligacion_1() throws  Exception
		{  
		
			ObligacionBean obligacion= new ObligacionBean();
			obligacion=service.traObligacion_sinVencimiento(dni_Socio_1);
			
			HttpServletResponse response=ServletActionContext.getResponse();
			
			String json1= new Gson().toJson(obligacion);	
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8");			
			response.getWriter().write(json1);
			
			return null;
		}//Termina action ObtenerPagosAnteriores

		//Action cuotasDeObligacion
		@Action(value = "/cuotasDeObligacion", results = { @Result(name="success",type="json") })
		public String cuotasDeObligacion() throws  Exception
		{  
			//Trae la obligacion
			ObligacionBean obligacion= new ObligacionBean();
			obligacion=service.traObligacion(dni_Socio);
			
			//Trae las cuotas de la obligacion
			String idObligacion=obligacion.getIdobligaciones();
			List<CuotaBean> cuotas=service.cuotasDeObligacion(idObligacion);
			List<CuotaBean> cuotas_sinFechaParse=service.cuotasDeObligacion_sinFechaParse(idObligacion);
			
			//Obtengo la fecha actual
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			//Calcula la mora
			for (int i = 0; i < cuotas.size(); i++) {
			
				String fecActual=sdf.format(date);
				String fecVencimiento=cuotas.get(i).getFechaPago();
				Date dateFecVencimiento=sdf.parse(fecVencimiento);
				double montoPagar=cuotas.get(i).getMonto();
				Mora beanMora=new Mora();
				
				long longFecVencimiento = dateFecVencimiento.getTime();
				long longFecActual = date.getTime();
				
				if(longFecActual>longFecVencimiento){
					EstadoBean estado= new EstadoBean();
					estado.setDescripcion("vencido");
					cuotas.get(i).setEstado(estado);
				}
				
				double mora=Double.parseDouble(service.calculaMora(fecVencimiento,fecActual, montoPagar, 0.25))-montoPagar;
				beanMora.setMora(mora);
				cuotas.get(i).setMora(beanMora);
				cuotas_sinFechaParse.get(i).setMora(beanMora);
				//Almacenar las cuotas en un arrglo
				cuotas_session.add(cuotas.get(i));
				cuotas_session_sin_fechaParse.add(cuotas_sinFechaParse.get(i));
			}//Termina calcula mora
			
			//agregamos a la session el arreglo de cuotas
			session.put("Session_Cuoatas", cuotas_session_sin_fechaParse);
			
			HttpServletResponse response=ServletActionContext.getResponse();
			
			String json1= new Gson().toJson(cuotas);	
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8");			
			response.getWriter().write(json1);
			
			return null;
		}//Termina action cuotasDeObligacion

		//Action ObtenerPagosAnteriores
		@Action(value = "/ObtenerPagosAnteriores", results = { @Result(name="success",type="json") })
		public String ObtenerPagosAnteriores() throws  Exception
		{  
			//Trae la obligacion
			ObligacionBean obligacion= new ObligacionBean();
			obligacion=service.traObligacion_sinVencimiento(dni_Socio_1);
			
			//Trae las cuotas de la obligacion
			String idObligacion=obligacion.getIdobligaciones();
			List<FacturaBean> pagos=service.ObtenerPagos(idObligacion);
						
			HttpServletResponse response=ServletActionContext.getResponse();
			
			String json1= new Gson().toJson(pagos);	
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8");			
			response.getWriter().write(json1);
			
			return null;
		}//Termina action ObtenerPagosAnteriores
		
		//Metodos get/set
		public String getDni_Socio() {
			return dni_Socio;
		}

		public void setDni_Socio(String dni_Socio) {
			this.dni_Socio = dni_Socio;
		}


		public CuotaBean getBean() {
			return bean;
		}


		public void setBean(CuotaBean bean) {
			this.bean = bean;
		}

		public String getFechaVencimiento() {
			return fechaVencimiento;
		}


		public void setFechaVencimiento(String fechaVencimiento) {
			this.fechaVencimiento = fechaVencimiento;
		}


		public double getMontoPagado() {
			return montoPagado;
		}


		public void setMontoPagado(double montoPagado) {
			this.montoPagado = montoPagado;
		}


		public double getMora() {
			return mora;
		}


		public void setMora(double mora) {
			this.mora = mora;
		}


		public String getIdobligaciones() {
			return idobligaciones;
		}


		public void setIdobligaciones(String idobligaciones) {
			this.idobligaciones = idobligaciones;
		}


		public int getIdcuota() {
			return idcuota;
		}


		public void setIdcuota(int idcuota) {
			this.idcuota = idcuota;
		}
		
		public String getDniPagador() {
			return dniPagador;
		}


		public void setDniPagador(String dniPagador) {
			this.dniPagador = dniPagador;
		}


		public String getNombresPagador() {
			return nombresPagador;
		}


		public void setNombresPagador(String nombresPagador) {
			this.nombresPagador = nombresPagador;
		}
		public String getDni_Socio_1() {
			return dni_Socio_1;
		}


		public void setDni_Socio_1(String dni_Socio_1) {
			this.dni_Socio_1 = dni_Socio_1;
		}
		
		public double getNuevoMonto() {
			return nuevoMonto;
		}

		public void setNuevoMonto(double nuevoMonto) {
			this.nuevoMonto = nuevoMonto;
		}

		public String getDniPagador_1() {
			return dniPagador_1;
		}

		public void setDniPagador_1(String dniPagador_1) {
			this.dniPagador_1 = dniPagador_1;
		}

		public String getNombresPagador_1() {
			return nombresPagador_1;
		}

		public void setNombresPagador_1(String nombresPagador_1) {
			this.nombresPagador_1 = nombresPagador_1;
		}

		public String getIdobligaciones_1() {
			return idobligaciones_1;
		}

		public void setIdobligaciones_1(String idobligaciones_1) {
			this.idobligaciones_1 = idobligaciones_1;
		}

}
