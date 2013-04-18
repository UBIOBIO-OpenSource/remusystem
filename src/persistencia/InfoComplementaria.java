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
 * InfoComplementaria entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "info_complementaria", catalog = "tesisfinal")
public class InfoComplementaria implements java.io.Serializable {

	// Fields

	private Integer id;
	private DetalleLiquidacion detalleLiquidacion;
	private Double baseDeCalculo;
	private Double factor;

	// Constructors

	/** default constructor */
	public InfoComplementaria() {
	}

	/** full constructor */
	public InfoComplementaria(DetalleLiquidacion detalleLiquidacion,
			Double baseDeCalculo, Double factor) {
		this.detalleLiquidacion = detalleLiquidacion;
		this.baseDeCalculo = baseDeCalculo;
		this.factor = factor;
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
	@JoinColumn(name = "id_detalle_liquidacion")
	public DetalleLiquidacion getDetalleLiquidacion() {
		return this.detalleLiquidacion;
	}

	public void setDetalleLiquidacion(DetalleLiquidacion detalleLiquidacion) {
		this.detalleLiquidacion = detalleLiquidacion;
	}

	@Column(name = "base_de_calculo", precision = 20, scale = 5)
	public Double getBaseDeCalculo() {
		return this.baseDeCalculo;
	}

	public void setBaseDeCalculo(Double baseDeCalculo) {
		this.baseDeCalculo = baseDeCalculo;
	}

	@Column(name = "factor", precision = 20, scale = 5)
	public Double getFactor() {
		return this.factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

}