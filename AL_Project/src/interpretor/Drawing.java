package interpretor;

import diagram.IDiagram;

/**
 * Cette interface repr�sente l'interpr�tation de notre langage
 * 
 * C'est ici que l'on interpr�te notre langage (sous forme de SVG ou de texte...)
 *
 */

public interface Drawing {

	/** Permet d'afficher le dessin sous la forme demand�e (XML, texte etc..)  */
	public void display();
}
