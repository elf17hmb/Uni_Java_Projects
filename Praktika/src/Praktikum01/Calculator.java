package Praktikum01;

import java.util.Stack;

public class Calculator {
	String num;
	Stack<Integer> stack = new Stack<Integer>();
	Stack<String> stack2 = new Stack<String>();

	public Calculator(String num) {
		this.num = num;
	}

	public int calculate() {
		char[] chararray = num.toCharArray();
		for (int i = 0; i < num.length(); i++) {
			String currentString = num.substring(i, i + 1);
			System.out.println("Aktuelles Zeichen:" + currentString);

			// Integer currentValue;
			if (Character.isDigit(chararray[i])) {
				stack.push(Integer.parseInt(Character.toString(chararray[i])));

			} else if (!stack.isEmpty()) {
				if (currentString.equals("+")) {
					stack2.push(Character.toString(chararray[i]));
				} else if (currentString.equals("-")) {
					stack2.push(Character.toString(chararray[i]));
				} else if (currentString.equals("*")) {
					i++;
					stack.push(stack.pop() * Integer.parseInt(num.substring(i, i + 1)));
				} else if (currentString.equals("/")) {
					i++;
					stack.push(stack.pop() / Integer.parseInt(num.substring(i, i + 1)));
				}
				// System.out.println(stack.peek());
			} else {
				throw new IllegalArgumentException();
			}

			// System.out.println(chararray[i]);
		}
		// System.out.println(stack2.size());
		while (!stack2.isEmpty()) {
			String symbol = stack2.pop();
			if (symbol.equals("+")) {
				stack.push(stack.pop() + stack.pop());
			} else if (symbol.equals("-")) {
				int value = stack.pop();
				stack.push(stack.pop() - value);
			}
		}
		// System.out.println("fertig: " + stack.size());
		return stack.pop();
	}

}
