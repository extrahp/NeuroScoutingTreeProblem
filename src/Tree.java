import java.util.ArrayList;

public class Tree {
	int value;
	Tree left;
	Tree right;
	Tree parent;
	
	public Tree() {
	}
	
	public Tree(int val, Tree par) {
		value = val;
		parent = par;
	}
	
	public int getValue() {
		return value;
	}
	private void setValue(int val) {
		value = val;
	}
	private void setLeft(Tree l) {
		left = l;
	}
	private void setRight(Tree r) {
		right = r;
	}
	
	public Tree getLeft() {
		return left;
	}
	
	public Tree getRight() {
		return right;
	}
	
	public Tree getParent() {
		return parent;
	}
	
	public Tree setUpTree(int num) {
		Tree yes = createChildren(num);
		yes.neuroScope();
		return yes;
	}
	private Tree createChildren(int num) {
		if (num > 1) {
			Tree newTree = new Tree(value, parent);
			Tree newLeft = new Leaf(value, newTree);
			Tree newRight = new Leaf(value, newTree);
			num -= 1;
			newLeft = newLeft.createChildren(num);
			newRight = newRight.createChildren(num);
			newTree.setLeft(newLeft);
			newTree.setRight(newRight);
			return newTree;
		}
		else return this;
		
	}
	
	private boolean hasLeft() {
		Tree currentParent = parent;
		Tree currentThis = this;
		while (currentParent != null && currentParent.getLeft() == currentThis) {
			currentThis = currentParent;
			currentParent = currentParent.getParent();
		}
		return (currentParent != null);
	}
	private boolean hasRight() {
		Tree currentParent = parent;
		Tree currentThis = this;
		while (currentParent != null && currentParent.getRight() == currentThis) {
			currentThis = currentParent;
			currentParent = currentParent.getParent();
		}
		return (currentParent != null);
	}
	
	private int getLeftNeighbor() {
		int levels = 0;
		Tree currentParent = parent;
		Tree currentThis = this;
		while (currentParent.getLeft() == currentThis) {
			levels += 1;
			currentThis = currentParent;
			currentParent = currentParent.getParent();
		}
		return getLeftNeighborHelper(currentParent, levels);
	}
	
	private int getLeftNeighborHelper(Tree start, int levels) {
		start = start.getLeft();
		while (levels > 0) {
			levels -= 1;
			start = start.getRight();
		}
		return start.getValue();
	}
	
	private int getRightNeighbor() {
		int levels = 0;
		Tree currentParent = parent;
		Tree currentThis = this;
		while (currentParent.getRight() == currentThis) {
			levels += 1;
			currentThis = currentParent;
			currentParent = currentParent.getParent();
		}
		return getRightNeighborHelper(currentParent, levels);
	}
	
	private int getRightNeighborHelper(Tree start, int levels) {
		start = start.getRight();
		while (levels > 0) {
			levels -= 1;
			start = start.getLeft();
		}
		return start.getValue();
	}
	
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
	
	public int getHeight() {
		return 1 + left.getHeight();
	}
	
	public void printTree() {
		ArrayList<Integer> theValues = getAllValues();
		double numSpacesFront;
		double numSpacesMiddle;
		int i = 0;
		int lineNum = 0;
		double perLine;
		int posInLine;
		while (lineNum < getHeight()) {
			perLine = Math.pow(2, lineNum);
			posInLine = 0;
			numSpacesFront = Math.pow(2, getHeight()-lineNum) - 1;
			numSpacesMiddle = Math.pow(2, getHeight()-lineNum + 1) - 1;
			makeSpaces(numSpacesFront);
			System.out.print(theValues.get(i));
			i += 1;
			posInLine += 1;
			while (posInLine < perLine) {
				makeSpaces(numSpacesMiddle);
				System.out.print(theValues.get(i));
				i += 1;
				posInLine += 1;
			}
			System.out.println("");
			lineNum += 1;
		}
	}
	
	private void makeSpaces(double nums) {
		while (nums > 0) {
			nums -= 1;
			System.out.print(" ");
		}
	}
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

class Leaf extends Tree {

	public Leaf(int val, Tree par) {
		value = val;
		parent = par;
	}
	
	void neuroScope() {
	}
	
	public int getHeight() {
		return 1;
	}
	
	ArrayList<Integer> getAllValues() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(getValue());
		return nums;
	}
	
	public Tree getLeft() {
		return null;
	}
	
	public Tree getRight() {
		return null;
	}
	
}