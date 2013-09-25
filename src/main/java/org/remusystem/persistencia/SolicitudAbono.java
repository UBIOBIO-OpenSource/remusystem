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
 * SolicitudAbono entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "solicitud_abono", catalog = "remusystem")
public class SolicitudAbono implements java.io.Serializable {

	// Fields

	private Integer id;
	private RelacionLaboral relacionLaboral;
	private Abonos abonos;

	// Constructors

	/** default constructor */
	public SolicitudAbono() {
	}

	/** full constructor */
	public SolicitudAbono(RelacionLaboral relacionLaboral, Abonos abonos) {
		this.relacionLaboral = relacionLaboral;
		this.abonos = abonos;
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
	@JoinColumn(name = "id_relacion_laboral")
	public RelacionLaboral getRelacionLaboral() {
		return this.relacionLaboral;
	}

	public void setRelacionLaboral(RelacionLaboral relacionLaboral) {
		this.relacionLaboral = relacionLaboral;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_abonos")
	public Abonos getAbonos() {
		return this.abonos;
	}

	public void setAbonos(Abonos abonos) {
		this.abonos = abonos;
	}

}