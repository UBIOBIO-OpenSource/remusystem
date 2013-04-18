<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ page import="persistencia.Trabajador" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Resumen de Liquidación</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css"></link>
	-->
  
  
  
  <script type="text/javascript" src="js/jquery-1.7.2.js"></script> <!--  script para habilitar el jquery -->
  <sj:head jqueryui="true" jquerytheme="redmond" locale="es"/><!--  script para habilitar el jquery con tema -->
  <script type="text/javascript" src="js/validaRutBuscarTra.js"></script> <!-- Validador del rut -->
  <script type="text/javascript" src="js/validaDescuentos.js"></script>
  <script type="text/javascript" src="js/validaRecalcular.js"></script>
  
  	<%--CSS Files--%>
	<link href="css/jquery-ui-1.8.7.css" rel="stylesheet" type="text/css" />
	<link href="css/ui.jqgrid.css" rel="stylesheet" type="text/css" />
	<link href="css/ui.multiselect.css" rel="stylesheet" type="text/css" />
 
	<%--jQuery Library--%>
	<script src="js/jquery-1.4.4.min.js" type="text/javascript"></script>
 
	<%--Must load language tag BEFORE script tag--%>
	<script src="js/grid.locale-en.js" type="text/javascript"></script>
	<script src="js/jquery.jqGrid.min.js" type="text/javascript"></script>
	
  <!-- PARA EL CSS SmartBizz-->
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<!--<script type="text/javascript" src="js/jquery-1.4.2.js" ></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_400.font.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_700.font.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_600.font.js"></script>-->
<!--[if lt IE 9]>
	<script type="text/javascript" src="http://info.template-help.com/files/ie6_warning/ie6_script_other.js"></script>
	<script type="text/javascript" src="js/html5.js"></script>
<![endif]-->
<link rel="shortcut icon" href="images/2_icono_remuneraciones_viaticos.png"/>
<script type="text/javascript" src="http://www.bci.cl/common/include/valores.js"></script>
<script type="text/javascript" src="js/fechaHoy.js"></script>
  </head>
  
  <%
  				if(session.getAttribute("tipo") != "empresa"){
  						
   %>
   <meta http-equiv="Refresh"  content="0;URL= actions/Bienvenido" />
   <% } %>
  
   
 <body id="page4">
<div class="main">
<!-- header -->
	<header>
		<section id="content">
		<div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""><h5 align="center"><script language="javascript">document.write("" + textosemana[diasemana] + ", " + diames + " de " + textomes[mes] + " de " + ano + "<br>");</script>UTM: <script type="text/javascript">if(typeof(arrValores) != "undefined")if(typeof(arrValores[6])=="object")document.write(formatear_numero(arrValores[5].valor2));</script> | UF: <script type="text/javascript">if(typeof(arrValores) != "undefined")if(typeof(arrValores[4])=="object")document.write(formatear_numero(arrValores[4].valor2));</script> | Dolar Obs.: <script type="text/javascript">if(typeof(arrValores) != "undefined")if(typeof(arrValores[6])=="object")document.write(formatear_numero(arrValores[55].valor2));</script></h5></article>
				</div>
			</div>
			<div class="box pad_bot1 bot">
				<div class="pad marg_top">
				<a href="#" id="logo"> </a>
			<div id="Datossesion">
  				<table>
  					<tr>
  						<td><p class="welcome">BIENVENIDO, </p></td><td><p class="nombre_session"> <s:property value="#session.nombre" /></p></td>
  					</tr>
