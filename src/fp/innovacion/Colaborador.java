package fp.innovacion;

import fp.utiles.Checkers;

public record Colaborador(String nombre, String departamento) implements Comparable<Colaborador> {
	
	public Colaborador {
		Checkers.checkNoNull(nombre, departamento);
		Checkers.check("El nombre no puede estar vacío."
				, !nombre.isEmpty());
		Checkers.check("El departamento no puede estar vacío."
				, !departamento.isEmpty());
	}

	public int compareTo(Colaborador co) {
		Integer res = nombre().compareTo(co.nombre());
		if (res == 0) {
			res = departamento().compareTo(co.departamento());
		}
		return res;
	}	
	
}
