import java.io.*;
import java.util.Arrays;


public class Solution3 {
	
	boolean debug = true;
	
	int[] arr;
	int[] sum;
	int n;
	int l;
	int r;
	
	public boolean check(int num) {
		return( num>=l && num<=r);
	}
	
	public Solution3(String[] line1, String[] line2) {
		n = Integer.parseInt(line1[0]);
		l = Integer.parseInt(line1[1]);
		r = Integer.parseInt(line1[2]);
		arr = new int[n];
		sum = new int[n+1];
		
		for( int i = 0; i < n ; ++i ) {
			arr[i] = Integer.parseInt(line2[i]);
		}
		Arrays.sort(arr);

		sum[0] = 0;//arr[0]; 
		for( int i = 1; i < n+1 ; ++i ) {
			sum[i] = arr[i-1]+sum[i-1];
		}
	}
	
	public int solve() {
		int count = 0;
		for(int i = 0 ; i < n ; ++i) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		for( int i = 0 ; i < n+1 ; ++ i ) {
			
			for( int j = 0 ; j < i ; ++j ) {
				System.out.println(j+" "+i+" : "+ (sum[i] - sum[j]) + " " + check( sum[i] - sum[j] ));
				if( check( sum[i] - sum[j] ) ) {
					count+=1;
				}
				
			}
			
		}
		
		/*if( check(sum[n-1]) ) {
			count+=1;
		}
		if( check(0) ) {
			count+=1;
		}*/
		
		return count;
	}
	
	static BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );

	public static void main(String[] args) {
		
		int t = -1;
		Solution3 sol;
		
		try {
			t = Integer.parseInt(br.readLine());
			
			for( int i = 0 ; i < t ; ++i ) {
				
				sol = new Solution3(br.readLine().split(" "), br.readLine().split(" "));
				
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