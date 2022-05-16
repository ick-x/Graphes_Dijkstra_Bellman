package algos;

import java.util.ArrayList;

import application.GrapheI;
import application.IPCC;
import exceptions.ArcAbsorbantException;
import exceptions.NonAtteignableException;

public class Bellman implements IPCC{
	private static boolean check(GrapheI g, int[] pred, double[]poids) {
		for(int i = 0; i < poids.length;++i ) 
			if(pred[i]!=0)
				if(poids[pred[i]-1]+g.poidsArc(pred[i], i+1)<poids[i])
					return false;
		return true;
	}

	@Override
	public int PCC(GrapheI g, int start, int end, ArrayList<Integer> cheminCalcule) {
		cheminCalcule.clear();
		//preconditions
		if(start<1||start>g.getNbNoeuds()||end>g.getNbNoeuds()||end<1)
			throw new IllegalArgumentException();

		double[] poids = new double[g.getNbNoeuds()];
		int[] predecesseurs = new int[g.getNbNoeuds()];
		for(int i = 0;i<g.getNbNoeuds();++i) {
			poids[i]= Double.POSITIVE_INFINITY;
			predecesseurs[i]=0;
		}
		poids[start-1]=0;
		for(int i = 0; i< g.getNbNoeuds()-1;++i) {
			for(int j = 0; j < g.getNbNoeuds();++j) {
				for(int k : g.successeurs(j+1)) {
					if(poids[j]+g.poidsArc(j+1, k)<poids[k-1]) {
						poids[k-1]=poids[j]+g.poidsArc(j+1, k);
						predecesseurs[k-1]=j+1;
					}
				}
			}
		}
		if(!check(g, predecesseurs, poids))
			throw new ArcAbsorbantException();

		if(predecesseurs[end-1]!=0) {
			solution(predecesseurs,start,end,cheminCalcule);
			return g.distance(cheminCalcule);
		}
		else {
			throw new NonAtteignableException(start,end);
		}
	}

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
}

