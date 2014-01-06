package Clases;

import BaseDatos.ConectorBD;

public class DetalleComprasC implements BaseDatos{
	
	private String Id, idGenero , cantidad , precio , idcompra ,  facturada, genero ;



	public DetalleComprasC()
	{
		Id="";
		idGenero="";
		cantidad="";
		precio="";
		idcompra="";
		genero="";
		facturada="0";
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

	public String getFacturada() {
		return facturada;
	}

	public void setFacturada(String facturada) {
		this.facturada = facturada;
	}

	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		String valores=this.idGenero+" , ";
		valores+=this.cantidad+" , ";
		valores+=this.precio+" , ";
		valores+=this.idcompra+" , ";
		valores+=this.facturada;
		ConectorBD.bdMySQL.Insert("detalleCompras",valores);
		return 0;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores=this.idGenero+" , ";
		valores+=this.cantidad+" , ";
		valores+=this.precio+" , ";
		valores+=this.idcompra+" , ";
		valores+=this.facturada;
		ConectorBD.bdMySQL.Update("detalleCompras",valores, this.Id);
	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		
	}  

}
