package view;

/**
 * Enumeration des intitules des element du menu
 * @author Boris Ho, Arthur Serre
 */
public enum MenuObjet {

	/**
	 * Intitule de l'element Effacer du menu
	 */
	mEffacer("Effacer"),
	/**
	 * Intitule de l'element Tout effacer du menu
	 */
	mTtEffacer("Tout effacer"),
	/**
	 * Intitule de l'element Aide du menu
	 */
	mAide("Aide"),
	/**
	 * Intitule de l'element Resoudre du menu
	 */
	mResoudre("Resoudre"),
	/**
	 * Intitule de l'element Sauver du menu
	 */
	mSauver("Sauver"),
	/**
	 * Intitule de l'element Charger du menu
	 */
	mCharger("Charger"),
	/**
	 * Intitule de l'element Sudoku precedent du menu
	 */
	mPrecedent("Sudoku precedent"),
	/**
	 * Intitule de l'element Sudoku suivant du menu
	 */
	mSuivant("Sudoku suivant"),
	/**
	 * Intitule de l'element Quitter du menu
	 */
	mQuitter("Quitter");

	/**
	 * Intitule de l'element
	 */
	private String nom;

	/**
	 * Constructeur d'un intitule d'un element du menu
	 * @param nom
	 * Intitule du menu
	 */
	private MenuObjet(String nom){
		this.nom = nom;
	}

	public String nom(){
		return this.nom;
	}
}
