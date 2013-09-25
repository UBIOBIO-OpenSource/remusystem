package org.remusystem.persistencia;

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

/**
 * InstitucionPrevision entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "institucion_prevision", catalog = "remusystem")
public class InstitucionPrevision implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nombre;
	private Double comision;
	private Double porcentajeDescuento;
	/*private Set<RelacionLaboral> relacionLaborals = new HashSet<RelacionLaboral>(
			0);*/

	// Constructors

	/** default constructor */
	public InstitucionPrevision() {
	}

	/** full constructor */
	public InstitucionPrevision(String nombre, Double comision,
			Double porcentajeDescuento, Set<RelacionLaboral> relacionLaborals) {
		this.nombre = nombre;
		this.comision = comision;
		this.porcentajeDescuento = porcentajeDescuento;
		//this.relacionLaborals = relacionLaborals;
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

	@Column(name = "comision", precision = 10, scale = 5)
	public Double getComision() {
		return this.comision;
	}

	public void setComision(Double comision) {
		this.comision = comision;
	}

	@Column(name = "porcentaje_descuento", precision = 10, scale = 5)
	public Double getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}

	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "institucionPrevision")
	public Set<RelacionLaboral> getRelacionLaborals() {
		return this.relacionLaborals;
	}

	public void setRelacionLaborals(Set<RelacionLaboral> relacionLaborals) {
		this.relacionLaborals = relacionLaborals;
	}*/

}