package application;

import java.util.ArrayList;

public interface GrapheI {

	void ajouterArc(int pred, int succ, int poids);
	boolean aArc(int pred, int succ);
	int dOut(int noeud);
	int dIn(int noeud);
	
	int poidsArc(int predecesseur,int successeur);
	ArrayList<Integer> predecesseurs(int noeud);
	ArrayList<Integer> successeurs(int noeud);
	
	
	
	int getNbNoeuds();
	int distance(ArrayList<Integer> cheminCalcule);
	
}
