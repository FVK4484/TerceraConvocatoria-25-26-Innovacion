package fp.innovacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ProyectoManager {
	private List<Proyecto> proyectos;

	public ProyectoManager(List<Proyecto> proyectos) {
		this.proyectos = new ArrayList<>(proyectos);
	}
	
	public List<Proyecto> getProyectos() {
		return new ArrayList<>(proyectos);
	}

	public int hashCode() {
		return Objects.hash(proyectos);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProyectoManager other = (ProyectoManager) obj;
		return Objects.equals(proyectos, other.proyectos);
	}

	public String toString() {
		return "ProyectoManager [proyectos=" + proyectos + "]";
	}
	
	// 1. responsablesPorAmbito: dado un ámbito, devuelve una lista con los nombres de los responsables
	// de los proyectos que contienen dicho ámbito. (0,5 puntos)	
	public List<String> responsablesPorAmbito(String ambito) {
		return proyectos.stream()
				.filter(p -> p.getAmbitos().contains(ambito))
				.map(pr -> pr.getResponsable().nombre())
				.collect(Collectors.toList());
	}
	
	//	2. presupuestoTotalPorDepartamento: devuelve un SortedMap<String, Double> (orden natural) que
	//	asocia a cada departamento del responsable, el presupuesto total de sus proyectos financiados.
	//	(0,75 puntos)
	public SortedMap<String, Double> presupuestoTotalPorDepartamento() {
		return proyectos.stream()
				.filter(pr -> pr instanceof ProyectoFinanciado)
				.map(pr -> (ProyectoFinanciado) pr)
				.collect(Collectors.groupingBy(prFin -> prFin.getResponsable().departamento(),
						TreeMap::new,
						Collectors.summingDouble(prFin -> prFin.getPresupuesto())));
	}
	
	//	3. titulosProyectosLargos1: devuelve un SortedSet<String> con los títulos de los proyectos ordenados
	//	por duración (de mayor a menor) que se prolonguen por encima de un número n de días dado como
	//	parámetro (1,75 ptos)
	
	public SortedSet<String> titulosProyectosLargos1(Integer dias) {
		return proyectos.stream()
				.filter(p -> p.getDuracion().intValue() > dias)
				.sorted(Comparator.comparing(Proyecto::getDuracion).reversed())
				.map(Proyecto::getTitulo)
				.collect(Collectors.toCollection(TreeSet::new));
				
	}
	
//	4. proyectoMasColaborativo: devuelve el título del proyecto con más departamentos colaboradores.
//	En caso de empate, devuelva el que comience antes o null si no hay ningún título que mostrar. (1,5
//	ptos)
	
	return 

}
