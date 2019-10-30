package lmu.cmsi281.assignments;

import java.util.ArrayList;
import java.util.Random;

class HashSetIntOp {
	
	public static void sort(ArrayList<Integer> arr) {
		
		for (int i = 0; i < arr.size(); i++) {
			int min = i;
			for (int j = i+1; j < arr.size(); j++) {
				if (arr.get(j) < arr.get(min)) {
					min = j;
				}
			}
			int temp = arr.get(i);
			arr.set(i, arr.get(min));
			arr.set(min, temp);
		}
	}

	static HashSetInt union(HashSetInt hashSetA, HashSetInt hashSetB) {
		ArrayList<Integer> hashListA = hashSetA.toList();
		ArrayList<Integer> hashListB = hashSetB.toList();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		HashSetInt unionized = new HashSetInt();
		for (int i = 0; i < hashListA.size(); i++) {
			arr.add(hashListA.get(i));
		}
		for (int i = 0; i < hashListB.size(); i++) {
			if (!arr.contains(hashListB.get(i))) {
				arr.add(hashListB.get(i));
			}
		}
		for (int i = 0; i < arr.size(); i++) {
			unionized.add(arr.get(i));
		}
		return unionized;
	}
	
	public static HashSetInt intersection(HashSetInt hashSetA, HashSetInt hashSetB) {
		ArrayList<Integer> hashListA = hashSetA.toList();
		ArrayList<Integer> hashListB = hashSetB.toList();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		HashSetInt intersectionized = new HashSetInt();
		for (int i = 0; i < hashListA.size(); i++) {
			if (hashListB.contains(hashListA.get(i))) {
				arr.add(hashListA.get(i));
			}
		}
		for (int i = 0; i < arr.size(); i++) {
			intersectionized.add(arr.get(i));
		}
		return intersectionized;
	}
	
	public static HashSetInt difference(HashSetInt hashSetA, HashSetInt hashSetB) {
		ArrayList<Integer> hashListA = hashSetA.toList();
		ArrayList<Integer> hashListB = hashSetB.toList();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		HashSetInt differentiaited = new HashSetInt();
		for (int i = 0; i < hashListA.size(); i++) {
			if (!hashListB.contains(hashListA.get(i))) {
				arr.add(hashListA.get(i));
			}
		}
		for (int i = 0; i < arr.size(); i++) {
			differentiaited.add(arr.get(i));
		}
		return differentiaited;
	}
}

public class HashSetInt {

	private int N_BUCKET = (int)Math.pow(2, 15);
	private ArrayList<LinkedListInt> hashSet;
	private double seed;
	
	public HashSetInt() {
		hashSet = new ArrayList<LinkedListInt>(N_BUCKET);
		for (int i = 0; i < N_BUCKET; i++) {
			hashSet.add(new LinkedListInt());
		}
		Random rand = new Random();
		seed = rand.nextInt() * rand.nextDouble();
	}
	
	private int hash(int element) {
		double x = Math.abs(element * seed);
		double h = x - Math.floor(x);
		int index = (int)Math.floor(h * N_BUCKET); 
		return index;
	}
	
	public void add(int element) {
		int index = hash(element);
		LinkedListInt chain = hashSet.get(index);
		if (!chain.contains(element)) {
			chain.add(element);
		}
	}
	
	public void remove(int element) {
		int index = hash(element);
		LinkedListInt chain = hashSet.get(index);
		boolean found = false;
		int removeIndex;
		for (removeIndex = 0; removeIndex < chain.size(); removeIndex++) {
			if (chain.get(removeIndex) == element) {
				found = true;
				break;
			}
		}
		if (found) {
			chain.remove(removeIndex);
		}
	}
	
	public boolean contains(int element) {
		int index = hash(element);
		LinkedListInt chain = hashSet.get(index);
		if (chain.contains(element)) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Integer> toList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < hashSet.size(); i++) {
			LinkedListInt chain = hashSet.get(i);
			for (int j = 0; j < chain.size(); j++) {
				list.add(chain.get(j));
			}
		}
		return list;
	}
	
	public static void main(String[] args) { 
		HashSetInt hashSetA = new HashSetInt();
		HashSetInt hashSetB = new HashSetInt();
		hashSetA.add(1);
		hashSetA.add(2);
		hashSetA.add(3);
		hashSetA.add(4);
		hashSetA.add(5);
		hashSetB.add(3);
		hashSetB.add(4);
		hashSetB.add(5);
		hashSetB.add(6);
		hashSetB.add(7);

		HashSetInt hashSetAuB = HashSetIntOp.union(hashSetA, hashSetB);
		HashSetInt hashSetAiB = HashSetIntOp.intersection(hashSetA, hashSetB);
		HashSetInt hashSetAdB = HashSetIntOp.difference(hashSetA, hashSetB);
		
		System.out.println(hashSetAuB.toList());	// [7, 6, 5, 4, 3, 2, 1]
		System.out.println(hashSetAiB.toList());	// [3, 4, 5]
		System.out.println(hashSetAdB.toList()); 	// [1, 2]
	}
}
