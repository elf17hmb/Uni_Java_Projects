package Praktikum03;

import java.util.ArrayList;
import java.util.List;

public class Familytree {
	private static Node root = null;
	public static void main(String[] args) {
		Node Gwendolyn = new Node("Gwendolyn");
		Node Fanny = new Node("Fanny");
		Node Edward = new Node("Edward");
		Node Daniela = new Node("Daniela");
		Node Charlotte = new Node("Charlotte");
		Node Brian = new Node("Brian");
		Node Adam = new Node("Adam");
		List<Node> people = new ArrayList<>();
		setRoot(Adam);
		Adam.addLeft(Brian);
		Adam.addRight(Charlotte);
		Brian.addRight(Daniela);
		Charlotte.addLeft(Edward);
		Charlotte.addRight(Fanny);
		Fanny.addRight(Gwendolyn);

	}
	public void populate() {
		List<Node> people = new ArrayList<>();
		Node Gwendolyn = new Node("Gwendolyn");
		Node Fanny = new Node("Fanny");
		Node Edward = new Node("Edward");
		Node Daniela = new Node("Daniela");
		Node Charlotte = new Node("Charlotte");
		Node Brian = new Node("Brian");
		Node Adam = new Node("Adam");
		people.add(Charlotte);
		people.add(Daniela);
		people.add(Edward);
		people.add(Gwendolyn);
		people.add(Adam);
		people.add(Brian);
		people.add(Fanny);
	}

	public static boolean setRoot(Node node) {
		if (root == null) {
			root = node;
			return true;
		} else {
			return false;
		}
	}

	public void ausgabe() {

	}

}
