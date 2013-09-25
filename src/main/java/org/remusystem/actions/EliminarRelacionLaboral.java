package org.remusystem.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;
import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.persistencia.RelacionLaboralDAO;

public class EliminarRelacionLaboral extends ActionSupport implements SessionAware {

	//Session
		private Map session;
		
		public String execute(){
			Controlador control = Controlador.getInstance();
			RelacionLaboral rel = (RelacionLaboral) session.get("relacion");
			RelacionLaboralDAO relDAO = new RelacionLaboralDAO();
			if(session.get("relacion")!=null){
						
//			int respuesta = JOptionPane.showConfirmDialog(null, "�Confirma el despido?", "�Desea despedir al Empleado?", JOptionPane.OK_CANCEL_OPTION);
//			
//			switch(respuesta) {
//			
//			case JOptionPane.OK_OPTION:
		
			//Operaciones en caso afirmativo
				rel.setEstado(false);
				control.updateRelacion(rel);
				session.remove("relacion");
				session.remove("trabajador");
				addActionMessage("se dio de baja al trabajador");
			
//			break;
//			
//			case JOptionPane.CANCEL_OPTION:
//			
//			//Operaciones en caso negativo
//			
//			break;
//			
//			
//			}
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

		
}
