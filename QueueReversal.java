package com.learn.ds.queue;

import com.learn.ds.stack.LinkedStack;

public class QueueReversal {
	public static LinkedQueue reverseQueue(LinkedQueue linkedQueue) throws Exception {
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		while (!linkedQueue.isEmpty()) {
			stack.push(linkedQueue.deQueue());
		}

		while (!stack.isEmpty()) {
			linkedQueue.enQueue(stack.pop());
		}
		return linkedQueue;
	}
}
