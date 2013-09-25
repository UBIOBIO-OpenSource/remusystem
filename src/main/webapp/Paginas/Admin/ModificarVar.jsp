<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Modificar Variables</title>
    
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
<script type="text/javascript" src="js/fechaHoy.js"></script>
<link rel="shortcut icon" href="images/2_icono_remuneraciones_viaticos.png"/>
<script type="text/javascript" src="http://www.bci.cl/common/include/valores.js"></script>
  </head>
  
  <%
  				if(session.getAttribute("tipo") != "admin"){
  						
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
  						<td align="right"></td><td align="right"><s:a title="Cerrar Sesión" cssClass="marker_cerrar" name="Cerrarsession" value="CerrarSession"></s:a></td>
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
				<li class="alpha"><s:a name="Administrar" value="loadRegEmpresa"><span><span>Registrar Empresa</span></span></s:a></li>
				<li class="omega" id="menu_active"><s:a name="IngresarTra" value="loadModVariables"><span><span>Modificar Variables de Cálculo</span></span></span></s:a></li>
			</ul>
		<!-- </nav> -->
  			</header>
<!-- / header -->
<!-- content -->
  
  	<!-- Action Grid Impuesto Unico -->
    <s:url id="remoteurlImpuestoUnico" action="listarImpuestoUnicoSegunda"/>
    <s:url id="editUrlImpuestoUnico" action="EditarImpuestoUnicoSegunda"/>
  
    <!-- Action Grid Asignacion Familiar -->
    <s:url id="remoteurlAsignacionFamiliar" action="listarAsignacionFamiliar"/>
    <s:url id="editUrlAsignacionFamiliar" action="EditarAsignacionFamiliar"/>
    
    <!-- Action Grid Valores -->
    <s:url id="remoteurlValores" action="listarValores"/>
    <s:url id="editUrlValores" action="editarValores"/>
    
    <!-- Action Grid Topes -->
    <s:url id="remoteurlTopes" action="listarTopes"/>
    <s:url id="editUrlTopes" action="editarTopes"/>
    
    <!-- Action Grid AFP -->
    <s:url id="remoteurlAFP" action="listarAFP"/>
    <s:url id="editUrlAFP" action="editarAFP"/>
    
    
    
 	<section id="content">
		<div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""><h3>Modificar Variables para el Cálculo</h3></article>
					</div>
			</div>
			<div class="box pad_bot1">
				<div class="pad marg_top">
					
    	<sj:tabbedpanel id="localtabs" title="Variables" cssStyle="font-size:small">
      	<sj:tab id="tab1" target="tone" label="Impuesto Unico" cssStyle="font-size:small"/>
      	<sj:tab id="tab2" target="ttwo" label="Asignacion Familiar" cssStyle="font-size:small"/>
      	<sj:tab id="tab3" target="tthree" label="UF y UTM" cssStyle="font-size:small"/>
      	<sj:tab id="tab4" target="tfour" label="Topes" cssStyle="font-size:small"/>
      	<sj:tab id="tab5" target="tfive" label="AFP" cssStyle="font-size:small"/>
      	<sj:div id="tone">
      	<sjg:grid
      		id="gridImnpuestoUnico"
	        caption="Impuesto Unico Segunda Categoria"
	        dataType="json"
	        href="%{remoteurlImpuestoUnico}"
	        pager="true"
	        gridModel="gridModel"
	        rowList="10,15,20"
	        rowNum="10"
	        rownumbers="true"
	        multiselect="false"
	        width="800"
	        cssStyle="font-size:small"
	        navigator="true"
	    	editurl="%{editUrlImpuestoUnico}"
			editinline="false"
	    	viewrecords="true"
	    	sortable="true"
	        
	        onSelectRowTopics="rowselect"
	        navigatorAdd="true"
	        navigatorSearch="false" 
		    navigatorRefresh="true"
		    navigatorDelete="false"
		    navigatorEdit="true" 
		    navigatorView="true" 
		    
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
      							
      			<sjg:gridColumn name="desde" index="desde" title="Desde (UTM)"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}"   
		    		
		    		
		    		/>
		    	
		    	<sjg:gridColumn name="hasta" index="hasta" title="Hasta (UTM)"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    			    		
		    		/>
		    		
		    	<sjg:gridColumn name="factor" index="factor" title="Factor"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		
		    		/>
		    		
		    	<sjg:gridColumn name="rebaja" index="rebaja" title="Rebaja (UTM)"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		
		    		/>
		    </sjg:grid> 
		    </sj:div>
      <sj:div id="ttwo">
      		<sjg:grid
      		id="gridAsignacionFamiliar"
	        caption="Asignacion Familiar"
	        dataType="json"
	        href="%{remoteurlAsignacionFamiliar}"
	        pager="true"
	        gridModel="gridModel"
	        rowList="10,15,20"
	        rowNum="10"
	        rownumbers="true"
	        multiselect="false"
	        width="800"
	        cssStyle="font-size:small"
	        navigator="true"
	    	editurl="%{editUrlAsignacionFamiliar}"
			editinline="false"
	    	viewrecords="true"
	    	sortable="true"
	    		        
	        onSelectRowTopics="rowselect"
	        navigatorAdd="true"
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
      							sortable="false" 
      							editable="false" 
      							hidden="true"/>
      							
      			<sjg:gridColumn name="tramo" index="tramo" title="Tramo"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}"   
		    		width="50"
		    		
		    		/>
		    	
		    	<sjg:gridColumn name="monto" index="monto" title="Monto ($)"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    			    		
		    		/>
		    		
		    	<sjg:gridColumn name="desde" index="desde" title="Desde ($)"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		
		    		/>
		    		
		    	<sjg:gridColumn name="hasta" index="hasta" title="Hasta ($)"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		
		    		/>
		    </sjg:grid> 
      </sj:div>
      
      <sj:div id="tthree">
      <sjg:grid
      		id="gridUF"
	        caption="Valores de UF y UTM"
	        dataType="json"
	        href="%{remoteurlValores}"
	        pager="true"
	        gridModel="gridModel"
	        rowList="10,15,20"
	        rowNum="10"
	        rownumbers="true"
	        multiselect="false"
	        width="800"
	        cssStyle="font-size:small"
	        navigator="true"
	    	editurl="%{editUrlValores}"
			editinline="false"
	    	viewrecords="true"
	    	sortable="true"
	        
	        onSelectRowTopics="rowselect"
	        navigatorAdd="true"
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
      							sortable="false" 
      							editable="false" 
      							hidden="true"/>
      							
      			<sjg:gridColumn name="nombre" index="nombre" title="Nombre"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}"   
		    		
		    		
		    		/>
		    	
		    	<sjg:gridColumn name="monto" index="monto" title="Valor en Pesos"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    			    		
		    		/>
		    		
		    	
		    </sjg:grid>
		    </sj:div>
		    
      <sj:div id="tfour">
      <sjg:grid
      		id="gridTOPES"
	        caption="Topes Maximos"
	        dataType="json"
	        href="%{remoteurlTopes}"
	        pager="true"
	        gridModel="gridModel"
	        rowList="10,15,20"
	        rowNum="10"
	        rownumbers="true"
	        multiselect="false"
	        width="800"
	        cssStyle="font-size:small"
	        navigator="true"
	    	editurl="%{editUrlTopes}"
			editinline="false"
	    	viewrecords="true"
	    	sortable="true"
	        
	        onSelectRowTopics="rowselect"
	        navigatorAdd="true"
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
      							sortable="false" 
      							editable="false" 
      							hidden="true"/>
      							
      			<sjg:gridColumn name="nombre" index="nombre" title="Nombre"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}"   
		    		
		    		
		    		/>
		    	
		    	<sjg:gridColumn name="montoUf" index="montoUf" title="Monto en UF"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    			    		
		    		/>
		    		
		    	
		    </sjg:grid>
		    </sj:div>
		    
      <sj:div id="tfive">
      <sjg:grid
      		id="gridAFP"
	        caption="Asociacion de Fondos de Pensiones"
	        dataType="json"
	        href="%{remoteurlAFP}"
	        pager="true"
	        gridModel="gridModel"
	        rowList="10,15,20"
	        rowNum="10"
	        rownumbers="true"
	        multiselect="false"
	        width="800"
	        cssStyle="font-size:small"
	        navigator="true"
	    	editurl="%{editUrlAFP}"
			editinline="false"
	    	viewrecords="true"
	    	sortable="true"
	        
	        onSelectRowTopics="rowselect"
	        navigatorAdd="true"
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
      							sortable="false" 
      							editable="false" 
      							hidden="true"/>
      							
      			<sjg:gridColumn name="nombre" index="nombre" title="Nombre"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}"   
		    		
		    		
		    		/>
		    			    		
		    	<sjg:gridColumn name="porcentajeDescuento" index="porcentajeDescuento" title="Comision (factor)"
		    	    sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    			    		
		    		/>
		    		
		    	
		    </sjg:grid> </sj:div>
    </sj:tabbedpanel>
    </div>
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