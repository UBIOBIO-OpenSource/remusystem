package org.remusystem.control;

import java.util.List;

import org.remusystem.persistencia.*;
//import persistencia.*;

public class Controlador {
	private static Controlador instance = null;
	
	private Controlador(){
		
	}
	
	public static Controlador getInstance(){
		if(instance==null){
			instance = new Controlador();
		}
		return instance;
	}
	
	
	//BUSCA SI EXISTE EL USUARIO SEGUN EL RUT, Y VERIFICA SU PASSWORD
		public Usuario validaUsuario(String rut, String pass) {
			UsuarioDAO uDAO = new UsuarioDAO();
			return uDAO.findByUserAndPass(rut, pass);
		}
		
	//AGREGA UN NUEVO USUARIO A LA BASE DE DATOS
		public void AgregaUsuario(Usuario usuario){
			UsuarioDAO eDAO = new UsuarioDAO();
			eDAO.save(usuario);
		}
	
	//BUSCA SI ALGUNA EMPRESA CON TAL RUT YA SE ENCUENTRA REGISTRADA
		public Empresa buscaEmpresa(String rutEmpresa){
			EmpresaDAO uDAO = new EmpresaDAO();
			return uDAO.findByRUT(rutEmpresa);
		}
		
	//BUSCA LA EMPRESA SEGUN SU ID
		public Empresa buscaEmpresaID(Integer id_emp){
			EmpresaDAO uDAO = new EmpresaDAO();
			return uDAO.findById(id_emp);
		}
		
	//AGREGA UNA NUEVA EMPRESA A LA BASE DE DATOS
		public void AgregaEmpresa(Empresa empresa){
			EmpresaDAO eDAO = new EmpresaDAO();
			eDAO.save(empresa);
		}
	
	//AGREGA UN NUEVO DESCUENTO
		public void AgregaDescuento(OtrosDescuentos descuento){
			OtrosDescuentosDAO oDAO = new OtrosDescuentosDAO();
			oDAO.save(descuento);
		}
	
	//AGREGA EL REGISTRO DEL DESCUENTO
		public void AgregaSolicitud(Solicitud solicitud){
			SolicitudDAO sDAO = new SolicitudDAO();
			sDAO.save(solicitud);
		}
		
	//AGREGA UNA NUEVA RELACION LABORAL A LA BASE DE DATOS
		public void AgregaRelacionLaboral(RelacionLaboral relacionLaboral){
			RelacionLaboralDAO cDAO = new RelacionLaboralDAO();
			cDAO.save(relacionLaboral);
		}
		
	//AGREGA UN NUEVO TRABAJADOR A LA BASE DE DATOS
		public void AgregaTrabajador(Trabajador trabajador){
			TrabajadorDAO cDAO = new TrabajadorDAO();
			cDAO.save(trabajador);
		}
		
	//BUSCA EL TRABAJADOR SEGUN SU ID
		public Trabajador buscaTrabajadorID(Integer id_tra){
			TrabajadorDAO uDAO = new TrabajadorDAO();
			return uDAO.findById(id_tra);
		}
	
	//BUSCA SI ALGUNA EMPRESA CON TAL RUT YA SE ENCUENTRA REGISTRADA
		public Trabajador buscaTrabajador(String rutTrabajador){
			TrabajadorDAO uDAO = new TrabajadorDAO();
			return uDAO.findByRUT(rutTrabajador);
		}
		
	//BUSCA UNA MUTUAL CON EL ID QUE SE LE ENVIE
		public Mutual buscaMutual(Integer id_mutual){
			MutualDAO mDAO = new MutualDAO();
			return mDAO.findById(id_mutual);
		}
		
	//BUSCA UNA CAJA DE COMPENSACION CON EL ID
		public CajaCompensacion buscaCaja(Integer id_caja){
			CajaCompensacionDAO cDAO = new CajaCompensacionDAO();
			return cDAO.findById(id_caja);
		}
		
	//BUSCA UNA INST PREVISION CON EL ID
		public InstitucionPrevision buscaPrevision(Integer id_prev){
			InstitucionPrevisionDAO cDAO = new InstitucionPrevisionDAO();
			return cDAO.findById(id_prev);
		}
		
	//Lista todas las AFP para el GRID
		public List<InstitucionPrevision> findAllAFP() {
			InstitucionPrevisionDAO iDAO = new InstitucionPrevisionDAO();
			return iDAO.findAllGRID();
		}
		
