# Projet_Architecture_logicielle

Le projet vise à définir un langage embarqué dédié au dessin de diagrammes de types.

Installation :

    1. Télécharger et importer le .zip du projet sur Eclipse 
    2. Il convient pour pouvoir exécuter le projet de disposer du plugin Maven sur Eclipse
    3. Toutes les dépendances (librairies externes ici) sont gérées dans le fichier pom.xml
        

Configuration et Utilisation :

    1. Pour afficher les informations d'une ou plusieurs classes dans la console :
        1.1 Se rendre dans la classe Interpretor.DrawingText
        1.2 Dans la méthode Main, une ligne de code " listeTypes.add(new MyClass("type.Relation")); " permet de définir une classe à décrire, le
            nom de cette classe est ici "type.Relation".
            1.2.1 Remplacez donc "type.Relation" dans cette ligne de code pour afficher la classe voulue.
            1.2.2 Copier cette ligne et faites l'étape 1.1 pour ajouter une nouvelle classe à décrire.
            1.2.3 Supprimer cette ligne pour enlever la description d'une classe.
        1.3 Lancer le main de la classe Interpretor.DrawingText.
    
    2. Pour afficher dans une fenêtre graphique le diagramme UML (sous format SVG) :
        2.1 Se rendre dans la classe Tests.TestDisplaySVG
        2.2 Dans le constructeur TestDisplaySVG(), la ligne de code " listeTypes.add(new MyClass("type.Relation")); " permet de définir une classe à décrire, le nom de cette classe est ici "type.Relation".
            2.2.1 Remplacez "type.Relation" dans cette ligne de code pour afficher la classe voulue.
            2.2.2 Copier cette ligne et faites l'étape 1.1 pour ajouter une nouvelle classe à décrire.
            2.2.3 Supprimer cette ligne pour enlever la description d'une classe.
        2.3 Vous modifier les couleurs du pinceau afin d'obtenir un rendu avec les couleurs souhaitées.
        2.4 Lancer la méthode main de la classe Tests.WindowSVG ( une pop-up contiendra le diagramme à ce moment là ).
    
Fichiers présents :

    - README
    - Le projet AL_Project
    - Le fichier .jar Batik-bin-1.8.zip nécessaire à l'utilisation du projet ( dans le dossier AL_Project )
    
Contacts :

    - ali.belghiti@etudiant.mines-nantes.fr
    - david.hartke@etudiant.mines-nantes.fr
    - tom.bigault@etudiant.mines-nantes.fr
        
Bugs connus :
    
    Aucun

Améliorations possibles :
    
    -Ajouter une interface pour définir les classes à dessiner
    -Rendre le code plus modulaire afin de simplifier les fonctionnalités de dessin associées à l'ajout d'un type
