package org.remusystem.listas;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.persistencia.GrupoHabDesc;
import org.remusystem.persistencia.GrupoHabDescDAO;

public class Cargos extends ActionSupport{
	private Map<Integer, String> lista_cargos;


public Map<Integer, String> getLista_cargos() {
		return lista_cargos;
	}


	public void setLista_cargos(Map<Integer, String> lista_cargos) {
		this.lista_cargos = lista_cargos;
	}


public String execute() {
		GrupoHabDescDAO caja = new GrupoHabDescDAO();
		List<String> listEditOptions;
		listEditOptions = caja.findAll();
		
		lista_cargos = new HashMap<Integer, String>();
		Iterator iter = listEditOptions.iterator();
		
		while(iter.hasNext()){
			GrupoHabDesc aux = (GrupoHabDesc) iter.next();
			lista_cargos.put(aux.getId(), aux.getCargo());
			}
		
		return SUCCESS;
		
	}

}