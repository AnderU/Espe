package Clases;

import BaseDatos.ConectorBD;

public class DetalleFacturasCliente implements BaseDatos{
	
	private String Id, fecha, idgenero, cantidad, precio, albaran, idfactura , genero, importe;
	
	public String formateaFecha(String fecha)
	{
		String ano=fecha.substring(6, 10);
		String mes=fecha.substring(3, 5);
		String dia=fecha.substring(0, 2);
		return ano+"-"+mes+"-"+dia;
	}

	public DetalleFacturasCliente() {

		Id ="";
		this.fecha ="NULL";
		this.genero="";
		this.idgenero = "";
		this.cantidad = "0.0";
		this.precio = "0.0";
		this.albaran = "";
		this.idfactura = "";
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getIdgenero() {
		return idgenero;
	}

	public void setIdgenero(String idgenero) {
		this.idgenero = idgenero;
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

	public String getAlbaran() {
		return albaran;
	}

	public void setAlbaran(String albaran) {
		this.albaran = albaran;
	}

	public String getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(String idfactura) {
		this.idfactura = idfactura;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		String valores="'"+formateaFecha(fecha)+"' ||| ";
		valores+=this.idgenero+" ||| ";
		valores+=cantidad+" ||| ";
		valores+=precio+" ||| ";
		valores+="'"+this.albaran+"' |||";
		valores+=this.idfactura;
		ConectorBD.bdMySQL.Insert("detallefacturasclientes",valores);
		return 0;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+fecha+"' ||| ";
		valores+=this.idgenero+" ||| ";
		valores+=cantidad+" ||| ";
		valores+=precio+" ||| ";
		valores+="'"+this.albaran+"' |||";
		valores+=this.idfactura;
		ConectorBD.bdMySQL.Update("detallefacturasclientes", valores, this.Id);

	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("detallefacturasclientes", this.Id);
	}

}
