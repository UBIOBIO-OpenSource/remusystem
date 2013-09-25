package org.remusystem.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import org.remusystem.persistencia.Usuario;
import org.remusystem.persistencia.UsuarioDAO;

import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;

public class CambiarContrasenaTra extends ActionSupport implements SessionAware {
	
	private Map session;
	private String pw_actual;
	private String pw_new;
	private String pw_confirm;
	
	public String execute(){
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario user = (Usuario) session.get("User");
		if(user.getContrasena().equals(pw_actual)){
			user.setContrasena(pw_new);
			Controlador control = Controlador.getInstance();
			control.updateUser(user);
			addActionMessage("Se ha Modificado la Contrase�a");
			
			return SUCCESS;	
		}else {
			addActionError("La contrase�a actual no coincide con la registrada por favor revise");
			return ERROR;
		}
		
		
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public String getPw_actual() {
		return pw_actual;
	}

	public void setPw_actual(String pw_actual) {
		this.pw_actual = pw_actual;
	}

	public String getPw_new() {
		return pw_new;
	}

	public void setPw_new(String pw_new) {
		this.pw_new = pw_new;
	}

	public String getPw_confirm() {
		return pw_confirm;
	}

	public void setPw_confirm(String pw_confirm) {
		this.pw_confirm = pw_confirm;
	}

}
