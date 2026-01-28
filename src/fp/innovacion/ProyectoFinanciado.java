package fp.innovacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;

import fp.utiles.Checkers;

public class ProyectoFinanciado extends Proyecto{
	
	Double presupuesto;
	String entidadFinanciadora;

	public ProyectoFinanciado(String titulo, List<Colaborador> colaboradores, LocalDate fechaInicio, LocalDate fechaFin,
			SortedSet<String> ambitos, Double presupuesto, String entidadFinanciadora) {
		super(titulo, colaboradores, fechaInicio, fechaFin, ambitos);
		setPresupuesto(presupuesto);
		setEntidadFinanciadora(entidadFinanciadora);
	}

	public Double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Double presupuesto) {
		Checkers.check("El presupuesto debe ser mayor que 0."
				, presupuesto > 0);
		Checkers.check("La duración del proyecto debe ser menor de 2 años si el presupuesto es inferior a 10000 €"
				, !(presupuesto < 100) || (getDuracion() / 365) < 2);
		this.presupuesto = presupuesto;
	}

	public String getEntidadFinanciadora() {
		return entidadFinanciadora;
	}

	public void setEntidadFinanciadora(String entidadFinanciadora) {
		Checkers.checkNoNull(entidadFinanciadora);
		Checkers.check("La entidad financiadora no puede ser vacía."
				, !(entidadFinanciadora.isEmpty()));
		this.entidadFinanciadora = entidadFinanciadora;
	}
	
	public void setFechaInicio(LocalDate f) {
		Checkers.check("No mayor de 2 años si presupuesto menor de 10000",
		!(getPresupuesto() < 10000) || (getDuracion() / 365) < 2);
		super.setFechaInicio(f);
		}

	public String toString() {
		return "ProyectoFinanciado [Proyecto=" + super.toString() + "presupuesto=" + presupuesto + ", entidadFinanciadora=" + entidadFinanciadora + "]";
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
