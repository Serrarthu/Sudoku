package model;

/**
 * Enumeration des type d'erreur lors de la verification de la validite d'une action
 * @author Boris Ho, Arthur Serre
 */
public enum ErreurAction {
	/**
	 * Valeur non ajoutée car valeur deja presente dans la meme colonne
	 */
	COLONNE,
	/**
	 * Valeur non ajoutée car valeur deja presente dans la meme ligne
	 */
	LIGNE,
	/**
	 * Valeur non ajoutée car valeur deja presente dans le meme carre
	 */
	SUB,
	/**
	 * Correct, la valeur peut etre ajoutee
	 */
	CORRECT;
	
}
