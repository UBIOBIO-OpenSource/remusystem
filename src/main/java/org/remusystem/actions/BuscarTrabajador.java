package org.remusystem.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;
import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.persistencia.RelacionLaboralDAO;
import org.remusystem.persistencia.Usuario;
import org.remusystem.persistencia.Empresa;

public class BuscarTrabajador extends ActionSupport implements SessionAware{
	private String nom_tra;
	private String rut_tra;
	private String app_tra;
	private String apm_tra;
	private List<RelacionLaboral> rel;
	private Map session;
	
	public String execute(){
		Controlador control = Controlador.getInstance();
		session.remove("relacion");
		session.remove("trabajador");
		Usuario user = (Usuario) session.get("User");
		Empresa emp = control.buscaEmpresa(user.getRut());
		RelacionLaboralDAO rDAO = new RelacionLaboralDAO();
		Integer id_empresa = emp.getId();
		rel = rDAO.findBYrutempresa(id_empresa);
		System.out.println(rut_tra);
		if(rel!=null){
		Iterator iter = rel.iterator();
		if(iter!=null){
		while(iter.hasNext()){
			RelacionLaboral objetoiter = (RelacionLaboral) iter.next();
			if(objetoiter.getEstado().equals(true) && objetoiter.getTrabajador().getRut().equals(rut_tra)){
				session.put("relacion", objetoiter);
				session.put("trabajador", objetoiter.getTrabajador());
				return SUCCESS;
				
			}
		}
		addActionError("El rut del trabajador no coincide con los de su empresa");
		return ERROR;
		}else{
			addActionError("El rut ingresado no coincide con ningun trabajador de su empresa");
			return ERROR;
		}
		}else{
			addActionError("debe registrar trabajadores para su empresa");
			return ERROR;
		}
	}



	public String getNom_tra() {
		return nom_tra;
	}


	public void setNom_tra(String nom_tra) {
		this.nom_tra = nom_tra;
	}


	public String getRut_tra() {
		return rut_tra;
	}


	public void setRut_tra(String rut_tra) {
		this.rut_tra = rut_tra;
	}


	public String getApp_tra() {
		return app_tra;
	}


	public void setApp_tra(String app_tra) {
		this.app_tra = app_tra;
	}


	public String getApm_tra() {
		return apm_tra;
	}


	public void setApm_tra(String apm_tra) {
		this.apm_tra = apm_tra;
	}


	public Map getSession() {
		return session;
	}


	public void setSession(Map session) {
		this.session = session;
	}


	public List<RelacionLaboral> getRel() {
		return rel;
	}


	public void setRel(List<RelacionLaboral> rel) {
		this.rel = rel;
	}




}
