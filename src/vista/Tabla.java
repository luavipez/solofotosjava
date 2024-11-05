package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import main.Usuario;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tabla extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla frame = new Tabla();
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
	public Tabla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 11, 382, 239);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla();
			}
		});
		btnActualizar.setBounds(31, 280, 89, 23);
		contentPane.add(btnActualizar);
		llenarTabla();
	}
	
	
	
	public ArrayList<Usuario> llenarTablaContrl() {
		Conexion cn = new Conexion();
		Connection con;
		con = cn.conectar();
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from users");
			
			ResultSet rs; 
			rs = ps.executeQuery();
			while(rs.next()) {
				Usuario us = new Usuario();
				us.setId(rs.getInt(1));
				us.setUsername(rs.getString(2));
				us.setPassword(rs.getString(3));
				lista.add(us);						
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public void llenarTabla() {
		ArrayList<Usuario> lusv= null;
		lusv = llenarTablaContrl();
		DefaultTableModel dtm = new DefaultTableModel();
		Object[] titulos = {"id", "Usuario","Contraseña"};
		dtm.setColumnIdentifiers(titulos);
		Object[] usu = new Object[3];		
		  for (int i = 0; i < lusv.size(); i++) { 
			  usu[0] = lusv.get(i).getId();
			  usu[1] = lusv.get(i).getUsername(); 
			  usu[2] = lusv.get(i).getPassword();
			  dtm.addRow(usu);
		  }		
		table.setModel(dtm);
	}
}
