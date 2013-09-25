package org.remusystem.actions;

import java.io.File;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.views.jasperreports.ValueStackShadowMap;
import org.hibernate.dialect.InformixDialect;
import org.remusystem.actions.reportes.ReportConector;
import org.remusystem.persistencia.*;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionSupport;

import org.remusystem.control.Controlador;
import org.remusystem.control.NumLetrasJ;

import javax.servlet.ServletContext;

public class CalcularSueldoTrabajadoresALL extends ActionSupport implements SessionAware {

    //para calcular el sueldo
    private Map session;
    private String Dias = "30";
    private String HorasExtras = "0";
    private Boolean Horas = false;
    private Date Fecha = new Date();
    private List<RelacionLaboral> todosEMP = new ArrayList<RelacionLaboral>();
    //para generar el reporte automaticamente
    private Date fecha = new Date();
    private Map<String, Object> parametros;
    private Connection conexion;
    //variables para mostarlas en el resumen despues
    private Double SueldoBaseImponibleTrabajado;
    private Double HorasExtrasTrabajadas;
    private Double ValorGratificacion;
    private Double AbonosImponibles;
    private Double AbonosImponiblesNoTributables;
    private Double BaseImponible;
    private Double cotizarAFP;
    private Double cotizarSalud;
    private Double cargoTrabajadorAFC;
    private Double cargoEmpleadorAFC;
    private Double SegurodeCesantia;
    private Double AbonosNoImponiblesTributables;
    private Double BaseTributable;
    private Double ImpuestoTributarioaPagar;
    private Double SuelDspsImpto;
    private Double AbonosNoImponiblesNoTributables;
    private Double Anticipos;
    private Double SueldoLiquido;
    private Double AsignacionaPagar;

