import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VentanaArrayList extends JFrame {

	private JPanel contentPane;
	 ArrayList<Registros> lr;
	 DefaultTableModel modeloT = new DefaultTableModel();
	 private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaArrayList frame = new VentanaArrayList();
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
	public VentanaArrayList() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 30, 336, 198);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(modeloT);
		
		ConsultaArrayList cal = new ConsultaArrayList();
		lr = cal.consultarR();
		
		String[] tituls = {"No", "NControl","Nombre","Fecha","HEntrada","HSalida"};
		modeloT.setColumnIdentifiers(tituls);
		for (Registros reg : lr) {					
			Object[] rgi = new Object[tituls.length];
			rgi[0]= reg.getNo();
			rgi[1] = reg.getNo_control();
			rgi[2] = reg.getNombre();
			rgi[3] = reg.getFecha();
			rgi[4] = reg.getHora_ent();
			rgi[5] = reg.getHora_sal();				
			modeloT.addRow(rgi);
		}
	}

}
