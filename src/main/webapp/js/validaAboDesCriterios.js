function validaAnticipoCargo(F){
	if((F.car_tra.value)==-1){
		alert("Debe Seleccionar un Cargo");
		document.getElementById("car_tra");
		F.car_tra.focus();
		return false;
	}else{
		if(vacio(F.nombre.value)){
			alert("Por favor ingrese una descripcion");
			document.getElementById("nombre");
			F.nombre.focus();
			return false;
		}else{
			if(vacio(F.monto.value)){
				alert("Por favor ingrese un monto");
				document.getElementById("monto");
				F.monto.focus();
				return false;
			}else{
				if(vacio(F.num_cuotas.value)){
					alert("Por favor ingrese el numero de cuotas");
					document.getElementById("num_cuotas");
					F.num_cuotas.focus();
					return false;
				}else{
					if(vacio(F.desde.value)){
						alert("debe seleccionar la fecha de inicio");
						document.getElementById("desde");
						F.desde.focus();
						return false;
					}else{
					return true;
					}
					}
				}
			}
		}
}

function validaAbonoCargo(F){
	if((F.car_tra2.value)==-1){
		alert("Debe Seleccionar un Cargo");
		document.getElementById("car_tra2");
		F.car_tra2.focus();
		return false;
	}else{
		if(vacio(F.nombre2.value)){
			alert("Por favor ingrese una descripcion");
			document.getElementById("nombre2");
			F.nombre2.focus();
			return false;
		}else{
			if(vacio(F.monto2.value)){
				alert("Por favor ingrese un monto");
				document.getElementById("monto2");
				F.monto2.focus();
				return false;
			}else{
				if(vacio(F.num_cuotas2.value)){
					alert("Por favor ingrese el numero de cuotas");
					document.getElementById("num_cuotas2");
					F.num_cuotas2.focus();
					return false;
				}else{
					if(vacio(F.desde2.value)){
						alert("debe seleccionar la fecha de inicio");
						document.getElementById("desde2");
						F.desde2.focus();
						return false;
					}else{
						if((F.tipoabono.value)==-1){
							alert("debe seleccionar el tipo de abono");
							document.getElementById("tipoabono");
							F.tipoabono.focus();
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

function validaAnticipoRango(F){
	if(vacio(F.nombre3.value)){
		alert("Ingrese una Descripción");
		document.getElementById("nombre3");
		F.nombre3.focus();
		return false;
	}else{
		if(vacio(F.monto_desde.value)){
			alert("Ingrese desde que sueldo se entregará el abono");
			document.getElementById("monto_desde");
			F.monto_desde.focus();
			return false;
		}else{
			if(vacio(F.monto_hasta.value)){
				alert("Ingrese hasta que sueldo se entregará el abono");
				document.getElementById("monto_hasta");
				F.monto_hasta.focus();
				return false;
			}else{
				if(vacio(F.monto3.value)){
					alert("debe ingresar el monto");
					document.getElementById("monto3");
					F.monto3.focus();
					return false;
				}else{
					if(vacio(F.num_cuotas3.value)){
						alert("debe ingresar el numero de cuotas");
						document.getElementById("num_cuotas3");
						F.num_cuotas3.focus();
						return false;
					}else{
						if(vacio(F.desde3.value)){
							alert("debe ingresar la fecha de inicio");
							document.getElementById("desde3");
							F.desde3.focus();
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


function validaAbonoRango(F){
	if(vacio(F.nombre4.value)){
		alert("Ingrese una Descripción");
		document.getElementById("nombre4");
		F.nombre4.focus();
		return false;
	}else{
		if(vacio(F.monto_desde2.value)){
			alert("Ingrese desde que sueldo se entregará el abono");
			document.getElementById("monto_desde2");
			F.monto_desde2.focus();
			return false;
		}else{
			if(vacio(F.monto_hasta2.value)){
				alert("Ingrese hasta que sueldo se entregará el abono");
				document.getElementById("monto_hasta2");
				F.monto_hasta2.focus();
				return false;
			}else{
				if(vacio(F.monto4.value)){
					alert("debe ingresar el monto");
					document.getElementById("monto4");
					F.monto4.focus();
					return false;
				}else{
					if(vacio(F.num_cuotas4.value)){
						alert("debe ingresar el numero de cuotas");
						document.getElementById("num_cuotas4");
						F.num_cuotas4.focus();
						return false;
					}else{
						if(vacio(F.desde4.value)){
							alert("debe ingresar la fecha de inicio");
							document.getElementById("desde4");
							F.desde4.focus();
							return false;
						}else{
							if((F.tipoabono2.value)==-1){
								alert("debe seleccionar el tipo de abono");
								document.getElementById("tipoabono2");
								F.tipoabono2.focus();
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


function vacio(q) {  
    for ( i = 0; i < q.length; i++ ) {  
            if ( q.charAt(i) != " " ) {  
                    return false;
            }  
    }  
    return true;
}

//Revisa que sea un numero
function Sue(S){
	var1 = parseInt(S);
	if( isNaN(var1)){
		alert("Debe Ingresar una cantidad");
		return false;
	}else{
		return true;
	}
}

