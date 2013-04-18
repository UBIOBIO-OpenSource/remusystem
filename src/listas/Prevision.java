package listas;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import persistencia.InstitucionPrevision;
import persistencia.InstitucionPrevisionDAO;

public class Prevision extends ActionSupport{
	private Map<Integer, String> lista_prevision;

public Map<Integer, String> getLista_prevision() {
		return lista_prevision;
	}

public void setLista_prevision(Map<Integer, String> lista_prevision) {
		this.lista_prevision = lista_prevision;
	}


public String execute() {
		InstitucionPrevisionDAO caja = new InstitucionPrevisionDAO();
		List<String> listEditOptions;
		listEditOptions = caja.findAll();
		
		lista_prevision = new HashMap<Integer, String>();
		Iterator iter = listEditOptions.iterator();
		
		while(iter.hasNext()){
			InstitucionPrevision aux = (InstitucionPrevision) iter.next();
			lista_prevision.put(aux.getId(), aux.getNombre());
			}
		
		return SUCCESS;
		
	}

}
