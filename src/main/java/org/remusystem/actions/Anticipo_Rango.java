package org.remusystem.actions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;
import org.remusystem.persistencia.Empresa;
import org.remusystem.persistencia.Solicitud;
import org.remusystem.persistencia.OtrosDescuentos;
import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.persistencia.RelacionLaboralDAO;
import org.remusystem.persistencia.Usuario;

public class Anticipo_Rango extends ActionSupport implements SessionAware{

	private Map session;
	private String nombre3;
	private String monto_desde;
	private String monto_hasta;
	private String monto3;
	private String num_cuotas3;
	private Date desde3;
//	private Date hasta3;
	



	public String execute(){
		
		Controlador control = Controlador.getInstance();
		RelacionLaboralDAO tDAO = new RelacionLaboralDAO();
		Usuario userSession = (Usuario) session.get("User");
		Empresa emp = control.buscaEmpresa(userSession.getRut());
		Integer desde = Integer.parseInt(monto_desde);
		Integer hasta = Integer.parseInt(monto_hasta);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-aaaa");
		String fechainiciocalculo = sdf.format(desde);
		
		List<RelacionLaboral> trabajadores = tDAO.findByRangoSueldo(emp.getId(),desde,hasta);
		
		if(trabajadores!=null){
			//en caso que las cuotas sean 0 el pago es indefinido asi que debe durar muchisimo
			if(num_cuotas3.equals("0")){
				num_cuotas3 = "9999";
			}
			
			//creamos el anticipo o descuento es un anticipo comun para muchas solicitudes
			OtrosDescuentos anticipo = new OtrosDescuentos();
			anticipo.setNombre(nombre3);
			anticipo.setMonto(Integer.parseInt(monto3));
			anticipo.setNumerCuotas(Integer.parseInt(num_cuotas3));
			anticipo.setFechaInicio(desde3);
			anticipo.setFechaFinal(calcularFechaFinal(fechainiciocalculo, num_cuotas3));
			control.AgregaDescuento(anticipo);
			//el siguiente for guarda uno a uno la solicitud para los trabajadores
			for(int i=0;i<trabajadores.size();i++){
				Solicitud sol = new Solicitud();
				RelacionLaboral relacion = trabajadores.get(i);
				sol.setOtrosDescuentos(anticipo);
				sol.setRelacionLaboral(relacion);
				control.AgregaSolicitud(sol);
				
			}
			
			addActionMessage("se ha registrado el nuevo anticipo o descuento para el rango seleccionado");
			return SUCCESS;
		}else{
			addActionError("no se encontraron trabajadores para el rango");
			return ERROR;
		}
		
		
	}
	
	//para transformar el string en fecha
	 public Date getDate(String date)
	     {
	         DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	         try {
	             return df.parse(date);
	         } catch (ParseException ex) {
	         }
	         return null;
	     } 
	
	//para calcular la fecha final segun el numero de cuotas
	public Date calcularFechaFinal(String date, String cuotas){
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(desde3);
		cal.add(Calendar.MONTH, Integer.parseInt(cuotas)-1);   
		String newdate = dateformat.format(cal.getTime());
		System.out.println(newdate);
		return (getDate(newdate));
	}
	
	public Map getSession() {
		return session;
	}


	public void setSession(Map session) {
		this.session = session;
	}

	public String getMonto_desde() {
		return monto_desde;
	}

	public void setMonto_desde(String montoDesde) {
		monto_desde = montoDesde;
	}

	public String getMonto_hasta() {
		return monto_hasta;
	}

	public void setMonto_hasta(String montoHasta) {
		monto_hasta = montoHasta;
	}


	public Date getDesde3() {
		return desde3;
	}

	public void setDesde3(Date desde3) {
		this.desde3 = desde3;
	}

//	public Date getHasta3() {
//		return hasta3;
//	}
//
//	public void setHasta3(Date hasta3) {
//		this.hasta3 = hasta3;
//	}


	public String getNombre3() {
		return nombre3;
	}

	public void setNombre3(String nombre3) {
		this.nombre3 = nombre3;
	}

	public String getMonto3() {
		return monto3;
	}

	public void setMonto3(String monto3) {
		this.monto3 = monto3;
	}

	public String getNum_cuotas3() {
		return num_cuotas3;
	}

	public void setNum_cuotas3(String numCuotas3) {
		num_cuotas3 = numCuotas3;
	}
}
