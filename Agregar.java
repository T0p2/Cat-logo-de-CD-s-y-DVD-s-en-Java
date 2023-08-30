


import javax.swing.*;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

//import javax.*;

import java.math.*;

public class Agregar extends JPanel{
	
	JButton boton;
	JRadioButton boton1;
	JRadioButton boton2;
	JRadioButton botonTenemos0;
	JRadioButton botonTenemos1;
	
	ButtonGroup grupo;
	ButtonGroup grupoDeBotones;
	

	Font font = new Font("Tahoma", Font.BOLD, 14);
	Font font1 = new Font("Pacifico", Font.PLAIN, 24);
	
	JTextArea textoTitulo;
	JTextArea textoDir_int;
	JTextArea textoGenero;
	JTextArea textoMinutos;
	JTextArea textoComentario;

	JLabel labelImgDvd;
	
	JTextField textDvd = new JTextField();
	JTextField textCd = new JTextField();
	
	JComboBox<String> listaDvd;
	JComboBox<String> listaCd;
	private JLabel lblNewLabel_1;
	


	public Agregar (Catalogo catalogo) throws SQLException {
	
		setSize(1530, 999);
		setLayout(null);
		
		
		iniciarComponentes();
		listaProductos(catalogo);
	
		
		labelImgDvd = new JLabel("");
		labelImgDvd.setBackground(Color.BLACK);
		labelImgDvd.setHorizontalAlignment(SwingConstants.CENTER);	
		labelImgDvd.setIcon(new ImageIcon());
		labelImgDvd.setBounds(28, 400, 130, 200);
		add(labelImgDvd);
		
		JButton botonActualizar = new JButton("Actualizar");
		botonActualizar.setForeground(Color.WHITE);
		botonActualizar.setBackground(Color.DARK_GRAY);
		botonActualizar.setBounds(1044, 404, 138, 23);
		add(botonActualizar);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Nacho\\Pictures\\Camera Roll\\24-04-20-digital.jpg"));
		lblNewLabel_1.setBounds(1303, 17, 578, 623);
		add(lblNewLabel_1);
		
		botonActualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					actualizarListas();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
			
		});
		
		
		
	
		boton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if (boton1.isSelected()) {
					catalogo.agregarPelibbd(getTitulo(), getDir_Int() , getGenero(), getMinutos(),getTenemos(), getComentario());
				}else if (boton2.isSelected()) {
					catalogo.agregarTemabbd(getTitulo(), getDir_Int() , getGenero(), getMinutos(),getTenemos(), getComentario());
				}
				guardarTexto(catalogo);
				grupo.clearSelection();
				grupoDeBotones.clearSelection();
				
			}});
		
		
		
		
		
	listaDvd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					listaDvd = (JComboBox<String>) e.getSource();
					String selectedTitle = (String) listaDvd.getSelectedItem();
					if(selectedTitle != null) {
					if(catalogo.buscarTitulo(selectedTitle) != -1) {
						String dir_int = catalogo.getDirectorPeli(selectedTitle);
						String comentario = catalogo.getComentarioPeli(selectedTitle);
						String genero = catalogo.getGeneroPeli(selectedTitle);
						int minutos = catalogo.getMinutosPeli(selectedTitle);
						textDvd.setText("");
					colocarTextFieldAtributosDvd(dir_int, genero, comentario, minutos);
					
					}
					
					}}
		});
		
		
		listaCd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					listaCd = (JComboBox<String>) e.getSource();
					String selectedTitle = (String) listaCd.getSelectedItem();
					if(selectedTitle != null) {
					if(catalogo.buscarTitulo(selectedTitle) != -1) {
						String dir_int = catalogo.getDirectorTema(selectedTitle);
						String comentario = catalogo.getComentarioTema(selectedTitle);
						String genero = catalogo.getGeneroTema(selectedTitle);
						int minutos = catalogo.getMinutosTema(selectedTitle);
						textDvd.setText("");
					colocarTextFieldAtributosCd(dir_int, genero, comentario, minutos);
					
					
					}
					}
					}
		});
		
		
		
	
	}
	
	private void guardarTexto(Catalogo c) {
		String item = textoTitulo.getText().toString();
		if(boton1.isSelected()) {
			listaDvd.addItem(item);}
		if(boton2.isSelected()) {
			listaCd.addItem(item);}
		textoTitulo.setText("");
		textoDir_int.setText("");
		textoGenero.setText("");
		textoMinutos.setText("");
		textoComentario.setText("");
		JOptionPane.showMessageDialog(this, "Producto guardado: " + item);
		}
	
	private void iniciarComponentes() {
		
//Lables: 
		
		
		JLabel label = new JLabel("Agrega tus productos: ");
		label.setBounds(25, 0, 500, 30);
		label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 24));
		add(label);
		
		JLabel label2 = new JLabel("Titulo:  ");
		label2.setBounds(175, 118, 100, 25);
		add(label2);
		
		JLabel label3 = new JLabel("Director/ Interprete: ");
		label3.setBounds(375, 118, 150, 25);
		add(label3);
		
		JLabel label4 = new JLabel("Genero: ");
		label4.setBounds(575, 118, 150, 25);
		add(label4);
		
		JLabel label5 = new JLabel("Minutos: ");
		label5.setBounds(770, 118, 150, 25);
		add(label5);
		
		JLabel label6 = new JLabel("Adquirir:");
		label6.setBounds(946, 118, 150, 25);
		add(label6);
		
		JLabel label7 = new JLabel("Comentarios: ");
		label7.setBounds(1090, 118, 150, 25);
		add(label7);
		
		
		JLabel lblNewLabel = new JLabel("Peliculas");
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblNewLabel.setBounds(248, 372, 100, 30);
		add(lblNewLabel);
		
		JLabel lblTemas = new JLabel("Temas");
		lblTemas.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblTemas.setBounds(720, 372, 100, 30);
		add(lblTemas);
		
		
		
