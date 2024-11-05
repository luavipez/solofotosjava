package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ImagenControlador;
import modelo.Imagen;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class vfielchooser extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vfielchooser frame = new vfielchooser();
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
	public vfielchooser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnConsultar = new JButton("Consultar imagen");
		btnConsultar.setBounds(5, 5, 424, 23);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				consultar();
				//ocultarVentana();
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnConsultar);
		
		lblFoto = new JLabel("Foto");
		lblFoto.setBounds(198, 62, 212, 177);
		contentPane.add(lblFoto);
		
		txtId = new JTextField();
		txtId.setBounds(10, 60, 103, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingresa el id:");
		lblNewLabel.setBounds(15, 35, 69, 14);
		contentPane.add(lblNewLabel);
	}
	
	private JLabel lblFoto;
	private void consultar() {
		ImagenControlador ic = new ImagenControlador();		
		Imagen img = ic.consultar(Integer.parseInt(txtId.getText()));
		try {
			byte[] datos = img.getFoto().getBytes(1, (int)img.getFoto().length());
			BufferedImage im = ImageIO.read(new ByteArrayInputStream(datos));
			ImageIcon ico = new ImageIcon(im);
			Icon imag = new ImageIcon(ico.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
			lblFoto.setIcon(imag);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		BufferedImage bimg = null;
	}
		
	public void ocultarVentana() {
		this.setVisible(false);
	}
	
	
	
}
