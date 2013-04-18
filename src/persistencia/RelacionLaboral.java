package persistencia;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * RelacionLaboral entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="relacion_laboral"
    ,catalog="tesisfinal"
)

public class RelacionLaboral  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Trabajador trabajador;
     private Empresa empresa;
     private InstitucionSalud institucionSalud;
     private InstitucionPrevision institucionPrevision;
     private GrupoHabDesc grupoHabDesc;
     private String tipoContrato;
     private Date fechaInicio;
     private Date fechaFin;
     private Integer sueldoBase;
     private String rutaArchivoRespaldo;
     private Boolean estado;
     private Double valorPlanIsapre;
//     private Set<SolicitudAbono> solicitudAbonos = new HashSet<SolicitudAbono>(0);
//     private Set<Solicitud> solicituds = new HashSet<Solicitud>(0);
//     private Set<LiquidacionDeSueldo> liquidacionDeSueldos = new HashSet<LiquidacionDeSueldo>(0);


    // Constructors

    /** default constructor */
    public RelacionLaboral() {
    }

    
    /** full constructor */
    public RelacionLaboral(Trabajador trabajador, Empresa empresa, InstitucionSalud institucionSalud, InstitucionPrevision institucionPrevision, GrupoHabDesc grupoHabDesc, String tipoContrato, Date fechaInicio, Date fechaFin, Integer sueldoBase, String rutaArchivoRespaldo, Boolean estado, Double valorPlanIsapre, Set<SolicitudAbono> solicitudAbonos, Set<Solicitud> solicituds, Set<LiquidacionDeSueldo> liquidacionDeSueldos) {
        this.trabajador = trabajador;
        this.empresa = empresa;
        this.institucionSalud = institucionSalud;
        this.institucionPrevision = institucionPrevision;
        this.grupoHabDesc = grupoHabDesc;
        this.tipoContrato = tipoContrato;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.sueldoBase = sueldoBase;
        this.rutaArchivoRespaldo = rutaArchivoRespaldo;
        this.estado = estado;
        this.valorPlanIsapre = valorPlanIsapre;
//        this.solicitudAbonos = solicitudAbonos;
//        this.solicituds = solicituds;
//        this.liquidacionDeSueldos = liquidacionDeSueldos;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="Id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="id_trabajador")

    public Trabajador getTrabajador() {
        return this.trabajador;
    }
    
    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="id_empresa")

    public Empresa getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="id_institucion_salud")

    public InstitucionSalud getInstitucionSalud() {
        return this.institucionSalud;
    }
    
    public void setInstitucionSalud(InstitucionSalud institucionSalud) {
        this.institucionSalud = institucionSalud;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="id_institucion_prevision")

    public InstitucionPrevision getInstitucionPrevision() {
        return this.institucionPrevision;
    }
    
    public void setInstitucionPrevision(InstitucionPrevision institucionPrevision) {
        this.institucionPrevision = institucionPrevision;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="id_grupo_hab_desc")

    public GrupoHabDesc getGrupoHabDesc() {
        return this.grupoHabDesc;
    }
    
    public void setGrupoHabDesc(GrupoHabDesc grupoHabDesc) {
        this.grupoHabDesc = grupoHabDesc;
    }
    
    @Column(name="tipo_contrato")

    public String getTipoContrato() {
        return this.tipoContrato;
    }
    
    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_inicio", length=10)

    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_fin", length=10)

    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    @Column(name="sueldo_base")

    public Integer getSueldoBase() {
        return this.sueldoBase;
    }
    
    public void setSueldoBase(Integer sueldoBase) {
        this.sueldoBase = sueldoBase;
    }
    
    @Column(name="ruta_archivo_respaldo")

    public String getRutaArchivoRespaldo() {
        return this.rutaArchivoRespaldo;
    }
    
    public void setRutaArchivoRespaldo(String rutaArchivoRespaldo) {
        this.rutaArchivoRespaldo = rutaArchivoRespaldo;
    }
    
    @Column(name="estado")

    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    @Column(name="valor_plan_isapre", precision=10, scale=3)

    public Double getValorPlanIsapre() {
        return this.valorPlanIsapre;
    }
    
    public void setValorPlanIsapre(Double valorPlanIsapre) {
        this.valorPlanIsapre = valorPlanIsapre;
    }
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="relacionLaboral")
//
//    public Set<SolicitudAbono> getSolicitudAbonos() {
//        return this.solicitudAbonos;
//    }
//    
//    public void setSolicitudAbonos(Set<SolicitudAbono> solicitudAbonos) {
//        this.solicitudAbonos = solicitudAbonos;
//    }
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="relacionLaboral")
//
//    public Set<Solicitud> getSolicituds() {
//        return this.solicituds;
//    }
//    
//    public void setSolicituds(Set<Solicitud> solicituds) {
//        this.solicituds = solicituds;
//    }
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="relacionLaboral")
//
//    public Set<LiquidacionDeSueldo> getLiquidacionDeSueldos() {
//        return this.liquidacionDeSueldos;
//    }
//    
//    public void setLiquidacionDeSueldos(Set<LiquidacionDeSueldo> liquidacionDeSueldos) {
//        this.liquidacionDeSueldos = liquidacionDeSueldos;
//    }
//   








}