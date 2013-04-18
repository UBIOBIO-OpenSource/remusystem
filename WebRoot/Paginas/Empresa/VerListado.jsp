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
    
    <title>Ver Listado de Trabajadores</title>
    
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
				<li class="alpha" ><s:a name="GestionarTra" value="loadGestionar"><span><span>Ficha Empleado</span></span></span></s:a></li>
				<li><s:a name="IngresarTra" value="loadIngEmpleado"><span><span>Nuevo Trabajador</span></span></s:a></li>
    			<li><s:a name="Administrar" value="loadAdministrar"><span><span>Abo. y Desc.</span></span></s:a></li>
    			<li class="omega"  id="menu_active"><s:a name="VerList" value="loadVerListado"><span><span>Listar</span></span></s:a></li>
			</ul>
		<!-- </nav> -->
		</header>
<!-- / header -->
<!-- content -->
	<section id="content">
		<div class="wrapper">
		<sj:tabbedpanel id="localtabs_3" cssStyle="font-size-small" >
     			    <sj:tab id="tab1_3" target="tone_3" label="Listado de Trabajadores" cssStyle="font-size-small"  />
     				<sj:tab id="tab2_3" target="ttwo_3" label="Obtener Libro de Remuneraciones" cssStyle="font-size-small" />
      				<div id="tone_3">
<!--			<div class="pad">-->
<!--				<div class="wrapper">-->
<!--					<article class=""><h3>Listado de Trabajadores</h3></article>-->
<!--					</div>-->
<!--			</div>-->
<!--			<div class="box pad_bot1 bot">-->
<!--				<div class="pad marg_top">-->
<!--					<article class="">-->
					
			<!-- Action Anticipos -->
    <s:url id="remoteurlTrabajadores" action="listarTrabajadores"/>
	<s:url id="remoteurlEditarTrabajadores" action="editarTrabajadores"/>
			
			<sjg:grid
      		id="gridTrabajadores"
	        caption="Listado de Trabajadores"
	        dataType="json"
	        href="%{remoteurlTrabajadores}"
	        pager="true"
	        gridModel="gridModel"
	        rowList="15,25,50"
	        rowNum="10"
	        rownumbers="true"
	        multiselect="false"
	        width="870"
	        cssStyle="font-size:small"
	        navigator="true"
	    	editurl="%{remoteurlEditarTrabajadores}"
			editinline="false"
	    	viewrecords="true"
	    	
	    	sortable="true"
	    	sortname="rut"
	    	sortorder="desc"
	    		        
	        onSelectRowTopics="rowselect"
	        navigatorAdd="false"
	        navigatorSearch="false" 
		    navigatorRefresh="true"
		    navigatorDelete="false"
		    navigatorEdit="true" 
		    navigatorView="true" 
		    navigatorViewOptions="{width:400}"
		    navigatorEditOptions="{width:400,reloadAfterSubmit:true,closeAfterEdit:true}"
		   
		    
	    	>
      			<sjg:gridColumn name="id"
      							index="id" 
      							title="id" 
      							key="true" 
      							formatter="integer" 
      							sortable="tru" 
      							editable="false" 
      							hidden="true"/>
      							
      			<sjg:gridColumn name="rut" index="rut" title="Rut"  
		    		sortable="true" 
		    		editable="true" 
		    		edittype="text" 
		    		width="150"
		    		
		    		
		    		  
		    		
		    		
		    		/>
      							
      			<sjg:gridColumn name="nombre" index="nombre" title="Nombres"  
		    		sortable="true" 
		    		editable="false" 
		    		edittype="text"  
		    		width="100"
		    		 
		    		
		    		
		    		/>
		    	
		    	<sjg:gridColumn name="apellidoPaterno" index="apellidoPaterno" title="Apellido Paterno"  
		    		sortable="true" 
		    		editable="false" 
		    		edittype="text"  
		    		editoptions="{size: 100}"
		    		
		    			    		
		    		/>
		    		
		    	<sjg:gridColumn name="apellidoMaterno" index="apellidoMaterno" title="Apellido Materno"  
		    		sortable="true" 
		    		search="true"
		    		editable="false" 
		    		edittype="text"  
		    		editoptions="{size: 100}"
		    		
		    			    		
		    		/>
		    		
		    	<sjg:gridColumn name="fechaNacimiento" index="fechaNacimiento" title="Fecha de Nacimiento"  
		    		sortable="true" 
		    		search="true"
		    		editable="false" 
		    		edittype="text"  
		    		formatter="date"
		    		
		    		/>
		    		
		    	<sjg:gridColumn name="nacionalidad" index="nacionalidad" title="Nacionalidad"  
		    		sortable="true" 
		    		search="true"
		    		editable="false" 
		    		edittype="text"  
		    		editoptions="{size: 100}"
		    		
		    		
		    		
		    		/>
		    		
		    		
		    	<sjg:gridColumn name="sexo" index="sexo" title="Sexo" 
		    		sortable="true"
		    		search="true"
		    		editable="false"
	    			edittype="text" 
		    		editoptions="{size: 30}"
    		/>
		    		
		    	<sjg:gridColumn name="direccion" index="direccion" title="Dirección"  
		    		sortable="true" 
		    		search="true"
		    		editable="false" 
		    		edittype="text"  
		    		editoptions="{size: 150}" 
		    		
		    		
		    		/>
		    		
		    	<sjg:gridColumn name="telefonoFijo" index="telefonoFijo" title="Telefono Fijo"  
		    		sortable="true" 
		    		search="true"
		    		editable="false" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		
		    		/>
		    		
		    	<sjg:gridColumn name="celular" index="celular" title="Celular"  
		    		sortable="true" 
		    		search="true"
		    		editable="false" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		
		    		/>
		    		
		    	<sjg:gridColumn name="email" index="email" title="E-Mail"  
		    		sortable="true" 
		    		search="true"
		    		editable="false" 
		    		edittype="text"  
		    		editoptions="{size: 70}" 
		    		
		    		/>
		    		
		    	<sjg:gridColumn name="numeroCargas" index="numeroCargas" title="Numero de Cargas"  
		    		sortable="true" 
		    		search="true"
		    		editable="false" 
		    		edittype="text"  
		    		editoptions="{size: 10}" 
		    		
		    		/>
		    		
		    </sjg:grid>
			

   
