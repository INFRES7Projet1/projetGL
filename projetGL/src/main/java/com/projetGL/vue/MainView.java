package com.projetGL.vue;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainView {
	
	public static void main(String[] args) {
		
		//Application du theme Nimbus.
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            System.out.println("Theme applique.\n");
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
					FenetreView	window = new FenetreView();
					window.frame.setVisible(true);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	}//main()
}
