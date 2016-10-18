package com.learn.ds.queue;

public class FixedSizeArrayQueue {
	private int[] queueRep;
	private int size, front, rear;
	private static final int MAX = 16;

	public FixedSizeArrayQueue() {
		queueRep = new int[MAX];
		size = 0;
		front = 0;
		rear = 0;
	}

	public FixedSizeArrayQueue(int capacity) {
		queueRep = new int[capacity];
		size = 0;
		front = 0;
		rear = 0;
	}

	public void enQueue(int data) throws NullPointerException, IllegalStateException {
		if (size == MAX) {
			throw new IllegalStateException("Queue is fill : Overflow");
		} else {
			size++;
			queueRep[rear] = data;
			rear = (rear + 1) % MAX;
		}
	}

	public int deQueue() throws IllegalStateException {
		if (size == 0) {
			throw new IllegalStateException("Queue is empty : Undefined");
		} else {
			size--;
			int data = queueRep[(front % MAX)];
			queueRep[front] = Integer.MIN_VALUE;
			front = (front + 1) % MAX;
			return data;
		}
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public boolean isFull() {
		return (size == MAX);
	}

	public int size() {
		return size;
	}
}