	//Agrega una nueva Institucion de prevision
		public void AgregaAFP(InstitucionPrevision AFP){
			InstitucionPrevisionDAO iDAO = new InstitucionPrevisionDAO();
			iDAO.save(AFP);
		}
		
	//Actualiza una institucion de prevision
		public void updateAFP(InstitucionPrevision AFP){
			InstitucionPrevisionDAO iDAO = new InstitucionPrevisionDAO();
			iDAO.update(AFP);
		}
	
		
	//BUSCA UNA INST DE SALUD CON EL ID
		public InstitucionSalud buscaSalud(Integer id_sal){
			InstitucionSaludDAO cDAO = new InstitucionSaludDAO();
			return cDAO.findById(id_sal);
		}
		
	//BUSCA EL CARGO SEGUN EL ID
		public GrupoHabDesc buscaCargo(Integer id_cargo){
			GrupoHabDescDAO cDAO = new GrupoHabDescDAO();
			return cDAO.findById(id_cargo);
		}
		
	//BUSCA RELACION LABORAL SEGUN ID DEL TRABAJADOR
		public RelacionLaboral buscaRelacionconTra(Integer id_tra){
			RelacionLaboralDAO cDAO = new RelacionLaboralDAO();
			return cDAO.findByIDtra(id_tra);
		}
		
	//BUSCA ANTICIPOS SEGUN ID DEL TRABAJADOR
		public List<Solicitud> buscaSolicitudconIDrel(Integer id_rel){
			SolicitudDAO cDAO = new SolicitudDAO();
			return cDAO.findByIDrel(id_rel);
			}
		
	//Actualiza un trabajador
		public void updateTrabajador(Trabajador trab) {
			TrabajadorDAO tDAO = new TrabajadorDAO();
			tDAO.update(trab);
			
		}

		public void updateRelacion(RelacionLaboral rel) {
			RelacionLaboralDAO rDAO = new RelacionLaboralDAO();
			rDAO.update(rel);
			
		}

		public void updateUser(Usuario user) {
			UsuarioDAO uDAO = new UsuarioDAO();
			uDAO.update(user);
			
		}

		public void UpdateEmpresa(Empresa emp) {
			EmpresaDAO eDAO = new EmpresaDAO();
			eDAO.update(emp);
			
		}

		//Lista a los trabajadores que mantienen relacion laboral activa en una empresa.
		public List<Trabajadorrelacionlaboral> findByIDemp(Integer id) {
			TrabajadorrelacionlaboralDAO tDAO = new TrabajadorrelacionlaboralDAO();
			return tDAO.findAllIdEmp(id);
		}

		//Lista las relacion laboral activa en una empresa.
		public List<RelacionLaboral> RelacionIdEmpresa(Integer id) {
			RelacionLaboralDAO rDAO = new RelacionLaboralDAO();
			return rDAO.findByIdEmpresaEstado(id);
		}

		//Lista la tabla asignacion Familiar para el jGrid
		public List<AsignacionFamiliar> findAllAsignacionFamiliar() {
			AsignacionFamiliarDAO aDAO = new AsignacionFamiliarDAO();
			return aDAO.findAllGRID();
		}
		
		//Agrega una asignacion Familiar
		public void AgregaAsignacionFamiliar(AsignacionFamiliar asignacion) {
			AsignacionFamiliarDAO aDAO = new AsignacionFamiliarDAO();
			aDAO.save(asignacion);
		}

		//Actualiza la Asignacion Familiar
		public void updateAsignacionFamiliar(AsignacionFamiliar asignacion) {
			AsignacionFamiliarDAO aDAO = new AsignacionFamiliarDAO();
			aDAO.update(asignacion);
			
		}
		
		//Lista el Impuesto Unico de Segunda Categoria para el GRID
		public List<ImpuestoSegundaCategoria> findAllImpuestoUnico() {
			ImpuestoSegundaCategoriaDAO iDAO = new ImpuestoSegundaCategoriaDAO();
			return iDAO.findAllGRID();
			
		}

		//Agrega Un nuevo tramo de impuesto
		public void AgregaImpuestoUnico(ImpuestoSegundaCategoria impuesto) {
			ImpuestoSegundaCategoriaDAO iDAO = new ImpuestoSegundaCategoriaDAO();
			iDAO.save(impuesto);
		}
		
