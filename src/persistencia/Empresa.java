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
 * Empresa entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "empresa", catalog = "tesisfinal")
public class Empresa implements java.io.Serializable {

	// Fields

	private Integer id;
	private CajaCompensacion cajaCompensacion;
	private Usuario usuario;
	private Mutual mutual;
	private String rut;
	private String nombre;
	private String giro;
	private String direccion;
	private String telefono;
	private String fax;
	private String email;
	

	// Constructors

	/** default constructor */
	public Empresa() {
	}

	/** full constructor */
	public Empresa(CajaCompensacion cajaCompensacion, Usuario usuario,
			Mutual mutual, String rut, String nombre, String giro,
			String direccion, String telefono, String fax, String email) {
		this.cajaCompensacion = cajaCompensacion;
		this.usuario = usuario;
		this.mutual = mutual;
		this.rut = rut;
		this.nombre = nombre;
		this.giro = giro;
		this.direccion = direccion;
		this.telefono = telefono;
		this.fax = fax;
		this.email = email;
		
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_caja_compensacion")
	public CajaCompensacion getCajaCompensacion() {
		return this.cajaCompensacion;
	}

	public void setCajaCompensacion(CajaCompensacion cajaCompensacion) {
		this.cajaCompensacion = cajaCompensacion;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_mutual")
	public Mutual getMutual() {
		return this.mutual;
	}

	public void setMutual(Mutual mutual) {
		this.mutual = mutual;
	}

	@Column(name = "rut")
	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "giro")
	public String getGiro() {
		return this.giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	@Column(name = "direccion")
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "telefono")
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "fax")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}