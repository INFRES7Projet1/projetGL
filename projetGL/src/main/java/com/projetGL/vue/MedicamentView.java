package com.projetGL.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.projetGL.controller.*;
import com.projetGL.model.*;


public class MedicamentView extends FenetreView {
	
	private List<Medicament> infosMedicament;
	private static String _title = "Medicament";
	private JPanel jpnlHeader;
	private JTable tab;
	private JPanel jpnlBottom;
	private JButton btnAdd;
	private JButton btnModif;
	private JButton btnSuppr;
	
	

	/**
	 * @wbp.parser.entryPoint
	 */
	//constructeur
	public MedicamentView() {
		System.out.println("coucou constructeur medoc");
		initialize();

	}
	
	//Initialisation des composants de la vue
	protected void initialize() {
		System.out.println("fenetre colis in");
		frame.setSize(1024, 600);
		frame.setTitle("Escrim Software");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JPanel jpnlContent = new JPanel();
		
		//Appel au controleur 
		MedicamentController ctrllerMedicament = new MedicamentController();
		infosMedicament = ctrllerMedicament.returnListe();

	    //Les titres des colonnes
//	    String  title[] = {"Pseudo", "Age", "Taille"};
//	    JTable tableau = new JTable(donnees, title);
		String[] colonnes = {"Id", "Produit", "Quantité",  "DLU", "Dotation", "Forme Dosage", "Lot", "DCI", "Classe Therapeutique" };
		DefaultTableModel tableModel = new DefaultTableModel(colonnes, 0);
		
		//donnee = tableau de colis ? liste de colis ?
		for (Medicament med : infosMedicament){
				Object [] donnees = {med.Id, med.Produit, med.Quantite, med.Dlu.toString(), med.Dotation, med.FormeDosage, med.Lot, med.Dci.Designation, med.Dci.ClasseT.Designation };  	
				//Object []  donnees = {"1", "Urologie", "Demi_Plein", "50", "DAC", "Option", "BAC"};
				tableModel.addRow(donnees);
			}
		tab = new JTable(tableModel);
		tab.setRowHeight(30);
		tab.setPreferredSize(new Dimension(1000,500));
		
		//inserer le JTable dans un JScrollPane pour une meilleure visualisation des donnees
		JScrollPane jsScroll = new JScrollPane(tab);
		
		jsScroll.setPreferredSize(new Dimension(1000,500));
		jpnlContent.add(jsScroll);
		
		jpnlContent.setPreferredSize(new Dimension(1000,500));
		//Frame - Ajout du content, tableau de donnees a la frame.
		frame.getContentPane().add(jpnlContent, BorderLayout.CENTER);
		//Recuperation du menu et des boutons editions
		jpnlHeader = getJpnlHeader(_title);
		jpnlBottom = getJpnlBottom();
		
		//Frame - Ajout du header a  la frame
		frame.getContentPane().add(jpnlHeader, BorderLayout.NORTH);
		frame.getContentPane().add(jpnlBottom, BorderLayout.SOUTH);
		frame.setVisible(true);
	}//initialize()
	private JPanel getJpnlBottom(){
		
		FlowLayout layoutEdition = new FlowLayout();
		jpnlBottom = new JPanel(layoutEdition);
		
		btnAdd = new JButton("Ajouter");
		btnAdd.setToolTipText("Ajouter");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddMedicamentsView vueAjoutMedoc = new AddMedicamentsView();
			}
		});
		jpnlBottom.add(btnAdd);
			
		/*btnModif = new JButton("Modifier");
		btnModif.setToolTipText("Modifier");
		jpnlBottom.add(btnModif);*/
		
		btnSuppr = new JButton("Supprimer");
		btnSuppr.setToolTipText("Supprimer");
		btnSuppr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DeleteMedicamentView vueDeleteMedoc = new DeleteMedicamentView();
			}
		});
		jpnlBottom.add(btnSuppr);		
		
		return jpnlBottom;
	}//getJpnlBottom	
}//MedicamentView
