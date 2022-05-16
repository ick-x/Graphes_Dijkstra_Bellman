package test;

import application.IPCC;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import algos.Bellman;
import algos.Dijkstra;
import application.GraphImporter;

class PlusCourtTest {
	private static final String REPERTOIRE_ENONCE = "graphes/";
	private static final String REPERTOIRE_REPONSE = "reponses/";
	private static final String REPERTOIRE_DIJKSTRA = "ac/";
	private static final String REPERTOIRE_BELLMAN = "sc/";
	private static List<String> listeFichiers;
	private static List<String> listeFichiersb;
	static {
		listeFichiers = new ArrayList<>();
		listeFichiers.add("g-10-1.txt");
		listeFichiers.add("g-10-2.txt");
		listeFichiers.add("g-10-3.txt");
		listeFichiers.add("g-10-4.txt");
		listeFichiers.add("g-10-5.txt");
		listeFichiers.add("g-10-6.txt");
		listeFichiers.add("g-10-7.txt");
		listeFichiers.add("g-10-8.txt");
		listeFichiers.add("g-10-9.txt");
		listeFichiers.add("g-10-10.txt");
		
		listeFichiers.add("g-100-1.txt");
		listeFichiers.add("g-100-2.txt");
		listeFichiers.add("g-100-3.txt");
		listeFichiers.add("g-100-4.txt");
		listeFichiers.add("g-100-5.txt");
		

		listeFichiers.add("g-1000-1.txt");
		listeFichiers.add("g-1000-2.txt");
		
		listeFichiers.add("g-10000-1.txt");
		listeFichiers.add("g-10000-2.txt");
		
		listeFichiers.add("g-100000-1.txt");
		listeFichiers.add("g-100000-2.txt");
		
		listeFichiersb = new ArrayList<>();
		listeFichiersb.add("g-10-1.txt");
		listeFichiersb.add("g-10-2.txt");
		listeFichiersb.add("g-10-3.txt");
		listeFichiersb.add("g-10-4.txt");
		listeFichiersb.add("g-10-5.txt");
		listeFichiersb.add("g-10-6.txt");
		listeFichiersb.add("g-10-7.txt");
		listeFichiersb.add("g-10-8.txt");
		listeFichiersb.add("g-10-9.txt");
		listeFichiersb.add("g-10-10.txt");
		
		listeFichiersb.add("g-100-1.txt");
		listeFichiersb.add("g-100-2.txt");
		listeFichiersb.add("g-100-3.txt");
		listeFichiersb.add("g-100-4.txt");
		listeFichiersb.add("g-100-5.txt");
		
		listeFichiersb.add("g-1000-1.txt");
		listeFichiersb.add("g-1000-2.txt");
		
		listeFichiersb.add("g-10000-1.txt");
		listeFichiersb.add("g-10000-2.txt");
		
		listeFichiersb.add("g-100000-1.txt");
		listeFichiersb.add("g-100000-2.txt");
	}
	
	
	@Test
	void testDijkstra() throws NumberFormatException, IOException {
		IPCC algo = new Dijkstra();
		for (String fichier : listeFichiers) {
			assertTrue(GraphImporter.comparer(REPERTOIRE_ENONCE+REPERTOIRE_DIJKSTRA + fichier, REPERTOIRE_REPONSE+REPERTOIRE_DIJKSTRA + fichier.replace('g', 'r'), algo));	
			assertTrue(GraphImporter.comparer(REPERTOIRE_ENONCE+REPERTOIRE_BELLMAN + fichier, REPERTOIRE_REPONSE+REPERTOIRE_BELLMAN + fichier.replace('g', 'r'), algo));	
		}
	}
	
	//@Test
	void testBellman() throws NumberFormatException, IOException {
		IPCC algo = new Bellman();
		for (String fichier : listeFichiersb) {
			assertTrue(GraphImporter.comparer(REPERTOIRE_ENONCE+REPERTOIRE_BELLMAN + fichier, REPERTOIRE_REPONSE+REPERTOIRE_BELLMAN + fichier.replace('g', 'r'), algo));	
		}
	}
}
