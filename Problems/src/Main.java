import java.io.*;

public class Main {

	public int n = 15;

	// for current solution
	public int k;
	public int[] x;
	public int[] y;

	public static BufferedReader r = new BufferedReader (new InputStreamReader (System.in));

	double x_ = 0;
	double y_ = 0;

	public boolean read() throws IOException {
		String[] line;
		String l = r.readLine();
		System.out.println(l);
		if( l == null )
			return false;

		k = Integer.parseInt( l );
		
		if( k <= 0)
			return false;

		x = new int[k];
		y = new int[k];


		for( int i = 0; i < k; ++i) {
			line = r.readLine().split(" ");
			System.out.println(line[0] + " " + line[1]);
			x[i] = Integer.parseInt( line[0] );
			y[i] = Integer.parseInt( line[1] );

			x_ = ( x_*i + x[i] ) / (i+1);
			y_ = ( y_*i + y[i] ) / (i+1);
		}
		return true;
	}

	public double dist(int w, int z) {
		double sum = 0;
		for( int i = 0; i < k; ++i ) {
			int dx = x[i] - w;
			int dy = y[i] - z;
			sum = sum + Math.sqrt( dx*dx + dy*dy );
		}
//		System.out.println(w + " " + z + " " + sum);
		return sum;
	}

	public double[] solve() {
		
		int xl = (int) Math.floor(x_);
		int xg = (int) Math.ceil(x_);		
		int yl = (int) Math.floor(y_);
		int yg = (int) Math.ceil(y_);

		int[][] check;

		double dmin = Double.MAX_VALUE;
		int xo = Integer.MAX_VALUE;
		int yo = Integer.MAX_VALUE;
		check = new int[][] { { xl, yl } , { xg, yl } , { xl, yg } , { xg, yg } };


		for( int h = 1 ; h > 0; ) {
			double d;
			boolean foundNew = false;
			for( int i = 0; i < check.length; ++i ) {
				d = dist(check[i][0], check[i][1]);
				if( d < dmin ) {
					xo = check[i][0];
					yo = check[i][1];
					dmin = d;
					foundNew = true;
				}
			}

			if( foundNew ) {
				xl = xo - h;
				xg = xo + h;
				yl = yo - h;
				yg = yo + h;
				check = new int[][] { { xl, yl } , { xg, yl } , { xl, yg } , { xg, yg } };
				h /= 2;
			} else {
				h *= 2;
			}

		}

		return new double[] { xo, yo , dmin };
 
	}


	
	public static void main (String[] args) throws java.lang.Exception {
		Main sol = new Main();
		for( int i = 0; i < 15; ++i) {
			if( !sol.read() ) 
				break;
			System.out.printf( "%.5f\n", sol.solve()[2] );
		}
		
		return;
	}
	
}