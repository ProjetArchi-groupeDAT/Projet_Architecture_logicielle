package Tests;

import interpretor.DrawingSVG;
import interpretor.PaintBrush;

import java.awt.Color;
import java.util.ArrayList;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;

import diagram.CurrentDiagram;
import type.MyClass;
import type.MyInterface;
import type.Relation;
import type.Type;

/**
 * Classe de test pour la représentation en SVG
 * 
 */
public class TestDisplaySVG {
	
	JSVGCanvas canvas ;
	
	public TestDisplaySVG() {
		
		// Création d'un diagramme et du dessin associé sous format SVG
		
		Type typeClasse = new MyClass("type.MyClass");
		Type type = new MyInterface("type.Type");
		
		Type currentDiag = new MyClass("diagram.CurrentDiagram");
		Type Idiag = new MyInterface("diagram.IDiagram");
		
		ArrayList<Type> listeTypes = new ArrayList<>();
		listeTypes.add(new MyClass("type.Relation"));
		listeTypes.add(new MyClass("diagram.CurrentDiagram"));
		listeTypes.add(new MyClass("type.MyInterface"));
		listeTypes.add(new MyClass("diagram.EmptyDiagram"));
		listeTypes.add(typeClasse);
		listeTypes.add(type);
		listeTypes.add(new MyInterface("diagram.IDiagram"));
		listeTypes.add(new MyInterface("interpretor.Drawing"));
		listeTypes.add(new MyInterface("diagram.IDiagram"));
		listeTypes.add(new Relation(typeClasse, type));
		listeTypes.add(new Relation(currentDiag, Idiag));
		
		CurrentDiagram diag = new CurrentDiagram(listeTypes, "diagramme test");
		
		Color couleurContour = Color.black;
		Color couleurRemplissage = new Color(255, 252, 191);
		int epaisseurTrait = 3 ;
		PaintBrush p = new PaintBrush(epaisseurTrait, couleurContour, couleurRemplissage);
		DrawingSVG drawingSVG = new DrawingSVG(diag, p);
		
		// On affecte le document SVG au canvas
		SVGDocument doc = (SVGDocument) drawingSVG.getSvgGraphics().getDOMFactory();
		canvas = new JSVGCanvas();
		canvas.setSVGDocument(doc);
	}

	public JSVGCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(JSVGCanvas canvas) {
		this.canvas = canvas;
	}
	
}
