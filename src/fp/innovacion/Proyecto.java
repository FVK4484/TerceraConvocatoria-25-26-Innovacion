package fp.innovacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.utiles.Checkers;

public class Proyecto implements Comparable<Proyecto> {
	
	String titulo;
	List<Colaborador> colaboradores;
	LocalDate fechaInicio;
	LocalDate fechaFin;
	SortedSet<String> ambitos;
	
	public Proyecto(String titulo, List<Colaborador> colaboradores, LocalDate fechaInicio, LocalDate fechaFin,
			SortedSet<String> ambitos) {
		Checkers.check("La fecha de fin debe ser posterior a la de inicio."
				, fechaFin.isAfter(fechaInicio));
		Checkers.check("No puede haber colaboradores duplicados (mismo nombre y departamento)."
				, !hayDuplicados(colaboradores));
		Checkers.checkNoNull(ambitos);
		Checkers.check("El conjunto de ámbitos no puede estar vacío."
				, !ambitos.isEmpty());
		this.titulo = titulo;
		this.colaboradores = new ArrayList<>(colaboradores);
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.ambitos = new TreeSet<>(ambitos);
	}
	
	private Boolean hayDuplicados(List<Colaborador> colaboradores) {
		Set<Colaborador> repes = new HashSet<>(colaboradores);
		return repes.size() != colaboradores.size();
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		Checkers.check("La fecha de inicio no puede ser posterior a la de fin",
				!fechaFin.isBefore(fechaInicio));
		this.fechaInicio = fechaInicio;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<Colaborador> getColaboradores() {
		return new ArrayList<>(colaboradores);
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public SortedSet<String> getAmbitos() {
		return new TreeSet<>(ambitos);
	}
	
	public Colaborador getResponsable() {
		Colaborador res = null;
		if(!colaboradores.isEmpty()) {
			res = colaboradores.get(0);
		}
		return res;
	}
	
	public Long getDuracion() {
		return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
//		return fechaInicio.until(fechaFin, ChronoUnit.DAYS);
//		return (long)Period.between(fechaInicio, fechaFin).getDays();
	}
	
	public Estado getEstado() {
		Estado result = null;
		if(LocalDate.now().isBefore(getFechaInicio())) {
			result = Estado.CONCEDIDO;
		} else if(LocalDate.now().isAfter(getFechaFin())) {
			result = Estado.FINALIZADO;
		} else {
			result = Estado.EN_CURSO;
		}
		return result;
	}
	
	public Integer getNumeroColaboradores() {
		return colaboradores.size();
	}

	public String toString() {
		return "Proyecto [titulo=" + titulo + ", colaboradores=" + colaboradores + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", ambitos=" + ambitos + "]";
	}

	public int hashCode() {
		return Objects.hash(getResponsable(), titulo);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proyecto other = (Proyecto) obj;
		return Objects.equals(getResponsable(), other.getResponsable()) 
				&& Objects.equals(titulo, other.titulo);
	}

	public int compareTo(Proyecto p) {
		Integer res = titulo.compareTo(p.getTitulo());
		if (res == 0) {
			res = getResponsable().compareTo(p.getResponsable());
		}
		return res;
	}

}
