//package Ventana;

public abstract class ItemsCatalogo  implements Comparable<ItemsCatalogo>{
	private String titulo;
	private String genero;
	private String comentario;
	private boolean tenemos;
	private int minutos;
	
	public ItemsCatalogo(String titulo, String genero, String comentario, int minutos, boolean tenemos) {
		this.titulo = titulo;
		this.genero=genero;
		this.comentario=comentario;
		this.tenemos= tenemos;
		this.minutos = minutos;
	}
	
	
		public abstract String getID();
		
		public String getGenero() {return genero;}
		
		public String getTitulo() {return titulo;}
		
		public String getComentario() {return comentario;}
		
		public boolean getTenemos () {return tenemos;}
		
		public int getMinutos() {return (minutos);}
		
		public abstract void setID(String s);
		
		public void setTitulo(String t) {titulo = t;}
		
		public void setGenero(String g) {genero = g;}
		
		public void setTenemos(boolean tenemos) {this.tenemos = tenemos;}
		
		public void setComentario(String c) {comentario = c;}
		
		public void setMinutos (int m) { minutos = m;}

		
		public int compareTo(ItemsCatalogo c) {
			return this.titulo.compareTo(c.titulo);
		}
	
}
