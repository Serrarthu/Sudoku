package model;

/**
 * Grille d'un sudoku
 * @author Boris Ho, Arthur Serre
 */
public class Grille implements java.io.Serializable{

	private static final long serialVersionUID = -8948825143188878020L;
	/**
	 * Tableau des valeurs de la grille
	 */
	protected int[][] grille;
	/**
	 * True si la grille est resolue, false sinon
	 */
	public boolean solved;

	/**
	 * Constructeur d'une grille vide
	 */
	public Grille() {
		grille = new int[9][9];
		solved = false;
	}

	/**
	 * Constructeur d'une grille a partir d'un tableau de valeurs existant
	 * @param grille
	 * Tableau d'entiers
	 */
	public Grille(int[][] grille) {
		this.grille=new int[9][9];
		for (int i=0; i<9; i++) {
			for(int j=0; j<9; j++)
				this.grille[i][j]=grille[i][j];
		}
		solved = false;
	}
	

	public int getValue(int i, int j) {
		return grille[i][j];
	}
	
	public int[][] getGrille() {
		return grille;
	}
	
	
	public int[][] getSub(int n) {
		int[][] sub = new int[3][3];

		int x = n/3;
		int y = n%3;

		for (int i=0 ; i<3; i++) {
			for(int j=0 ; j<3; j++) {
				sub[i][j]=grille[i+3*x][j+3*y];
			}
		}
		return sub;
	}
	
	public void set(int i, int j, int value) {
		this.grille[i][j]=value;
	}

	@Override
	public String toString() {
		String str = "";
		for(int i[]: grille) {
			for (int j:i) {
				str+=j+" ";
			}
			str+="\n";
		}
		return str;
	}

	/**
	 * Verifie si une action est possible
	 * @param i
	 * Ligne ou on veut ajouter la valeur
	 * @param j
	 * Colonne ou on veut ajouter la valeur
	 * @param val
	 * Valeur a ajouter
	 * @return Enumeration ErreurAction qui permet de savoir si l'action est valide ou non
	 * @see ErreurAction
	 */
	public ErreurAction isLegal(int i, int j, int val) {
        for (int k = 0; k < 9; ++k)
            if (val == grille[k][j])
                return ErreurAction.COLONNE;

        for (int k = 0; k < 9; ++k)
            if (val == grille[i][k])
                return ErreurAction.LIGNE;

        int boxRowOffset = (i / 3)*3;
        int boxColOffset = (j / 3)*3;
        for (int k = 0; k < 3; ++k)
            for (int m = 0; m < 3; ++m)
                if (val == grille[boxRowOffset+k][boxColOffset+m])
                    return ErreurAction.SUB;

        return ErreurAction.CORRECT;
    }

}
