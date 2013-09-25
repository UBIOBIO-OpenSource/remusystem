package org.remusystem.persistencia;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TrabajadorrelacionlaboralId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TrabajadorrelacionlaboralId implements java.io.Serializable {

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
	private String tipoContrato;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer sueldoBase;
	private Boolean estado;
	private Integer idEmpresa;
	private Integer idInstitucionSalud;
	private Integer idInstitucionPrevision;
	private Integer idGrupoHabDesc;

	// Constructors

	/** default constructor */
	public TrabajadorrelacionlaboralId() {
	}

	/** minimal constructor */
	public TrabajadorrelacionlaboralId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TrabajadorrelacionlaboralId(Integer id, String rut, String nombre,
			String apellidoPaterno, String apellidoMaterno,
			Date fechaNacimiento, String nacionalidad, Boolean sexo,
			String direccion, String telefonoFijo, String celular,
			String email, Integer numeroCargas, String tipoContrato,
			Date fechaInicio, Date fechaFin, Integer sueldoBase,
			Boolean estado, Integer idEmpresa, Integer idInstitucionSalud,
			Integer idInstitucionPrevision, Integer idGrupoHabDesc) {
		this.id = id;
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
		this.tipoContrato = tipoContrato;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.sueldoBase = sueldoBase;
		this.estado = estado;
		this.idEmpresa = idEmpresa;
		this.idInstitucionSalud = idInstitucionSalud;
		this.idInstitucionPrevision = idInstitucionPrevision;
		this.idGrupoHabDesc = idGrupoHabDesc;
	}

	// Property accessors

	@Column(name = "Id", nullable = false)
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

	@Column(name = "tipo_contrato")
	public String getTipoContrato() {
		return this.tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", length = 10)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "sueldo_base")
	public Integer getSueldoBase() {
		return this.sueldoBase;
	}

	public void setSueldoBase(Integer sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	@Column(name = "estado")
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Column(name = "id_empresa")
	public Integer getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Column(name = "id_institucion_salud")
	public Integer getIdInstitucionSalud() {
		return this.idInstitucionSalud;
	}

	public void setIdInstitucionSalud(Integer idInstitucionSalud) {
		this.idInstitucionSalud = idInstitucionSalud;
	}

	@Column(name = "id_institucion_prevision")
	public Integer getIdInstitucionPrevision() {
		return this.idInstitucionPrevision;
	}

	public void setIdInstitucionPrevision(Integer idInstitucionPrevision) {
		this.idInstitucionPrevision = idInstitucionPrevision;
	}

	@Column(name = "id_grupo_hab_desc")
	public Integer getIdGrupoHabDesc() {
		return this.idGrupoHabDesc;
	}

	public void setIdGrupoHabDesc(Integer idGrupoHabDesc) {
		this.idGrupoHabDesc = idGrupoHabDesc;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TrabajadorrelacionlaboralId))
			return false;
		TrabajadorrelacionlaboralId castOther = (TrabajadorrelacionlaboralId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getRut() == castOther.getRut()) || (this.getRut() != null
						&& castOther.getRut() != null && this.getRut().equals(
						castOther.getRut())))
				&& ((this.getNombre() == castOther.getNombre()) || (this
						.getNombre() != null
						&& castOther.getNombre() != null && this.getNombre()
						.equals(castOther.getNombre())))
				&& ((this.getApellidoPaterno() == castOther
						.getApellidoPaterno()) || (this.getApellidoPaterno() != null
						&& castOther.getApellidoPaterno() != null && this
						.getApellidoPaterno().equals(
								castOther.getApellidoPaterno())))
				&& ((this.getApellidoMaterno() == castOther
						.getApellidoMaterno()) || (this.getApellidoMaterno() != null
						&& castOther.getApellidoMaterno() != null && this
						.getApellidoMaterno().equals(
								castOther.getApellidoMaterno())))
				&& ((this.getFechaNacimiento() == castOther
						.getFechaNacimiento()) || (this.getFechaNacimiento() != null
						&& castOther.getFechaNacimiento() != null && this
						.getFechaNacimiento().equals(
								castOther.getFechaNacimiento())))
				&& ((this.getNacionalidad() == castOther.getNacionalidad()) || (this
						.getNacionalidad() != null
						&& castOther.getNacionalidad() != null && this
						.getNacionalidad().equals(castOther.getNacionalidad())))
				&& ((this.getSexo() == castOther.getSexo()) || (this.getSexo() != null
						&& castOther.getSexo() != null && this.getSexo()
						.equals(castOther.getSexo())))
				&& ((this.getDireccion() == castOther.getDireccion()) || (this
						.getDireccion() != null
						&& castOther.getDireccion() != null && this
						.getDireccion().equals(castOther.getDireccion())))
				&& ((this.getTelefonoFijo() == castOther.getTelefonoFijo()) || (this
						.getTelefonoFijo() != null
						&& castOther.getTelefonoFijo() != null && this
						.getTelefonoFijo().equals(castOther.getTelefonoFijo())))
				&& ((this.getCelular() == castOther.getCelular()) || (this
						.getCelular() != null
						&& castOther.getCelular() != null && this.getCelular()
						.equals(castOther.getCelular())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null
						&& castOther.getEmail() != null && this.getEmail()
						.equals(castOther.getEmail())))
				&& ((this.getNumeroCargas() == castOther.getNumeroCargas()) || (this
						.getNumeroCargas() != null
						&& castOther.getNumeroCargas() != null && this
						.getNumeroCargas().equals(castOther.getNumeroCargas())))
				&& ((this.getTipoContrato() == castOther.getTipoContrato()) || (this
						.getTipoContrato() != null
						&& castOther.getTipoContrato() != null && this
						.getTipoContrato().equals(castOther.getTipoContrato())))
				&& ((this.getFechaInicio() == castOther.getFechaInicio()) || (this
						.getFechaInicio() != null
						&& castOther.getFechaInicio() != null && this
						.getFechaInicio().equals(castOther.getFechaInicio())))
				&& ((this.getFechaFin() == castOther.getFechaFin()) || (this
						.getFechaFin() != null
						&& castOther.getFechaFin() != null && this
						.getFechaFin().equals(castOther.getFechaFin())))
				&& ((this.getSueldoBase() == castOther.getSueldoBase()) || (this
						.getSueldoBase() != null
						&& castOther.getSueldoBase() != null && this
						.getSueldoBase().equals(castOther.getSueldoBase())))
				&& ((this.getEstado() == castOther.getEstado()) || (this
						.getEstado() != null
						&& castOther.getEstado() != null && this.getEstado()
						.equals(castOther.getEstado())))
				&& ((this.getIdEmpresa() == castOther.getIdEmpresa()) || (this
						.getIdEmpresa() != null
						&& castOther.getIdEmpresa() != null && this
						.getIdEmpresa().equals(castOther.getIdEmpresa())))
				&& ((this.getIdInstitucionSalud() == castOther
						.getIdInstitucionSalud()) || (this
						.getIdInstitucionSalud() != null
						&& castOther.getIdInstitucionSalud() != null && this
						.getIdInstitucionSalud().equals(
								castOther.getIdInstitucionSalud())))
				&& ((this.getIdInstitucionPrevision() == castOther
						.getIdInstitucionPrevision()) || (this
						.getIdInstitucionPrevision() != null
						&& castOther.getIdInstitucionPrevision() != null && this
						.getIdInstitucionPrevision().equals(
								castOther.getIdInstitucionPrevision())))
				&& ((this.getIdGrupoHabDesc() == castOther.getIdGrupoHabDesc()) || (this
						.getIdGrupoHabDesc() != null
						&& castOther.getIdGrupoHabDesc() != null && this
						.getIdGrupoHabDesc().equals(
								castOther.getIdGrupoHabDesc())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getRut() == null ? 0 : this.getRut().hashCode());
		result = 37 * result
				+ (getNombre() == null ? 0 : this.getNombre().hashCode());
		result = 37
				* result
				+ (getApellidoPaterno() == null ? 0 : this.getApellidoPaterno()
						.hashCode());
		result = 37
				* result
				+ (getApellidoMaterno() == null ? 0 : this.getApellidoMaterno()
						.hashCode());
		result = 37
				* result
				+ (getFechaNacimiento() == null ? 0 : this.getFechaNacimiento()
						.hashCode());
		result = 37
				* result
				+ (getNacionalidad() == null ? 0 : this.getNacionalidad()
						.hashCode());
		result = 37 * result
				+ (getSexo() == null ? 0 : this.getSexo().hashCode());
		result = 37 * result
				+ (getDireccion() == null ? 0 : this.getDireccion().hashCode());
		result = 37
				* result
				+ (getTelefonoFijo() == null ? 0 : this.getTelefonoFijo()
						.hashCode());
		result = 37 * result
				+ (getCelular() == null ? 0 : this.getCelular().hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37
				* result
				+ (getNumeroCargas() == null ? 0 : this.getNumeroCargas()
						.hashCode());
		result = 37
				* result
				+ (getTipoContrato() == null ? 0 : this.getTipoContrato()
						.hashCode());
		result = 37
				* result
				+ (getFechaInicio() == null ? 0 : this.getFechaInicio()
						.hashCode());
		result = 37 * result
				+ (getFechaFin() == null ? 0 : this.getFechaFin().hashCode());
		result = 37
				* result
				+ (getSueldoBase() == null ? 0 : this.getSueldoBase()
						.hashCode());
		result = 37 * result
				+ (getEstado() == null ? 0 : this.getEstado().hashCode());
		result = 37 * result
				+ (getIdEmpresa() == null ? 0 : this.getIdEmpresa().hashCode());
		result = 37
				* result
				+ (getIdInstitucionSalud() == null ? 0 : this
						.getIdInstitucionSalud().hashCode());
		result = 37
				* result
				+ (getIdInstitucionPrevision() == null ? 0 : this
						.getIdInstitucionPrevision().hashCode());
		result = 37
				* result
				+ (getIdGrupoHabDesc() == null ? 0 : this.getIdGrupoHabDesc()
						.hashCode());
		return result;
	}

}