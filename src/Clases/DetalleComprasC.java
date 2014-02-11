package Clases;

import BaseDatos.ConectorBD;

public class DetalleComprasC implements BaseDatos{
	
	private String Id, idGenero , cantidad , precio , idcompra , genero , idfactura ;
	private boolean facturada;


	public DetalleComprasC()
	{
		Id="";
		idGenero="";
		cantidad="";
		precio="";
		idcompra="";
		genero="";
		facturada=false;
		idfactura="0";
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

	public boolean getFacturada() {
		return facturada;
	}

	public void setFacturada(boolean facturada) {
		this.facturada = facturada;
	}

	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		String valores=this.idGenero+" ||| ";
		valores+=this.cantidad+" ||| ";
		valores+=this.precio+" ||| ";
		valores+=this.idcompra+" ||| ";
		if (this.facturada)
			valores+="1 ||| ";
		else
			valores+="0 ||| ";
		valores+=this.idfactura;
		ConectorBD.bdMySQL.Insert("detallecompras",valores);
		return 0;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores=this.idGenero+" ||| ";
		valores+=this.cantidad+" ||| ";
		valores+=this.precio+" ||| ";
		valores+=this.idcompra+" ||| ";
		if (this.facturada)
			valores+="1 ||| ";
		else
			valores+="0 ||| ";
		valores+=this.idfactura;
		ConectorBD.bdMySQL.Update("detallecompras",valores, this.Id);
	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("detallecompras", this.Id);
	}

	public String getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(String idfactura) {
		this.idfactura = idfactura;
	}  

}
