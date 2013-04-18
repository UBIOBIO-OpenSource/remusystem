package actions.grid.utils;

import java.util.Comparator;
import persistencia.Trabajador;

public class OrdenarTrabajadores implements Comparator {
	
	public int compare(Object o1, Object o2) {
		Trabajador u1 = (Trabajador) o1;
		Trabajador u2 = (Trabajador) o2;
		
		return u1.getId().compareTo(u2.getId());
	}
	

}
