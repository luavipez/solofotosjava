
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Registros {

	int no;
	int no_control;
	String nombre;
	LocalDate fecha;
	LocalTime hora_ent;
	LocalTime hora_sal;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getNo_control() {
		return no_control;
	}
	public void setNo_control(int no_control) {
		this.no_control = no_control;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora_ent() {
		return hora_ent;
	}
	public void setHora_ent(LocalTime hora_ent) {
		this.hora_ent = hora_ent;
	}
	public LocalTime getHora_sal() {
		return hora_sal;
	}
	public void setHora_sal(LocalTime hora_sal) {
		this.hora_sal = hora_sal;
	}
		
		
	
}
