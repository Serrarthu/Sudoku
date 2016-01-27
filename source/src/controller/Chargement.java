package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.Grille;

/**
 * Chargement des donnees a partir d'un fichier texte
 * @author Boris Ho, Arthur Serre
 */
public class Chargement {

	/**
	 * Liste des Grille
	 * @see model.Grille
	 */
	public static ArrayList<Grille> listeGrilles;

	/**
	 * Initialisation de la liste des Grille a partir d'un fichier texte
	 * @param arg
	 * Nom du fichier texte
	 */
	public static void initialisation(String arg){
		listeGrilles = new ArrayList<Grille>();
		try {
			listeGrilles = chargement(arg);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	/**
	 * Chargement des donnes a partir d'un fichier texte
	 * @param fichier
	 * Nom du fichier
	 * @return Liste des Grilles
	 * @throws Exception
	 */
	public static ArrayList<Grille> chargement(String fichier) throws Exception{

		BufferedReader br;
		ArrayList<Grille> bdd = new ArrayList<Grille>();
		try {
			br = new BufferedReader(new FileReader(fichier));
			String line = br.readLine();

			while (line != null)
			{
				Grille g = new Grille();
				for (int i=0; i<9; i++) {
					for (int j=0; j<9;j++){
						if (!line.isEmpty()) {
							line = normalisation(line);
							g.set(i, j, Integer.parseInt(line.charAt(0)+""));
							
							line=line.substring(1);
						}
						else {
							throw new IndexOutOfBoundsException();
						}
					}
					line=br.readLine();
				}
				bdd.add(g);
			}
			br.close();
			return bdd;
		}
		catch (Exception e)
		{
			throw e;
		}
		
	}
	
	/**
	 * Charge, resoud et sauvegarde les resultats dans le fichier de sortie
	 * @param entree
	 * Fichier d'entree
	 * @param sortie
	 * Fichier de sortie
	 * @throws Exception
	 */
	public static void chargementResol(String entree, String sortie) throws Exception{
		try {
			BufferedReader br = new BufferedReader(new FileReader(entree));
			Solver ex = new Solver();
			StringBuffer sb = new StringBuffer();
			String line = br.readLine();

			while (line != null)
			{
				Grille g = new Grille();
				for (int i=0; i<9; i++) {
					for (int j=0; j<9;j++){
						if (!line.isEmpty()) {
							line = normalisation(line);
							g.set(i, j, Integer.parseInt(line.charAt(0)+""));
							line=line.substring(1);
						}
						else {
							throw new IndexOutOfBoundsException();
						}
					}
					line=br.readLine();
				}
				ex.solve(g);
				if (g.solved) {
				sb.append(g);}
			}
			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter(sortie));
			bw.write(sb.toString());
			bw.close();
		}
		catch (Exception e)
		{
			throw e;
		}
		
	}
	
	/**
	 * Normalise une chaîne de caracteres pour la rendre compatible avec le programme en supprimant les espaces
	 * @param str
	 * Chaine de caracteres a normaliser
	 * @return La chaine de caracteres normalise sous le bon format
	 */
	private static String normalisation(String str) {
		String res ="";
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			res+=st.nextToken();
		}
		return res;
	}
}
