package Clases;

import java.sql.Date;

import BaseDatos.ConectorBD;

public class FacturasProveedoresC implements BaseDatos{

	private int nFactura;
	private String IdProveedor, formaPago, observaciones, fecha, fechaCobro;
	@Override
	public void Insert() {
		// TODO Auto-generated method stub
		String valores="'"+nFactura+"' , ";
		valores+="'"+this.IdProveedor+"' , ";
		valores+="'"+this.formaPago+"' , ";
		valores+="'"+this.fecha+"' , ";
		valores+="'"+this.fechaCobro+"' , ";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Insert("facturaProv",valores);
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+nFactura+"' , ";
		valores+="'"+this.IdProveedor+"' , ";
		valores+="'"+this.formaPago+"' , ";
		valores+="'"+this.fecha+"' , ";
		valores+="'"+this.fechaCobro+"' , ";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Update("facturaProv", valores, Integer.toString(this.nFactura));
		
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("facturaProv", Integer.toString(this.nFactura));
		
	}
	
	
	public FacturasProveedoresC()
	{
		nFactura=0;
		IdProveedor="";
		formaPago="";
		observaciones="";
		
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFechaCobro() {
		return fechaCobro;
	}
	public void setFechaCobro(String fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	public int getnFactura() {
		return nFactura;
	}
	public void setnFactura(int nFactura) {
		this.nFactura = nFactura;
	}
	public String getIdProveedor() {
		return IdProveedor;
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
