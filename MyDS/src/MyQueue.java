
public class MyQueue {
	
	int _q[];
	private int i = 0; //last unoccupied index
	private int j = 0; //last occupied index
	//never let i = j unless empty!!!
	
	public int size() {
		return mod(j-i, _q.length);
		// max size when capacity -1
	}

	public MyQueue(int n) {
		_q = new int[n];
		i = 0;
		j = 0;
		System.out.println("Queue Capacity: "+n);
	}
	
	public MyQueue() {
		this(8);
	}
	
	private int mod(int i, int l) {
		if( i>=0 && i<l )
			return i;
		else if(i>=l)
			return mod(i-l, l);
		else // i < _q.length
			return mod(i+l, l);
	}
	
	public void enqueue(int p) {
		if(size()<_q.length-1) { // can't fill all the capacity, need to keep 1 unfilled
			j = mod(++j, _q.length);
			_q[j] = p;
		} else { // size() == _q.length-1
			int[] temp = _q;
			int l = _q.length;
			_q = new int[l*2];
			System.out.println("Stack Capacity increased to: "+ 2*l);
			
			int s = 0;
			for( int r=mod(i+1,l); mod(j-r+1,l)>0 ; r=mod(r+1,l) ) {
				//System.out.println("i:"+i + " r:"+r + " j:"+j);
				_q[++s] = temp[r];
			}
			i = 0;
			j = ++s;
			_q[j] = p;
		}
	}
	
	public int dequeue() {
		if( i!= j) {
			i = mod(++i, _q.length);
			return _q[i];
		} else {
			return _q[-1];
			//TODO adhoc fix for exception
		}
	}

	public void print(){
		System.out.println("-in-----");
		for( int r=j; mod(r-i,_q.length)>0; r=mod(--r,_q.length) ) {
			System.out.println("|"+_q[r]);
		}
		System.out.println("-out----");
	}
	
	private static int switchString2int(String s) {
		if( s.equalsIgnoreCase("enq")||s.equalsIgnoreCase("enqueue"))
			return 1;
		else if( s.equalsIgnoreCase("deq")||s.equalsIgnoreCase("dequeue") )
			return 2;
		else if( s.equalsIgnoreCase("size") )
			return 3;
		else if( s.equalsIgnoreCase("print"))
			return 4;
		else
			return -1;
	}

	public static void main( String[] args) {
		MyQueue q = new MyQueue(4);

		int n = Integer.parseInt(System.console().readLine());
		String[] line;
		for( int i=0; i<n; ++i) {
			line = System.console().readLine().split(" ");
			switch( switchString2int(line[0]) ) {
			case 1:
				q.enqueue(Integer.parseInt(line[1]));
				break;
			case 2:
				System.out.println(q.dequeue());
				break;
			case 3:
				System.out.println(q.size());
				break;
			case 4:
				q.print();
				break;
			case -1:
			default:
				//TODO Check standard library for string functions
				System.out.println( "Invalid Command: " + line[0] );
			}

		}

	}


}
