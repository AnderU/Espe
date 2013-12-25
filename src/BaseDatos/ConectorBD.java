package BaseDatos;

import java.sql.*;

public class ConectorBD {
	
	public  static ConectorBD bdMySQL;
	public  static ConectorBD bdAccess;
	
	private Connection conexion;
	private Statement st;
	private Statement st1;
	private ResultSet rs;
	private DatabaseMetaData meta;
	private ResultSet columns;
	
	public String GetEstructura(String tabla)
	{
		String estructura="";
		try {
			this.columns=meta.getColumns(null, null, tabla, null);
			while (columns.next())
			{
				if (!columns.getString("COLUMN_NAME").equals("Id"))
				{
					estructura+=columns.getString("COLUMN_NAME");
					if (!columns.isLast())
					{
						estructura+=" , ";
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return estructura;
	}
	
	public ResultSet Select(String tabla ,String consulta , String condicion)
	{
		
		try {
			rs = st.executeQuery("Select "+consulta+" from "+tabla+" WHERE "+condicion);
			return rs;
		} catch (SQLException e) {
			
		}
		return null;

	}
	
	public ResultSet SelectAux(String tabla ,String consulta , String condicion)
	{
		
		try {
			rs = st1.executeQuery("Select "+consulta+" from "+tabla+" WHERE "+condicion);
			return rs;
		} catch (SQLException e) {
			
		}
		return null;

	}
	
	public void  Insert(String tabla , String valores)
	{
		
		String estructura=this.GetEstructura(tabla);
		try {
			
			st.executeUpdate("INSERT INTO "+tabla +" ( "+estructura+" ) VALUES "+"( "+valores+" )");
			
		} catch (SQLException e) {
			
		}
		

	}
	public void  Update(String tabla , String valores, String Id)
	{
		
		String estructura=this.GetEstructura(tabla);
		String[] es1= estructura.split(",");
		String[] es2= valores.split(",");
		estructura="";
		for (int i=0; i<es1.length; i++)
		{
			estructura+=es1[i]+"="+es2[i];
			if (i!=(es1.length-1))
			{
				estructura+=" , ";
			}
		}
		
		try {

			st.executeUpdate("UPDATE "+tabla+" SET "+estructura+" WHERE Id="+Id);
			
		} catch (SQLException e) {
			
		}
		

	}
	
	public void  Delete(String tabla , String Id)
	{
		
		try {

			st.executeUpdate("DELETE FROM "+tabla+" WHERE Id="+Id);
			
		} catch (SQLException e) {
			
		}
		

	}
	
	
	public void ConectarMysql(String db)
	{
		try {
			  
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/"+db,"root","espe");
			st= conexion.createStatement();
			st1= conexion.createStatement();
			meta=conexion.getMetaData();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void ConectarAccess()
	{
		try {
			 
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};"  + "DBQ=C:/espedb.accdb","","");
			st= conexion.createStatement();
			st1= conexion.createStatement();
			meta=conexion.getMetaData();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
