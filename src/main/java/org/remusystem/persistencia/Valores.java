package org.remusystem.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Valores entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "valores", catalog = "remusystem")
public class Valores implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nombre;
	private Double monto;

	// Constructors

	/** default constructor */
	public Valores() {
	}

	/** full constructor */
	public Valores(String nombre, Double monto) {
		this.nombre = nombre;
		this.monto = monto;
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

	@Column(name = "monto", precision = 10)
	public Double getMonto() {
		return this.monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

}