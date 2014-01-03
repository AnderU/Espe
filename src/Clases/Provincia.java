package Clases;

public class Provincia {

	private String Id;
	private String provincia;
	
	public Provincia()
	{
		Id="0";
		provincia="Especifique provincia";
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	@Override
	public String toString()
	{
		return this.provincia;
	}
}