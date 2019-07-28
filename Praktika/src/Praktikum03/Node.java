package Praktikum03;

import java.util.HashMap;
import java.util.Map;

public class Node {
	String name;
	Node left, right;
	Node root = null;
	Person person;
	Map<String, Person> tree = new HashMap<>();

	public Node(String name) {
		this.name = name;
		left = null;
		right = null;

	}

	public Node(String name, Node left, Node right) {
		this.name = name;
		this.left = left;
		this.right = right;

	}

	public boolean addLeft(Node node) {
		Node parent = locate(node.getName());
		if (parent.getLeft() == null || parent == null) {
			return false;
		} else {
			parent.left = node;
			return true;
		}
	}

	public boolean addRight(Node node) {
		Node parent = locate(node.getName());
		if (parent.right == null || parent == null) {
			return false;
		} else {
			parent.right = node;
			return true;
		}
	}

	public Node locate(String p) {
		// Call the private recursive method
		return locate(p, root);
	}

	private Node locate(String p, Node famTree) {
		Node result = null;
		if (famTree == null)
			return null;
		if (famTree.name.equals(p))
			return famTree;
		if (famTree.left != null)
			result = locate(p, famTree.left);
		if (result == null)
			result = locate(p, famTree.right);
		return result;
	}

	public String getName() {
		return name;
	}

	public Node getLeft() {
		return left;
	}

}
