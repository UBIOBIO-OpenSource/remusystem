function validaAnticipo(F){
	if(vacio(F.nom_ant.value)){
		alert("Por favor ingrese una descripcion del descuento");
		document.getElementById("nom_ant");
		F.nom_ant.focus();
		return false;
	}else{
		if(vacio(F.monto_ant.value)){
			alert("Debe Ingresar un monto para el descuento");
			document.getElementById("monto_ant");
			F.monto_ant.focus();
			return false;
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

//Revisa que sea numero
function Sue(S){
	var1 = parseInt(S);
	if( isNaN(var1)){
		alert("Debe Ingresar un numero válido");
		return false;
	}else{
		return true;
	}
}

