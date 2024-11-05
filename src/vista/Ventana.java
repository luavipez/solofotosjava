package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Caret;
import javax.swing.text.MaskFormatter;

import conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreFoto;
	private FileInputStream fis;
	private int longitudBytes;
	private FileInputStream fi;
	private int largoarch;
	private DefaultTableModel dtm; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
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
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre de la foto");
		lblNewLabel.setBounds(282, 41, 134, 14);
		contentPane.add(lblNewLabel);

		txtNombreFoto = new JTextField();
		txtNombreFoto.setBounds(282, 85, 208, 26);
		contentPane.add(txtNombreFoto);
		txtNombreFoto.setColumns(10);

		JLabel lblClickEnEl = new JLabel("Click en el cuadro de abajo para agregar la foto:");
		lblClickEnEl.setBounds(246, 152, 244, 14);
		contentPane.add(lblClickEnEl);

		lblFoto = new JLabel("Foto");
		lblFoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter fnef = new FileNameExtensionFilter("Imagenes", "jpg", "png", "gif");
				jfc.setFileFilter(fnef);
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int estado = jfc.showOpenDialog(null);
				//JOptionPane.showMessageDialog(null, estado);
				
				if (estado == JFileChooser.APPROVE_OPTION) {
					JFileChooser jfchoo = new JFileChooser();
					jfchoo.setFileSelectionMode(JFileChooser.FILES_ONLY);
					jfchoo.showOpenDialog(lblClickEnEl);
					try {
						
						fis = new FileInputStream(jfc.getSelectedFile());
						fi = new FileInputStream(jfchoo.getSelectedFile());
						
						longitudBytes = (int) jfc.getSelectedFile().length();
						largoarch = (int) jfchoo.getSelectedFile().length();
						Image img = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblFoto.getWidth(),
								lblFoto.getHeight(), Image.SCALE_DEFAULT);
						Image im = ImageIO.read(jfchoo.getSelectedFile()).getScaledInstance(lfot.getWidth(), lfot.getHeight(), Image.SCALE_DEFAULT);
						lblFoto.setIcon(new ImageIcon(img));
						lblFoto.updateUI();
						lfot.setIcon(new ImageIcon(im));
						lfot.updateUI();
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Error al encontrar archivo: " + e);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error al cargar archivo: " + e);
					}
				}
			}
		});
		lblFoto.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(282, 177, 235, 233);
		contentPane.add(lblFoto);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarImagen();
			}
		});
		btnGuardar.setBounds(53, 177, 134, 52);
		contentPane.add(btnGuardar);

		JButton btnIrAConsultar = new JButton("ir a consultar");
		btnIrAConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarFoto cf = new ConsultarFoto();
				cf.setVisible(true);
				dispose();
			}
		});
		btnIrAConsultar.setBounds(53, 252, 134, 52);
		contentPane.add(btnIrAConsultar);

		try {
			jftFechaNac = new JFormattedTextField(new MaskFormatter("####-##-##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jftFechaNac.setBounds(148, 366, 213, 20);
		contentPane.add(jftFechaNac);
		
		lfot = new JLabel("");
		lfot.setBounds(10, 11, 62, 44);
		contentPane.add(lfot);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 341, 98, 105);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	JFormattedTextField jftFechaNac;
	JLabel lfot;
	JLabel lblFoto;
	private JTable table;
	private JScrollPane scrollPane;

	private void guardarImagen() {
		if (this.txtNombreFoto.getText().isEmpty()) {
			this.txtNombreFoto.setBackground(Color.red);
			JOptionPane.showMessageDialog(null, "Llena todos los datos");
		} else {
			String nombre = txtNombreFoto.getText().trim();
			Connection con = Conexion.conectar();
			try {
				PreparedStatement ps = con.prepareStatement("insert into fotos values(0,?,?,?)");
				ps.setString(1, nombre);
				ps.setBlob(2, fis);
				LocalDate d = LocalDate.parse(jftFechaNac.getText());
				ps.setDate(3, java.sql.Date.valueOf(d));
				int guarda = ps.executeUpdate();
				if (guarda > 0) {
					JOptionPane.showMessageDialog(null, "Guardado");
				} else {
					JOptionPane.showMessageDialog(null, "No guardado");
				}
				txtNombreFoto.setBackground(Color.white);
				limpiar();
				con.close();
				ps.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al guardar imagen: " + e);
			}
		}
	}

	private void limpiar() {
		this.txtNombreFoto.setText("");
		lblFoto.setIcon(null);
		this.lblFoto.setText("Foto");
		this.jftFechaNac.setText("");
	}
}
