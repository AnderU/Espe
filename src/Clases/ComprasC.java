package Clases;

import java.util.Date;

import BaseDatos.ConectorBD;

public class ComprasC implements BaseDatos{

	private String Id, idproveedor , observaciones, impuestos , iva;
	private Date fecha;
	
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
	public void Insert() {
		// TODO Auto-generated method stub
		String valores="'"+this.idproveedor+"' , ";
		valores+="'"+this.fecha+""+"' , ";
		valores+="'"+this.observaciones+"' , ";
		valores+=this.impuestos+" , ";
		valores+=this.iva;
		ConectorBD.bdMySQL.Insert("compras",valores);
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		
	}
}
