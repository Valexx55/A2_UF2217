
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class MainLibro
{
	
	private final static String cadena_conexion = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String user = "HR";
	private final static String password = "password";
	
	
		
	private static Map <String, Libro> componerInfoLibro (ResultSet rs)
	{
		Map <String, Libro> libros = null;
		
			try 
			{
				Blob blob = rs.getBlob("libro");
				
				ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());
				
				libros = (Map <String, Libro>) ois.readObject();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		
		return libros;
		
	}
	
	
	public static void main(String[] args) throws SAXException
	{		
		System.out.println ("*** HashMap con Objetos y como Clave un String ***");
		Map <String, Libro> mapa_libros = new HashMap <String, Libro>();
			
		mapa_libros.put("El Ocho", new Libro("978-84-8346-520-2", "El Ocho", "Katherine Neville", "1988", "Ballantine Books"));
		mapa_libros.put("Qumran", new Libro("84-226-6765-7", "Qumran", "Eliette Abecasis", "1997", "Ediciones B"));
		mapa_libros.put("Memorias de Idhun I - La Resistencia", new Libro("84-675-069-X", "Memorias de Idhun I - La Resistencia", "Laura Gallego García", "2005", "Ediciones SM"));
	
		Connection conn = null;
		Statement st = null;	
		ResultSet rs = null;
		try
		{
			/**
			 * LEER LIBROS - DESERIALIZAR
			 */
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());// método equivalente al anterior
			conn = DriverManager.getConnection (cadena_conexion, user, password);
			String consulta = "SELECT libro FROM LIBRO";
			st = conn.createStatement();
			rs = st.executeQuery(consulta);
			while (rs.next())
			{
			mapa_libros = componerInfoLibro(rs);
			}
			
			Iterator <String> it = mapa_libros.keySet().iterator();
			while (it.hasNext())
			{
				String straux = it.next();
				Libro l = mapa_libros.get(straux);
				System.out.println(l.toString());
			}
			
			/**
			 * ESCRIBIR LIBROS - SERIALIZAR
			 */
			
			/*
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(libros);
			
			PreparedStatement pst = null;
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());// método equivalente al anterior
			//Sea como sea, es, <<oye, si te piden una conexión, se la pides a esa clase!>>
			conn = DriverManager.getConnection (cadena_conexion, user, password);
			pst = conn.prepareStatement("INSERT INTO LIBRO (libro) VALUES (?)");
			
			pst.setBytes(1, baos.toByteArray());
			pst.execute();*/
		} 
			catch (Throwable e)
		{
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				if (rs != null) rs.close();
				if (st != null) st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
									} 
}
