package interpretor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import type.Type;
import diagram.IDiagram;

/**
 * Cette classe repr�sente une repr�sentation du diagramme en utilisant la librairie batik
 * 
 */

public class DrawingBatik implements Drawing {

	private IDiagram diagram;
	SVGGraphics2D svgGenerator ;
	
	static int largeur = 200 ;
	
	public DrawingBatik(IDiagram diagram) {
		this.diagram = diagram;

		// Cr�ation document SVG
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
		SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);

		// Cr�ation convertisseur pour ce document
		this.svgGenerator = new SVGGraphics2D(doc);
	}
	/**
	 * Cette m�thode donne la taille du texte qui est �crit (largeur - hauteur)
	 */
	public static Curseur tailleTexte(String texte){
		//String texte = "Hello World";
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		Font font = new Font("Tahoma", Font.PLAIN, 12);
		int textwidth = (int)(font.getStringBounds(texte, frc).getWidth());
		int textheight = (int)(font.getStringBounds(texte, frc).getHeight());
		System.out.println("largeur texte : " + textwidth);
		System.out.println("hauteur texte : " + textheight);
		return new Curseur(textwidth, textheight);
	}
	
	public static int placeMilieuX (String texte){
		Curseur taille = tailleTexte(texte);
		return (largeur/2 - taille.getX()/2) ;
	}
	
	public void populateRootDocument(SVGDocument doc){
		// Populate the document root with the generated SVG content.
		Element root = doc.getDocumentElement();
		svgGenerator.getRoot(root);
	}
	
	@Override
	public void draw() {
		if (!diagram.isEmpty()){
			for (int i = 0 ; i < diagram.getTypes().size() ; i++){
				Type t = diagram.getType(i);
				if(t.getType()=="Class"){
					this.etiquetterClasse(t);
				}
			}
		}
	}
	
	public void etiquetterClasse(Type t){
		Curseur depart = new Curseur(10,10);
		Curseur curseurMobile = depart ;
		
		// On d�finit la couleur 
		svgGenerator.setPaint(Color.black);
		
		// svgGenerator.drawRect(curseurMobile.getX(), curseurMobile.getY(), largeur, hauteur);
		
		// Texte � placer : 1�re partie
		String s = ("<< Java Class >>") ;
		Curseur tailleTexte = tailleTexte(s);
		svgGenerator.drawString(s, curseurMobile.getX() + placeMilieuX(s), curseurMobile.getY() + tailleTexte.getY());
		curseurMobile = curseurMobile.down(tailleTexte.getY());
		
		s=(String) t.getInfo("name");
		tailleTexte = tailleTexte(s);
		svgGenerator.drawString(s, depart.getX() + placeMilieuX(s), curseurMobile.getY() + tailleTexte.getY());
		curseurMobile = curseurMobile.down(tailleTexte.getY());
		
		s=(String) t.getInfo("package");
		tailleTexte = tailleTexte(s);
		svgGenerator.drawString(s, depart.getX() + placeMilieuX(s), curseurMobile.getY() + tailleTexte.getY());
		curseurMobile = curseurMobile.down(tailleTexte.getY());
		
		// 1er trait
		curseurMobile = curseurMobile.down(tailleTexte.getY()/2);
		svgGenerator.drawLine(depart.getX(), curseurMobile.getY(), depart.getX() + largeur, curseurMobile.getY());
		
		// Variables d'instances
		int ecartDroit = tailleTexte(s).getY() ; // hauteur de la string s
		curseurMobile.setX(ecartDroit);
		ArrayList<String> l = (ArrayList<String>) t.getInfo("variables");
		for (String var : l){
			s=var ;
			svgGenerator.drawString(s, curseurMobile.getX(), curseurMobile.getY() + tailleTexte.getY());
			curseurMobile = curseurMobile.down(tailleTexte.getY() + tailleTexte.getY()/2);
		}
		
		// 2e trait
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
			curseurMobile = curseurMobile.down(tailleTexte.getY() + tailleTexte.getY()/2);
		}
		// Trac� du rectangle
		//curseurMobile = curseurMobile.down(tailleTexte.getY()/2);
		svgGenerator.drawRect(depart.getX(), depart.getY(), largeur, curseurMobile.getY());
		
		svgGenerator.setSVGCanvasSize(new Dimension(500, 500));
	}
}
