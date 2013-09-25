package org.remusystem.actions;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;
import org.remusystem.persistencia.Usuario;

public class Login extends ActionSupport {
	
	private String rut;
	private String password;
	private Map<String, Object> session;
	

	public String login(){
		Controlador control = Controlador.getInstance();
		session = new HashMap<String, Object>();
		session = ActionContext.getContext().getSession();
		String result = "";
		session.clear();
		
		
		Usuario user = control.validaUsuario(rut, password);
		if (user != null) {
			session.put("User", user);
			session.put("rut", user.getRut());
			session.put("nombre", user.getNombres());
			session.put("id_user", user.getId());
			
				if(user.getTipo().equals("admin")){
				session.put("tipo", "admin");
				result = "admin";
				return result;
				} else if(user.getTipo().equals("trabajador")){
				session.put("tipo", "trabajador");
				result = "trabajador";
			    return result;
				} else {
					session.put("tipo", "empresa");
					result = "empresa";
					return result;
			}
			}
			addActionError("Usuario y/o Contrase�a invalido revise los datos ingresados");
			result = "error";
		    return result;		
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
