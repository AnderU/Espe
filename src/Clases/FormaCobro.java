package Clases;

public class FormaCobro {

	private String Id;
	private String forma;
	
	public FormaCobro()
	{
		Id="0";
		forma="Especifique la forma de cobro";
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getForma() {
		return forma;
	}
	public void setForma(String forma) {
		this.forma = forma;
	}
	@Override
	public String toString()
	{
		return this.forma;
	}
}
