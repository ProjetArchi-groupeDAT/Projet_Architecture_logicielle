package type;

/**
 * Le Type repr�sente les objets de notre diagramme (les composants UML tels que
 * la classe, les fl�ches etc..)
 *
 */

public interface Type {

	/** Permet d'obtenir l'information relative � la cha�ne de caract�re s  */
	public Object getInfo(String s);
	
	/** Permet d'obtenir la nature du type (Classe, Interface ...) */
	public String getType();

}
