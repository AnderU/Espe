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
	public int Insert() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+this.nombre+"' ||| ";
		this.valor=this.valor.replace("\\", "\\\\");
		valores+=" ' "+this.valor+" ' ";
		ConectorBD.bdMySQL.Update("configuracion", valores, this.Id);
	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		
	}

}
