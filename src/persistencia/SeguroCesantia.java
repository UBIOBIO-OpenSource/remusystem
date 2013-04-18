package persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SeguroCesantia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "seguro_cesantia", catalog = "tesisfinal")
public class SeguroCesantia implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tipoContrato;
	private Double empleador;
	private Double trabajador;

	// Constructors

	/** default constructor */
	public SeguroCesantia() {
	}

	/** full constructor */
	public SeguroCesantia(String tipoContrato, Double empleador,
			Double trabajador) {
		this.tipoContrato = tipoContrato;
		this.empleador = empleador;
		this.trabajador = trabajador;
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

	@Column(name = "tipo_contrato")
	public String getTipoContrato() {
		return this.tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	@Column(name = "empleador", precision = 10)
	public Double getEmpleador() {
		return this.empleador;
	}

	public void setEmpleador(Double empleador) {
		this.empleador = empleador;
	}

	@Column(name = "trabajador", precision = 10)
	public Double getTrabajador() {
		return this.trabajador;
	}

	public void setTrabajador(Double trabajador) {
		this.trabajador = trabajador;
	}

}