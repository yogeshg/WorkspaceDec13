
public class MyBTree {
	
	class MyNode {
		Integer value;
		MyNode leftTree;
		MyNode rightTree;
		
		public MyNode(Integer v, MyNode l, MyNode r) {
			value = v;
			leftTree = l;
			rightTree = r;
		}
	}
	
	MyNode root;

	public MyBTree() {
		root = null;
	}
	
	public boolean add(Integer arg) {
		if ( root==null ) {
			root = new MyNode(arg, null, null);
			return true;
		} else {
			MyNode it = root;
			while ( it!=null ) {
				if ( arg <= it.value ) {
					if( it.leftTree == null ) {
						it.leftTree = new MyNode( arg, null, null);
						return true;
					} else {
						it = it.leftTree;
					}
				} else {
					if( it.rightTree == null ) {
						it.rightTree = new MyNode( arg, null, null);
						return true;
					} else {
						it = it.rightTree;
					}
				}
			}
			return false;
		}
	}
	
	public void print() {
		print(root, 0);
	}
	
	public void print(MyNode node, int level) {
		if ( node!=null ) {
			print( node.leftTree, level+1);
			String s = "";
			for( int i = 0; i<level; ++i)
				s+="\t";
			System.out.println(s+node.value);
			print( node.rightTree, level+1);
		}
	}
	
	public void printInOrder(MyNode node) {
		if ( node!=null ) {
			printInOrder( node.leftTree);
			System.out.print( node.value +" ");
			printInOrder( node.rightTree);
		}
	}
	
	public int riot(MyNode node, int sum) {
		if( node != null ) {
			node.value += riot(node.rightTree, sum);
			sum = riot(node.leftTree, node.value);
			{
				MyNode temp = node.leftTree;
				node.leftTree = node.rightTree;
				node.rightTree = temp;
			}
			return sum;
		} else {
			return sum;
		}
	}
	
	public void deleteLeaves() {
		deleteLeaves(root);
	}
	
	// returns if the node is to be deleted
	public boolean deleteLeaves( MyNode node ) {
		boolean hasLeft = ( node.leftTree != null );
		boolean hasRight = ( node.rightTree != null );
		if( (!hasLeft) && (!hasRight) ) {
			return true;
		} else {
			if( hasLeft ){
				if( deleteLeaves( node.leftTree ) )
					node.leftTree = null;
			}
			if( hasRight) {
				if( deleteLeaves( node.rightTree ) )
					node.rightTree = null;
			}
			return false;
		}
	}
	
	public void printPreOrder(MyNode node) {
		if ( node!=null ) {
			System.out.print( node.value +" ");
			printPreOrder( node.leftTree);
			printPreOrder( node.rightTree);
		}
	}
	
	public void printPostOrder(MyNode node) {
		if ( node!=null ) {
			printPostOrder( node.leftTree);
			printPostOrder( node.rightTree);
			System.out.print( node.value +" ");
		}
	}
	
	public static void main(String[] args) {
		MyBTree bt = new MyBTree();
		bt.add(4);
		bt.add(2);
		bt.add(1);
		bt.add(3);
		bt.add(6);
		bt.add(7);
		bt.add(5);
		
		bt.print();

		bt.riot(bt.root, 0);
		
		bt.print();
		
		bt.deleteLeaves();
		
		bt.print();
		
		
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public boolean contains(Integer arg) {
		MyNode node = root;
		while( node != null) {
			if( arg == node.value)
				return true;
			else if ( arg < node.value)
				node = node.leftTree;
			else
				node = node.rightTree;
		}
		return false;
	}

	public boolean isEmpty() {
		return ( root == null );
	}

/*	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
*/
	public MyNode delete( MyNode node ) {
		if( node.leftTree==null && node.rightTree==null ) {
			return null;
		} else if( node.leftTree!=null && node.rightTree==null ) {
			return node.leftTree;
		} else if( node.leftTree==null && node.rightTree!=null ) {
			return node.rightTree;
		} else { // both subtrees not null
			MyNode firstP = node;
			MyNode first = firstP.rightTree;
			if( first.leftTree == null ) {
				firstP.rightTree = delete(first);
				first.leftTree = node.leftTree;
				first.rightTree = node.rightTree;
				return first;
			} else {
				while( first.leftTree!=null ) {
					firstP = first;
					first = firstP.leftTree;
				}
				firstP.leftTree = delete(first);
				first.leftTree = node.leftTree;
				first.rightTree = node.rightTree;
				return first;
			}
		}
	}
	
	public MyNode delete( MyNode node, Integer arg) throws Exception {
		if( node == null ) {
			throw new Exception();
		} else if ( arg==node.value ) {
			return delete( node );
		} else if ( arg<node.value ) {
			node.leftTree = delete( node.leftTree, arg);
			return node;
		} else {
			node.rightTree = delete( node.rightTree, arg);
			return node;
		}
	}
	
	public boolean remove(Integer arg) {
		try {
			root = delete( root, arg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}


// NOTE contain should probably use get : to avoid code duplicacy!