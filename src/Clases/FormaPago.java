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
