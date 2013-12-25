package Clases;

public class GrupoConcepto {
	
	private int Id;
	private String grupo;
	
	public GrupoConcepto()
	{
		Id=0;
		grupo="Especifique grupo";
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString()
	{
		return this.grupo;
	}
}
