package actions.grid;

import com.opensymphony.xwork2.ActionSupport;

import persistencia.Valores;
import persistencia.ValoresDAO;

import control.Controlador;

public class EditarValores extends ActionSupport {
	
	private String oper;
	private String id;
	private String nombre;
	private String monto;
	
public String execute(){
	System.out.println("oper: " + oper);
	System.out.println("id: " + id);
	
	Controlador control = Controlador.getInstance();
	Valores valor = new Valores();
	ValoresDAO vDAO = new ValoresDAO();
	
	if (oper.equalsIgnoreCase("add")){
		valor.setNombre(nombre);
		valor.setMonto(Double.parseDouble(monto));
		control.AgregaValor(valor);
		
	}else if(oper.equalsIgnoreCase("edit")) {
		valor = vDAO.findById(Integer.parseInt(id));
		valor.setNombre(nombre);
		valor.setMonto(Double.parseDouble(monto));
		control.updateValor(valor);
		
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

public String getMonto() {
	return monto;
}

public void setMonto(String monto) {
	this.monto = monto;
}


}
