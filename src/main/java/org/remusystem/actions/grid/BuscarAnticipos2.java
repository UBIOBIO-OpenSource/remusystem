package org.remusystem.actions.grid;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.persistencia.RelacionLaboral;
import org.remusystem.persistencia.Solicitud;
import org.remusystem.persistencia.SolicitudDAO;
import org.remusystem.persistencia.OtrosDescuentos;
import org.remusystem.persistencia.OtrosDescuentosDAO;
import org.remusystem.control.Controlador;

public class BuscarAnticipos2 extends ActionSupport implements SessionAware{
	private Map session;
	private List<Solicitud> SolicitudAnticipo;
	private List<OtrosDescuentos> gridModel;
	private List<OtrosDescuentos> Anticipos = new ArrayList<OtrosDescuentos>();
	private OtrosDescuentosDAO otrosDescuentosDAO;
	private Date desde;
	private Date hasta;
	private Date fechaComparar;
	
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
		
		SolicitudDAO sDAO = new SolicitudDAO();
		RelacionLaboral rel = (RelacionLaboral) session.get("Rel_PRO");
		SolicitudAnticipo = sDAO.findByIDrel(rel.getId());
		fechaComparar = (Date) session.get("fechaLiquidacion");
		//buscamos los anticipos asociados al trabajador (no importa la fecha)
		for(int i = 0; i<SolicitudAnticipo.size(); i++){
			
				Anticipos.add(SolicitudAnticipo.get(i).getOtrosDescuentos());
			
		}
		
		//N�mero de registros(Select count(*) from users)
		setRecords(Anticipos.size());
		
		//Calcula HASTA qu� registro ser� la consulta, suponiendo que rows=10....
        int to = (getRows() * getPage());//.....la primera vez to=10  (10*1)
        //Calcula DESDE qu� registro se har� la consulta...
        int from = to - getRows();//....from=0  (10-10)
        //si la variable "to" sobrepasa el n�mero de registros disponible, entonces le ponemos ese valor m�ximo de registros.
        if(to>getRecords())to=getRecords();
	    List<OtrosDescuentos> AnticiposListado = new ArrayList<OtrosDescuentos>();
		for(int i = from ; i < to; i++){
			SolicitudAnticipo = sDAO.findByIDrel(rel.getId());
			//if(SolicitudAnticipo.get(i).getOtrosDescuentos().getFechaInicio().after(desde)&&SolicitudAnticipo.get(i).getOtrosDescuentos().getFechaFinal().before(hasta)){
			if((calcularFechaFinal(SolicitudAnticipo.get(i).getOtrosDescuentos().getFechaFinal()).after(fechaComparar))&&(SolicitudAnticipo.get(i).getOtrosDescuentos().getFechaInicio().before(fechaComparar))){
				AnticiposListado.add(SolicitudAnticipo.get(i).getOtrosDescuentos());
				System.out.println("ID Anticipos: " +SolicitudAnticipo.get(i).getOtrosDescuentos().getId());
			}
			//}
			
		}
		
		setGridModel(AnticiposListado);
		//calcula el total de p�ginas que genera la consulta
	    total = (int) Math.ceil((double) getRecords() / (double) getRows());
		System.out.println("TOtal = " +total);
		return SUCCESS;
	}
	
	//arreglo del mes de los anticipos y abonos
	//para calcular la fecha final segun el numero de cuotas
	public Date calcularFechaFinal(Date fecha){
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.MONTH, 1);   
		String newdate = dateformat.format(cal.getTime());
		System.out.println(newdate);
		return (getDate(newdate));
	}
	
	//para transformar el string en fecha
	 public Date getDate(String date)
	     {
	         DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	         try {
	             return df.parse(date);
	         } catch (ParseException ex) {
	         }
	         return null;
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

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public Date getFechaComparar() {
		return fechaComparar;
	}

	public void setFechaComparar(Date fechaComparar) {
		this.fechaComparar = fechaComparar;
	}

}
