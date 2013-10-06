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
    
    <title>Registrar Empleado</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css"></link>
	-->
	
	<link rel="stylesheet" href="css/style.css" type="text/css"/>
  
  
  
  <script type="text/javascript" src="js/jquery-1.7.2.js"></script> <!--  script para habilitar el jquery -->
  <sj:head jqueryui="true" jquerytheme="redmond" locale="es"/><!--  script para habilitar el jquery con tema -->
  <script type="text/javascript" src="js/validaRegTra.js"></script> <!--  revisa el formulario y lo valida -->
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
				<li class="alpha" ><s:a name="GestionarTra" value="loadGestionar"><span><span>Ficha Empleado</span></span></span></s:a></li>
				<li  id="menu_active"><s:a name="IngresarTra" value="loadIngEmpleado"><span><span>Nuevo Trabajador</span></span></s:a></li>
    			<li><s:a name="Administrar" value="loadAdministrar"><span><span>Abo. y Desc.</span></span></s:a></li>
    			<li class="omega"><s:a name="VerList" value="loadVerListado"><span><span>Listar</span></span></s:a></li>
			</ul>
		<!-- </nav> -->
		</header>
<!-- / header -->
<!-- content -->
	<section id="content">
	<sj:tabbedpanel id="localtabs" cssStyle="font-size-small">
      <sj:tab id="tab1" target="tone" label="Datos Nuevo Trabajador" cssStyle="font-size-small"/>
      <sj:tab id="tab2" target="ttwo" label="Crear Nuevo Cargo" cssErrorStyle="font-size-small"/>
      <sj:div id="tone">
		<div class="wrapper">
