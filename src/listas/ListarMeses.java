package listas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import persistencia.LiquidacionDeSueldo;
import persistencia.LiquidacionDeSueldoDAO;
import persistencia.RelacionLaboral;

import com.opensymphony.xwork2.ActionSupport;

public class ListarMeses extends ActionSupport{
	
	private Map<String, String> Lista_Meses;
	private Map session;
	
	public Map<String, String> getLista_Meses() {
		return Lista_Meses;
	}

	public void setLista_Meses(Map<String, String> listaMeses) {
		Lista_Meses = listaMeses;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}
	
	public String execute(){
		
		Lista_Meses = new HashMap<String, String>();
		
		Lista_Meses.put("Enero", "Enero");
		Lista_Meses.put("Febrero", "Febrero");
		Lista_Meses.put("Marzo", "Marzo");
		Lista_Meses.put("Abril", "Abril");
		Lista_Meses.put("Mayo", "Mayo");
		Lista_Meses.put("Junio", "Junio");
		Lista_Meses.put("Julio", "Julio");
		Lista_Meses.put("Agosto", "Agosto");
		Lista_Meses.put("Septiembre", "Septiembre");
		Lista_Meses.put("Octubre", "Octubre");
		Lista_Meses.put("Noviembre", "Noviembre");
		Lista_Meses.put("Diciembre", "Diciembre");
		
		/*RelacionLaboral rel = (RelacionLaboral) session.get("relacion");
		
		LiquidacionDeSueldoDAO lDAO = new LiquidacionDeSueldoDAO();
		List<LiquidacionDeSueldo> listliq = lDAO.findByIdRel(rel.getId());
		for(int i=0; i<listliq.size(); i++){
			Lista_Meses.put(listliq.get(i).getMes(), listliq.get(i).getMes());
		}*/
		System.out.println(Lista_Meses);
		
		return SUCCESS;
	}



	
}
