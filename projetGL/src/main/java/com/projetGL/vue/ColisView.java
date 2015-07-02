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


public class ColisView extends FenetreView {
			
	private List<Colis> infosColis;
	private JPanel jpnlHeader;
	private JTable tab;
	private JPanel jpnlBottom;
	private JButton btnAdd;
	private JButton btnModif;
	private JButton btnSuppr;
	
	private static String _title = "Colis";
		
	/**
	 * @wbp.parser.entryPoint
	 */
	//constructeur
	public ColisView() {
		initialize();

	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void initialize() {
		System.out.println("fenetre colis in");
		frame.setSize(1024, 600);
		frame.setTitle("Escrim Software");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
//		BorderLayout layoutMainContent = new BorderLayout();
//		JPanel jpnlChooseConf = new JPanel();
		
		
		
		JPanel jpnlContent = new JPanel();
		
		
		//Appel au controleur 
		ColisController ctrllerColis = new ColisController();
		infosColis = ctrllerColis.returnListe();

	    //Les titres des colonnes
		String[] colonnes = {"Id", "Designation", "Etat", "Poids", "Affectataire", "Option", "Secteur", "Designation Générique", "Type", "Dimension"};
		DefaultTableModel tableModel = new DefaultTableModel(colonnes, 0);
		
		for (Colis col : infosColis){
				Object [] donnees = {col.Id, col.Designation, col.Etat.toString(), col.Poids, col.Affectataire, col.Option.Designation, col.SecteurUtilisation.Designation , col.SecteurUtilisation.DesignationGenerique.Designation, col.Type.Designation, col.Type.GetDimension()};  	
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
				AddColisView vueAjoutColis = new AddColisView();
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
				DeleteColisView vueSuppressionColis = new DeleteColisView();
			}
		});
		jpnlBottom.add(btnSuppr);		
		
		return jpnlBottom;
	}//getJpnlBottom
}//ColisView
