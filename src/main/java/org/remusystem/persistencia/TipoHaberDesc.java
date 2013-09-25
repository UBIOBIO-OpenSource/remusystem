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
 * TipoHaberDesc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipo_haber_desc", catalog = "remusystem")
public class TipoHaberDesc implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tipo;
	private String nombre;
	private Integer posicion;
	private Set<Agrupar> agrupars = new HashSet<Agrupar>(0);

	// Constructors

	/** default constructor */
	public TipoHaberDesc() {
	}

	/** full constructor */
	public TipoHaberDesc(String tipo, String nombre, Integer posicion,
			Set<Agrupar> agrupars) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.posicion = posicion;
		this.agrupars = agrupars;
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

	@Column(name = "tipo")
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "posicion")
	public Integer getPosicion() {
		return this.posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoHaberDesc")
	public Set<Agrupar> getAgrupars() {
		return this.agrupars;
	}

	public void setAgrupars(Set<Agrupar> agrupars) {
		this.agrupars = agrupars;
	}

}