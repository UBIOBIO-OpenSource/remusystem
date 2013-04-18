package persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Topes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "topes", catalog = "tesisfinal")
public class Topes implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nombre;
	private Double montoUf;

	// Constructors

	/** default constructor */
	public Topes() {
	}

	/** full constructor */
	public Topes(String nombre, Double montoUf) {
		this.nombre = nombre;
		this.montoUf = montoUf;
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

	@Column(name = "nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "monto_uf", precision = 10)
	public Double getMontoUf() {
		return this.montoUf;
	}

	public void setMontoUf(Double montoUf) {
		this.montoUf = montoUf;
	}

}