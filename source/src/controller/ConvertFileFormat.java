package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Conversion d'un fichier de donnees au format utilise par le programme
 * @author Boris Ho, Arthur Serre
 */
public class ConvertFileFormat {

	/**
	 * Convertit un fichier de donnees au format utilise par le programme
	 * @param entree
	 * Nom du fichier d'entree
	 * @param sortie
	 * Nom de fichier de sortie
	 * @param matrix
	 * True si la grille est representee sous forme de matrice, false si elle est representee sous forme de lignes
	 * @throws Exception
	 */
	static public void convert(String entree, String sortie, boolean matrix) throws Exception {
		BufferedReader br;
		BufferedWriter bw;
		String str = "";
		try {
			br = new BufferedReader(new FileReader(entree));
			String file="", line;
			while((line = br.readLine()) != null) {
				file+=line+"\n";
			}
			StringTokenizer st = new StringTokenizer(file, "\n");
			while(st.hasMoreTokens()) {
				line=st.nextToken();
				for(int i=0; i<9; i++){
					for(int j=0; j<9; j++) {
						line = normalisation(line);
						if (!line.isEmpty()) {
							if ("123456789".contains(line.charAt(0)+"")) {
								str+=line.charAt(0)+" ";
							} else str+="0 ";
							line=line.substring(1);
						}
					}
					str+="\n";
					if (matrix && st.hasMoreElements()) line=st.nextToken();
				}
			}
			br.close();
			bw = new BufferedWriter(new FileWriter(sortie));
			bw.write(str);
			bw.close();
		}
		catch (IOException e)
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
