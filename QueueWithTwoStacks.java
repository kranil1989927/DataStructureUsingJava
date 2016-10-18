package com.learn.ds.queue;

import com.learn.ds.stack.LinkedStack;

// Implementation of queue using two stacks
public class QueueWithTwoStacks<T> {

	private LinkedStack<T> s1 = new LinkedStack<T>();
	private LinkedStack<T> s2 = new LinkedStack<T>();

	public void enQueue(T data) {
		s1.push((int) data);
	}
	
	public int deQueue() {
		if (s2.isEmpty()) {
			while (!s2.isEmpty()) {
				s2.push(s1.pop());
			}
		}
		return s2.pop();
	}
}
