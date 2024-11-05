import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
public class ConsultaArrayList {

	private Connection conectar() {
		Connection con = null;
		try {
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda612luciano","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public ArrayList<Registros> consultarR(){
	
		ArrayList<Registros> lr = new ArrayList<Registros>();
		String sql = "select * from registros";
		try {
			PreparedStatement ps = conectar().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Registros r = new Registros();
				r.setNo(rs.getInt(1));
				r.setNo_control(rs.getInt(2));
				r.setNombre(rs.getString(3));				
				r.setFecha(LocalDate.parse(rs.getString(4), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				r.setHora_ent(LocalTime.parse(rs.getString(5)));
				r.setHora_sal(LocalTime.parse(rs.getString(6)));
				lr.add(r);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lr;
	}
	
	
	 
	
	
}
