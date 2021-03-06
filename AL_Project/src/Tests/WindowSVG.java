package Tests;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * Cette fen�tre permet d'afficher le canvas SVG dans une fen�tre graphique
 * 
 */
public class WindowSVG extends JFrame {
	
	public WindowSVG(int largeur, int hauteur) {
		this.setTitle("Fen�tre SVG");
		this.setSize(largeur,hauteur);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// On cr�e une instance de test
		TestDisplaySVG test = new TestDisplaySVG();
		
		getContentPane().add(test.getCanvas());
		//this.pack();
	}
	
	public static void main(String[] args) {
		WindowSVG w = new WindowSVG(500, 600);
		// Dans ce cas pr�cis, pr�ciser la largeur et la hauteur n'est d'aucune utilit�
		w.setExtendedState(MAXIMIZED_BOTH);
		w.setVisible(true);
	}
}
