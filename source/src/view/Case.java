package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Case d'un Sudoku a afficher
 * Case est un JTextField
 * Une case a un fond blanc et une bordure noire
 * Si la case est selectionnable, le fond est gris
 * Si la case est modifiable, la police est en gras
 * @author Boris Ho, Arthur Serre
 */
public class Case extends JTextField implements java.io.Serializable{

	private static final long serialVersionUID = -9017143116229397815L;
	/**
	 * True si la case est selectionnee, false sinon
	 */
	protected boolean selected;
	/**
	 * True si la case est modifiable par l'utilisateur, false sinon
	 */
	protected boolean modifiable;

	/**
	 * Constructeur d'une case qui construit une case a partir d'un nom
	 * @param text
	 * Intitule de la case, egal au chiffre, ou a un espace si la case est vide
	 */
	public Case(String text){
		super(text);
		if (text.equals("0")) {
			this.setText(" ");
			this.modifiable = true;
			Font police = new Font(UIManager.getDefaults().getFont("TabbedPane.font").getName(),Font.PLAIN, UIManager.getDefaults().getFont("TabbedPane.font").getSize());
			this.setFont(police);
		}
		else {
			this.modifiable = false;
			Font police = new Font(UIManager.getDefaults().getFont("TabbedPane.font").getName(),Font.BOLD, UIManager.getDefaults().getFont("TabbedPane.font").getSize()+2);
			this.setFont(police);
		}
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);
		this.setEditable(false);
		selected = false;
	}

	/**
	 * Constructeur d'une case qui construit une case a aprtir d'une case existante
	 * @param c
	 * Case existante
	 */
	public Case(Case c){

		super(c.getText());
		this.modifiable = c.isModifiable();
		this.selected = c.isSelected();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		actualiser();
	}

	public boolean isSelected(){
		return selected;
	}

	public void setSelected(boolean selected){
		this.selected = selected;
		if (selected) this.setBackground(Color.LIGHT_GRAY);
		else this.setBackground(Color.WHITE);
	}

	public boolean isModifiable() {
		return modifiable;
	}

	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}

	/**
	 * Actualise l'affichage de la case en fonction de ses attributs
	 */
	public void actualiser(){
		if(modifiable){
			Font police = new Font(UIManager.getDefaults().getFont("TabbedPane.font").getName(),Font.PLAIN, UIManager.getDefaults().getFont("TabbedPane.font").getSize());
			this.setFont(police);
		}
		else{
			Font police = new Font(UIManager.getDefaults().getFont("TabbedPane.font").getName(),Font.BOLD, UIManager.getDefaults().getFont("TabbedPane.font").getSize()+2);
			this.setFont(police);
		}
		if (selected){
			this.setBackground(Color.LIGHT_GRAY);
		}
		else{
			this.setBackground(Color.WHITE);
		}
	}
}
