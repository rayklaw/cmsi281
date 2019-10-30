package lmu.cmsi281.assignments;

/**
 * CMSI Assignment 2
 * 
 * @author <Law, Raymond>
 *
 */
public class LinkedListInt {

	int size;
	Node head;

	class Node {
		int data;
		Node next;

		public Node(int d) {
			data = d;
			next = null;
		}
	}

	public LinkedListInt() {
		size = 0;
		head = null;
	}

	// Returns the current size of the Linked List
	public int size() {
		return size;
	}

	// Returns the value stored in the data field of the Node at the given index
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public int get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// Iterate to specified position
		Node curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr.data;
	}

	// Sets the value stored in the data field of the Node at the given index with
	// element
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void set(int index, int element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node curr = head;
		for (int i = 0; i <= index; i++) {
			if (i == index) {
				curr.data = element;
			}
			curr = curr.next;
		}
	}

	// Adds a new element to the Linked List by adding a new Node
	public void add(int element) {
		Node node = new Node(element);
		// If there is nothing in the Linked List
		if (head == null) {
			head = node;
		} else {
			Node curr = head;
			// Iterate until we arrive at the last node
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = node;
		}
		size = size + 1;
	}

	// Inserts a new element as a new Node into the Linked List at the given index
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void insert(int index, int element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		Node node = new Node(element);
		if (index == size()) {
			add(element);
			return;
		}
		if (index == 0) {
			node.next = head;
			head = node;
			size += 1;
			return;
		}
		Node curr = head;
		for (int i = 0; i <= index; i++) {
			if (i == index - 1) {
				node.next = curr.next;
				curr.next = node;
			}
			curr = curr.next;
		}
		size += 1;
	}

	// Removes the element at a given index from the Linked List
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node curr = head;
		Node prev = head;
		if (index == 0) {
			head = head.next;
			size -= 1;
			return;
		}
		for (int i = 0; i < index; i++) {
			prev = curr;
			curr = curr.next;
		}
		prev.next = curr.next;
		size -= 1;
	}

	// Returns true if the Linked List contains the element, else false
	public boolean contains(int element) {
		Node curr = head;
		for (int i = 0; i < size(); i++) {
			if (curr.data == element) {
				return true;
			}
			curr = curr.next;
		}
		return false;
	}

	// Add all the given elements as Nodes to the Linked List at the given index
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void addAll(int index, int[] elements) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (elements.length == 0) {
			return;
		}
		Node arrHead = new Node(elements[0]);
		Node arrCurr = arrHead;
		for (int i = 1; i < elements.length; i++) {
			arrCurr.next = new Node(elements[i]);
			arrCurr = arrCurr.next;
		}
		if (index == 0) {
			arrCurr.next = head;
			head = arrHead;
			size = size + elements.length;
			return;
		}
		Node prev = head;
		Node curr = head;
		for (int i = 0; i < index; i++) {
			prev = curr;
			curr = curr.next;
		}
		prev.next = arrHead;
		arrCurr.next = curr;
		size = size + elements.length;
	}

	public String toString() {
		if (head == null) {
			return "[ ]";
		}
		String out = "[ ";
		Node curr = head;
		while (curr.next != null) {
			out = out + curr.data + ", ";
			curr = curr.next;
		}

		out = out + curr.data + " ]";
		return out;
	}

	public static void main(String[] args) {
		LinkedListInt a = new LinkedListInt();
		LinkedListInt b = new LinkedListInt();

		a.add(7);
		a.add(11);
		a.add(93);
		System.out.println("a = " + a.toString());
		b.add(7);
		b.add(11);
		b.add(93);
		System.out.println("b = " + b.toString());

		a.insert(0, 15);
		a.insert(3, 44);
		a.insert(a.size(), 20);
		System.out.println("a = " + a.toString());

		a.set(0, 20);
		a.set(3, 15);
		a.set(5, 44);
		System.out.println("a = " + a.toString());

		a.remove(0);
		System.out.println("a = " + a.toString());
		a.remove(2);
		System.out.println("a = " + a.toString());
		a.remove(3);
		System.out.println("a = " + a.toString());

		a.addAll(3, new int[] { 0, 1, 2 });
		System.out.println("a = " + a.toString());
	}
}
