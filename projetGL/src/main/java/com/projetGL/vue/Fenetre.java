package com.projetGL.vue;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.projetGL.model.EscrimDAO;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.tree.TreeModel;

public class Fenetre {

	private JFrame frame;
	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Application du thème Nimbus.
	try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            System.out.println("Thème appliqué.\n");
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
					System.out.println(database.GetAllColis());
					
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
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnHome = new JButton("");
		btnHome.setIcon(new ImageIcon(Fenetre.class.getResource("/com/projetGL/resources/icons25/home.png")));
		btnHome.setOpaque(false);
		// to remote the spacing between the image and button's borders
		btnHome.setMargin(new Insets(0, 0, 0, 0));	
		// to remove the border
		btnHome.setBorder(null);
		btnHome.setContentAreaFilled(false); 
		//		btnHome.setBorderPainted(false); 
		//		btnHome.setFocusPainted(false); 

		JButton btnConf = new JButton("Configurations");
		btnConf.setIcon(new ImageIcon(Fenetre.class.getResource("/com/projetGL/resources/icons25/configs.png")));
		btnHome.setIcon(new ImageIcon(Fenetre.class.getResource("/com/projetGL/resources/icons25/home.png")));
		btnHome.setOpaque(false);
		btnHome.setMargin(new Insets(0, 0, 0, 0));	
		btnHome.setBorder(null);
		btnHome.setContentAreaFilled(false); 
		
		JLabel lblEscrimSoftware = DefaultComponentFactory.getInstance().createTitle("ESCRIM Software");
		lblEscrimSoftware.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 28));
		GridBagConstraints gbc_lblEscrimSoftware = new GridBagConstraints();
		gbc_lblEscrimSoftware.insets = new Insets(0, 0, 5, 5);
		gbc_lblEscrimSoftware.gridx = 5;
		gbc_lblEscrimSoftware.gridy = 0;
		frame.getContentPane().add(lblEscrimSoftware, gbc_lblEscrimSoftware);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		
		JSeparator horizontalSep = new JSeparator(JSeparator.HORIZONTAL);
		//		horizontalStrut.setVisible(true);
		//		horizontalStrut.setBackground(Color.BLACK);
				GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
				gbc_horizontalStrut.anchor = GridBagConstraints.NORTH;
				gbc_horizontalStrut.fill = GridBagConstraints.HORIZONTAL;
				gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
				gbc_horizontalStrut.gridx = 5;
				gbc_horizontalStrut.gridy = 5;
				//		frame.getContentPane().add(horizontalStrut, gbc_horizontalStrut);
						frame.getContentPane().add(horizontalSep, gbc_horizontalStrut);
		
		GridBagConstraints gbc_btnMedicaments = new GridBagConstraints();
		gbc_btnMedicaments.anchor = GridBagConstraints.WEST;
		gbc_btnMedicaments.insets = new Insets(0, 0, 5, 5);
		gbc_btnMedicaments.gridx = 4;
		gbc_btnMedicaments.gridy = 6;
		frame.getContentPane().add(btnConf, gbc_btnMedicaments);
		
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.insets = new Insets(0, 0, 5, 5);
		gbc_btnHome.gridx = 2;
		gbc_btnHome.gridy = 0;
		frame.getContentPane().add(btnHome, gbc_btnHome);
		
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 6;
		gbc_verticalStrut.gridy = 6;
		frame.getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 8;
		gbc_btnNewButton.gridy = 6;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		tree = new JTree();
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.insets = new Insets(0, 0, 5, 5);
		gbc_tree.fill = GridBagConstraints.HORIZONTAL;
		gbc_tree.gridx = 5;
		gbc_tree.gridy = 7;
		frame.getContentPane().add(tree, gbc_tree);
		



	}

	public TreeModel getTreeModel() {
		return tree.getModel();
	}
	public void setTreeModel(TreeModel model) {
		tree.setModel(model);
	}
}
