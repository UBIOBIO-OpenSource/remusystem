package org.remusystem.persistencia;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LiquidacionDeSueldo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "liquidacion_de_sueldo", catalog = "remusystem")
public class LiquidacionDeSueldo implements java.io.Serializable {

	// Fields

	private Integer id;
	private RelacionLaboral relacionLaboral;
	private String anio;
	private String mes;
	private Date fechaEmision;
	private Date fechaPago;
	private Set<DetalleLiquidacion> detalleLiquidacions = new HashSet<DetalleLiquidacion>(
			0);

	// Constructors

	/** default constructor */
	public LiquidacionDeSueldo() {
	}

	/** full constructor */
	public LiquidacionDeSueldo(RelacionLaboral relacionLaboral, String anio,
			String mes, Date fechaEmision, Date fechaPago,
			Set<DetalleLiquidacion> detalleLiquidacions) {
		this.relacionLaboral = relacionLaboral;
		this.anio = anio;
		this.mes = mes;
		this.fechaEmision = fechaEmision;
		this.fechaPago = fechaPago;
		this.detalleLiquidacions = detalleLiquidacions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_relacion_laboral")
	public RelacionLaboral getRelacionLaboral() {
		return this.relacionLaboral;
	}

	public void setRelacionLaboral(RelacionLaboral relacionLaboral) {
		this.relacionLaboral = relacionLaboral;
	}

	@Column(name = "anio")
	public String getAnio() {
		return this.anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	@Column(name = "mes")
	public String getMes() {
		return this.mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_emision", length = 10)
	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_pago", length = 10)
	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "liquidacionDeSueldo")
	public Set<DetalleLiquidacion> getDetalleLiquidacions() {
		return this.detalleLiquidacions;
	}

	public void setDetalleLiquidacions(
			Set<DetalleLiquidacion> detalleLiquidacions) {
		this.detalleLiquidacions = detalleLiquidacions;
	}

}