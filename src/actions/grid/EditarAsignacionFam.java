package actions.grid;

import com.opensymphony.xwork2.ActionSupport;

import persistencia.AsignacionFamiliar;
import persistencia.AsignacionFamiliarDAO;
import control.Controlador;

public class EditarAsignacionFam extends ActionSupport {
	
	private String oper;
	private String id;
	private String tramo;
	private String monto;
	private String desde;
	private String hasta;
	
	
	public String execute(){
		
		System.out.println("oper: " + oper);
		System.out.println("id: " + id);
		
		AsignacionFamiliar asignacion = new AsignacionFamiliar();
		AsignacionFamiliarDAO aDAO = new AsignacionFamiliarDAO();
		Controlador control = Controlador.getInstance();
		
		if (oper.equalsIgnoreCase("add")){
			asignacion.setTramo(tramo);
			asignacion.setDesde(Integer.parseInt(desde));
			asignacion.setHasta(Integer.parseInt(hasta));
			asignacion.setMonto(Integer.parseInt(monto));
			control.AgregaAsignacionFamiliar(asignacion);
		}else if(oper.equalsIgnoreCase("edit")) {
			asignacion = aDAO.findById(Integer.parseInt(id));
			asignacion.setTramo(tramo);
			asignacion.setDesde(Integer.parseInt(desde));
			asignacion.setHasta(Integer.parseInt(hasta));
			asignacion.setMonto(Integer.parseInt(monto));
			control.updateAsignacionFamiliar(asignacion);
		}
		
		
		return NONE;
	
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


	public String getTramo() {
		return tramo;
	}


	public void setTramo(String tramo) {
		this.tramo = tramo;
	}


	public String getMonto() {
		return monto;
	}


	public void setMonto(String monto) {
		this.monto = monto;
	}


	public String getDesde() {
		return desde;
	}


	public void setDesde(String desde) {
		this.desde = desde;
	}


	public String getHasta() {
		return hasta;
	}


	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

}
