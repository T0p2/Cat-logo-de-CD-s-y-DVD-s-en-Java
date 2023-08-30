//package Ventana;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;




public class Catalogo {
	
	
private Connection conexion;
private Statement sentencia;

	
	public Catalogo() {
		 String url = "jdbc:mysql://localhost:3306/catalogos";
	        String username = "root";
	        String password = "";

	        try {
	            // Establecer conexión
	            conexion = DriverManager.getConnection(url, username, password);
	            System.out.println("conexion establecida");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	}
	
	
	public void agregarPelibbd (String titulo, String director, String genero, int minutos, boolean tenemos, String comentario) {
		try {
            String sql = "INSERT INTO peliculas (titulo, director, genero, minutos, tenemos, comentario) VALUES (?, ?, ?, ?, ?, ?)";            		
            PreparedStatement sentencia1 = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            
            sentencia1.setString(1, titulo);
            sentencia1.setString(2, director);
            sentencia1.setString(3, genero);
            sentencia1.setInt(4, minutos);
            sentencia1.setBoolean(5, tenemos);
            sentencia1.setString(6, comentario);
            
            

            int filasInsertadas = sentencia1.executeUpdate();
           
            if (filasInsertadas == 1) {
              
                ResultSet rs = sentencia1.getGeneratedKeys();
                if (rs.next()) {
                    int idPeli = rs.getInt(1);

                    String sqlCatalogo = "INSERT INTO Catalogo (idPeli) VALUES (?)";
                    
                    PreparedStatement sentenciaCatalogo = conexion.prepareStatement(sqlCatalogo);

                    sentenciaCatalogo.setInt(1, idPeli);

                    int filasInsertadasCatalogo = sentenciaCatalogo.executeUpdate();

                    if (filasInsertadasCatalogo == 1) {
                        System.out.println("Se insertó la nuevo pelicula y se guardó el ID en la tabla 'Catalogo'.");
                    } else {
                        System.out.println("Error al insertar en la tabla 'Catalogo'.");
                    }
            }}
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	public void agregarTemabbd (String titulo, String director, String genero, int minutos, boolean tenemos, String comentario) {
		try {
            
			String sql = "INSERT INTO temas (titulo, director, genero, minutos, tenemos, comentario) VALUES (?, ?, ?, ?, ?, ?)";            		
            PreparedStatement sentencia1 = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            
            sentencia1.setString(1, titulo);
            sentencia1.setString(2, director);
            sentencia1.setString(3, genero);
            sentencia1.setInt(4, minutos);
            sentencia1.setBoolean(5, tenemos);
            sentencia1.setString(6, comentario);
            
            

            int filasInsertadas = sentencia1.executeUpdate();
           
            if (filasInsertadas == 1) {
            
                ResultSet rs = sentencia1.getGeneratedKeys();
                if (rs.next()) {
                    int idTema = rs.getInt(1);

                    String sqlCatalogo = "INSERT INTO Catalogo (idTema) VALUES (?)";
                    
                    PreparedStatement sentenciaCatalogo = conexion.prepareStatement(sqlCatalogo);

                    sentenciaCatalogo.setInt(1, idTema);

                    int filasInsertadasCatalogo = sentenciaCatalogo.executeUpdate();

                    if (filasInsertadasCatalogo == 1) {
                        System.out.println("Se insertó el nuevo tema y se guardó el ID en la tabla 'Catalogo'.");
                    } else {
                        System.out.println("Error al insertar en la tabla 'Catalogo'.");
                    }
            }}
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	public void eliminarPeliculas(String titulo) throws SQLException {
		String sql2 = "SELECT idPeli FROM peliculas WHERE titulo = ?";
		String sql1 = "DELETE FROM peliculas WHERE titulo = ?;";
		String sqlCatalogo = "DELETE FROM catalogo WHERE idPeli = ?";
		
		PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
		sentencia2.setString(1, titulo);

	
		ResultSet resultado2 = sentencia2.executeQuery();
		if(resultado2.next()) {
		int idpeli = resultado2.getInt("idPeli");
		
		
        PreparedStatement sentenciaCatalogo = conexion.prepareStatement(sqlCatalogo);
        sentenciaCatalogo.setInt(1, idpeli);
        
        int filas = sentenciaCatalogo.executeUpdate();
        
        if(filas > 0) {
        
		PreparedStatement sentencia1 = conexion.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
		sentencia1.setString(1, titulo);
		int filasEliminadas = sentencia1.executeUpdate();
		if(filasEliminadas == 1) {
			System.out.println("bien");
		}else {
			System.out.println("mal");
		}
        
	}else{
		JOptionPane.showMessageDialog(null, "Hubo un error en la base de datos, por lo que no fue posible eliminar su titulo");
	}}
	}
	
	
	
	public void eliminarTemas(String titulo) throws SQLException {
		String sql2 = "SELECT idTema FROM temas WHERE titulo = ?";
		String sql1 = "DELETE FROM temas WHERE titulo = ?;";
		String sqlCatalogo = "DELETE FROM catalogo WHERE idTema = ?";
		
		PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
		sentencia2.setString(1, titulo);

	
		ResultSet resultado2 = sentencia2.executeQuery();
		if(resultado2.next()) {
		int idtema = resultado2.getInt("idTema");
		
		
        PreparedStatement sentenciaCatalogo = conexion.prepareStatement(sqlCatalogo);
        sentenciaCatalogo.setInt(1, idtema);
        
        int filas = sentenciaCatalogo.executeUpdate();
        
        if(filas > 0) {
        
		PreparedStatement sentencia1 = conexion.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
		sentencia1.setString(1, titulo);
		int filasEliminadas = sentencia1.executeUpdate();
		if(filasEliminadas == 1) {
			System.out.println("bien");
		}else {
			System.out.println("mal");
		}
        
	}else{
		JOptionPane.showMessageDialog(null, "Hubo un error en la base de datos, por lo que no fue posible eliminar su titulo");
	}}
	}
	
	
	
	
	public int buscarTitulo (String t) {
		int encontrado = -1;
		String sql = "SELECT titulo FROM peliculas";
		String sql1 = "SELECT titulo FROM temas ";
		PreparedStatement sentencia;
		PreparedStatement sentencia1;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia1 = conexion.prepareStatement(sql1);
			
			
			
			ResultSet resultado0 = sentencia.executeQuery();
			ResultSet resultado1 = sentencia1.executeQuery();

			while(resultado0.next()) {
				if(t.compareTo(resultado0.getString("titulo")) == 0) {
			    encontrado = 1;
			}}
			while(resultado1.next()) {
			if(resultado1.getString("titulo").compareTo(t) == 0) {
				    encontrado = 2;
			} }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return (encontrado);
	}
	
	
	
		
	
	
	
			
	
	
	
	//paso 2 atributos para modifcar porque el texto no especiufca cuantos atributos o cuales debo modificar.
	public void modifcarAtrib(String titulo, String comentario, int minutos ) throws SQLException {
		String sqlPeli = "UPDATE peliculas SET comentario = ?, minutos = ? WHERE titulo = ?";
	    String sqlTema = "UPDATE temas SET comentario = ?, minutos = ? WHERE titulo = ?";
	    
	    try (PreparedStatement sentenciaPeli = conexion.prepareStatement(sqlPeli);
	         PreparedStatement sentenciaTema = conexion.prepareStatement(sqlTema)) {
	        
	        sentenciaPeli.setString(1, comentario);
	        sentenciaPeli.setInt(2, minutos);
	        sentenciaPeli.setString(3, titulo);
	        int resultadoPeli = sentenciaPeli.executeUpdate();

	        if (resultadoPeli > 0) {
	            System.out.println("La película se modificó correctamente");
	        }
	        
	        sentenciaTema.setString(1, comentario);
	        sentenciaTema.setInt(2, minutos);
	        sentenciaTema.setString(3, titulo);
	        int resultadoTema = sentenciaTema.executeUpdate();

	        if (resultadoTema > 0) {
	            System.out.println("El tema se modificó correctamente");
	        } else {
	            System.out.println("No se encontró ninguna película o tema con el título proporcionado");
	        }
	    }
	}
	
	

	
	 public String getDirectorPeli(String titulo){
			String sql = "SELECT director FROM peliculas WHERE titulo = ? ";
			PreparedStatement sentencia;
			try {
				sentencia = conexion.prepareStatement(sql);
				sentencia.setString(1, titulo);
				ResultSet resultSet = sentencia.executeQuery();
				if(resultSet.next()) {
					return(resultSet.getString("director"));
				}else {
					return("error");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return("Error");}	}
	 
	 
		
	public String getGeneroPeli(String titulo){
			String sql = "SELECT genero FROM peliculas WHERE titulo = ? ";
			PreparedStatement sentencia;
			try {
				sentencia = conexion.prepareStatement(sql);
				sentencia.setString(1, titulo);
				ResultSet resultSet = sentencia.executeQuery();
				if(resultSet.next()) {
					return(resultSet.getString("genero"));
				}else {
					return("error");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return("Error");}	}
	
	
	
	public String getComentarioPeli (String titulo) {
		String sql = "SELECT comentario FROM peliculas WHERE titulo = ? ";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, titulo);
			ResultSet resultSet = sentencia.executeQuery();
			if(resultSet.next()) {
				return(resultSet.getString("comentario"));
			}else {
				return("error");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return("Error");}
	}
	
	
	
	public int getMinutosPeli (String titulo) {
		String sql = "SELECT minutos FROM peliculas WHERE titulo = ? ";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, titulo);
			ResultSet resultSet = sentencia.executeQuery();
			if(resultSet.next()) {
				return(resultSet.getInt("minutos"));
			}else {
				return(-1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return(-1);}
	}

	
	
	
	 public String getDirectorTema(String titulo){
			String sql = "SELECT director FROM temas WHERE titulo = ? ";
			PreparedStatement sentencia;
			try {
				sentencia = conexion.prepareStatement(sql);
				sentencia.setString(1, titulo);
				ResultSet resultSet = sentencia.executeQuery();
				if(resultSet.next()) {
					return(resultSet.getString("director"));
				}else {
					return("error");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return("Error");}	}
	 
	 
		
	public String getGeneroTema(String titulo){
			String sql = "SELECT genero FROM temas WHERE titulo = ? ";
			PreparedStatement sentencia;
			try {
				sentencia = conexion.prepareStatement(sql);
				sentencia.setString(1, titulo);
				ResultSet resultSet = sentencia.executeQuery();
				if(resultSet.next()) {
					return(resultSet.getString("genero"));
				}else {
					return("error");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return("Error");}	}
	
	
	
	public String getComentarioTema (String titulo) {
		String sql = "SELECT comentario FROM temas WHERE titulo = ? ";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, titulo);
			ResultSet resultSet = sentencia.executeQuery();
			if(resultSet.next()) {
				return(resultSet.getString("comentario"));
			}else {
				return("error");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return("Error");}
	}
	
	
	
	public int getMinutosTema (String titulo) {
		String sql = "SELECT minutos FROM temas WHERE titulo = ? ";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, titulo);
			ResultSet resultSet = sentencia.executeQuery();
			if(resultSet.next()) {
				return(resultSet.getInt("minutos"));
			}else {
				return(-1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return(-1);}
	}

	 
	
}
	



