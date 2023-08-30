//package Ventana;

public class DVD extends ItemsCatalogo implements Elementos{
	private String director;
	private int i;
	
	
	public DVD(String titulo, String genero, String comentario, int minutos, boolean tenemos, String director) {
		super(titulo, genero, comentario, minutos, tenemos);
		this.director=director;
	}
	
	

	@Override
	public String getID() {return director;}
	
	@Override
	public void setID(String s) {this.director=s;}


	
	
	
	
		
}