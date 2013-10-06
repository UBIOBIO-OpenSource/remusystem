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
    
    <title>Login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/style.css" type="text/css"></link>
	<sj:head jqueryui="true" jquerytheme="start"/>
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="js/validaLogin.js"></script>
	
	<!-- PARA EL CSS SmartBizz-->
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<link rel="shortcut icon" href="images/2_icono_remuneraciones_viaticos.png"/>
<script type="text/javascript" src="js/jquery-1.4.2.js" ></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_400.font.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_700.font.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_600.font.js"></script>
<!--[if lt IE 9]>
	<script type="text/javascript" src="http://info.template-help.com/files/ie6_warning/ie6_script_other.js"></script>
	<script type="text/javascript" src="js/html5.js"></script>
<![endif]-->
	
	</head>
  
  <body id="page4">
  <div class="main_login">
  <header>
  <section id="header">
		
		<div class="wrapper">
			<h1 align="center"><a href="#" id="logo"> </a></h1>
		
		</div>
		</section>
	</header>
 
 <!-- content -->
	<section id="content">
		<div class="wrapper">
			<div class="pad_login">
				<div class="wrapper">
  					<article class="col_login"><h2 align="center" >Iniciar Sesion</h2></article>
  				</div>
  			</div>
  			<div class="box pad_bot1 bot">
				<div class="pad marg_top">
					<article class="col1">
  
    <s:form id="loginForm" name="loginForm" action="Login" method="POST" onsubmit="return validaLogin(this);">
    <s:textfield cssClass="bg" name="rut" id="rut" label="Rut" title="Ingrese su rut" onchange="javascript: Rut(this.value);"/>
	<s:password cssClass="bg" name="password" id="password" label="Password" title="Ingrese su password" />
	<sj:submit cssClass="button" value="Entrar" title="Pulse el botón para ingresar al sistema"/>
	<s:actionerror cssClass="ui-state-error" cssStyle="font-size-small;width: 340;margin: 8px;text-align: center"/>
	</s:form>
	</article>
	</div>
	</div>
	</div>
	</section>
<!-- content -->
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
