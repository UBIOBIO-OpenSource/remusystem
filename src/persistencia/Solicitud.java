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
 * Solicitud entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "solicitud", catalog = "tesisfinal")
public class Solicitud implements java.io.Serializable {

	// Fields

	private Integer id;
	private OtrosDescuentos otrosDescuentos;
	private RelacionLaboral relacionLaboral;

	// Constructors

	/** default constructor */
	public Solicitud() {
	}

	/** full constructor */
	public Solicitud(OtrosDescuentos otrosDescuentos,
			RelacionLaboral relacionLaboral) {
		this.otrosDescuentos = otrosDescuentos;
		this.relacionLaboral = relacionLaboral;
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
	@JoinColumn(name = "id_otros_descuentos")
	public OtrosDescuentos getOtrosDescuentos() {
		return this.otrosDescuentos;
	}

	public void setOtrosDescuentos(OtrosDescuentos otrosDescuentos) {
		this.otrosDescuentos = otrosDescuentos;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_relacion_laboral")
	public RelacionLaboral getRelacionLaboral() {
		return this.relacionLaboral;
	}

	public void setRelacionLaboral(RelacionLaboral relacionLaboral) {
		this.relacionLaboral = relacionLaboral;
	}

}