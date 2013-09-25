package org.remusystem.persistencia;

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
 * Agrupar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agrupar", catalog = "remusystem")
public class Agrupar implements java.io.Serializable {

	// Fields

	private Integer id;
	private GrupoHabDesc grupoHabDesc;
	private TipoHaberDesc tipoHaberDesc;

	// Constructors

	/** default constructor */
	public Agrupar() {
	}

	/** full constructor */
	public Agrupar(GrupoHabDesc grupoHabDesc, TipoHaberDesc tipoHaberDesc) {
		this.grupoHabDesc = grupoHabDesc;
		this.tipoHaberDesc = tipoHaberDesc;
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
	@JoinColumn(name = "id_grupo_hab_desc")
	public GrupoHabDesc getGrupoHabDesc() {
		return this.grupoHabDesc;
	}

	public void setGrupoHabDesc(GrupoHabDesc grupoHabDesc) {
		this.grupoHabDesc = grupoHabDesc;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_hab_desc")
	public TipoHaberDesc getTipoHaberDesc() {
		return this.tipoHaberDesc;
	}

	public void setTipoHaberDesc(TipoHaberDesc tipoHaberDesc) {
		this.tipoHaberDesc = tipoHaberDesc;
	}

}