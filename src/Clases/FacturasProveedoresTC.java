package Clases;

import java.util.Date;

public class FacturasProveedoresTC implements BaseDatos{

	private String Id, idGenero, genero, cantidad ,precio , iva , idCompra;
	private Date fecha;
	private boolean facturada;
	
	public FacturasProveedoresTC()
	{
		Id="";
		idGenero="";
		genero="";
		cantidad="";
		precio="";
		iva="";
		fecha=null;
		
	}
	
	public String getSubtotal()
	{
		 return Double.toString(Double.parseDouble(cantidad)*Double.parseDouble(precio));
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean getFacturada() {

			return this.facturada;
	}

	public void setFacturada(Boolean facturada) {
		this.facturada = facturada;
	}

	public String getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(String idCompra) {
		this.idCompra = idCompra;
	}

}
