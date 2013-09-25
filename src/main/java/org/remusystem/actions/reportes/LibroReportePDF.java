package org.remusystem.actions.reportes;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import org.remusystem.persistencia.Empresa;
import org.remusystem.persistencia.HibernateSessionFactory;
import org.remusystem.persistencia.RelacionLaboral;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionSupport;

public class LibroReportePDF extends ActionSupport implements SessionAware{

	private Map session;
	private Date fecha = new Date();
	//private Map<String, String> parametros = new HashMap<String, String>();
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private HttpServletResponse response;
	private String MesLibro;
	private String AnioLibro;
	
	public String execute(){
		Empresa emp = (Empresa) session.get("Empresa");
		String mes = TransformarMes(fecha.getMonth());
		Integer numAnio = fecha.getYear()+1900;
		String mes2 = MesLibro;
		String anio2 = AnioLibro;
		parametros.put("id_empresa", Integer.toString(emp.getId()));
		parametros.put("Mes", mes2);
		parametros.put("Anio", anio2);
		System.out.println("mes2: "+mes2);
		System.out.println("anio2: "+anio2);
		
		try{
		
			//Compilamos el archivo
						
			//Coneccion BD
            Connection conexion = ReportConector.getConnection();



            JasperCompileManager.compileReport("LibrodeRemuneraciones2.jrxml");
			
			JasperReport reporte = (JasperReport) JRLoader.loadObject("LibrodeRemuneraciones2.jasper");

			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
			
			JRExporter exporter = new JRPdfExporter();
		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporteLibrodeRemuneracionPDF.pdf"));
		    exporter.exportReport();
		    try { //con esto abro el pdf creado
		    	   File path = new File ("reporteLibrodeRemuneracionPDF.pdf");
		    	   Desktop.getDesktop().open(path);
		    	   addActionMessage("Se ha generado el reporte espere mientras carga el programa");
					//conexion.close();
		    	   return SUCCESS;
		    	   }
		    	catch (IOException ex) {
		    		ex.printStackTrace();
		    		addActionError("No se pudo crear el reporte.  Intente nuevamente");//TODO:modificar mensaje de error
			    	//conexion.close();
		    		return ERROR;
		    	}

		    
			/*ServletOutputStream sos = response.getOutputStream();
			InputStream reportStream = ServletActionContext.getServletContext().getResourceAsStream("C:\\Users\\Krlituss\\Workspaces\\MyEclipse 8.5\\SistemaRem\\WebRoot\\reportes\\LiquidaciondeSueldo.jasper");
			response.setContentType( "application/pdf" );
    		response.setHeader("Content-Disposition:", "attachment; filename=" + "Reporte.PDF");
			
			
			//Coneccion BD
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/remusystem", "remusystem", "123456");
			
			try{
				JasperPrint print = JasperFillManager.fillReport(reportStream, parametros, conexion);
				JasperExportManager.exportReportToPdfFile(print,"C:\\reporte.pdf");
			}
			catch (JRException er) {	
    	    	er.printStackTrace();
			}
			 	sos.flush();
	        	sos.close();
	        	
	        	conexion.close();*/
	   		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	  finally{
			// // conexion.close();
		  }

	
	}


public String TransformarMes(Integer nummes){
		String mes = "";
		
		switch(nummes){
		case 0:
			mes = "Enero";
			break;
		case 1:
			mes = "Febrero";
			break;
		case 2:
			mes = "Marzo";
			break;	
		case 3:
			mes = "Abril";
			break;
		case 4:
			mes = "Mayo";
			break;
		case 5:
			mes = "Junio";
			break;
		case 6:
			mes = "Julio";
			break;	
		case 7:
			mes = "Agosto";
			break;
		case 8:
			mes = "Septiembre";
			break;
		case 9:
			mes = "Octubre";
			break;
		case 10:
			mes = "Noviembre";
			break;
		case 11:
			mes = "Diciembre";
			break;
		}
		return mes;
	}


public Map getSession() {
	return session;
}


public void setSession(Map session) {
	this.session = session;
}


public Date getFecha() {
	return fecha;
}


public void setFecha(Date fecha) {
	this.fecha = fecha;
}


public Map<String, Object> getParametros() {
	return parametros;
}


public void setParametros(Map<String, Object> parametros) {
	this.parametros = parametros;
}


public HttpServletResponse getResponse() {
	return response;
}


public void setResponse(HttpServletResponse response) {
	this.response = response;
}


public String getMesLibro() {
	return MesLibro;
}


public void setMesLibro(String mesLibro) {
	MesLibro = mesLibro;
}


public String getAnioLibro() {
	return AnioLibro;
}


public void setAnioLibro(String anioLibro) {
	AnioLibro = anioLibro;
}
}
