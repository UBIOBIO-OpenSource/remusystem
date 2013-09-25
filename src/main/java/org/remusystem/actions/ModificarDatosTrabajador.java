package org.remusystem.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;
import org.remusystem.persistencia.GrupoHabDesc;
import org.remusystem.persistencia.InstitucionPrevision;
import org.remusystem.persistencia.InstitucionSalud;
import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.persistencia.RelacionLaboralDAO;
import org.remusystem.persistencia.Trabajador;
import org.remusystem.persistencia.TrabajadorDAO;
import org.remusystem.persistencia.Usuario;
import org.remusystem.persistencia.UsuarioDAO;

public class ModificarDatosTrabajador extends ActionSupport implements SessionAware {

	//Session
		private Map session;
		
	//variables datos del trabajador
		private String nom_tra;
		private String app_tra;
		private String apm_tra;
		private String dir_tra;
		private String tel_tra;
		private String cel_tra;
		private String email_tra;
		private String num_car_tra;
		
	//var relacion laboral
		private String suel_bas_tra;
		private Integer sal_tra;
		private Integer pre_tra;
		private Integer car_tra;
		private String valor_plan;
		
		public String execute(){
			Controlador control = Controlador.getInstance();
			Trabajador trab = (Trabajador) session.get("trabajador");
			TrabajadorDAO trabDAO = new TrabajadorDAO();
			RelacionLaboral rel = (RelacionLaboral) session.get("relacion");
			RelacionLaboralDAO relDAO = new RelacionLaboralDAO();
			
			UsuarioDAO uDAO = new UsuarioDAO();
			
			if(session.get("relacion")!=null){
			trab.setNombre(nom_tra);
			trab.setApellidoPaterno(app_tra);
			trab.setApellidoMaterno(apm_tra);
			trab.setDireccion(dir_tra);
			trab.setTelefonoFijo(tel_tra);
			trab.setCelular(cel_tra);
			trab.setEmail(email_tra);
			trab.setNumeroCargas(Integer.parseInt(num_car_tra));
			control.updateTrabajador(trab);
			Usuario user = uDAO.findByRutString(trab.getRut());
			user.setApellidoMaterno(apm_tra);
			user.setApellidoPaterno(app_tra);
			user.setEmail(email_tra);
			user.setNombres(nom_tra);
			control.updateUser(user);
			
			InstitucionSalud revSal = control.buscaSalud(sal_tra); //busco la inst de salud segun ID
			
			if(revSal!=null){
				
				rel.setInstitucionSalud(revSal);
			}
			
			if(sal_tra!=1){
				rel.setValorPlanIsapre(Double.parseDouble(valor_plan));
			}else{
				Double fonasa = 0.0;
				rel.setValorPlanIsapre(fonasa);
			}
			
			InstitucionPrevision revPre = control.buscaPrevision(pre_tra); //busco la inst de prev segun ID
			if(revPre!=null){
				
				rel.setInstitucionPrevision(revPre);
			}
			
			GrupoHabDesc revCar = control.buscaCargo(car_tra); //busco el cargo segun ID
			if(revCar!=null){
				
				rel.setGrupoHabDesc(revCar);
			}
			
			rel.setSueldoBase(Integer.parseInt(suel_bas_tra));
			control.updateRelacion(rel);
			addActionMessage("Datos Actualizados");
			
			return SUCCESS;
			}else{
					addActionError("debe realizar una busqueda primero");
					return ERROR;
			}
			
		}

		public Map getSession() {
			return session;
		}

		public void setSession(Map session) {
			this.session = session;
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

		public String getDir_tra() {
			return dir_tra;
		}

		public void setDir_tra(String dir_tra) {
			this.dir_tra = dir_tra;
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

		public String getValor_plan() {
			return valor_plan;
		}

		public void setValor_plan(String valorPlan) {
			valor_plan = valorPlan;
		}
}
