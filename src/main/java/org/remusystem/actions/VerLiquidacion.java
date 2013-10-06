package org.remusystem.actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import org.remusystem.persistencia.DetalleLiquidacion;
import org.remusystem.persistencia.DetalleLiquidacionDAO;
import org.remusystem.persistencia.LiquidacionDeSueldo;
import org.remusystem.persistencia.LiquidacionDeSueldoDAO;
import org.remusystem.persistencia.RelacionLaboral;

import com.opensymphony.xwork2.ActionSupport;

public class VerLiquidacion extends ActionSupport implements SessionAware{
	
	
	private Map session;
	private String Mes;
	private String Anio;
	
	
	
	//variables para mostarlas en el resumen despues
	private Double SueldoBaseImponibleTrabajado;
	private Double HorasExtrasTrabajadas;
	private Double ValorGratificacion;
	private Double AbonosImponibles;
	private Double AbonosImponiblesNoTributables;
	private Double BaseImponible;
	private Double cotizarAFP;
	private Double cotizarSalud;
	private Double cargoTrabajadorAFC;
	private Double cargoEmpleadorAFC;
	private Double SegurodeCesantia;
	private Double AbonosNoImponiblesTributables;
	private Double BaseTributable;
	private Double ImpuestoTributarioaPagar;
	private Double SuelDspsImpto;
	private Double AbonosNoImponiblesNoTributables;
	private Double Anticipos;
	private Double SueldoLiquido;
	private Double AsignacionaPagar;
	
	//sumatorias no consideradas
	private Double Total_desc_prev;
	private Double Total_otros_haberes;
	
	
	public String execute(){
		
		RelacionLaboral rel = (RelacionLaboral) session.get("relacion");
		
		LiquidacionDeSueldoDAO liqDAO = new LiquidacionDeSueldoDAO();
		
		Integer numAnio = Integer.parseInt(Anio);
		if(rel!=null){
			
		
		LiquidacionDeSueldo li = liqDAO.findBYIDrelAnioMes(rel.getId(), numAnio, Mes);
		System.out.println(li);
		if(li!=null){
            session.remove("VerMes");
            session.remove("VerAnio");
            session.put("VerMes", Mes);
            session.put("VerAnio",Anio);
			DetalleLiquidacionDAO dDAO = new DetalleLiquidacionDAO();
			List<DetalleLiquidacion> detalles = dDAO.findByIdLiquidacion(li.getId());
			System.out.println(detalles);
			for(int i=0; i<detalles.size(); i++){
				 DetalleLiquidacion det = detalles.get(i);
				 Integer cond = det.getPosicion();
				 System.out.println(cond);
				 switch(cond){
				 
				 case 1:
						SueldoBaseImponibleTrabajado = det.getMonto();
					break;
					
				 case 2:
						HorasExtrasTrabajadas = det.getMonto();
					break;
					
				 case 3:
						ValorGratificacion = det.getMonto();
					break;
					
				 case 4:
						AbonosImponibles = det.getMonto();
					break;
					
				 case 5:
						AbonosImponiblesNoTributables = det.getMonto();
					break;
					
				 case 6:
						BaseImponible = det.getMonto();
					break;
					
				 case 7:
						cotizarAFP = det.getMonto();
					break;
					
				 case 8:
						cotizarSalud = det.getMonto();
					break;
					
				 case 9:
						SegurodeCesantia = det.getMonto();
					break;
					
				 case 10:
						AbonosNoImponiblesTributables = det.getMonto();
					break;
					
				 case 11:
						BaseTributable = det.getMonto();
					break;
					
				 case 12:
						ImpuestoTributarioaPagar = det.getMonto();
					break;
					
				 case 13:
						AsignacionaPagar = det.getMonto();
					break;
					
				 case 14:
						SuelDspsImpto = det.getMonto();
					break;
					
				 case 15:
						AbonosNoImponiblesNoTributables = det.getMonto();
					break;
					
				 case 16:
						Anticipos = det.getMonto();
					break;
					
				 case 17:
						SueldoLiquido = det.getMonto();
					break;
				 
				 
				 }
				
			}
			Total_desc_prev = cotizarAFP + cotizarSalud + SegurodeCesantia;
			Total_desc_prev = Redondear(Total_desc_prev, 2);
			Total_otros_haberes = AsignacionaPagar + AbonosNoImponiblesNoTributables+AbonosNoImponiblesTributables;
			
			
			return SUCCESS;
			
		}else{
			addActionError("No se han encontrado liquidacion de sueldo para el mes y año seleccionados");
			return ERROR;
		}
		}else{
			addActionError("Debe realizar la busqueda de algun trabajador primero");
			return ERROR;
		}
		
		
}
	public double Redondear(double nD, int nDec)
	{
	  return Math.round(nD*Math.pow(10,nDec))/Math.pow(10,nDec);
	}
	public Double calculagratificacion(Double sueldo){
		Double gratificacion = sueldo*0.25;
		Double tope = 193000 * 4.75;
		Double valorAnual = gratificacion*12;
		if(valorAnual>= tope){
			return tope/12;
		}else{
			return gratificacion;
		}
	}


