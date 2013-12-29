package Clases;

public class Poblacion {

	private String Id;
	private String poblacion;
	
	public Poblacion()
	{
		Id="0";
		poblacion="Especifique población";
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	@Override
	public String toString()
	{
		return this.poblacion;
	}
}
