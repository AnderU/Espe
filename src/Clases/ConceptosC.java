package Clases;

public class ConceptosC implements BaseDatos{

	private int Id;
	private String concepto;
	private int IdTipo;
	private int IdGrupo;
	
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

	@Override
	public void Insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		
	}

}
