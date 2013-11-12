package Test.PocketGems;

import java.io.*;
public class Solution {
    
    int n;
    String[] subs;
//    int[][] map = new int[26][26]; // a before b or b before a
    boolean [][] before = new boolean[26][26];
    boolean [] inAnswer = new boolean[26];
    int length = -1;
    char[] answer;
    
    private String answer() {
    	
    	String s = "";
    	
    	for( int i = 25 ; i >= 0 ; --i) {
    		s = lexOrd(i) + s;
    	}
    	    	
    	return s;
    	
    }

    private String lexOrd(int node) {
    	// 0 <= node < 26
    	String s = "";
    	
    	for( int child = 25; child>=0 ; --child ) {
    		if( before[node][child] ) {
    			s = lexOrd(child) + s;
    		}
    	}
    	if(inAnswer[node])
    		return (char)(node+'a')+s;
    	else
    		return s;
    }

    public String solve() {
        
        String str;
        
        for( int s = 0; s < n ; ++s ) {
            str = subs[s];
            for( int i = 0 ; i < str.length() ; ++i ) {
                for( int j = 0 ; j < str.length() ; ++j ) {
                	
                	before[str.charAt(i) - 'a'][str.charAt(j) - 'a'] = true;
//                    map[str.charAt(i) - 'a'][str.charAt(j) - 'a'] = 1; // before
//                    map[str.charAt(j) - 'a'][str.charAt(i) - 'a'] = -1;// after
                    inAnswer[str.charAt(i) - 'a'] = true;
                    inAnswer[str.charAt(j) - 'a'] = true;
                }
            }
        }
        
        
        
        return answer();
        
        /*
        for( int i = 0 ; i < 26 ; ++i ) {
            position[i] = 0;
            for( int j = 0 ; j < 26 ; ++j ) {
                position[i] += before[i][j]?1:0;
            }
            length = (position[i]>length) ? position[i] : length;
        }
        answer = new char[length];
        
        for( int i = 0 ; i < 26 ; ++i ) {
            if( position[i] > 0 ) {
                answer[position[i]-1] = (char)('a'+i);
            }
        }
        
        return (new String(answer));
        */
    }
    
    public static void read(Solution sol) throws IOException{
        
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
        sol.n = Integer.parseInt( br.readLine() );
        sol.subs = new String[sol.n];
        
        for( int i = 0 ; i < sol.n ; ++i ) {
            sol.subs[i] = br.readLine();
        }
        
    }
    
    public static void main(String args[] ) throws Exception {
        
        Solution sol = new Solution();                
        try {
            read(sol);
        } catch( Exception e ) {
        }
        
        System.out.println(sol.solve());
        
    }
}