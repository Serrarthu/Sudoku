package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Chargement;
import controller.Solver;
import model.ErreurAction;
import model.Grille;
/**
 * Ecouteur des JButton declares dans l'IHM
 * @author Boris Ho, Arthur Serre
 * @see IHM
 */
public class EcouteurBouton implements ActionListener {

	/**
	 * Panel menu
	 */
	public JPanel panelMenu;
	/**
	 * Tableau de Case
	 * @see Case
	 */
	public Case[][] textChiffres;
	/**
	 * Liste des elements ChoixMenu du menu
	 * @see ChoixMenu
	 */
	public ArrayList<ChoixMenu> menuObjets;

	/**
	 * Constructeur de l'ecouteur
	 * @param textChiffres
	 * Tableau de Case
	 * @param panelMenu
	 * Panel menu
	 * @param menuObjets
	 * Liste des elements ChoixMenu du menu
	 */
	public EcouteurBouton(Case[][] textChiffres, JPanel panelMenu, ArrayList<ChoixMenu> menuObjets) {
		this.textChiffres = textChiffres;
		this.panelMenu = panelMenu;
		this.menuObjets = menuObjets;
	}

	/**
	 * Bouton Haut: deplace la selection du sudoku ou du menu vers le haut
	 * Bouton Bas: deplace la selection du sudoku ou du menu vers le bas
	 * Bouton Gauche: deplace la selection du sudoku vers la gauche
	 * Bouton Droite: deplace la selection du sudoku vers la droite
	 * Bouton 1: change la valeur a 1 de la case selectionnee si l'action est autorisee
	 * Bouton 2: change la valeur a 2 de la case selectionnee si l'action est autorisee
	 * Bouton 3: change la valeur a 3 de la case selectionnee si l'action est autorisee
	 * Bouton 4: change la valeur a 4 de la case selectionnee si l'action est autorisee
	 * Bouton 5: change la valeur a 5 de la case selectionnee si l'action est autorisee
	 * Bouton 6: change la valeur a 6 de la case selectionnee si l'action est autorisee
	 * Bouton 7: change la valeur a 7 de la case selectionnee si l'action est autorisee
	 * Bouton 8: change la valeur a 8 de la case selectionnee si l'action est autorisee
	 * Bouton 9: change la valeur a 9 de la case selectionnee si l'action est autorisee
	 * Bouton 0,*,#: aucune action, les boutons sont present pour representer l'aspect d'un telephone
	 * Bouton Menu: ouvre ou ferme le menu
	 * Bouton Ok: applique le choix du menu si celui-ci est ouvert
	 * 			  Effacer: efface la case selectionnee
	 * 			  Tout effacer: efface toutes les cases et reinitialise la grille
	 * 			  Aide: devoile la valeur de la case selectionee
	 * 			  Resoudre: devoile toutes les cases et resoud la grille
	 * 			  Sudoku precedent: navigue dans la liste des grilles, decremente le compteur numGrille pour afficher la grille precedente
	 * 			  Sudoku suivant:  navigue dans la liste des grilles, incremente le compteur numGrille pour afficher la grille suivante
	 * 			  Quitter: Termine et ferme le programme
	 * @see Bouton
	 */
	public void actionPerformed(ActionEvent e) {
		IHM.actualiser(textChiffres);
		int iSelected = 0;
		int jSelected = 0;
		int hSelected = 0;
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				if (IHM.listeGrillesIHM.get(IHM.numGrille)[i][j].isSelected()){
					iSelected = i;
					jSelected = j;
				}
			}
		}
		if (((JButton)e.getSource()).getText().equals(Bouton.bHaut.nom())){
			if(panelMenu.isVisible()==false && iSelected!=0){
				IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected][jSelected].setSelected(false);
				IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected-1][jSelected].setSelected(true);
				IHM.actualiser(textChiffres);
			}
			else if (panelMenu.isVisible()==true){
				for (int h=0;h<menuObjets.size();h++){
					if (menuObjets.get(h).isSelected()) hSelected = h;
				}
				if (hSelected!=0){
					menuObjets.get(hSelected).setSelected2(false);
					menuObjets.get(hSelected-1).setSelected2(true);
				}
				else{
					menuObjets.get(hSelected).setSelected2(false);
					menuObjets.get(menuObjets.size()-1).setSelected2(true);
				}
			}
		}
		else if (((JButton)e.getSource()).getText().equals(Bouton.bBas.nom())){
			if(panelMenu.isVisible()==false && iSelected!=8){
				IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected][jSelected].setSelected(false);
				IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected+1][jSelected].setSelected(true);
				IHM.actualiser(textChiffres);
			}
			else if (panelMenu.isVisible()==true){
				for (int h=0;h<menuObjets.size();h++){
					if (menuObjets.get(h).isSelected()) hSelected = h;
				}
				if (hSelected!=menuObjets.size()-1){
					menuObjets.get(hSelected).setSelected2(false);
					menuObjets.get(hSelected+1).setSelected2(true);
				}
				else{
					menuObjets.get(hSelected).setSelected2(false);
					menuObjets.get(0).setSelected2(true);
				}
			}
		}
		else if (((JButton)e.getSource()).getText().equals(Bouton.bGauche.nom())){
			if(panelMenu.isVisible()==false && jSelected!=0){
				IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected][jSelected].setSelected(false);
				IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected][jSelected-1].setSelected(true);
				IHM.actualiser(textChiffres);
			}
		}
		else if (((JButton)e.getSource()).getText().equals(Bouton.bDroite.nom())){
			if (panelMenu.isVisible()==false && jSelected!=8){
				IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected][jSelected].setSelected(false);
				IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected][jSelected+1].setSelected(true);
				IHM.actualiser(textChiffres);
			}
		}
		else if (((JButton)e.getSource()).getText().equals(Bouton.bMenu.nom())){
			panelMenu.setVisible(!panelMenu.isVisible());
		}
		else if (((JButton)e.getSource()).getText().equals(Bouton.bOk.nom())){
			if(panelMenu.isVisible()==true){
				int kSelected = 0;
				for(int k=0;k<menuObjets.size();k++){
					if (menuObjets.get(k).isSelected()) kSelected = k;
				}
				switch(kSelected){
				case 0: 
					if (textChiffres[iSelected][jSelected].isModifiable())  {
						IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected][jSelected].setText(" ");
						Chargement.listeGrilles.get(IHM.numGrille).set(iSelected,jSelected, 0);
					}
					IHM.actualiser(textChiffres);
					break;
				case 1: 
					for (int i=0;i<9;i++){
						for (int j=0;j<9;j++){
							if (textChiffres[i][j].isModifiable())  {
								IHM.listeGrillesIHM.get(IHM.numGrille)[i][j].setText(" ");
								Chargement.listeGrilles.get(IHM.numGrille).set(i,j, 0);
							}
						}
					}
					IHM.actualiser(textChiffres);
					break;
				case 2: 
					for (int i=0;i<9;i++){
						for (int j=0;j<9;j++){
							if (textChiffres[i][j].isModifiable())  {
								IHM.listeGrillesIHM.get(IHM.numGrille)[i][j].setText(" ");
								Chargement.listeGrilles.get(IHM.numGrille).set(i,j, 0);
							}
						}
					}
					Grille solution2 = new Grille(Chargement.listeGrilles.get(IHM.numGrille).getGrille());
					Solver ex2 = new Solver();
					ex2.solve(solution2);
					IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected][jSelected].setText(""+solution2.getValue(iSelected,jSelected));
					Chargement.listeGrilles.get(IHM.numGrille).set(iSelected,jSelected,solution2.getValue(iSelected,jSelected));
					IHM.actualiser(textChiffres);
					break;
				case 3: 
					for (int i=0;i<9;i++){
						for (int j=0;j<9;j++){
							if (textChiffres[i][j].isModifiable())  {
								IHM.listeGrillesIHM.get(IHM.numGrille)[i][j].setText(" ");
								Chargement.listeGrilles.get(IHM.numGrille).set(i,j, 0);
							}
						}
					}
					Grille solution = new Grille(Chargement.listeGrilles.get(IHM.numGrille).getGrille());
					Solver ex = new Solver();
					ex.solve(solution);
					for (int i=0;i<9;i++){
						for (int j=0;j<9;j++){
							IHM.listeGrillesIHM.get(IHM.numGrille)[i][j].setText(""+solution.getValue(i,j));
							Chargement.listeGrilles.get(IHM.numGrille).set(i,j,solution.getValue(i,j));
						}
					}
					IHM.actualiser(textChiffres);
					break;
				case 4: 
					IHM.sauvegarder();
					break;
				case 5: 
					IHM.restaurer();
					IHM.actualiser(textChiffres);
					break;
				case 6: 
					if (IHM.numGrille!=0) {
						IHM.numGrille = IHM.numGrille-1;
						IHM.actualiser(textChiffres);
					}
					break;
				case 7: 
					if (IHM.numGrille!=Chargement.listeGrilles.size()-1) {
						IHM.numGrille++;
						IHM.actualiser(textChiffres);
					}
					break;
				case 8: 
					System.exit(0);
					break;
				default: 
					break;
				}
				panelMenu.setVisible(false);
			}
		}
		else if (((JButton)e.getSource()).getText().equals(Bouton.b0.nom()) || ((JButton)e.getSource()).getText().equals(Bouton.bEtoile.nom()) || ((JButton)e.getSource()).getText().equals(Bouton.bDiese.nom())){

		}
		else if (Integer.parseInt(((JButton)e.getSource()).getText())<9) {
			if(panelMenu.isVisible()==false){
				if(Chargement.listeGrilles.get(IHM.numGrille).isLegal(iSelected,jSelected, Integer.parseInt(((JButton)e.getSource()).getText())).equals(ErreurAction.CORRECT)){
					if (textChiffres[iSelected][jSelected].isModifiable()) {
						IHM.listeGrillesIHM.get(IHM.numGrille)[iSelected][jSelected].setText(""+Integer.parseInt(((JButton)e.getSource()).getText()));
						Chargement.listeGrilles.get(IHM.numGrille).set(iSelected,jSelected, Integer.parseInt(((JButton)e.getSource()).getText()));
					}
					IHM.actualiser(textChiffres);
				}
				else if (textChiffres[iSelected][jSelected].isModifiable() && Chargement.listeGrilles.get(IHM.numGrille).isLegal(iSelected,jSelected, Integer.parseInt(((JButton)e.getSource()).getText())).equals(ErreurAction.LIGNE)){
					for(int j=0;j<9;j++){
						if (((JButton)e.getSource()).getText().equals(textChiffres[iSelected][j].getText())) {
							textChiffres[iSelected][j].setBackground(Color.GRAY);
							textChiffres[iSelected][j].updateUI();
						}
					}
				}
				else if (textChiffres[iSelected][jSelected].isModifiable() && Chargement.listeGrilles.get(IHM.numGrille).isLegal(iSelected,jSelected, Integer.parseInt(((JButton)e.getSource()).getText())).equals(ErreurAction.COLONNE)){
					for(int i=0;i<9;i++){
						if (((JButton)e.getSource()).getText().equals(textChiffres[i][jSelected].getText())) {
							textChiffres[i][jSelected].setBackground(Color.GRAY);
							textChiffres[i][jSelected].updateUI();
						}
					}
				}
				else if (Chargement.listeGrilles.get(IHM.numGrille).isLegal(iSelected,jSelected, Integer.parseInt(((JButton)e.getSource()).getText())).equals(ErreurAction.SUB)){
					for (int i=(Integer)(iSelected/3)*3;i<(Integer)((iSelected/3)+1)*3;i++){
						for (int j=(Integer)(jSelected/3)*3;j<(Integer)((jSelected/3)+1)*3;j++){
							if (((JButton)e.getSource()).getText().equals(textChiffres[i][j].getText())) {
								textChiffres[i][j].setBackground(Color.GRAY);
								textChiffres[i][j].updateUI();
							}
						}
					}
				}
			}
		}
	}
}
