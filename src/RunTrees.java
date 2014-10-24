public class RunTrees {

	public static void main (String[] args) {
		Tree testTree = new Leaf(1, null);
		testTree = testTree.setUpTree(3);
		testTree.printTree();
	}
}
