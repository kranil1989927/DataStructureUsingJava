package com.learn.ds.linkedList;

public class CircularLinkedList {

	protected CLLNode tail;
	protected int length;

	public CircularLinkedList() {
		this.tail = null;
		this.length = 0;
	}

	public void add(int data) {
		this.addToHead(data);
	}

	public void addToHead(int data) {
		CLLNode temp = new CLLNode(data);
		if (tail == null) {
			tail = temp;
			tail.setNext(temp);
		} else {
			temp.setNext(tail.getNext());
			tail.setNext(temp);
		}
		length++;
	}

	public void addToTail(int data) {
		this.addToHead(data);
		tail = tail.getNext();
	}

	public int peek() {
		return tail.getNext().getData();
	}

	public int tailPeek() {
		return tail.getData();
	}

	// Return and remove data from the list
	public int removeFromhead() {
		CLLNode temp = tail.getNext(); // This is the head of list
		if (tail == tail.getNext()) { // If it is single node
			tail = null;
		} else {
			tail.setNext(temp.getNext());
			temp.setNext(null);
		}

		length--;
		return temp.getData();
	}

	// Remove from Tail
	public int removeFromTail() {
		if (tail == null) {
			return Integer.MIN_VALUE;
		}
		CLLNode ptr = tail;
		while (ptr.getNext() != tail) {
			ptr = ptr.getNext();
		}

		CLLNode temp = tail;
		if (ptr == tail) {
			tail = null;
		} else {
			ptr.setNext(temp.getNext());
			tail = ptr;
		}
		length--;
		return temp.getData();
	}

	public boolean contains(int data) {
		if (tail == null) {
			return false;
		}

		CLLNode ptr;
		ptr = tail.getNext();
		while (ptr != tail && (!(ptr.getData() == data))) {
			ptr = ptr.getNext();
		}

		return ptr.getData() == data;
	}

	// Remove and return element equal to data or null
	public int remove(int data) {
		if (tail == null) {
			return Integer.MIN_VALUE;
		}

		CLLNode ptr = tail.getNext();
		CLLNode prePtr = tail;
		int compares;

		for (compares = 0; compares < length && (!(ptr.getData() == data)); compares++) {
			prePtr = ptr;
			ptr = ptr.getNext();
		}

		if (ptr.getData() == data) {
			if (tail == tail.getNext()) {
				tail = null;
			} else {
				if (ptr == tail) {
					tail = prePtr;
				}
				prePtr.setNext(prePtr.getNext().getNext());
			}
			ptr.setNext(null);
			length--;
		}

		return ptr.getData();
	}
}