<!--  	 </div>-->
  	 <div class="pad">
  	 <s:a id="button_solo_2" name="calcular_remuneraciones" value="CalcularRemuneracionParaTodos"><span>Calcular Remuneraciones</span></s:a>
<!--  	 </div>-->
</div>
  	 </div>
  	 
  	 <div id="ttwo_3">
<!--  	 </section>-->
<!--  	 <section id="content">-->
		<div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""><h3>Obtener Libro en PDF</h3></article>
					</div>
			</div>
			<div class="box pad_bot1">
				<div class="pad marg_top">
					<article class="">
   			<s:actionerror cssClass="ui-state-error" cssStyle="font-size-small"/>
   			<s:actionmessage cssClass="ui-state-highlight" cssStyle="font-size-small"/>
    	    <s:form id="obtenerLibroPDF" name="ObtenerLibroPDF" action="Struts2obtenerLibroPDF" >
   			<s:select cssClass="bg" label="Mes" id="MesLibro" name="MesLibro" headerKey="-1" headerValue="Seleccione el Mes" list="#{'Enero':'Enero','Febrero':'Febrero','Marzo':'Marzo','Abril':'Abril','Mayo':'Mayo','Junio':'Junio','Julio':'Julio','Agosto':'Agosto','Septiembre':'Septiembre','Octubre':'Octubre','Noviembre':'Noviembre','Diciembre':'Diciembre'}"/>
    		<s:select cssClass="bg" label="Año" id="AnioLibro" name="AnioLibro" headerKey="-1" headerValue="Seleccione el Año" list="#{'2012':'2012','2013':'2013','2014':'2014','2015':'2015'}"/>
<!--   			<s:textfield cssClass="bg" id="MesLibro" name="MesLibro" label="Mes"/>-->
<!--   			<s:textfield cssClass="bg" id="AnioLibro" name="AnioLibro" label="Año"/>-->
    		<s:submit cssClass="button" value="Obtener PDF" />
    		</s:form>
    		</div>
    		</div>
    		</div>
<!--    		</section>-->
     
<!--   <section id="content">-->
		<div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""> <h3>Obtener Libro en Excel</h3></article>
					</div>
			</div>
			<div class="box pad_bot1">
				<div class="pad marg_top">
					<article class="">  
   
   
    <s:form id="obtenerLibroExcel" name="obtenerLibroExcel" action="Struts2obtenerLibroXLS" >
    <s:select cssClass="bg" label="Mes" id="MesLibro" name="MesLibro" headerKey="-1" headerValue="Seleccione el Mes" list="#{'Enero':'Enero','Febrero':'Febrero','Marzo':'Marzo','Abril':'Abril','Mayo':'Mayo','Junio':'Junio','Julio':'Julio','Agosto':'Agosto','Septiembre':'Septiembre','Octubre':'Octubre','Noviembre':'Noviembre','Diciembre':'Diciembre'}"/>
    <s:select cssClass="bg" label="Año" id="AnioLibro" name="AnioLibro" headerKey="-1" headerValue="Seleccione el Año" list="#{'2012':'2012','2013':'2013','2014':'2014','2015':'2015'}"/>
<!--    <s:textfield cssClass="bg" id="MesLibro" name="MesLibro" label="Mes"/>-->
<!--   	<s:textfield cssClass="bg" id="AnioLibro" name="AnioLibro" label="Año"/>-->
    <s:submit cssClass="button" value="Obtener Excel" />
    </s:form>
    </div>
    </div>
    </div>
    </div>
    </sj:tabbedpanel>
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