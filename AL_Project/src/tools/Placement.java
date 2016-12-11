package tools;

import interpretor.Curseur;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import type.Type;

/**
 * Cette classe regroupe des fonctions n�cessaires au placement des diff�rents
 * �l�ments pour le dessin SVG (avec batik)
 * 
 */

public class Placement {
	
	int placementHorizontal ; // Nombre d'�l�ments moins un pr�sents sur la ligne courante
	int ligneCourante ; // Ligne courante
	
	public Placement() {
		placementHorizontal=0;
		ligneCourante=0 ;
	}
	
	public int getPlacementHorizontal() {
		return placementHorizontal;
	}
	
	public void setPlacementHorizontal(int placementHorizontal) {
		this.placementHorizontal = placementHorizontal;
	}

	public int getLigneCourante() {
		return ligneCourante;
	}

	public void setLigneCourante(int placementVertical) {
		this.ligneCourante = placementVertical;
	}
	
	/**
	 * Cette m�thode va permettre de g�rer le placement des types dans le
	 * diagramme (repr�sentation SVG)
	 * 
	 * On place align� au maximum 4 objets (classe ou interface). On saute
	 * ensuite une ligne et on recommence
	 */
	public void algoPlacement() {
		if (placementHorizontal < 3) {
			placementHorizontal++;
		} else {
			// On a plac� 4 �l�ments sur une m�me ligne
			placementHorizontal = 0;
			ligneCourante++;
		}
	}
	
	/**
	 * Cette m�thode donne la taille du texte qui est �crit (largeur - hauteur)
	 */
	public static Curseur tailleTexte(String texte){
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		Font font = new Font("Tahoma", Font.PLAIN, 12);
		int textwidth = (int)(font.getStringBounds(texte, frc).getWidth());
		int textheight = (int)(font.getStringBounds(texte, frc).getHeight());
		return new Curseur(textwidth, textheight);
	}
	
	/**
	 * Cette m�thode permet de calculer la position o� le texte doit commencer
	 * afin qu'il soit plac� au milieu du diagramme
	 */
	public static int placeMilieuX(String texte, int largeur) {
		Curseur taille = tailleTexte(texte);
		return (largeur / 2 - taille.getX() / 2);
	}
	
	/**
	 * Cette m�thode permet retourner la hauteur des objets de type classe ou
	 * interface (afin de pouvoir tracer le rectangle contenant les
	 * caract�ristiques de l'objet)
	 */
	public static int calculHauteur (Curseur depart, Type t){
		
		Curseur curseurMobile = new Curseur(depart) ;
		
		// Texte � placer : 1�re partie
		String s = ("<< Java " + t.getType() + " >>") ;
		Curseur tailleTexte = Placement.tailleTexte(s);
		curseurMobile = curseurMobile.down(tailleTexte.getY());
		
		s=(String) t.getInfo("name");
		tailleTexte = Placement.tailleTexte(s);
		curseurMobile = curseurMobile.down(tailleTexte.getY());
		
		s=(String) t.getInfo("package");
		tailleTexte = Placement.tailleTexte(s);
		curseurMobile = curseurMobile.down(tailleTexte.getY());
		
		// 1er trait de s�paration
		curseurMobile = curseurMobile.down(tailleTexte.getY()/2);
		
		// Variables d'instances
		int ecartDroit = Placement.tailleTexte(s).getY()/2 ; // hauteur de la string s
		curseurMobile.setX(ecartDroit + curseurMobile.getX());
		ArrayList<String> l = (ArrayList<String>) t.getInfo("variables");
		for (String var : l){
			s=var ;
			curseurMobile = curseurMobile.down(tailleTexte.getY() + tailleTexte.getY()/2);
		}
		
		// 2e trait de s�paration
		curseurMobile = curseurMobile.down(tailleTexte.getY()/2);
		
		// Constructeur(s)
		l = (ArrayList<String>) t.getInfo("constructors");
		for (String constructor : l) {
			s = constructor;
			curseurMobile = curseurMobile.down(tailleTexte.getY() + tailleTexte.getY()/2);
		}
		
		// M�thode(s)
		l = (ArrayList<String>) t.getInfo("methods");
		for (String method : l) {
			s = method;
			curseurMobile = curseurMobile.down(tailleTexte.getY() + tailleTexte.getY()/3);
		}
		return (curseurMobile.getY() - depart.getY());
	}
}
