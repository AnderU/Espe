package Clases;

public class ComboC {
		private String Id, nombre;

		@Override
		public String toString() {
			return  nombre ;
		}

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
		
		public ComboC()
		{
			Id="0";
			nombre="Especifique un elemento";
		}
}
