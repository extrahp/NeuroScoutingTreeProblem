import java.util.Scanner;

public class RunTrees {
	
	static int errors = 0;
	
	private static void testStuff(int i1, int i2) {
		if (i1 != i2)
			errors += 1;
	}

	
	public static void main (String[] args) {
		Tree testTree = new Leaf(1, null);
		testTree = testTree.setUpTree(1);
		testStuff(testTree.getValue(), 1);
		testTree = testTree.setUpTree(2);
		testStuff(testTree.getValue(), 1);
		testStuff(testTree.getLeft().getValue(), 1);
		testStuff(testTree.getRight().getValue(), 1);
		testTree = testTree.setUpTree(4);
		testStuff(testTree.getValue(), 1);
		testStuff(testTree.getLeft().getValue(), 1);
		testStuff(testTree.getLeft().getRight().getValue(), 2);
		testStuff(testTree.getLeft().getLeft().getValue(), 1);
		testStuff(testTree.getLeft().getLeft().getRight().getValue(), 3);
		testStuff(testTree.getLeft().getRight().getRight().getValue(), 4);
		testStuff(testTree.getRight().getLeft().getLeft().getValue(), 
				testTree.getLeft().getRight().getRight().getValue());
		System.out.println("There were " + Integer.toString(errors) + " errors from the tests.");
		System.out.println("Hi! Welcome to the NeuroScouting Tree Creator!");
		System.out.print("Please enter your tree's height as an integer: ");
		Scanner in = new Scanner(System.in);
		int input;
		input = in.nextInt();
		testTree = testTree.setUpTree(input);
		testTree.printTree();
	}
}
