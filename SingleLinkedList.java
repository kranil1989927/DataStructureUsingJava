package com.learn.ds.linkedList;

public class SingleLinkedList {

	private int length;
	private LinkedListNode head;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public SingleLinkedList() {
		this.setLength(0);
	}

	// First node of the list
	public synchronized LinkedListNode getHead() {
		return this.head;
	}

	// Insert a node at the beginning of the list
	public synchronized void insertAtBegin(LinkedListNode listNode) {
		listNode.setNext(this.head);
		listNode = this.head;
		this.setLength(this.getLength() + 1);
	}

	// Insert a node at the end of the List
	public synchronized void insertAtEnd(LinkedListNode listNode) {
		if (this.head == null) {
			this.head = listNode;
		} else {
			LinkedListNode ptr;
			ptr = this.head;

			while (ptr.getNext() != null) {
				ptr = ptr.getNext();
			}
			ptr.setNext(listNode);
		}
	}

	// Add a new value to the list at a given location.
	// Add the values to the position at the end move over to make room
	public void insert(int data, int position) {
		if (position < 0) {
			position = 0;
		}

		if (position > this.length) {
			position = this.length;
		}

		if (this.head == null) { // Only head be only element
			this.head = new LinkedListNode(data);
		} else if (position == 0) { // Adding at the front of the list
			LinkedListNode listNode = new LinkedListNode(data);
			listNode.setNext(this.head);
			this.head = listNode;

		} else { // Find the correct position and insert

			LinkedListNode temp = this.head;
			for (int i = 0; i < position; i++) {
				temp = temp.getNext();
			}
			LinkedListNode newNode = new LinkedListNode(data);
			newNode.setNext(temp.getNext());
			temp.setNext(newNode);
		}
	}

	// Deletion of element in linked list

	// Remove and return the node at the head of the list
	public synchronized LinkedListNode removeFromBegin() {
		LinkedListNode listNode = head;
		if (listNode != null) {
			head = listNode.getNext();
			listNode.setNext(null);
		}
		return listNode;
	}

	// Remove and return the node at the end of the list
	public synchronized LinkedListNode removeFromEnd() {
		if (head == null) {
			return null;
		}
		LinkedListNode ptr = head;
		LinkedListNode prePtr = ptr;
		while (ptr.getNext() != null) {
			prePtr = ptr;
			ptr = ptr.getNext();
		}

		prePtr.setNext(null);
		return ptr;
	}

	// Remove a node matching the specified node from the list
	public synchronized void removeMatched(LinkedListNode listNode) {
		if (head == null) {
			return;
		}

		if (listNode.equals(head)) {
			head = head.getNext();
			return;
		}

		LinkedListNode ptr = head;
		LinkedListNode prePtr = ptr;
		while (ptr.getNext() != null) {
			if (listNode.equals(ptr)) {
				prePtr.setNext(ptr.getNext());
				return;
			}
			prePtr = ptr;
			ptr = ptr.getNext();
		}
	}

	// Remove the value at a given position
	public void remove(int position) {
		if (position < 0) {
			position = 0;
		}

		if (position > length) {
			position = length;
		}

		if (head == null) {
			return;
		}

		if (position == 0) { // Remove the first element
			head = head.getNext();
		} else {
			LinkedListNode ptr = head;
			LinkedListNode prePtr = ptr;
			for (int i = 0; i < position; i++) {
				prePtr = ptr;
				ptr = ptr.getNext();
			}

			prePtr.setNext(ptr.getNext());
		}
	}

	// Get the position of the first value that is equal to a given value
	public int getPosition(int data) {
		LinkedListNode temp = null;
		int position = 0;
		while (temp != null) {
			if (temp.getData() == data) {
				return position;
			}

			temp = temp.getNext();
			position++;
		}
		return Integer.MIN_VALUE;
	}

	@Override
	public String toString() {
		String result = "[";
		if (head == null) {
			return result + "]";
		}
		result = result + head.getData();
		LinkedListNode temp = head.getNext();
		while (temp != null) {
			result = result + ", " + temp.getData();
			temp = temp.getNext();
		}
		return result + "]";
	}

}
