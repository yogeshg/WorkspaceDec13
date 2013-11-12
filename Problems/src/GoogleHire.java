import java.io.*;

public class GoogleHire {

	private static final String WORD = "hire";
	
	public static boolean validate( String str ) {
		
		int i = 0;
		int l = str.length();
		int n;
		int m;
		
		while( i < l) {
			n = 0;
			while( i<l && str.charAt(i) == WORD.charAt(0) ) {
				++i;
				++n;
			}
			if( n <= 0 ) {
				return false;
			}
			for( int j=1; j<WORD.length(); ++j) {
				m=0;
				while( i<l && str.charAt(i)==WORD.charAt(j) ) {
					++i;
					++m;
				}
				if( m!= n )
					return false;
			}
			
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in) );
		
		try {
			System.out.println( validate( br.readLine() ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
