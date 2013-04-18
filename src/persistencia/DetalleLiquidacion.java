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
 * DetalleLiquidacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "detalle_liquidacion", catalog = "tesisfinal")
public class DetalleLiquidacion implements java.io.Serializable {

	// Fields

	private Integer id;
	private LiquidacionDeSueldo liquidacionDeSueldo;
	private String descripcion;
	private Double monto;
	private Integer posicion;
	private String enPalabras;
	private Set<InfoComplementaria> infoComplementarias = new HashSet<InfoComplementaria>(
			0);

	// Constructors

	/** default constructor */
	public DetalleLiquidacion() {
	}

	/** full constructor */
	public DetalleLiquidacion(LiquidacionDeSueldo liquidacionDeSueldo,
			String descripcion, Double monto, Integer posicion,
			String enPalabras, Set<InfoComplementaria> infoComplementarias) {
		this.liquidacionDeSueldo = liquidacionDeSueldo;
		this.descripcion = descripcion;
		this.monto = monto;
		this.posicion = posicion;
		this.enPalabras = enPalabras;
		this.infoComplementarias = infoComplementarias;
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
	@JoinColumn(name = "id_liquidacion_sueldo")
	public LiquidacionDeSueldo getLiquidacionDeSueldo() {
		return this.liquidacionDeSueldo;
	}

	public void setLiquidacionDeSueldo(LiquidacionDeSueldo liquidacionDeSueldo) {
		this.liquidacionDeSueldo = liquidacionDeSueldo;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "monto", precision = 20)
	public Double getMonto() {
		return this.monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@Column(name = "posicion")
	public Integer getPosicion() {
		return this.posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	@Column(name = "en_palabras")
	public String getEnPalabras() {
		return this.enPalabras;
	}

	public void setEnPalabras(String enPalabras) {
		this.enPalabras = enPalabras;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detalleLiquidacion")
	public Set<InfoComplementaria> getInfoComplementarias() {
		return this.infoComplementarias;
	}

	public void setInfoComplementarias(
			Set<InfoComplementaria> infoComplementarias) {
		this.infoComplementarias = infoComplementarias;
	}

}