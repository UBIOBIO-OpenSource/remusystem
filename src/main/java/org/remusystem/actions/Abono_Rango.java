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
import org.remusystem.persistencia.SolicitudAbono;
import org.remusystem.persistencia.Abonos;
import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.persistencia.RelacionLaboralDAO;
import org.remusystem.persistencia.Usuario;

public class Abono_Rango extends ActionSupport implements SessionAware {
	
	
	private Map session;
	private String nombre4;
	private String monto_desde2;
	private String monto_hasta2;
	private String monto4;
	private String num_cuotas4;
	private Date desde4;
//	private Date hasta4;
	private String tipoabono2;
	
	public String execute(){
		
		Controlador control = Controlador.getInstance();
		RelacionLaboralDAO tDAO = new RelacionLaboralDAO();
		Usuario userSession = (Usuario) session.get("User");
		Empresa emp = control.buscaEmpresa(userSession.getRut());
		Integer desde = Integer.parseInt(monto_desde2);
		Integer hasta = Integer.parseInt(monto_hasta2);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-aaaa");
		String fechainiciocalculo = sdf.format(desde4);
		
		
		List<RelacionLaboral> trabajadores = tDAO.findByRangoSueldo(emp.getId(),desde,hasta);
		
		if(trabajadores!=null){
			//en caso que las cuotas sean 0 el pago es indefinido asi que debe durar muchisimo
			if(num_cuotas4.equals("0")){
				num_cuotas4 = "9999";
			}
			//creamos el abono que es comun
			Abonos abono = new Abonos();
			abono.setNombre(nombre4);
			abono.setMonto(Integer.parseInt(monto4));
			abono.setNumeroCuotas(Integer.parseInt(num_cuotas4));
			abono.setFechaInicio(desde4);
			abono.setFechaFinal(calcularFechaFinal(fechainiciocalculo, num_cuotas4));
			abono.setTipoAbono(tipoabono2);
			control.AgregaAbono(abono);
			for(int i=0;i<trabajadores.size();i++){
				SolicitudAbono sol = new SolicitudAbono();
				RelacionLaboral relacion = trabajadores.get(i);
				sol.setAbonos(abono);
				sol.setRelacionLaboral(relacion);
				control.AgregaSolicitudAbono(sol);
			}
			
			
			addActionMessage("se ha registrado el abono para el rango de sueldos seleccionados");
			return SUCCESS;
		}else{
			addActionError("no se encontraron trabajadores para el rango escogido");
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
		cal.setTime(desde4);
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

	public Date getDesde4() {
		return desde4;
	}

	public void setDesde4(Date desde4) {
		this.desde4 = desde4;
	}

//	public Date getHasta4() {
//		return hasta4;
//	}
//
//	public void setHasta4(Date hasta4) {
//		this.hasta4 = hasta4;
//	}

	public String getTipoabono2() {
		return tipoabono2;
	}

	public void setTipoabono2(String tipoabono2) {
		this.tipoabono2 = tipoabono2;
	}

	public String getNombre4() {
		return nombre4;
	}

	public void setNombre4(String nombre4) {
		this.nombre4 = nombre4;
	}

	public String getMonto_desde2() {
		return monto_desde2;
	}

	public void setMonto_desde2(String montoDesde2) {
		monto_desde2 = montoDesde2;
	}

	public String getMonto_hasta2() {
		return monto_hasta2;
	}

	public void setMonto_hasta2(String montoHasta2) {
		monto_hasta2 = montoHasta2;
	}

	public String getMonto4() {
		return monto4;
	}

	public void setMonto4(String monto4) {
		this.monto4 = monto4;
	}

	public String getNum_cuotas4() {
		return num_cuotas4;
	}

	public void setNum_cuotas4(String numCuotas4) {
		num_cuotas4 = numCuotas4;
	}




}