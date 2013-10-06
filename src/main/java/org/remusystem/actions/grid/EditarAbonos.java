package org.remusystem.actions.grid;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.control.Controlador;
import org.remusystem.persistencia.SolicitudAbonoDAO;
import org.remusystem.persistencia.SolicitudAbono;
import org.remusystem.persistencia.Abonos;
import org.remusystem.persistencia.AbonosDAO;

public class EditarAbonos extends ActionSupport implements SessionAware{
	
	private Map session;
	private String oper;
	private String id;
	private String nombre;
	private String fechaInicio;
	private String fechaFinal;
	private String monto;
	private String numeroCuotas;
	private String tipoAbono;
	
	public String execute(){
		
		System.out.println("oper: " + oper);
		System.out.println("id: " + id);
		
		RelacionLaboral rel = (RelacionLaboral)session.get("relacion");
		Controlador control = Controlador.getInstance();
		SolicitudAbono sol = new SolicitudAbono();
		SolicitudAbonoDAO solDAO = new SolicitudAbonoDAO();
		Abonos abono = new Abonos();
		AbonosDAO abonoDAO = new AbonosDAO();
		
		if (oper.equalsIgnoreCase("add")){
			//en caso que las cuotas sean 0 el pago es indefinido asi que debe durar muchisimo
			if(numeroCuotas.equals("0")){
				numeroCuotas = "9999";
			}
			abono.setMonto(Integer.parseInt(monto));
			abono.setNombre(nombre);
			abono.setNumeroCuotas(Integer.parseInt(numeroCuotas));
			abono.setTipoAbono(tipoAbono);
			abono.setFechaInicio(getDate(fechaInicio));
			abono.setFechaFinal(calcularFechaFinal(fechaInicio, numeroCuotas));
			control.AgregaAbono(abono);
			sol.setAbonos(abono);
			sol.setRelacionLaboral(rel);
			control.AgregaSolicitudAbono(sol);
		}else if(oper.equalsIgnoreCase("edit")){
			//en caso que las cuotas sean 0 el pago es indefinido asi que debe durar muchisimo
			if(numeroCuotas.equals("0")){
				numeroCuotas = "9999";
			}
			abono = abonoDAO.findById(Integer.parseInt(id));
			abono.setMonto(Integer.parseInt(monto));
			abono.setNombre(nombre);
			abono.setNumeroCuotas(Integer.parseInt(numeroCuotas));
			abono.setTipoAbono(tipoAbono);
			abono.setFechaInicio(getDate(fechaInicio));
			abono.setFechaFinal(calcularFechaFinal(fechaInicio, numeroCuotas));
			control.updateAbono(abono);
		}else if(oper.equalsIgnoreCase("del")){
			abono = abonoDAO.findById(Integer.parseInt(id));
			sol = solDAO.findByIdAbono(Integer.parseInt(id));
			control.deleteSolicitudAbono(sol);
//			control.deleteAbono(abono);
		}
		
		
		
		return NONE;
	}
	
	//para transformar el string en fecha
	 public Date getDate(String date)
	     {
	         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	         try {
	             return sdf.parse(date.trim());
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

	public String getTipoAbono() {
		return tipoAbono;
	}

	public void setTipoAbono(String tipoAbono) {
		this.tipoAbono = tipoAbono;
	}

	public String getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(String numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

}
