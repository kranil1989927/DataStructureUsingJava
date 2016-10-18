package com.learn.ds.stack;

/*
 * Design a stack such that getMinimun() should o(1). Take an auxiliary
 * stack that maintains the minimum of all values in the stack. Also assumes
 * that, each element of the stack is less than its below elements
 */
public class AdvancedStack {

	private LinkedStack<Integer> elementStack = new LinkedStack<Integer>();
	private LinkedStack<Integer> minStack = new LinkedStack<Integer>();

	public void push(int data) {
		elementStack.push(data);

		if (minStack.isEmpty() || (Integer) minStack.peek() >= (Integer) data) {
			minStack.push(data);
		} else {
			minStack.push(minStack.pop());
		}
	}

	public int pop() {
		if (elementStack.isEmpty())
			return -1;
		minStack.pop();
		return elementStack.pop();
	}

	public int getMinimum() {
		return elementStack.pop();
	}

	public int top() {
		return elementStack.peek();
	}
}
