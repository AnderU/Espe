package Clases;

public class TipoConcepto {
	
	private int Id;
	private String tipo;
	
	public TipoConcepto()
	{
		Id=0;
		tipo="Especifique tipo";
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString()
	{
		return this.tipo;
	}

}
