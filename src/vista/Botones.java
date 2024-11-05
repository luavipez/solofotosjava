package vista;

import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Botones extends JFrame {

	private JPanel contentPane;
	private ButtonGroup rbtnGSexo;
	private CheckboxGroup cbxGroup;
	private JTextField txtNControl;
	private JRadioButton rbtnHombre;
	private JRadioButton rbtnMujer;
	
	private JCheckBox chbxFutbol;	
	private JCheckBox chbxBasquet;
	private JCheckBox chbxBeisball;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Botones frame = new Botones();
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
	public Botones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlSexo = new JPanel();
		
		pnlSexo.setBorder(new TitledBorder(null, "sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSexo.setBounds(40, 41, 137, 88);
		contentPane.add(pnlSexo);
				
		rbtnHombre = new JRadioButton("Hombre");
		pnlSexo.add(rbtnHombre);
		
		rbtnMujer = new JRadioButton("Mujer");
		pnlSexo.add(rbtnMujer);
		
		rbtnGSexo = new ButtonGroup();
		rbtnGSexo.add(rbtnHombre);
		rbtnGSexo.add(rbtnMujer);
		
		JPanel pnlHobbies = new JPanel();
		pnlHobbies.setBorder(new TitledBorder(null, "Hobbies", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlHobbies.setBounds(40, 156, 148, 114);
		contentPane.add(pnlHobbies);
		
		cbxGroup = new CheckboxGroup();
		chbxFutbol = new JCheckBox("Futbol");
		pnlHobbies.add(chbxFutbol,cbxGroup );
		
		chbxBasquet = new JCheckBox("Basquetball");
		pnlHobbies.add(chbxBasquet,cbxGroup);
		
		chbxBeisball = new JCheckBox("Beisball");
		pnlHobbies.add(chbxBeisball,cbxGroup);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				guardarSexo();
				guardarHobbies();
			}			
		});
		btnGuardar.setBounds(24, 323, 89, 23);
		contentPane.add(btnGuardar);
		
		JLabel lblNewLabel = new JLabel("Numero de control:");
		lblNewLabel.setBounds(255, 41, 111, 14);
		contentPane.add(lblNewLabel);
		
		txtNControl = new JTextField();
		txtNControl.setBounds(376, 38, 86, 20);
		contentPane.add(txtNControl);
		txtNControl.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(123, 323, 89, 23);
		contentPane.add(btnConsultar);
		
		System.out.println(rbtnGSexo.getSelection()); 
		
		if(cbxGroup.getSelectedCheckbox()!=null) {
			
		}
		
	}
	
	
	private void guardarSexo() {
		PreparedStatement ps;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdluc", "luc","Wh1th3R4b1t#");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean hombre = rbtnHombre.isSelected();
		boolean mujer = rbtnMujer.isSelected();
		
		if(hombre==true) {
			try {
				ps = con.prepareStatement("insert into sexo values(0,?,?)");
				ps.setString(1, "Masculino");
				ps.setInt(2, Integer.parseInt(txtNControl.getText()));
				int s = ps.executeUpdate();
		
				con.close();
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(mujer == true) {
			try {
				ps = con.prepareStatement("insert into sexo values(0,?,?)");
				ps.setString(1, "Femenino");
				ps.setInt(2, Integer.parseInt(txtNControl.getText()));
				int s = ps.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void guardarHobbies() {
		PreparedStatement ps;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdluc", "luc","Wh1th3R4b1t#");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			boolean beis;
			boolean basq;
			boolean foot;
			//if(chbxBeisball.isSelected()) {
				beis= chbxBeisball.isSelected();
			//}else {
				//beis = false;
			//}
			//if(chbxBasquet.isSelected()) {
				basq= chbxBasquet.isSelected();
			//}else {
				//basq = false;
			//}
			//if(chbxFutbol.isSelected()) {
				foot= chbxFutbol.isSelected();
			//}else {
				//foot = false;
			//}
			System.out.println(""+ beis+ basq+ foot);
			ps = con.prepareStatement("insert into hobbies values(0,?,?,?,?)");
			
			ps.setBoolean(2, beis);
			ps.setBoolean(3, basq);
			ps.setBoolean(4, foot);
			ps.setInt(1, Integer.parseInt(txtNControl.getText()));
			int h = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
