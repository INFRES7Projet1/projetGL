package com.projetGL.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.projetGL.controller.*;
import com.projetGL.model.*;


public class MedicamentView extends JInternalFrame {
	
	private JPanel jpnlContent = new JPanel(); 			//tableau de donnees

	/**
	 * @wbp.parser.entryPoint
	 */
	//constructeur
	public MedicamentView() {
		System.out.println("coucou constructeur medoc");
		this.setTitle("mabite");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setClosable(true);
		this.setVisible(true);
		this.setIconifiable(true);
		
		jpnlContent.setLayout(new BorderLayout());

	}
	
	
}//MedicamentView
