package Clases;

import BaseDatos.ConectorBD;

public class MovimientosC implements BaseDatos{

	private String  IdMov, fecha, fechaPC, IdConcepto, IdGrupo, nFactura, observaciones;
	private String Tipo, Grupo;
	

	public String getIdGrupo() {
		return IdGrupo;
	}


	public void setIdGrupo(String idGrupo) {
		IdGrupo = idGrupo;
	}


	private double importe;
	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		String valores="'"+fecha+"' , ";
		valores+="'"+this.fechaPC+"' , ";
		valores+=this.IdConcepto+" , ";
		valores+=this.importe+" ,";
		valores+="'"+this.IdGrupo+"' , ";
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
		valores+=this.IdConcepto+" , ";
		valores+=+this.importe+" ,";
		valores+="'"+this.IdGrupo+"' , ";
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
		IdConcepto="";
		importe=0;
		IdGrupo="";
		nFactura="sin numero";
		observaciones="";
		Tipo="";
		Grupo="";
		
	}



	public String getTipo() {
		return Tipo;
	}


	public void setTipo(String tipo) {
		Tipo = tipo;
	}


	public String getGrupo() {
		return Grupo;
	}


	public void setGrupo(String grupo) {
		Grupo = grupo;
	}


	public String getnFactura() {
		return nFactura;
	}



	public void setnFactura(String nFactura) {
		this.nFactura = nFactura;
	}




	public String getIdMov() {
		return IdMov;
	}


	public void setIdMov(String idMov) {
		IdMov = idMov;
	}


	public String getIdConcepto() {
		return IdConcepto;
	}


	public void setIdConcepto(String idConcepto) {
		IdConcepto = idConcepto;
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