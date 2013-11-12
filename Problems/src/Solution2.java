import java.io.*;
import java.util.Arrays;


public class Solution2 {
	
	boolean debug = true;
	
	int[] arr;
	int x;
	int n;
	
	public Solution2(String line0, String[] line1, String line2) {
		n = Integer.parseInt(line0);
		arr = new int[n];
		
		for( int i = 0; i < n ; ++i ) {
			arr[i] = Integer.parseInt(line1[i]);
		}
		Arrays.sort(arr);
		x = Integer.parseInt(line2);
	}
	
	public int numNeg() {
		return numNeg(0, n-1);
	}
	
	public void addAll(int inc){
		for( int i = 0 ; i < n ; ++i ) {
			arr[i]+=inc;
		}
	}
	
	public int numNeg(int i, int j) {
		if( arr[i] >= 0 ) {
			return 0;
		} else if( i == j) {
			return 1;
		}
		
		int mid = (i+j)/2;
		
		if (arr[mid+1]>=0) {
			return numNeg(i,mid);
		} else { // arr[mid+1] < 0
			return ( (mid+1-i) + numNeg( mid+1, j ) );
		}
	}
	
	public int solve() {
		int neg = -1;
		int cost = 0;
		int inc = -1;
		for( int i = n-1 ; i >= 0 ; --i ) {
			inc = -arr[i];
			
			if( inc >= 0 )
			{
				neg = numNeg();
				if( neg > x ) {
					// do x step inc times
					cost += inc * x;
					addAll(inc);
				} else {
					// do 1 step inc times
					cost += inc * 1;
					arr[i]+=inc;
				}
				if( debug ) printArr();
			}
		}
		
		 return cost;
	}
	
	public void printArr() {
		for( int i = 0; i < n ; ++i ){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	static BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );

	public static void main(String[] args) {
		
		int t = -1;
		Solution2 sol;
		
		try {
			t = Integer.parseInt(br.readLine());
			
			for( int i = 0 ; i < t ; ++i ) {
				
				sol = new Solution2(br.readLine(), br.readLine().split(" "), br.readLine());
				
				System.out.println(sol.solve());
				
			}
			
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
