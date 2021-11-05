package Logica;

import Logica.crud.CitaCrudService;
import Logica.crud.CitaCrudServiceImpl;

public class BusinessFactory {



	public static CitaCrudService forCitaCrudService() {
		return new CitaCrudServiceImpl();
	}

}

