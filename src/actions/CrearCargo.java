package actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import control.Controlador;
import persistencia.GrupoHabDesc;
import persistencia.GrupoHabDescDAO;

public class CrearCargo extends ActionSupport implements SessionAware{

	private Map session;
	private String nombre_cargo;
	
	public String execute(){
		
		Controlador control = Controlador.getInstance();
		
		GrupoHabDesc cargos = new GrupoHabDesc();
		GrupoHabDesc cargos2 = new GrupoHabDesc();
		GrupoHabDescDAO cDAO = new GrupoHabDescDAO();
		
		cargos2 = cDAO.findBynombre(nombre_cargo);
		if(cargos2!=null){
			addActionError("El Cargo que desea agregar ya se encuentra registrado");
			return ERROR;
		}else{
			cargos.setCargo(nombre_cargo);
			cDAO.save(cargos);
			addActionMessage("se ha creado el cargo con nombre: "+nombre_cargo);
			return SUCCESS;
		}
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public String getNombre_cargo() {
		return nombre_cargo;
	}

	public void setNombre_cargo(String nombreCargo) {
		nombre_cargo = nombreCargo;
	}
}
