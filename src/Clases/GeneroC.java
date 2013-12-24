package Clases;


import BaseDatos.ConectorBD;



public class GeneroC implements BaseDatos{
	
	private int Id;
	private String genero;
	
	public GeneroC()
	{
		Id=0;
		genero="";
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	@Override
	public void Insert() {
		// TODO Auto-generated method stub
		String valores="'"+genero+"'";
		ConectorBD.bdMySQL.Insert("genero",valores);
		
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+genero+"'";
		ConectorBD.bdMySQL.Update("genero", valores, Integer.toString(this.Id));
	}
	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("genero", Integer.toString(this.Id));
	}

}
