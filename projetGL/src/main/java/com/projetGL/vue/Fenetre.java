package com.projetGL.vue;

import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.projetGL.model.*;


public class Fenetre {

	private JFrame frame;

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
					EscrimDAO database = new EscrimDAO();
					
					/*List<Colis> res = database.GetAllColis();
					for(Colis col : res){
						System.out.println(col.toString());
					}*/
					
					/*ConfigurationColis conf = database.GetConfigurationContent(1);
					System.out.println(conf.toString());*/
					
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
					
					// DB
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
		
		//panel 1
		JPanel panel1 = new JPanel();
		//definir sa taille et positionner un layout..
		
		//panel2 etc..

	}//initialize()

}
