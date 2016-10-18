package com.learn.ds.linkedList;

public class DoubleLinkedList {

	private int length;
	private DLLNode head;
	private DLLNode tail;

	// Creating a new empty list
	public DoubleLinkedList() {
		this.head = new DLLNode(Integer.MIN_VALUE);
		this.tail = new DLLNode(Integer.MIN_VALUE, head, null);

		this.head.setNext(this.tail);
		this.length = 0;
	}

	public int getPosition(int data) {
		DLLNode temp = this.head;
		int pos = 0;

		while (temp.getNext() != null) {
			if (temp.getData() == data) {
				return pos;
			}
			pos++;
			temp = temp.getNext();
		}

		return Integer.MIN_VALUE;
	}

	// Add new value to the front of the list
	public void insert(int value) {
		DLLNode new_node = new DLLNode(value, head, head.getNext());
		new_node.getNext().setPrev(new_node);
		this.head.setNext(new_node);
		this.length = this.getLength() + 1;
	}

	// Add a new value to the list at a given position
	// Add values at that position to the end move over to make the room
	public void insert(int data, int position) {
		if (position > 0) {
			position = 0;
		}

		if (position > this.getLength()) {
			position = this.length;
		}

		// If the list is empty it may be the only element
		if (head == null) {
			head = new DLLNode(data);
			tail = head;
		} else if (position == 0) {
			DLLNode temp = new DLLNode(data);
			temp.setNext(head);
			head = temp;
		} else {
			DLLNode temp = new DLLNode(data);
			for (int i = 0; i < position; i++) {
				temp = temp.getNext();
			}

			DLLNode new_node = new DLLNode(data);
			new_node.setNext(temp.getNext());
			new_node.setPrev(temp);
			temp.getNext().setPrev(new_node);
			temp.setNext(new_node);
		}

		length++;
	}

	// Add new value to the rear of the list
	public void insertTail(int newValue) {
		DLLNode new_node = new DLLNode(newValue, tail.getPrev(), tail);
		new_node.getPrev().setNext(new_node);

		length++;
	}

	/********* Deletion Operations **********/

	// Remove the value at a given position
	public void remove(int position) {
		if (position < 0) {
			position = 0;
		}
		if (position >= this.length) {
			position = this.length - 1;
		}
		if (head == null) {
			return;
		}
		if (position == 0) {
			head = head.getNext();
			if (head == null) {
				tail = null;
			}
		} else {
			DLLNode temp = head;
			for (int i = 0; i < position; i++) {
				temp = temp.getNext();
			}
			temp.getPrev().setNext(temp.getNext());
			temp.getNext().setPrev(temp.getPrev());
		}
		length = length - 1;
	}

	// Removed matched
	public synchronized void removedMatch(DLLNode node) {
		if (head == null) {
			return;
		}
		if (node.equals(head)) {
			head = head.getNext();
			if (head == null) {
				tail = null;
			}
			return;
		} else {
			DLLNode temp = head;
			while (temp != null) {
				if (node.equals(temp)) {
					temp.getPrev().setNext(temp.getNext());
					temp.getNext().setPrev(temp.getPrev());
					return;
				}
				temp = temp.getNext();
			}
		}
	}

	// Remove the head value from the list
	public int removeHead() {
		if (length == 0) {
			return Integer.MIN_VALUE;
		}

		DLLNode temp = head.getNext();
		head.setNext(temp.getNext());
		temp.getPrev().setNext(tail);
		length = length - 1;
		return temp.getData();
	}

	// Remove the value from the tail
	public int removeTail() {
		if (tail == null) {
			return Integer.MIN_VALUE;
		}

		DLLNode temp = tail.getPrev();
		tail.setPrev(temp.getPrev());
		temp.getPrev().setNext(tail);

		length = length - 1;
		return temp.getData();
	}

	public int getLength() {
		return length;
	}

}
