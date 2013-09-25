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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OtrosDescuentos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "otros_descuentos", catalog = "remusystem")
public class OtrosDescuentos implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nombre;
	private Date fechaInicio;
	private Integer numerCuotas;
	private Date fechaFinal;
	private Integer monto;
	//private Set<Solicitud> solicituds = new HashSet<Solicitud>(0);

	// Constructors

	/** default constructor */
	public OtrosDescuentos() {
	}

	/** full constructor */
	public OtrosDescuentos(String nombre, Date fechaInicio,
			Integer numerCuotas, Date fechaFinal, Integer monto,
			Set<Solicitud> solicituds) {
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.numerCuotas = numerCuotas;
		this.fechaFinal = fechaFinal;
		this.monto = monto;
		//this.solicituds = solicituds;
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

	@Column(name = "nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Column(name = "numer_cuotas")
	public Integer getNumerCuotas() {
		return this.numerCuotas;
	}

	public void setNumerCuotas(Integer numerCuotas) {
		this.numerCuotas = numerCuotas;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_final", length = 10)
	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	@Column(name = "monto")
	public Integer getMonto() {
		return this.monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "otrosDescuentos")
	public Set<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(Set<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}*/

}