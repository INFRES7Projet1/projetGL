package com.projetGL.vue;

import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.projetGL.model.*;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

public class Fenetre {

	private JFrame frame;
	private JPanel jpnlHeader;
	private JPanel jpnlBottom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Application du theme Nimbus.
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            System.out.println("Theme appliquee.\n");
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
					
					/*List<Colis> res = database.GetAllColis();
					for(Colis col : res){
						System.out.println(col.toString());
					}*/
					
					/*ConfigurationColis conf = database.GetConfigurationContent(1);
					System.out.println(conf.toString());
					
					List<ConfigurationColis> res = database.GetAllConfiguration();
					for(ConfigurationColis config : res){
						System.out.println(config.toString());
					}
					
					List<OptionColis> l = database.GetListeOptions();
					for(OptionColis opt : l){
						System.out.println(opt.toString());
					}
					
					List<Medicament> lm = database.GetListeMedicaments();
					for(Medicament med : lm){
						System.out.println(med.toString());
					}
					
					List<Objet> lo = database.GetListeObjets();
					for(Objet ob : lo){
						System.out.println(ob.toString());
					}
					
					List<Outil> lou = database.GetListeOutils();
					for(Outil ou : lou){
						System.out.println(ou.toString());
					}
					
					List<TypeColis> ltc = database.GetListeTypeColis();
					for(TypeColis tc : ltc){
						System.out.println(tc.toString());
					}
					**/
					// DB
					Fenetre window = new Fenetre();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//main()

	public JPanel getJpnlHeader() {
		//Header - Création du container et du Layout associé. 
		GridBagLayout gbl_jpnlHeader = new GridBagLayout();
		gbl_jpnlHeader.rowWeights = new double[]{0.0, 1.0};
		gbl_jpnlHeader.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		jpnlHeader = new JPanel(gbl_jpnlHeader);
		
		//Frame - Ajout du header à  la frame
		frame.getContentPane().add(jpnlHeader, BorderLayout.NORTH);

		//Header - Définition des contraintes du layout.
		GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = gbc.gridy = 0;
			gbc.gridheight = gbc.gridwidth = 1;
			//gbc.anchor = GridBagConstraints.FIRST_LINE_START;
												
		//Header - Création des composants de Header.
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
		GridBagConstraints gbc_btnConf = new GridBagConstraints();
		gbc_btnConf.insets = new Insets(0, 0, 5, 5);
		gbc_btnConf.gridx = 3;
		gbc_btnConf.gridy = 0;
		jpnlHeader.add(btnConf, gbc_btnConf);
		btnConf.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/conf.png"));
		
		JButton btnColis = new JButton("Colis");
		btnColis.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/colis.png"));
		GridBagConstraints gbc_btnColis = new GridBagConstraints();
		gbc_btnColis.insets = new Insets(0, 0, 5, 5);
		gbc_btnColis.gridx = 4;
		gbc_btnColis.gridy = 0;
		jpnlHeader.add(btnColis, gbc_btnColis);
		
		JButton btnMedoc = new JButton("Médicaments");
		btnMedoc.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/medocs.png"));
		GridBagConstraints gbc_btnMedoc = new GridBagConstraints();
		gbc_btnMedoc.insets = new Insets(0, 0, 5, 5);
		gbc_btnMedoc.gridx = 6;
		gbc_btnMedoc.gridy = 0;
		jpnlHeader.add(btnMedoc, gbc_btnMedoc);
		
		JButton btnOutil = new JButton("Outils");
		btnOutil.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/tools.png"));
		GridBagConstraints gbc_btnOutil = new GridBagConstraints();
		gbc_btnOutil.insets = new Insets(0, 0, 5, 5);
		gbc_btnOutil.gridx = 7;
		gbc_btnOutil.gridy = 0;
		jpnlHeader.add(btnOutil, gbc_btnOutil);
		
		JButton btnObjet = new JButton("Objets");
		btnObjet.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/objet.png"));
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

		return jpnlHeader;
	}//getJpnlHeader()
	
	public JPanel getJpnlBottom() {
		//Footer - Définition du Jpanel, Layout et ajout a la frame
		jpnlBottom = new JPanel();
		frame.getContentPane().add(jpnlBottom, BorderLayout.SOUTH);
		
		JButton btnContact= new JButton("Nous contacter");
		jpnlBottom.add(btnContact);
		btnContact.setIcon(new ImageIcon("./src/main/resources/com/projetGL/resources/icons32/mail.png"));
		return jpnlBottom;
	}//getJpnlBottom()
	
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

		getJpnlHeader();
		getJpnlBottom();
		
	}//initialize()
	
	


}
