package actions;

import java.util.List;  
import java.util.ArrayList;  
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import control.Controlador;

import persistencia.Trabajador;
import persistencia.TrabajadorDAO;
import persistencia.RelacionLaboral;
import persistencia.RelacionLaboralDAO;
import persistencia.InstitucionPrevision;
import persistencia.InstitucionPrevisionDAO;
import persistencia.InstitucionSalud;
import persistencia.InstitucionSaludDAO;
import persistencia.Usuario;
import persistencia.UsuarioDAO;
import persistencia.GrupoHabDesc;

public class VerFichaTrabajador extends ActionSupport implements SessionAware {
	private Map session;
	
	public String execute(){
		Controlador control = Controlador.getInstance();
		Usuario user = (Usuario) session.get("User");
		TrabajadorDAO tDAO = new TrabajadorDAO();
		InstitucionSalud salud = new InstitucionSalud();
		InstitucionSaludDAO sDAO = new InstitucionSaludDAO();
		InstitucionPrevision prevision = new InstitucionPrevision();
		InstitucionPrevisionDAO pDAO = new InstitucionPrevisionDAO();
		RelacionLaboral relacion = new RelacionLaboral();
		RelacionLaboralDAO rDAO = new RelacionLaboralDAO();
		GrupoHabDesc cargo = new GrupoHabDesc();
		Trabajador Trab = new Trabajador();
		
		String rutTrab = user.getRut();
		Trab = control.buscaTrabajador(rutTrab);
		relacion = control.buscaRelacionconTra(Trab.getId());
		salud = relacion.getInstitucionSalud();
		prevision = relacion.getInstitucionPrevision();
		cargo = relacion.getGrupoHabDesc();
		
		
		session.put("Rel_PRO", relacion);
		session.put("Trab_PRO", Trab);
		session.put("Sal_PRO", salud);
		session.put("Car_PRO", cargo);
		session.put("Pre_PRO", prevision);
		
		
		return SUCCESS;
	}
	


	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}



}
