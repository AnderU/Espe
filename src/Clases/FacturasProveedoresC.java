package Clases;

import BaseDatos.ConectorBD;

public class FacturasProveedoresC implements BaseDatos{

	private String nFactura, IdProveedor, formaPago, observaciones, fecha, fechaPago,IdFormaPago;
	@Override
	public void Insert() {
		// TODO Auto-generated method stub
		String valores="'"+nFactura+"' , ";
		valores+=this.IdProveedor+" , ";
		valores+=this.IdFormaPago+" , ";
		valores+="'"+this.fecha+"' , ";
		valores+="'"+this.fechaPago+"' , ";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Insert("facturaProv",valores);
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+nFactura+"' , ";
		valores+=this.IdProveedor+" , ";
		valores+=this.IdFormaPago+" , ";
		valores+="'"+this.fecha+"' , ";
		valores+="'"+this.fechaPago+"' , ";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Update("facturaProv", valores, this.nFactura);
		
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("facturaProv", this.nFactura);
		
	}
	
	
	public FacturasProveedoresC()
	{
		nFactura="sin numero";
		IdProveedor="";
		formaPago="";
		observaciones="";
		IdFormaPago="";
		
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getnFactura() {
		return nFactura;
	}
	public void setnFactura(String nFactura) {
		this.nFactura = nFactura;
	}
	public String getIdProveedor() {
		return IdProveedor;
	}
	public String getIdFormaPago() {
		return IdFormaPago;
	}

	public void setIdFormaPago(String idFormaPago) {
		IdFormaPago = idFormaPago;
	}

	public void setIdProveedor(String idProveedor) {
		IdProveedor = idProveedor;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
