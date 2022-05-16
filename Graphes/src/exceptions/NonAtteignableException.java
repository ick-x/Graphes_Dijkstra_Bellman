package exceptions;

public class NonAtteignableException extends IllegalArgumentException {

	private int start ;
	private int end ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 154909707083156473L;


	public NonAtteignableException(int s, int e) {
		this.start=s;this.end=e;
	}
	
	@Override
	public String toString() {
		return "pas de chemin entre "+start+" et "+end;
	}
}
