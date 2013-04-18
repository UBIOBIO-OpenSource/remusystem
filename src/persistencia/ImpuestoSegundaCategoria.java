package persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ImpuestoSegundaCategoria entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "impuesto_segunda_categoria", catalog = "tesisfinal")
public class ImpuestoSegundaCategoria implements java.io.Serializable {

	// Fields

	private Integer id;
	private Double desde;
	private Double hasta;
	private Double factor;
	private Double rebaja;

	// Constructors

	/** default constructor */
	public ImpuestoSegundaCategoria() {
	}

	/** full constructor */
	public ImpuestoSegundaCategoria(Double desde, Double hasta, Double factor,
			Double rebaja) {
		this.desde = desde;
		this.hasta = hasta;
		this.factor = factor;
		this.rebaja = rebaja;
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

	@Column(name = "desde", precision = 10, scale = 3)
	public Double getDesde() {
		return this.desde;
	}

	public void setDesde(Double desde) {
		this.desde = desde;
	}

	@Column(name = "hasta", precision = 10, scale = 3)
	public Double getHasta() {
		return this.hasta;
	}

	public void setHasta(Double hasta) {
		this.hasta = hasta;
	}

	@Column(name = "factor", precision = 10, scale = 3)
	public Double getFactor() {
		return this.factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	@Column(name = "rebaja", precision = 10, scale = 3)
	public Double getRebaja() {
		return this.rebaja;
	}

	public void setRebaja(Double rebaja) {
		this.rebaja = rebaja;
	}

}