<!--			<div class="pad">-->
<!--				<div class="wrapper">-->
<!--					<article class=""><h3>Registrar Trabajador</h3></article>-->
<!--					</div>-->
<!--			</div>-->
			<div class="box pad_bot1 bot">
				<div class="pad marg_top">
					<article class="">
					<s:form action="RegistrarTrabajador" method="POST" id="RegTraForm" name="RegTraForm" onsubmit="return validaFormRegTra(this)">
					<s:actionerror cssClass="ui-state-error" cssStyle="font-size-small;margin: 25px;text-align: center;padding: 10px"/>
					<s:actionmessage cssClass="ui-state-highlight"  cssStyle="font-size-small;margin: 25px;text-align: center"/>
					<s:label label="Datos Personales del Trabajador" cssClass="h4"  />
						<s:textfield cssClass="bg" label="Rut" id="rut_tra" name="rut_tra" title="Ingrese el rut del trabajador" onchange="javascript: Rut(this.value);"/>
						<s:textfield cssClass="bg" id="nom_tra" name="nom_tra" label="Nombres" title="Ingrese el nombre del trabajador" />
						<s:textfield cssClass="bg" id="app_tra" name="app_tra" label="Apellido Paterno" title="Ingrese el Apellido Paterno" />
						<s:textfield cssClass="bg" id="apm_tra" name="apm_tra" label="Apellido Materno" title="Ingrese el Apellido Materno" />
    					<sj:datepicker cssClass="bg" id="fec_nac_tra" name="fec_nac_tra" changeMonth="true" changeYear="true" label="Fecha de Nacimiento" displayFormat="dd/mm/yy" firstDay="1" yearRange="-100:-10" />	          
    					<s:textfield cssClass="bg" id="dir_tra" name="dir_tra" label="Direccion" title="Ingrese la Direccion del trabajador" />
    					<s:textfield cssClass="bg" id="nac_tra" name="nac_tra" label="Nacionalidad" title="Ingrese la Nacionalidad del Trabajador" value="Chilena"/>
						<s:div name="sexos" label="Marque el Sexo">
							<s:checkbox id="sex_mas_tra" name="sex_mas_tra" label="Masculino" value="true"/>
							<s:checkbox id="sex_fem_tra" name="sex_fem_tra" label="Femenino"/>
						</s:div>
						<s:textfield cssClass="bg" id="tel_tra" name="tel_tra" label="Telefono" title="Ingrese un numero de contacto" onchange="javascript: Tel(this.value);" />
						<s:textfield cssClass="bg" id="cel_tra" name="cel_tra" label="Celular" title="Ingrese un celular de contacto" onchange="javascript: Fax(this.value);" />
						<s:textfield cssClass="bg" id="email_tra" name="email_tra" label="Email" title="Ingrese una direccion email de contacto" onchange="javascript: Email(this.value);" />
						<s:textfield cssClass="bg" id="num_car_tra" name="num_car_tra" label="Numero de Cargas Familiares" title="Ingrese el numero de cargas" onchange="javascript: Sue(this.value);"/>
					<s:label label="Datos Relacion Laboral (Contrato)" cssClass="h4" />
						<s:select cssClass="bg" label="Tipo de Contrato" id="tip_con_tra" name="tip_con_tra" headerKey="-1" headerValue="Seleccion el Tipo de Contrato" list="#{'indefinido':'Indefinido','fijo':'A Plazo Fijo'}"/>
						<sj:datepicker cssClass="bg" label="Fecha de Inicio Contrato" id="fec_ini_con" name="fec_ini_con" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" firstDay="1" />
						<sj:datepicker cssClass="bg" label="Fecha de Fin Contrato" id="fec_fin_con" name="fec_fin_con" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" title="Si es indefinido dejar en blanco" firstDay="1" />
						<s:textfield cssClass="bg" id="suel_bas_tra" name="suel_bas_tra" label="Sueldo Base" title="Ingrese el sueldo base" value="193000" onchange="javascript: Sue(this.value);"/>
						<s:url id="remoteurl" action="lista_inst_salud" /> 
							<sj:select
								label="Institucion de Salud"
								href="%{remoteurl}" 
								id="sal_tra" 
								name="sal_tra" 
								list="lista_salud" 
								headerKey="-1" 
								headerValue="Seleccione una Institucion de Salud"
								cssClass="bg"
							/>
						<s:textfield cssClass="bg" id="valor_isapre" name="valor_isapre" label="Valor Plan en UF" title="Ingrese el Valor del Plan de Isapre" value="0.0"/>
						<s:url id="remoteurl2" action="lista_inst_prevision" /> 
							<sj:select
								label="Institucion de Prevision"
								href="%{remoteurl2}" 
								id="pre_tra" 
								name="pre_tra" 
								list="lista_prevision" 
								headerKey="-1" 
								headerValue="Seleccione una Institucion de Prevision"
								cssClass="bg"
							/>
						<s:url id="remoteurl3" action="lista_cargos_tra" /> 

						<sj:select
								label="Cargo"
								href="%{remoteurl3}" 
								id="car_tra" 
								name="car_tra" 
								list="lista_cargos" 
								headerKey="-1" 
								headerValue="Seleccione el Cargo del Trabajador"
								cssClass="bg"
							/>

						
							
						<s:submit cssClass="button" value="Registrar" align="center"/>
    				</s:form>
    				</div>
    				</div>
    				</div>
    				
    				
    				
    				
    				
    				
    				</sj:div>
    				<sj:div id="ttwo">
    				
    				<div class="box pad_bot1">
				<div class="pad marg_top">
					<article class="">
    				<s:form id="crearCargo" name="crearCargo" action="crearCargo" method="POST" onsubmit="return validaCargo(this)">
    			
    				<s:textfield cssClass="bg" name="nombre_cargo" id="nombre_cargo" label="Nombre para el Cargo" title="Ingrese un nombre"/>
					<sj:submit value="Guardar" cssClass="button" title="Pulse el botón para guardar"/>
					
					</s:form>
				</div>
			</div>
    				</sj:div>
    				</sj:tabbedpanel>
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