package lmu.cmsi281.assignments;

import java.util.ArrayList;
import java.util.Stack;

public class InfixToPostfixEvaluation {

	public static int getPrecedence(String operator) {
		// Addition and subtraction holds least precedence
		if (operator.equals("+") || operator.equals("-")) {
			return 1;
		}
		// Multiplication and division hold higher precedence
		if (operator.equals("*") || operator.equals("/")) {
			return 2;
		}
		// Exponent holds highest precedence
		if (operator.equals("^")) {
			return 3;
		}
		// Invalid operand
		return -1;
	}

	public static boolean isDigit(String s) {
		try {
			toDigit(s);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static int toDigit(String s) throws NumberFormatException {
		return Integer.parseInt(s);
	}

	public static boolean isValidSymbol(String s) {
		if (isDigit(s)) {
			return true;
		}

		switch (s) {
		case "+":
		case "-":
		case "*":
		case "/":
		case "^":
		case "(":
		case ")":
			return true;
		}
		return false;
	}

	public static boolean isNonCommutative(String s) {
		switch (s) {
		case "-":
		case "/":
		case "^":
			return true;
		}
		return false;
	}

	public static ArrayList<String> infixToPostfix(String infix) throws RuntimeException {

		/**
		 * Infix to Postfix Algorithm Given a list of tokens:
		 * 
		 * If the token is not a numeric nor a valid symbol then we will throw a
		 * RuntimeException If the token is a numeric (operand) then we add it to the
		 * result If the token is a "(" then we push it onto the stack If the token is a
		 * ")" then we pop each operator off the stack and add to our result until we
		 * reach "(" which we also pop off If the token is an operator: "+", "-", "*",
		 * "/", "^" then check the precedence of the operator on the stack, and while
		 * the operators on the stack has more than or equal precedence to the one at
		 * hand, we pop them off the stack and add them to our result until we reach one
		 * that has less precedence than the one we are processing we then push our
		 * operator onto the stack Once we are done with processing our tokens, pop
		 * every element off the stack and add it to our result
		 */

		ArrayList<String> result = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();

		// Splits the infix based on spaces: "1 + 2" --> [ 1, +, 2 ]
		String[] tokens = (infix.trim() + " )").split(" ");

		for (int i = 0; i < tokens.length; i++) {
			if (!isValidSymbol(tokens[i])) {
				throw new RuntimeException();
			}
			if (i == 0) {
				stack.push("(");
			}
			if (isDigit(tokens[i])) {
				result.add(tokens[i]);
			}
			if (!isDigit(tokens[i]) && !tokens[i].equals(")")) {
				while (getPrecedence(stack.peek()) >= getPrecedence(tokens[i]) && !tokens[i].equals("(")) {
					result.add(stack.pop());
				}
				stack.push(tokens[i]);
			}
			if (tokens[i].equals(")")) {
				while (!stack.peek().equals("(")) {
					result.add(stack.pop());
				}
				stack.pop();
			}
		}

		return result;
	}

	public static Integer evaluatePostfix(ArrayList<String> postfix) throws RuntimeException {

		/**
		 * Postfix Evaluation Algorithm Given a list of tokens of a postfix expression:
		 * 
		 * If the token is not a numeric nor a valid symbol then we will throw a
		 * RuntimeException If the token is a numeric (operand) then we add it to the
		 * operands stack If the token is an operator: "+", "-", "*", "/", "^" then we
		 * evaluate the first two elements in operands using the operator we then push
		 * result back onto the operands stack Once we are done with processing our
		 * tokens, we return the result
		 *
		 * If the postfix expression is invalid, we should throw a RuntimeException
		 */

		Stack<String> operands = new Stack<String>();

		for (int i = 0; i < postfix.size(); i++) {
			if (!isValidSymbol(postfix.get(i))) {
				throw new RuntimeException();
			}
			if (isDigit(postfix.get(i))) {
				operands.push(postfix.get(i));
			}
			if (!isDigit(postfix.get(i))) {
				if (isNonCommutative(postfix.get(i))) {
					Integer a = toDigit(operands.pop());
					Integer b = toDigit(operands.pop());
					operands.push(Integer.toString(evaluate(postfix.get(i), b, a)));
				} else {
					operands.push(Integer
							.toString(evaluate(postfix.get(i), toDigit(operands.pop()), toDigit(operands.pop()))));
				}
			}
		}

		return toDigit(operands.pop());
	}

	public static Integer evaluate(String operator, int a, int b) throws RuntimeException {

		if (operator.equals("+")) {
			return a + b;
		}

		if (operator.equals("-")) {
			return a - b;
		}

		if (operator.equals("*")) {
			return a * b;
		}

		if (operator.equals("/")) {
			return a / b;
		}

		if (operator.equals("^")) {
			return (int) Math.pow(a, b);
		}
		// Invalid operator
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		String infix0 = "1 * ( 2 + 3 ) / 4 + 5";
		String infix1 = "1 + 2 * 3 / 4 - 5 + 6 / 7 ";
		String infix2 = "1 + 2 * ( 3 ^ 4 - 5 ) + ( 6 + 7 * 8 ) - 9";

		ArrayList<String> postfix0 = infixToPostfix(infix0);
		ArrayList<String> postfix1 = infixToPostfix(infix1);
		ArrayList<String> postfix2 = infixToPostfix(infix2);

		System.out.println(postfix0); // [1, 2, 3, +, *, 4, /, 5, +]
		System.out.println(postfix1); // [1, 2, 3, *, 4, /, +, 5, -, 6, 7, /, +]
		System.out.println(postfix2); // [1, 2, 3, 4, ^, 5, -, *, +, 6, 7, 8, *, +, +, 9, -]

		System.out.println(evaluatePostfix(postfix0)); // 6
		System.out.println(evaluatePostfix(postfix1)); // -3
		System.out.println(evaluatePostfix(postfix2)); // 206
	}
}
