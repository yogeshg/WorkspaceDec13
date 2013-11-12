package Test.PocketGems;

import java.util.Comparator;

public class Pair implements Comparable<Pair> {
	int a;
	int b;
	
	public Pair(int arg1, int arg2) {
		a = arg1;
		b = arg2;
	}
	
	public int compareTo( Pair q ) {
		return ( this.b - q.b );
	}
	
	public String toString() {
		return "<"+a+","+b+">";
	}
	
	public static Comparator<Pair> PairComparator = new Comparator<Pair>() {
		public int compare(Pair p, Pair q) {
			return p.a-q.a ;
		}
	};
}