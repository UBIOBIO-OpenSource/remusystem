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
 * CajaCompensacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "caja_compensacion", catalog = "tesisfinal")
public class CajaCompensacion implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nombre;
	private Double comision;
	private Double porcentajeDescuento;
	private Set<Empresa> empresas = new HashSet<Empresa>(0);

	// Constructors

	/** default constructor */
	public CajaCompensacion() {
	}

	/** full constructor */
	public CajaCompensacion(String nombre, Double comision,
			Double porcentajeDescuento, Set<Empresa> empresas) {
		this.nombre = nombre;
		this.comision = comision;
		this.porcentajeDescuento = porcentajeDescuento;
		this.empresas = empresas;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cajaCompensacion")
	public Set<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

}