<!--   					<tr>-->
<!--  						<td>Rut:</td><td><s:property value="#session.rut" /></td>-->
<!--  					</tr>-->
   					<tr>
  						<td align="right"></td><td align="right"><s:a title="Modificar Mis Datos" cssClass="marker" name="ModificarDatos" value="ModificarDatos"></s:a><s:a title="Cerrar Sesión" cssClass="marker_cerrar" name="Cerrarsession" value="CerrarSession"></s:a></td>
  					</tr>
  				</table>
  			</div>
  		</div>
		</div>
		</div>
		</div>
		<div class="main">
		<!-- <nav> -->
		<ul id="menu">
				<li class="alpha" id="menu_active"><s:a name="GestionarTra" value="loadGestionar"><span><span>Ficha Empleado</span></span></span></s:a></li>
				<li><s:a name="IngresarTra" value="loadIngEmpleado"><span><span>Nuevo Trabajador</span></span></s:a></li>
    			<li><s:a name="Administrar" value="loadAdministrar"><span><span>Abo. y Desc.</span></span></s:a></li>
    			<li class="omega"><s:a name="VerList" value="loadVerListado"><span><span>Listar</span></span></s:a></li>
			</ul>
		<!-- </nav> -->
		</header>
<!-- / header -->
<!-- content -->
	<section id="content">
		<div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""><h3>Resumen de la Liquidacion de Sueldo</h3></article>
					</div>
			</div>
			<div class="box pad_bot1 bot">
				<div class="pad marg_top">
					<article class="">
   <!-- //como ya tenemos lista la liquidacion iniciamos con el detalle que esta contendra...
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
		 */  --> 
    <div id="Liquidacion_sueldo">
    <s:actionmessage cssClass="ui-state-highlight" cssStyle="font-size-small"/>
    <table width="940" border="1">
  <tr>
    <td rowspan="2"><table align="left" width="460" border="1">
      <tr>
        <td class="texto_titulo" colspan="2" align="left">HABERES IMPONIBLES</td>
        </tr>
      <tr>
        <td class="texto">+ Sueldo Base</td>
        <td class="cifra"><s:property  value="SueldoBaseImponibleTrabajado"/></td>
      </tr>
      <tr>
        <td class="texto">+ Horas Extras</td>
        <td class="cifra"><s:property value="HorasExtrasTrabajadas" /></td>
      </tr>
      <tr>
        <td class="texto">+ Gratificación</td>
        <td class="cifra"><s:property value="ValorGratificacion"/></td>
      </tr>
      <tr>
        <td class="texto">+ Abonos Imponibles Tributables</td>
        <td class="cifra"><s:property value="AbonosImponibles"/></td>
      </tr>
      <tr>
        <td class="texto">+ Abonos Imponibles No Tributables</td>
        <td class="cifra"><s:property value="AbonosImponiblesNoTributables"/></td>
      </tr>
      <tr>
        <td class="texto_subtitulo" align="center">BASE IMPONIBLE</td>
        <td class="cifra_total"><s:property value="BaseImponible"/></td>
      </tr>
    </table></td>
    <td><table width="460" border="1" align="left">
      <tr>
        <td  class="texto_titulo" colspan="2" align="left">DESCUENTOS PREVISIONALES</td>
        </tr>
      <tr>
        <td class="texto">- AFP</td>
        <td class="cifra"><s:property value="cotizarAFP"/></td>
      </tr>
      <tr>
        <td class="texto">- Salud</td>
        <td class="texto"><s:property value="cotizarSalud"/></td>
      </tr>
      <tr>
        <td class="texto">- Seguro de Cesantía</td>
        <td class="cifra"><s:property value="SegurodeCesantia"/></td>
      </tr>
      <tr>
        <td class="texto_subtitulo" align="center">TOTAL</td>
        <td class="cifra_total"><s:property value="Total_desc_prev"/></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="460" border="1">
      <tr>
        <td  class="texto_titulo" colspan="2" align="left">OTROS HABERES</td>
        </tr>
      <tr>
        <td class="texto">+ Asignacion Familiar</td>
        <td class="cifra"><s:property value="AsignacionaPagar"/></td>
      </tr>
      <tr>
        <td class="texto">+ Abonos No Imp. No Trib.</td>
        <td class="cifra"><s:property value="AbonosNoImponiblesNoTributables"/></td>
      </tr>
      <tr>
        <td class="texto_subtitulo" align="center">TOTAL</td>
        <td class="cifra_total"><s:property value="Total_otros_haberes"/></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="460" border="1">
      <tr>
        <td class="texto">+ Abonos No Imponibles Tributables</td>
        <td class="cifra"><s:property value="AbonosNoImponiblesTributables"/></td>
      </tr>
      <tr>
        <td class="texto_subtitulo" align="center">BASE TRIBUTABLE</td>
        <td class="cifra_total"><s:property value="BaseTributable"/></td>
      </tr>
      <tr>
        <td class="texto">- Impuesto a Pagar</td>
        <td class="cifra"><s:property value="ImpuestoTributarioaPagar"/></td>
      </tr>
      <tr>
        <td class="texto_subtitulo" align="center">SUELDO DESPUES DE IMPUESTO</td>
        <td class="cifra_total"><s:property value="SuelDspsImpto"/></td>
      </tr>
    </table></td>
    <td><table width="460" border="1">
      <tr>
        <td class="texto">- Anticipos u Otros Dscts.</td>
        <td class="cifra"><s:property value="Anticipos"/></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td class="texto_sueldo" align="right">SUELDO LIQUIDO A PAGAR</td>
    <td class="cifra_sueldo" align="left"><s:property value="SueldoLiquido"/></td>
  </tr>
