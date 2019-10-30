package lmu.cmsi281.assignments;

import java.util.ArrayList;

class HeapNodeInt {
	private int data;
	private int index;
	
	public HeapNodeInt(int element, int n) {
		data = element;
		index = n;
	}
	
	public int getData() {
		return data;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setData(int element) {
		data = element;
	}
	
	public void setIndex(int n) {
		index = n;
	}
}

public class MaxHeapInt {
	private ArrayList<HeapNodeInt> heap; 
	
	public MaxHeapInt() {
		heap = new ArrayList<HeapNodeInt>();
	}
	
	public HeapNodeInt getRoot() {
		if (heap.isEmpty()) {
			return null;
		}
		return heap.get(0);
	}
	
	public HeapNodeInt getLeft(HeapNodeInt node) {
		int leftIndex = node.getIndex() * 2 + 1;
		if (leftIndex >= heap.size()) {
			return null;
		}
		return heap.get(leftIndex);
	}
	
	public HeapNodeInt getRight(HeapNodeInt node) {
		int rightIndex = node.getIndex() * 2 + 2;
		if (rightIndex >= heap.size()) {
			return null;
		}
		return heap.get(rightIndex);
	}
	
	public HeapNodeInt getParent(HeapNodeInt node) { 
		if (node.getIndex() == 0) {
			return null;
		}
		int parentIndex = (node.getIndex() - 1) / 2;
		return heap.get(parentIndex);
	}
	
	public void addNode(int element) {
		heap.add(new HeapNodeInt(element, heap.size()));
		HeapNodeInt node = heap.get(heap.size() - 1);
		HeapNodeInt parent = getParent(node);
		while (parent != null) {
			if (parent.getData() < node.getData()) {
				swap(parent, node);
				parent = getParent(heap.get(node.getIndex()));
			} else {
				break;
			}
		}
	}
	
	public void deleteNode(int element) {
		int i;
		boolean found = false;
		for (i = 0; i < heap.size(); i++) {
			if (element == heap.get(i).getData()) {
				found = true;
				break;
			}
		}
		if (found) {
			heap.get(i).setData(heap.get(heap.size() - 1).getData());
			heap.remove(heap.size() - 1);
			HeapNodeInt node = heap.get(i);
			HeapNodeInt left = getLeft(node);
			HeapNodeInt right = getRight(node);
			while (true) {
				if (left != null && right != null && node.getData() < left.getData() && left.getData() > right.getData()) {
					swap(node, left);
					left = getLeft(node);
				} else if (left != null && right != null && node.getData() < right.getData() && left.getData() < right.getData()) {
					swap(node, right);
					right = getRight(node);
				} else if (left != null && right == null && node.getData() < left.getData()) {
					swap(node, left);
					left = getLeft(node);
				} else if (left == null && right != null && node.getData() < right.getData()) {
					swap(node, right);
					right = getRight(node);
				} else {
					break;
				}
			}
		}
	}
	
	void swap(HeapNodeInt nodeA, HeapNodeInt nodeB) {
		int tempIndex = nodeA.getIndex();
		nodeA.setIndex(nodeB.getIndex());
		nodeB.setIndex(tempIndex);
		heap.set(nodeA.getIndex(), nodeA);
		heap.set(nodeB.getIndex(), nodeB);
	}
	
	public ArrayList<Integer> toArray() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int index = 0; index < heap.size(); index++) {
			arr.add(heap.get(index).getData());
		}
		return arr;
	}
	
	public static void main(String[] args) {
		MaxHeapInt maxHeap = new MaxHeapInt();
		maxHeap.addNode(35);
		maxHeap.addNode(33);
		maxHeap.addNode(42);
		maxHeap.addNode(10);
		maxHeap.addNode(14);
		maxHeap.addNode(19);
		maxHeap.addNode(27);
		maxHeap.addNode(44);
		maxHeap.addNode(26);
		maxHeap.addNode(31);
		System.out.println(maxHeap.toArray().toString());
		maxHeap.deleteNode(44);
		System.out.println(maxHeap.toArray().toString());
		maxHeap.deleteNode(31);
		System.out.println(maxHeap.toArray().toString());
	}
}
