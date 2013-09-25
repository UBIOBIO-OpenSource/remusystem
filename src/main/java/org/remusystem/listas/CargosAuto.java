package org.remusystem.listas;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.persistencia.GrupoHabDesc;
import org.remusystem.persistencia.GrupoHabDescDAO;

public class CargosAuto extends ActionSupport{
	private Map<String, String> lista_cargos;


public Map<String, String> getLista_cargos() {
		return lista_cargos;
	}


	public void setLista_cargos(Map<String, String> lista_cargos) {
		this.lista_cargos = lista_cargos;
	}


public String execute() {
		GrupoHabDescDAO caja = new GrupoHabDescDAO();
		List<String> listEditOptions;
		listEditOptions = caja.findAll();
		
		lista_cargos = new HashMap<String, String>();
		Iterator iter = listEditOptions.iterator();
		
		while(iter.hasNext()){
			GrupoHabDesc aux = (GrupoHabDesc) iter.next();
			lista_cargos.put(aux.getCargo(), aux.getCargo());
			}
		
		return SUCCESS;
		
	}

}