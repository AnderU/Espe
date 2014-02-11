package Clases;

import BaseDatos.ConectorBD;

public class ClienteC implements BaseDatos{
	private int Id;
	private String IdPoblacion,cliente,direccion, telefono1, telefono2, correo, fax, web, cp, IdProvincia, nif, cuentaCorriente, banco, observaciones;
	
	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(String cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public ClienteC()
	{
		Id=0;
		cliente="Especifique cliente";
		setIdPoblacion("");
		correo="";
		direccion="";
		telefono1="";
		telefono2="";
	}
	


	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public int Insert() {
		// TODO Auto-generated method stub
		String valores="'"+this.cliente+"' , ";
		valores+=this.IdPoblacion+" , ";
		valores+="'"+this.direccion+"' , ";
		valores+="'"+this.telefono1+"' , ";
		valores+="'"+this.telefono2+"' , ";
		valores+="'"+this.correo+"' ,";
		valores+="'"+this.fax+"' , ";
		valores+="'"+this.web+"' , ";
		valores+="'"+this.cp+"' ,  ";
		valores+=this.IdProvincia+" , ";
		valores+="'"+this.nif+"' , ";
		valores+="'"+this.cuentaCorriente+"' , ";
		valores+="'"+this.banco+"' ,";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Insert("clientes",valores);
		return 0;
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+this.cliente+"' , ";
		valores+=this.IdPoblacion+" , ";
		valores+="'"+this.direccion+"' , ";
		valores+="'"+this.telefono1+"' , ";
		valores+="'"+this.telefono2+"' , ";
		valores+="'"+this.correo+"' ,";
		valores+="'"+this.fax+"' , ";
		valores+="'"+this.web+"' , ";
		valores+="'"+this.cp+"' ,  ";
		valores+=this.IdProvincia+" , ";
		valores+="'"+this.nif+"' , ";
		valores+="'"+this.cuentaCorriente+"' , ";
		valores+="'"+this.banco+"' ,";
		valores+="'"+this.observaciones+"'";
		ConectorBD.bdMySQL.Update("clientes", valores, Integer.toString(this.Id));
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("clientes", Integer.toString(this.Id));
	}

	public String getIdPoblacion() {
		return IdPoblacion;
	}

	public void setIdPoblacion(String idPoblacion) {
		IdPoblacion = idPoblacion;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIdProvincia() {
		return IdProvincia;
	}

	public void setIdProvincia(String idProvincia) {
		IdProvincia = idProvincia;
	}
	
	public String toString()
	{
		return this.cliente;
	}

}