import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Rectangle;

public class Eliminacion extends JPanel {
	
	JTextArea textoTituloElim;
	JButton botonEliminacion;

	public Eliminacion(Catalogo catalogo, Agregar a) {
		

		setLayout(null);
		
		iniciarComponentes();
		
		
		botonEliminacion.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String title = textoTituloElim.getText().toString();
				int i = catalogo.buscarTitulo(title);
				if( i == 1) {
					try {
						a.eliminarItemDvd(title, catalogo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Eliminado :)  \n" + title);}
					else if(i == 2) {
						try {
							a.eliminarItemCd(title, catalogo);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Eliminado :)  \n" + title);
					}
					else {
						JOptionPane.showMessageDialog(null, "El titulo escrito no se encuentra en la base de datos");
						}
				textoTituloElim.setText("");
			}
			
		});
		
	}

	
	private void iniciarComponentes() {
			JLabel label = new JLabel("Eliminar productos:  ");
			label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 24));
			label.setBounds(10, 28, 319, 45);
			add(label);
			
			JLabel label2 = new JLabel("Titulo:  ");
			label2.setBounds(490, 131, 100, 25);
			add(label2);
			
			textoTituloElim = new JTextArea();
			textoTituloElim.setBounds(490, 164, 100, 25);
			textoTituloElim.setBackground(Color.LIGHT_GRAY);
			add(textoTituloElim);
			
			botonEliminacion = new JButton ("ELIMINAR");
			botonEliminacion.setBounds(466, 253, 200, 30);
			botonEliminacion.setBackground(Color.DARK_GRAY);
			botonEliminacion.setForeground(Color.WHITE);
			add(botonEliminacion);
		
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Nacho\\Pictures\\Camera Roll\\fotoElim.jpg"));
			lblNewLabel.setBounds(804, 11, 482, 465);
			add(lblNewLabel);
		
			
		
	}
}