		//Actualiza el impuesto unico
		public void updateImpuestoUnico(ImpuestoSegundaCategoria impuesto){
			ImpuestoSegundaCategoriaDAO iDAO = new ImpuestoSegundaCategoriaDAO();
			iDAO.update(impuesto);
		}

		
		//Lista todos los Topes
		public List<Topes> findAllTopes(){
			TopesDAO tDAO = new TopesDAO();
			return tDAO.findAllGRID();
		}
		
		//agrega Tope
		public void AgregaTope(Topes tope){
			TopesDAO tDAO = new TopesDAO();
			tDAO.save(tope);
		}
		
		//edita TOPE
		public void updateTope(Topes tope){
			TopesDAO tDAO = new TopesDAO();
			tDAO.update(tope);
		}
		
		//Lista todos los valores
		public List<Valores> findAllValores(){
			ValoresDAO vDAO = new ValoresDAO();
			return vDAO.findAllGRID();
		}
		
		//Agrega Valores
		public void AgregaValor(Valores valor) {
			ValoresDAO vDAO = new ValoresDAO();
			vDAO.save(valor);
			
		}
		
		//edita Valores
		public void updateValor(Valores valor) {
			ValoresDAO vDAO = new ValoresDAO();
			vDAO.update(valor);
			
		}

		//Modifica los Anticipos o Descuentos
		public void updateAnticipo(OtrosDescuentos anticipo) {
			OtrosDescuentosDAO oDAO = new OtrosDescuentosDAO();
			oDAO.update(anticipo);
			
		}

		//Elimina un Anticipo o Descuento
		public void deleteAnticipo(OtrosDescuentos anticipo) {
			OtrosDescuentosDAO oDAO = new OtrosDescuentosDAO();
			oDAO.delete(anticipo);
			
		}

		//Elimina la Solicitud del anticipo anterior
		public void deleteSolicitud(Solicitud sol) {
			SolicitudDAO sDAO = new SolicitudDAO();
			sDAO.delete(sol);
			
		}

		//Agrega un nuevo Abono
		public void AgregaAbono(Abonos abono) {
			AbonosDAO aDAO = new AbonosDAO();
			aDAO.save(abono);
			
		}
		
		//Agrega una nueva solicitud de abono
		public void AgregaSolicitudAbono(SolicitudAbono solicitud){
			SolicitudAbonoDAO sDAO = new SolicitudAbonoDAO();
			sDAO.save(solicitud);
		}
		
		//Actualiza un Abono
		public void updateAbono(Abonos abono){
			AbonosDAO aDAO = new AbonosDAO();
			aDAO.update(abono);
		}
		
		//Elimina un Abono
		public void deleteAbono(Abonos abono){
			AbonosDAO aDAO = new AbonosDAO();
			aDAO.delete(abono);
		}
		
		//Elimina una solicitud de abono
		public void deleteSolicitudAbono(SolicitudAbono solicitud){
			SolicitudAbonoDAO sDAO = new SolicitudAbonoDAO();
			sDAO.delete(solicitud);
		}
		
		
		//--------------------------------- Creamos la Liquidacion -----------------------------
		
		//Agrega Liquidacion de sueldo
		public void guardarLiquidacion(LiquidacionDeSueldo liq){
			LiquidacionDeSueldoDAO lDAO = new LiquidacionDeSueldoDAO();
			lDAO.save(liq);
		}
		
		//Edita Liquidacion de sueldo
		public void actualizarLiquidacion(LiquidacionDeSueldo liq){
			LiquidacionDeSueldoDAO lDAO = new LiquidacionDeSueldoDAO();
			lDAO.update(liq);
		}
		
		//agrega detalle
		public void guardarDetalleLiq(DetalleLiquidacion det){
			DetalleLiquidacionDAO dDAO = new DetalleLiquidacionDAO();
			dDAO.save(det);
		}
		
		//actualiza detalle
		public void actualizaDetalleLiq(DetalleLiquidacion det){
			DetalleLiquidacionDAO dDAO = new DetalleLiquidacionDAO();
			dDAO.update(det);
		}
		
		//agrega infocomplementaria
		public void guardarInfoComple(InfoComplementaria info){
			InfoComplementariaDAO iDAO = new InfoComplementariaDAO();
			iDAO.save(info);
		}
		
		//actualiza infocomplementaria
		public void actualizarInfoComple(InfoComplementaria info){
			InfoComplementariaDAO iDAO = new InfoComplementariaDAO();
			iDAO.update(info);
		}

	

		
		
		


}
