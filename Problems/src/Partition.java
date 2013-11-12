
public class Partition {

	public Partition() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main( String[] args ) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
		
		if (partitionEqualNum(arr) )
			System.out.println("Found!");
		else
			System.out.println("Can't Find!");
		
	}
	
	public static boolean partitionEqualNum( int[] arr ) {
		
		/*
		 * T[i][s][l]
		 * i : last element considered
		 * s : sum required
		 * l : length of element
		 * 
		 * T[i][s][l] = T[i-1][s][l] || T[i-1][s-item_i][l-1]
		 * 
		 * final answer:
		 * fix l = n/2
		 * start looking for s = S/2 downwards
		 * 
		 */
		
		int N = arr.length;
		int S = sumOfArray( arr );
		int requiredSum = S/2;
		int requiredLength = N/2;
		
		System.out.println( N + " " + S);
		System.out.println( requiredLength + " " + requiredSum);

		boolean[][][] T = new boolean[N][requiredSum+1][requiredLength+1];
		
		for( int i = 0 ; i < N ; ++i ) {
			T[i][0][0] = true;
		}
		for ( int s = 1 ; s <= requiredSum; ++s ) {
			T[0][s][1] = ( arr[0]==s );
			
			for ( int l = 2; l <= requiredLength; ++l ){
				T[0][s][l] = false;
			}
		}
		for ( int i = 1 ; i < N ; ++i ) {
			for ( int s = 1 ; s <= requiredSum; ++s) {
				for ( int l = 1; l <= requiredLength; ++l ) {
					if( arr[i] <= s ) {
						T[i][s][l] = T[i-1][s-arr[i]][l-1];
					} else {
						T[i][s][l] = false;
					}
					T[i][s][l] = T[i][s][l] || T[i-1][s][l];
				}
			}
		}
		
		for( int s = requiredSum ; s >= 0; --s ) {
			for( int i = 0 ; i < N ; ++i ) {
				if( T[i][s][requiredLength] ) {
					System.out.println(i + " " + s);
					return true;
				}
			}
		}
		return false;
	}

	public static int sumOfArray(int [] A) {
		int len = A.length;
		int sum = 0;
		for (int i=0; i<len; i++) {
			sum += A[i];
		}
		return sum;
	}
	

}
