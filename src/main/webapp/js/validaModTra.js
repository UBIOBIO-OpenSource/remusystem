function validaFormModTra(F){
	if(vacio(F.nom_tra.value)){
			alert("Ingrese el nombre del trabajador");
			document.getElementById("nom_tra");
			F.nom_tra.focus();
			return false;
		}else{
			if(vacio(F.app_tra.value)){
				alert("Ingrese el Apellido Paterno del Trabajador");
				document.getElementById("app_tra");
				F.app_tra.focus();
				return false;
			}else{
				if(vacio(F.apm_tra.value)){
					alert("Ingrese el apellido materno del trabajador");
					document.getElementById("apm_tra");
					F.apm_tra.focus();
					return false;
				}else{
					if(vacio(F.dir_tra.value)){
							alert("Ingrese una direcci�n");
							document.getElementById("dir_tra");
							return false;
						}else{
							if(vacio(F.tel_tra.value)){
									alert("Ingrese un numero de tel�fono");
									document.getElementById("tel_tra");
									return false;
								}else{
									if(vacio(F.cel_tra.value)){
										alert("ingrese un numero de celular");
										document.getElementById("cel_tra");
										return false;
									}else{
										if(vacio(F.num_car_tra.value)){
											alert("Ingrese el numero de Cargas");
											document.getElementById("num_car_tra");
											return false;
										}else{
											
													
													if(vacio(F.suel_bas_tra.value)){
														alert("Debe ingresar un sueldo base");
														document.getElementById("suel_bas_tra");
														return false;
													}else{
														
														return true;
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

//Revisa que el sueldo sea numero
function Sue(S){
	var1 = parseInt(S);
	if( isNaN(var1)){
		alert("Debe Ingresar una cantidad");
		return false;
	}else{
		return true;
	}
}

//valida numero de telefono
function Tel(t){
	var1 = parseInt(t);
	 if ( isNaN(var1)) {  
	        alert("El numero de telefono ingresado no es valido, por favor solo ingrese n�meros.");
	        return false;
	   }else{
		   largo = t.length;
		   if((largo < 6) || (largo > 9)){
			   alert("El numero de telefono debe contener entre 6 y 9 digitos");
			   window.document.RegTraForm.tel_tra.focus();		
			   window.document.RegTraForm.tel_tra.select();
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
	        alert("El numero de celular ingresado no es valido, por favor solo ingrese n�meros.");
	        return false;
	   }else{
		   largo = f.length;
		   if((largo < 8) || (largo > 9)){
			   alert("El numero de celular debe contener entre 8 y 9 digitos ej: 82547981");
			   window.document.RegtraForm.fax_tra.focus();		
			   window.document.RegtraForm.fax_tra.select();
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
		alert("El email "+ E +" no es v�lido");
		window.document.RegtraForm.email_tra.focus();		
		window.document.RegtraForm.email_tra.select();
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
		window.document.RegTraForm.rut_tra.focus();		
		window.document.RegTraForm.rut_tra.select();	
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
		window.document.RegTraForm.rut_tra.focus();		
		window.document.RegTraForm.rut_tra.select();		
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
		window.document.RegTraForm.rut_tra.focus();		
		window.document.RegTraForm.rut_tra.select();		
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
		window.document.RegTraForm.rut_tra.focus();		
		window.document.RegTraForm.rut_tra.select();			
		return false;	
	}	

	for (i=0; i < largo ; i++ )	
	{			
		if ( texto.charAt(i) !="0" && texto.charAt(i) != "1" && texto.charAt(i) !="2" && texto.charAt(i) != "3" && texto.charAt(i) != "4" && texto.charAt(i) !="5" && texto.charAt(i) != "6" && texto.charAt(i) != "7" && texto.charAt(i) !="8" && texto.charAt(i) != "9" && texto.charAt(i) !="k" && texto.charAt(i) != "K" )
 		{			
			alert("El valor ingresado no corresponde a un R.U.T valido");			
			window.document.RegTraForm.rut_tra.focus();		
			window.document.RegTraForm.rut_tra.select();				
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

	window.document.RegTraForm.rut_tra.value = invertido.toUpperCase();	
	if ( revisarDigito2(texto) )		
		return true;	

	return false;
}