package lmu.cmsi281.assignments;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

class PreOrderTraversal {

	public void traverseIterative(BinaryTreeNodeString root, ArrayList<String> path) {
		if (root == null) {
			return;
		}
		Stack<BinaryTreeNodeString> stack = new Stack<BinaryTreeNodeString>();
		stack.push(root);
		while (!stack.isEmpty()) {
			BinaryTreeNodeString node = stack.peek();
			path.add(stack.pop().getData());
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
		}
	}

	public void traverseRecursive(BinaryTreeNodeString root, ArrayList<String> path) {
		if (root == null) {
			return;
		}
		path.add(root.getData());
		traverseRecursive(root.getLeft(), path);
		traverseRecursive(root.getRight(), path);
	}
}

class InOrderTraversal {

	public void traverseIterative(BinaryTreeNodeString root, ArrayList<String> path) {
		if (root == null) {
			return;
		}
		Stack<BinaryTreeNodeString> stack = new Stack<BinaryTreeNodeString>();
		BinaryTreeNodeString curr = root;
		while (curr != null || stack.size() > 0) {
			while (curr != null) {
				stack.push(curr);
				curr = curr.getLeft();
			}
			curr = stack.peek();
			path.add(stack.pop().getData());
			curr = curr.getRight();
		}
	}

	public void traverseRecursive(BinaryTreeNodeString root, ArrayList<String> path) {
		if (root == null) {
			return;
		}
		traverseRecursive(root.getLeft(), path);
		path.add(root.getData());
		traverseRecursive(root.getRight(), path);
	}
}

class PostOrderTraversal {

	public void traverseIterative(BinaryTreeNodeString root, ArrayList<String> path) {
		if (root == null) {
			return;
		}
		Stack<BinaryTreeNodeString> stack = new Stack<BinaryTreeNodeString>();
		BinaryTreeNodeString curr = root;
		while (curr != null || !stack.isEmpty()) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.getLeft();
			} else {
				BinaryTreeNodeString temp = stack.peek().getRight();
				if (temp == null) {
					temp = stack.pop();
					path.add(temp.getData());
					while (!stack.isEmpty() && temp == stack.peek().getRight()) {
						temp = stack.pop();
						path.add(temp.getData());
					}
				} else {
					curr = temp;
				}
			}
		}
	}

	public void traverseRecursive(BinaryTreeNodeString root, ArrayList<String> path) {
		if (root == null) {
			return;
		}
		traverseRecursive(root.getLeft(), path);
		traverseRecursive(root.getRight(), path);
		path.add(root.getData());
	}
}

class DepthFirstSearch {

	public Boolean searchIterative(BinaryTreeNodeString root, String value, ArrayList<String> path) {
		if (root.getData().equals(value)) {
			return true;
		}
		Stack<BinaryTreeNodeString> stack = new Stack<BinaryTreeNodeString>();
		stack.push(root);
		while (!stack.isEmpty()) {
			BinaryTreeNodeString node = stack.peek();
			path.add(stack.peek().getData());
			if (stack.pop().getData().equals(value)) {
				return true;
			}
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
		}
		return false;
	}

	public Boolean searchRecursive(BinaryTreeNodeString root, String value, ArrayList<String> path) {
		if (root == null) {
			return false;
		}
		path.add(root.getData());
		if (root.getData().equals(value)) {
			return true;
		} else {
			return searchRecursive(root.getLeft(), value, path) || searchRecursive(root.getRight(), value, path);
		}
	}
}

class BreadthFirstSearch {

	public Boolean searchIterative(BinaryTreeNodeString root, String value, ArrayList<String> path) {
		LinkedBlockingQueue<BinaryTreeNodeString> queue = new LinkedBlockingQueue<BinaryTreeNodeString>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			BinaryTreeNodeString curr = queue.poll();
			path.add(curr.getData());
			if (curr.getData().equals(value)) {
				return true;
			}
			if (curr.getLeft() != null) {
				queue.offer(curr.getLeft());
			}
			if (curr.getRight() != null) {
				queue.offer(curr.getRight());
			}
		}
		return false;
	}
}

