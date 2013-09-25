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
 * Trabajador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trabajador", catalog = "remusystem")
public class Trabajador implements java.io.Serializable {

	// Fields

	private Integer id;
	private String rut;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date fechaNacimiento;
	private String nacionalidad;
	private Boolean sexo;
	private String direccion;
	private String telefonoFijo;
	private String celular;
	private String email;
	private Integer numeroCargas;
	//private Set<RelacionLaboral> relacionLaborals = new HashSet<RelacionLaboral>(
			//0);

	// Constructors

	/** default constructor */
	public Trabajador() {
	}

	/** full constructor */
	public Trabajador(String rut, String nombre, String apellidoPaterno,
			String apellidoMaterno, Date fechaNacimiento, String nacionalidad,
			Boolean sexo, String direccion, String telefonoFijo,
			String celular, String email, Integer numeroCargas,
			Set<RelacionLaboral> relacionLaborals) {
		this.rut = rut;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.sexo = sexo;
		this.direccion = direccion;
		this.telefonoFijo = telefonoFijo;
		this.celular = celular;
		this.email = email;
		this.numeroCargas = numeroCargas;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento", length = 10)
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Column(name = "nacionalidad")
	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Column(name = "sexo")
	public Boolean getSexo() {
		return this.sexo;
	}

	public void setSexo(Boolean sexo) {
		this.sexo = sexo;
	}

	@Column(name = "direccion")
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "telefono_fijo")
	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	@Column(name = "celular")
	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "numero_cargas")
	public Integer getNumeroCargas() {
		return this.numeroCargas;
	}

	public void setNumeroCargas(Integer numeroCargas) {
		this.numeroCargas = numeroCargas;
	}

/*	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "trabajador")
	public Set<RelacionLaboral> getRelacionLaborals() {
		return this.relacionLaborals;
	}

	public void setRelacionLaborals(Set<RelacionLaboral> relacionLaborals) {
		this.relacionLaborals = relacionLaborals;
	}*/

}