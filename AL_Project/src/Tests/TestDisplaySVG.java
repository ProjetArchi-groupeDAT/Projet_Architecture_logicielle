package Tests;

import interpretor.DrawingBatik;

import java.util.ArrayList;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;

import diagram.CurrentDiagram;
import type.MyClass;
import type.Type;

public class TestDisplaySVG {
	
	JSVGCanvas canvas ;
	
	public TestDisplaySVG() {
		
		// Cr�ation d'un diagramme et du dessin associ� sous format SVG
		ArrayList<Type> listeTypes = new ArrayList<>();
		listeTypes.add(new MyClass("type.Relation"));
		listeTypes.add(new MyClass("diagram.CurrentDiagram"));
		CurrentDiagram diag = new CurrentDiagram(listeTypes, "diagramme test");
		DrawingBatik drawingSVG = new DrawingBatik(diag);
		
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