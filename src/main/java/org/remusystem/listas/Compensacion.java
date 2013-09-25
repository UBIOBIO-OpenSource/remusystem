package org.remusystem.listas;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.persistencia.CajaCompensacion;
import org.remusystem.persistencia.CajaCompensacionDAO;

public class Compensacion extends ActionSupport{
	private Map<Integer, String> lista_caja;

	public Map<Integer, String> getLista_caja() {
		return lista_caja;
	}

	public void setLista_caja(Map<Integer, String> lista_caja) {
		this.lista_caja = lista_caja;
	}
	
public String execute() {
		CajaCompensacionDAO caja = new CajaCompensacionDAO();
		List<String> listEditOptions;
		listEditOptions = caja.findAll();
		
		lista_caja = new HashMap<Integer, String>();
		Iterator iter = listEditOptions.iterator();
		
		while(iter.hasNext()){
			CajaCompensacion aux = (CajaCompensacion) iter.next();
			lista_caja.put(aux.getId(), aux.getNombre());
			}
		
		return SUCCESS;
		
	}

}
