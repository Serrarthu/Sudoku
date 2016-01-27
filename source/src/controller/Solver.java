package controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Grille;

/**
 * Resolution d'uen grille a l'aide de l'algoritme des "Dancing Links"
 * @author Boris Ho, Arthur Serre
 */
public class Solver {

	
	private Header root = null;
	private List<X> solution = new ArrayList<X>();
	private int solutionCounter = 0;
	private int b;
	private int n;
	private int threeN;
	private int twoN;
	private int threeNsquare;
	private int nSquare;
	private int nPow3;



	public Solver() {
		b=3;
		n = b * b;
		nSquare = n*n;
		twoN = 2*n;
		threeN = 3*n;
		threeNsquare = 3*nSquare;
		nPow3 = nSquare*n;
	}


	/**
	 * Resoud la grille g et stocke la solution dans g
	 * @param g
	 * grille a resoudre
	 */
	public void  solve(Grille g) {
		solutionCounter = 0;
		short[][] matrix = createMatrix(g.getGrille());
		@SuppressWarnings("unused")
		Header doubleLinkedList = createDoubleLinkedLists(matrix);      
		search(0, g);
	}

	/**
	 * Creation de la matrice 729*324 representant les contraintes et les coups possibles
	 * @param initialMatrix
	 * matrice de contrainte initiale, ici la grille a resoudre
	 * @return Matrice de contrainte
	 */
	private short[][] createMatrix(int[][] initialMatrix) {      
		int[][] prefill = null;
		if(initialMatrix != null) {
			List<int[]> prefillList = new ArrayList<int[]>();
			int count = 0;
			for(int r = 0; r < n; r++) {
				for(int c = 0; c < n; c++) {
					if(initialMatrix[r][c] > 0) {
						prefillList.add(new int[]{initialMatrix[r][c],r,c});
						count++;
					}
				}
			}
			prefill = new int[count][];
			for(int i = 0; i < count; i++) {
				prefill[i] = (int[])prefillList.get(i);
			}
		}

		short[][] matrix = new short[nPow3][4*nSquare];

		for(int d = 0; d < n; d++) {
			for(int r = 0; r < n; r++) {
				for(int c = 0; c < n; c++) {
					if(!cellIsFilled(d,r,c,prefill)) {
						int rowIndex = c + (n * r) + (n * n * d);
						int blockIndex = ((c / b) + ((r / b) * b));
						int colIndexRow = threeN*d+r;
						int colIndexCol = threeN*d+n+c;
						int colIndexBlock = threeN*d+twoN+blockIndex;
						int colIndexSimple = threeN*n+(c+n*r);

						matrix[rowIndex][colIndexRow] = 1;
						matrix[rowIndex][colIndexCol] = 1;
						matrix[rowIndex][colIndexBlock] = 1;
						matrix[rowIndex][colIndexSimple] = 1;
					}
				}
			}
		}
		return matrix;
	}


	/**
	 * Utilisee pour la creation de la matrice de contrainte, determine si une case est deja remplie
	 * @param digit
	 * valeur de la case
	 * @param row
	 * ligne de la case
	 * @param col
	 * colonne de la case
	 * @param prefill
	 * contraintes existantes
	 * @return
	 */
	private boolean cellIsFilled(int digit, int row, int col, int[][] prefill) {
		boolean cellIsFilled = false;
		if(prefill != null) {
			for(int i = 0; i < prefill.length; i++) {
				int d = prefill[i][0]-1;
				int r = prefill[i][1];
				int c = prefill[i][2];

				int blockStartIndexCol = (c/b)*b;
				int blockEndIndexCol = blockStartIndexCol + b;
				int blockStartIndexRow = (r/b)*b;
				int blockEndIndexRow = blockStartIndexRow + b;
				if(d != digit && row == r && col == c) {
					cellIsFilled = true;
				} else if((d == digit) && (row == r || col == c) && !(row == r && col == c)) {
					cellIsFilled = true;
				} else if((d == digit) && (row > blockStartIndexRow) && (row < blockEndIndexRow) && (col > blockStartIndexCol) && (col < blockEndIndexCol) && !(row == r && col == c)) {
					cellIsFilled = true;
				}
			}
		}
		return cellIsFilled;
	}

	/**
	 * Cree la liste doublement chainee necessaire a la fonction search
	 * @param matrix
	 * Matrice de contrainte
	 * @return Racine de la liste doublement chainee
	 * @see Solver#search(int, Grille)
	 */
	private Header createDoubleLinkedLists(short[][] matrix) {
		root = new Header();

		Header currentHeader = root;
		for(int col = 0; col < matrix[0].length; col++) {

			HeaderInfo info = new HeaderInfo();
			if(col < threeNsquare) {

				int digit = (col / (threeN)) + 1;
				info.digit = digit;

				int index = col-(digit-1)*threeN;
				if(index < n) {
					info.type = HeaderInfo.TYPE_ROW;
					info.position = index;
				} else if(index < twoN) {
					info.type = HeaderInfo.TYPE_COL;
					info.position = index-n;
				} else {
					info.type = HeaderInfo.TYPE_BLOCK;
					info.position = index-twoN;
				}            
			} else {
				info.type = HeaderInfo.TYPE_CELL;
				info.position = col - threeNsquare;
			}
			currentHeader.right = new Header();
			currentHeader.right.left = currentHeader;
			currentHeader = (Header)currentHeader.right;
			currentHeader.info = info;
			currentHeader.header = currentHeader;
		}
		currentHeader.right = root;
		root.left = currentHeader;


		for(int row = 0; row < matrix.length; row++) {

			currentHeader = (Header)root.right;
			X lastCreatedElement = null;
			X firstElement = null;
			for(int col = 0; col < matrix[row].length; col++) {
				if(matrix[row][col] == 1) {

					X colElement = currentHeader;
					while(colElement.down != null) {
						colElement = colElement.down;
					}
					colElement.down = new X();
					if(firstElement == null) {
						firstElement = colElement.down;
					}
					colElement.down.up = colElement;
					colElement.down.left = lastCreatedElement;
					colElement.down.header = currentHeader;
					if(lastCreatedElement != null) {
						colElement.down.left.right = colElement.down;
					}
					lastCreatedElement = colElement.down;
					currentHeader.size++;
				}
				currentHeader = (Header)currentHeader.right;
			}

			if(lastCreatedElement != null) {
				lastCreatedElement.right = firstElement;
				firstElement.left = lastCreatedElement;
			}
		}
		currentHeader = (Header)root.right;

		for(int i = 0; i < matrix[0].length; i++) {
			X colElement = currentHeader;
			while(colElement.down != null) {
				colElement = colElement.down;
			}
			colElement.down = currentHeader;
			currentHeader.up = colElement;
			currentHeader = (Header)currentHeader.right;
		}
		return root;
	}