</table>
</div>
    <s:a id="button_solo" name="GenerarReporteLiquidacionPDF" value="Struts2obtenerLiquidacionPDF"><span>Obtener en PDF</span></s:a>
    <s:a id="button_solo" name="GenerarReporteLiquidacionExcel" value="Struts2obtenerLiquidacionXLS"><span>Obtener en Excel</span></s:a>
     </div>
    </div>
    </div>
    
    
     <div>
  
		<div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""><h3>Ingrese Datos para Recalcular Sueldo</h3></article>
					</div>
			</div>
			<div class="box pad_bot1">
				<div class="pad marg_top">
					<article class="">
    <s:actionerror cssClass="ui-state-error" cssStyle="font-size-small"/>
    <s:form id="reCalcularSueldo" name="reCalcularSueldo" action="recalcularsueldoEMP" title="Ingresar Datos Para Recalcular Sueldo del Mes" onsubmit="return validaRecalcularLiq(this);">
    <s:textfield cssClass="bg" id="Dias" name="Dias" label="Dias Trabajados" onchange="javascript: Sue(this.value);"/>
    <s:checkbox id="Horas" name="Horas" label="Marque si indica solo Horas Trabajadas"/>
    <s:textfield cssClass="bg" id="HorasExtras" name="HorasExtras" label="Horas Extras" onchange="javascript: Sue(this.value);"/>
    <s:select cssClass="bg" id="MesCalcular" name="MesCalcular" label="Mes a Recalcular" headerKey="-1" headerValue="Seleccione el Mes" list="#{'Enero':'Enero','Febrero':'Febrero','Marzo':'Marzo','Abril':'Abril','Mayo':'Mayo','Junio':'Junio','Julio':'Julio','Agosto':'Agosto','Septiembre':'Septiembre','Octubre':'Octubre','Noviembre':'Noviembre','Diciembre':'Diciembre'}"/>
    <s:select cssClass="bg" id="AnioCalcular" name="AnioCalcular" label="Año a Recalcular" headerKey="-1" headerValue="Seleccione el Año" list="#{'2012':'2012','2013':'2013','2014':'2014','2015':'2015'}"/>

    <s:submit cssClass="button" value="Recalcular" />
    </s:form>
	</div>
	</div>
	</div>
	</section>
  </div>
</section>
<!-- content -->
<!-- footer -->	
	<footer>
	Software desarrollado por <a href="http://cl.linkedin.com/pub/carlos-sebastián-cáceres-lópez/20/735/576/">Carlos Cáceres López</a><br>
	como requisito parcial para la obtención del título de Ingeniero Civil Informático por la Universidad del Bíobio<br>
	Software distribuido bajo la licencia <a href="http://www.apache.org/licenses/LICENSE-2.0.html">Apache License 2.0</a> <br>
	 
	
	</footer>
		
	
<!-- / footer -->
</div>
<div align="center">
<a href="http://www.ubiobio.cl" id="Footer_logo_universidad"></a>
<a href="#" id="Footer_logo"> </a>
</div>


</body>
</html>