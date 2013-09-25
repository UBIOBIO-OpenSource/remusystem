package org.remusystem.actions.grid;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.persistencia.Abonos;
import org.remusystem.persistencia.AbonosDAO;
import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.persistencia.SolicitudAbono;
import org.remusystem.persistencia.SolicitudAbonoDAO;

import org.remusystem.control.Controlador;


public class ListarAbonos extends ActionSupport implements SessionAware{
	
	private Map session;
	private List<SolicitudAbono> SolicitudAbono;
	private List<Abonos> gridModel;
	private List<Abonos> Abonos = new ArrayList<Abonos>();
	private AbonosDAO abonosDAO;
	
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
		
		SolicitudAbonoDAO sDAO = new SolicitudAbonoDAO();
		RelacionLaboral rel = (RelacionLaboral) session.get("relacion");
		SolicitudAbono = sDAO.findByIDrel(rel.getId());
		
		//buscamos los anticipos asociados al trabajador (no importa la fecha)
		for(int i = 0; i<SolicitudAbono.size(); i++){
			Abonos.add(SolicitudAbono.get(i).getAbonos());
		}
		
		//N�mero de registros(Select count(*) from users)
		setRecords(Abonos.size());
		
		//Calcula HASTA qu� registro ser� la consulta, suponiendo que rows=10....
        int to = (getRows() * getPage());//.....la primera vez to=10  (10*1)
        //Calcula DESDE qu� registro se har� la consulta...
        int from = to - getRows();//....from=0  (10-10)
        //si la variable "to" sobrepasa el n�mero de registros disponible, entonces le ponemos ese valor m�ximo de registros.
        if(to>getRecords())to=getRecords();
	    List<Abonos> ListadoAbonos = new ArrayList<Abonos>();
	    for(int i = from ; i < to; i++){
	    	SolicitudAbono = sDAO.findByIDrel(rel.getId());
	    	ListadoAbonos.add(SolicitudAbono.get(i).getAbonos());
	    	System.out.println("ID abono: " +SolicitudAbono.get(i).getAbonos().getId());
	    }
		
		setGridModel(ListadoAbonos);
		//calcula el total de p�ginas que genera la consulta
	    total = (int) Math.ceil((double) getRecords() / (double) getRows());
		System.out.println("TOtal = " +total);
		return SUCCESS;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public List<SolicitudAbono> getSolicitudAbono() {
		return SolicitudAbono;
	}

	public void setSolicitudAbono(List<SolicitudAbono> solicitudAbono) {
		SolicitudAbono = solicitudAbono;
	}

	public List<Abonos> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<Abonos> gridModel) {
		this.gridModel = gridModel;
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

	public List<Abonos> getAbonos() {
		return Abonos;
	}

	public void setAbonos(List<Abonos> abonos) {
		Abonos = abonos;
	}

	public AbonosDAO getAbonosDAO() {
		return abonosDAO;
	}

	public void setAbonosDAO(AbonosDAO abonosDAO) {
		this.abonosDAO = abonosDAO;
	}

}
