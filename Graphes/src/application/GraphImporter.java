package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import graphes.GrapheLA;
import graphes.GrapheMA;

public class GraphImporter {
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException {
		ArrayList<Integer>df = new ArrayList<>();
		importer("graphe1.txt",df);
		System.out.println("debut fin : "+ df.get(0) + " ==> "+ df.get(1));
		
		verifierGraphes();
	}

	public static void verifierGraphes() throws FileNotFoundException {
		GrapheI g;
		ArrayList<Integer>df = new ArrayList<>();
		String dirStr = System.getProperty("user.dir")+ "\\graphes\\sc";
		System.out.println("Working Directory = " + dirStr);
		File dir = new File(dirStr);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				System.out.println(child);
				g = importer(child, df);
				System.out.println(g.getNbNoeuds()+" sommets");
				System.out.println("debut et fin du chemin à trouver : "+ df.get(0)+ " ==> "+df.get(1)+"\n");
				
			}
		} else {
			System.out.println("Erreur : "+ dirStr + " n'est pas un répertoire");
		}
	}

	public static int[] parse(String string) {
		String[] parts = string.split(" ");
		int source, valuation, destination;
		try {  
			source = Integer.parseInt(parts[0]);
			valuation = Integer.valueOf(parts[1]);
			destination = Integer.parseInt(parts[2]);
		} catch (Exception e) {
			throw new IllegalArgumentException(string + " n'est pas un arc");
		}
		int[]a = {source, valuation, destination};
		return a;
	}

	private static GrapheI importer(File file, ArrayList<Integer> df) throws FileNotFoundException {

		Scanner sc = new Scanner(file);
		String line;
		GrapheI g;
		if (! sc.hasNextLine()) {
			sc.close();
			throw new IllegalArgumentException("Pas de graphe dans "+ file);
		}
		line = sc.nextLine();
		int nbNodes = Integer.parseInt(line.trim());
		g = new GrapheLA(nbNodes);
		int[]a = new int[3];
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			a = parse(line);
			if (sc.hasNextLine())
				g.ajouterArc(a[0],  a[2], a[1]);
			else {// la derniere ligne n'est pas un arc mais le debut et la fin du chemin à trouver
				df.clear();
				df.add(a[0]);
				df.add(a[2]);
			}
		}

		sc.close();
		return g;		
	}
	private static GrapheI importer(String filepath, ArrayList<Integer> df) 
			throws  NumberFormatException, IOException, FileNotFoundException {
		File file = new File(filepath);
		System.out.println(file);
		return importer(file, df);
	}

	public static boolean comparer(String fichierGraphe, String fichierReponse, IPCC algo)
					throws NumberFormatException, IOException {
		ArrayList<Integer> cheminPossible = new ArrayList<>();
		ArrayList<Integer> cheminCalcule = new ArrayList<>();
		ArrayList<Integer>df= new ArrayList<>();
		GrapheI g;
		g = GraphImporter.importer(fichierGraphe, df);
		try {
			int distanceCalculee = algo.PCC(g, df.get(0), df.get(1), cheminCalcule);
			int distanceAttendue = GraphImporter.importerReponse(fichierReponse, cheminPossible);
			System.out.println(fichierGraphe + " vs " +  fichierReponse);
			System.out.println("Chemin possible : "+ cheminToString(cheminPossible));
			System.out.println("Chemin calcule : "+ cheminToString(cheminCalcule));
			System.out.println("Distance attendue : " + distanceAttendue);
			System.out.println("Distance calculee : " + distanceCalculee);
			if (distanceCalculee != distanceAttendue)
				return false;
			int distanceVerifiee = g.distance(cheminCalcule);
			System.out.println("Distance verifiee "+ distanceVerifiee);
			if (distanceCalculee!=distanceVerifiee)
				return false;
			return true;
		}
		
		catch(Exception e) {
			StringBuilder sb = new StringBuilder();
			Scanner sc = new Scanner(new File(fichierReponse));
			while (sc.hasNext())
				sb.append(sc.next() + " ");
			sb.deleteCharAt(sb.length()-1);
			return ("pas de chemin entre " + df.get(0) + " et " + df.get(1)).equals(sb.toString());
		}
	}

	private static String cheminToString(ArrayList<Integer> chemin) {
		StringBuilder sb = new StringBuilder();
		
		for (int n : chemin)
			sb.append(n + " ");
		return sb.toString();
	}

	public static int importerReponse(String filePath, ArrayList<Integer> chemin) throws FileNotFoundException {
		chemin.clear();
		File file = new File(filePath);
		Scanner sc = new Scanner(file);
		String line;
		if (! sc.hasNextLine()) {
			sc.close();
    		throw new IllegalArgumentException("Pas de reponse dans "+ file);
		}
		line = sc.nextLine(); // nom de l'algo recommandé
		line = sc.nextLine(); // distance attendue
		int distance = Integer.parseInt(line.trim());
		line = sc.nextLine(); // chemin
		String[] noeuds = line.split(" ");
		for(String s : noeuds)
			chemin.add(Integer.parseInt(s));
		return distance;
	}

}
