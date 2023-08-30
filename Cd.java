//package Ventana;

public class Cd extends ItemsCatalogo implements Elementos{
	
	private String interprete;
	
	public Cd(String titulo, String genero, String comentario, int minutos, boolean tenemos, String interprete) {
		super(titulo, genero, comentario, minutos, tenemos);
		this.interprete = interprete;
	}
	
	
	@Override
	public String getID() {return interprete;}
	
	@Override
	public void setID(String s) {this.interprete=s;}

	

}