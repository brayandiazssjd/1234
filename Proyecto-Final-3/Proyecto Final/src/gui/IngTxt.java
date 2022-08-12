package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logica.Traductor;

public class IngTxt extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngTxt frame = new IngTxt();
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
	public IngTxt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 912, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 876, 465);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Codigo en java");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(57, 11, 222, 51);
		panel.add(lblNewLabel);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(31, 59, 760, 334);
		panel.add(textArea);

		JButton btnNewButton = new JButton("Traducir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					System.out.println(0);
					File inputJavaCode = new File("D:\\inputJavaCode.txt");
					if (inputJavaCode.exists() == false) {
						inputJavaCode.createNewFile();
					}
					System.out.println(1);
				
							
					BufferedWriter brw = new BufferedWriter(new FileWriter("D:\\inputJavaCode.txt"));
							
							
					textArea.write(brw);
					
					
					// creacion de un nuevo objeto traductor
					Traductor traductor = new Traductor();
					
					File a = traductor.translate(inputJavaCode, textArea);
					try {
						BufferedReader brr = new BufferedReader(new FileReader(a));
						textArea.read(brr, null);
					} catch (Exception e3) {
						// TODO Auto-generated catch block
						System.out.println("error in copying");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Error in IngTxt trans");
				}

				

			}
		});
		btnNewButton.setBounds(679, 404, 89, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Atras");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

			}
		});
		btnNewButton_1.setBounds(31, 417, 89, 23);
		panel.add(btnNewButton_1);
	}
}
