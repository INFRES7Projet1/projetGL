package com.projetGL.vue;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.projetGL.controller.*;
import com.projetGL.model.*;


public class ColisView extends Fenetre {
	
	private JFrame frame;
	private JPanel jpnlHeader;
	private JPanel jpnlBottom;

	private JTable table;
	//ou 
	
	private List<com.projetGL.model.Colis> infosColis;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public ColisView() {
		initialize();
	}
	
	void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("ESCRIM Software");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getJpnlHeader();
				
		//Appel au controleur 
		ColisController ctrlColis = new ColisController();
		infosColis = ctrlColis.returnInfosColis();
		
		JPanel jpnlContent = new JPanel();
		frame.getContentPane().add(jpnlContent, BorderLayout.CENTER);

		//tableau de donnees
		String[] entetes = {"Id", "Designation", "Etat", "Poids", "Affectataire", ""};
		
		DefaultTableModel tableModel = new DefaultTableModel(entetes, 0);
        // The 0 argument is number rows.


		jpnlContent.add(table);
		
		for (com.projetGL.model.Colis col : infosColis){
		   Object[] data = {col.Id, col.Designation, col.Etat, col.Poids, col.Affectataire, col.Option, col.Type};  	
		   tableModel.addRow(data);
		}

		JTable table = new JTable(tableModel);
		
	}//initialize()
	
}//Colis
