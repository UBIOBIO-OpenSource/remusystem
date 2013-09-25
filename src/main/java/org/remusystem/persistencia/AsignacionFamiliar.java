package org.remusystem.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AsignacionFamiliar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "asignacion_familiar", catalog = "remusystem")
public class AsignacionFamiliar implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tramo;
	private Integer monto;
	private Integer desde;
	private Integer hasta;

	// Constructors

	/** default constructor */
	public AsignacionFamiliar() {
	}

	/** full constructor */
	public AsignacionFamiliar(String tramo, Integer monto, Integer desde,
			Integer hasta) {
		this.tramo = tramo;
		this.monto = monto;
		this.desde = desde;
		this.hasta = hasta;
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

	@Column(name = "tramo")
	public String getTramo() {
		return this.tramo;
	}

	public void setTramo(String tramo) {
		this.tramo = tramo;
	}

	@Column(name = "monto")
	public Integer getMonto() {
		return this.monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}

	@Column(name = "desde")
	public Integer getDesde() {
		return this.desde;
	}

	public void setDesde(Integer desde) {
		this.desde = desde;
	}

	@Column(name = "hasta")
	public Integer getHasta() {
		return this.hasta;
	}

	public void setHasta(Integer hasta) {
		this.hasta = hasta;
	}

}