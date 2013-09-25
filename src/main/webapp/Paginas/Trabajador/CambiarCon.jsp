<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page import="org.remusystem.persistencia.Trabajador" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Cambiar Contraseña</title>
    
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
  <script type="text/javascript" src="js/validaPass.js"></script><!-- PARA EL CSS SmartBizz-->
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
  		    <li class="alpha" id="menu_active"><s:a name="VerFicha" value="verFichaTra"><span><span>Ver Mis Datos</span></span></s:a></li>
    		<li><s:a name="BusAnticipos"><span><span>Ver Anticipos</span></span></s:a></li>
    		<li class="omega"><s:a name="VerLiquidacion"><span><span>Ver Liquidación</span></span></s:a></li>
					</ul>
		<!-- </nav> -->
		</header>
<!-- / header -->
<!-- content -->
	<section id="content">
		<div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""><h3>Cambiar Contraseña</h3></article>
					</div>
			</div>
			<div class="box pad_bot1 bot">
				<div class="pad marg_top">
					<article class="">
    
    <% Trabajador trabajador = (Trabajador) session.getAttribute("Trab_PRO");%>


	<s:actionerror cssClass="ui-state-error" cssStyle="font-size-small"/>
	<s:actionmessage cssClass="ui-state-highlight" cssStyle="font-size-small"/>
    <s:form id="ModContra" name="ModContra" action="ModContra" method="POST" onsubmit="return validaPass(this);">
    <s:password cssClass="bg" name="pw_actual" id="pw_actual" label="Password Actual" title="Ingrese su actual password" />
	<s:password cssClass="bg" name="pw_new" id="pw_new" label="Ingrese su nueva Password" title="Ingrese su nueva password" />
	<s:password cssClass="bg" name="pw_confirm" id="pw_confirm" label="Confirme la nueva Password" title="repita la nueva password" />
	<sj:submit cssClass="button" value="Modificar" title="Pulse el botón para modificar"/>
	
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
<script type="text/javascript"> Cufon.now(); </script>

</body>
</html>