public class BinaryTreeString {

	public static void main(String[] args) {
		BinaryTreeNodeString root = new BinaryTreeNodeString("A");
		root.setLeft("B");
		root.setRight("C");
		root.getLeft().setLeft("D");
		root.getLeft().setRight("E");
		root.getRight().setLeft("F");
		root.getRight().setRight("G");
		root.getLeft().getLeft().setLeft("H");
		root.getLeft().getLeft().setRight("I");
		root.getLeft().getRight().setLeft("J");

		ArrayList<String> path = new ArrayList<String>();

		PreOrderTraversal pre = new PreOrderTraversal();
		pre.traverseIterative(root, path);
		System.out.println("Using iterative pre order traversal:");
		// [A, B, D, H, I, E, J, C, F, G]
		System.out.println(path);
		path.clear();
		pre.traverseRecursive(root, path);
		System.out.println("Using recursive pre order traversal:");
		// [A, B, D, H, I, E, J, C, F, G]
		System.out.println(path);

		InOrderTraversal in = new InOrderTraversal();
		path.clear();
		in.traverseIterative(root, path);
		System.out.println("Using iterative in order traversal:");
		// [H, D, I, B, J, E, A, F, C, G]
		System.out.println(path);
		path.clear();
		in.traverseRecursive(root, path);
		System.out.println("Using recursive in order traversal:");
		// [H, D, I, B, J, E, A, F, C, G]
		System.out.println(path);

		PostOrderTraversal post = new PostOrderTraversal();
		path.clear();
		post.traverseIterative(root, path);
		System.out.println("Using iterative post order traversal:");
		// [H, I, D, J, E, B, F, G, C, A]
		System.out.println(path);
		path.clear();
		post.traverseRecursive(root, path);
		System.out.println("Using recursive post order traversal:");
		// [H, I, D, J, E, B, F, G, C, A]
		System.out.println(path);

		Boolean found;

		BreadthFirstSearch bfs = new BreadthFirstSearch();

		System.out.println("Using iterative breadth first search:");
		path.clear();
		found = bfs.searchIterative(root, "H", path);
		// Searching for H... Found=true
		// path=[A, B, C, D, E, F, G, H]
		System.out.println("Searching for H... " + "Found=" + found);
		System.out.println("path=" + path);

		path.clear();
		found = bfs.searchIterative(root, "G", path);
		// Searching for G... Found=true
		// path=[A, B, C, D, E, F, G]
		System.out.println("Searching for G... " + "Found=" + found);
		System.out.println("path=" + path);

		DepthFirstSearch dfs = new DepthFirstSearch();

		System.out.println("Using iterative depth first search:");
		path.clear();
		found = dfs.searchIterative(root, "H", path);
		// Searching for H... Found=true
		// path=[A, B, D, H]
		System.out.println("Searching for H... " + "Found=" + found);
		System.out.println("path=" + path);

		path.clear();
		found = dfs.searchIterative(root, "G", path);
		// Searching for G... Found=true
		// path=[A, B, D, H, I, E, J, C, F, G]
		System.out.println("Searching for G... " + "Found=" + found);
		System.out.println("path=" + path);

		path.clear();
		found = dfs.searchIterative(root, "Z", path);
		// Searching for Z... Found=false
		// path=[A, B, D, H, I, E, J, C, F, G]
		System.out.println("Searching for Z... " + "Found=" + found);
		System.out.println("path=" + path);

		System.out.println("Using recursive search:");
		path.clear();
		found = dfs.searchRecursive(root, "H", path);
		// Searching for H... Found=true
		// path=[A, B, D, H]
		System.out.println("Searching for H... " + "Found=" + found);
		System.out.println("path=" + path);

		path.clear();
		found = dfs.searchRecursive(root, "G", path);
		// Searching for G... Found=true
		// path=[A, B, D, H, I, E, J, C, F, G]
		System.out.println("Searching for G... " + "Found=" + found);
		System.out.println("path=" + path);
	}
}
