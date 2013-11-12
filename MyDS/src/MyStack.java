import java.io.*;
import java.util.logging.*;

//TODO Check Java 1.6 vs 1.7
//TODO Check for standard string library

public class MyStack {
	
	int[] stack;
	private int size = 0;
	private static final Logger log = Logger.getLogger( MyStack.class.getName() );

	public int size() {
		return size;
	}
	
	public MyStack( int n ) {
		stack = new int[n];
		size = 0;
		log.log(Level.INFO, "Stack Capacity: "+n);
	}
	
	public MyStack() {
		this( 8 );
	}
	
	public void push(int p) {
		if( size < stack.length ) {
			stack[size++] = p;
		} else { // size == stack.length
				//TODO Check if I should make a check for size > stack.length
			int[] temp = stack;
			stack = new int[2*size];
			log.log(Level.INFO, "Stack Capacity increased to: "+2*size);
			//TODO Check if we can call constructor here "call constructor elsewhere / in between"
			
			for( int i=0; i<size; ++i) {
				stack[i] = temp[i];
			}
			
			stack[size++] = p;
			
		}
	}
	
	public int pop() {
//		if( size > 0 )
			return stack[--size] ;
//		else
//			throw new OutofBoundException();
		//TODO Check how to create new Exceptions
	}
	
	public int top(){
		// top == peek
		int t = pop();
		push(t);
		return t;
	}
	
	public void print(){
		System.out.println("-in-out-");
		for( int i=size; i>0; --i ) {
			System.out.println("|"+stack[i-1]);
		}
		System.out.println("--------");
	}
	
	private static int switchString2int(String s) {
		if( s.equalsIgnoreCase("push"))
			return 1;
		else if( s.equalsIgnoreCase("pop") )
			return 2;
		else if( s.equalsIgnoreCase("top") )
			return 3;
		else if( s.equalsIgnoreCase("size"))
			return 4;
		else if( s.equalsIgnoreCase("print"))
			return 5;
		else
			return -1;
	}
	
	public static void main( String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		MyStack st = new MyStack();

		int n;
		try {
			n = Integer.parseInt(br.readLine());
		} catch (Exception e1) {
			n = 0;
		}
		String[] line;
		for( int i=0; i<n; ++i) {
			try {
				line = br.readLine().split(" ");
				//process:
				switch( switchString2int(line[0]) ) {
				case 1:
					st.push(Integer.parseInt(line[1]));
					break;
				case 2:
					System.out.println(st.pop());
					break;
				case 3:
					System.out.println(st.top());
					break;
				case 4:
					System.out.println(st.size());
					break;
				case 5:
					st.print();
					break;
				case -1:
				default:
					//TODO Check standard library for string functions
					log.log(Level.WARNING, "Invalid Command: " + line[0] );
				}
				//End process
			} catch (IOException e) {
				log.log(Level.WARNING, "Error Reading Line");
			} catch (ArrayIndexOutOfBoundsException e) {
				log.log(Level.WARNING, "Unexpected Format");
			}
			
		}

	}

}
