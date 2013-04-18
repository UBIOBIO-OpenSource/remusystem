package actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import control.Controlador;
import persistencia.RelacionLaboral;
import persistencia.RelacionLaboralDAO;
import persistencia.Trabajador;
import persistencia.TrabajadorDAO;
import persistencia.InstitucionPrevision;
import persistencia.InstitucionPrevisionDAO;
import persistencia.InstitucionSalud;
import persistencia.InstitucionSaludDAO;
import persistencia.Empresa;
import persistencia.EmpresaDAO;
import persistencia.GrupoHabDesc;
import persistencia.GrupoHabDescDAO;
import persistencia.Usuario;
import persistencia.UsuarioDAO;

public class RegistrarTrabajador extends ActionSupport implements SessionAware {
	
//Session
	private Map session;
	
//variables datos del trabajador
	private String rut_tra;
	private String nom_tra;
	private String app_tra;
	private String apm_tra;
	private String nac_tra;
	private Date fec_nac_tra;
	private String dir_tra;
	private Boolean sex_mas_tra;
	private Boolean sex_fem_tra;
	private String tel_tra;
	private String cel_tra;
	private String email_tra;
	private String num_car_tra;
	
	
//var relacion laboral
	private String tip_con_tra;
	private Date fec_ini_con;
	private Date fec_fin_con;
	private String suel_bas_tra;
	private Integer sal_tra;
	private Integer pre_tra;
	private Integer car_tra;
	private String valor_isapre;
	
	public String execute() throws Exception {
		Controlador control = Controlador.getInstance();
		Usuario user = new Usuario();
		UsuarioDAO userDAO = new UsuarioDAO();
		Trabajador trab = new Trabajador();
		TrabajadorDAO trabDAO = new TrabajadorDAO();
		RelacionLaboral rel = new RelacionLaboral();
		RelacionLaboralDAO relDAO = new RelacionLaboralDAO();
		Usuario userSession = (Usuario) session.get("User"); //trae los datos de la session
		
		Integer cargas = Integer.parseInt(num_car_tra); //transformo en entero el numero de cargas
		Integer sueldo = Integer.parseInt(suel_bas_tra); //Transformo a entero el sueldo
		InstitucionSalud revSal = control.buscaSalud(sal_tra); //busco la inst de salud segun ID
		InstitucionPrevision revPre = control.buscaPrevision(pre_tra); //busco la inst de prev segun ID
		GrupoHabDesc revCar = control.buscaCargo(car_tra); //busco el cargo segun ID
		Empresa revEmp = control.buscaEmpresa(userSession.getRut());//Busca empresa de la session
		List<Trabajador> listado = trabDAO.findByRUTtodos(rut_tra);
		if(listado!=null){
			for(int i=0; i<listado.size(); i++){
				RelacionLaboral rel2 = relDAO.findByIdTrabajadorEstadoEmpresa(listado.get(i).getId(), revEmp.getId());
				if(rel2!=null){
					addActionError("El trabajador ingresado ya mantiene una relacion laboral vigente con la empresa por favor verifique");
					return ERROR;
				}
			}
		}
		
		//Guardando los datos del trabajador
		trab.setRut(rut_tra);
		trab.setNombre(nom_tra);
		trab.setApellidoPaterno(app_tra);
		trab.setApellidoMaterno(apm_tra);
		trab.setFechaNacimiento(fec_nac_tra);
		trab.setNacionalidad(nac_tra);
		if(sex_fem_tra){
			trab.setSexo(sex_fem_tra);
		}
		if(sex_mas_tra){
			trab.setSexo(sex_mas_tra);
		}
		trab.setDireccion(dir_tra);
		trab.setTelefonoFijo(tel_tra);
		trab.setCelular(cel_tra);
		trab.setEmail(email_tra);
		trab.setNumeroCargas(cargas);
		control.AgregaTrabajador(trab);
		
		
		//Guardando el nuevo Usuario
		user.setRut(rut_tra);
		user.setContrasena(rut_tra);
		user.setTipo("trabajador");
		user.setNombres(nom_tra);
		user.setApellidoPaterno(app_tra);
		user.setApellidoMaterno(apm_tra);
		user.setEmail(email_tra);
		control.AgregaUsuario(user);
		
		//Guardando la Relacion Laboral
		rel.setTipoContrato(tip_con_tra);
		rel.setFechaInicio(fec_ini_con);
		if(tip_con_tra.equals("fijo")){
			rel.setFechaFin(fec_fin_con);	
		}
		if(sal_tra!=1){
			rel.setValorPlanIsapre(Double.parseDouble(valor_isapre));
		}else{
			rel.setValorPlanIsapre(0.0);
		}
		rel.setSueldoBase(sueldo);
		rel.setEmpresa(revEmp);
		rel.setInstitucionSalud(revSal);
		rel.setInstitucionPrevision(revPre);
		rel.setGrupoHabDesc(revCar);
		rel.setTrabajador(trab);
		rel.setEstado(true);
		control.AgregaRelacionLaboral(rel);
		addActionMessage("Se ha registrado el trabajador, sus credenciales de sesion son rut: "+rut_tra+" y password: "+rut_tra);
		
		
		
		
	
		return SUCCESS;
	}
	
	
	
	
	
	
	
