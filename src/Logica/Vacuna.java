package Logica;

import java.util.Objects;

public class Vacuna {

	private String nombre;
	private String componente;



	public Vacuna(String nombre, String componente){
		this.nombre = nombre;
		this.componente = componente;

	}
	
	public Vacuna (Vacuna otroArticulo) {
        this(otroArticulo.nombre, otroArticulo.componente);
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.nombre);
		buffer.append(" - ");
		buffer.append(this.componente);
		return buffer.toString();
	   }

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vacuna other = (Vacuna) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}
