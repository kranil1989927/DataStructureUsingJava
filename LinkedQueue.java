package com.learn.ds.queue;

import com.learn.ds.linkedList.LinkedListNode;

public class LinkedQueue {

	private int length;
	private LinkedListNode front, rear;

	public LinkedQueue() {
		this.length = 0;
		this.front = null;
		this.rear = null;
	}

	public void enQueue(int data) {
		LinkedListNode node = new LinkedListNode(data);
		if (isEmpty()) {
			front = node;
		} else {
			rear.setNext(node);
		}
		rear = node;
		length++;
	}

	public int deQueue() throws Exception {
		if (isEmpty()) {
			throw new Exception("Queue : Undefined");
		}
		int result = front.getData();
		front = front.getNext();
		length--;
		if (isEmpty()) {
			rear = null;
		}
		return result;
	}

	public int first() throws Exception {
		if (isEmpty()) {
			throw new Exception("Queue : Undefined");
		}
		return front.getData();
	}

	public boolean isEmpty() {
		return (length == 0);
	}

	public int size() {
		return length;
	}
}
