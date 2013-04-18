package actions.grid;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import persistencia.OtrosDescuentos;
import persistencia.OtrosDescuentosDAO;
import persistencia.RelacionLaboral;
import persistencia.Solicitud;
import persistencia.SolicitudDAO;

import control.Controlador;

public class ListarAnticipos extends ActionSupport implements SessionAware{
	
	private Map session;
	private List<Solicitud> SolicitudAnticipo;
	private List<OtrosDescuentos> gridModel;
	private List<OtrosDescuentos> Anticipos = new ArrayList<OtrosDescuentos>();
	private OtrosDescuentosDAO otrosDescuentosDAO;
	
	//Número de filas que queremos tener dentro del grid - atributo "rowNum" del grid
    private Integer rows = 0;
    
    //Página actual. Por defecto el grid lo pone en 1
    private Integer page = 0;
    
    //Orden - asc o desc
    private String sord;
    
    //Índice de la fila - i.e. click del usuario para ordenar
    private String sidx;
    
    //campo de búsqueda
    private String searchField;
    
    //cadena de búsqueda
    
    private String searchString;
    
    //La operación de búsqueda ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    private String searchOper;
    
    //Páginas totales
    private Integer total = 0;
    
    //todos los registros
    private Integer records = 0;
    
    private boolean loadonce = false;
	
	public String execute(){
		
		System.out.println("searchString2: "+searchString);
    	System.out.println("searchField2: "+searchField);
    	System.out.println("searchOper2: "+searchOper);
		
		Controlador control = Controlador.getInstance();
		
		SolicitudDAO sDAO = new SolicitudDAO();
		RelacionLaboral rel = (RelacionLaboral) session.get("relacion");
		SolicitudAnticipo = sDAO.findByIDrel(rel.getId());
		
		//buscamos los anticipos asociados al trabajador (no importa la fecha)
		for(int i = 0; i<SolicitudAnticipo.size(); i++){
			Anticipos.add(SolicitudAnticipo.get(i).getOtrosDescuentos());
		}
		
		//Número de registros(Select count(*) from users)
		setRecords(Anticipos.size());
		
		//Calcula HASTA qué registro será la consulta, suponiendo que rows=10....
        int to = (getRows() * getPage());//.....la primera vez to=10  (10*1)
        //Calcula DESDE qué registro se hará la consulta...
        int from = to - getRows();//....from=0  (10-10)
        //si la variable "to" sobrepasa el número de registros disponible, entonces le ponemos ese valor máximo de registros.
        if(to>getRecords())to=getRecords();
	    List<OtrosDescuentos> AnticiposListado = new ArrayList<OtrosDescuentos>();
		for(int i = from ; i < to; i++){
			SolicitudAnticipo = sDAO.findByIDrel(rel.getId());
			AnticiposListado.add(SolicitudAnticipo.get(i).getOtrosDescuentos());
			System.out.println("ID : " +SolicitudAnticipo.get(i).getOtrosDescuentos().getId());
			
		}
		
		setGridModel(AnticiposListado);
		//calcula el total de páginas que genera la consulta
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

	public List<Solicitud> getSolicitudAnticipo() {
		return SolicitudAnticipo;
	}

	public void setSolicitudAnticipo(List<Solicitud> solicitudAnticipo) {
		SolicitudAnticipo = solicitudAnticipo;
	}

	public List<OtrosDescuentos> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<OtrosDescuentos> gridModel) {
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

	public List<OtrosDescuentos> getAnticipos() {
		return Anticipos;
	}

	public void setAnticipos(List<OtrosDescuentos> anticipos) {
		Anticipos = anticipos;
	}

	public OtrosDescuentosDAO getOtrosDescuentosDAO() {
		return otrosDescuentosDAO;
	}

	public void setOtrosDescuentosDAO(OtrosDescuentosDAO otrosDescuentosDAO) {
		this.otrosDescuentosDAO = otrosDescuentosDAO;
	}

}
