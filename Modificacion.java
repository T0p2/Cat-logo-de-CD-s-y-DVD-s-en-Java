import javax.swing.JPanel;


	
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JTabbedPane;
	import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

	public class Modificacion extends JPanel{
		JButton botonModif;
		
		JTextArea textoTitulo1;
		JTextArea textoMinutos1;
		JTextArea textoComentario1;
		
		JTextArea textoAtributo;
		JTextArea atributoDir_Int;
		JTextArea atributoComentario;
		
		JPanel panel;
		
		
		public Modificacion (Catalogo c, Agregar a ) throws SQLException {
			setForeground(Color.GRAY);
			
			
			setLayout(null);
			
			iniciarComponentes();
			
			
			botonModif.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					String title = textoTitulo1.getText().toString();
					System.out.println(title);
					int i = c.buscarTitulo(title);
					if( i == 1 || i == 2) {
						try {
							c.modifcarAtrib(title, textoComentario1.getText().toString(), Integer.parseInt(textoMinutos1.getText()));
						
						
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, "Modificado :)");
					}else {
						JOptionPane.showMessageDialog(null, "No existe el titulo ");
					}
					textoTitulo1.setText("");
					textoComentario1.setText("");
					textoMinutos1.setText("");
				}
			});
			
	
		}
		
		
		
		
		private void iniciarComponentes(){
			
		//Labels: 
			
			JLabel label8 = new JLabel("Modificar Atributos: ");
			label8.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 24));
			//label8.setFont(font);
			label8.setBounds(56, 23, 345, 72);
			add(label8);
			
			JLabel label9 = new JLabel("Titulo:  ");
			label9.setFont(new Font("Sitka Text", Font.PLAIN, 13));
			label9.setBounds(85, 165, 58, 25);
			add(label9);
			
			JLabel label10 = new JLabel("Minutos: ");
			label10.setFont(new Font("Sitka Text", Font.PLAIN, 13));
			label10.setBounds(559, 165, 80, 25);
			add(label10);
			
			JLabel label11 = new JLabel("Comentarios: ");
			label11.setFont(new Font("Sitka Text", Font.PLAIN, 13));
			label11.setBounds(301, 165, 100, 25);
			add(label11);
			
		//Texto: 
			
			textoTitulo1 = new JTextArea();
			textoTitulo1.setBounds(56, 201, 100, 25);
			add(textoTitulo1);
			
			textoMinutos1 = new JTextArea();
			textoMinutos1.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
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
			textoMinutos1.setBounds(539, 201, 100, 25);
			add(textoMinutos1);
			
			textoComentario1 = new JTextArea();
			textoComentario1.setBounds(301, 201, 100, 25);
			add(textoComentario1);
			
		//Botones
			
			botonModif = new JButton("MODIFICAR");
			botonModif.setFont(new Font("Sitka Text", Font.PLAIN, 13));
			botonModif.setBounds(227, 358, 200, 30);
			botonModif.setBackground(Color.DARK_GRAY);
			botonModif.setForeground(Color.WHITE);
			add(botonModif);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Nacho\\Pictures\\Camera Roll\\OIP2.jpg"));
			lblNewLabel.setBounds(733, 31, 412, 376);
			add(lblNewLabel);
			
		}
	}


