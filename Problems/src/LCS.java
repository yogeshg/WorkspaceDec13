import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class LCS {

	enum dir {
		diag, up, left;
	}
	
	private final boolean debug;
	
	private String s;
	private String t;
	
	private String lcs;

	private final int m;
	private final int n;

	private int[][] length;
	private int[][] number;
	private dir[][] parent;
	
	public LCS( String a, String b) {
		this(a, b, false);
	}
	public LCS( String a, String b, boolean de) {

		s = a;
		t = b;

		m = s.length();
		n = t.length();

		length = new int[m][n];
		parent = new dir[m][n];
		
		findLCS();
		
		debug = de;
		if( debug ) printDynamics();

	}
	
	private void findLCS() {
		
		if( m<=0 || n <= 0) return;

		int i = 0;
		int j = 0;
		
		if( s.charAt(i) == t.charAt(j) ) {
			length[i][j] = 1;
			parent[i][j] = dir.diag;
		} else { // unequal
			length[i][j] = 0;
			parent[i][j] = dir.diag;
		}
		
		for( i=0, j=1; j<n; ++j) {
			if( s.charAt(i) == t.charAt(j) ) {
				length[i][j] = 1;
				parent[i][j] = dir.diag;
			} else { // unequal
				length[i][j] = length[i][j-1];
				parent[i][j] = dir.left;
			}
		}

		for( i=1, j=0; i<m; ++i) {
			if( s.charAt(i) == t.charAt(j) ) {
				length[i][j] = 1;
				parent[i][j] = dir.diag;
			} else { // unequal
				length[i][j] = length[i-1][j];
				parent[i][j] = dir.up;
			}
		}
		
		for( i=1; i<m; ++i) {
			for( j=1; j<n; ++j) {
				if( s.charAt(i) == t.charAt(j) ) {
					length[i][j] = 1+length[i-1][j-1];
					parent[i][j] = dir.diag;
				} else { //unequal
					if( length[i-1][j] > length[i][j-1] ) {
						length[i][j] = length[i-1][j];
						parent[i][j] = dir.up;
					} else { // <=
						length[i][j] = length[i][j-1];
						parent[i][j] = dir.left;
						// preference to left
					}
				}
			}
		}

	}
	
	private void findLCSnum() {
		
		if( m<=0 || n <= 0) return;

		int i = 0;
		int j = 0;
		
		if( s.charAt(i) == t.charAt(j) ) {
			number[i][j] = 1;
		} else { // unequal
			number[i][j] = 0;
		}
		
		for( i=0, j=1; j<n; ++j) {
			if( s.charAt(i) == t.charAt(j) ) {
				number[i][j] = 1;// + number[i][j-1];
			} else { // unequal
				number[i][j] = number[i][j-1];
			}
		}

		for( i=1, j=0; i<m; ++i) {
			if( s.charAt(i) == t.charAt(j) ) {
				number[i][j] = 1;// + number[i-1][j];
			} else { // unequal
				number[i][j] = number[i-1][j];
			}
		}
		
		for( i=1; i<m; ++i) {
			for( j=1; j<n; ++j) {
				if( s.charAt(i) == t.charAt(j) ) {
					number[i][j] = number[i-1][j-1];
					if( number[i][j] <= 0)
						number[i][j] = 1;
				} else { //unequal
					if( length[i-1][j] > length[i][j-1] ) {
						number[i][j] = number[i-1][j];
					} else if( length[i-1][j] < length[i][j-1] ) {
						number[i][j] = number[i][j-1];
					} else { // equal
						number[i][j] = number[i][j-1]*number[i-1][j];
						// preference to left
					}
				}
			}
		}

	}
	
	public int getLCSlength() {
		if( m>0 && n>0 )
			return length[m-1][n-1];
		else
			return 0;
	}
	
	public String getLCS() {
		if( lcs == null) lcs = getLCS(m-1, n-1);
		return lcs;
	}

	private String getLCS(int i, int j) {
		if( i<0 || j<0 )
			return "";
		switch( parent[i][j] ) {
		case diag :
			return getLCS(i-1, j-1)+s.charAt(i);
		case up :
			return getLCS(i-1, j);
		case left :
			return getLCS(i, j-1);
		default :
			return "";
		}
	}
	
	/**
	 * Not working at the moment
	 * @return should return number of LCS possible
	 */
	public int getLCSnum() {
		if (number == null) {
			number = new int[m][n];
			findLCSnum();
			if( debug ) {
				printNumberDynamics();
			}
		}
		if( m>0 && n>0 )
			return number[m-1][n-1];
		else
			return 0;
	}
	
	public void printDynamics() {
		int i;
		int j;

		System.out.println("s\t: " + s);
		System.out.println("t\t: " + t);
		System.out.println("LCS\t: " + getLCS());
		System.out.println("length(LCS)\t: " + getLCSlength());
		
		System.out.println("Length Matrix:");
		for( i=0; i<m; ++i) {
			for( j=0; j<n; ++j) {
				System.out.print("\t"+length[i][j]);
			}
			System.out.println();
		}

		System.out.println("Parent Matrix:");
		for( i=0; i<m; ++i) {
			for( j=0; j<n; ++j) {
				char c;
				switch( parent[i][j]) {
				case diag :
					c = '\\'; break;
				case up :
					c = '^'; break;
				case left :
					c = '<'; break;
				default:
					c = ' '; break;
				}
				System.out.print("\t"+c);
			}
			System.out.println();
		}
	}
	
	public void printNumberDynamics() {
		int i;
		int j;

		System.out.println("Number Matrix:");

		for( j=0; j<n; ++j) {
			System.out.print( "\t" + t.charAt(j) );
		}
		System.out.println();
		for( j=0; j<n; ++j) {
			System.out.print( "\t-" );
		}
		System.out.println();
		
		for( i=0; i<m; ++i) {
			System.out.print( s.charAt(i)+" |" );;
			for( j=0; j<n; ++j) {
				System.out.print("\t"+number[i][j]);
			}
			System.out.println();
		}
	}

	
	public static void main( String[] args) {
		
		byte[] line = new byte[16];
		Random r = new Random();

		LCS s;
		
		/*
		BufferedReader bw1;
		BufferedReader bw2;
		
		String l1;
		String l2;
		
//		boolean flag = true;
		int flag = 20;

		try {
			bw1 = new BufferedReader( new FileReader(new File("files/0.txt")) );
			bw2 = new BufferedReader( new FileReader(new File("files/World Quant.txt")) );
			while ( flag>0) {
				l1 = bw1.readLine();
				l2 = bw2.readLine();
				s = new LCS( l1, l2 );
				System.out.println(flag + " " + s.getLCS());
				flag -= (s.getLCSlength() < l1.length() ) ? 1 : 0;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		

		s = new LCS( "YZPQR", "PXSYDQZR", true);
		System.out.println( s.getLCS() );
		System.out.println( s.getLCSnum() );

		r.nextBytes(line);
		String str1 = new String(line);
		r.nextBytes(line);
		String str2 = new String(line);
		
		s = new LCS( str1, str2);
		System.out.println( s.getLCS() );
		
	}
}
