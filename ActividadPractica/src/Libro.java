import java.io.Serializable;

public class Libro implements Serializable
{	
	/**
	 * @param isbn 
	 * @param titulo
	 * @param autor
	 * @param anyo
	 * @param editorial	 */
	
	public Libro (String isbn, String titulo, String autor, String anyo, String editorial)
	{
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.anyo = anyo;
		this.editorial = editorial;
	}
	
	private String isbn;
	private String titulo;
	private String autor;
	private String anyo;
	private String editorial;
	
	public String getIsbn()
	{
		return isbn;
	}
	
	public void setIsbn (String isbn)
	{
		this.isbn = isbn;
	}
	
	public String getTitulo()
	{
		return titulo;
	}
	
	public void setTitulo (String titulo)
	{
		this.titulo = titulo;
	}
	
	public String getAutor()
	{
		return autor;
	}
	
	public void setAutor (String autor)
	{
		this.autor = autor;
	}
	
	public String getAnyo()
	{
		return anyo;
	}
	
	public void setAnyo (String anyo)
	{
		this.anyo = anyo;
	}
	
	public String getEditorial()
	{
		return editorial;
	}
	
	public void setEditorial (String editorial)
	{
		this.editorial = editorial;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.titulo + "  " + this.isbn;
	}
}
