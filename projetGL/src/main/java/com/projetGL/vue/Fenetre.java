package com.projetGL.vue;


import javax.swing.JFrame;


import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Fenetre {

	protected JFrame frame = new JFrame(); //fenetre
	protected JPanel jpnlHeader;			//menu 
	protected JPanel jpnlContent; 			//tableau de donnees
	protected JPanel jpnlBottom;    		//footer

	protected String _title = "ESCRIM Software";
	
	/**
	 * Launch the application.
	 */

	
	//Constructeur - creation Fenetre principale
	public Fenetre() {
		initialize();
	}

	//Initialisation des composants de la vue d'accueil
	protected void initialize() {
//		frame = new JFrame();
		//to specify the position and size of a GUI component 
		frame.setSize(1024, 768);
		frame.setTitle("ESCRIM Software");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		//Recuperation des components dans des containers
		jpnlBottom = getJpnlBottom();
		jpnlHeader = getJpnlHeader(_title);
		
		//Frame - Ajout du header et du footer a  la frame
		frame.getContentPane().add(jpnlHeader, BorderLayout.NORTH);
		frame.getContentPane().add(jpnlBottom, BorderLayout.SOUTH);
		
/*		
		//inserer le JTable dans un JScrollPane pour une meilleure visualisation des donnees
//		jpnlContent.add(new JScrollPane(table));
		jpnlContent.add(new JScrollPane(tableau));
*/		
		frame.setVisible(true);
	}//initialize()
	
	protected JPanel getJpnlHeader(String title) {
		//Header - Cr�ation du Layout associ�. 
		GridBagLayout gbl_jpnlHeader = new GridBagLayout();
		gbl_jpnlHeader.rowWeights = new double[]{0.0, 1.0};
		gbl_jpnlHeader.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		jpnlHeader = new JPanel(gbl_jpnlHeader);
		//Header - D�finition des contraintes du layout.
		GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = gbc.gridy = 0;
			gbc.gridheight = gbc.gridwidth = 1;
			//gbc.anchor = GridBagConstraints.FIRST_LINE_START;
												
		//Header - Cr�ation des composants de Header et des actions associees.
			//Creation des boutons.
		JButton btnHome = new JButton("");
		btnHome.setToolTipText("Home");
		btnHome.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/home.png"));;
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
			gbc_btnHome.insets = new Insets(0, 0, 5, 5);
			gbc_btnHome.anchor = GridBagConstraints.FIRST_LINE_START;
			//gbc_btnHome.insets = new Insets(0, 0, 0, 5);
			gbc_btnHome.gridx = 0;
			gbc_btnHome.gridy = 0;
			gbc_btnHome.gridwidth = 3;
		jpnlHeader.add(btnHome, gbc_btnHome);

		JButton btnConf = new JButton("Gestion Configurations");
		btnConf.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/conf.png"));
		btnConf.setToolTipText("Gestion des configurations");
		btnConf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				System.out.println("initialisation de la vue colis...");
				ConfigurationView vueConf = new ConfigurationView();				
				System.out.println("vue colis initialisee");
			}
		});
		GridBagConstraints gbc_btnConf = new GridBagConstraints();
		gbc_btnConf.insets = new Insets(0, 0, 5, 5);
		gbc_btnConf.gridx = 3;
		gbc_btnConf.gridy = 0;
		jpnlHeader.add(btnConf, gbc_btnConf);
		

//MouseListener Colis		
		JButton btnColis = new JButton("Colis");
		btnColis.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/colis.png"));
		btnColis.setToolTipText("Colis");
		btnColis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("initialisation de la vue colis...");
				ColisView vueColis = new ColisView();
				frame.setVisible(false);
//				frame.setVisible(false);
				System.out.println("vue colis initialisee");
			}
		});
		GridBagConstraints gbc_btnColis = new GridBagConstraints();
		gbc_btnColis.insets = new Insets(0, 0, 5, 5);
		gbc_btnColis.gridx = 4;
		gbc_btnColis.gridy = 0;
		jpnlHeader.add(btnColis, gbc_btnColis);
		
		JButton btnMedoc = new JButton("Médicaments");
		btnMedoc.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/medocs.png"));
		btnMedoc.setToolTipText("Médicaments");
		btnMedoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				System.out.println("initialisation de la vue medoc...");
				MedicamentView vueMedoc = new MedicamentView();
				System.out.println("vue medoc initialisee...");
			}
		});
		GridBagConstraints gbc_btnMedoc = new GridBagConstraints();
		gbc_btnMedoc.insets = new Insets(0, 0, 5, 5);
		gbc_btnMedoc.gridx = 6;
		gbc_btnMedoc.gridy = 0;
		jpnlHeader.add(btnMedoc, gbc_btnMedoc);
		
		JButton btnOutil = new JButton("Outils");
		btnOutil.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/tools.png"));
		btnOutil.setToolTipText("Outils");
		btnOutil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				System.out.println("vue outil...");
				OutilView vueOutil = new OutilView();		
				System.out.println("vue accueil recup");
			}
		});
		GridBagConstraints gbc_btnOutil = new GridBagConstraints();
		gbc_btnOutil.insets = new Insets(0, 0, 5, 5);
		gbc_btnOutil.gridx = 7;
		gbc_btnOutil.gridy = 0;
		jpnlHeader.add(btnOutil, gbc_btnOutil);
		
		JButton btnObjet = new JButton("Objets");
		btnObjet.setIcon(new ImageIcon(this.getClass().getResource("/resources/icons32/objet.png")));
		btnObjet.setToolTipText("Objets");
		btnObjet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				System.out.println("vue objet...");
				ObjetView vueObjet = new ObjetView();		
				System.out.println("retour sur la vue accueil...");
			}
		});
		GridBagConstraints gbc_btnObjet = new GridBagConstraints();
		gbc_btnObjet.insets = new Insets(0, 0, 5, 0);
		gbc_btnObjet.gridx = 8;
		gbc_btnObjet.gridy = 0;
		jpnlHeader.add(btnObjet, gbc_btnObjet);
		
			//Creation du titre.
		JPanel jpnlTitle = new JPanel();
		GridBagConstraints gbc_jpnlTitle = new GridBagConstraints();
			gbc_jpnlTitle.insets = new Insets(0, 0, 5, 5);
			gbc_jpnlTitle.fill = GridBagConstraints.BOTH;
			gbc_jpnlTitle.gridx = 5;
			gbc_jpnlTitle.gridy = 2;
		jpnlHeader.add(jpnlTitle, gbc_jpnlTitle);
		jpnlTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblEscrimSoftware = new JLabel(title);
		jpnlTitle.add(lblEscrimSoftware);
		lblEscrimSoftware.setFont(new Font("DokChampa", Font.BOLD | Font.ITALIC, 34));									
			
		return jpnlHeader;
	}//getJpnlHeader()
	
	private JPanel getJpnlBottom() {
		//Footer - D�finition du Jpanel, Layout et ajout a la frame
		jpnlBottom = new JPanel();
		
		JButton btnContact= new JButton("Nous contacter");
		jpnlBottom.add(btnContact);
		btnContact.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/mail.png"));
		return jpnlBottom;
	}//getJpnlBottom()
	

	
} //Fenetre
