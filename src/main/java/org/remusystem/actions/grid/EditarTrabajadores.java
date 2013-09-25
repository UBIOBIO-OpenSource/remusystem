package org.remusystem.actions.grid;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;

import org.remusystem.persistencia.Empresa;
import org.remusystem.persistencia.RelacionLaboralDAO;
import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.persistencia.Usuario;

public class EditarTrabajadores extends ActionSupport implements SessionAware{
	
	private String oper;
	private String id;
	private String rut;
	private Map session;
	
public String execute(){
	
	if(oper.equalsIgnoreCase("edit")) {
		Controlador control = Controlador.getInstance();
		System.out.println("ide trabajador: "+id);
		session.remove("relacion");
		session.remove("trabajador");
		Usuario user = (Usuario) session.get("User");
		Empresa emp = control.buscaEmpresa(user.getRut());
		RelacionLaboralDAO rDAO = new RelacionLaboralDAO();
		Integer id_empresa = emp.getId();
		List<RelacionLaboral> rel = rDAO.findBYrutempresa(id_empresa);
		Iterator iter = rel.iterator();
		if(iter!=null){
		while(iter.hasNext()){
			RelacionLaboral objetoiter = (RelacionLaboral) iter.next();
//			TrabajadorDAO tDAO = new TrabajadorDAO();
//			Trabajador Trabajador_buscado = tDAO.findById(Integer.parseInt(id));
			if(objetoiter.getEstado().equals(true) && objetoiter.getTrabajador().getRut().equals(rut)){
				session.put("relacion", objetoiter);
				session.put("trabajador", objetoiter.getTrabajador());
			}
		}
		
	}
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

public Map getSession() {
	return session;
}

public void setSession(Map session) {
	this.session = session;
}

public String getRut() {
	return rut;
}

public void setRut(String rut) {
	this.rut = rut;
}
}
