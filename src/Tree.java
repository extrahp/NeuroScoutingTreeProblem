import java.util.ArrayList;

//The Tree Node Class
public class Tree {
	//the value to be stored in the node
	int value;
	//the tree's left child
	Tree left;
	//the tree's right child
	Tree right;
	//the tree's parent node, null if root node
	Tree parent;
	
	//default tree constructor
	public Tree() {
	}
	
	//tree constructor that takes a parent
	public Tree(Tree par) {
		value = 1;
		parent = par;
	}
	
	//returns the value stored in this node
	public int getValue() {
		return value;
	}
	
	//sets the value to be stored in this node
	private void setValue(int val) {
		value = val;
	}
	
	//sets the left child of this node
	private void setLeft(Tree l) {
		left = l;
	}
	
	//sets the right child of this node
	private void setRight(Tree r) {
		right = r;
	}
	
	//returns the Tree stored as the left child of this node
	public Tree getLeft() {
		return left;
	}
	
	//returns the Tree stored as the right child of this node
	public Tree getRight() {
		return right;
	}
	
	//returns the Tree stored as the parent of this node
	public Tree getParent() {
		return parent;
	}
	
	//returns a new tree created from the given height
	public Tree setUpTree(int num) {
		Tree yes = createChildren(num);
		yes.neuroScope();
		return yes;
	}
	
	//a helper function that creates all the children of the tree, but will have incorrect values
	private Tree createChildren(int num) {
		if (num > 1) {
			Tree newTree = new Tree(parent);
			Tree newLeft = new Leaf(newTree);
			Tree newRight = new Leaf(newTree);
			num -= 1;
			newLeft = newLeft.createChildren(num);
			newRight = newRight.createChildren(num);
			newTree.setLeft(newLeft);
			newTree.setRight(newRight);
			return newTree;
		}
		else return new Leaf(null);
		
	}
	
	//checks if this node has a left neighbor
	private boolean hasLeft() {
		Tree currentParent = parent;
		Tree currentThis = this;
		while (currentParent != null && currentParent.getLeft() == currentThis) {
			currentThis = currentParent;
			currentParent = currentParent.getParent();
		}
		return (currentParent != null);
	}
	
	//checks if this node has a right neighbor
	private boolean hasRight() {
		Tree currentParent = parent;
		Tree currentThis = this;
		while (currentParent != null && currentParent.getRight() == currentThis) {
			currentThis = currentParent;
			currentParent = currentParent.getParent();
		}
		return (currentParent != null);
	}
	
	//returns the value of the node directly to the left of this node
	private int getLeftNeighbor() {
		int levels = 0;
		Tree currentParent = parent;
		Tree currentThis = this;
		//continue going up the levels until we get to the opposite side of the tree
		while (currentParent.getLeft() == currentThis) {
			levels += 1;
			currentThis = currentParent;
			currentParent = currentParent.getParent();
		}
		//then go down the same number of levels as we traveled up to get the neighbor
		return getLeftNeighborHelper(currentParent, levels);
	}
	
	//helper function that goes down to the correct neighbor from the root node
	private int getLeftNeighborHelper(Tree start, int levels) {
		start = start.getLeft();
		while (levels > 0) {
			levels -= 1;
			start = start.getRight();
		}
		return start.getValue();
	}
	
	//returns the value of the node directly to the right of this node
	private int getRightNeighbor() {
		int levels = 0;
		Tree currentParent = parent;
		Tree currentThis = this;
		//continue going up the levels until we get to the opposite side of the tree
		while (currentParent.getRight() == currentThis) {
			levels += 1;
			currentThis = currentParent;
			currentParent = currentParent.getParent();
		}
		//then go down the same number of levels as we traveled up to get the neighbor
		return getRightNeighborHelper(currentParent, levels);
	}
	
	//helper function that goes down to the correct neighbor from the root node
	private int getRightNeighborHelper(Tree start, int levels) {
		start = start.getRight();
		while (levels > 0) {
			levels -= 1;
			start = start.getLeft();
		}
		return start.getValue();
	}
	
	//a special function that correctly sets all the values of all children so that
	//the sum of the child is equal to the parent plus the corresponding neighbor's values
	void neuroScope() {
		if (hasLeft()) {
			left.setValue(value + getLeftNeighbor());
		}
		if (hasRight()) {
			right.setValue(value + getRightNeighbor());
		}
		left.neuroScope();
		right.neuroScope();
		left.neuroScope();
	}
	
	//returns the height of this  tree
	public int getHeight() {
		return 1 + left.getHeight();
	}
	
	//a function that prints out a binary tree of any height
	public void printTree() {
		ArrayList<Integer> theValues = getAllValues();
		double numSpacesFront;
		double numSpacesMiddle;
		int i = 0;
		int lineNum = 0;
		double perLine;
		int posInLine;
		//goes through and figures out what to print line by line
		while (lineNum < getHeight()) {
			//prints 1 number on first line, 2 numbers on second line, 4 on third line, etc...
			perLine = Math.pow(2, lineNum);
			//reset the position of the numbers on each line
			posInLine = 0;
			numSpacesFront = Math.pow(2, getHeight()-lineNum) - 1;
			numSpacesMiddle = Math.pow(2, getHeight()-lineNum + 1) - 1;
			//make the first set of spaces
			makeSpaces(numSpacesFront);
			//print the first value of the line
			System.out.print(theValues.get(i));
			//increment to next value in the arraylist of all tree values
			i += 1;
			//increment the position of the number
			posInLine += 1;
			//repeat and print all values that need to be printed on that line
			while (posInLine < perLine) {
				//spaces inbetween
				makeSpaces(numSpacesMiddle);
				System.out.print(theValues.get(i));
				i += 1;
				posInLine += 1;
			}
			//go to next line
			System.out.println("");
			lineNum += 1;
		}
	}
	
	//a helper function that creates the correct number of spaces between each node
	//when printing to console
	private void makeSpaces(double nums) {
		while (nums > 0) {
			nums -= 1;
			System.out.print(" ");
		}
	}
	
	//a function that returns all the values in the tree as an arraylist, in a Breadth First sort
	ArrayList<Integer> getAllValues() {
		ArrayList<Tree> queue = new ArrayList<Tree>();
		ArrayList<Integer> theValues = new ArrayList<Integer>();
		queue.add(this);
		while (!queue.isEmpty()) {
			Tree currentTree = queue.remove(0);
			theValues.add(currentTree.getValue());
			if (currentTree.getLeft() != null)
			queue.add(currentTree.getLeft());
			if (currentTree.getRight() != null)
			queue.add(currentTree.getRight());
		}
		return theValues;
	}
}

//the Leaf class, where has no children
class Leaf extends Tree {

	//Leaf constructor
	public Leaf(Tree par) {
		value = 1;
		parent = par;
	}
	
	//ends recursion
	void neuroScope() {
	}
	
	//returns the height of the leaf, which is always 1
	public int getHeight() {
		return 1;
	}
	
	//returns the value of the leaf as an arraylist
	ArrayList<Integer> getAllValues() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(getValue());
		return nums;
	}
	
	//returns the left child, which will be null
	public Tree getLeft() {
		return null;
	}
	
	//returns the right child, which will be null
	public Tree getRight() {
		return null;
	}
	
}