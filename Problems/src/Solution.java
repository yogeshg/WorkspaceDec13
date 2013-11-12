import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public class Node {
        
        public char a;
        public Node leftTree = null;
        public Node rightTree = null;
        
        public Node(char t) {
            a = t;
        }
    };
        
    Node root = null;
        
    public int findPaths( int N, String S, int K, String A ) {
        root = new Node( S.charAt(N-1) );
        System.out.println(root.a);
        return 0;
    }
    
    static int explodePaths(int N, String S, int K, String A) {
        
        Solution sol = new Solution();
        
        return sol.findPaths(N, S, K, A);
                
        /* Implement this function */
        
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;
        int _N;
        _N = in.nextInt();
        
        String _S;
        _S = in.next();
        
        int _K;
        _K = in.nextInt();
        
        String _A;
        _A = in.next();
        
        res = explodePaths(_N, _S, _K, _A);
        System.out.println(res);
        
    }
}