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
					/*
					MedicamentDAO meddao = new MedicamentDAO();
					Medicament med = meddao.find(11);
					System.out.println(med.toString());
					med.Produit= "Doliprane 9001";
					meddao.delete(med);
					
					
					ClasseTherapeutiqueDAO ctDAO = new ClasseTherapeutiqueDAO();
					ClasseTherapeutique ct = ctDAO.find(7);
					ct.Designation = "Cardio Electrolytes";
					ctDAO.delete(ct);
					
					ColisDAO coDAO = new ColisDAO();
					Colis co = coDAO.find(6);
					co.Designation = "Cardiology";
					coDAO.delete(co);
					
					
					ConfigurationColisDAO ccDAO = new ConfigurationColisDAO();
					ConfigurationColis cc = ccDAO.find(2);
					cc.Designation = "Tourbillon";
					ccDAO.delete(cc);
					
					DCIDAO dciDAO = new DCIDAO();
					DCI dci = dciDAO.find(11);
					dci.Designation = "Acide Atro-pinepinemique";
					dciDAO.delete(dci);
				
					DesignationGeneriqueDAO dgDAO = new DesignationGeneriqueDAO();
					DesignationGenerique dg = dgDAO.find(5);
					dg.Designation = "Soutien Infectant";
					dgDAO.delete(dg);
					
					ObjetDAO obDAO = new ObjetDAO();
					Objet ob = obDAO.find(10);
					ob.Designation = "Echelle dépliable";
					obDAO.delete(ob);
					
					OutilDAO ouDAO = new OutilDAO();
					Outil ou = ouDAO.find(10);
					ou.Designation = "Defibrilateur Pre-Mortem";
					ouDAO.delete(ou);
					
					OptionColisDAO opDAO = new OptionColisDAO();
					OptionColis op = opDAO.find(4);
					op.Designation = "Pays Tempéré";
					opDAO.delete(op);
					
				
					TypeColisDAO tpDAO = new TypeColisDAO();
					TypeColis tp = tpDAO.find(4);
					tp.Designation = "BAG";
					tpDAO.delete(tp);
					
					SecteurDAO secDAO = new SecteurDAO();
					Secteur sec = secDAO.find(10);
					sec.Designation = "(G) Bloc opératoire 4";
					secDAO.delete(sec);
					
					
					ColisDAO ccDAO = new ColisDAO();
					ccDA
					O.DeleteColisFromConfigurationColis(6);
					*/
					
					MedicamentDAO medDAO = new MedicamentDAO();
					medDAO.DeleteMedicamentFromMedicamentColis(11);
					
					ObjetDAO obDAO = new ObjetDAO();
					obDAO.DeleteObjetFromObjetColis(10);
					
					OutilDAO ouDAO = new OutilDAO();
					ouDAO.DeleteOutilFromOutilColis(10);
					
					
					/*
					ColisDAO cDAO = new ColisDAO();
					Colis c = cDAO.find(4);
					System.out.println(c.toString());
					
					ConfigurationColisDAO ccDAO = new ConfigurationColisDAO();
					ConfigurationColis cc = ccDAO.find(1);
					System.out.println(cc.toString());
					*/
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