	public String getRut_tra() {
		return rut_tra;
	}
	public void setRut_tra(String rut_tra) {
		this.rut_tra = rut_tra;
	}
	public String getNom_tra() {
		return nom_tra;
	}
	public void setNom_tra(String nom_tra) {
		this.nom_tra = nom_tra;
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
	public String getNac_tra() {
		return nac_tra;
	}
	public void setNac_tra(String nac_tra) {
		this.nac_tra = nac_tra;
	}
	public Date getFec_nac_tra() {
		return fec_nac_tra;
	}
	public void setFec_nac_tra(Date fec_nac_tra) {
		this.fec_nac_tra = fec_nac_tra;
	}
	public String getDir_tra() {
		return dir_tra;
	}
	public void setDir_tra(String dir_tra) {
		this.dir_tra = dir_tra;
	}
	public Boolean getSex_mas_tra() {
		return sex_mas_tra;
	}
	public void setSex_mas_tra(Boolean sex_mas_tra) {
		this.sex_mas_tra = sex_mas_tra;
	}
	public Boolean getSex_fem_tra() {
		return sex_fem_tra;
	}
	public void setSex_fem_tra(Boolean sex_fem_tra) {
		this.sex_fem_tra = sex_fem_tra;
	}
	public String getTel_tra() {
		return tel_tra;
	}
	public void setTel_tra(String tel_tra) {
		this.tel_tra = tel_tra;
	}
	public String getCel_tra() {
		return cel_tra;
	}
	public void setCel_tra(String cel_tra) {
		this.cel_tra = cel_tra;
	}
	public String getEmail_tra() {
		return email_tra;
	}
	public void setEmail_tra(String email_tra) {
		this.email_tra = email_tra;
	}
	public String getNum_car_tra() {
		return num_car_tra;
	}
	public void setNum_car_tra(String num_car_tra) {
		this.num_car_tra = num_car_tra;
	}
	public String getTip_con_tra() {
		return tip_con_tra;
	}
	public void setTip_con_tra(String tip_con_tra) {
		this.tip_con_tra = tip_con_tra;
	}
	public Date getFec_ini_con() {
		return fec_ini_con;
	}
	public void setFec_ini_con(Date fec_ini_con) {
		this.fec_ini_con = fec_ini_con;
	}
	public Date getFec_fin_con() {
		return fec_fin_con;
	}
	public void setFec_fin_con(Date fec_fin_con) {
		this.fec_fin_con = fec_fin_con;
	}
	public String getSuel_bas_tra() {
		return suel_bas_tra;
	}
	public void setSuel_bas_tra(String suel_bas_tra) {
		this.suel_bas_tra = suel_bas_tra;
	}
	public Integer getSal_tra() {
		return sal_tra;
	}
	public void setSal_tra(Integer sal_tra) {
		this.sal_tra = sal_tra;
	}
	public Integer getPre_tra() {
		return pre_tra;
	}
	public void setPre_tra(Integer pre_tra) {
		this.pre_tra = pre_tra;
	}
	public Integer getCar_tra() {
		return car_tra;
	}
	public void setCar_tra(Integer car_tra) {
		this.car_tra = car_tra;
	}
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}







	public String getValor_isapre() {
		return valor_isapre;
	}







	public void setValor_isapre(String valorIsapre) {
		valor_isapre = valorIsapre;
	}
	
	
	
	

}
