package Tests;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * Cette fenêtre permet d'afficher le canvas SVG dans une fenêtre graphique
 * 
 */
public class WindowSVG extends JFrame {
	
	public WindowSVG(int largeur, int hauteur) {
		this.setTitle("Fenêtre SVG");
		this.setSize(largeur,hauteur);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// On crée une instance de test
		TestDisplaySVG test = new TestDisplaySVG();
		
		getContentPane().add(test.getCanvas());
		//this.pack();
	}
	
	public static void main(String[] args) {
		WindowSVG w = new WindowSVG(500, 600);
		// Dans ce cas précis, préciser la largeur et la hauteur n'est d'aucune utilité
		w.setExtendedState(MAXIMIZED_BOTH);
		w.setVisible(true);
	}
}
