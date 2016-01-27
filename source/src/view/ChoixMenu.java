package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;

/**
 * Element du menu
 * ChoixMenu est un JMenuItem
 * Si l'element est selectionne, le fond est gris, sinon il est clair
 * @author Boris Ho, Arthur Serre
 *
 */
public class ChoixMenu extends JMenuItem{

	private static final long serialVersionUID = -1640728546365261378L;
	/**
	 * True si l'objet est selectionne, false sinon
	 */
	protected boolean selected;
	
	/**
	 * Constructeur d'un element du menu
	 * @param nom
	 * Intitule de l'element
	 */
	public ChoixMenu(String nom){
		super(nom);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		this.setBackground(Color.WHITE);
		selected = false;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected2(boolean selected) {
		this.selected = selected;
		if (selected) this.setBackground(Color.LIGHT_GRAY);
		else this.setBackground(Color.WHITE);
	}

}