    public String execute() {

        Controlador control = Controlador.getInstance();

        String Mes = TransformarMes(Fecha.getMonth()); //transforma el numero del 0-11 en el nombre del mes

        System.out.println("Numero día? :" + Fecha.getDate());
        System.out.println("Numero MES: " + Fecha.getMonth());
        System.out.println("fecha: " + Mes);
        Integer numAnio = Fecha.getYear() + 1900;
        System.out.println("Año: " + numAnio);
        System.out.println("fecha: " + Fecha);

        System.out.println(getHorasExtras());
        System.out.println(getDias());

        Usuario user = (Usuario) session.get("User");
        Empresa emp = control.buscaEmpresa(user.getRut());

        todosEMP = control.RelacionIdEmpresa(emp.getId());
        if (todosEMP != null) {
            for (int j = 0; j < todosEMP.size(); j++) {

                RelacionLaboral rel = todosEMP.get(j);
                LiquidacionDeSueldoDAO liqDAO = new LiquidacionDeSueldoDAO();
                LiquidacionDeSueldo li = liqDAO.findBYIDrelAnioMes(rel.getId(), numAnio, Mes);

                if (li == null) {

                    Trabajador trab = rel.getTrabajador();
                    Integer SueldoBase = rel.getSueldoBase();
                    TopesDAO tDAO = new TopesDAO(); //iniciamos variables
                    List<Topes> topes = tDAO.findAll(); //iniciamos variables
                    ValoresDAO vDAO = new ValoresDAO(); //iniciamos variables
                    List<Valores> valores = vDAO.findAll(); //iniciamos variables
                    Double TopeImponible = 0.0; //iniciamos variables
                    Double TopeSeguroCesantia = 0.0; //iniciamos variables
                    Double TopeAPV = 0.0;  //iniciamos variables


                    for (int i = 0; i < topes.size(); i++) { //obtengo los topes
                        if (topes.get(i).getNombre().equals("Tope Imponible")) {
                            TopeImponible = topes.get(i).getMontoUf() * valores.get(1).getMonto(); //tope imponible AFP
                            System.out.println(TopeImponible);
                        } else if (topes.get(i).getNombre().equals("Tope Seguro Cesantia")) { //tope seguro cesantia
                            TopeSeguroCesantia = topes.get(i).getMontoUf() * valores.get(1).getMonto();
                            System.out.println(TopeSeguroCesantia);
                        } else if (topes.get(i).getNombre().equals("Tope APV")) { //tope ahorro previsional Voluntario
                            TopeAPV = topes.get(i).getMontoUf() * valores.get(1).getMonto();
                            System.out.println(TopeAPV);
                        }
                    }

                    Double valorhoraextra = ((((SueldoBase / 30) * 7)) / 45) * 1.5; //formula extraida del apunte de la profesora
                    String suel = String.valueOf(SueldoBase); //paso el sueldo base a string
                    Double Sueldo = Double.parseDouble(suel); //lo transformo en double
                    Double valordia = (Sueldo / 30); //calculo el valor diario
                    Double valorhoranormal = (((Sueldo / 30) * 7)) / 45; //calculo el valor de la hora

                    SueldoBaseImponibleTrabajado = 0.0;

                    //calculamos la base imponible
                    if (Horas) {
                        SueldoBaseImponibleTrabajado = valorhoranormal * (Integer.parseInt(Dias)); //valor imponible de las horas trabajadas
                    } else {
                        SueldoBaseImponibleTrabajado = valordia * (Integer.parseInt(Dias)); //valor imponible de los dias trabajados
                    }
                    HorasExtrasTrabajadas = valorhoraextra * (Integer.parseInt(HorasExtras)); //valor imponible de las horas extras
                    ValorGratificacion = calculagratificacion(Sueldo); //entrega el valor imponible de la gratificacion
                    BaseImponible = (SueldoBaseImponibleTrabajado + HorasExtrasTrabajadas + ValorGratificacion);

                    System.out.println("SueldoBaseImponibleTrabajado: " + SueldoBaseImponibleTrabajado);
                    System.out.println("HorasExtrasTrabajadas: " + HorasExtrasTrabajadas);
                    System.out.println("ValorGratificacion: " + ValorGratificacion);
                    System.out.println("Base Imponible: " + BaseImponible);
                    System.out.println("redondeo: " + Redondear(SueldoBaseImponibleTrabajado, 2));
                    System.out.println("redondeo 2: " + Redondear(BaseImponible, 2));

                    //ahora sumamos los abonos imponibles tributables
                    SolicitudAbonoDAO solDAO = new SolicitudAbonoDAO();
                    List<SolicitudAbono> abonos = new ArrayList<SolicitudAbono>();
                    abonos = solDAO.findByIDrel(rel.getId());
                    List<Abonos> abonosImponibles = new ArrayList<Abonos>();
                    AbonosImponibles = 0.0;
                    AbonosImponiblesNoTributables = 0.0;
                    if (abonos != null) {
                        for (int i = 0; i < abonos.size(); i++) {
                            Abonos abonotemporal = abonos.get(i).getAbonos();
                            if (calcularFechaFinal(abonotemporal.getFechaFinal()).after(Fecha) && (abonotemporal.getTipoAbono().equals("Imponible Tributable"))) {
                                Integer AbonoRevisado = abonotemporal.getMonto();
                                AbonosImponibles = AbonosImponibles + abonotemporal.getMonto();
                                System.out.println(AbonosImponibles);
                            } else {
                                AbonosImponibles = AbonosImponibles;
                            }
                        }

                        //Sumamos aparte los abonos imponibles no tributables

                        for (int i = 0; i < abonos.size(); i++) {
                            Abonos abonotemporal = abonos.get(i).getAbonos();
                            if (calcularFechaFinal(abonotemporal.getFechaFinal()).after(Fecha) && (abonotemporal.getTipoAbono().equals("Imponible NO Tributable"))) {
                                Integer AbonoRevisado = abonotemporal.getMonto();
                                AbonosImponibles = AbonosImponiblesNoTributables + abonotemporal.getMonto();
                                System.out.println(AbonosImponiblesNoTributables);
                            } else {
                                AbonosImponiblesNoTributables = AbonosImponiblesNoTributables;
                            }
                        }
                    }

                    BaseImponible = BaseImponible + AbonosImponibles + AbonosImponiblesNoTributables;
                    //ya tengo la base imponible que es la variable BaseImponible!!
                    //Ahora sacamos los descuentos
                    cotizarAFP = 0.0;
                    InstitucionPrevision AFP = rel.getInstitucionPrevision(); //busco la AFP
                    if (BaseImponible >= TopeImponible) { //si se cumple cotizamos por el tope imponible para la AFP
                        cotizarAFP = TopeImponible * AFP.getPorcentajeDescuento(); //calculo el descuento de la afp
                        System.out.println("CotizarAFP: " + cotizarAFP);
                    } else {
                        cotizarAFP = BaseImponible * AFP.getPorcentajeDescuento(); //calculo el dscto de la AFP
                        System.out.println("CotizarAFP: " + cotizarAFP);
                    }

                    Double cotSalud = 0.0;
                    cotizarSalud = 0.0;
                    if (rel.getInstitucionSalud().getId().equals(1)) { //para FONASA
                        cotSalud = BaseImponible * (rel.getInstitucionSalud().getPorcentajeDescuento()); //calculo el dscto
                        cotizarSalud = cotSalud;
                        System.out.println("Cotizacion Salud FONASA: " + cotSalud);
                    } else { //para ISAPRE
                        cotSalud = BaseImponible * 0.07;
                        Double valorPlanIsapre = rel.getValorPlanIsapre() * valores.get(1).getMonto();
                        if (valorPlanIsapre >= cotSalud) {//debo cotizar por el valor de la isapre sino por el 7%
                            cotizarSalud = valorPlanIsapre;
                            System.out.println("Cotizar Salud ISAPRE valor plan%: " + cotizarSalud);
                        } else {
                            cotizarSalud = BaseImponible * 0.07;
                            System.out.println("Cotizar Salud ISAPRE: " + cotizarSalud);
                        }
                    }

                    System.out.println("Valor UF Actual: " + valores.get(1).getMonto());

                    SeguroCesantiaDAO sDAO = new SeguroCesantiaDAO();
                    List<SeguroCesantia> cesantia = sDAO.findAll();

                    //el seguro de cesantia
                    cargoEmpleadorAFC = 0.0;
                    cargoTrabajadorAFC = 0.0;
                    if (BaseImponible < TopeSeguroCesantia) {
                        if (rel.getTipoContrato().equals("indefinido")) {
                            cargoEmpleadorAFC = BaseImponible * ((cesantia.get(0).getEmpleador()) / 100);
                            cargoTrabajadorAFC = BaseImponible * ((cesantia.get(0).getTrabajador()) / 100);
                            System.out.println("AFC emp indefinido: " + cargoEmpleadorAFC);
                            System.out.println("AFC trab indefinido: " + cargoTrabajadorAFC);
                        } else if (rel.getTipoContrato().equals("fijo")) {
                            cargoEmpleadorAFC = BaseImponible * ((cesantia.get(0).getEmpleador()) / 100);

                            System.out.println("AFC emp contrato fijo: " + cargoEmpleadorAFC);

                        }
                    } else {
                        if (rel.getTipoContrato().equals("indefinido")) {
                            cargoEmpleadorAFC = TopeSeguroCesantia * ((cesantia.get(0).getEmpleador()) / 100);
                            cargoTrabajadorAFC = TopeSeguroCesantia * ((cesantia.get(0).getTrabajador()) / 100);
                            System.out.println("AFC emp indefinido: " + cargoEmpleadorAFC);
                            System.out.println("AFC trab indefinido: " + cargoTrabajadorAFC);
                        } else if (rel.getTipoContrato().equals("fijo")) {
                            cargoEmpleadorAFC = TopeSeguroCesantia * ((cesantia.get(0).getEmpleador()) / 100);

                            System.out.println("AFC emp contrato fijo: " + cargoEmpleadorAFC);

                        }
                    }


                    //Ahora Buscamos los Abonos No Imponibles pero tributables
                    AbonosNoImponiblesTributables = 0.0;
                    if (abonos != null) {
                        for (int i = 0; i < abonos.size(); i++) {
                            Abonos abonotemporal = abonos.get(i).getAbonos();
                            if (calcularFechaFinal(abonotemporal.getFechaFinal()).after(Fecha) && (abonotemporal.getTipoAbono().equals("NO Imponible Tributable"))) {
                                Integer AbonoRevisado = abonotemporal.getMonto();
                                AbonosNoImponiblesTributables = AbonosNoImponiblesTributables + abonotemporal.getMonto();
                                System.out.println(AbonosNoImponiblesTributables);
                            } else {
                                AbonosNoImponiblesTributables = AbonosNoImponiblesTributables;
                            }
                        }
                    }
                    //la base tributable seria entonces
                    BaseTributable = BaseImponible - cotizarAFP - cotizarSalud - cargoTrabajadorAFC + AbonosNoImponiblesTributables;
                    System.out.println("Base Tributable = " + BaseTributable);

                    //CALCULAMOS EL IMPUESTO TRIBUTARIO O DE SEGUNDA CATEGOR�A.
                    ImpuestoSegundaCategoriaDAO impuestoDAO = new ImpuestoSegundaCategoriaDAO();
                    List<ImpuestoSegundaCategoria> rangos = impuestoDAO.findAll();
                    ImpuestoTributarioaPagar = calcularImpuesto(BaseTributable);

                    //CALCULAMOS LA ASIGNACION FAMILIAR CORRESPONDIENTE
                    AsignacionaPagar = calcularAsignacionFamiliar(BaseTributable, rel.getTrabajador().getNumeroCargas());

                    //Le restamos a la base tributable el impuesto a pagar
                    SuelDspsImpto = BaseTributable - ImpuestoTributarioaPagar;
                    System.out.println("Sueldo Despues de Impuesto: " + SuelDspsImpto);

                    //Ahora Buscamos los Abonos No Imponibles NO tributables
                    AbonosNoImponiblesNoTributables = 0.0;
                    if (abonos != null) {
                        for (int i = 0; i < abonos.size(); i++) {
                            Abonos abonotemporal = abonos.get(i).getAbonos();
                            if (calcularFechaFinal(abonotemporal.getFechaFinal()).after(Fecha) && (abonotemporal.getTipoAbono().equals("NO Imponible NO Tributable"))) {
                                Integer AbonoRevisado = abonotemporal.getMonto();
                                AbonosNoImponiblesNoTributables = AbonosNoImponiblesNoTributables + abonotemporal.getMonto();
                                System.out.println("Abonos No Imp No tri: " + AbonosNoImponiblesTributables);
                            } else {
                                AbonosNoImponiblesNoTributables = AbonosNoImponiblesNoTributables;
                            }
                        }
                    }

                    //Ahora buscamos todos los otros tipos de descuentos como anticipos y similares.
                    SolicitudDAO anticipoDAO = new SolicitudDAO();
                    List<Solicitud> solicitudAnticipos = anticipoDAO.findByIDrel(rel.getId());
                    Anticipos = 0.0;
                    if (solicitudAnticipos != null) {
                        for (int i = 0; i < solicitudAnticipos.size(); i++) {
                            OtrosDescuentos anticipotemporal = solicitudAnticipos.get(i).getOtrosDescuentos();
                            if (calcularFechaFinal(anticipotemporal.getFechaFinal()).after(Fecha)) {
                                Integer AnticipoRevisado = anticipotemporal.getMonto();
                                Anticipos = Anticipos + anticipotemporal.getMonto();
                                System.out.println("Anticipos: " + Anticipos);
                            } else {
                                Anticipos = Anticipos;
                            }
                        }
                    }


                    //calculamos el sueldo liquido :D
                    SueldoLiquido = SuelDspsImpto + AbonosNoImponiblesNoTributables - Anticipos + AsignacionaPagar;
                    System.out.println("Sueldo Liquido: " + SueldoLiquido);


                    //Ahora que ya tenemos todo veamos como lo guardamos en el modelo :S
                    LiquidacionDeSueldoDAO lDAO = new LiquidacionDeSueldoDAO();
                    LiquidacionDeSueldo liquidacion = new LiquidacionDeSueldo();
                    DetalleLiquidacionDAO dDAO = new DetalleLiquidacionDAO();
                    InfoComplementariaDAO iDAO = new InfoComplementariaDAO();
                    InfoComplementaria infoComplementaria = new InfoComplementaria();


                    //Primero Ingresamos los datos de la liquidacion (datos de fecha)
                    liquidacion.setAnio(Integer.toString(numAnio)); //el a�o es un entero que sale de la operacion 1900 - fechaactual, por eso le sumo 1900
                    liquidacion.setFechaEmision(Fecha);
                    liquidacion.setFechaPago(Fecha);
                    liquidacion.setMes(Mes);
                    liquidacion.setRelacionLaboral(rel);
                    control.guardarLiquidacion(liquidacion); //agregamos a la BD

                    //como ya tenemos lista la liquidacion iniciamos con el detalle que esta contendra...
            /* variables a guardar:
			 * (+) SuedloBaseImponibleTrabajado: representa el sueldo fijo
			 * (+) HorasExtrasTrabajadas: Representa las horas extras del mes.
			 * (+) ValorGratificacion: gratificacion obligatoria 25%
			 * (+) AbonosImponibles: sumatoria de los abonos imponibles
			 * (+) AbonosImponiblesNoTributables: sumatoria abonos imp no tributables
			 * ..........................BaseImponible...........................
			 * (-) cotizarAFP: descuento AFP
			 * (-) cotizarSalud: fonasa o isapre
			 * (-) SeguroCesantia: seguro cesantia		
			 * 			(+) cargoTrabajadorAFC: seguro cesantia trabajador 2.4 %
			 * 			(+) cargoEmpleadorAFC: seguro cesantia empleador 0.6%
			 * (+) AbonosNoImponiblesTributables: abonos
			 * ..........................BaseTributable.........................
			 * (-) ImpuestoTributarioaPagar
			 * ..........................SuelDspsImpto..........................
			 * (+) AbonosNoImponiblesNoTributables
			 * (-) Anticipos
			 * ..........................SueldoLiquido..........................
			 */

                    //detalle sueldo base imponible
                    DetalleLiquidacion suelBase = new DetalleLiquidacion();
                    suelBase.setDescripcion("Sueldo Base (dias u horas*valor dia u hora)");
                    Double sbRedondeado = Redondear(SueldoBaseImponibleTrabajado, 2);
                    suelBase.setMonto(sbRedondeado);
                    suelBase.setPosicion(1);
                    suelBase.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(suelBase);//Agregamos a la BD
                    //agregamos informacion complementaria
                    InfoComplementaria infosuelBase = new InfoComplementaria();
                    if (Horas) {
                        Double fRedondeado = Redondear(valorhoranormal, 2);
                        infosuelBase.setFactor(fRedondeado);
                    } else {
                        Double fRedondeado = Redondear(valordia, 2);
                        infosuelBase.setFactor(fRedondeado);
                    }
                    infosuelBase.setBaseDeCalculo(Redondear(Double.parseDouble(Dias), 2));
                    infosuelBase.setDetalleLiquidacion(suelBase);
                    control.guardarInfoComple(infosuelBase); //agregamos a la BD

                    //detalle sueldo Horas Extras
                    DetalleLiquidacion horasExtras = new DetalleLiquidacion();
                    horasExtras.setDescripcion("Horas Extras (horas*valor hora)");
                    horasExtras.setMonto(Redondear(HorasExtrasTrabajadas, 2));
                    horasExtras.setPosicion(2);
                    horasExtras.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(horasExtras);//Agregamos a la BD
                    //agregamos informacion complementaria
                    InfoComplementaria infoHorasExtras = new InfoComplementaria();
                    infoHorasExtras.setFactor(Redondear(valorhoraextra, 2));
                    infoHorasExtras.setBaseDeCalculo(Redondear((Double.parseDouble(HorasExtras)), 2));
                    infoHorasExtras.setDetalleLiquidacion(horasExtras);
                    control.guardarInfoComple(infoHorasExtras); //agregamos a la BD

                    //detalle valorGratificacion
                    DetalleLiquidacion gratificacion = new DetalleLiquidacion();
                    gratificacion.setDescripcion("Gratificacion Obligatoria 25%");
                    gratificacion.setMonto(Redondear(ValorGratificacion, 2));
                    gratificacion.setPosicion(3);
                    gratificacion.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(gratificacion);
                    //agregamos info complementaria
                    InfoComplementaria infoGratificacion = new InfoComplementaria();
                    infoGratificacion.setFactor(0.25);
                    infoGratificacion.setBaseDeCalculo(Redondear((SueldoBaseImponibleTrabajado + HorasExtrasTrabajadas), 2));
                    infoGratificacion.setDetalleLiquidacion(gratificacion);
                    control.guardarInfoComple(infoGratificacion);

                    //detalle Abonos Imponibles
                    DetalleLiquidacion sumAbonos = new DetalleLiquidacion();
                    sumAbonos.setDescripcion("Sumatoria de Abonos Imponibles Tributables");
                    sumAbonos.setMonto(Redondear(AbonosImponibles, 2));
                    sumAbonos.setPosicion(4);
                    sumAbonos.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(sumAbonos);
                    //agregamos info complementaria
                    InfoComplementaria infoSumAbonos = new InfoComplementaria();
                    infoSumAbonos.setFactor(0.0);
                    infoSumAbonos.setBaseDeCalculo(Redondear(AbonosImponibles, 2));
                    infoSumAbonos.setDetalleLiquidacion(sumAbonos);
                    control.guardarInfoComple(infoSumAbonos);

                    //detalle AbonosImponibleNoTributables
                    DetalleLiquidacion sumAbonosNoTributables = new DetalleLiquidacion();
                    sumAbonosNoTributables.setDescripcion("Sumatoria de Abonos Imponibles No Tributables");
                    sumAbonosNoTributables.setMonto(Redondear(AbonosImponiblesNoTributables, 2));
                    sumAbonosNoTributables.setPosicion(5);
                    sumAbonosNoTributables.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(sumAbonosNoTributables);
                    //agregamos info complementaria
                    InfoComplementaria infoSumAbonosNoTributables = new InfoComplementaria();
                    infoSumAbonosNoTributables.setFactor(0.0);
                    infoSumAbonosNoTributables.setBaseDeCalculo(Redondear(AbonosImponiblesNoTributables, 2));
                    infoSumAbonosNoTributables.setDetalleLiquidacion(sumAbonosNoTributables);
                    control.guardarInfoComple(infoSumAbonosNoTributables);

                    //detalle BaseImponible
                    DetalleLiquidacion baseImponible = new DetalleLiquidacion();
                    baseImponible.setDescripcion("BASE IMPONIBLE");
                    baseImponible.setMonto(Redondear(BaseImponible, 2));
                    baseImponible.setPosicion(6);
                    baseImponible.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(baseImponible);
                    //agregamos info complementaria
                    InfoComplementaria infobaseImponible = new InfoComplementaria();
                    infobaseImponible.setFactor(0.0);
                    infobaseImponible.setBaseDeCalculo(Redondear(BaseImponible, 2));
                    infobaseImponible.setDetalleLiquidacion(baseImponible);
                    control.guardarInfoComple(infobaseImponible);

                    //detalle AFP
                    DetalleLiquidacion desctAFP = new DetalleLiquidacion();
                    desctAFP.setDescripcion(AFP.getNombre());
                    desctAFP.setMonto(Redondear(cotizarAFP, 2));
                    desctAFP.setPosicion(7);
                    desctAFP.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(desctAFP);
                    //agregamos info complementaria
                    InfoComplementaria infodesctAFP = new InfoComplementaria();
                    if (BaseImponible >= TopeImponible) { //si se cumple cotizamos por el tope imponible para la AFP
                        infodesctAFP.setFactor(AFP.getPorcentajeDescuento());
                        infodesctAFP.setBaseDeCalculo(Redondear(BaseImponible, 2));
                        infodesctAFP.setDetalleLiquidacion(desctAFP);
                    } else {
                        infodesctAFP.setFactor(AFP.getPorcentajeDescuento());
                        infodesctAFP.setBaseDeCalculo(Redondear(TopeImponible, 2));
                        infodesctAFP.setDetalleLiquidacion(desctAFP);
                    }
                    control.guardarInfoComple(infodesctAFP);

                    //detalle SALUD
                    DetalleLiquidacion dsctoSalud = new DetalleLiquidacion();
                    dsctoSalud.setDescripcion(rel.getInstitucionSalud().getNombre());
                    dsctoSalud.setMonto(Redondear(cotizarSalud, 2));
                    dsctoSalud.setPosicion(8);
                    dsctoSalud.setLiquidacionDeSueldo(liquidacion);
                    //agregamos info complementaria
                    InfoComplementaria infodsctoSalud = new InfoComplementaria();
                    control.guardarDetalleLiq(dsctoSalud);
                    if (rel.getInstitucionSalud().getId().equals(1)) { //para FONASA
                        infodsctoSalud.setFactor(rel.getInstitucionSalud().getPorcentajeDescuento());
                        infodsctoSalud.setBaseDeCalculo(Redondear(BaseImponible, 2));
                        infodsctoSalud.setDetalleLiquidacion(dsctoSalud);
                    } else { //para ISAPRE
                        Double valorPlanIsapre = rel.getValorPlanIsapre() * valores.get(1).getMonto();
                        if (valorPlanIsapre >= cotSalud) {//debo cotizar por el valor de la isapre sino por el 7%
                            infodsctoSalud.setFactor(Redondear((valores.get(1).getMonto()), 4));
                            infodsctoSalud.setBaseDeCalculo(Redondear((rel.getValorPlanIsapre()), 2));
                            infodsctoSalud.setDetalleLiquidacion(dsctoSalud);
                        } else {
                            infodsctoSalud.setFactor(0.07);
                            infodsctoSalud.setBaseDeCalculo(Redondear(BaseImponible, 2));
                            infodsctoSalud.setDetalleLiquidacion(dsctoSalud);
                        }
                    }
                    control.guardarInfoComple(infodsctoSalud);

                    //detalle seguroCesantia
                    DetalleLiquidacion dsctSeguroCesantia = new DetalleLiquidacion();
                    dsctSeguroCesantia.setDescripcion("Seguro de Cesantia");
                    dsctSeguroCesantia.setMonto(Redondear((cargoEmpleadorAFC + cargoTrabajadorAFC), 2));
                    dsctSeguroCesantia.setPosicion(9);
                    dsctSeguroCesantia.setLiquidacionDeSueldo(liquidacion);
                    //agregamos info complementaria
                    InfoComplementaria infodsctSeguroCesantia = new InfoComplementaria();
                    control.guardarDetalleLiq(dsctSeguroCesantia);
                    if (BaseImponible < TopeSeguroCesantia) {
                        if (rel.getTipoContrato().equals("indefinido")) {
                            infodsctSeguroCesantia.setFactor(Redondear(((((cesantia.get(0).getEmpleador()) / 100) + ((cesantia.get(0).getTrabajador()) / 100))), 4));
                            infodsctSeguroCesantia.setBaseDeCalculo(Redondear(BaseImponible, 2));
                            infodsctSeguroCesantia.setDetalleLiquidacion(dsctSeguroCesantia);
                        } else if (rel.getTipoContrato().equals("fijo")) {
                            infodsctSeguroCesantia.setFactor(Redondear(((cesantia.get(0).getEmpleador()) / 100), 4));
                            infodsctSeguroCesantia.setBaseDeCalculo(Redondear(BaseImponible, 2));
                            infodsctSeguroCesantia.setDetalleLiquidacion(dsctSeguroCesantia);
                        } else {
                            if (rel.getTipoContrato().equals("indefinido")) {
                                infodsctSeguroCesantia.setFactor(Redondear((((cesantia.get(0).getEmpleador()) / 100) + ((cesantia.get(0).getTrabajador()) / 100)), 4));
                                infodsctSeguroCesantia.setBaseDeCalculo(Redondear(TopeSeguroCesantia, 2));
                                infodsctSeguroCesantia.setDetalleLiquidacion(dsctSeguroCesantia);
                            } else if (rel.getTipoContrato().equals("fijo")) {
                                infodsctSeguroCesantia.setFactor(Redondear(((cesantia.get(0).getEmpleador()) / 100), 4));
                                infodsctSeguroCesantia.setBaseDeCalculo(Redondear(TopeSeguroCesantia, 2));
                                infodsctSeguroCesantia.setDetalleLiquidacion(dsctSeguroCesantia);
                            }
                        }
                    }
                    control.guardarInfoComple(infodsctSeguroCesantia);

                    //detalle Abonos no Imponible Tributables
                    DetalleLiquidacion subAbonNoImpTri = new DetalleLiquidacion();
                    subAbonNoImpTri.setDescripcion("Abonos No Imponibles Tributables");
                    subAbonNoImpTri.setMonto(Redondear(AbonosNoImponiblesTributables, 2));
                    subAbonNoImpTri.setPosicion(10);
                    subAbonNoImpTri.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(subAbonNoImpTri);
                    //agregamos info complementaria
                    InfoComplementaria infosubAbonNoImpTri = new InfoComplementaria();
                    infosubAbonNoImpTri.setFactor(0.0);
                    infosubAbonNoImpTri.setBaseDeCalculo(Redondear(AbonosNoImponiblesTributables, 2));
                    infosubAbonNoImpTri.setDetalleLiquidacion(subAbonNoImpTri);
                    control.guardarInfoComple(infosubAbonNoImpTri);

                    //detalle BaseTributable
                    DetalleLiquidacion baseTributable = new DetalleLiquidacion();
                    baseTributable.setDescripcion("BASE TRIBUTABLE");
                    baseTributable.setMonto(Redondear(BaseTributable, 2));
                    baseTributable.setPosicion(11);
                    baseTributable.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(baseTributable);
                    //agregamos info complementaria
                    InfoComplementaria infobaseTributable = new InfoComplementaria();
                    infobaseTributable.setFactor(0.0);
                    infobaseTributable.setBaseDeCalculo(Redondear(BaseTributable, 2));
                    infobaseTributable.setDetalleLiquidacion(baseTributable);
                    control.guardarInfoComple(infobaseTributable);

                    //detalle ImpuestoTributario a pagar
                    DetalleLiquidacion ImptoTributario = new DetalleLiquidacion();
                    ImptoTributario.setDescripcion("Impuesto Unico de Segunda Categoria");
                    ImptoTributario.setMonto(Redondear(ImpuestoTributarioaPagar, 2));
                    ImptoTributario.setPosicion(12);
                    ImptoTributario.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(ImptoTributario);
                    //agregamos info complementaria
                    InfoComplementaria infoImptoTributario = new InfoComplementaria();
                    infoImptoTributario.setFactor(0.0);
                    infoImptoTributario.setBaseDeCalculo(Redondear(ImpuestoTributarioaPagar, 2));
                    infoImptoTributario.setDetalleLiquidacion(ImptoTributario);
                    control.guardarInfoComple(infoImptoTributario);

                    //detalle AsignacionFamiliar
                    DetalleLiquidacion aFamiliar = new DetalleLiquidacion();
                    aFamiliar.setDescripcion("Asignacion Familiar");
                    aFamiliar.setMonto(Redondear(AsignacionaPagar, 2));
                    aFamiliar.setPosicion(13);
                    aFamiliar.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(aFamiliar);
                    //agregamos info complementaria
                    InfoComplementaria infoaFamiliar = new InfoComplementaria();
                    infoaFamiliar.setFactor(0.0);
                    infoaFamiliar.setBaseDeCalculo(Redondear(AsignacionaPagar, 2));
                    infoaFamiliar.setDetalleLiquidacion(aFamiliar);
                    control.guardarInfoComple(infoaFamiliar);

                    //detalle Sueldo despues de impuesto
                    DetalleLiquidacion suelDespuesImpto = new DetalleLiquidacion();
                    suelDespuesImpto.setDescripcion("SUELDO DESPUES DE IMPUESTO");
                    suelDespuesImpto.setMonto(Redondear(SuelDspsImpto, 2));
                    suelDespuesImpto.setPosicion(14);
                    suelDespuesImpto.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(suelDespuesImpto);
                    //agregamos info complementaria
                    InfoComplementaria infosuelDespuesImpto = new InfoComplementaria();
                    infosuelDespuesImpto.setFactor(0.0);
                    infosuelDespuesImpto.setBaseDeCalculo(Redondear(SuelDspsImpto, 2));
                    infosuelDespuesImpto.setDetalleLiquidacion(suelDespuesImpto);
                    control.guardarInfoComple(infosuelDespuesImpto);

                    //detalle AbonosNOImponibleNoTributables
                    DetalleLiquidacion sumAbonosNoImpNoTributables = new DetalleLiquidacion();
                    sumAbonosNoImpNoTributables.setDescripcion("Sumatoria de Abonos No Imponibles No Tributables");
                    sumAbonosNoImpNoTributables.setMonto(Redondear(AbonosNoImponiblesNoTributables, 2));
                    sumAbonosNoImpNoTributables.setPosicion(15);
                    sumAbonosNoImpNoTributables.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(sumAbonosNoImpNoTributables);
                    //agregamos info complementaria
                    InfoComplementaria infosumAbonosNoImpNoTributables = new InfoComplementaria();
                    infosumAbonosNoImpNoTributables.setFactor(0.0);
                    infosumAbonosNoImpNoTributables.setBaseDeCalculo(Redondear(AbonosNoImponiblesNoTributables, 2));
                    infosumAbonosNoImpNoTributables.setDetalleLiquidacion(sumAbonosNoImpNoTributables);
                    control.guardarInfoComple(infosumAbonosNoImpNoTributables);

                    //detalle Anticipos y Descuentos
                    DetalleLiquidacion sumAnticipos = new DetalleLiquidacion();
                    sumAnticipos.setDescripcion("Sumatoria de Anticipos u Otros Descuentos");
                    sumAnticipos.setMonto(Redondear(Anticipos, 2));
                    sumAnticipos.setPosicion(16);
                    sumAnticipos.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(sumAnticipos);
                    //agregamos info complementaria
                    InfoComplementaria infosumAnticipos = new InfoComplementaria();
                    infosumAnticipos.setFactor(0.0);
                    infosumAnticipos.setBaseDeCalculo(Redondear(Anticipos, 2));
                    infosumAnticipos.setDetalleLiquidacion(sumAnticipos);
                    control.guardarInfoComple(infosumAnticipos);

                    //detalle Sueldo Liquido a Pagar
                    DetalleLiquidacion suelLiquido = new DetalleLiquidacion();
                    suelLiquido.setDescripcion("SUELDO LIQUIDO A PAGAR");
                    suelLiquido.setMonto(Redondear(SueldoLiquido, 2));
                    suelLiquido.setPosicion(17);
                    suelLiquido.setLiquidacionDeSueldo(liquidacion);
                    control.guardarDetalleLiq(suelLiquido);
                    //agregamos info complementaria
                    InfoComplementaria infosuelLiquido = new InfoComplementaria();
                    infosuelLiquido.setFactor(0.0);
                    infosuelLiquido.setBaseDeCalculo(Redondear(SueldoLiquido, 2));
                    infosuelLiquido.setDetalleLiquidacion(suelLiquido);
                    control.guardarInfoComple(infosuelLiquido);

                    SegurodeCesantia = cargoEmpleadorAFC + cargoTrabajadorAFC;
                    addActionMessage("Se ha calculado el sueldo ahora puede obtener la liquidacion");
                    try {
                        double sueldoCero = Redondear(SueldoLiquido, 0);
                        int sueldito = (int) sueldoCero;
                        String sueldo_cero = Integer.toString(sueldito);
                        System.out.println("el sueldo entero:" + sueldito);
                        System.out.println("el sueldo en edouble:" + sueldoCero);
                        System.out.println("el sueldo string desde el entero:" + sueldo_cero);
                        String EnPalabras = NumLetrasJ.Convierte(sueldo_cero, NumLetrasJ.Tipo.Pronombre);
                        System.out.println(EnPalabras);
                        //detalle Sueldo Liquido a Pagar en palabras
                        DetalleLiquidacion sueldoenpalabras = new DetalleLiquidacion();
                        sueldoenpalabras.setDescripcion("Sueldo a Pagar en Palabras");
                        sueldoenpalabras.setMonto(0.0);
                        sueldoenpalabras.setPosicion(18);
                        sueldoenpalabras.setEnPalabras(EnPalabras);
                        sueldoenpalabras.setLiquidacionDeSueldo(liquidacion);
                        control.guardarDetalleLiq(sueldoenpalabras);
                        //agregamos info complementaria aunque no es necesario lo hago para que se mantenga la misma numeracion
                        InfoComplementaria infosueldoenpalabras = new InfoComplementaria();
                        infosueldoenpalabras.setFactor(0.0);
                        infosueldoenpalabras.setBaseDeCalculo(0.0);
                        infosueldoenpalabras.setDetalleLiquidacion(sueldoenpalabras);
                        control.guardarInfoComple(infosuelLiquido);

                    } catch (Exception e) {
                        addActionError("No sirve la funcion de los numeros");
                        e.getStackTrace();// TODO: handle exception
                    }


                }//else{
                //hacer funcion para recalcular el sueldo de los trabajadores que ya poseen liquidacion...
//				RelacionLaboral rel = todosEMP.get(j);
//				LiquidacionDeSueldoDAO liqDAO = new LiquidacionDeSueldoDAO();
//				LiquidacionDeSueldo li = liqDAO.findBYIDrelAnioMes(rel.getId(), numAnio, Mes);

                //}

            }
            String mes = TransformarMes(fecha.getMonth());
            Integer numAnio2 = fecha.getYear() + 1900;

            try {
                parametros = new HashMap<String, Object>();
                parametros.put("id_rel", Integer.toString(emp.getId()));
                parametros.put("Mes", mes);
                parametros.put("Anio", Integer.toString(numAnio2));



                String fullPath = System.getenv("remusystem_report");
                if(fullPath==null){
				      fullPath="/home/remusystem/remu_report/";
				}

                parametros.put("SUBREPORT_DIR", fullPath);
                System.out.println(fullPath);
				//Coneccion BD
                conexion = ReportConector.getConnection();
                addActionMessage("Se ha calculado el sueldo para todos sus trabajadores espere mientras se carga el reporte (path:" + fullPath + ")");
                return SUCCESS;
            } catch (Exception e) {
                addActionError("No se pudo obtener una conección a la base de datos");
                e.printStackTrace();
                return ERROR;
            }
        } else {
            addActionError("No posee trabajadores activos");
            return ERROR;
        }
    }

