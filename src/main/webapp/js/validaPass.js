
function validaPass(F){
	if(vacio(F.pw_actual.value)){
		alert("Por favor ingrese su pw actual.");
		document.getElementById("pw_actual");
		F.pw_actual.focus();
		return false;
	}else{
		if(vacio(F.pw_new.value)){
			alert("Por favor ingrese su nueva clave.");
			document.getElementById("pw_new");
			F.pw_new.focus();
			return false;
		}else{
			if(vacio(F.pw_confirm.value)){
				alert("Por favor ingrese la confirmacion de clave.");
				document.getElementById("pw_confirm");
				F.pw_new.focus();
				return false;
							
			}else{
				if((F.pw_confirm.value)!=(F.pw_new.value)){
					alert("La nueva password con su confirmacion no coinciden por favor revise");
					return false;
				}else{
					return true;
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
