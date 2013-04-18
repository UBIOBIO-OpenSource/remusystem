package listas;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import persistencia.InstitucionSalud;
import persistencia.InstitucionSaludDAO;

public class Salud extends ActionSupport{
	private Map<Integer, String> lista_salud;


	
public Map<Integer, String> getLista_salud() {
		return lista_salud;
	}



	public void setLista_salud(Map<Integer, String> lista_salud) {
		this.lista_salud = lista_salud;
	}



public String execute() {
		InstitucionSaludDAO caja = new InstitucionSaludDAO();
		List<String> listEditOptions;
		listEditOptions = caja.findAll();
		
		lista_salud = new HashMap<Integer, String>();
		Iterator iter = listEditOptions.iterator();
		
		while(iter.hasNext()){
			InstitucionSalud aux = (InstitucionSalud) iter.next();
			lista_salud.put(aux.getId(), aux.getNombre());
			}
		
		return SUCCESS;
		
	}

}