    //arreglo del mes de los anticipos y abonos
    //para calcular la fecha final segun el numero de cuotas
    public Date calcularFechaFinal(Date fecha) {
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, 1);
        String newdate = dateformat.format(cal.getTime());
        System.out.println(newdate);
        return (getDate(newdate));
    }

    //para transformar el string en fecha
    public Date getDate(String date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return df.parse(date);
        } catch (ParseException ex) {
        }
        return null;
    }

    public String TransformarMes(Integer nummes) {
        String mes = "";

        switch (nummes) {
            case 0:
                mes = "Enero";
                break;
            case 1:
                mes = "Febrero";
                break;
            case 2:
                mes = "Marzo";
                break;
            case 3:
                mes = "Abril";
                break;
            case 4:
                mes = "Mayo";
                break;
            case 5:
                mes = "Junio";
                break;
            case 6:
                mes = "Julio";
                break;
            case 7:
                mes = "Agosto";
                break;
            case 8:
                mes = "Septiembre";
                break;
            case 9:
                mes = "Octubre";
                break;
            case 10:
                mes = "Noviembre";
                break;
            case 11:
                mes = "Diciembre";
                break;
        }
        return mes;
    }

    public double Redondear(double nD, int nDec) {
        return Math.round(nD * Math.pow(10, nDec)) / Math.pow(10, nDec);
    }

    public Double calculagratificacion(Double sueldo) {
        Double gratificacion = sueldo * 0.25;
        Double tope = 193000 * 4.75;
        Double valorAnual = gratificacion * 12;
        if (valorAnual >= tope) {
            return tope / 12;
        } else {
            return gratificacion;
        }
    }

    public double calcularAsignacionFamiliar(double basetributable, Integer numCargas) {
        Double Asignacion = 0.0;
        AsignacionFamiliarDAO aDAO = new AsignacionFamiliarDAO();
        List<AsignacionFamiliar> asigFam = aDAO.findAll();
        for (int i = 0; i < asigFam.size(); i++) {
            if ((asigFam.get(i).getDesde() < basetributable) && (asigFam.get(i).getHasta() >= basetributable)) {
                Double cargas = Double.parseDouble(Integer.toString(numCargas));
                Asignacion = cargas * asigFam.get(i).getMonto();
            }
        }

        return Asignacion;
    }

    public double calcularImpuesto(double basetributable) { //calcula el impuesto unico de segunga categoria
        ImpuestoSegundaCategoriaDAO impuestoDAO = new ImpuestoSegundaCategoriaDAO();
        List<ImpuestoSegundaCategoria> rangos = impuestoDAO.findAll();
        ValoresDAO vDAO = new ValoresDAO(); //iniciamos variables
        List<Valores> valores = vDAO.findAll(); //iniciamos variables
        Double ImpuestoaPagar = 0.0;
        Double Impuestosinrebaja = 0.0;
        Double Rebaja = 0.0;
        for (int i = 0; i < rangos.size(); i++) {
            if ((((rangos.get(i).getDesde() * valores.get(2).getMonto()) < basetributable)) && ((rangos.get(i).getHasta() * valores.get(2).getMonto()) >= basetributable)) {
                System.out.println("Factor: " + rangos.get(i).getFactor());
                System.out.println("RebajaUTM: " + rangos.get(i).getRebaja());
                Impuestosinrebaja = basetributable * rangos.get(i).getFactor();
                Rebaja = rangos.get(i).getRebaja() * valores.get(2).getMonto();
                ImpuestoaPagar = Impuestosinrebaja - Rebaja;
            }
        }
        System.out.println("Valor UTM: " + valores.get(2).getMonto());
        System.out.println("Impuesto sin rebaja: " + Impuestosinrebaja);
        System.out.println("Rebaja: " + Rebaja);
        System.out.println("ImpuestoaPagar: " + ImpuestoaPagar);
        return ImpuestoaPagar;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getDias() {
        return Dias;
    }

    public void setDias(String dias) {
        Dias = dias;
    }

    public String getHorasExtras() {
        return HorasExtras;
    }

    public void setHorasExtras(String horasExtras) {
        HorasExtras = horasExtras;
    }

    public Boolean getHoras() {
        return Horas;
    }

    public void setHoras(Boolean horas) {
        Horas = horas;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Double getSueldoBaseImponibleTrabajado() {
        return SueldoBaseImponibleTrabajado;
    }

    public void setSueldoBaseImponibleTrabajado(Double sueldoBaseImponibleTrabajado) {
        SueldoBaseImponibleTrabajado = sueldoBaseImponibleTrabajado;
    }

    public Double getHorasExtrasTrabajadas() {
        return HorasExtrasTrabajadas;
    }

    public void setHorasExtrasTrabajadas(Double horasExtrasTrabajadas) {
        HorasExtrasTrabajadas = horasExtrasTrabajadas;
    }

    public Double getValorGratificacion() {
        return ValorGratificacion;
    }

    public void setValorGratificacion(Double valorGratificacion) {
        ValorGratificacion = valorGratificacion;
    }

    public Double getAbonosImponibles() {
        return AbonosImponibles;
    }

    public void setAbonosImponibles(Double abonosImponibles) {
        AbonosImponibles = abonosImponibles;
    }

    public Double getAbonosImponiblesNoTributables() {
        return AbonosImponiblesNoTributables;
    }

    public void setAbonosImponiblesNoTributables(
            Double abonosImponiblesNoTributables) {
        AbonosImponiblesNoTributables = abonosImponiblesNoTributables;
    }

    public Double getBaseImponible() {
        return BaseImponible;
    }

    public void setBaseImponible(Double baseImponible) {
        BaseImponible = baseImponible;
    }

    public Double getCotizarAFP() {
        return cotizarAFP;
    }

    public void setCotizarAFP(Double cotizarAFP) {
        this.cotizarAFP = cotizarAFP;
    }

    public Double getCotizarSalud() {
        return cotizarSalud;
    }

    public void setCotizarSalud(Double cotizarSalud) {
        this.cotizarSalud = cotizarSalud;
    }

    public Double getCargoTrabajadorAFC() {
        return cargoTrabajadorAFC;
    }

    public void setCargoTrabajadorAFC(Double cargoTrabajadorAFC) {
        this.cargoTrabajadorAFC = cargoTrabajadorAFC;
    }

    public Double getCargoEmpleadorAFC() {
        return cargoEmpleadorAFC;
    }

    public void setCargoEmpleadorAFC(Double cargoEmpleadorAFC) {
        this.cargoEmpleadorAFC = cargoEmpleadorAFC;
    }

    public Double getAbonosNoImponiblesTributables() {
        return AbonosNoImponiblesTributables;
    }

    public void setAbonosNoImponiblesTributables(
            Double abonosNoImponiblesTributables) {
        AbonosNoImponiblesTributables = abonosNoImponiblesTributables;
    }

    public Double getBaseTributable() {
        return BaseTributable;
    }

    public void setBaseTributable(Double baseTributable) {
        BaseTributable = baseTributable;
    }

    public Double getImpuestoTributarioaPagar() {
        return ImpuestoTributarioaPagar;
    }

    public void setImpuestoTributarioaPagar(Double impuestoTributarioaPagar) {
        ImpuestoTributarioaPagar = impuestoTributarioaPagar;
    }

    public Double getSuelDspsImpto() {
        return SuelDspsImpto;
    }

    public void setSuelDspsImpto(Double suelDspsImpto) {
        SuelDspsImpto = suelDspsImpto;
    }

    public Double getAbonosNoImponiblesNoTributables() {
        return AbonosNoImponiblesNoTributables;
    }

    public void setAbonosNoImponiblesNoTributables(
            Double abonosNoImponiblesNoTributables) {
        AbonosNoImponiblesNoTributables = abonosNoImponiblesNoTributables;
    }

    public Double getAnticipos() {
        return Anticipos;
    }

    public void setAnticipos(Double anticipos) {
        Anticipos = anticipos;
    }

    public Double getSueldoLiquido() {
        return SueldoLiquido;
    }

    public void setSueldoLiquido(Double sueldoLiquido) {
        SueldoLiquido = sueldoLiquido;
    }

    public Double getSegurodeCesantia() {
        return SegurodeCesantia;
    }

    public void setSegurodeCesantia(Double segurodeCesantia) {
        SegurodeCesantia = segurodeCesantia;
    }

    public Double getAsignacionaPagar() {
        return AsignacionaPagar;
    }

    public void setAsignacionaPagar(Double asignacionaPagar) {
        AsignacionaPagar = asignacionaPagar;
    }

    public List<RelacionLaboral> getTodosEMP() {
        return todosEMP;
    }

    public void setTodosEMP(List<RelacionLaboral> todosEMP) {
        this.todosEMP = todosEMP;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
