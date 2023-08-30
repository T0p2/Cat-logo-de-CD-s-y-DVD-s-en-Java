import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


//package Ventana;
import javax.swing.*;

public class PestañaPrincipal extends JFrame{

	 Catalogo c;
	
	 JTabbedPane pestaña ;
	
	 Agregar panelProd;
	 Modificacion panelModi;
	 Eliminacion panelElim;
	 
	 JPanel panel;
	
	public PestañaPrincipal(Catalogo c ) throws SQLException {
	
		ImageIcon image  = new ImageIcon ("file:///C:/Users/usuario/eclipse-workspace/Proyecto2/src/OIP.jpeg");
		
		
		
		panelProd = new Agregar(c);
		panelElim = new Eliminacion(c, panelProd);
		panelModi = new Modificacion(c, panelProd);
		
		pestaña = new JTabbedPane();
		
		
		
		pestaña.addTab("Agregar/ Mostrar productos", panelProd);
		pestaña.addTab("Modificacion productos" , panelModi);
		pestaña.addTab("Eliminacion productos", panelElim );
		
		add(pestaña);
		
		
		
		
		
		
	}
}