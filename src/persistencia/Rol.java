package persistencia;

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

/**
 * Rol entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rol", catalog = "tesisfinal")
public class Rol implements java.io.Serializable {

	// Fields

	private Integer id;
	private Usuario usuario;
	private String nombre;
	private String descripcion;
	private Set<Privilegio> privilegios = new HashSet<Privilegio>(0);

	// Constructors

	/** default constructor */
	public Rol() {
	}

	/** full constructor */
	public Rol(Usuario usuario, String nombre, String descripcion,
			Set<Privilegio> privilegios) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.privilegios = privilegios;
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
	@JoinColumn(name = "id_usuario")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
	public Set<Privilegio> getPrivilegios() {
		return this.privilegios;
	}

	public void setPrivilegios(Set<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}

}