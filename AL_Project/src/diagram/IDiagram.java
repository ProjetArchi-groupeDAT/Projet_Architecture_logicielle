package diagram;

import type.Type;

/**
 * Cette interface repr�sente notre diagramme avec l'ensemble des composants UML
 * 
 * Les composants sont repr�sent�s par des types
 *
 */

public interface IDiagram {
	
	// Permet l'ajout d'un type dans le diagramme
	public void addType(Type type);
	
	// Permet L'insertion d'un diagramme � l'int�rieur d'un autre diagramme
	public void insertDiagram(IDiagram diagram);
	
}
