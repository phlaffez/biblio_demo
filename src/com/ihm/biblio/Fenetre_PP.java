package com.ihm.biblio;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import phl.outils.panneaux.outilsStandards.PanneauOutilsStandard;

public class Fenetre_PP extends JFrame
{

	public Fenetre_PP() {
		// TODO Auto-generated constructor stub
		
	
		   
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	    this.setTitle("Gestion de bibliothèque");
		 this.setSize(800,600);
		 this.setLocationRelativeTo(null);  
		 
		 JTabbedPane tabbedPane;
		   int ntabs = 0;
		   this. getContentPane().setLayout(new BorderLayout());
		   UIManager.put("TabbedPane.selected", Color.red);
		   tabbedPane = new JTabbedPane();
		   for (ntabs = 0; ntabs <6; ntabs ++)
		   {
		      tabbedPane.addTab("Tab #" + ntabs, new JLabel("Tab #" + ntabs)); 
		   }
		   
		   
		   
		   String titrePan = "Tests";
			 Color colFond = Color.GREEN;
			 Color colEnt = Color.lightGray; 
			 Color coulTexPP= Color.black;
			 Color centfont = Color.red;
			 Color cboutex = Color.DARK_GRAY;
			 Color cboufon = Color.RED;
			 Color panTab = Color.CYAN;
			 Color coulPanText = Color.WHITE;
			 Color colEntTex = Color.black;
			 panneauGenre pan = new panneauGenre(titrePan,colFond,coulTexPP,panTab,coulPanText,colEnt,
		                colEntTex,cboufon,cboutex);
		   tabbedPane.addTab("Test" , pan); 
		   this.getContentPane().add(BorderLayout.CENTER, tabbedPane);
		      
		 
		     
	    this.setVisible(true);
	}

}
