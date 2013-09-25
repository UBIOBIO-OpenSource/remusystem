package org.remusystem.actions.grid;

import java.util.List;
import java.util.ArrayList;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.persistencia.ImpuestoSegundaCategoria;
import org.remusystem.persistencia.ImpuestoSegundaCategoriaDAO;
import org.remusystem.control.Controlador;

public class ListarImpuestoUnico extends ActionSupport {
	
	private ImpuestoSegundaCategoriaDAO ImpuestoUnicoDAO;
	private List<ImpuestoSegundaCategoria> gridModel;
	private List<ImpuestoSegundaCategoria> myImpuesto;
	
	//N�mero de filas que queremos tener dentro del grid - atributo "rowNum" del grid
    private Integer rows = 0;
    
    //P�gina actual. Por defecto el grid lo pone en 1
    private Integer page = 0;
    
    //Orden - asc o desc
    private String sord;
    
    //�ndice de la fila - i.e. click del usuario para ordenar
    private String sidx;
    
    //campo de b�squeda
    private String searchField;
    
    //cadena de b�squeda
    
    private String searchString;
    
    //La operaci�n de b�squeda ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    private String searchOper;
    
    //P�ginas totales
    private Integer total = 0;
    
    //todos los registros
    private Integer records = 0;
    
    private boolean loadonce = false;
    
public String execute(){
	
	Controlador control = Controlador.getInstance();
	
	//N�mero de registros(Select count(*) from users)
	setRecords(control.findAllImpuestoUnico().size());
	//Calcula HASTA qu� registro ser� la consulta, suponiendo que rows=10....
    int to = (getRows() * getPage());//.....la primera vez to=10  (10*1)
    //Calcula DESDE qu� registro se har� la consulta...
    int from = to - getRows();//....from=0  (10-10)
    //si la variable "to" sobrepasa el n�mero de registros disponible, entonces le ponemos ese valor m�ximo de registros.
    if(to>getRecords())to=getRecords();
    List<ImpuestoSegundaCategoria> impuesto = new ArrayList<ImpuestoSegundaCategoria>();
    for(int i = from ; i < to; i++){
    	myImpuesto = control.findAllImpuestoUnico();
    	impuesto.add(myImpuesto.get(i));
    	System.out.println("ID: " +myImpuesto.get(i).getId());
    }
    
    setGridModel(impuesto);
    //calcula el total de p�ginas que genera la consulta
	    total = (int) Math.ceil((double) getRecords() / (double) getRows());
      
  	return SUCCESS;
	
}
public ImpuestoSegundaCategoriaDAO getImpuestoUnicoDAO() {
	return ImpuestoUnicoDAO;
}
public void setImpuestoUnicoDAO(ImpuestoSegundaCategoriaDAO impuestoUnicoDAO) {
	ImpuestoUnicoDAO = impuestoUnicoDAO;
}
public List<ImpuestoSegundaCategoria> getGridModel() {
	return gridModel;
}
public void setGridModel(List<ImpuestoSegundaCategoria> gridModel) {
	this.gridModel = gridModel;
}
public List<ImpuestoSegundaCategoria> getMyImpuesto() {
	return myImpuesto;
}
public void setMyImpuesto(List<ImpuestoSegundaCategoria> myImpuesto) {
	this.myImpuesto = myImpuesto;
}
public Integer getRows() {
	return rows;
}
public void setRows(Integer rows) {
	this.rows = rows;
}
public Integer getPage() {
	return page;
}
public void setPage(Integer page) {
	this.page = page;
}
public String getSord() {
	return sord;
}
public void setSord(String sord) {
	this.sord = sord;
}
public String getSidx() {
	return sidx;
}
public void setSidx(String sidx) {
	this.sidx = sidx;
}
public String getSearchField() {
	return searchField;
}
public void setSearchField(String searchField) {
	this.searchField = searchField;
}
public String getSearchString() {
	return searchString;
}
public void setSearchString(String searchString) {
	this.searchString = searchString;
}
public String getSearchOper() {
	return searchOper;
}
public void setSearchOper(String searchOper) {
	this.searchOper = searchOper;
}
public Integer getTotal() {
	return total;
}
public void setTotal(Integer total) {
	this.total = total;
}
public Integer getRecords() {
	return records;
}
public void setRecords(Integer records) {
	this.records = records;
	if (this.records > 0 && this.rows > 0) {
    	this.total = (int) Math.ceil((double) this.records / (double) this.rows);
    } else {
    	this.total = 0;
    }
}
public boolean isLoadonce() {
	return loadonce;
}
public void setLoadonce(boolean loadonce) {
	this.loadonce = loadonce;
}

}
