package com.projetGL.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.projetGL.model.EscrimDAO;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Fenetre {

	private JFrame frame;
	private JPanel jpnlHeader;
	private JPanel jpnlBottom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Application du th�me Nimbus.
	try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            System.out.println("Th�me appliqu�.\n");
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					EscrimDAO database = new EscrimDAO();
//					System.out.println(database.GetAllColis());
					
					Fenetre window = new Fenetre();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Fenetre() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("ESCRIM Software");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//Header - Cr�ation du container et du Layout associ�. 
		GridBagLayout gbl_jpnlHeader = new GridBagLayout();
		gbl_jpnlHeader.rowWeights = new double[]{0.0, 1.0};
		gbl_jpnlHeader.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		jpnlHeader = new JPanel(gbl_jpnlHeader);
		
		//Frame - Ajout du header �  la frame
		frame.getContentPane().add(jpnlHeader, BorderLayout.NORTH);


		//Header - D�finition des contraintes du layout.
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		//		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
												
			//Header - Cr�ation des composants de Header.
			JButton btnHome = new JButton("");
			btnHome.setToolTipText("Home");
					btnHome.setIcon(new ImageIcon("C:\\Users\\root\\Desktop\\Projet ESCRIM\\eclipse\\workspace\\config git\\projetGL\\src\\main\\resources\\com\\projetGL\\resources\\icons32\\home.png"));
					GridBagConstraints gbc_btnHome = new GridBagConstraints();
					gbc_btnHome.insets = new Insets(0, 0, 5, 5);
					gbc_btnHome.anchor = GridBagConstraints.FIRST_LINE_START;
	//				gbc_btnHome.insets = new Insets(0, 0, 0, 5);
					gbc_btnHome.gridx = 0;
					gbc_btnHome.gridy = 0;
					gbc_btnHome.gridwidth = 3;
			jpnlHeader.add(btnHome, gbc_btnHome);
		
		
//A virer jpnlMenu, ses composants -> Header.				
//		JPanel jpnlMenu = new JPanel(gbl_jpnlHeader);
//		frame.getContentPane().add(jpnlMenu, BorderLayout.NORTH);
		
			
		JButton btnConf = new JButton("Gestion Configurations");
		GridBagConstraints gbc_btnConf = new GridBagConstraints();
		gbc_btnConf.insets = new Insets(0, 0, 5, 5);
		gbc_btnConf.gridx = 3;
		gbc_btnConf.gridy = 0;
		jpnlHeader.add(btnConf, gbc_btnConf);
//		btnConf.setIcon(new ImageIcon("/projetGL/src/main/resources/com/projetGL/resources/icons32/conf.png"));
		btnConf.setIcon(new ImageIcon("C:\\Users\\root\\Desktop\\Projet ESCRIM\\eclipse\\workspace\\config git\\projetGL\\src\\main\\resources\\com\\projetGL\\resources\\icons32\\conf.png"));
		
		JButton btnColis = new JButton("Colis");
		btnColis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//ouvrir page colis
			}
		});
		btnColis.setIcon(new ImageIcon("C:\\Users\\root\\Desktop\\Projet ESCRIM\\eclipse\\workspace\\config git\\projetGL\\src\\main\\resources\\com\\projetGL\\resources\\icons32\\colis.png"));
		GridBagConstraints gbc_btnColis = new GridBagConstraints();
		gbc_btnColis.insets = new Insets(0, 0, 5, 5);
		gbc_btnColis.gridx = 4;
		gbc_btnColis.gridy = 0;
		jpnlHeader.add(btnColis, gbc_btnColis);
		
		JButton btnMedoc = new JButton("M�dicaments");
		btnMedoc.setIcon(new ImageIcon("C:\\Users\\root\\Desktop\\Projet ESCRIM\\eclipse\\workspace\\config git\\projetGL\\src\\main\\resources\\com\\projetGL\\resources\\icons32\\medocs.png"));
		GridBagConstraints gbc_btnMedoc = new GridBagConstraints();
		gbc_btnMedoc.insets = new Insets(0, 0, 5, 5);
		gbc_btnMedoc.gridx = 6;
		gbc_btnMedoc.gridy = 0;
		jpnlHeader.add(btnMedoc, gbc_btnMedoc);
		
		JButton btnOutil = new JButton("Outils");
		btnOutil.setIcon(new ImageIcon("C:\\Users\\root\\Desktop\\Projet ESCRIM\\eclipse\\workspace\\config git\\projetGL\\src\\main\\resources\\com\\projetGL\\resources\\icons32\\tools.png"));
		GridBagConstraints gbc_btnOutil = new GridBagConstraints();
		gbc_btnOutil.insets = new Insets(0, 0, 5, 5);
		gbc_btnOutil.gridx = 7;
		gbc_btnOutil.gridy = 0;
		jpnlHeader.add(btnOutil, gbc_btnOutil);
		
		JButton btnObjet = new JButton("Objets");
		btnObjet.setIcon(new ImageIcon("C:\\Users\\root\\Desktop\\Projet ESCRIM\\eclipse\\workspace\\config git\\projetGL\\src\\main\\resources\\com\\projetGL\\resources\\icons32\\objet.png"));
		GridBagConstraints gbc_btnObjet = new GridBagConstraints();
		gbc_btnObjet.insets = new Insets(0, 0, 5, 0);
		gbc_btnObjet.gridx = 8;
		gbc_btnObjet.gridy = 0;
		jpnlHeader.add(btnObjet, gbc_btnObjet);
											
											JPanel jpnlTitle = new JPanel();
											GridBagConstraints gbc_jpnlTitle = new GridBagConstraints();
											gbc_jpnlTitle.insets = new Insets(0, 0, 5, 5);
											gbc_jpnlTitle.fill = GridBagConstraints.BOTH;
											gbc_jpnlTitle.gridx = 5;
											gbc_jpnlTitle.gridy = 1;
											jpnlHeader.add(jpnlTitle, gbc_jpnlTitle);
											jpnlTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
								
												
											JLabel lblEscrimSoftware = new JLabel("ESCRIM Software");
											jpnlTitle.add(lblEscrimSoftware);
											lblEscrimSoftware.setFont(new Font("DokChampa", Font.BOLD | Font.ITALIC, 34));
		
		//Footer - D�finition du Jpanel, Layout et ajout a la frame
		jpnlBottom = new JPanel();
		frame.getContentPane().add(jpnlBottom, BorderLayout.SOUTH);
		
		JButton btnContact= new JButton("Nous contacter");
		jpnlBottom.add(btnContact);
		btnContact.setIcon(new ImageIcon("C:\\Users\\root\\Desktop\\Projet ESCRIM\\eclipse\\workspace\\config git\\projetGL\\src\\main\\resources\\com\\projetGL\\resources\\icons32\\mail.png"));
		
	}//initialize()

	public JPanel getJpnlHeader() {
		return jpnlHeader;
	}
	public JPanel getJpnlBottom() {
		return jpnlBottom;
	}
}
