package Clases;

public class FormaPago {

	private String Id;
	private String forma;
	
	public FormaPago()
	{
		Id="0";
		forma="Especifique la forma de pago";
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPoblacion() {
		return forma;
	}
	public void setPoblacion(String poblacion) {
		this.forma = poblacion;
	}
	@Override
	public String toString()
	{
		return this.forma;
	}
}
