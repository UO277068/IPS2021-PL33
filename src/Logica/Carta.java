package Logica;

import java.util.ArrayList;
import java.util.List;

public class Carta {
	
	private static final String FICHERO_VACUNAS0 = "Multimedia/Vacunas0.csv";
	private static final String FICHERO_VACUNAS1 = "Multimedia/Vacunas1.csv";
	private List<Vacuna> listaVacunas0 = null;
	private List<Vacuna> listaVacunas1 = null;
	private List<Vacuna> listaVacunas = null;
	
	public Carta(){
		listaVacunas0 = new ArrayList<Vacuna>();
		listaVacunas1 = new ArrayList<Vacuna>();
		listaVacunas = new ArrayList<Vacuna>();
		cargarArticulos();
		listaVacunas.addAll(listaVacunas0);
		listaVacunas.addAll(listaVacunas1);
	}

	private void cargarArticulos(){
		FileUtil.loadFile (FICHERO_VACUNAS0, listaVacunas0);
		FileUtil.loadFile (FICHERO_VACUNAS1, listaVacunas1);
	  }

	public List<Vacuna> getVacunas() {
		return listaVacunas;
	}

//	public Vacuna[] getVacunas(){
//		Vacuna[] articulos = listaVacunas.toArray(new Vacuna[listaVacunas.size()]);
//		return articulos;	
//	}
	
	
	

	
}
