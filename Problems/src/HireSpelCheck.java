/**
 * 
 */

/**
 * @author yogeshgarg
 *
 */
public class HireSpelCheck {

	public static void printArray( Object[] arr) {
		printArray( arr, " ");
	}
	public static void printArray( Object[] arr, String s) {
		for( int i = 0 ; i < arr.length ; ++i ) {
			System.out.print(arr[i] + s);
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] myargs = {"hire", "hhhiiirrreee", "hirehhhiiirrreee" };
		
		String[] word;
		int countH;
		
		for( int i = 0 ; i < 3 ; ++i) {
			word = myargs[i].split("h");
			printArray( word );
			countH = 0;
			for (; word[countH].equals("") ; countH++ );
			System.out.println(countH);
		}

	}

}
