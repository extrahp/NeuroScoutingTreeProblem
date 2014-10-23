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
	public void setValue(int val) {
		value = val;
	}
	void setLeft(Tree l) {
		left = l;
	}
	void setRight(Tree r) {
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
	
	public boolean hasLeft() {
		Tree currentParent = parent;
		Tree currentThis = this;
		while (currentParent != null && currentParent.getLeft() == currentThis) {
			currentThis = currentParent;
			currentParent = currentParent.getParent();
		}
		return (currentParent != null);
	}
	public boolean hasRight() {
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
		ArrayList<Integer> theList = getAllValues();
		for (int i = 0; i < theList.size(); i+= 1) {
			System.out.println(theList.get(i));
		}
	}
	
	ArrayList<Integer> getAllValues() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(getValue());
		nums.addAll(left.getAllValues());
		nums.addAll(right.getAllValues());
		return nums;
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
}