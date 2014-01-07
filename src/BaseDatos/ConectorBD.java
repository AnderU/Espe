package BaseDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;

import javax.swing.JOptionPane;

import Clases.ConfiguracionConexion;

public class ConectorBD {
	
	public  static ConectorBD bdMySQL;
	public  static ConectorBD bdAccess;
	
	private Connection conexion;
	private Statement st;
	private Statement st1;
	private Statement st2;
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
	public ResultSet SelectAux1(String tabla ,String consulta , String condicion)
	{
		
		try {
			rs = st2.executeQuery("Select "+consulta+" from "+tabla+" WHERE "+condicion);
			return rs;
		} catch (SQLException e) {
			
		}
		return null;

	}
	
	public int  Insert(String tabla , String valores)
	{
		
		String estructura=this.GetEstructura(tabla);
		int id=0;
		ResultSet rs=null;
		try {
			
			st.executeUpdate("INSERT INTO "+tabla +" ( "+estructura+" ) VALUES "+"( "+valores+" )", Statement.RETURN_GENERATED_KEYS);
			rs=st.getGeneratedKeys();
			if (null != rs && rs.next()) {
			     id =(int) rs.getLong(1);
			}
		} catch (SQLException e) {
			
		}
		return id;

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
			
			ConfiguracionConexion aux = null;
			try
			{
				FileInputStream fic= new FileInputStream("cbd.conf");
				ObjectInputStream lector= new ObjectInputStream(fic);
				
				aux=(ConfiguracionConexion) lector.readObject();

				
			}
			catch(FileNotFoundException e)
			{
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String puerto="";
			if (!aux.getPuerto().equals(""))
			{
				puerto=":"+aux.getPuerto();
			}
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://"+aux.getServer()+puerto+"/"+aux.getDbName(),aux.getUser(),aux.getPassword());
			st= conexion.createStatement();
			st1= conexion.createStatement();
			st2= conexion.createStatement();
			meta=conexion.getMetaData();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Por favor configure la conexión a la base de datos, en el apartado configuración de la herramienta");
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
