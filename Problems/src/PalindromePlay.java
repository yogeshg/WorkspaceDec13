public class PalindromePlay {
	
	public static int numPalindrome( int d ) {
		if( d <= 0 )
			return 0;
		if( d%2 == 0 ) {
			d = d/2;
		} else {
			d = (d+1)/2;
		}
		return 9*((int)Math.pow(10,d-1));
	}
	
	public static int getDigit( int n, int d) {
		int mask = (int) Math.pow(10,d-1);
		return (n/mask)%10;
	}
	
	public static int addDigit( int n, int d, int s) {
		int mask = (int) Math.pow(10,d-1);
		return n+s*mask;
	}
	
	public static int getNthPalindrome(int d, int n) {
		int e=0;
		if( d <= 0 )
			return 0;
		if( d%2 == 0 ) {
			d = d/2;
			e = d;
		} else {
			d = (d+1)/2;
			e = d-1;
		}
		
		
		
		return -1;
	}
	
	public static int getDigitofPalindrome(int d, int n, int k) {
		int e=0;
		if( d <= 0 )
			return 0;
		if( d%2 == 0 ) {
			d = d/2;
			e = d;
		} else {
			d = (d+1)/2;
			e = d-1;
		}
		
		// no of digits of n < d
		
		n = n + (int)Math.pow(10, d-1);
		
		n = n*(int)Math.pow(10, e);
		
		for( int i = 1 ; i <= e ; ++i) {
			n = addDigit( n, i , getDigit( n, d-i+1) );
		}
		
		return n;
	}
	
	public static int palindromeStream( int k ) {
		
		int d = 1;
		int bucket;
		while( k > (bucket = d*numPalindrome(d)) ) {
			k -= bucket;
			++d;
			bucket = d*numPalindrome(d);
		} // k <= bucket = d*numPalindrome(d)
		
		int n = k/d;
		k = k % d;
		
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(numPalindrome(0));
		System.out.println(numPalindrome(1));
		System.out.println(numPalindrome(2));
		System.out.println(numPalindrome(3));
		System.out.println(numPalindrome(4));
		System.out.println(numPalindrome(10));
//		System.out.println( getDigitofPalindrome( 2, 1, 1 ) );
	}

}
