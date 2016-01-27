package view;

/**
 * Enumeration des intitules des boutons du telephone
 * @author Boris Ho, Arthur Serre
 */
public enum Bouton {

	/**
	 * Intitule de la touche 1 du clavier numerique telephone
	 */
	b1("1"),
	/**
	 * Intitule de la touche 2 du clavier numerique telephone
	 */
	b2("2"),
	/**
	 * Intitule de la touche 3 du clavier numerique telephone
	 */
	b3("3"),
	/**
	 * Intitule de la touche 4 du clavier numerique telephone
	 */
	b4("4"),
	/**
	 * Intitule de la touche 5 du clavier numerique telephone
	 */
	b5("5"),
	/**
	 * Intitule de la touche 6 du clavier numerique telephone
	 */
	b6("6"),
	/**
	 * Intitule de la touche 7 du clavier numerique telephone
	 */
	b7("7"),
	/**
	 * Intitule de la touche 8 du clavier numerique telephone
	 */
	b8("8"),
	/**
	 * Intitule de la touche 9 du clavier numerique telephone
	 */
	b9("9"),
	/**
	 * Intitule de la touche * du clavier numerique telephone
	 */
	bEtoile("*"),
	/**
	 * Intitule de la touche 0 du clavier numerique telephone
	 */
	b0("0"),
	/**
	 * Intitule de la touche # du clavier numerique telephone
	 */
	bDiese("#"),
	/**
	 * Intitule de la touche droite Ok du telephone
	 */
	bOk("Ok"),
	/**
	 * Intitule de la touche Fleche directionnelle Haut du telephone
	 */
	bHaut("Haut"),
	/**
	 * Intitule de la touche Fleche directionnelle Bas du telephone
	 */
	bBas("Bas"),
	/**
	 * Intitule de la touche Fleche directionnelle Gauche du telephone
	 */
	bGauche("Gauche"),
	/**
	 * Intitule de la touche Fleche directionnelle Droite du telephone
	 */
	bDroite("Droite"),
	/**
	 * Intitule de la touche gauche Menu du telephone
	 */
	bMenu("Menu");	
	
	/**
	 * Intitule du bouton
	 */
	private String nom;
	
	/**
	 * Constructeur d'un intitule de bouton
	 * @param nom
	 * Intitule du bouton
	 */
	private Bouton(String nom){
		this.nom = nom;
	}
	
	public String nom(){
		return this.nom;
	}
}
