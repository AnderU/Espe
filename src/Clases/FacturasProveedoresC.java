package Clases;

import BaseDatos.ConectorBD;

public class FacturasProveedoresC implements BaseDatos{

	private String IdFactProv, nFactura, IdProveedor, formaPago, observaciones, fecha, fechaPago,IdFormaPago;
	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		String valores="'"+nFactura+"' , ";
		valores+=this.IdProveedor+" , ";
		valores+=this.IdFormaPago+" , ";
		valores+="'"+this.fecha+"' , ";
		valores+="'"+this.fechaPago+"' , ";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Insert("facturasproveedores",valores);
		return 0;
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
		ConectorBD.bdMySQL.Update("facturasproveedores", valores, this.IdFactProv);
		
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("facturasproveedores", this.IdFactProv);
		
	}
	
	
	public FacturasProveedoresC()
	{
		IdFactProv="0";
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

	public String getIdFactProv() {
		return IdFactProv;
	}

	public void setIdFactProv(String idFactProv) {
		IdFactProv = idFactProv;
	}

}
