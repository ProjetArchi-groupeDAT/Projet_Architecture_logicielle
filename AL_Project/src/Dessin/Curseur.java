package Dessin;

public class Curseur {

	private int X;
	private int Y;

	public Curseur(int X, int Y) {
		this.X = X;
		this.Y = Y;
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
	public Curseur down() {
		return new Curseur(this.getX(), this.getY() + 20);
	}
	
	public Curseur right() {
		return new Curseur(this.getX() + 25, this.getY() );
	}

}
