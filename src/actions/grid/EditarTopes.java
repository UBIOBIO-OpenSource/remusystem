package actions.grid;

import com.opensymphony.xwork2.ActionSupport;

import persistencia.Topes;
import persistencia.TopesDAO;

import control.Controlador;

public class EditarTopes extends ActionSupport {
	
	private String oper;
	private String id;
	private String nombre;
	private String montoUf;
	
public String execute(){
	System.out.println("oper: " + oper);
	System.out.println("id: " + id);
	
	Controlador control = Controlador.getInstance();
	Topes tope = new Topes();
	TopesDAO tDAO = new TopesDAO();
	
	if (oper.equalsIgnoreCase("add")){
		tope.setNombre(nombre);
		tope.setMontoUf(Double.parseDouble(montoUf));
		control.AgregaTope(tope);
		
	}else if(oper.equalsIgnoreCase("edit")) {
		tope = tDAO.findById(Integer.parseInt(id));
		tope.setNombre(nombre);
		tope.setMontoUf(Double.parseDouble(montoUf));
		control.updateTope(tope);
		
	}
	
	return NONE;
}

public String getOper() {
	return oper;
}

public void setOper(String oper) {
	this.oper = oper;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getMontoUf() {
	return montoUf;
}

public void setMontoUf(String montoUf) {
	this.montoUf = montoUf;
}

}
