package persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Privilegio entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "privilegio", catalog = "tesisfinal")
public class Privilegio implements java.io.Serializable {

	// Fields

	private Integer id;
	private Rol rol;
	private String nombre;
	private String descripcion;

	// Constructors

	/** default constructor */
	public Privilegio() {
	}

	/** full constructor */
	public Privilegio(Rol rol, String nombre, String descripcion) {
		this.rol = rol;
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	@JoinColumn(name = "id_rol")
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}