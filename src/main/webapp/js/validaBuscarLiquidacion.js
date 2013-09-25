function validaverliquidacion(F){
	if((F.Anio.value==-1)&&(F.Mes.value==-1)){
		alert("Ingrese Mes y Año para la Busqueda");
		return false;
	
	}else{
		if(F.Mes.value==-1){
			alert("Por Favor Seleccione un Mes.");
			
			return false;
		
		}else{
			if(F.Anio.value==-1){
				alert("Por favor seleccione un año.");
				
				return false;
			
			}else{
				return true;
			}
			
	}
	}
	return false;
}
