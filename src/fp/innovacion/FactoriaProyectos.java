package fp.innovacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaProyectos {
	
//	En la clase FactoriaProyectos, que se le da parcialmente implementada en el paquete fp.innovacion,
//	implemente el método:
//	• parseaProyecto: crea un objeto Proyecto o ProyectoFinanciado a partir de una línea del archivo
//	CSV (como las mostradas al principio del enunciado). Tenga en cuenta que si la línea tiene un
//	presupuesto, el artículo debe ser de tipo ProyectoFinanciado, mientras que si la línea no tiene
//	precio, debe ser de tipo Proyecto.
	 
	private static Proyecto parseaProyecto(String lineaCSV) {
		Checkers.checkNoNull(lineaCSV);
		String [] trozos = lineaCSV.split(",");
		Checkers.check("La longitud de el array debe ser o 5 o 7.", trozos.length == 5 || trozos.length == 7);
		Proyecto res = null;
		if (trozos.length == 5) {
			String titulo = trozos[0].trim();
			List<Colaborador> colaboradores	= parseaColaborador(trozos[1].trim());	
			LocalDate fechaInicio = LocalDate.parse(trozos[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate fechaFin = LocalDate.parse(trozos[3].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			SortedSet<String> ambitos = parseaAmbito(trozos[4].trim());
			res = new Proyecto(titulo, colaboradores, fechaInicio, fechaFin, ambitos);
		} else {
			String titulo = trozos[0].trim();
			List<Colaborador> colaboradores	= parseaColaborador(trozos[1].trim());	
			LocalDate fechaInicio = LocalDate.parse(trozos[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate fechaFin = LocalDate.parse(trozos[3].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			SortedSet<String> ambitos = parseaAmbito(trozos[4].trim());
			Double presupuesto = Double.valueOf(trozos[5].trim());
			String entidadFinanciadora = trozos[6].trim();
			res = new ProyectoFinanciado(titulo, colaboradores, fechaInicio, fechaFin, ambitos, presupuesto, entidadFinanciadora);
		}
		return res;
	}

	private static List<Colaborador> parseaColaborador(String colaboradoresCSV) {
		String nuevosColaboradoresCSV = colaboradoresCSV.replace("[", "").replace("]", "");
		String [] trozos = nuevosColaboradoresCSV.split(";");
		List<Colaborador> colaboradores = new ArrayList<>();
		for (String trozo : trozos) {
			String [] trocito = trozo.trim().split("â");
			String nombre = trocito[0].trim();
			String departamento = trocito[1].trim(); 
			colaboradores.add(new Colaborador(nombre, departamento));
		}
		return colaboradores;
	}
	
	private static SortedSet<String> parseaAmbito(String ambitosCSV) {
		String nuevosAmbitosCSV = ambitosCSV.replace("[", "").replace("]", "");
		String [] trozos = nuevosAmbitosCSV.split(";");
		SortedSet<String> ambitos = new TreeSet<>();
		for (String trozo : trozos) {
			ambitos.add(trozo.trim());
		}
		return ambitos;
	}
	
	public static List<Proyecto> leeProyectos(String lineasCSV) {
		Checkers.checkNoNull(lineasCSV);
		String err = "Error al abrir el fichero";
		List<String> lineas = Ficheros.leeFichero(err , lineasCSV);
		List<Proyecto> proyectos = new ArrayList<>();
		for (String linea : lineas) {
			proyectos.add(parseaProyecto(linea));
		}
		return proyectos;
	}

}
