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

public class Abono_Cargo extends ActionSupport implements SessionAware {
	
	
	private Integer car_tra2;
	private String nombre2;
	private String monto2;
	private String num_cuotas2;
	private Date desde2;
//	private Date hasta2;
	private Map session;
	private String tipoabono;
	
	public String execute(){
		
		Controlador control = Controlador.getInstance();
		RelacionLaboralDAO tDAO = new RelacionLaboralDAO();
		Usuario userSession = (Usuario) session.get("User");
		Empresa emp = control.buscaEmpresa(userSession.getRut());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-aaaa");
		String fechainiciocalculo = sdf.format(desde2);
		
		
		List<RelacionLaboral> trabajadores = tDAO.findbyCargo(car_tra2,emp.getId());
		
		if(trabajadores!=null){
			//en caso que las cuotas sean 0 el pago es indefinido asi que debe durar muchisimo
			if(num_cuotas2.equals("0")){
				num_cuotas2 = "9999";
			}
			//creamos el abono que es comun
			Abonos abono = new Abonos();
			abono.setNombre(nombre2);
			abono.setMonto(Integer.parseInt(monto2));
			abono.setNumeroCuotas(Integer.parseInt(num_cuotas2));
			abono.setFechaInicio(desde2);
			abono.setFechaFinal(calcularFechaFinal(fechainiciocalculo, num_cuotas2));
			abono.setTipoAbono(tipoabono);
			control.AgregaAbono(abono);
			for(int i=0;i<trabajadores.size();i++){
				SolicitudAbono sol = new SolicitudAbono();
				RelacionLaboral relacion = trabajadores.get(i);
				sol.setAbonos(abono);
				sol.setRelacionLaboral(relacion);
				control.AgregaSolicitudAbono(sol);
			}
			
			
			addActionMessage("se ha registrado el abono para el cargo seleccionado");
			return SUCCESS;
		}else{
			addActionError("no se encontraron trabajadores para el cargo escogido");
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
		cal.setTime(desde2);
		cal.add(Calendar.MONTH, Integer.parseInt(cuotas)-1);    //Adding 1 month to current date
		String newdate = dateformat.format(cal.getTime());
		System.out.println(newdate);
		return (getDate(newdate));
	}
	
	public Integer getCar_tra2() {
		return car_tra2;
	}

	public void setCar_tra2(Integer carTra2) {
		car_tra2 = carTra2;
	}

	public Date getDesde2() {
		return desde2;
	}

	public void setDesde2(Date desde2) {
		this.desde2 = desde2;
	}

//	public Date getHasta2() {
//		return hasta2;
//	}
//
//	public void setHasta2(Date hasta2) {
//		this.hasta2 = hasta2;
//	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public String getTipoabono() {
		return tipoabono;
	}

	public void setTipoabono(String tipoabono) {
		this.tipoabono = tipoabono;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getMonto2() {
		return monto2;
	}

	public void setMonto2(String monto2) {
		this.monto2 = monto2;
	}

	public String getNum_cuotas2() {
		return num_cuotas2;
	}

	public void setNum_cuotas2(String numCuotas2) {
		num_cuotas2 = numCuotas2;
	}

}
