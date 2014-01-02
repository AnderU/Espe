package Clases;

import BaseDatos.ConectorBD;

public class ClienteC implements BaseDatos{
	private int Id, IdPoblacion;
	private String cliente, direccion, telefono1, telefono2, correo;
	
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
		cliente="";
		IdPoblacion=0;
		correo="";
		direccion="";
		telefono1="";
		telefono2="";
		
	}
	
	public int getIdPoblacion() {
		return IdPoblacion;
	}

	public void setIdPoblacion(int IdPoblacion) {
		this.IdPoblacion = IdPoblacion;
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
	public void Insert() {
		// TODO Auto-generated method stub
		String valores="'"+cliente+"'";
		ConectorBD.bdMySQL.Insert("cliente",valores);
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+cliente+"'";
		ConectorBD.bdMySQL.Update("cliente", valores, Integer.toString(this.Id));
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("cliente", Integer.toString(this.Id));
	}

}
