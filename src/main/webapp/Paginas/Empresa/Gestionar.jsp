<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ page import="org.remusystem.persistencia.Trabajador" %>
<%@ page import="java.util.List" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Gestionar Trabajador</title>
    
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
  <script type="text/javascript" src="js/validaBuscarLiquidacion.js"></script>
  
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
					<article class=""><h3>Buscar Empleado</h3></article>
					</div>
			</div>
			<div class="box pad_bot1">
				<div class="pad marg_top">
					<article class="">
					<s:actionerror cssClass="ui-state-error" cssStyle="font-size-small" />
					<s:actionmessage cssClass="ui-state-highlight" cssStyle="font-size-small"/>
    				<% Trabajador trabajador = (Trabajador) session.getAttribute("trabajador"); %>
    				<s:form id="buscaTrabajador" name="buscaTrabajador" action="BuscarTrabajadorEMP" onsubmit="return validaForm(this)">
    				<s:textfield label="Rut" cssClass="bg" id="rut_tra" name="rut_tra" onchange="javascript: Rut(this.value);" value="%{#session.trabajador.getRut()}" />
    				<s:a cssClass="button" name="BuscarBt" href="javascript:document.buscaTrabajador.submit()"><span>Buscar</span></s:a>
  					<s:a cssClass="button" name="ModificarTra" value="loadModificarEMP"><span>Modificar</span></s:a>
  					
  					<s:a cssClass="button" name="EliminarRel" value="EliminarRel" onclick="return confirm('¿Está seguro que desea eliminar la relacion laboral?')" ><span>Eliminar</span></s:a>
    				</s:form>
    				<!-- <form id="buscaTrabajador" name="buscaTrabajador" action="BuscarTrabajadorEMP" onsubmit="return validaForm(this)">
    				<table>
    					<tr>
    						<td><s:label>Rut: </s:label></td><td><input class="bg" type="text" id="rut_tra" name="rut_tra" onchange="javascript: Rut(this.value);"></td>
							<td><input type="submit" value="Buscar" class="button" /></td>
							<td><s:a cssClass="button" name="ModificarTra" value="loadModificarEMP"><span>Modificar</span></s:a></td>
  							<td><s:a cssClass="button" name="EliminarRel" value="EliminarRel"><span>Eliminar</span></s:a></td>
  						</tr>
  					</table>
					</form> -->
    				
    				
    				<div id="ficha_trabajador">
    				<table width="100%" border="1">
    	     		<tr>
    					<td class="texto">Nombres:</td>
    					<td class="texto">Sueldo Base</td>
    				</tr>
  					<tr>
    					<td class="cifra"><s:property value="#session.trabajador.getNombre()"/></td>
    					<td class="cifra"><s:property value="#session.relacion.getSueldoBase()"/></td>
  					</tr>
  					<tr>
    					<td class="texto">Apellido Paterno:</td>
    					<td class="texto">Apellido Materno</td>
    				</tr>
  					<tr>
    					<td class="cifra"><s:property value="#session.trabajador.getApellidoPaterno()"/></td>
    					<td class="cifra"><s:property value="#session.trabajador.getApellidoMaterno()"/></td>
  					</tr>
  					<tr>
    					<td class="texto">Fecha de Nacimiento:</td>
    					<td class="texto">Nacionalidad:</td>
    				</tr>
    				<tr>	
    					<td class="cifra"><s:property value="#session.trabajador.getFechaNacimiento()"/></td>
    					<td class="cifra"><s:property value="#session.trabajador.getNacionalidad()"/></td>
  					</tr>
  					<tr>
    					<td class="texto">Sexo:</td>
    					<td class="texto">Cargas Familiares:</td>
    				</tr>
    				<tr>	
    					<td><%if(trabajador!=null){ %>
    					<%if((trabajador.getSexo()).equals(true)){%>
    					<p class="cifra">Masculino</p> <%}else{ %> <p class="cifra">Femenino</p> <%}}%></td>
    					<td class="cifra"><s:property value="#session.trabajador.getNumeroCargas()"/></td>
  					</tr>
  					<tr>
    					<td class="texto">Telefono Fijo:</td>
    					<td class="texto">Celular:</td>
    				</tr>
    				<tr>	
    					<td class="cifra"><s:property value="#session.trabajador.getTelefonoFijo()"/></td>
    					<td class="cifra"><s:property value="#session.trabajador.getCelular()"/></td>
  					</tr>
		  			<tr>
    					<td class="texto">Dirección:</td>
    					<td class="texto">Valor Plan Isapre:</td>
    				</tr>
    				<tr>
    					<td class="cifra"><s:property value="#session.trabajador.getDireccion()"/></td>
    					<td class="cifra"><s:property value="#session.relacion.getValorPlanIsapre()"/></td>
  					</tr>
  					<tr>
    					<td class="texto">Email:</td>
    					<td class="texto">Cargo:</td>
    				</tr>
    				<tr>
    					<td class="cifra"><s:property value="#session.trabajador.getEmail()"/></td>
    					<td class="cifra"><s:property value="#session.relacion.getGrupoHabDesc().getCargo()"/></td>
  					</tr>
  					<tr>
    					<td class="texto">Inst. Previsión:</td>
    					<td class="texto">Inst. Salud:</td>
    				</tr>
    				<tr>
    					<td class="cifra"><s:property value="#session.relacion.getInstitucionPrevision().getNombre()"/></td>
    					<td class="cifra"><s:property value="#session.relacion.getInstitucionSalud().getNombre()"/></td>
  					</tr>
	  		
  				</table></div>
  				
    </div>
    </div>
    </div>
     
    
    
   	<!-- Action Anticipos -->
    <s:url id="remoteurlAnticipos" action="listarAnticipos"/>
    <s:url id="editUrlAnticipos" action="editarAnticipos"/>
   
  
    <!-- Action Grid Abonos -->
    <s:url id="remoteurlAbonos" action="listarAbonos"/>
    <s:url id="editUrlAbonos" action="editarAbonos"/>
    
    
		<div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""><h3>Abonos y Descuentos Asociados al Trab.</h3></article>
					</div>
			</div>
			<div class="box pad_bot1">
				<div class="pad marg_top">
					<article class="">
   <sj:tabbedpanel id="localtabs" cssStyle="font-size-small">
      <sj:tab id="tab1" target="tone" label="Descuentos" cssStyle="font-size-small"/>
      <sj:tab id="tab2" target="ttwo" label="Abonos" cssStyle="font-size-small"/>
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
	    	sortorder="desc"
	        
	        onSelectRowTopics="rowselect"
	        navigatorAdd="true"
	        navigatorSearch="false" 
		    navigatorRefresh="true"
		    navigatorDelete="true"
		    navigatorEdit="true" 
		    navigatorView="true" 
		    navigatorViewOptions="{width:500}"
		    navigatorAddOptions="{width:500,reloadAfterSubmit:true,closeAfterAdd:true}"
		    navigatorEditOptions="{width:500,reloadAfterSubmit:true,closeAfterEdit:true}"
		    navigatorDeleteOptions="{width:400,reloadAfterSubmit:true}"
		    
		    
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
		    		editrules="{required:true}"
		    		
		    		/>
		    		
		    		
		    	<sjg:gridColumn name="monto" index="monto" title="Monto Mensual"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		editrules="{number:true,required:true}"
		    		/>
		    	
		    	
		    		
		    	<sjg:gridColumn name="numerCuotas" index="numerCuotas" title="Numero de Cuotas"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		editrules="{number:true,required:true}"
		    		
		    		/>
		    		
		    	<sjg:gridColumn name="fechaInicio" index="fechaInicio" title="Fecha Inicio"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		formatter="date"
		    		editoptions="{value:'dd-mm-aaaa'}"
		    		editrules="{required:true}"
		    			    			    		
		    		/>
		    		
		    	<sjg:gridColumn name="fechaFinal" index="fechaFinal" title="Fecha Final"  
		    		sortable="true" 
		    		search="true"
		    		editable="false" 
		    		edittype="text"  
		    		formatter="date"
		    		
		    		
		    		/>
		    		
		    	
		    </sjg:grid> 
      
      </sj:div>
      
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
	        navigatorAdd="true"
	        navigatorSearch="false" 
		    navigatorRefresh="true"
		    navigatorDelete="true"
		    navigatorEdit="true" 
		    navigatorView="true" 
		    navigatorViewOptions="{width:500}"
		    navigatorAddOptions="{width:500,reloadAfterSubmit:true,closeAfterAdd:true}"
		    navigatorEditOptions="{width:500,reloadAfterSubmit:true,closeAfterEdit:true}"
		    navigatorDeleteOptions="{width:400,reloadAfterSubmit:true}"
		    
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
		    		editrules="{required:true}"
		    		
		    		/>
		    		
		    	<sjg:gridColumn name="monto" index="monto" title="Monto Mensual"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		editrules="{number:true,required:true}"
		    		
		    		/>
		    	
		    	<sjg:gridColumn name="numeroCuotas" index="numeroCuotas" title="Numero de Cuotas"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		editoptions="{size: 30}" 
		    		editrules="{number:true,required:true}"
		    		/>
		    		
		    	<sjg:gridColumn name="fechaInicio" index="fechaInicio" title="Fecha Inicio"  
		    		sortable="true" 
		    		search="true"
		    		editable="true" 
		    		edittype="text"  
		    		formatter="date"
		    		editoptions="{value:'dd-mm-aaaa'}"
		    		editrules="{required:true}"
		    		
		    		/>
		    			    	   		
		    	<sjg:gridColumn name="fechaFinal" index="fechaFinal" title="Fecha Final"  
		    		sortable="true" 
		    		search="true"
		    		editable="false" 
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
 	</div>
 	</div>
 	</div>
