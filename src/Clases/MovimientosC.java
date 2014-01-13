package Clases;

import BaseDatos.ConectorBD;

public class MovimientosC implements BaseDatos{

	private String  IdMov, fecha, fechaPC, IdProvCli, nFactura, observaciones;
	private double importe;
	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		String valores="'"+fecha+"' , ";
		valores+="'"+this.fechaPC+"' , ";
		valores+=this.IdProvCli+" , ";
		valores+=+this.importe+" ,";
		valores+="'"+this.nFactura+"' , ";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Insert("movimientos",valores);
		return 0;
	}


	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+fecha+"' , ";
		valores+="'"+this.fechaPC+"' , ";
		valores+=this.IdProvCli+" , ";
		valores+=+this.importe+" ,";
		valores+="'"+this.nFactura+"' , ";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Update("movimientos", valores, this.IdMov);
		
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("movimientos", this.IdMov);
		
	}
	
	
	public MovimientosC()
	{
		IdMov="0";
		fecha="";
		fechaPC="";
		nFactura="sin numero";
		IdProvCli="";
		observaciones="";
		
	}



	public String getIdFact() {
		return IdMov;
	}



	public void setIdFact(String idFact) {
		IdMov = idFact;
	}



	public String getnFactura() {
		return nFactura;
	}



	public void setnFactura(String nFactura) {
		this.nFactura = nFactura;
	}



	public String getIdProvCli() {
		return IdProvCli;
	}



	public void setIdProvCli(String idProvCli) {
		IdProvCli = idProvCli;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public String getFechaPC() {
		return fechaPC;
	}



	public void setFechaPC(String fechaPC) {
		this.fechaPC = fechaPC;
	}
	public double getImporte() {
		return importe;
	}


	public void setImporte(double importe) {
		this.importe = importe;
	}
}