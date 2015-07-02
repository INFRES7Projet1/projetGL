package com.projetGL.vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.awt.Color;

public class InfoView {
	
	//constructeur
	public InfoView(){
		JFrame frame = new JFrame();
		frame.setSize(400, 300);
		frame.setTitle("Infos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JTextPane txtpnauthors = new JTextPane();
		txtpnauthors.setText("@authors : \n"
							+ "    Aymeric Cossard \n"
							+ "    Jonathan Baronci\n"
							+ "    Jimmy Barouh\n"
							+ "    Rémy Benedetti\n"
							+ "\n\n"
							+ "@contact :\n"
							+ "    'prenom'.'nom'@mines-ales.org\n"
							+ " \n\n"
							+ "@date : \n"
							+ "    02 Juillet 2015");
		frame.getContentPane().add(txtpnauthors, BorderLayout.CENTER);
		frame.getContentPane().setBackground(Color.BLUE);
		frame.setVisible(true);		
	}
}//InfoView