	/**
	 * Recherche la solution en parcourant l'arbre represente par la variable globale root
	 * @param k
	 * niveau de recursion, initialise a 0
	 * @param g
	 * grille dans laquelle on sauvegarde la solution
	 */
	private void search(int k, Grille g) {
		// Condition d'arret
		//boolean res=true;
		if(root.right == root || solutionCounter >=1) {
			return;
			//solutionCounter != 0;
		}
		Header c = chooseColumn();
		coverColumn(c);
		X r = c.down;
		while(r != c) {

			if(k < solution.size()) {
				solution.remove(k);
			}         
			solution.add(k,r);

			X j = r.right;
			while(j != r) {
				coverColumn(j.header);
				j = j.right;
			}
			search(k+1, g);


			X r2 = (X)solution.get(k);

			X j2 = r2.left;
			while(j2 != r2) {
				uncoverColumn(j2.header);
				j2 = j2.left;
			}
			r = r.down;


			if(k == n*n-1) {
				solutionCounter++;
				printSolution(g);
			}

		}
		uncoverColumn(c);
		//return res;
	}

	/**
	 * Sauvegarde la solution dans la grille g
	 * @param g
	 */
	private void printSolution(Grille g) {
		int[] result = new int[n*n];
		for(Iterator<X> it = solution.iterator(); it.hasNext(); ) {
			int digit = -1;
			int cell = -1;
			X element = (X)it.next();
			X next = element;
			do {
				if(next.header.info.type == HeaderInfo.TYPE_ROW) {
					digit = next.header.info.digit;
				} else if(next.header.info.type == HeaderInfo.TYPE_CELL) {
					cell = next.header.info.position;
				}
				next = next.right;
			} while(element != next);
			result[cell] = digit;
		}

		g.solved=true;
		for (int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				g.set(i, j, result[9*i+j]);
			}
		}
	}

	/**
	 * Selectionne l'element de la liste chainee avec la colonne la plus petite
	 * @return element selectione
	 */
	private Header chooseColumn() {
    
		Header h = (Header)root.right;
		Header smalest = h;
		while(h.right != root) {
			h = (Header)h.right;
			if(h.size < smalest.size) {
				smalest = h;
			}         
		}      
		return smalest;
	}

	/**
	 * Parcours la colonne de l'element pour verifier les contradicitions par rapport aux contraintes
	 * @param column
	 * element a verifier
	 */
	private void coverColumn(X column) {
		column.right.left = column.left;
		column.left.right = column.right;
		X i = column.down;
		while(i != column) {
			X j = i.right;
			while(j != i) {
				j.down.up = j.up;
				j.up.down = j.down;
				j.header.size--;
				j = j.right;
			}
			i = i.down;
		}
	}

	/**
	 * insert l'element dans la colonne
	 * @param column
	 * element a inserer
	 */
	private void uncoverColumn(X column) {
		X i = column.up;
		while(i != column) {
			X j = i.left;
			while(j != i) {
				j.header.size++;
				j.down.up = j;
				j.up.down = j;
				j = j.left;
			}
			i = i.up;
		}
		column.right.left = column;
		column.left.right = column;
	}

	/**
	 * Element de la liste doublement chainee
	 * @author Boris Ho, Arthur Serre
	 */
	class X {
		X left;
		X right;
		X up;
		X down;
		Header header;
	}

	/**
	 * SuperClasse de X contenant un element de la liste chainee et les informations associee
	 * @author Boris Ho, Arthur Serre
	 * @see Solver.X
	 */
	class Header extends X {
		int size = 0;
		HeaderInfo info;
	}

	/**
	 * Informations concernant un element de la liste chainee
	 * @author Boris Ho, Arthur Serre
	 */
	class HeaderInfo {
		static final int TYPE_ROW = 0;
		static final int TYPE_COL = 1;
		static final int TYPE_BLOCK = 2;
		static final int TYPE_CELL = 3;

		int type = -1;
		int digit = -1;
		int position = -1;

		public String toString() {

			StringBuffer name = new StringBuffer();
			if(type == TYPE_ROW) {
				name.append("Digit ");
				name.append(digit);
				name.append(" in row ");
			} else if(type == TYPE_COL) {
				name.append("Digit ");
				name.append(digit);
				name.append(" in column ");
			} else if(type == TYPE_BLOCK) {
				name.append("Digit ");
				name.append(digit);
				name.append(" in block ");
			} else if(type == TYPE_CELL) {
				name.append("Digit in cell ");
			}
			name.append(position+1);
			return name.toString();
		}
	}
} 
