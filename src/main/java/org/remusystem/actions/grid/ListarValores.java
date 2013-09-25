package org.remusystem.actions.grid;

import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import org.remusystem.persistencia.Valores;
import org.remusystem.persistencia.ValoresDAO;
import org.remusystem.control.Controlador;

public class ListarValores extends ActionSupport {
	
	private ValoresDAO valoresDAO;
	private List<Valores> gridModel;
	private List<Valores> myValores;
	
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
    	
    	System.out.println("searchString2: "+searchString);
    	System.out.println("searchField2: "+searchField);
    	System.out.println("searchOper2: "+searchOper);
    	
    	Controlador control = Controlador.getInstance();
    	
    	//N�mero de registros(Select count(*) from users)
    	setRecords(control.findAllValores().size());
    	//Calcula HASTA qu� registro ser� la consulta, suponiendo que rows=10....
        int to = (getRows() * getPage());//.....la primera vez to=10  (10*1)
        //Calcula DESDE qu� registro se har� la consulta...
        int from = to - getRows();//....from=0  (10-10)
        //si la variable "to" sobrepasa el n�mero de registros disponible, entonces le ponemos ese valor m�ximo de registros.
        if(to>getRecords())to=getRecords();
        List<Valores> valorUF = new ArrayList<Valores>();
        for(int i = from ; i < to; i++){
        	myValores = control.findAllValores();
        	valorUF.add(myValores.get(i));
        	System.out.println("ID: UF " +myValores.get(i).getId());
        }
        
        setGridModel(valorUF);
      //calcula el total de p�ginas que genera la consulta
	    total = (int) Math.ceil((double) getRecords() / (double) getRows());

    	
    	return SUCCESS;
    }

	public ValoresDAO getValoresDAO() {
		return valoresDAO;
	}

	public void setValoresDAO(ValoresDAO valoresDAO) {
		this.valoresDAO = valoresDAO;
	}

	public List<Valores> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<Valores> gridModel) {
		this.gridModel = gridModel;
	}

	public List<Valores> getMyValores() {
		return myValores;
	}

	public void setMyValores(List<Valores> myValores) {
		this.myValores = myValores;
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
