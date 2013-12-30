package Clases;

import BaseDatos.ConectorBD;

public class ProveedorC implements BaseDatos{
	private int Id, IdPoblacion;
	private String proveedor;
	
	public ProveedorC()
	{
		Id=0;
		proveedor="";
		IdPoblacion=0;
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

}