	public Map getSession() {
		return session;
	}


	public void setSession(Map session) {
		this.session = session;
	}


	public String getMes() {
		return Mes;
	}


	public void setMes(String mes) {
		Mes = mes;
	}


	public String getAnio() {
		return Anio;
	}


	public void setAnio(String anio) {
		Anio = anio;
	}


	public Double getSueldoBaseImponibleTrabajado() {
		return SueldoBaseImponibleTrabajado;
	}


	public void setSueldoBaseImponibleTrabajado(Double sueldoBaseImponibleTrabajado) {
		SueldoBaseImponibleTrabajado = sueldoBaseImponibleTrabajado;
	}


	public Double getHorasExtrasTrabajadas() {
		return HorasExtrasTrabajadas;
	}


	public void setHorasExtrasTrabajadas(Double horasExtrasTrabajadas) {
		HorasExtrasTrabajadas = horasExtrasTrabajadas;
	}


	public Double getValorGratificacion() {
		return ValorGratificacion;
	}


	public void setValorGratificacion(Double valorGratificacion) {
		ValorGratificacion = valorGratificacion;
	}


	public Double getAbonosImponibles() {
		return AbonosImponibles;
	}


	public void setAbonosImponibles(Double abonosImponibles) {
		AbonosImponibles = abonosImponibles;
	}


	public Double getAbonosImponiblesNoTributables() {
		return AbonosImponiblesNoTributables;
	}


	public void setAbonosImponiblesNoTributables(
			Double abonosImponiblesNoTributables) {
		AbonosImponiblesNoTributables = abonosImponiblesNoTributables;
	}


	public Double getBaseImponible() {
		return BaseImponible;
	}


	public void setBaseImponible(Double baseImponible) {
		BaseImponible = baseImponible;
	}


	public Double getCotizarAFP() {
		return cotizarAFP;
	}


	public void setCotizarAFP(Double cotizarAFP) {
		this.cotizarAFP = cotizarAFP;
	}


	public Double getCotizarSalud() {
		return cotizarSalud;
	}


	public void setCotizarSalud(Double cotizarSalud) {
		this.cotizarSalud = cotizarSalud;
	}


	public Double getCargoTrabajadorAFC() {
		return cargoTrabajadorAFC;
	}


	public void setCargoTrabajadorAFC(Double cargoTrabajadorAFC) {
		this.cargoTrabajadorAFC = cargoTrabajadorAFC;
	}


	public Double getCargoEmpleadorAFC() {
		return cargoEmpleadorAFC;
	}


	public void setCargoEmpleadorAFC(Double cargoEmpleadorAFC) {
		this.cargoEmpleadorAFC = cargoEmpleadorAFC;
	}


	public Double getSegurodeCesantia() {
		return SegurodeCesantia;
	}


	public void setSegurodeCesantia(Double segurodeCesantia) {
		SegurodeCesantia = segurodeCesantia;
	}


	public Double getAbonosNoImponiblesTributables() {
		return AbonosNoImponiblesTributables;
	}


	public void setAbonosNoImponiblesTributables(
			Double abonosNoImponiblesTributables) {
		AbonosNoImponiblesTributables = abonosNoImponiblesTributables;
	}


	public Double getBaseTributable() {
		return BaseTributable;
	}


	public void setBaseTributable(Double baseTributable) {
		BaseTributable = baseTributable;
	}


	public Double getImpuestoTributarioaPagar() {
		return ImpuestoTributarioaPagar;
	}


	public void setImpuestoTributarioaPagar(Double impuestoTributarioaPagar) {
		ImpuestoTributarioaPagar = impuestoTributarioaPagar;
	}


	public Double getSuelDspsImpto() {
		return SuelDspsImpto;
	}


	public void setSuelDspsImpto(Double suelDspsImpto) {
		SuelDspsImpto = suelDspsImpto;
	}


	public Double getAbonosNoImponiblesNoTributables() {
		return AbonosNoImponiblesNoTributables;
	}


	public void setAbonosNoImponiblesNoTributables(
			Double abonosNoImponiblesNoTributables) {
		AbonosNoImponiblesNoTributables = abonosNoImponiblesNoTributables;
	}


	public Double getAnticipos() {
		return Anticipos;
	}


	public void setAnticipos(Double anticipos) {
		Anticipos = anticipos;
	}


	public Double getSueldoLiquido() {
		return SueldoLiquido;
	}


	public void setSueldoLiquido(Double sueldoLiquido) {
		SueldoLiquido = sueldoLiquido;
	}


	public Double getAsignacionaPagar() {
		return AsignacionaPagar;
	}


	public void setAsignacionaPagar(Double asignacionaPagar) {
		AsignacionaPagar = asignacionaPagar;
	}


	public Double getTotal_desc_prev() {
		return Total_desc_prev;
	}


	public void setTotal_desc_prev(Double totalDescPrev) {
		Total_desc_prev = totalDescPrev;
	}


	public Double getTotal_otros_haberes() {
		return Total_otros_haberes;
	}


	public void setTotal_otros_haberes(Double totalOtrosHaberes) {
		Total_otros_haberes = totalOtrosHaberes;
	}

}
