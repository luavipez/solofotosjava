package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.sql.Blob;

import conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class ConsultarFoto extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarFoto frame = new ConsultarFoto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultarFoto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreFoto = new JLabel("Nombre foto");
		lblNombreFoto.setBounds(212, 75, 138, 14);
		contentPane.add(lblNombreFoto);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(212, 127, 255, 231);
		contentPane.add(lblFoto);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Connection con = Conexion.conectar();
			try {
				PreparedStatement ps = con.prepareStatement("select * from fotos where id=?");
				ps.setInt(1, Integer.parseInt(txtId.getText()));
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					lblNombreFoto.setText(rs.getString(2));
					Blob foto = rs.getBlob(3);
					byte[] datos = foto.getBytes(1, (int) foto.length());					
					BufferedImage img = null;
					try {						
						img = ImageIO.read(new ByteArrayInputStream(datos));
						ImageIcon ic = new ImageIcon(img);
						Icon imagen = new ImageIcon(ic.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
						lblFoto.setIcon(imagen);
						jftFechaCreacion.setText(rs.getString(4));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,"Error al convertir la foto: " +e);
					}
				}else {
					JOptionPane.showMessageDialog(null, "No encontrado");
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,"Error al consultar: " +e);
			}
			
			}			
		});
		btnConsultar.setBounds(37, 208, 89, 23);
		contentPane.add(btnConsultar);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setBounds(10, 75, 21, 14);
		contentPane.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(40, 72, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		try {
			jftFechaCreacion = new JFormattedTextField(new MaskFormatter("####-##-##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jftFechaCreacion.setBounds(75, 321, 127, 20);
		contentPane.add(jftFechaCreacion);
	}
	JFormattedTextField jftFechaCreacion;

}
