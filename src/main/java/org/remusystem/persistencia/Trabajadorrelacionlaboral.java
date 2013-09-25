package org.remusystem.persistencia;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Trabajadorrelacionlaboral entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trabajadorrelacionlaboral", catalog = "remusystem")
public class Trabajadorrelacionlaboral implements java.io.Serializable {

	// Fields

	private TrabajadorrelacionlaboralId id;

	// Constructors

	/** default constructor */
	public Trabajadorrelacionlaboral() {
	}

	/** full constructor */
	public Trabajadorrelacionlaboral(TrabajadorrelacionlaboralId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "id", column = @Column(name = "Id", nullable = false)),
			@AttributeOverride(name = "rut", column = @Column(name = "rut")),
			@AttributeOverride(name = "nombre", column = @Column(name = "nombre")),
			@AttributeOverride(name = "apellidoPaterno", column = @Column(name = "apellido_paterno")),
			@AttributeOverride(name = "apellidoMaterno", column = @Column(name = "apellido_materno")),
			@AttributeOverride(name = "fechaNacimiento", column = @Column(name = "fecha_nacimiento", length = 10)),
			@AttributeOverride(name = "nacionalidad", column = @Column(name = "nacionalidad")),
			@AttributeOverride(name = "sexo", column = @Column(name = "sexo")),
			@AttributeOverride(name = "direccion", column = @Column(name = "direccion")),
			@AttributeOverride(name = "telefonoFijo", column = @Column(name = "telefono_fijo")),
			@AttributeOverride(name = "celular", column = @Column(name = "celular")),
			@AttributeOverride(name = "email", column = @Column(name = "email")),
			@AttributeOverride(name = "numeroCargas", column = @Column(name = "numero_cargas")),
			@AttributeOverride(name = "tipoContrato", column = @Column(name = "tipo_contrato")),
			@AttributeOverride(name = "fechaInicio", column = @Column(name = "fecha_inicio", length = 10)),
			@AttributeOverride(name = "fechaFin", column = @Column(name = "fecha_fin", length = 10)),
			@AttributeOverride(name = "sueldoBase", column = @Column(name = "sueldo_base")),
			@AttributeOverride(name = "estado", column = @Column(name = "estado")),
			@AttributeOverride(name = "idEmpresa", column = @Column(name = "id_empresa")),
			@AttributeOverride(name = "idInstitucionSalud", column = @Column(name = "id_institucion_salud")),
			@AttributeOverride(name = "idInstitucionPrevision", column = @Column(name = "id_institucion_prevision")),
			@AttributeOverride(name = "idGrupoHabDesc", column = @Column(name = "id_grupo_hab_desc")) })
	public TrabajadorrelacionlaboralId getId() {
		return this.id;
	}

	public void setId(TrabajadorrelacionlaboralId id) {
		this.id = id;
	}

}