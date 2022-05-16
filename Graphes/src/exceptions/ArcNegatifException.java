package exceptions;

public class ArcNegatifException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8175212803587203L;
	
	
	@Override
	public String toString() {
		return "Arc de poids négatif";
	}
}
