import java.util.Scanner;

//The running class
public class RunTrees {
	
	//count if there are any errors found from the tests
	static int errors = 0;
	
	//test function. if the expected test failed and were not equal, increase error count
	private static void testStuff(int i1, int i2) {
		if (i1 != i2)
			errors += 1;
	}

	//main function, run this
	public static void main (String[] args) {
		
		//Tests
		Tree testTree = new Leaf(null);
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
		//End of tests
		
		System.out.println("There were " + Integer.toString(errors) + " errors from the tests.");
		System.out.println("Hi! Welcome to the NeuroScouting Tree Creator!");
		
		Scanner in = new Scanner(System.in);
		int input = -1;
		
		while (input != 0) {
			System.out.print("Please enter your tree's height as an integer. Enter 0 to end: ");
			input = in.nextInt();
			if (input != 0) {
				testTree = testTree.setUpTree(input);
				testTree.printTree();
			}
		}
		System.out.println("Thank you for using NeuroScouting Tree Generator! Have a good day!");
	}
}
