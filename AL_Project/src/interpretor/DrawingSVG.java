package interpretor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import tools.Placement;
import type.Type;
import diagram.EmptyDiagramException;
import diagram.IDiagram;

/**
 * Cette classe permet une r�pr�sentation du diagramme en utilisant la librairie batik
 * 
 * On utilise un objet de type SVGGraphics2D que l'on remplit
 * 
 */

public class DrawingSVG implements Drawing {

	private IDiagram diagram;
	private SVGGraphics2D svgGenerator;
	private PaintBrush paintBrush ;

	static int largeur = 200;

	public DrawingSVG(IDiagram diagram, PaintBrush p) {
		this.diagram = diagram;
		this.paintBrush=p;
		this.generateAndDraw();
	}

	/**
	 * On g�n�re le fichier SVG et on le remplit (en dessinant)
	 * 
	 */
	public void generateAndDraw() {
		// Cr�ation document SVG
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
		SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);

		// Cr�ation convertisseur pour ce document
		this.svgGenerator = new SVGGraphics2D(doc);

		// On remplit l'objet avec les types contenus dans le diagramme
		try {
			this.draw();
			// Populate the document root with the generated SVG content.
			Element root = doc.getDocumentElement();
			svgGenerator.getRoot(root);
		} catch (EmptyDiagramException e) {
			e.printStackTrace();
		}
	}

	public IDiagram getDiagram() {
		return diagram;
	}

	public SVGGraphics2D getSvgGraphics() {
		return svgGenerator;
	}

	@Override
	public void draw() throws EmptyDiagramException {
		if (!diagram.isEmpty()) {
			
			// Cr�ation d'une instance de placement
			Placement p = new Placement();
			// Ce curseur repr�sente le point sup�rieur gauche du premier objet (classe ou interface)
			Curseur depart = new Curseur(10, 10) ;
			// Ce curseur repr�sente le point sup�rieur gauche de l'objet courant 
			Curseur coinSuperieurGauche = new Curseur(10, 10) ;
			
			for (int i = 0; i < diagram.getTypes().size(); i++) {
				Type t = diagram.getTypes().get(i);
				if (t.getType() == "Class" || t.getType() == "Interface") {
					coinSuperieurGauche.display();
					this.etiquetter(t,coinSuperieurGauche);
					p.algoPlacement();
					coinSuperieurGauche.setX(depart.getX() + p.getPlacementHorizontal()*3*largeur/2);
					coinSuperieurGauche.setY(depart.getY() + p.getLigneCourante()*4*largeur/3);
				}
			}
		}
	}
	
	/**
	 * cette m�thode permet de cr�er le rectangle contenant les informations du
	 * type et de le remplir avec les couleurs associ�es
	 * 
	 */
	public void remplissage(Type t, Curseur c) {
		// Calcul de la hauteur du rectangle
		int hauteur = Placement.calculHauteur(c, t);

		// On commence par remplir le rectangle
		svgGenerator.setPaint(this.paintBrush.getCouleurRemplissage());
		svgGenerator.fill(new Rectangle(c.getX(), c.getY(), largeur, hauteur));

		// On trace ensuite le contour de ce rectangle
		svgGenerator.setPaint(this.paintBrush.getCouleurContour());
		svgGenerator.draw(new Rectangle(c.getX(), c.getY(), largeur, hauteur));
	}
	
	public void etiquetter(Type t, Curseur coin){
		Curseur depart = coin;
		Curseur curseurMobile = coin ;
		
		// Cr�ation et remplissage du rectangle
		this.remplissage(t, depart);
		
		// Texte � placer : 1�re partie
		String s = ("<< Java " + t.getType() + " >>") ;
		Curseur tailleTexte = Placement.tailleTexte(s);
		svgGenerator.drawString(s, curseurMobile.getX() + Placement.placeMilieuX(s,largeur), curseurMobile.getY() + tailleTexte.getY());
		curseurMobile = curseurMobile.down(tailleTexte.getY());
		
		s=(String) t.getInfo("name");
		tailleTexte = Placement.tailleTexte(s);
		svgGenerator.drawString(s, depart.getX() + Placement.placeMilieuX(s,largeur), curseurMobile.getY() + tailleTexte.getY());
		curseurMobile = curseurMobile.down(tailleTexte.getY());
		
		s=(String) t.getInfo("package");
		tailleTexte = Placement.tailleTexte(s);
		svgGenerator.drawString(s, depart.getX() + Placement.placeMilieuX(s,largeur), curseurMobile.getY() + tailleTexte.getY());
		curseurMobile = curseurMobile.down(tailleTexte.getY());
		
		// 1er trait de s�paration
		curseurMobile = curseurMobile.down(tailleTexte.getY()/2);
		svgGenerator.drawLine(depart.getX(), curseurMobile.getY(), depart.getX() + largeur, curseurMobile.getY());
		
		// Variables d'instances
		int ecartDroit = Placement.tailleTexte(s).getY()/2 ; // hauteur de la string s
		curseurMobile.setX(ecartDroit + curseurMobile.getX());
		ArrayList<String> l = (ArrayList<String>) t.getInfo("variables");
		for (String var : l){
			s=var ;
			svgGenerator.drawString(s, curseurMobile.getX(), curseurMobile.getY() + tailleTexte.getY());
			curseurMobile = curseurMobile.down(tailleTexte.getY() + tailleTexte.getY()/2);
		}
		
		// 2e trait de s�paration
		curseurMobile = curseurMobile.down(tailleTexte.getY()/2);
		svgGenerator.drawLine(depart.getX(), curseurMobile.getY(), depart.getX() + largeur, curseurMobile.getY());
		
		// Constructeur(s)
		l = (ArrayList<String>) t.getInfo("constructors");
		for (String constructor : l) {
			s = constructor;
			svgGenerator.drawString(s, curseurMobile.getX(),
					curseurMobile.getY() + tailleTexte.getY());
			curseurMobile = curseurMobile.down(tailleTexte.getY() + tailleTexte.getY()/2);
		}
		
		// M�thode(s)
		l = (ArrayList<String>) t.getInfo("methods");
		for (String method : l) {
			s = method;
			svgGenerator.drawString(s, curseurMobile.getX(),
					curseurMobile.getY() + tailleTexte.getY());
			curseurMobile = curseurMobile.down(tailleTexte.getY() + tailleTexte.getY()/3);
		}
		svgGenerator.setSVGCanvasSize(new Dimension(500, 500));
	}
	
}