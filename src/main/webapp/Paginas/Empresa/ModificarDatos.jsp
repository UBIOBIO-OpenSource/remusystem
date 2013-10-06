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
    
    <title>Modificar Datos Empleado</title>
    
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
  <script type="text/javascript" src="js/validaModTra.js"></script>  <!-- PARA EL CSS SmartBizz-->
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
					<article class=""><h3>Modificar Datos Trabajador</h3></article>
					</div>
			</div>
			<div class="box pad_bot1 bot">
				<div class="pad marg_top">
					<article class="">
    <% Trabajador trabajador = (Trabajador) session.getAttribute("trabajador"); %>
    <s:form id="ModificaTrabajador" name="ModificaTrabajador" action="ModificarTrabajadorEMP" onsubmit="return validaFormModTra(this)">
    <s:actionmessage cssClass="ui-state-highlight"  cssStyle="font-size-small;margin: 25px;text-align: center"/>
    <s:actionerror cssClass="ui-state-error" cssStyle="font-size-small;margin: 25px;text-align: center;padding: 10px"/>
<!--    <ul>-->
<!--	<li><span>Rut: <s:property value="#session.trabajador.getRut()" /></span></li>-->
<!--	<li><span>Fecha de Nacimiento: <s:property value="#session.trabajador.getFechaNacimiento()"/></span></li>-->
<!--    <li><span>Nacionalidad: <s:property value="#session.trabajador.getNacionalidad()"/></span></li>-->
<!--    <li><span>Sexo: <%if(trabajador!=null){ %>-->
<!--    			<%if((trabajador.getSexo()).equals(true)){%>-->
<!--    			Masculino <%}else{ %> Femenino <%}}%></span></li>-->
<!--    </ul>-->
    <s:textfield label="Rut" cssClass="bg" id="rut_tra" name="rut_tra" value="%{#session.trabajador.getRut()}" disabled="true"/>
    <s:textfield label="Fecha de Nacimiento" cssClass="bg" id="fec_nac_tra" name="fec_nac_tra" value="%{#session.trabajador.getFechaNacimiento()}" disabled="true"/>
    <s:textfield label="Nacionalidad" cssClass="bg" id="nac_tra" name="nac_tra" value="%{#session.trabajador.getNacionalidad()}" disabled="true"/>
    <s:textfield label="Nombre" cssClass="bg" id="nom_tra" name="nom_tra" value="%{#session.trabajador.getNombre()}"/>
  	<s:textfield label="Apellido Paterno" cssClass="bg" id="app_tra" name="app_tra" value="%{#session.trabajador.getApellidoPaterno()}"/>
  	<s:textfield label="Apellido Materno" cssClass="bg" id="apm_tra" name="apm_tra" value="%{#session.trabajador.getApellidoMaterno()}"/>
  	<s:textfield label="Cargas Familiares" cssClass="bg" id="num_car_tra" name="num_car_tra" value="%{#session.trabajador.getNumeroCargas()}"/>
  	<s:textfield label="Telefono Fijo" cssClass="bg" id="tel_tra" name="tel_tra" value="%{#session.trabajador.getTelefonoFijo()}" onchange="javascript: Tel(this.value);"/>
    <s:textfield label="Celular" cssClass="bg" id="cel_tra" name="cel_tra" value="%{#session.trabajador.getCelular()}" onchange="javascript: Fax(this.value);"/>
  	<s:textfield label="Direccion" cssClass="bg" id="dir_tra" name="dir_tra" value="%{#session.trabajador.getDireccion()}"/>
    
  	<s:textfield label="Email" cssClass="bg" id="email_tra" name="email_tra" value="%{#session.trabajador.getEmail()}" onchange="javascript: Email(this.value);"/>
  	<s:textfield label="Sueldo Base" cssClass="bg" id="suel_bas_tra" name="suel_bas_tra" value="%{#session.relacion.getSueldoBase()}" onchange="javascript: Sue(this.value);"/>
    <s:url id="remoteurl3" action="lista_cargos_tra" /> 
					<sj:select
						href="%{remoteurl3}" 
						id="car_tra" 
						name="car_tra" 
						list="lista_cargos" 
						headerKey="-1" 
						headerValue="Seleccione el Cargo del Trabajador"
						cssClass="bg"
						label="Cargo"
					/>
  	<s:url id="remoteurl" action="lista_inst_salud" /> 
					<sj:select
						href="%{remoteurl}" 
						id="sal_tra" 
						name="sal_tra" 
						list="lista_salud" 
						headerKey="-1" 
						headerValue="Seleccione una Institucion de Salud"
						cssClass="bg"
						label="Institucion de Salud"
					/>
	<s:textfield label="Valor Plan Isapre" cssClass="bg" id="valor_plan" name="valor_plan" value="%{#session.relacion.getValorPlanIsapre()}"/>
    <s:url id="remoteurl2" action="lista_inst_prevision" /> 
					<sj:select
						href="%{remoteurl2}" 
						id="pre_tra" 
						name="pre_tra" 
						list="lista_prevision" 
						headerKey="-1" 
						headerValue="Seleccione una Institucion de Prevision"
						cssClass="bg"
						label="Institucion de Previsión"
					/>
  	
  		<s:submit cssClass="button" value="Modificar"/>
    </s:form>
    </div>
    </div>
    </div>
    </section>
</div>
</section>
<!-- content -->

</div>
<!-- footer -->
    <footer>
        <div align="center">
        <a href="http://www.ubiobio.cl" id="Footer_logo_universidad"></a>
            <p><br>
            REMUSYSTEM , es desarrollado y mantenido por alumnos, ex-alumnos y profesores de la carrera de Ingeniería Civil Informática de la <br>
            <a href="http://www.ubiobio.cl/face/">Facultad de Ciencias Empresariales de la Universidad del Bío-Bío.</a>
            La versión inicial fue desarrollado por <a href="http://cl.linkedin.com/pub/carlos-sebastián-cáceres-lópez/20/735/576/">Carlos Cáceres López</a><br>
    	    como requisito parcial para la obtención del título de Ingeniero Civil Informático.
    	    <br><br>
    	    <a href="www.remusystem.org">www.remusystem.org</a><br>
    	    Software distribuido bajo la licencia <a href="http://www.apache.org/licenses/LICENSE-2.0.html">Apache License 2.0</a> <br>

        </div>
    </footer>
<!-- / footer -->

</body>
</html>