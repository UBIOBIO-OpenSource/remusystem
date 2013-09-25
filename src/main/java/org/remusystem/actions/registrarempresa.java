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


public class registrarempresa extends ActionSupport implements SessionAware{

	private String rut_emp;
	private String nom_emp;
	private String gir_emp;
	private String dir_emp;
	private String tel_emp;
	private String fax_emp;
	private String email_emp;
	private Integer caj_emp;
	private Integer mut_emp;
	private Integer ID_usuario;
	private Map session;
	
	
	public String execute() throws Exception {
		Controlador control = Controlador.getInstance();
		Usuario user = new Usuario();
		UsuarioDAO userDAO = new UsuarioDAO();
		Empresa emp = new Empresa();
		EmpresaDAO empDAO = new EmpresaDAO();
		ID_usuario = (Integer) session.get("id_user");
		Empresa revEmp = control.buscaEmpresa(rut_emp);
		CajaCompensacion revCaj = control.buscaCaja(caj_emp);
		Mutual revMut = control.buscaMutual(mut_emp);
		Usuario userSession = (Usuario) session.get("User");
		if(revEmp==null){
			emp.setRut(rut_emp);
			emp.setNombre(nom_emp);
			emp.setGiro(gir_emp);
			emp.setDireccion(dir_emp);
			emp.setTelefono(tel_emp);
			emp.setFax(fax_emp);
			emp.setEmail(email_emp);
			emp.setCajaCompensacion(revCaj);
			emp.setMutual(revMut);
			emp.setUsuario(userSession);
			control.AgregaEmpresa(emp);
			addActionMessage("Se ha guardado la nueva empresa si desea registre otra");
			
			user.setRut(rut_emp);
			user.setContrasena(rut_emp);
			user.setNombres(nom_emp);
			user.setEmail(email_emp);
			user.setTipo("empresa");
			control.AgregaUsuario(user);
			
			addActionMessage("Se ha registrado un usuario con los datos de la empresa, su user y pw es el rut con puntos y guion");
			return SUCCESS;
		} else {
			addActionError("La Empresa que intenta Registrar ya se encuentra en nuestra Base de Datos");
			return ERROR;
		}
		
		
		
		
		
		
	}
	
	public Integer getID_usuario() {
		return ID_usuario;
	}


	public void setID_usuario(Integer iD_usuario) {
		ID_usuario = iD_usuario;
	}

	public String getRut_emp() {
		return rut_emp;
	}
	public void setRut_emp(String rut_emp) {
		this.rut_emp = rut_emp;
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


	
	
	
}
