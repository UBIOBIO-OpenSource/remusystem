<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ page import="org.remusystem.persistencia.Trabajador" %>
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
   <script type="text/javascript" src="js/fechaHoy.js"></script>
    <script type="text/javascript" src="http://www.bci.cl/common/include/valores.js"></script>
  </head>
  
  <%
  					if(session.getAttribute("tipo") != "trabajador"){
  						
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
  						<td><p class="welcome">BIENVENIDO, </p></td><td><p class="nombre_session"> <s:property  value="#session.nombre" /> <s:property value="#session.Trab_PRO.getApellidoPaterno()"/></p></td>
  					</tr>
<!--   					<tr>-->
<!--  						<td>Rut:</td><td><s:property value="#session.rut" /></td>-->
<!--  					</tr>-->
   					<tr>
  						<td align="right"></td><td align="right"><s:a cssClass="marker" name="CambiarContrasena" value="CambiarContrasena"></s:a><s:a cssClass="marker_cerrar" name="Cerrarsession" value="CerrarSession"></s:a></td>
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
  		    <li class="alpha"><s:a name="VerFicha" value="verFichaTra"><span><span>Ver Mis Datos</span></span></s:a></li>
    		<li class="omega" id="menu_active"><s:a value="loadBuscarLiquidacion"><span><span>Ver Liquidación</span></span></s:a></li>
					</ul>
		<!-- </nav> -->
		</header>
<!-- / header -->
<!-- content -->
	<section id="content">
	
		<div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""><h3>Buscar Liquidación de Sueldo</h3></article>
					</div>
			</div>
			<div class="box pad_bot1">
				<div class="pad marg_top">
					<article class="">
					<s:actionerror cssClass="ui-state-error" cssStyle="font-size-small"/>
					<s:actionmessage cssClass="ui-state-highlight" cssStyle="font-size-small"/>
    				<s:form id="verliquidacion" name="verliquidacion" action="MostrarLiquidacionTrabajador">
    				<s:select cssClass="bg" label="Mes" id="Mes" name="Mes" headerKey="-1" headerValue="Seleccione el Mes" list="#{'Enero':'Enero','Febrero':'Febrero','Marzo':'Marzo','Abril':'Abril','Mayo':'Mayo','Junio':'Junio','Julio':'Julio','Agosto':'Agosto','Septiembre':'Septiembre','Octubre':'Octubre','Noviembre':'Noviembre','Diciembre':'Diciembre'}"/>
    				<s:select cssClass="bg" label="Anio" id="Anio" name="Anio" headerKey="-1" headerValue="Seleccione el Año" list="#{'2012':'2012','2013':'2013','2014':'2014','2015':'2015'}"/>
    				<s:submit cssClass="button" value="Buscar" />
    				</s:form>
				</div>
			</div>
		</div>
	

		    
    <div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""><h3>Detalles de los Abonos y Descuentos Asociados</h3></article>
					</div>
			</div>
			<div class="box pad_bot1">
				<div class="pad marg_top">
					<article class="">
					
<s:url id="remoteurlAnticipos" action="buscarAnticipos2"/>
<s:url id="remoteurlAbonos" action="buscarAbonos2"/>
    <sj:tabbedpanel id="localtabs" cssStyle="font-size-small">
      <sj:tab id="tab1" target="tone" label="Descuentos" cssStyle="font-size-small"/>
      <sj:tab id="tab2" target="ttwo" label="Abonos" cssErrorStyle="font-size-small"/>
      <sj:div id="tone">
		<sjg:grid
      		id="gridAnticipos"
	        caption="Anticipos u Otros Descuentos"
	        dataType="json"
	        href="%{remoteurlAnticipos}"
	        pager="true"
	        gridModel="gridModel"
	        rowList="10,15,20"
	        rowNum="10"
	        rownumbers="true"
	        multiselect="false"
	        width="800"
	        cssStyle="font-size:small"
	        navigator="true"
	    	editurl="%{editUrlAnticipos}"
			editinline="false"
	    	viewrecords="true"
	    	sortable="true"
	        
	        onSelectRowTopics="rowselect"
	        navigatorAdd="false"
	        navigatorSearch="false" 
		    navigatorRefresh="true"
		    navigatorDelete="false"
		    navigatorEdit="false" 
		    navigatorView="true" 
		    navigatorViewOptions="{width:400}"
		    navigatorEditOptions="{width:400,reloadAfterSubmit:true,closeAfterEdit:true}" 
		    
	    	>
      			<sjg:gridColumn name="id"
      							index="id" 
      							title="id" 
      							key="true" 
      							formatter="integer" 
      							sortable="false" 
      							editable="false" 
      							hidden="true"/>
      							
      			<sjg:gridColumn name="nombre" index="nombre" title="Descripcion"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}"   
		    		
		    		
		    		/>
		    			
		    	<sjg:gridColumn name="monto" index="monto" title="Monto Mensual"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		
		    		/>
		    	
		    		
		    	<sjg:gridColumn name="numerCuotas" index="numerCuotas" title="Numero de Cuotas"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		
		    		/>
		    		
		    	
		    	<sjg:gridColumn name="fechaInicio" index="fechaInicio" title="Fecha Inicio"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		formatter="date"
		    		
		    			    		
		    		/>
		    		
		    	<sjg:gridColumn name="fechaFinal" index="fechaFinal" title="Fecha Final"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		formatter="date"
		    		
		    		
		    		/>
		    		
		    	
		    </sjg:grid></sj:div>
		    <sj:div id="ttwo">
		    <sjg:grid
      		id="gridAbonos"
	        caption="Abonos"
	        dataType="json"
	        href="%{remoteurlAbonos}"
	        pager="true"
	        gridModel="gridModel"
	        rowList="10,15,20"
	        rowNum="10"
	        rownumbers="true"
	        multiselect="false"
	        width="800"
	        cssStyle="font-size:small"
	        navigator="true"
	    	editurl="%{editUrlAbonos}"
			editinline="false"
	    	viewrecords="true"
	    	sortable="true"
	        
	        onSelectRowTopics="rowselect"
	        navigatorAdd="false"
	        navigatorSearch="false" 
		    navigatorRefresh="true"
		    navigatorDelete="false"
		    navigatorEdit="false" 
		    navigatorView="true" 
		    navigatorViewOptions="{width:400}"
		    navigatorEditOptions="{width:400,reloadAfterSubmit:true,closeAfterEdit:true}" 
		    
	    	>
      			<sjg:gridColumn name="id"
      							index="id" 
      							title="id" 
      							key="true" 
      							formatter="integer" 
      							sortable="false" 
      							editable="false" 
      							hidden="true"/>
      							
      			<sjg:gridColumn name="nombre" index="nombre" title="Descripcion"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}"   
		    		
		    		
		    		/>
		    	
		    	<sjg:gridColumn name="monto" index="monto" title="Monto Mensual"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		
		    		/>
		    	
		    	
		    		
		    	<sjg:gridColumn name="numeroCuotas" index="numeroCuotas" title="Numero de Cuotas"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		
		    		/>
		    	<sjg:gridColumn name="fechaInicio" index="fechaInicio" title="Fecha Inicio"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		formatter="date"
		    			    		
		    		/>
		    			    	   		
		    	<sjg:gridColumn name="fechaFinal" index="fechaFinal" title="Fecha Final"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		formatter="date"
		    		/>
		    		
		    		
		    
		    		
		    	<sjg:gridColumn name="tipoAbono" index="tipoAbono" title="Tipo Abono"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="select"  
		    		editoptions="{value:'Imponible Tributable:Imponible Tributable;Imponible NO Tributable:Imponible NO Tributable;NO Imponible Tributable:NO Imponible Tributable;NO Imponible NO Tributable:NO Imponible NO Tributable'}" 
		    		
		    		/>
		    </sjg:grid>
		    </sj:div>
         </sj:tabbedpanel>
    </div></div></div>
    
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
    <s:actionmessage/>
    <table width="950" border="1">
  <tr>
    <td rowspan="2"><table align="left" width="470" border="1">
      <tr>
        <td class="texto_titulo" colspan="2" align="left">HABERES IMPONIBLES</td>
        </tr>
      <tr>
        <td class="texto">+ Sueldo Base</td>
        <td class="cifra"><s:property value="SueldoBaseImponibleTrabajado"/></td>
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
    <td><table width="470" border="1" align="left">
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
    <td><table width="470" border="1">
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
    <td><table width="470" border="1">
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
    <td><table width="470" border="1">
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
    <s:a id="button_solo" name="GenerarReporteLiquidacionPDF" value="obtenerLiquidacionPDFTrabajador"><span>Obtener en PDF</span></s:a>
    <s:a id="button_solo" name="GenerarReporteLiquidacionExcel" value="obtenerLiquidacionXLSTrabajador"><span>Obtener en Excel</span></s:a>
     </div>
    </div>
    </div>
    
    
    
    
    
    
    
  </section> 
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
<script type="text/javascript"> Cufon.now(); </script>

</body>
</html>