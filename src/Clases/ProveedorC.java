package Clases;

import BaseDatos.ConectorBD;

public class ProveedorC implements BaseDatos{
	private int Id;
	private String IdPoblacion,proveedor,direccion, telefono1, telefono2, correo;
	
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

	public ProveedorC()
	{
		Id=0;
		proveedor="";
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
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	@Override
	public void Insert() {
		// TODO Auto-generated method stub
		String valores="'"+proveedor+"'";
		ConectorBD.bdMySQL.Insert("proveedor",valores);
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+proveedor+"'";
		ConectorBD.bdMySQL.Update("proveedor", valores, Integer.toString(this.Id));
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("proveedor", Integer.toString(this.Id));
	}

	public String getIdPoblacion() {
		return IdPoblacion;
	}

	public void setIdPoblacion(String idPoblacion) {
		IdPoblacion = idPoblacion;
	}

}
