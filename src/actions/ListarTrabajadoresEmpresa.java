package actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;

import persistencia.RelacionLaboral;
import persistencia.Trabajador;
import persistencia.Trabajadorrelacionlaboral;
import persistencia.TrabajadorrelacionlaboralDAO;
import persistencia.Empresa;
import persistencia.EmpresaDAO;
import persistencia.Usuario;
import actions.grid.utils.OrdenarTrabajadores;

import control.Controlador;

public class ListarTrabajadoresEmpresa extends ActionSupport implements SessionAware {

	private Map session;
	private List<Trabajadorrelacionlaboral> Todos = new ArrayList<Trabajadorrelacionlaboral>();
	private List<Trabajador> Mostramos = new ArrayList<Trabajador>();
	private List<RelacionLaboral> todosEMP = new ArrayList<RelacionLaboral>();
	private List<Trabajador> gridModel = new ArrayList<Trabajador>();
	
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
		
		Controlador cntrl = Controlador.getInstance();
		Usuario user = (Usuario) session.get("User");
		Empresa emp = cntrl.buscaEmpresa(user.getRut());
		
		todosEMP = cntrl.RelacionIdEmpresa(emp.getId());
		
		//Número de registros(Select count(*) from users)
		setRecords(todosEMP.size());
		//Calcula HASTA qué registro será la consulta, suponiendo que rows=10....
        int to = (getRows() * getPage());//.....la primera vez to=10  (10*1)
        //Calcula DESDE qué registro se hará la consulta...
        int from = to - getRows();//....from=0  (10-10)
        //si la variable "to" sobrepasa el número de registros disponible, entonces le ponemos ese valor máximo de registros.
        if(to>getRecords())to=getRecords();
        List<Trabajador> listadoTrabajadores = new ArrayList<Trabajador>();
        for(int i = from ; i < to; i++){
        	todosEMP = cntrl.RelacionIdEmpresa(emp.getId());
        	if(todosEMP.get(i).getEstado()){
        		listadoTrabajadores.add(todosEMP.get(i).getTrabajador());
        		Mostramos.add(todosEMP.get(i).getTrabajador());
        		System.out.println("ID Trabajador: "+todosEMP.get(i).getTrabajador().getId());
        		
        	}
        }
        
        setGridModel(listadoTrabajadores);
        System.out.println("el sord esta en: "+sord);
        if (getSord()!=null && getSord().equalsIgnoreCase("asc"))
        {
          Collections.sort(Mostramos, new OrdenarTrabajadores());
          }
        if (getSord()!=null && getSord().equalsIgnoreCase("desc"))
        {
          Collections.sort(Mostramos, new OrdenarTrabajadores());
          Collections.reverse(Mostramos);
        }
              //calcula el total de páginas que genera la consulta
	    total = (int) Math.ceil((double) getRecords() / (double) getRows());
		
		
		session.put("Empresa", emp);
	    System.out.println("TOtal = " +total);
	    System.out.println("records = " +records);
		return SUCCESS;
	}
	
	public String getJSON(){
        return execute();
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public List<Trabajadorrelacionlaboral> getTodos() {
		return Todos;
	}

	public void setTodos(List<Trabajadorrelacionlaboral> todos) {
		Todos = todos;
	}

	public List<Trabajador> getMostramos() {
		return Mostramos;
	}

	public void setMostramos(List<Trabajador> mostramos) {
		Mostramos = mostramos;
	}

	public List<RelacionLaboral> getTodosEMP() {
		return todosEMP;
	}

	public void setTodosEMP(List<RelacionLaboral> todosEMP) {
		this.todosEMP = todosEMP;
	}

	public List<Trabajador> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<Trabajador> gridModel) {
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
	

}
