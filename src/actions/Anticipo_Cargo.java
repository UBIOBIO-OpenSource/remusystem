package actions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import control.Controlador;
import persistencia.Empresa;
import persistencia.GrupoHabDesc;
import persistencia.GrupoHabDescDAO;
import persistencia.Solicitud;
import persistencia.SolicitudDAO;
import persistencia.OtrosDescuentos;
import persistencia.OtrosDescuentosDAO;
import persistencia.RelacionLaboral;
import persistencia.RelacionLaboralDAO;
import persistencia.Usuario;

public class Anticipo_Cargo extends ActionSupport implements SessionAware{
	
	private Integer car_tra;
	private String nombre;
	private String monto;
	private String num_cuotas;
	private Date desde;
//	private Date hasta;
	private Map session;
	
	
	public String execute(){
		
		Controlador control = Controlador.getInstance();
		RelacionLaboralDAO tDAO = new RelacionLaboralDAO();
		Usuario userSession = (Usuario) session.get("User");
		Empresa emp = control.buscaEmpresa(userSession.getRut());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-aaaa");
		String fechainiciocalculo = sdf.format(desde);
		
		
		List<RelacionLaboral> trabajadores = tDAO.findbyCargo(car_tra,emp.getId());
		
		if(trabajadores!=null){
			//en caso que las cuotas sean 0 el pago es indefinido asi que debe durar muchisimo
			if(num_cuotas.equals("0")){
				num_cuotas = "9999";
			}
			//creamos el anticipo o descuento es un anticipo comun para muchas solicitudes
			OtrosDescuentos anticipo = new OtrosDescuentos();
			anticipo.setNombre(nombre);
			anticipo.setMonto(Integer.parseInt(monto));
			anticipo.setNumerCuotas(Integer.parseInt(num_cuotas));
			anticipo.setFechaInicio(desde);
			anticipo.setFechaFinal(calcularFechaFinal(fechainiciocalculo, num_cuotas));
			control.AgregaDescuento(anticipo);
			//el siguiente for guarda uno a uno la solicitud para los trabajadores
			for(int i=0;i<trabajadores.size();i++){
				Solicitud sol = new Solicitud();
				RelacionLaboral relacion = trabajadores.get(i);
				sol.setOtrosDescuentos(anticipo);
				sol.setRelacionLaboral(relacion);
				control.AgregaSolicitud(sol);
				
			}
			
			addActionMessage("se ha registrado el nuevo anticipo o descuento para el cargo seleccionado");
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
		cal.setTime(desde);
		cal.add(Calendar.MONTH, Integer.parseInt(cuotas)-1);   
		String newdate = dateformat.format(cal.getTime());
		System.out.println(newdate);
		return (getDate(newdate));
	}
	
	
	
	
	public Integer getCar_tra() {
		return car_tra;
	}
	public void setCar_tra(Integer carTra) {
		car_tra = carTra;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getNum_cuotas() {
		return num_cuotas;
	}
	public void setNum_cuotas(String numCuotas) {
		num_cuotas = numCuotas;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
//	public Date getHasta() {
//		return hasta;
//	}
//	public void setHasta(Date hasta) {
//		this.hasta = hasta;
//	}




	public Map getSession() {
		return session;
	}




	public void setSession(Map session) {
		this.session = session;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
