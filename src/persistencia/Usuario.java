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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuario", catalog = "tesisfinal")
public class Usuario implements java.io.Serializable {

	// Fields

	private Integer id;
	private String rut;
	private String contrasena;
	private String tipo;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String email;
	private Set<Rol> rols = new HashSet<Rol>(0);
	private Set<Empresa> empresas = new HashSet<Empresa>(0);

	// Constructors

	/** default constructor */
	public Usuario() {
	}

	/** full constructor */
	public Usuario(String rut, String contrasena, String tipo, String nombres,
			String apellidoPaterno, String apellidoMaterno, String email,
			Set<Rol> rols, Set<Empresa> empresas) {
		this.rut = rut;
		this.contrasena = contrasena;
		this.tipo = tipo;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.email = email;
		this.rols = rols;
		this.empresas = empresas;
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

	@Column(name = "rut")
	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	@Column(name = "contrasena")
	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Column(name = "tipo")
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "nombres")
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Column(name = "apellido_paterno")
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Column(name = "apellido_materno")
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Rol> getRols() {
		return this.rols;
	}

	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

}