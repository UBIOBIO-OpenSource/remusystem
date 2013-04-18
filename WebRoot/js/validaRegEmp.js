function validaFormRegEmp(F){
	if(vacio(F.rut_emp.value)){
		alert("Por Favor ingrese el rut de la empresa");
		document.getElementById("rut_emp");
		F.rut_emp.focus();
		return false;
	}else{
		if(vacio(F.nom_emp.value)){
			alert("Ingrese el nombre de la empresa");
			document.getElementById("nom_emp");
			F.nom_emp.focus();
			return false;
		}else{
			if(vacio(F.gir_emp.value)){
				alert("Ingrese el Giro de la empresa");
				document.getElementById("gir_emp");
				F.gir_emp.focus();
				return false;
			}else{
				if(vacio(F.dir_emp.value)){
					alert("Ingrese la direccion de la empresa");
					document.getElementById("dir_emp");
					F.dir_emp.focus();
					return false;
				}else{
					if(vacio(F.tel_emp.value)){
						alert("Ingrese un numero de contacto para la empresa");
						document.getElementById("tel_emp");
						return false;
					}else{
						if(vacio(F.email_emp.value)){
							alert("Ingrese un email de contacto");
							document.getElementById("email_emp");
							return false;
						}else{
							if(vacio(F.fax_emp.value)){
								alert("Ingrese un fax para la empresa");
								document.getElementById("fax_emp");
								return false;
							}else{
								if((F.caj_emp.value)==-1){
									alert("Debe Seleccionar una Caja de Compensación");
									
									return false;
								}else{
									if((F.mut_emp.value)==-1){
										alert("Debe Seleccionar una Mutual de Seguridad");
										
										return false;
									}else{
										if(((Rut(F.rut_emp.value)) && (Tel(F.tel_emp.value))) && ((Fax(F.fax_emp.value)) && (Email(F.email_emp.value))) ){
										return true;
									}else{
										return false;
									}
									
									}
									
								}
								

							}
						}
					}
				}
			}
		}
	}
	
	
}

//revisa que no hayan campos vacios
function vacio(q) {  
    for ( i = 0; i < q.length; i++ ) {  
            if ( q.charAt(i) != " " ) {  
                    return false;
            }  
    }  
    return true;
}

//valida numero de telefono
function Tel(t){
	var1 = parseInt(t);
	 if ( isNaN(var1)) {  
	        alert("El numero de telefono ingresado no es valido, por favor solo ingrese números.");
	        return false;
	   }else{
		   largo = t.length;
		   if((largo < 6) || (largo > 9)){
			   alert("El numero de telefono debe contener entre 6 y 9 digitos");
			   window.document.RegEmpForm.tel_emp.focus();		
			   window.document.RegEmpForm.tel_emp.select();
			   return false;
			   
		   }else{
			   return true;
		   }
	   }
	
}

//valida numero de telefono
function Fax(f){
	var1 = parseInt(f);
	 if ( isNaN(var1)) {  
	        alert("El numero de fax ingresado no es valido, por favor solo ingrese números.");
	        return false;
	   }else{
		   largo = f.length;
		   if((largo < 6) || (largo > 9)){
			   alert("El numero de fax debe contener entre 6 y 9 digitos");
			   window.document.RegEmpForm.fax_emp.focus();		
			   window.document.RegEmpForm.fax_emp.select();
			   return false;
			   
		   }else{
			   return true;
		   }
	   }
	
}


		

//valida el correo electronico
function Email(E){
	var b= /^[^@\s]+@[^@\.\s]+(\.[^@\.\s]+)+$/;
	//if(/^[w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(E)){
	if(b.test(E)){	
		return true;
	}else{
		alert("El email "+ E +" no es válido");
		window.document.RegEmpForm.email_emp.focus();		
		window.document.RegEmpForm.email_emp.select();
		return false;
	}
}

