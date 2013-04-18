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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * InstitucionSalud entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "institucion_salud", catalog = "tesisfinal")
public class InstitucionSalud implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nombre;
	private Double comision;
	private Double porcentajeDescuento;
	

	// Constructors

	/** default constructor */
	public InstitucionSalud() {
	}

	/** full constructor */
	public InstitucionSalud(String nombre, Double comision,
			Double porcentajeDescuento, Set<RelacionLaboral> relacionLaborals) {
		this.nombre = nombre;
		this.comision = comision;
		this.porcentajeDescuento = porcentajeDescuento;
		
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

	@Column(name = "comision", precision = 10, scale = 5)
	public Double getComision() {
		return this.comision;
	}

	public void setComision(Double comision) {
		this.comision = comision;
	}

	@Column(name = "porcentaje_descuento", precision = 10, scale = 5)
	public Double getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}

	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	

}