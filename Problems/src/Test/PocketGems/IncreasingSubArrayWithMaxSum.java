package Test.PocketGems;

import java.util.Arrays;
import java.util.Random;

import Test.PocketGems.Pair; 


public class IncreasingSubArrayWithMaxSum {
	
	private static final boolean debug = false;
	
	public static int solve( Pair[] pairs  ) {
		
		int maxSum = 0;
		int optI = -1;
		int optJ = -1;
		
		int curSum = 0;
		int curI = 0;
		int curJ = 0;
		
		int prev_a = Integer.MIN_VALUE;
		
		for( int i = 0 ; i < pairs.length ; ++i ) {
			if( pairs[i].a >= prev_a ) {
				prev_a = pairs[i].a;
				curSum += pairs[i].b;
				curJ = i;
			} else {
				prev_a = pairs[i].a;
				curSum = pairs[i].b;
				curI = curJ = i;
			}
			if(curSum > maxSum) {
				maxSum = curSum;
				optI = curI;
				optJ = curJ;
			}
		}
		if( debug )
			System.out.println(optI +" "+ optJ);
		return maxSum;
	}
	
	public static int findMaxSum( int[] a, int[] b  ) {
		
		int maxSum = 0;
		int optI = -1;
		int optJ = -1;
		
		int curSum = 0;
		int curI = 0;
		int curJ = 0;
		
		int prev_a = Integer.MIN_VALUE;
		
		for( int i = 0 ; i < a.length ; ++i ) {
			if( a[i] >= prev_a ) {
				prev_a = a[i];
				curSum += b[i];
				curJ = i;
			} else {
				prev_a = a[i];
				curSum = b[i];
				curI = curJ = i;
			}
			if(curSum > maxSum) {
				maxSum = curSum;
				optI = curI;
				optJ = curJ;
			}
		}
		System.out.println(optI +" "+ optJ);
		return maxSum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random r = new Random(System.currentTimeMillis() );
		
		Pair[ ] pairs = new Pair[10];
		int[] a = new int[10];
		int[] b = new int[10];
		
		for( int i = 0 ; i < 10 ; ++i ) {
			pairs[i] = new Pair(r.nextInt(10), 100+r.nextInt(100));
			a[i] = pairs[i].a;
			b[i] = pairs[i].b;
		}
		int sum = 0;
		for( int i = 0 ; i < 10; ++i) {
			sum += pairs[i].b;
		}
		System.out.println(sum);

//		Arrays.sort(pairs, Pair.PairComparator);
		System.out.println( Arrays.toString(pairs) );
		System.out.println( solve(pairs) );
		System.out.println( findMaxSum(a, b) );
	}

}
