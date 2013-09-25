<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Abonos y Descuentos</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css"></link>
	-->
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

 <script type="text/javascript" src="js/jquery-1.7.2.js"></script> <!--  script para habilitar el jquery -->
  <sj:head jqueryui="true" jquerytheme="redmond" locale="es"/><!--  script para habilitar el jquery con tema -->

<link rel="shortcut icon" href="images/2_icono_remuneraciones_viaticos.png"/>
<script type="text/javascript" src="http://www.bci.cl/common/include/valores.js"></script>
<script type="text/javascript" src="js/fechaHoy.js"></script>
<script type="text/javascript" src="js/validaAboDesCriterios.js"></script>
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
				<li class="alpha" ><s:a name="GestionarTra" value="loadGestionar"><span><span>Ficha Empleado</span></span></span></s:a></li>
				<li><s:a name="IngresarTra" value="loadIngEmpleado"><span><span>Nuevo Trabajador</span></span></s:a></li>
    			<li id="menu_active"><s:a name="Administrar" value="loadAdministrar"><span><span>Abo. y Desc.</span></span></s:a></li>
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
				<article class=""><h3>Ingresar Descuentos o Abonos segun Criterios.</h3></article>
				</div>
				</div>
					<sj:tabbedpanel id="localtabs_3" cssStyle="font-size-small" >
     			    <sj:tab id="tab1_3" target="tone_3" label="Segun Cargo" cssStyle="font-size-small"  />
     				<sj:tab id="tab2_3" target="ttwo_3" label="Segun Rango" cssStyle="font-size-small" />
      				<div id="tone_3">
					<article class="">
					<sj:tabbedpanel id="localtabs" cssStyle="font-size-small" >
     			    <sj:tab id="tab1" target="tone" label="Descuento" cssStyle="font-size-small"  />
     				<sj:tab id="tab2" target="ttwo" label="Abono" cssStyle="font-size-small" />
      				<div id="tone">
      				<div class="box pad_bot1">
					        <div class="pad marg_top">
    						<s:form id="anticipoCargo" name="anticipoCargo" action="anticipoCargo" method="POST" onsubmit="return validaAnticipoCargo(this);">
    						<s:actionmessage cssClass="ui-state-highlight" cssStyle="font-size-small"/>
    						    <s:url id="remoteurl3" action="lista_cargos_tra" /> 
									<sj:select
										label="Cargo"
										href="%{remoteurl3}" 
										id="car_tra" 
										name="car_tra" 
										list="lista_cargos" 
										headerKey="-1" 
										headerValue="Seleccione el Cargo"
										cssClass="bg"
									/>
								<s:textfield cssClass="bg" name="nombre" id="nombre" label="Descripción" title="Ingrese un nombre"/>	
    							<s:textfield cssClass="bg" name="monto" id="monto" label="Monto del Anticipo" title="Ingrese un monto para el anticipo ej: 40000" onchange="javascript: Sue(this.value);"/>
    							<s:textfield cssClass="bg" name="num_cuotas" id="num_cuotas" label="Numero de Cuotas" title="Cantidad de veces a pagar" onchange="javascript: Sue(this.value);"/>
    							<sj:datepicker cssClass="bg" label="Fecha de Inicio" id="desde" name="desde" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" />
								<sj:submit value="Guardar" cssClass="button" title="Pulse el botón para guardar"/>
							<s:actionerror cssClass="ui-state-error" cssStyle="font-size-small"/>
							</s:form>
							</div></div></div>
							<div id="ttwo">
							<div class="box pad_bot1">
							<div class="pad marg_top">
							<s:form id="abonoCargo" name="abonoCargo" action="abonoCargo" method="POST" onsubmit="return validaAbonoCargo(this);">
    						<s:actionmessage cssClass="ui-state-highlight" cssStyle="font-size-small"/>
    						    
									<sj:select
										label="Cargo"
										href="%{remoteurl3}" 
										id="car_tra2" 
										name="car_tra2" 
										list="lista_cargos" 
										headerKey="-1" 
										headerValue="Seleccione el Cargo"
										cssClass="bg"
									/>
								<s:textfield cssClass="bg" name="nombre2" id="nombre2" label="Descripción" title="Ingrese un nombre"/>
    							<s:textfield cssClass="bg" name="monto2" id="monto2" label="Monto del Anticipo" title="Ingrese un monto para el anticipo ej: 40000" onchange="javascript: Sue(this.value);"/>
    							<s:textfield cssClass="bg" name="num_cuotas2" id="num_cuotas2" label="Numero de Cuotas" title="Cantidad de veces a pagar" onchange="javascript: Sue(this.value);"/>
    							<sj:datepicker cssClass="bg" label="Fecha de Inicio" id="desde2" name="desde2" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" />
								<s:select cssClass="bg" label="Tipo de Abono" id="tipoabono" name="tipoabono" headerKey="-1" headerValue="Seleccion el Tipo de Abono" list="#{'Imponible Tributable':'Imponible Tributable','Imponible NO Tributable':'Imponible NO Tributable','NO Imponible Tributable':'NO Imponible Tributable','NO Imponible NO Tributable':'NO Imponible NO Tributable'}"/>
								<sj:submit value="Guardar" cssClass="button" title="Pulse el botón para guardar"/>
							<s:actionerror cssClass="ui-state-error" cssStyle="font-size-small"/>
							</s:form>
							</div></div></div>
							</sj:tabbedpanel>
							</div>
					
      				<div id="ttwo_3">
 
    				<sj:tabbedpanel id="localtabs2" >
     			    <sj:tab id="tab12" target="tone2" label="Descuento" />
     				<sj:tab id="tab22" target="ttwo2" label="Abono"/>
      				<div id="tone2">
      				<div class="box pad_bot1">
					        <div class="pad marg_top">
    						<s:form id="anticipoRango" name="anticipoRango" action="anticipoRango" method="POST" onsubmit="return validaAnticipoRango(this);">
    						<s:actionmessage cssClass="ui-state-highlight" cssStyle="font-size-small"/>
    							<s:textfield cssClass="bg" name="nombre3" id="nombre3" label="Descripción" title="Ingrese un nombre"/>
    							<s:textfield cssClass="bg" name="monto_desde" id="monto_desde" label="Desde (sueldo)" title="Desde que sueldo se dara el abono" onchange="javascript: Sue(this.value);"/>
								<s:textfield cssClass="bg" name="monto_hasta" id="monto_hasta" label="Hasta (sueldo)" title="Hasta que sueldo se dara el abono" onchange="javascript: Sue(this.value);"/>
    							<s:textfield cssClass="bg" name="monto3" id="monto3" label="Monto del Anticipo" title="Ingrese un monto para el anticipo ej: 40000" onchange="javascript: Sue(this.value);"/>
    							<s:textfield cssClass="bg" name="num_cuotas3" id="num_cuotas3" label="Numero de Cuotas" title="Cantidad de veces a pagar"/>
    							<sj:datepicker cssClass="bg" label="Fecha de Inicio" id="desde3" name="desde3" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" />
								<sj:submit value="Guardar" cssClass="button" title="Pulse el botón para guardar"/>
							<s:actionerror cssClass="ui-state-error" cssStyle="font-size-small"/>
							</s:form>
							</div></div></div>
							<div id="ttwo2">
							<div class="box pad_bot1">
							<div class="pad marg_top">
							<s:form id="abonoRango" name="abonoRango" action="abonoRango" method="POST" onsubmit="return validaAbonoRango(this);">
    						<s:actionmessage cssClass="ui-state-highlight" cssStyle="font-size-small"/>
    							<s:textfield cssClass="bg" name="nombre4" id="nombre4" label="Descripción" title="Ingrese un nombre"/>
    						    <s:textfield cssClass="bg" name="monto_desde2" id="monto_desde2" label="Desde (sueldo)" title="Desde que sueldo se dara el abono" onchange="javascript: Sue(this.value);"/>
								<s:textfield cssClass="bg" name="monto_hasta2" id="monto_hasta2" label="Hasta (sueldo)" title="Hasta que sueldo se dara el abono" onchange="javascript: Sue(this.value);"/>
    							<s:textfield cssClass="bg" name="monto4" id="monto4" label="Monto del Anticipo" title="Ingrese un monto para el anticipo ej: 40000" onchange="javascript: Sue(this.value);"/>
    							<s:textfield cssClass="bg" name="num_cuotas4" id="num_cuotas4" label="Numero de Cuotas" title="Cantidad de veces a pagar" onchange="javascript: Sue(this.value);"/>
    							<sj:datepicker cssClass="bg" label="Fecha de Inicio" id="desde4" name="desde4" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" />
								<s:select cssClass="bg" label="Tipo de Abono" id="tipoabono2" name="tipoabono2" headerKey="-1" headerValue="Seleccion el Tipo de Abono" list="#{'Imponible Tributable':'Imponible Tributable','Imponible NO Tributable':'Imponible NO Tributable','NO Imponible Tributable':'NO Imponible Tributable','NO Imponible NO Tributable':'NO Imponible NO Tributable'}"/>
								<sj:submit value="Guardar" cssClass="button" title="Pulse el botón para guardar"/>
							<s:actionerror cssClass="ui-state-error" cssStyle="font-size-small"/>
							</s:form>
							</div></div></div>
							</sj:tabbedpanel>
				</div>
				</sj:tabbedpanel>
			</div>
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