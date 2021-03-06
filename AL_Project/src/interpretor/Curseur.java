package interpretor;

/**
 * Cette classe d�signe un point mobile qui va nous permettre de calculer les
 * positions des �l�ments graphiques SVG
 * 
 */
public class Curseur {

	private int X;
	private int Y;

	public Curseur(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}
	
	public Curseur() {
		this(0,0);
	}
	
	public Curseur(Curseur c){
		this(c.getX(), c.getY());
	}

	public int getX() {
		return X;
	}

	public void setX(int X) {
		this.X = X;
	}

	public int getY() {
		return Y;
	}

	public void setY(int Y) {
		this.Y = Y;
	}

	// On ajuste les coordonn�es du point pour placer la premi�re �tiquette

	public Curseur ajuster() {
		return new Curseur(this.getX() + 15, this.getY() + 20);
	}
	
	// On utilise cette m�thode afin de placer un texte en dessous de la position du curseur
	public Curseur down(int val) {
		return new Curseur(this.getX(), this.getY() + val);
	}
	
	public Curseur right() {
		return new Curseur(this.getX() + 25, this.getY() );
	}
	
	// Affichage des coordonn�es du point
	public void display(){
		System.out.println("( X : " + this.getX() + " , Y : " + this.getY() + " )");
	}

}
