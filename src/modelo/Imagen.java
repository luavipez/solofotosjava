package modelo;

import java.sql.Blob;

public class Imagen {

	private int id;
	private String nombre;
	private Blob foto;
	
	private Byte[] img;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Blob getFoto() {
		return foto;
	}

	public void setFoto(Blob foto) {
		this.foto = foto;
	}

	public Byte[] getImg() {
		return img;
	}

	public void setImg(Byte[] img) {
		this.img = img;
	}
	
	
	
	
}
