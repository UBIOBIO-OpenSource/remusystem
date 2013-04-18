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
  <!-- PARA EL CSS SmartBizz-->
<link rel="stylesheet" href="/css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="/css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="/css/style.css" type="text/css" media="all">
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
    
    <meta http-equiv="Refresh"  content="0;URL= actions/verFichaTra" />
        <title>Espere un momento...</title>
  </head>
  
  
  <body>
    <div align="center">
    <img align="middle" src="/imagenes/barraProgresoLogin.gif"></img> 
    <p>espere un momento lo estamos redirigiendo...</p></div>
  
  </body>
</html>
