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
 * Abonos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "abonos", catalog = "remusystem")
public class Abonos implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nombre;
	private Date fechaInicio;
	private Integer numeroCuotas;
	private Date fechaFinal;
	private Integer monto;
	private String tipoAbono;
	//private Set<SolicitudAbono> solicitudAbonos = new HashSet<SolicitudAbono>(0);

	// Constructors

	/** default constructor */
	public Abonos() {
	}

	/** full constructor */
	public Abonos(String nombre, Date fechaInicio, Integer numeroCuotas,
			Date fechaFinal, Integer monto, String tipoAbono,
			Set<SolicitudAbono> solicitudAbonos) {
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.numeroCuotas = numeroCuotas;
		this.fechaFinal = fechaFinal;
		this.monto = monto;
		this.tipoAbono = tipoAbono;
		//this.solicitudAbonos = solicitudAbonos;
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

	@Column(name = "Nombre")
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

	@Column(name = "numero_cuotas")
	public Integer getNumeroCuotas() {
		return this.numeroCuotas;
	}

	public void setNumeroCuotas(Integer numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
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

	@Column(name = "tipo_abono")
	public String getTipoAbono() {
		return this.tipoAbono;
	}

	public void setTipoAbono(String tipoAbono) {
		this.tipoAbono = tipoAbono;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "abonos")
	public Set<SolicitudAbono> getSolicitudAbonos() {
		return this.solicitudAbonos;
	}

	public void setSolicitudAbonos(Set<SolicitudAbono> solicitudAbonos) {
		this.solicitudAbonos = solicitudAbonos;
	}*/

}