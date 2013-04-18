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
 * GrupoHabDesc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "grupo_hab_desc", catalog = "tesisfinal")
public class GrupoHabDesc implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cargo;
	private Set<RelacionLaboral> relacionLaborals = new HashSet<RelacionLaboral>(
			0);
	private Set<Agrupar> agrupars = new HashSet<Agrupar>(0);

	// Constructors

	/** default constructor */
	public GrupoHabDesc() {
	}

	/** full constructor */
	public GrupoHabDesc(String cargo, Set<RelacionLaboral> relacionLaborals,
			Set<Agrupar> agrupars) {
		this.cargo = cargo;
		this.relacionLaborals = relacionLaborals;
		this.agrupars = agrupars;
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

	@Column(name = "cargo")
	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grupoHabDesc")
	public Set<RelacionLaboral> getRelacionLaborals() {
		return this.relacionLaborals;
	}

	public void setRelacionLaborals(Set<RelacionLaboral> relacionLaborals) {
		this.relacionLaborals = relacionLaborals;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grupoHabDesc")
	public Set<Agrupar> getAgrupars() {
		return this.agrupars;
	}

	public void setAgrupars(Set<Agrupar> agrupars) {
		this.agrupars = agrupars;
	}

}