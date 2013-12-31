package Clases;

import BaseDatos.ConectorBD;

public class ClienteC implements BaseDatos{
	private int Id, IdPoblacion;
	private String cliente;
	
	public ClienteC()
	{
		Id=0;
		cliente="";
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
