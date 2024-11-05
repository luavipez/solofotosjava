package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import conexion.Conexion;
import modelo.Imagen;

public class ImagenControlador {

	private Imagen img = new Imagen();
	
	private Connection con;
	private Conexion cn;
	private PreparedStatement ps;
	
	public Imagen consultar(int id) {
		String sql ="select * from fotos where id=? "; 
		
		cn = new Conexion();
		con = cn.conectar();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet imag = ps.executeQuery();
			if(imag.next()) {
				img.setId(id);
				img.setFoto(imag.getBlob(3));				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return img;
	}
		
	
	
}
