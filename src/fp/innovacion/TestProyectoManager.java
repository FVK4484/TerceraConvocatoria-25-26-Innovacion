package fp.innovacion;

import java.util.List;

public class TestProyectoManager {

	public static void main(String[] args) {
		List<Proyecto> proyectos = FactoriaProyectos.leeProyectos("./data/proyectos.csv");
		ProyectoManager pf = new ProyectoManager(proyectos);
		testTitulosProyectosLargos1(pf, 300);

	}
	
	private static void testTitulosProyectosLargos1(ProyectoManager pf, Integer dias) {
		try {
			System.out.println(" === Proyectos con duración > " + dias + " días ===");
			for (String d : pf.titulosProyectosLargos1(dias)) {
				System.out.println(" " + d);
			}
		} catch (Exception e) {
			System.out.println(" Excepción capturada.");
			System.out.println(e.getLocalizedMessage());
		}
	}

}
