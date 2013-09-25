package org.remusystem.actions;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;
import org.remusystem.persistencia.Usuario;
import org.remusystem.persistencia.Empresa;
import org.remusystem.persistencia.CajaCompensacion;
import org.remusystem.persistencia.Mutual;
import org.remusystem.persistencia.EmpresaDAO;
import org.remusystem.persistencia.UsuarioDAO;


public class ModificarEmpresa extends ActionSupport implements SessionAware{

	private String nom_emp;
	private String gir_emp;
	private String dir_emp;
	private String tel_emp;
	private String fax_emp;
	private String email_emp;
	private Integer caj_emp;
	private Integer mut_emp;
	private String pw_actual;
	private String pw_new;
	private Map session;
	
	
	public String execute() throws Exception {
		Controlador control = Controlador.getInstance();
		UsuarioDAO userDAO = new UsuarioDAO();
		Empresa emp = (Empresa) session.get("empresa");
		EmpresaDAO empDAO = new EmpresaDAO();
		CajaCompensacion revCaj = control.buscaCaja(caj_emp);
		Mutual revMut = control.buscaMutual(mut_emp);
		Usuario userSession = (Usuario) session.get("User");
		
		if(pw_actual.equals(userSession.getContrasena())){
			
			emp.setNombre(nom_emp);
			emp.setGiro(gir_emp);
			emp.setDireccion(dir_emp);
			emp.setTelefono(tel_emp);
			emp.setFax(fax_emp);
			emp.setEmail(email_emp);
			if(revCaj!=null){
				emp.setCajaCompensacion(revCaj);
			}
			if(revMut!=null){
				emp.setMutual(revMut);
			}
			
			
			String pw = pw_new;
			String bandera = vacio(pw);
			
			if(bandera.equals("datos")){
				userSession.setContrasena(pw_new);
			}
			
			userSession.setNombres(nom_emp);
			userSession.setEmail(email_emp);
			userSession.setTipo("empresa");
			control.UpdateEmpresa(emp);
			control.updateUser(userSession);
			
			addActionMessage("Se han modificado los datos");
			return SUCCESS;
		} else {
			addActionError("La contrase�a actual que ha ingresado no coincide con la de nuestros datos");
			return ERROR;
		}
		
		
		
		
		
		
	}
	
	public String vacio(String q) {  
    if(q.isEmpty()){
    	return "vacio";
    }  
    return "datos";
}
	
	public String getNom_emp() {
		return nom_emp;
	}
	public void setNom_emp(String nom_emp) {
		this.nom_emp = nom_emp;
	}
	public String getGir_emp() {
		return gir_emp;
	}
	public void setGir_emp(String gir_emp) {
		this.gir_emp = gir_emp;
	}
	public String getDir_emp() {
		return dir_emp;
	}
	public void setDir_emp(String dir_emp) {
		this.dir_emp = dir_emp;
	}
	public String getTel_emp() {
		return tel_emp;
	}
	public void setTel_emp(String tel_emp) {
		this.tel_emp = tel_emp;
	}
	public String getFax_emp() {
		return fax_emp;
	}
	public void setFax_emp(String fax_emp) {
		this.fax_emp = fax_emp;
	}
	public String getEmail_emp() {
		return email_emp;
	}
	public void setEmail_emp(String email_emp) {
		this.email_emp = email_emp;
	}
	public Integer getCaj_emp() {
		return caj_emp;
	}
	public void setCaj_emp(Integer caj_emp) {
		this.caj_emp = caj_emp;
	}
	public Integer getMut_emp() {
		return mut_emp;
	}
	public void setMut_emp(Integer mut_emp) {
		this.mut_emp = mut_emp;
	}
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}


	public String getPw_new() {
		return pw_new;
	}


	public void setPw_new(String pw_new) {
		this.pw_new = pw_new;
	}

	public String getPw_actual() {
		return pw_actual;
	}

	public void setPw_actual(String pw_actual) {
		this.pw_actual = pw_actual;
	}


	
	
	
}
