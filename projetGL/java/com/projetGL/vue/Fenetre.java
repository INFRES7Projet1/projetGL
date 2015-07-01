package com.projetGL.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.projetGL.controller.*;
import com.projetGL.model.Colis;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class Fenetre extends JFrame implements ActionListener {

//	protected JFrame frame = new JFrame(); //fenetre
	private JDesktopPane vuePrincipale = new JDesktopPane();
	private JPanel jpnlHeader;			//menu 
	
	private JPanel jpnlContent = new JPanel(); 			//tableau de donnees
	
	private JButton btnConf = new JButton("Configurations");
	private JButton btnColis = new JButton("Colis");
	private JButton btnHome = new JButton("Accueil");
	private JButton btnMedoc =  new JButton("Médicaments");
	private JButton btnObjet = new JButton("Objets");
	private JButton btnOutil = new JButton("Outils");
	
	/**
	 * Launch the application.
	 */

	//Constructeur - creation Fenetre principale
	public Fenetre() {
		this.setTitle("ESCRIM Software");
		this.setIconImage(new ImageIcon("images/logo.png").getImage());
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		jpnlHeader = getJpnlHeader();
		
		this.initBouton();
		
		//Frame - Ajout du header et du footer a  la frame
		this.getContentPane().add(jpnlHeader, BorderLayout.NORTH);
		this.getContentPane().add(jpnlContent, BorderLayout.CENTER);
		this.setVisible(true);
	}

	
	public void initBouton(){		
		btnConf.addActionListener(this);
		btnHome.addActionListener(this);
		btnMedoc.addActionListener(this);
		btnObjet.addActionListener(this);
		btnOutil.addActionListener(this);
	}//initBouton 
	
	//Action a realiser
	public void actionPerformed (ActionEvent arg0){
		if (arg0.getSource() == btnMedoc){
			System.out.println("yolo medoc");
			MedicamentView vueMedicament = new MedicamentView();
			vuePrincipale.add(vueMedicament);
		}
		if (arg0.getSource() == btnOutil){
			System.out.println("yolo outil");
		}
	}//actionPerformed()
	
	private JPanel getJpnlHeader() {
		//Header - Création du Layout associé. 
		GridBagLayout gbl_jpnlHeader = new GridBagLayout();
		gbl_jpnlHeader.rowWeights = new double[]{0.0, 1.0};
		gbl_jpnlHeader.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		jpnlHeader = new JPanel(gbl_jpnlHeader);
		//Header - Définition des contraintes du layout.
		GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = gbc.gridy = 0;
			gbc.gridheight = gbc.gridwidth = 1;
												
		//Header - Création des composants de Header et des actions associees.
			//Creation des boutons.	
		btnColis.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/colis.png"));
		btnColis.setToolTipText("Colis");
		btnColis.addActionListener(this);
		GridBagConstraints gbc_btnColis = new GridBagConstraints();
		gbc_btnColis.insets = new Insets(0, 0, 5, 5);
		gbc_btnColis.gridx = 4;
		gbc_btnColis.gridy = 0;
		jpnlHeader.add(btnColis, gbc_btnColis);
		
		btnConf.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/conf.png"));
		btnConf.setToolTipText("Gestion des configurations");
		GridBagConstraints gbc_btnConf = new GridBagConstraints();
			gbc_btnConf.insets = new Insets(0, 0, 5, 5);
			gbc_btnConf.gridx = 3;
			gbc_btnConf.gridy = 0;
		jpnlHeader.add(btnConf, gbc_btnConf);
		
		btnHome.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/home.png"));;
		btnHome.setToolTipText("Home");
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
			gbc_btnHome.insets = new Insets(0, 0, 5, 5);
			gbc_btnHome.anchor = GridBagConstraints.FIRST_LINE_START;
			gbc_btnHome.gridx = 0;
			gbc_btnHome.gridy = 0;
			gbc_btnHome.gridwidth = 3;
		jpnlHeader.add(btnHome, gbc_btnHome);
		
		btnMedoc.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/medocs.png"));
		btnMedoc.setToolTipText("Médicaments");
			GridBagConstraints gbc_btnMedoc = new GridBagConstraints();
			gbc_btnMedoc.insets = new Insets(0, 0, 5, 5);
			gbc_btnMedoc.gridx = 6;
			gbc_btnMedoc.gridy = 0;
		jpnlHeader.add(btnMedoc, gbc_btnMedoc);
		
		btnOutil.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/tools.png"));
		btnOutil.setToolTipText("Outils");
		GridBagConstraints gbc_btnOutil = new GridBagConstraints();
		gbc_btnOutil.insets = new Insets(0, 0, 5, 5);
		gbc_btnOutil.gridx = 7;
		gbc_btnOutil.gridy = 0;
		jpnlHeader.add(btnOutil, gbc_btnOutil);
		
		btnObjet.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/objet.png"));
		btnObjet.setToolTipText("Objets");
		GridBagConstraints gbc_btnObjet = new GridBagConstraints();
			gbc_btnObjet.insets = new Insets(0, 0, 5, 0);
			gbc_btnObjet.gridx = 8;
			gbc_btnObjet.gridy = 0;
		jpnlHeader.add(btnObjet, gbc_btnObjet);
		
		return jpnlHeader;
	}//getJpnlHeader()
	
} //Fenetre
