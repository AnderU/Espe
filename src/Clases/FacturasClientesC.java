package Clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import BaseDatos.ConectorBD;

public class FacturasClientesC implements BaseDatos{

	private String IdFactCli, nFactura, IdCliente, formaCobro, observaciones, fecha, fechaCobro,IdFormaCobro, iva, cliente , total;
	
	public String formateaFecha(String fecha)
	{
		String ano=fecha.substring(6, 10);
		String mes=fecha.substring(3, 5);
		String dia=fecha.substring(0, 2);
		return ano+"-"+mes+"-"+dia;
	}
	
	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		String valores="'"+nFactura+"' ||| ";
		valores+=this.IdFormaCobro+" ||| ";
		valores+=this.IdCliente+" ||| ";
		valores+="'"+formateaFecha(fecha)+"' ||| ";
		if (!this.fechaCobro.equals("NULL"))
		{
			valores+="'"+formateaFecha(this.fechaCobro)+"' ||| ";
		}
		else
			valores+=this.fechaCobro+" ||| ";
		valores+=this.iva+" ||| ";
		valores+="'"+this.observaciones+"'";
		
		return ConectorBD.bdMySQL.Insert("facturasclientes",valores);
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
		String valores="'"+nFactura+"' ||| ";
		valores+=this.IdFormaCobro+" ||| ";
		valores+=this.IdCliente+" ||| ";
		valores+="'"+formateaFecha(fecha)+"' ||| ";
		if (!this.fechaCobro.equals("NULL"))
		{
			valores+="'"+formateaFecha(this.fechaCobro)+"' ||| ";
		}
		else
			valores+=this.fechaCobro+" ||| ";
		valores+=this.iva+" ||| ";
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
		this.fecha="NULL";
		this.fechaCobro="NULL";
		
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
}