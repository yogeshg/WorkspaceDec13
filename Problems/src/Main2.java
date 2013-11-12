CodeChef submission 521546 (JAVA) plaintext list. Status: AC, problem CPOINT, contest . By madkite (madkite), 2011-04-14 15:48:50.
import java.io.*;
import java.math.*;
import java.util.*;
 
/**
 * Codechef/April Long Contest/CPOINT
 * @author Roman Kosenko <madkite@gmail.com>
 */
public class Main {
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		File file = new File("input.txt");
		FastScanner scanner = new FastScanner(!file.exists() ? System.in : new FileInputStream(file));
		for(int n, k = 15;; k--) {
			if((n = scanner.nextInt()) == 0)
				break;
			x = new int[n]; y = new int[n];
			long sx = 0, sy = 0;
			for(int i = 0; i < n; i++) {
				sx += x[i] = scanner.nextInt();
				sy += y[i] = scanner.nextInt();
			}
			int cx = (int)((sx * 2 + n) / n / 2), cy = (int)((sy * 2 + n) / n / 2);
			int r[] = solveGradient(cx, cy), rx = r[0], ry = r[1];
			double dx = rx - cx, dy = ry - cy, t = Math.sqrt(dx * dx + dy * dy);
			System.out.printf("%.6f\n", solveCauchy(rx, ry, Math.max(100, Math.sqrt(t)), (TIME_LIMIT + startTime - System.currentTimeMillis()) / k));
		}
		//System.out.println(System.currentTimeMillis() - startTime);
	}
	private static XorRandom random = new Xor64Random();
	private static final long TIME_LIMIT = 14000;
	private static double solveCauchy(int x, int y, double t0, long timeLimit) {
		long endTime = System.currentTimeMillis() + timeLimit;
		double e = d(x, y), result = e;
		for(int k = 2; (k & 0xff) != 0 || System.currentTimeMillis() < endTime; k++) {
			double t = t0 / Math.sqrt(k);
			if(t < 1)
				break;
			for(;;) {
				int tx, ty;
				do {
					tx = (int)(t * Math.tan(Math.PI * (random.nextDouble() - .5)) + .5);
					ty = (int)(t * Math.tan(Math.PI * (random.nextDouble() - .5)) + .5);
				} while(tx == 0 && ty == 0);
				tx += x;
				ty += y;
				double d = d(tx, ty);
				if(d < result)
					result = d;
				else if(random.nextDouble() >= Math.exp((e - d) / t))
					continue;
				x = tx; y = ty;
				e = d;
				break;
			}
		}
		return result;
	}
	private static double solveBoltzmann(int x, int y, double t0, long timeLimit) {
		long endTime = System.currentTimeMillis() + timeLimit;
		double e = d(x, y), result = e;
		for(int k = 2; (k & 0xff) != 0 || System.currentTimeMillis() < endTime; k++) {
			double t = t0 / Math.log(k);
			if(t < 1)
				break;
			for(;;) {
				int tx, ty;
				do {
					tx = (int)(t * random.nextGaussian() + .5);
					ty = (int)(t * random.nextGaussian() + .5);
				} while(tx == 0 && ty == 0);
				tx += x;
				ty += y;
				double d = d(tx, ty);
				if(d < result)
					result = d;
				else if(random.nextDouble() >= Math.exp((e - d) / t))
					continue;
				x = tx; y = ty;
				e = d;
				break;
			}
		}
		return result;
	}
	private static int[] solveGradient(int cx, int cy) {
		double d = d(cx, cy), p;
		for(int h = 1; h > 0;) {
			p = d;
			for(int i = 0; i != SM;) {
				int k = random.nextInt(S.length), s[] = S[k], tx = cx + s[0] * h, ty = cy + s[1] * h;
				double t = d(tx, ty);
				if(t < d) {
					d = t;
					cx = tx; cy = ty;
				}
				i |= 1 << k;
			}
			if(d != p)
				h *= 2;
			else
				h /= 2;
		}
		return new int[]{cx, cy};
	}
	private static final int S[][] = new int[][]{{-1, 0}, {+1, 0}, {0, -1}, {0, +1}, /*{-1, -1}, {-1, +1}, {+1, -1}, {+1, +1}*/}, SM = (1 << S.length) - 1;
	private static int[] x, y;
	private static double d(int cx, int cy) {
		double r = 0;
		for(int i = 0, n = x.length; i < n; i++) {
			double dx = x[i] - cx, dy = y[i] - cy;
			r += Math.sqrt(dx * dx + dy * dy);
		}
		return r;
	}
	private static double d2(int cx, int cy) {
		BigDecimal r = BigDecimal.ZERO;
		for(int i = 0, n = x.length; i < n; i++) {
			double dx = x[i] - cx, dy = y[i] - cy;
			r = r.add(BigDecimal.valueOf(Math.sqrt(dx * dx + dy * dy)));
		}
		return r.doubleValue();
	}
	private static void test() throws IOException {
		PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream("input.txt")));
		final int n = 2000, max = (int)1e9;
		Random random = new Random();
		for(int j = 15; j > 0; j--) {
			writer.println(n);
			for(int i = n; i > 0; i--)
				writer.println((random.nextInt(2 * max) - max) + " " + (random.nextInt(2 * max) - max));
		}
		writer.println("0");
		writer.close();
	}
}
 
 
abstract class XorRandom extends Random {
	@Override
	public abstract int nextInt();
	@Override
	protected int next(int bits) {
		return nextInt() >>> (32 - bits);
	}
	@Override
	public int nextInt(int n) {
		return (nextInt() >>> 1) % n;
	}
	@Override
	public long nextLong() {
		return ((long)(nextInt()) << 32) + nextInt();
	}
	private static final double MAX_DOUBLE = (double)(1l << 53);
	@Override
	public double nextDouble() {
		return (nextLong() >>> 11) / MAX_DOUBLE;
	}
}
 
class Xor64Random extends XorRandom {
	private long x;
	public Xor64Random(long seed) {
		x = seed;
	}
	public Xor64Random() {
		this(System.nanoTime());
	}
	@Override
	protected int next(int bits) {
		return (int)(nextLong() >>> (64 - bits));
	}
	@Override
	public long nextLong() {
		x ^= (x << 13);
		x ^= (x >>> 7);
		x ^= (x << 17);
		return x;
	}
	@Override
	public int nextInt() {
		return (int)nextLong();
	}
}
 
class FastScanner {
	private final InputStream is;
	private byte[] data = new byte[0x2000];
	private int next, size;
	public FastScanner(InputStream is) {
		this.is = is;
	}
	private boolean read() throws IOException {
		size = is.read(data);
		if(size == 0) {
			int i = is.read();
			if(i < 0)
				return false;
			data[0] = (byte)i;
			size = 1;
		} else if(size < 0)
			return false;
		next = 0;
		return true;
	}
	public int nextInt() throws IOException {
		int r; boolean negative = false;
		do {
			if(next >= size && !read())
				throw new EOFException();
			negative |= (r = data[next++]) == '-';
		} while(r < '0' || r > '9');
		r -= '0';
		for(int d; (next < size || read()) && '0' <= (d = data[next++]) && d <= '9';)
			r = r * 10 + d - '0';
		return negative ? -r : r;
	}
}
 