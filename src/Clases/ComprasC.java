package Clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import BaseDatos.ConectorBD;

public class ComprasC implements BaseDatos{

	private String Id, idproveedor , observaciones, impuestos , iva , proveedor , importe, total;
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	private Date fecha;
	
	public ComprasC()
	{
		Id="";
		idproveedor="";
		observaciones="";
		impuestos="";
		iva="";
		proveedor="";
		importe="";
		total="";
		fecha=null;
		
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getIdproveedor() {
		return idproveedor;
	}
	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		String valores="'"+this.idproveedor+"' ||| ";
		valores+="'"+df.format(this.fecha)+"' ||| ";
		valores+="'"+this.observaciones+"' ||| ";
		valores+=this.impuestos+" ||| ";
		valores+=this.iva;
		int id=ConectorBD.bdMySQL.Insert("compras",valores);
		return id;
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		String valores="'"+this.idproveedor+"' ||| ";
		valores+="'"+df.format(this.fecha)+"' ||| ";
		valores+="'"+this.observaciones+"' ||| ";
		valores+=this.impuestos+" ||| ";
		valores+=this.iva;
		ConectorBD.bdMySQL.Update("compras",valores,this.Id);
		
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("compras", this.Id);
		
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
}
