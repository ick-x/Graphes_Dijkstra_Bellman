package graphes;

import java.util.ArrayList;

import application.GrapheI;

public class GrapheLA implements GrapheI{
	private ArrayList<ArrayList<int[]>> LA;
	private int nbNoeuds;


	public GrapheLA(int n) {
		if(n<1)
			throw new IllegalArgumentException();
		this.LA = new ArrayList<ArrayList<int[]>>();
		nbNoeuds = n;
		for(int i = 0;i<n;++i) {
			LA.add(new ArrayList<int[]>());
		}
	}

	@Override
	public void ajouterArc(int pred, int succ, int poids) {
		verifNoeud(pred);verifNoeud(succ);
		int[] temp = {succ,poids};
		LA.get(pred-1).add(temp);
	}
	@Override
	public boolean aArc(int pred, int succ) {
		verifNoeud(pred);verifNoeud(succ);
		for(int[] i : LA.get(pred-1))
			if(i[0]==succ)
				return true;
		return false;
	}
	@Override
	public int dOut(int noeud) {
		verifNoeud(noeud);
		return LA.get(noeud-1).size();
	}
	@Override
	public int dIn(int noeud) {
		verifNoeud(noeud);
		int ct=0;
		for(ArrayList<int[]> temp : LA) {
			for(int i[] : temp)
				if(i[0]==noeud)
					++ct;
		}
		return ct;
	}
	@Override
	public String toString() {
		StringBuilder S = new StringBuilder();
		for(int i = 0; i <nbNoeuds;++i) {
			S.append((i+1)+" -> ");
			for(int[] j : LA.get(i)) {
				S.append(j[0]+" "+"("+j[1]+") ");
			}
			S.append("\n");
		}
		return S.toString();
	}

	@Override
	public int getNbNoeuds() {
		return nbNoeuds;
	}

	@Override
	public int poidsArc(int predecesseur, int successeur) {
		verifNoeud(predecesseur);
		for(int [] i : LA.get(predecesseur - 1))
			if(i[0]==successeur)return i[1];
		throw new IllegalArgumentException();

	}

	@Override
	public ArrayList<Integer> successeurs(int noeud) throws IllegalArgumentException{
		verifNoeud(noeud);
		ArrayList<Integer> s = new ArrayList<>();
		for(int[] i: LA.get(noeud-1))
			s.add(i[0]);
		return s;
	}

	@Override
	public ArrayList<Integer> predecesseurs(int noeud)  {
		verifNoeud(noeud);
		ArrayList<Integer> s = new ArrayList<>();
		for(ArrayList<int[]> temp : LA)
			for(int [] i : temp)
				if(i[0]==noeud)
					s.add(LA.indexOf(temp)+1);

		return s;
	}
	private void verifNoeud(int noeud){
		if (noeud<0||noeud>this.getNbNoeuds())
			throw new IllegalArgumentException();

	}
	@Override
	public int distance(ArrayList<Integer> cheminCalcule) {
		int sol = 0;
		for(int i = 0; i<cheminCalcule.size()-1;++i)
			sol+=poidsArc(cheminCalcule.get(i), cheminCalcule.get(i+1));
		return sol;
	}
	
}
