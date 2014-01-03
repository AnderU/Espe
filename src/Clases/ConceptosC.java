package Clases;

import BaseDatos.ConectorBD;



public class ConceptosC implements BaseDatos{

	private int Id;
	private String concepto;
	private String patron;
	private int IdTipo;
	private int IdGrupo;
	private String Tipo;
	private String Grupo;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getPatron() {
		return patron;
	}

	public void setPatron(String patron) {
		this.patron = patron;
	}

	public int getIdTipo() {
		return IdTipo;
	}

	public void setIdTipo(int idTipo) {
		IdTipo = idTipo;
	}

	public int getIdGrupo() {
		return IdGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		IdGrupo = idGrupo;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getGrupo() {
		return Grupo;
	}

	public void setGrupo(String grupo) {
		Grupo = grupo;
	}


	




	

	@Override
	public void Insert() {
		// TODO Auto-generated method stub
		String valores="'"+this.concepto.toUpperCase()+"' , ";
		valores+="'"+this.patron.toUpperCase()+"' , ";
		valores+=Integer.toString(this.IdTipo)+" , ";
		valores+=Integer.toString(this.IdGrupo);
		ConectorBD.bdMySQL.Insert("conceptos", valores);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		String valores="'"+this.concepto.toUpperCase()+"' , ";
		valores+="'"+this.patron.toUpperCase()+"' , ";
		valores+=Integer.toString(this.IdTipo)+" , ";
		valores+=Integer.toString(this.IdGrupo);
		ConectorBD.bdMySQL.Update("conceptos", valores, Integer.toString(this.Id));
	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		ConectorBD.bdMySQL.Delete("conceptos", Integer.toString(this.Id));
	}
	

}
