package org.remusystem.actions.grid;

import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.persistencia.ImpuestoSegundaCategoria;
import org.remusystem.persistencia.ImpuestoSegundaCategoriaDAO;
import org.remusystem.control.Controlador;

public class EditarImpuestoUnico extends ActionSupport {
	private String oper;
	private String id;
	private String desde;
	private String hasta;
	private String factor;
	private String rebaja;
	
public String execute(){
	
	System.out.println("oper: " + oper);
	System.out.println("id: " + id);
	
	ImpuestoSegundaCategoria impuesto = new ImpuestoSegundaCategoria();
	ImpuestoSegundaCategoriaDAO impuestoDAO = new ImpuestoSegundaCategoriaDAO();
	Controlador control = Controlador.getInstance();
	
	if (oper.equalsIgnoreCase("add")){
		impuesto.setDesde(Double.parseDouble(desde));
		impuesto.setHasta(Double.parseDouble(hasta));
		impuesto.setRebaja(Double.parseDouble(rebaja));
		impuesto.setFactor(Double.parseDouble(factor));
		control.AgregaImpuestoUnico(impuesto);
	}else if(oper.equalsIgnoreCase("edit")) {
		impuesto = impuestoDAO.findById(Integer.parseInt(id));
		impuesto.setDesde(Double.parseDouble(desde));
		impuesto.setHasta(Double.parseDouble(hasta));
		impuesto.setRebaja(Double.parseDouble(rebaja));
		impuesto.setFactor(Double.parseDouble(factor));
		control.updateImpuestoUnico(impuesto);
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

public String getDesde() {
	return desde;
}

public void setDesde(String desde) {
	this.desde = desde;
}

public String getHasta() {
	return hasta;
}

public void setHasta(String hasta) {
	this.hasta = hasta;
}

public String getFactor() {
	return factor;
}

public void setFactor(String factor) {
	this.factor = factor;
}

public String getRebaja() {
	return rebaja;
}

public void setRebaja(String rebaja) {
	this.rebaja = rebaja;
}

}
