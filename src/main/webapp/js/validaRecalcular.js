function validaRecalcularLiq(F){
	if(vacio(F.Dias.value)){
		alert("Debe Ingresar los Dias u horas trabajadas.");
		return false;
	}else{
		if(vacio(F.Horas_Extras.value)){
			alert("Por favor ingrese las horas extras o el valor 0.");
			return false;
		}else{
			if((F.MesCalcular.value)==-1){
				alert("Por favor seleccione el mes a recalcular.");
				return false;
							
			}else{
				if((F.AnioCalcular.value)==-1){
					alert("Por favor seleccione el año");
					return false;
				}else{
					if(!sue(F.Dias.value)){
						alert("Revise que ha ingrsado un numero en Dias");
						return false;
					}else{
						if(!sue(F.Horas_Extras.value)){
							alert("Revise que ha ingrsado un numero en horas extras");
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

function vacio(q) {  
    for ( i = 0; i < q.length; i++ ) {  
            if ( q.charAt(i) != " " ) {  
                    return false;
            }  
    }  
    return true;
} 

//Revisa que sean numero enteros
function Sue(S){
	var1 = parseInt(S);
	if( isNaN(var1)){
		alert("Debe Ingresar una cantidad");
		return false;
	}else{
		return true;
	}
}
