package org.remusystem.actions.grid;

import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.persistencia.InstitucionPrevision;
import org.remusystem.persistencia.InstitucionPrevisionDAO;
import org.remusystem.control.Controlador;

public class EditarAfp extends ActionSupport {
	private String oper;
	private String id;
	private String nombre;
    private String porcentajeDescuento;


public String execute(){
	
	System.out.println("oper: " + oper);
	System.out.println("id: " + id);
	
	InstitucionPrevision AFP = new InstitucionPrevision();
	InstitucionPrevisionDAO afpDAO = new InstitucionPrevisionDAO();
	Controlador control = Controlador.getInstance();
	
	if (oper.equalsIgnoreCase("add")){
		AFP.setPorcentajeDescuento(Double.parseDouble(porcentajeDescuento));
		AFP.setNombre(nombre);
		control.AgregaAFP(AFP);
	}else if(oper.equalsIgnoreCase("edit")) {
		AFP = afpDAO.findById(Integer.parseInt(id));
		AFP.setPorcentajeDescuento(Double.parseDouble(porcentajeDescuento));
		control.updateAFP(AFP);
	}
	
	return NONE;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
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


public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}



}
