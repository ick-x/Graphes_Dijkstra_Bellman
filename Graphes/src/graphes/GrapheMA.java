package graphes;

import java.util.ArrayList;

import application.GrapheI;

public class GrapheMA implements GrapheI{
	private int[][] MatAdj;
	private int nbNoeuds;
	
	public GrapheMA(int n) {
		if(n<1)
			throw new IllegalArgumentException();
		this.nbNoeuds = n;
		this.MatAdj=new int[n][n];
		for(int i = 0;i<n;++i)
			for(int j =0;j<n;++j)
				this.MatAdj[i][j]=(int) Double.POSITIVE_INFINITY;
		
	}

	public void ajouterArc(int pred, int succ, int poids) {
		verifNoeud(pred);verifNoeud(succ);
		this.MatAdj[pred-1][succ-1]=poids;
	}
	@Override
	public String toString() {
		StringBuilder S = new StringBuilder();
		for(int i = 0;i<nbNoeuds;++i) {
			for(int j = 0;j<nbNoeuds;++j) {
				if(this.MatAdj[i][j]!=(int) Double.POSITIVE_INFINITY)
					S.append(this.MatAdj[i][j]+" ");
				else
					S.append("i ");
			}
			S.append("\n");
		}
		return S.toString();		
	}
	public int dOut(int noeud) {
		verifNoeud(noeud);
		int ct = 0;
		for(int i = 0;i<this.nbNoeuds;++i)
			if(this.MatAdj[noeud-1][i] !=(int) Double.POSITIVE_INFINITY)
				++ct;
		return ct;
	}
	public int dIn(int noeud) {
		verifNoeud(noeud);
		int ct = 0;
		for(int i = 0;i<this.nbNoeuds;++i)
			if(this.MatAdj[i][noeud-1]!=(int) Double.POSITIVE_INFINITY)
				++ct;
		return ct;
	}

	@Override
	public int getNbNoeuds() {
		return nbNoeuds;
	}

	@Override
	public int poidsArc(int predecesseur, int successeur) {
		if(!aArc(predecesseur, successeur))
			throw new IllegalArgumentException();
		return this.MatAdj[predecesseur-1][successeur-1];
	}

	@Override
	public ArrayList<Integer> predecesseurs(int noeud) {
		verifNoeud(noeud);
		ArrayList<Integer>s = new ArrayList<>();
		for(int i = 0; i < nbNoeuds;++i) {
			if(this.MatAdj[i][noeud-1]!=(int)Double.POSITIVE_INFINITY)
				s.add(i+1);
		}
		return s;
	}

	@Override
	public ArrayList<Integer> successeurs(int noeud) {
		verifNoeud(noeud);
		ArrayList<Integer>s = new ArrayList<>();
		for(int i = 0; i < nbNoeuds;++i) {
			if(this.MatAdj[noeud-1][i]!=(int)Double.POSITIVE_INFINITY)
				s.add(i+1);
		}
		return s;
	}
	private void verifNoeud(int noeud){
		if (noeud<0||noeud>this.getNbNoeuds())
			throw new IllegalArgumentException();
		
	}

	@Override
	public boolean aArc(int pred, int succ) {
		verifNoeud(pred);verifNoeud(succ);
		return this.MatAdj[pred-1][succ-1]!=(int) Double.POSITIVE_INFINITY;
	}

	@Override
	public int distance(ArrayList<Integer> cheminCalcule) {
		int sol = 0;
		for(int i = 0; i<cheminCalcule.size()-1;++i)
			sol+=poidsArc(cheminCalcule.get(i), cheminCalcule.get(i+1));
		return sol;
	}
	

}
