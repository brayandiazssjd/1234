package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;

public class TxtOut extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TxtOut frame = new TxtOut();
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
	public TxtOut() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1046, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1010, 521);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}
		});
		btnNewButton.setBounds(846, 471, 89, 23);
		panel.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(35, 77, 915, 383);
		panel.add(textArea);
		
		JLabel lblNewLabel = new JLabel("Codigo Python");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(67, 30, 167, 37);
		panel.add(lblNewLabel);

		if (pythonCodeOutput != null) {
			copyInTextArea(pythonCodeOutput, textArea);
		}
		
	}

	// this method copys the translation int this textArea

	public void copyInTextArea( File pythonCodeOutput, JTextArea textArea ) {
		
		
	}
	// this file allows us to keep the translation without losting it.
	File pythonCodeOutput = null;

	// this method assigns the file to a local file.
	public void reiciveFile ( File f ) {
		this.pythonCodeOutput = f;
	}
}
