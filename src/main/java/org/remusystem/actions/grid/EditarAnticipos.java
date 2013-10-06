package org.remusystem.actions.grid;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;
import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.persistencia.Solicitud;
import org.remusystem.persistencia.SolicitudDAO;
import org.remusystem.persistencia.OtrosDescuentos;
import org.remusystem.persistencia.OtrosDescuentosDAO;


public class EditarAnticipos extends ActionSupport implements SessionAware{

	private Map session;
	private String oper;
	private String id;
	private String nombre;
	private String fechaInicio;
	private String fechaFinal;
	private String monto;
	private String numerCuotas;

	
	public String execute(){
		
		RelacionLaboral rel = (RelacionLaboral)session.get("relacion");
		Controlador control = Controlador.getInstance();
		Solicitud sol = new Solicitud();
		SolicitudDAO solDAO = new SolicitudDAO();
		OtrosDescuentos anticipo = new OtrosDescuentos();
		OtrosDescuentosDAO anticipoDAO = new OtrosDescuentosDAO();
		
		
		
		if (oper.equalsIgnoreCase("add")){
        	//en caso que las cuotas sean 0 el pago es indefinido asi que debe durar muchisimo
			if(numerCuotas.equals("0")){
				numerCuotas = "9999";
			}
			anticipo.setMonto(Integer.parseInt(monto));
			anticipo.setNumerCuotas(Integer.parseInt(numerCuotas));
			anticipo.setFechaInicio(getDate(fechaInicio));
			anticipo.setFechaFinal(calcularFechaFinal(fechaInicio, numerCuotas));
			anticipo.setNombre(nombre);
			control.AgregaDescuento(anticipo);
			sol.setOtrosDescuentos(anticipo);
			sol.setRelacionLaboral(rel);
			control.AgregaSolicitud(sol);
        	}else if(oper.equalsIgnoreCase("edit")){
        		//en caso que las cuotas sean 0 el pago es indefinido asi que debe durar muchisimo
				if(numerCuotas.equals("0")){
					numerCuotas = "9999";
				}
				anticipo = anticipoDAO.findById(Integer.parseInt(id));
        		anticipo.setMonto(Integer.parseInt(monto));
                anticipo.setNumerCuotas(Integer.parseInt(numerCuotas));
                anticipo.setFechaInicio(getDate(fechaInicio));
            	anticipo.setFechaFinal(calcularFechaFinal(fechaInicio, numerCuotas));
                anticipo.setNombre(nombre);
            	control.updateAnticipo(anticipo);
            }else if(oper.equalsIgnoreCase("del")){
            	anticipo = anticipoDAO.findById(Integer.parseInt(id));
				sol = solDAO.findByIdAnticipo(Integer.parseInt(id));
				control.deleteSolicitud(sol);
            	//control.deleteAnticipo(anticipo);
				
			}
		
		return NONE;
	}
	
	//para transformar el string en fecha
	 public Date getDate(String date)
	     {
	         DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	         try {
                 Date dateObj=df.parse(date.trim());
                 System.out.println(dateObj);
	             return dateObj;
	         } catch (ParseException ex) {
                 System.out.println(ex);
	         }
	         return null;
	     } 
	 
	//para calcular la fecha final segun el numero de cuotas
	public Date calcularFechaFinal(String date, String cuotas){
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate(fechaInicio));
		cal.add(Calendar.MONTH, Integer.parseInt(cuotas)-1);    //Adding 1 month to current date
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

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getNumerCuotas() {
		return numerCuotas;
	}

	public void setNumerCuotas(String numerCuotas) {
		this.numerCuotas = numerCuotas;
	}

}
