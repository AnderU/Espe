package Clases;

public class DetalleComprasC {
	
	private String Id, idGenero , cantidad , precio , idcompra , genero;



	public DetalleComprasC()
	{
		Id="";
		idGenero="";
		cantidad="";
		precio="";
		idcompra="";
		genero="";
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getIdcompra() {
		return idcompra;
	}

	public void setIdcompra(String idcompra) {
		this.idcompra = idcompra;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}  

}
