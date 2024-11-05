package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {

	private static final String driver ="com.mysql.cj.jdbc.Driver";
	private static final String url ="jdbc:mysql://localhost:3306/sistemaescolar";	
	private static final String usuario ="root";
	private static final String contrasena =""; 	
	
	private static final void probarDriver() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Error de driver: " +e);
		}		
	}	
	
	public static final Connection conectar() {		
		Connection con = null;
		probarDriver();
		try {	
			con= DriverManager.getConnection(url, usuario, contrasena);
			return con;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al conectar: " +e);
		}
		return con;
	}

	public Conexion() {		
	}
	
	
	
	
	
}
