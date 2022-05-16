package algos;

import java.util.ArrayList;

import application.GrapheI;
import application.IPCC;
import exceptions.ArcNegatifException;
import exceptions.NonAtteignableException;

public class Dijkstra implements IPCC {

	private static void solution(int[] predecesseurs, int start, int end, ArrayList<Integer> sol) {
		int noeud = start;
		sol.add(start);

		while(noeud!=end) {
			int i = end;

			while(predecesseurs[i-1]!=noeud)
				i = predecesseurs[i-1];
			sol.add(i);
			noeud = i;
		}
	}

	private static int min(double[] poids, boolean[] bools, int noeud) {
		int min = -1;
		double poidsmin = Double.POSITIVE_INFINITY;

		for(int i = 0;i<poids.length;++i) 
			if(!bools[i]) 
				if(poids[i]<poidsmin||(poids[i]==poidsmin&&i+1==noeud)) {
					min = i;
					poidsmin = poids[i];
				}
		return min+1;
	}

	private static boolean estOk(GrapheI g) {
		for(int i = 0; i < g.getNbNoeuds();++i)
			for(int j : g.successeurs(i+1))
				if(g.poidsArc(i+1, j)<0)
					return false;
		return true;
	}

	@Override
	public int PCC(GrapheI g, int start, int end, ArrayList<Integer> cheminCalcule) throws ArcNegatifException {
		cheminCalcule.clear();
		if(start<1||start>g.getNbNoeuds()||end>g.getNbNoeuds()||end<1)
			throw new IllegalArgumentException();

		if(!estOk(g))throw new ArcNegatifException();

		boolean[] bools = new boolean[g.getNbNoeuds()];
		for(int i = 0;i<bools.length;++i)
			bools[i]=false;
		bools[start-1]=true;

		double[] poids = new double[g.getNbNoeuds()];
		for(int i = 0;i<g.getNbNoeuds();++i)
			poids[i]=Double.POSITIVE_INFINITY;
		poids[start-1]=0;

		int[] predecesseurs = new int[g.getNbNoeuds()];
		for(int i = 0;i<poids.length;++i)
			predecesseurs[i]=0;

		int noeud_courant=start;
		boolean ok = noeud_courant==end;
		if(ok) {
			cheminCalcule.add(end);
			return 0;
		}
		for(int i = 1;i<g.getNbNoeuds()&&!ok;++i) {
			for(int successeur : g.successeurs(noeud_courant)) {
				if(!bools[successeur-1]) {
					double poidsbis = poids[noeud_courant-1];
					poidsbis+=g.poidsArc(noeud_courant, successeur);
					if(poidsbis<poids[successeur-1]) {
						poids[successeur-1]=poidsbis;
						predecesseurs[successeur-1]=noeud_courant;
					}
				}
			}
			noeud_courant = min(poids,bools,end);
			if(noeud_courant == end)ok=true;
			bools[noeud_courant-1]=true;
		}
		if(predecesseurs[end-1]!=0) {
			solution(predecesseurs,start,end,cheminCalcule);
			return g.distance(cheminCalcule);
		}
		else {
			throw new NonAtteignableException(start,end);
		}
	}
}
