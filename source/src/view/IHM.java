package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import model.Grille;
import controller.Chargement;
import controller.ConvertFileFormat;

/**
 * Fenetre du programme
 * @author Boris Ho, Arthur Serre
 */
public class IHM extends JFrame{

	private static final long serialVersionUID = -2665941513441730095L;

	/**
	 * Indice de la grille actuelle affichee
	 */
	public static int numGrille;
	/**
	 * Liste de grilles sous forme de tableaux de Case
	 * La liste de grilles dans la classe Chargement n'apporte pas n'informations sur l'affichage, cette liste la complete
	 * @see Case
	 * @see controller.Chargement#listeGrilles
	 */
	public static ArrayList<Case[][]> listeGrillesIHM;

	/**
	 * Constructeur de la fenêtre du programme
	 * La fenetre est composee de trois JPanels principaux: l'ecran de jeu, le clavier numerique et les touches de deplacement/menu
	 * Elle affiche la grille d'indice numGrille dont les valeurs sont dans la liste listeGrilles et les parametres d'affichage sont dans la liste listeGrillesIHM
	 * @see EcouteurBouton
	 * @see IHM#listeGrillesIHM
	 * @see controller.Chargement#listeGrilles
	 */
	public IHM(){
		this.setTitle("Sudoku");
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(250, 400));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);

		Case[][] textChiffres = new Case[9][9];
		if (Chargement.listeGrilles.size()!=0){
			for (int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					textChiffres[i][j] = new Case(listeGrillesIHM.get(numGrille)[i][j]);
					textChiffres[i][j].setEditable(false);
				}
			}
		}

		JPanel panelChiffres = new JPanel(new GridLayout(3,3));
		JPanel[][] sousPanelChiffres = new JPanel[3][3];

		for (int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				sousPanelChiffres[i][j] = new JPanel();
				sousPanelChiffres[i][j].setLayout(new GridLayout(3,3));
				for(int k=0;k<3;k++){
					for (int l=0;l<3;l++){
						sousPanelChiffres[i][j].add(textChiffres[k+(i*3)][l+(j*3)]);
					}
				}
				sousPanelChiffres[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				panelChiffres.add(sousPanelChiffres[i][j]);
			}
		}

		JPanel panelMenu = new JPanel(new GridLayout(MenuObjet.values().length,1));
		ArrayList<ChoixMenu> menuObjets = new ArrayList<ChoixMenu>();
		for(int i=0;i<MenuObjet.values().length;i++){
			menuObjets.add(new ChoixMenu(MenuObjet.values()[i].nom()));
			panelMenu.add(menuObjets.get(i));
		}
		menuObjets.get(0).setSelected2(true);
		panelMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		panelMenu.setBounds(0,0,125,195);
		panelMenu.setVisible(false);

		ArrayList<JButton> boutons = new ArrayList<JButton>();
		for(int i=0;i<Bouton.values().length;i++){
			boutons.add(new JButton(Bouton.values()[i].nom()));
			boutons.get(i).addActionListener(new EcouteurBouton(textChiffres,panelMenu,menuObjets));
		}
		JPanel panelBoutonsChiffre = new JPanel(new GridLayout(4,3));
		JPanel panelBoutonsDeplacement = new JPanel(new GridLayout(3,3));
		for(int i=0;i<12;i++){
			panelBoutonsChiffre.add(boutons.get(i));
		}
		panelBoutonsDeplacement.add(boutons.get(Bouton.bMenu.ordinal()));
		panelBoutonsDeplacement.add(boutons.get(Bouton.bHaut.ordinal()));
		panelBoutonsDeplacement.add(boutons.get(Bouton.bOk.ordinal()));
		panelBoutonsDeplacement.add(boutons.get(Bouton.bGauche.ordinal()));
		panelBoutonsDeplacement.add(new JLabel());
		panelBoutonsDeplacement.add(boutons.get(Bouton.bDroite.ordinal()));
		panelBoutonsDeplacement.add(new JLabel());
		panelBoutonsDeplacement.add(boutons.get(Bouton.bBas.ordinal()));
		panelBoutonsDeplacement.add(new JLabel());

		JLayeredPane panelJeu = new JLayeredPane();
		panelJeu.setPreferredSize(new Dimension(250,195));
		panelChiffres.setBounds(0, 0, 250, 195);
		panelJeu.add(panelChiffres, new Integer(0));
		panelJeu.add(panelMenu, new Integer(1));

		this.add(panelJeu,BorderLayout.NORTH);
		this.add(panelBoutonsChiffre,BorderLayout.SOUTH);
		this.add(panelBoutonsDeplacement,BorderLayout.CENTER);

		pack();
		setVisible(true);
	}

	/**
	 * Initialise les grilles
	 * A partir des donnees dans la liste listeGrilles, il construit la liste listeGrillesIHM
	 * Si il ne trouve aucune grille, il ajoute une grille vide a afficher
	 */
	public static void initialisation(){
		numGrille = 0;
		listeGrillesIHM = new ArrayList<Case[][]>();
		if (Chargement.listeGrilles.size()!=0){
			for (int h=0;h<Chargement.listeGrilles.size();h++){
				Case[][] chiffres  = new Case[9][9];
				for (int i=0;i<9;i++){
					for(int j=0;j<9;j++){
						chiffres[i][j] = new Case(""+Chargement.listeGrilles.get(h).getValue(i, j));
						if (chiffres[i][j].getText().equals(" ")) chiffres[i][j].setModifiable(true);
						else chiffres[i][j].setModifiable(false);
						chiffres[i][j].actualiser();
					}
				}
				chiffres[4][4].setSelected(true);
				listeGrillesIHM.add(chiffres);
			}
		}
		else{
			Case[][] chiffres = new Case[9][9];
			for (int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					chiffres[i][j] = new Case("0");
					chiffres[i][j].actualiser();
				}
			}
			chiffres[4][4].setSelected(true);
			listeGrillesIHM.add(chiffres);
			int[][] chiffres2 = new int[9][9];
			for (int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					if (chiffres[i][j].getText().equals(" ")) chiffres2[i][j] = 0;
					else chiffres2[i][j] = Integer.parseInt(chiffres[i][j].getText());
				}
			}
			Chargement.listeGrilles.add(new Grille(chiffres2));
		}
	}

	/**
	 * Actualise l'affichage du tableau de Case a afficher
	 * @param textChiffres
	 * Tableau de Case
	 */
	public static void actualiser(Case[][] textChiffres){
		int iSelected = 0;
		int jSelected = 0;
		for (int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if (listeGrillesIHM.get(numGrille)[i][j].isSelected()) {
					iSelected = i;
					jSelected = j;
				}
				textChiffres[i][j].setText(listeGrillesIHM.get(numGrille)[i][j].getText());
				textChiffres[i][j].setModifiable(listeGrillesIHM.get(numGrille)[i][j].isModifiable());
				textChiffres[i][j].setSelected(false);
				textChiffres[i][j].actualiser();
				textChiffres[i][j].updateUI();
			}
		}
		textChiffres[iSelected][jSelected].setSelected(true);
		textChiffres[iSelected][jSelected].updateUI();
	}

	/**
	 * Serialise les donnees (listeGrillees et listeGrillesIHM) dans le fichier data.ser
	 * @see controller.Chargement#listeGrilles
	 * @see IHM#listeGrillesIHM
	 */
	public static void sauvegarder(){
		try {
			FileOutputStream fichier = new FileOutputStream("data.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(Chargement.listeGrilles);
			oos.writeObject(listeGrillesIHM);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deserialise les donnees du fichier data.ser dans les attributs listeGrillees et listeGrillesIHM
	 * @see controller.Chargement#listeGrilles
	 * @see IHM#listeGrillesIHM
	 */
	@SuppressWarnings("unchecked")
	public static void restaurer(){
		try {
			FileInputStream fichier = new FileInputStream ("data.ser");
			ObjectInputStream ois = new ObjectInputStream (fichier);
			Chargement.listeGrilles = (ArrayList<Grille>) ois.readObject();
			listeGrillesIHM = (ArrayList<Case[][]>) ois.readObject();
			ois.close();
		}
		catch (java.io.IOException e) {}
		catch (ClassNotFoundException e) {}
	}

	/**
	 * main du programme
	 * @param args
	 * 0 argument : le programme lance l'IHM en affichant une grille vide
	 * 1 argument (ex: "data.txt") : lance a partir du terminal, le programme lance l'IHM et charge l'ensemble des grilles contenues dans le fichier situe a la racine du projet dont le nom est args[0]
	 * 2 arguments (ex: "entree.txt" "sortie.txt") : lance a partir du terminal, le programme resoud les grilles du fichier situe a la racine du projet dont le nom est args[0], et enregistre les solutions dans le fichier de nom arg[1]
	 */
	public static void main(String[] args) {
		if (args.length==0){
			Chargement.listeGrilles = new ArrayList<Grille>();
			IHM.initialisation();
			@SuppressWarnings("unused")
			IHM ihm = new IHM();
		}
		else if (args.length==1){
			try {
				Chargement.initialisation(args[0]);
				IHM.initialisation();
				@SuppressWarnings("unused")
				IHM ihm = new IHM();
			} catch (Exception e) {
				@SuppressWarnings("unused")
				IHM ihm = new IHM();
				//e.printStackTrace();
			}
		}
		else if (args.length == 2){
			long start = System.currentTimeMillis();;
			try {
				Chargement.chargementResol(args[0], args[1]);
			}
			catch (Exception e) {
				try {
					ConvertFileFormat.convert(args[0], args[0], true);
					Chargement.chargementResol(args[0], args[1]);
				} catch (Exception ex) {
					//ex.printStackTrace();
					System.out.println("erreur");
					return;
				}
			}
			System.out.println("\nTemps :"+(System.currentTimeMillis()-start) +" ms");	
		}
		else {
			System.out.println("Erreur de syntaxe. Usage : Sudoku.jar [entree.txt sortie.txt]");
		}
	}

}