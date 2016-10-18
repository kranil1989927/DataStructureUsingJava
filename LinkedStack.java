package com.learn.ds.stack;

import java.util.EmptyStackException;

import com.learn.ds.linkedList.LinkedListNode;

public class LinkedStack<T> {

	private int length;
	private LinkedListNode top;

	public LinkedStack() {
		this.length = 0;
		this.top = null;
	}

	public void push(int data) {
		LinkedListNode temp = new LinkedListNode(data);
		temp.setNext(top);
		top = temp;
		length++;
	}

	public int pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		int result = top.getData();
		top = top.getNext();
		length--;

		return result;
	}

	public int peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		return top.getData();
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public int size() {
		return length;
	}
}
