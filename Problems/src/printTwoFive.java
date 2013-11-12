
public class printTwoFive {

	public printTwoFive() {
		// TODO Auto-generated constructor stub
	}

	public static int[] twoFive( int n) {
		
		int[] arr = new int[n];
		arr[0] = 1;
		int multTwo  = 0;
		int multFive = 0;
		int t1;
		int t2;
		
		for( int i = 1; i < n ; ++i ) {
			t1 = arr[multTwo] * 2;
			t2 = arr[multFive] * 5;
			if ( t1 < t2 ) {
				arr[i] = t1; 
				++multTwo;
			} else if ( t2 < t1 ) {
				arr[i] = t2;
				++multFive;
			} else { // t1 == t2
				arr[i] = t1;
				++multTwo;
				++multFive;
			}
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ans = twoFive( 20 );
		
		for( int i = 0; i < ans.length; ++i ) {
			System.out.println( ans[i] );
		}
	}

}
