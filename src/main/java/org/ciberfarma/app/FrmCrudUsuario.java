package org.ciberfarma.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmCrudUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
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
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CRUD");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(175, 11, 72, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo: ");
		lblNewLabel_1.setBounds(10, 40, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(77, 37, 86, 20);
		contentPane.add(txtCodigo);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(10, 89, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(77, 86, 86, 20);
		contentPane.add(txtNombre);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido");
		lblNewLabel_3.setBounds(10, 141, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(77, 138, 86, 20);
		contentPane.add(txtApellido);
		
		JButton btn_Registrar = new JButton("Registrar");
		btn_Registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btn_Registrar.setBounds(378, 36, 89, 23);
		contentPane.add(btn_Registrar);
		
		JButton btn_Buscar = new JButton("Buscar");
		btn_Buscar.setBounds(378, 70, 89, 23);
		contentPane.add(btn_Buscar);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listado();
			}
		});
		btnListado.setBounds(378, 166, 89, 23);
		contentPane.add(btnListado);
		
		txtS = new JTextArea();
		txtS.setBounds(26, 194, 444, 145);
		contentPane.add(txtS);
	}
	 void registrar() {
	
		
	}

	void listado() {
		// Obtener listado de los  usuarios
		 EntityManagerFactory fabrica= Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em=fabrica.createEntityManager();
		 
		TypedQuery<Usuario> consulta=em.createNamedQuery("Usuario.findAllWithType", Usuario.class);
		consulta.setParameter("xtipo", 1);
		List<Usuario> lstUsuarios=consulta.getResultList();
        
		//pasar el listado en txt
		for (Usuario u : lstUsuarios) {
			txtS.append(u.getCodigo() + "\t" + u.getNombre() +
					"\t" + u.getApellido() + "\n");
		}	

	}

	
	void buscar() {
		//leer codigo
		int codigo=leerCodigo();
		
		//buscar en la tabla, para obtener un Usuario
		EntityManagerFactory fabrica= Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em=fabrica.createEntityManager();
		
		Usuario u= em.find(Usuario.class, codigo);
		em.close();
		
		//Si existe lo muestra en los campos 
		if (u==null) {
			aviso("Usuario"+codigo+"no existe");
		}
		else {
			txtNombre.setText(u.getNombre());
			txtApellido.setText(u.getApellido());
		}
	}
	private void aviso(String msg) {
		JOptionPane.showMessageDialog(this,msg,"Aviso del Sistema",2);
		
	}
	private int leerCodigo() {
		
		return Integer.parseInt(txtCodigo.getText());
	}
}