<!-- 	</section>-->
 	
 	
<!--    <section id="content">-->
<!--		<div class="wrapper">-->
<!--			<div class="pad">-->
<!--				<div class="wrapper">-->
<!--					<article class=""><h3>Calcular Sueldo Trabajador</h3></article>-->
<!--					</div>-->
<!--			</div>-->
<!--			<div class="box pad_bot1">-->
<!--				<div class="pad marg_top">-->
<!--					<article class="">-->
<!--    				<s:actionerror/>-->
<!--    				<s:form id="CalcularSueldo" name="CalcularSueldo" action="calcularsueldoEMP" title="Ingresar Datos Para Calcular Sueldo del Mes">-->
<!--    				<s:textfield cssClass="bg" id="Dias" name="Dias" label="Dias Trabajados"/>-->
<!--    				<s:checkbox id="Horas" name="Horas" label="Marque si indica solo Horas Trabajadas"/>-->
<!--    				<s:textfield cssClass="bg" id="HorasExtras" name="HorasExtras" label="Horas Extras"/>-->
<!--    				<s:submit cssClass="button" value="Calcular" />-->
<!--    				</s:form>-->
<!--				</div>-->
<!--			</div>-->
<!--		</div>-->
<!--	</section>-->
    
     
		<div class="wrapper">
			<div class="pad">
				<div class="wrapper">
					<article class=""><h3>Buscar Liquidación del Trabajador</h3></article>
					</div>
			</div>
			<div class="box pad_bot1 bot">
				<div class="pad marg_top">
					<article class="">
    				<s:form id="verliquidacion" name="verliquidacion" action="MostrarLiquidacion" onsubmit="return validaverliquidacion(this);" >
    				<s:select cssClass="bg" label="Mes" id="Mes" name="Mes" headerKey="-1" headerValue="Seleccione el Mes" list="#{'Enero':'Enero','Febrero':'Febrero','Marzo':'Marzo','Abril':'Abril','Mayo':'Mayo','Junio':'Junio','Julio':'Julio','Agosto':'Agosto','Septiembre':'Septiembre','Octubre':'Octubre','Noviembre':'Noviembre','Diciembre':'Diciembre'}"/>
    				<s:select cssClass="bg" label="Anio" id="Anio" name="Anio" headerKey="-1" headerValue="Seleccione el Año" list="#{'2012':'2012','2013':'2013','2014':'2014','2015':'2015'}"/>
<!--   					<s:textfield cssClass="bg" id="Mes" name="Mes" label="Mes"/>-->
<!--   					<s:textfield cssClass="bg" id="Anio" name="Anio" label="Año"/>-->
    				<s:submit cssClass="button" value="Buscar" />
    				</s:form>
				</div>
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