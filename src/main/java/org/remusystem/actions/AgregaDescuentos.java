package org.remusystem.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;
import org.remusystem.persistencia.OtrosDescuentos;
import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.persistencia.Solicitud;

public class AgregaDescuentos extends ActionSupport implements SessionAware {
	private Map session;
	private String nom_ant;
	private String num_cuotas;
	private String monto_ant;
	
	
	
	public String execute(){
		Controlador control = Controlador.getInstance();
		OtrosDescuentos desc = new OtrosDescuentos();
		Solicitud soli = new Solicitud();
		RelacionLaboral rel = (RelacionLaboral) session.get("relacion");
		if(rel!=null){
			desc.setNombre(nom_ant);
			desc.setNumerCuotas(Integer.parseInt(num_cuotas));
			desc.setMonto(Integer.parseInt(monto_ant));
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha_inic = new Date();
			desc.setFechaInicio(fecha_inic);
			if(num_cuotas!="0"){
				String hoy = formatoFecha.format(new Date());
				String [] dataTemp = hoy.split("-");
				Calendar c = Calendar.getInstance();
				c.set(Integer.parseInt(dataTemp[0]), Integer.parseInt(dataTemp[1])- 1, Integer.parseInt(dataTemp[2]));
				c.add(Calendar.MONTH, Integer.parseInt(num_cuotas));
				desc.setFechaFinal(c.getTime());
				addFieldError("exito", "Se ha agregado un nuevo descuento pagadero en "+num_cuotas+", la ultima cuota para el mes de "+c.getTime().getMonth()+"");
				
			}
			control.AgregaDescuento(desc);
			soli.setOtrosDescuentos(desc);
			soli.setRelacionLaboral(rel);
			control.AgregaSolicitud(soli);
			addFieldError("exito", "Descuento Agregado con exito aplicable por tiempo indefinido");
			return SUCCESS;
		}else{
			addFieldError("Error_anticipo", "No ha Realizado una busqueda previa del trabajador");
			return ERROR;
		}
		
	}
	
	
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	public String getNom_ant() {
		return nom_ant;
	}
	public void setNom_ant(String nom_ant) {
		this.nom_ant = nom_ant;
	}
	public String getNum_cuotas() {
		return num_cuotas;
	}
	public void setNum_cuotas(String num_cuotas) {
		this.num_cuotas = num_cuotas;
	}
	public String getMonto_ant() {
		return monto_ant;
	}
	public void setMonto_ant(String monto_ant) {
		this.monto_ant = monto_ant;
	}
	
	
	

}
