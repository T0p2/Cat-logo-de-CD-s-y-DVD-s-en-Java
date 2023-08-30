//package Ventana;

import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JFrame;

import javax.swing.*;


public class Main  {

	
	
	public static void main(String[] args) throws SQLException {
		
		
		Catalogo catalogo = new Catalogo();


		PestañaPrincipal pestaña= new PestañaPrincipal(catalogo);
		pestaña.setSize(1700, 1000);
		pestaña.setVisible(true);
		
		
	}

}
