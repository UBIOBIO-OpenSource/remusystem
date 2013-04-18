package listas;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import persistencia.MutualDAO;
import persistencia.Mutual;

import com.opensymphony.xwork2.ActionSupport;

public class Mutuales extends ActionSupport {
	
	private Map<Integer, String> lista_mutual;

	public Map<Integer, String> getLista_mutual() {
		return lista_mutual;
	}

	public void setLista_mutual(Map<Integer, String> lista_mutual) {
		this.lista_mutual = lista_mutual;
	}
	
public String execute(){
		MutualDAO mutual = new MutualDAO();
		List<String> listEditOptions;
		listEditOptions = mutual.findAll();
		
		lista_mutual = new HashMap<Integer, String>();
		Iterator iter = listEditOptions.iterator();
		
		while(iter.hasNext()){
			Mutual aux = (Mutual) iter.next();
			lista_mutual.put(aux.getId(), aux.getNombre());
			}
		
		return SUCCESS;
		
	}
	

}