//valida el rut
function revisarDigito( dvr )
{	
	dv = dvr + "";	
	if ( dv != '0' && dv != '1' && dv != '2' && dv != '3' && dv != '4' && dv != '5' && dv != '6' && dv != '7' && dv != '8' && dv != '9' && dv != 'k'  && dv != 'K')	
	{		
		alert("Debe ingresar un digito verificador valido");		
		window.document.RegEmpForm.rut_emp.focus();
		window.document.RegEmpForm.rut_emp.select();	
		return false;	
	}	
	return true;
}

function revisarDigito2( crut )
{	
	largo = crut.length;	
	if ( largo < 2 )	
	{		
		alert("Debe ingresar el rut completo");		
		window.document.RegEmpForm.rut_emp.focus();		
		window.document.RegEmpForm.rut_emp.select();		
		return false;	
	}
	
	if ( largo > 2 )		
		rut = crut.substring(0, largo - 1);	
	else		
		rut = crut.charAt(0);	
	dv = crut.charAt(largo-1);	
	revisarDigito( dv );	

	if ( rut == null || dv == null )
		return 0;

	var dvr = '0';	
	suma = 0;
	mul  = 2;	

	for (i= rut.length -1 ; i >= 0; i--)	
	{	
		suma = suma + rut.charAt(i) * mul;		
		if (mul == 7)			
			mul = 2;
		else    			
			mul++;
	}	
	res = suma % 11;	
	if (res==1)		
		dvr = 'k';
	else if (res==0)		
		dvr = '0';	
	else	
	{		
		dvi = 11-res;		
		dvr = dvi + "";	
	}
	if ( dvr != dv.toLowerCase() )	
	{		
		alert("EL rut es incorrecto");		
		window.document.RegEmpForm.rut_emp.focus();		
		window.document.RegEmpForm.rut_emp.select();		
		return false;
	}

	return true;
}

function Rut(texto)
{	
	var tmpstr = "";	
	for ( i=0; i < texto.length ; i++ )		
		if ( texto.charAt(i) != ' ' && texto.charAt(i) != '.' && texto.charAt(i) != '-' )
			tmpstr = tmpstr + texto.charAt(i);	
	texto = tmpstr;	
	largo = texto.length;	
	if ( largo < 2 )	
	{		
		alert("Debe ingresar el rut completo");		
		window.document.RegEmpForm.rut_emp.focus();		
		window.document.RegEmpForm.rut_emp.select();			
		return false;	
	}	

	for (i=0; i < largo ; i++ )	
	{			
		if ( texto.charAt(i) !="0" && texto.charAt(i) != "1" && texto.charAt(i) !="2" && texto.charAt(i) != "3" && texto.charAt(i) != "4" && texto.charAt(i) !="5" && texto.charAt(i) != "6" && texto.charAt(i) != "7" && texto.charAt(i) !="8" && texto.charAt(i) != "9" && texto.charAt(i) !="k" && texto.charAt(i) != "K" )
 		{			
			alert("El valor ingresado no corresponde a un R.U.T valido");			
			window.document.RegEmpForm.rut_emp.focus();		
			window.document.RegEmpForm.rut_emp.select();				
			return false;		
		}	
	}	

	var invertido = "";	
	for ( i=(largo-1),j=0; i>=0; i--,j++ )		
		invertido = invertido + texto.charAt(i);	
	var dtexto = "";	
	dtexto = dtexto + invertido.charAt(0);	
	dtexto = dtexto + '-';	
	cnt = 0;	
	for ( i=1,j=2; i<largo; i++,j++ )	
	{		
		//alert("i=[" + i + "] j=[" + j +"]" );		
		if ( cnt == 3 )		
		{			
			dtexto = dtexto + '.';			
			j++;			
			dtexto = dtexto + invertido.charAt(i);			
			cnt = 1;		
		}		
		else		
		{				
			dtexto = dtexto + invertido.charAt(i);			
			cnt++;		
		}	
	}	
	invertido = "";	
	for ( i=(dtexto.length-1),j=0; i>=0; i--,j++ )		
		invertido = invertido + dtexto.charAt(i);	

	window.document.RegEmpForm.rut_emp.value = invertido.toUpperCase();	
	if ( revisarDigito2(texto) )		
		return true;	

	return false;
}