//Textos: 
		
		
		textoTitulo = new JTextArea();
		textoTitulo.setBounds(175, 154, 100, 25);
		textoTitulo.setBackground(Color.LIGHT_GRAY);
		add(textoTitulo);
		
		textoDir_int = new JTextArea();
		textoDir_int.setBounds(375, 154, 100, 25);
		textoDir_int.setBackground(Color.LIGHT_GRAY);
		add(textoDir_int);
	
		textoGenero = new JTextArea();
		textoGenero.setBounds(575, 154, 100, 25);
		textoGenero.setBackground(Color.LIGHT_GRAY);
		add(textoGenero);
		
		textoMinutos = new JTextArea();
		textoMinutos.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				if (!(letra <= '9' && letra >= '0'))
					e.consume();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		}
		);
		textoMinutos.setBounds(770, 154, 100, 25);
		textoMinutos.setBackground(Color.LIGHT_GRAY);
		add(textoMinutos);
		
		textoComentario = new JTextArea();
		textoComentario.setBounds(1090, 154, 100, 25);
		textoComentario.setBackground(Color.LIGHT_GRAY);
		add(textoComentario);
		
//Botones
		
		boton = new JButton("AGREGAR");
		boton.setBounds(439, 250, 200, 30);
		boton.setBackground(Color.DARK_GRAY);
		boton.setForeground(Color.WHITE);
		add(boton);
		
		boton1 = new JRadioButton("Pelicula");
		boton1.setBounds(6, 118, 75, 30);
		boton1.setBackground(SystemColor.controlHighlight);
		add(boton1);
		

		boton2 = new JRadioButton("Tema");
		boton2.setBounds(6, 151, 75, 30);
		boton2.setBackground(SystemColor.controlHighlight);
		add(boton2);
	
		 grupoDeBotones = new ButtonGroup();
		grupoDeBotones.add(boton1);
		grupoDeBotones.add(boton2);
		
		 botonTenemos0 = new JRadioButton("Si");
		botonTenemos0.setBounds(946, 150, 109, 16);
		add(botonTenemos0);
		
		 botonTenemos1 = new JRadioButton("No");
		botonTenemos1.setBounds(946, 169, 109, 16);
		add(botonTenemos1);
		
		 grupo = new ButtonGroup();
		grupo.add(botonTenemos0);
		grupo.add(botonTenemos1);
		
		
		
	
	}
	
	public void listaProductos(Catalogo c) throws SQLException{ 
		listaDvd = new JComboBox();
		listaCd = new JComboBox();
			java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalogos", "root", "");
			String sql = "SELECT titulo FROM catalogo JOIN peliculas ON catalogo.idPeli = peliculas.idPeli;";
			java.sql.PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			String sql1 = "SELECT titulo FROM catalogo JOIN temas ON catalogo.idTema = temas.idTema;";
			java.sql.PreparedStatement sentencia1 = conexion.prepareStatement(sql1);
			
			ResultSet resultadoPeli = sentencia.executeQuery();
			ResultSet resultadoTema = sentencia1.executeQuery();
			
			while(resultadoPeli.next()) {
				listaDvd.addItem(resultadoPeli.getString("titulo"));
			}
			
			
			while(resultadoTema.next()) {
				listaCd.addItem(resultadoTema.getString("titulo"));
			}
			
			
		listaDvd.setBounds(248, 400, 150, 30);
		listaCd.setBounds(720, 400, 150, 30);
		add(listaDvd);
		add(listaCd);
		
		JLabel lblTusProductos = new JLabel("Tus productos:");
		lblTusProductos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 24));
		lblTusProductos.setBounds(25, 309, 331, 30);
		add(lblTusProductos);
	}
	
	
	private void colocarTextFieldAtributosCd (String dir_int ,String genero, String comentario, int minutos) {
		
		textCd = new JTextField("Interprete: " + dir_int);
		textCd.setBounds(720, 450, 200, 20);
		textCd.setBackground(Color.LIGHT_GRAY);
		textCd.setFont(font);
		textCd.setEditable(false);
		add(textCd);
		
		JTextField textoGenero = new JTextField("Genero: " + genero);
		textoGenero.setBounds(720, 500, 200, 20);
		textoGenero.setBackground(Color.LIGHT_GRAY);
		textoGenero.setEditable(false);
		textoGenero.setFont(font);
		add(textoGenero);
		
		JTextField textoComentario = new JTextField("Comentario: " + comentario);
		textoComentario.setBounds(720, 550, 200, 20);
		textoComentario.setEditable(false);
		textoComentario.setBackground(Color.LIGHT_GRAY);
		textoComentario.setFont(font);
		add(textoComentario);
		
		JTextField textoMinutos = new JTextField("Minutos : " + minutos);
		textoMinutos.setBounds(720, 600, 200, 20);
		textoMinutos.setEditable(false);
		textoMinutos.setBackground(Color.LIGHT_GRAY);
		textoMinutos.setFont(font);
		add(textoMinutos);
	}
		
	
	private void colocarTextFieldAtributosDvd(String dir_int, String genero, String comentario, int minutos) {
		
		textDvd = new JTextField("Director: " + dir_int);
		textDvd.setBounds(248, 450, 200, 20);
		textDvd.setBackground(Color.LIGHT_GRAY);
		textDvd.setFont(font);
		textDvd.setEditable(false);
		add(textDvd);
		
		JTextField textoGenero0 = new JTextField("Genero: " + genero);
		textoGenero0.setBounds(248, 500, 200, 20);
		textoGenero0.setBackground(Color.LIGHT_GRAY);
		textoGenero0.setEditable(false);
		textoGenero0.setFont(font);
		add(textoGenero0);
		
		JTextField textoComentario0 = new JTextField("Comentario: " + comentario);
		textoComentario0.setBounds(248,550, 200, 20);
		textoComentario0.setEditable(false);
		textoComentario0.setBackground(Color.LIGHT_GRAY);
		textoComentario0.setFont(font);
		add(textoComentario0);
		
		JTextField textoMinutos0 = new JTextField("Minutos : " + minutos);
		textoMinutos0.setBounds(248, 600, 200, 20);
		textoMinutos0.setEditable(false);
		textoMinutos0.setBackground(Color.LIGHT_GRAY);
		textoMinutos0.setFont(font);
		add(textoMinutos0);
	}
	
	
	
	private String getTitulo() {return (textoTitulo.getText().toString());}
	private String getDir_Int() {return (textoDir_int.getText().toString());}
	private String getGenero() {return(textoGenero.getText().toString());}
	private int getMinutos() {return Integer.parseInt((textoMinutos.getText()));}
	private String getComentario() {return textoComentario.getText();	}
	
	
	
	private boolean getTenemos() {
		if(botonTenemos0.isSelected()) {
			return (false);
		}else if (botonTenemos1.isSelected()) {
			return(true);
		}else {
			JOptionPane.showMessageDialog(null, "Error en la seleccion de 'Tenemos' debe seleccionar uno");
		}
		return false;
	}
	
	
	
	
	public void eliminarItemDvd(String title, Catalogo c) throws SQLException {
		listaDvd.removeItem(title);
		c.eliminarPeliculas(title);
	}
	public void eliminarItemCd(String title, Catalogo c) throws SQLException {
		listaCd.removeItem(title);
		c.eliminarTemas(title);}



public void actualizarListas() throws SQLException {
	java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalogos", "root", "");
	 try {
  
         String sqlDVD = "SELECT titulo FROM peliculas";
         java.sql.PreparedStatement statementDVD = conexion.prepareStatement(sqlDVD);
         ResultSet resultSetDVD = statementDVD.executeQuery();


         listaDvd.removeAllItems();

 
         while (resultSetDVD.next()) {
             String titulo = resultSetDVD.getString("titulo");
             listaDvd.addItem(titulo);
         }
       
         String sqlCD = "SELECT titulo FROM temas ";
         java.sql.PreparedStatement statementCD = conexion.prepareStatement(sqlCD);
         ResultSet resultSetCD = statementCD.executeQuery();

         listaCd.removeAllItems();
         while (resultSetCD.next()) {
             String titulo = resultSetCD.getString("titulo");
             listaCd.addItem(titulo);
         }

         System.out.println("Datos cargados desde la base de datos");

     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
}	


