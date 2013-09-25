package org.remusystem.listas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.remusystem.persistencia.LiquidacionDeSueldo;
import org.remusystem.persistencia.LiquidacionDeSueldoDAO;
import org.remusystem.persistencia.RelacionLaboral;

import com.opensymphony.xwork2.ActionSupport;

public class ListarAnios extends ActionSupport{
	
	private Map<String, String> Lista_Anios;
	private Map session;
	
	public Map<String, String> getLista_Anios() {
		return Lista_Anios;
	}

	public void setLista_Anios(Map<String, String> listaAnios) {
		Lista_Anios = listaAnios;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	
	public String execute(){
		
		Lista_Anios = new HashMap<String, String>();
		
		Lista_Anios.put("2011", "2011");
		Lista_Anios.put("2012", "2012");
		Lista_Anios.put("2013", "2013");
		Lista_Anios.put("2014", "2014");
		Lista_Anios.put("2015", "2015");
		
		/*RelacionLaboral rel = (RelacionLaboral) session.get("relacion");
		
		LiquidacionDeSueldoDAO lDAO = new LiquidacionDeSueldoDAO();
		List<LiquidacionDeSueldo> listliq = lDAO.findByIdRel(rel.getId());
		for(int i=0; i<listliq.size(); i++){
			Lista_Anios.put(listliq.get(i).getAnio(), listliq.get(i).getAnio());
		}*/
		
		System.out.println(Lista_Anios);
		
		
		
		return SUCCESS;
	}

	
	
}
