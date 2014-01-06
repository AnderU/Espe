package Clases;

import BaseDatos.ConectorBD;

public class FacturasClientesC implements BaseDatos{

	private String IdFactCli, nFactura, IdCliente, formaCobro, observaciones, fecha, fechaCobro,IdFormaCobro;
	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		String valores="'"+nFactura+"' , ";
		valores+=this.IdCliente+" , ";
		valores+=this.IdFormaCobro+" , ";
		valores+="'"+this.fecha+"' , ";
		valores+="'"+this.fechaCobro+"' , ";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Insert("facturasclientes",valores);
		return 0;
	}
	
	public String getIdFactCli() {
		return IdFactCli;
	}

	public void setIdFactCli(String idFactCli) {
		IdFactCli = idFactCli;
	}

	public String getnFactura() {
		return nFactura;
	}

	public void setnFactura(String nFactura) {
		this.nFactura = nFactura;
	}

	public String getIdCliente() {
		return IdCliente;
	}

	public void setIdCliente(String idCliente) {
		IdCliente = idCliente;
	}

	public String getFormaCobro() {
		return formaCobro;
	}

	public void setFormaCobro(String formaCobro) {
		this.formaCobro = formaCobro;
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

	public String getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(String fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public String getIdFormaCobro() {
		return IdFormaCobro;
	}

	public void setIdFormaCobro(String idFormaCobro) {
		IdFormaCobro = idFormaCobro;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+nFactura+"' , ";
		valores+=this.IdCliente+" , ";
		valores+=this.IdFormaCobro+" , ";
		valores+="'"+this.fecha+"' , ";
		valores+="'"+this.fechaCobro+"' , ";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Update("facturasclientes", valores, this.IdFactCli);
		
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("facturasclientes", this.IdFactCli);
		
	}
	
	
	public FacturasClientesC()
	{
		IdFactCli="0";
		nFactura="sin numero";
		IdCliente="";
		formaCobro="";
		observaciones="";
		IdFormaCobro="";
		
	}
}