package Clases;

import BaseDatos.ConectorBD;

public class ConfiguracionC implements BaseDatos{

	private String Id, nombre , valor;
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public void Insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+this.nombre+"' , ";
		valores+=this.valor;
		ConectorBD.bdMySQL.Update("configuracion", valores, this.Id);
	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		
	}

}