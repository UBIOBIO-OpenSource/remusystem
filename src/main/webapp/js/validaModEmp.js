function validaFormModEmp(F){
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
								if(Tel(F.tel_emp.value) && ((Fax(F.fax_emp.value)) && (Email(F.email_emp.value))) ){
									if(vacio(F.pw_actual.value)){
										alert("debe ingresar su actual pw para modificar los datos");
										document.getElementById("pw_actual");
										return false;
									}else{
										if((F.pw_confirm.value)!=(F.pw_new.value)){
											alert("La nueva password con su confirmacion no coinciden por favor revise");
											document.getElementById("pw_new");
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
			   window.document.ModEmpForm.tel_emp.focus();		
			   window.document.ModEmpForm.tel_emp.select();
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
			   window.document.ModEmpForm.fax_emp.focus();		
			   window.document.ModEmpForm.fax_emp.select();
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
		window.document.ModEmpForm.email_emp.focus();		
		window.document.ModEmpForm.email_emp.select();
		return false;
	}
}

